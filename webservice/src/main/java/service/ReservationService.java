package service;

import connection.HibernateUtil;
import controler.ReservationControler;
import entity.Reservation;
import entity.Spectacle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class ReservationService {
    ReservationControler controler;

    public ReservationService(){
        controler = new ReservationControler();
    }

    public Reservation getById(){
        Scanner sc = new Scanner(System.in);

        Reservation reservation = null;
        Session session = null;
        Transaction tx = null;

        System.out.println("id: ");
        int id = sc.nextInt();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            reservation = controler.getByID(id);

            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
            if(tx != null)
                tx.rollback();

        }finally {
            if(session != null)
                session.close();
        }

        return reservation;
    }

    public Reservation insert(){
        Reservation reservation = null;
        Session session = null;
        Transaction tx = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("nom: ");
        String name = sc.nextLine();

        System.out.println("nb place");
        int nb_place = sc.nextInt();
        sc.nextLine();

        System.out.println("quel pièce? ");
        int piece = sc.nextInt();
        sc.nextLine();

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            reservation = controler.insert(name,nb_place,session.get(Spectacle.class,piece));

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(tx!=null){
                tx.rollback();
            }
        }finally {
            if(session != null)
                session.close();
        }

        return reservation;
    }

    public void Delete(){
        Scanner sc = new Scanner(System.in);

        Session session = null;
        Transaction tx = null;

        System.out.println("id: ");
        int id = sc.nextInt();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            controler.delete(id);

            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
            if(tx != null)
                tx.rollback();

        }finally {
            if(session != null)
                session.close();
        }
    }

}
