import Manager.EmployesManager;
import entity.Employes;
import entity.Entreprise;
import entity.Filliale;
import entity.Secteur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class fixtures {



    public static void main(String[] args) {

        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Clear table
        String[] clearTables = {"Employes", "Secteur","Filliale","Entreprise"}; // programme idempotent
        for (String table : clearTables) {
            session.beginTransaction();
            String stringQuery = "DELETE FROM " + table;
            Query query = session.createQuery(stringQuery);
            query.executeUpdate();
            session.getTransaction().commit();
        }

        // create entreprise
        session.beginTransaction();
        Entreprise nordBoucherie = new Entreprise();
        nordBoucherie.setNom("nordBoucherie");

        session.save(nordBoucherie);

        Entreprise Total = new Entreprise();
        Total.setNom("Total");
        session.save(Total);

        session.getTransaction().commit();

        //Create Secteur secteur 1 and 2
        session.beginTransaction();
        Secteur secteur1 = new Secteur();
        secteur1.setNom("Transport");
        secteur1.setLocalisation("Lille");
        session.save(secteur1);
        session.getTransaction().commit();




        session.beginTransaction();
        Secteur secteur2 = new Secteur();
        secteur2.setNom("Boucherie");
        secteur2.setLocalisation("Paris");
        session.save(secteur2);
        session.getTransaction().commit();

        //Create employes  1
        session.beginTransaction();
        Employes employe = new Employes();
        employe.setNom("wil");
        employe.setPrenom("juste ");
        employe.setEmail("wil@gmail.com");
        employe.setAge(27);
        employe.setFonction("dev");
        employe.setTelephone("06 30 30 30 30");
        employe.setAdresse_postal("15 rue de la gare");
        employe.setSecteur(secteur2);
        session.save(employe);
        session.getTransaction().commit();

        //Create employes  1
        session.beginTransaction();
        Employes employe1 = new Employes();
        employe1.setNom("wil");
        employe1.setPrenom("juste ");
        employe1.setEmail("wil@gmail.com");
        employe1.setAge(27);
        employe1.setFonction("dev");
        employe1.setTelephone("06 30 30 30 30");
        employe1.setAdresse_postal("15 rue de la gare");
        employe1.setSecteur(secteur2);
        session.save(employe1);
        session.getTransaction().commit();

        //Create employes  1
        session.beginTransaction();
        Employes employe2 = new Employes();
        employe2.setNom("wil");
        employe2.setPrenom("juste ");
        employe2.setEmail("wil@gmail.com");
        employe2.setAge(27);
        employe2.setFonction("dev");
        employe2.setTelephone("06 30 30 30 30");
        employe2.setAdresse_postal("15 rue de la gare");
        employe2.setSecteur(secteur2);
        session.save(employe2);
        session.getTransaction().commit();

        EmployesManager manager = new EmployesManager();
        manager.setup();
        System.out.println("employes count " +manager.count());

        // create fiffiale
        session.beginTransaction();
        Filliale filliale1 = new Filliale();
        filliale1.setNom("filliale1");
        filliale1.setNbre_employe(150);

        Filliale filliale2 = new Filliale();
        filliale2.setNom("filliale2");
        filliale2.setNbre_employe(100);

        Filliale filliale3 = new Filliale();
        filliale3.setNom("filliale3");
        filliale3.setNbre_employe(75);

        Filliale filliale4 = new Filliale();
        filliale4.setNom("filliale4");
        filliale4.setNbre_employe(300);

        filliale1.addSecteur(secteur2);
        secteur2.addFilliale(filliale1);
        filliale2.addSecteur(secteur2);
        secteur2.addFilliale(filliale2);



        filliale1.setEntreprise(nordBoucherie);
        filliale2.setEntreprise(Total);


        session.save(filliale1);
        session.save(filliale2);
        session.save(secteur1);
        session.save(secteur2);
        session.getTransaction().commit();


        session.close();
        sessionFactory.close();
    }
}
