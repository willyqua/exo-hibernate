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
        Employes wil = new Employes();
        wil.setNom("wil");
        wil.setPrenom("juste ");
        wil.setEmail("wil@gmail.com");
        wil.setAge(27);
        wil.setFonction("dev");
        wil.setTelephone("06 30 30 30 30");
        wil.setAdresse_postal("15 rue de la gare");
        wil.setSecteur(secteur2);
        session.save(wil);
        session.getTransaction().commit();


        // create fiffiale
        session.beginTransaction();
        Filliale filliale1 = new Filliale();
        filliale1.setNom("filliale1");
        filliale1.setNbre_employe(150);

        Filliale filliale2 = new Filliale();
        filliale2.setNom("filliale2");
        filliale2.setNbre_employe(100);

        filliale1.addSecteur(secteur2);
        secteur2.addFilliale(filliale1);
        filliale2.addSecteur(secteur2);
        secteur2.addFilliale(filliale2);

        filliale1.addSecteur(secteur1);
        secteur1.addFilliale(filliale1);
        filliale2.addSecteur(secteur1);
        secteur1.addFilliale(filliale2);

        filliale1.setEntreprise(nordBoucherie);
        filliale2.setEntreprise(nordBoucherie);
        filliale1.setEntreprise(Total);
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
