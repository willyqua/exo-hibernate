package Manager;

import entity.Employes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class EmployesManager {
    protected SessionFactory sessionFactory;

    private void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            ex.getStackTrace();
        }
    }

    protected void exit() {
        sessionFactory.close();
    }

    protected void create() {
        Employes wil = new Employes();
        wil.setNom("greg");
        wil.setPrenom("juste ");
        wil.setEmail("greg@gmail.com");
        wil.setAge(27);
        wil.setFonction("dev");
        wil.setTelephone("06 30 30 30 30");
        wil.setAdresse_postal("15 rue de la gare");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(wil);
        session.getTransaction().commit();
        session.close();
    }

    protected Employes read(long id) {
        // je lis un enregistrement
        Session session = sessionFactory.openSession();
        Employes employes = session.get(Employes.class, id);
        System.out.println("Nom: " + employes.getNom());
        System.out.println("prenom: " + employes.getPrenom());
        System.out.println("email: " + employes.getEmail());
        System.out.println("age: " + employes.getAge());
        System.out.println("téléphone: " + employes.getTelephone());
        System.out.println("fonction: " + employes.getFonction());
        System.out.println("code postal: " + employes.getAdresse_postal());
        session.close();
        return employes;
    }



    protected void update(long id, Employes newEmployes) {
        // Je mets à jour un enregistrement
        Employes employes = this.read(id);
        if (newEmployes.getNom() != null) {
            employes.setNom(newEmployes.getNom());

        }
        if (newEmployes.getPrenom() != null) {
            employes.setPrenom(newEmployes.getPrenom());
        }
        if (newEmployes.getAge() != employes.getAge()) {
            employes.setAge(newEmployes.getAge());
        }
        if (newEmployes.getEmail() != null) {
            employes.setEmail(newEmployes.getEmail());
        }
        if (newEmployes.getFonction() != null) {
            employes.setFonction(newEmployes.getFonction());
        }
        if (newEmployes.getTelephone() != null) {
            employes.setTelephone(newEmployes.getTelephone());
        }
        if (newEmployes.getAdresse_postal() != null) {
            employes.setAdresse_postal(newEmployes.getAdresse_postal());
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(employes);
        session.getTransaction().commit();
        session.close();

    }

    protected void delete(Employes employes) {
        //Je supprime un enregistrement
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employes);
        session.getTransaction().commit();
        session.close();
    }
    public static void main(String[] args) {
        EmployesManager manager = new EmployesManager();
        manager.setup();
        //manager.create();
        manager.read(3);

        /*Employes employes = new Employes();
        employes.setTelephone("06 20 20 20 20");
        long id = 3;
        manager.update(id, employes);*/
        Employes employes = manager.read(5);
        manager.delete(employes);
        manager.exit();


    }
}
