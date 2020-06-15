package controler;

import connection.HibernateUtil;
import entity.Membre;
import entity.Photo;
import org.hibernate.Session;

import java.util.Set;

public class MembreControler {

    public Membre getByID(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Membre.class,id);
    }

    public Membre insert(String nom, String prenom, String description){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Membre membre = new Membre(nom,prenom,description);
        session.save(membre);
        return membre;
    }

    public Membre update(int id, String nom, String prenom, String description){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Membre membre = session.get(Membre.class,id);
        if(nom!=null && !nom.isEmpty())
            membre.setNom(nom);
        if(prenom!=null && !prenom.isEmpty())
            membre.setPrenom(prenom);
        if(description!=null && description.isEmpty())
            membre.setDescription(description);
        session.save(membre);
        return membre;
    }

    public Set<Photo> delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Membre membre = session.get(Membre.class,id);
        Set<Photo> photos = membre.getPhotos();
        session.delete(membre);
        return photos;
    }
}
