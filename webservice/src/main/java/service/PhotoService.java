package service;

import connection.HibernateUtil;
import controler.PhotoControler;
import entity.Article;
import entity.Membre;
import entity.Message;
import entity.Photo;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class PhotoService {
    PhotoControler controler;

    public PhotoService(){
        controler = new PhotoControler();
    }

    public Photo getByid(){
        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id=sc.nextInt();

        Session session = null;
        Photo photo = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            photo = controler.getByID(id);
            if(photo.getArticle_idarticle() != null)
                Hibernate.initialize(photo.getArticle_idarticle());
            if(photo.getMembreIdmembre() != null)
                Hibernate.initialize(photo.getMembreIdmembre());
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }

        return photo;
    }

    public Photo newPhoto(){
        Article article = null;
        Membre membre = null;
        Session session = null;
        Transaction tx = null;
        Photo photo = null;


        System.out.println("URL: ");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();

        System.out.println("article: ");
        int articleID = sc.nextInt();
        sc.nextLine();


        System.out.println("Membre: ");
        int idMembre = sc.nextInt();
        sc.nextLine();


        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            if(articleID > 0 )
                article = session.get(Article.class,articleID);
            if(idMembre > 0 ){
                membre = session.get(Membre.class,idMembre);
            }

            photo = controler.insertPhoto(url,article,membre);


            tx.commit();

        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return photo;
    }

    public Photo editPhoto(){
        Session session = null;
        Transaction tx = null;
        Photo photo = null;

        Scanner sc = new Scanner(System.in);


        System.out.println("photo: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("URL: ");
        String url = sc.nextLine();

        System.out.println("article: ");
        int articleID = sc.nextInt();
        sc.nextLine();


        System.out.println("Membre: ");
        int idMembre = sc.nextInt();
        sc.nextLine();

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            photo = controler.EditPhoto(id,url,articleID,idMembre);
            if(photo.getArticle_idarticle() != null)
                Hibernate.initialize(photo.getArticle_idarticle());
            if(photo.getMembreIdmembre() != null)
                Hibernate.initialize(photo.getMembreIdmembre());
            tx.commit();

        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return photo;

    }

    public void Delete(){
        Session session = null;
        Transaction tx = null;

        Scanner sc = new Scanner(System.in);


        System.out.println("photo: ");
        int id = sc.nextInt();
        sc.nextLine();

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            controler.Delete(id);
            tx.commit();

        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }

    }
}
