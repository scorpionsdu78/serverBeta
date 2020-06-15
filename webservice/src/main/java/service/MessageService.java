package service;

import connection.HibernateUtil;
import controler.MessageControler;
import entity.Message;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Scanner;

@Path("Message")
public class MessageService {

    MessageControler controler;

    public MessageService() {
        controler = new MessageControler();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessageID(){
        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Session session = null;
        Message message = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            message = controler.getMessage(id);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }

        return message;

    }

    public void createMessage() {
        Scanner sc = new Scanner(System.in);

        System.out.println("email envoyeur: ");
        String email = sc.nextLine();

        System.out.println("message: ");
        String message = sc.nextLine();

        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            controler.insertMessage(new Message(email,message,true));
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void supprMessage() {
        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            controler.DeleteMessage(id);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void edditMessage(){
        Session session = null;
        Message message = null;
        Transaction tx = null;

        message = new Message(2,"francois.boni@efrei.net","test",Boolean.FALSE);

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            message = controler.EditMessage(message);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void treated(){

        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();


        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            controler.treated(id);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

}
