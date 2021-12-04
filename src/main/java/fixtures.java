import entity.Employes;
import entity.Secteur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class fixtures {

    public static void main(String[] args){

        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session =sessionFactory.openSession();


            String[] clearTables = {"Employes", "Secteur"};
            for (String table: clearTables) {
                session.beginTransaction();
                String stringQuery = "DELETE FROM " + table;
                Query query = session.createQuery(stringQuery);
                query.executeUpdate();
                session.getTransaction().commit();
            }


        //Create joke
        session.beginTransaction();
        Secteur joke = new Secteur();
        joke.setNom("geo");
        joke.setLocalisation("Paris");
        session.save(joke);
        session.getTransaction().commit();

        //Create user 1
        session.beginTransaction();
        Employes wil = new Employes();
        wil.setNom("greg");
        wil.setPrenom("juste ");
        wil.setEmail("greg@gmail.com");
        wil.setAge(27);
        wil.setFonction("dev");
        wil.setTelephone("06 30 30 30 30");
        wil.setAdresse_postal("15 rue de la gare");
        wil.setSecteur(joke);
        session.save(wil);
        session.getTransaction().commit();


        session.close();
        sessionFactory.close();
    }
}
