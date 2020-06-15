package controler;

import connection.HibernateUtil;
import entity.Repertoire;
import org.hibernate.Session;

public class RepertoireControler {

    public Repertoire getById(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Repertoire.class,id);
    }

    public Repertoire insert(String nom, String auteur, Boolean active){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Repertoire repertoire = new Repertoire(nom,auteur,active);
        session.save(repertoire);
        return repertoire;
    }

    public void Delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Repertoire repertoire = session.get(Repertoire.class,id);
        session.delete(repertoire);
    }

    public Repertoire update(int id, String nom, String auteur, Boolean active){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Repertoire repertoire = session.get(Repertoire.class,id);
        repertoire.setNom(nom);
        repertoire.setAuteur(auteur);
        repertoire.setActive(active);
        session.save(repertoire);
        return repertoire;
    }
}
