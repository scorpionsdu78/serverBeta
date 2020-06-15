package service;

import connection.HibernateUtil;
import controler.RepertoireControler;
import entity.Membre;
import entity.Repertoire;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class RepertoireService {
    RepertoireControler controler;

    public RepertoireService(){
        controler = new RepertoireControler();
    }

    public Repertoire getById() {
        Scanner sc = new Scanner(System.in);

        System.out.println("id? ");
        int id = sc.nextInt();

        Repertoire repertoire = null;
        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            repertoire = controler.getById(id);
            tx.commit();

        }catch (Exception e){
            if(tx != null)
                tx.rollback();
        }finally {
            if(session!=null)
                session.close();
        }

        return repertoire;
    }

    public Repertoire getByIdEager() {
        Scanner sc = new Scanner(System.in);

        System.out.println("id? ");
        int id = sc.nextInt();

        Repertoire repertoire = null;
        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            repertoire = controler.getById(id);
            Hibernate.initialize(repertoire.getMembres());
            tx.commit();

        }catch (Exception e){
            if(tx != null)
                tx.rollback();
        }finally {
            if(session!=null)
                session.close();
        }

        return repertoire;
    }

    public Repertoire insert(){
        Scanner sc = new Scanner(System.in);

        System.out.println("nom de la pièce: ");
        String name = sc.nextLine();

        System.out.println("auteur: ");
        String auteur = sc.nextLine();

        Repertoire repertoire = null;
        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            repertoire = controler.insert(name,auteur,Boolean.TRUE);

            System.out.println("how many commediant: ");
            int size = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < size; i++) {
                System.out.println("quel commedient? ");
                repertoire.getMembres().add(session.get(Membre.class,sc.nextInt()));
                sc.nextLine();
            }

            tx.commit();

        }catch (Exception e){
            if(tx != null)
                tx.rollback();
        }finally {
            if(session!=null)
                session.close();
        }

        return repertoire;

    }

}
