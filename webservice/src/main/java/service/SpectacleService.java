package service;

import connection.HibernateUtil;
import controler.ReservationControler;
import controler.SpectacleControler;
import entity.Repertoire;
import entity.Reservation;
import entity.Spectacle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.ToolBox;

import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class SpectacleService {
    SpectacleControler controler;

    public SpectacleService() {
        controler = new SpectacleControler();
    }

    public Spectacle getByID(){
        Session session = null;
        Transaction tx = null;
        Spectacle spectacle = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("what id: ");
        int id = sc.nextInt();

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            spectacle = controler.getById(id);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null){
                tx.rollback();
            }
        }finally {
            if(session !=null){
                session.close();
            }
        }

        return spectacle;

    }

    public Spectacle insert(){
        Session session = null;
        Transaction tx = null;
        Spectacle spectacle = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("what name: ");
        String name = sc.nextLine();

        System.out.println("date: ");
        Date date = ToolBox.readDate(sc,"YYYY-MM-DD");

        System.out.println("places?");
        int place = sc.nextInt();
        sc.nextLine();

        System.out.println("prix");
        String prix = sc.nextLine();

        System.out.println("pièce");
        int idPiece = sc.nextInt();


        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Repertoire repertoire = session.get(Repertoire.class,idPiece);

            spectacle = controler.insert(name,date,place,place,prix,repertoire);

            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            if(session !=null){
                session.close();
            }
        }

        return spectacle;
    }

    public void delete(){
        Session session = null;
        Transaction tx = null;

        Scanner scanner = new Scanner(System.in);

        ReservationControler reservationControler = new ReservationControler();

        System.out.println("id: ");
        int id = scanner.nextInt();

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Set<Reservation> reservationSet = controler.delete(id);

            /*for (Reservation r: reservationSet) {
                reservationControler.deleteByReference(r);
            }*/

            tx.commit();
        }catch (Exception e){
            if(tx!= null){
                tx.rollback();
            }
        }finally {
            if(session != null)
                session.close();
        }

    }
}
