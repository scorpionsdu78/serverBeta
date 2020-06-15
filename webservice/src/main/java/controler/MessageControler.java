package controler;

import connection.HibernateUtil;
import entity.Message;
import org.hibernate.Session;

public class MessageControler {

    public Message getMessage(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Message.class,id);
    }

    public void insertMessage(Message message){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(message);
    }

    public void DeleteMessage(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Message message = session.get(Message.class,id);
        session.delete(message);
    }

    public Message EditMessage(Message message){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return (Message)session.merge(message);
    }

    public void treated(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Message message = getMessage(id);
        message.setTraite(Boolean.TRUE);
        session.persist(message);
    }

}
