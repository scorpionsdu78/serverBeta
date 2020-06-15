package service;

import connection.HibernateUtil;
import controler.MembreControler;
import controler.PhotoControler;
import entity.Membre;
import entity.Photo;
import entity.Role;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MembreService {
    MembreControler controler;

    public MembreService() {
        controler = new MembreControler();
    }

    public Membre getById(){
        System.out.println("what id: ");
        Scanner sc = new Scanner(System.in);
        int id=sc.nextInt();

        Session session = null;
        Membre membre = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            membre = controler.getByID(id);
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
        return membre;
    }

    public Membre insertComplete(){
        Scanner sc = new Scanner(System.in);

        System.out.println("nom: ");
        String nom = sc.nextLine();
        System.out.println("prenom: ");
        String prenom = sc.nextLine();
        System.out.println("Description: ");
        String description = sc.nextLine();

        Session session = null;
        Membre membre = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            membre = controler.insert(nom,prenom,description);
            membre.setRoles(new HashSet<Role>());

            System.out.println("how many roles : ");
            int size = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < size; i++) {
                System.out.println("what roles? ");
                int role = sc.nextInt();
                sc.nextLine();
                membre.getRoles().add(session.get(Role.class,role));
            }

            System.out.println("how many pic : ");
            size = sc.nextInt();
            sc.nextLine();

            PhotoControler photoControler = new PhotoControler();

            for (int i = 0; i <size ; i++) {
                System.out.println("url:");
                photoControler.insertPhoto(sc.nextLine(),null,membre);
            }

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

        return membre;
    }

    public void deleteCascade(){
        Scanner sc = new Scanner(System.in);

        System.out.println("what id: ");
        int id = sc.nextInt();

        Session session = null;
        Membre membre = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Set<Photo> photos = controler.delete(id);
            for (Photo p: photos) {
                p.setMembreIdmembre(null);
            }
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

    public Membre addRole(){
        System.out.println("to who?");
        Scanner sc = new Scanner(System.in);
        int idmembre = sc.nextInt();
        sc.nextLine();

        System.out.println("what Role?");
        int role = sc.nextInt();

        Session session = null;
        Membre membre = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            membre = controler.getByID(idmembre);
            membre.getRoles().add(session.get(Role.class,role));

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

        return membre;
    }
}
