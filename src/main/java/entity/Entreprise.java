package entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="entreprise")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;

    @OneToMany(mappedBy = "entreprise", cascade =  CascadeType.ALL)
    private Set<Filliale> filliale;

    public Set<Filliale> getFilliale() {
        return filliale;
    }

    public void setFilliale(Set<Filliale> filliale) {
        this.filliale = filliale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
