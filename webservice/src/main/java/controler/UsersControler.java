package controler;

import connection.HibernateUtil;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsersControler {


    public Users getByid(int id){

        Session session = null;
        Users users = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.get(Users.class,id);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }

        return users;
    }

    public void createUser(Users users){
        Session session = null;
        Transaction tx =null;


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(users);
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
    }

    public void ChangePassword(int id, String password){
        Session session = null;
        Users users = null;
        Transaction tx =null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            users = session.get(Users.class,id);
            users.setPassword(password);
            tx.commit();

        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
    }

    public void Delete(int id){
        Session session = null;
        Transaction tx =null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Users users = session.get(Users.class,id);
            session.delete(users);
            tx.commit();

        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
    }

}
