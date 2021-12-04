package Manager;

import entity.Employes;
import entity.Filliale;
import entity.Secteur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SecteurManager {
    public static void main(String[] args){
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session =sessionFactory.openSession();
        session.beginTransaction();


        Employes wil = new Employes();
        wil.setNom("greg");
        wil.setPrenom("juste ");
        wil.setEmail("greg@gmail.com");
        wil.setAge(27);
        wil.setFonction("dev");
        wil.setTelephone("06 30 30 30 30");
        wil.setAdresse_postal("15 rue de la gare");



        Filliale admin = new Filliale();
        admin.setNom("administration");
        //admin.getNom();
        admin.setNbre_employe(150);
        //admin.getNbre_employe();

        Secteur joke = new Secteur();
        joke.setNom("geo");
       //joke.getNom();
        joke.setLocalisation("Paris");
        //joke.getLocalisation();

        joke.addFilliale(admin);
        admin.addSecteur(joke);

        session.save(joke);
        session.save(admin);
        session.save(wil);


        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
