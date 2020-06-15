package service;

import connection.HibernateUtil;
import controler.ArticleControler;
import controler.PhotoControler;
import entity.Article;
import entity.Message;
import entity.Photo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.ToolBox;

import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class ArticleService {
    ArticleControler controler;

    public ArticleService() {
        this.controler = new ArticleControler();
    }

    public Article getById(){
        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Session session = null;
        Article article = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            article = controler.getByID(id);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }

        return article;
    }

    public void Delete(){

        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Session session = null;
        Article article = null;
        Transaction tx = null;

        PhotoControler controlerPhoto = new PhotoControler();

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            Set<Photo> photos = controler.Delete(id);
            for (Photo p: photos) {
                p.setArticle_idarticle(null);
                session.persist(p);
            }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }

    }

    public Article Update(){

        Session session = null;
        Article article = null;
        Transaction tx = null;

        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("what date: ");
        Date creation = ToolBox.readDate(sc,"YYYY-MM-DD");

        System.out.println("content: ");
        String content = sc.nextLine();

        System.out.println("what title: ");
        String title = sc.nextLine();

        Boolean featured = true;


        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            article = controler.Update(id,creation,content,title,featured);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }

        return article;
    }

    public Article insert(){
        Session session = null;
        Article article = null;
        Transaction tx = null;

        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("what date: ");
        Date creation = ToolBox.readDate(sc,"YYYY-MM-DD");

        System.out.println("content: ");
        String content = sc.nextLine();

        System.out.println("what title: ");
        String title = sc.nextLine();

        Boolean featured = true;


        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            article = controler.insert(creation,content,title,featured);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }

        return article;
    }

    public Article insertWithPhoto(){
        Session session = null;
        Article article = null;
        Transaction tx = null;
        PhotoControler photoControler = new PhotoControler();

        Scanner sc = new Scanner(System.in);


        System.out.println("what date: ");
        Date creation = ToolBox.readDate(sc,"YYYY-MM-DD");

        System.out.println("content: ");
        String content = sc.nextLine();

        System.out.println("what title: ");
        String title = sc.nextLine();

        Boolean featured = true;


        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            article = controler.insert(creation,content,title,featured);
            if(article != null){
                System.out.println("how many pictures: ");
                int size = sc.nextInt();
                sc.nextLine();
                for (int i = 0; i <size ; i++) {
                    System.out.println("url:");
                    photoControler.insertPhoto(sc.nextLine(),article,null);
                }
            }
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }

        return article;

    }

}
