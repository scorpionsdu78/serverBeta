package controler;

import connection.HibernateUtil;
import entity.Reservation;
import entity.Spectacle;
import org.hibernate.Session;

public class ReservationControler {

    public Reservation getByID(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Reservation.class,id);
    }

    public Reservation insert(String name, int nb_place, Spectacle spectacle){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Reservation reservation = null;
        if (nb_place <= spectacle.getPlace_restante()) {
             reservation = new Reservation(name, nb_place, spectacle);
            session.save(reservation);
        }
        return reservation;
    }

    public Reservation update(int id, String name, int nb_place){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Reservation reservation = session.get(Reservation.class,id);
        if(name!=null && !name.isEmpty()){
            reservation.setNom_reservation(name);
        }
        if (nb_place<0 && nb_place<=reservation.getSpectacle().getPlace_restante()){
            reservation.setNb_place(nb_place);
        }
        session.save(reservation);
        return reservation;
    }

    public void delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Reservation reservation = session.get(Reservation.class,id);
        session.delete(reservation);
    }

    public void deleteByReference(Reservation reservation){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(reservation);
    }
}
