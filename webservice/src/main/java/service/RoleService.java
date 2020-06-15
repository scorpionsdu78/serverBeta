package service;

import connection.HibernateUtil;
import controler.MembreControler;
import controler.RoleControler;
import entity.Membre;
import entity.Role;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class RoleService {

    private RoleControler controler;

    public RoleService() {
        controler = new RoleControler();
    }

    public Role getByIdWithMember(){
        System.out.println("what ID: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Session session = null;
        Transaction tx = null;
        Role role = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            role = controler.getByID(id);
            Hibernate.initialize(role.getMembreSet());

            tx.commit();

        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
        return role;
    }

    public Role getById(){
        System.out.println("what ID: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Session session = null;
        Transaction tx = null;
        Role role = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            role = controler.getByID(id);

            tx.commit();

        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
        return role;
    }

    public Role Insert(){
        System.out.println("nom du rôle: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        Session session = null;
        Transaction tx = null;
        Role role = null;

        MembreControler controleMember = new MembreControler();

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            role = controler.insert(name);
            Hibernate.initialize(role.getMembreSet());

            System.out.println("combien on ce role ");
            int size = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i <size ; i++) {
                System.out.println("qui a ce role: ");
                int member = sc.nextInt();
                sc.nextLine();

                role.getMembreSet().add(session.get(Membre.class,member));
            }


            tx.commit();

        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
        return role;
    }

    public void delete(){
        System.out.println("what id to delete? ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            controler.Delete(id);


            tx.commit();

        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }

    }


}
