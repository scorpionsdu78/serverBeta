import controler.RoleControler;
import controler.SpectacleControler;
import controler.UsersControler;
import entity.*;
import service.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDeConnection {
    public static void main(String... args){
        /*MembreService factory = new MembreService();

        factory.deleteCascade();*/

        /*ArticleService factory = new ArticleService();

        System.out.println(factory.insertWithPhoto());*/

        /*RoleService factory = new RoleService();

        Role role = factory.Insert();

        System.out.println(role);

        for (Membre m: role.getMembreSet()) {
            System.out.println(m);
        }*/

        //factory.delete();

        /*RepertoireService factory = new RepertoireService();
        Repertoire repertoire = factory.insert();
        System.out.println(repertoire);
        for (Membre m: repertoire.getMembres()) {
            System.out.println(m);
        }*/

        SpectacleService factory = new SpectacleService();
        //System.out.println(factory.insert());
        factory.delete();

       /* ReservationService factory = new ReservationService();
        //System.out.println(factory.insert());
        factory.Delete();*/

    }
}
