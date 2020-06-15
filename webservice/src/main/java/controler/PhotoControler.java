package controler;

import connection.HibernateUtil;
import entity.Article;
import entity.Membre;
import entity.Photo;
import org.hibernate.Session;

public class PhotoControler {

    public Photo getByID(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //Photo photo = session.get(Photo.class,id);
        //System.out.println(photo);
        return session.get(Photo.class,id);
    }

    public Photo insertPhoto(String url, Article article, Membre membre){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Photo photo = new Photo(url,article,membre);
        session.save(photo);
        return photo;
    }

    public Photo EditPhoto(int id, String url, int idArticle, int idMembre){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Photo photo = session.get(Photo.class,id);
        if(url != null && !url.isEmpty()){
            photo.setUrl(url);
        }
        if (idArticle > 0){
            photo.setArticle_idarticle(session.get(Article.class,idArticle));
        }
        if(idMembre > 0){
            photo.setMembreIdmembre(session.get(Membre.class,idMembre));
        }
        session.save(photo);
        return photo;
    }

    public void RemovArticle(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Photo photo = session.get(Photo.class,id);
        photo.setArticle_idarticle(null);
        session.persist(photo);
    }

    public void RemovMembre(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Photo photo = session.get(Photo.class,id);
        photo.setMembreIdmembre(null);
        session.persist(photo);
    }

    public void Delete(int id){
        Session session =HibernateUtil.getSessionFactory().getCurrentSession();
        Photo photo = session.get(Photo.class,id);
        session.delete(photo);
    }
}
