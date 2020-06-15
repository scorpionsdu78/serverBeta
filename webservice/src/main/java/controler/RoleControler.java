package controler;

import connection.HibernateUtil;
import entity.Membre;
import entity.Role;
import org.hibernate.Session;

public class RoleControler {
    public Role getByID(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session.get(Role.class,id);
    }

    public Role insert(String nom){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Role role = new Role(nom);
        session.save(role);
        return role;
    }

    public void addRole(int idmembre, Role role){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.get(Membre.class,idmembre).getRoles().add(role);
    }

    public Role update(int id, String nom){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Role role = session.get(Role.class,id);
        role.setNom(nom);
        session.save(role);
        return role;
    }

    public void Delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Role role = session.get(Role.class,id);
        session.delete(role);
    }
}
