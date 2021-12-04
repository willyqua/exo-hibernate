package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "secteur")
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String localisation;

    @OneToMany(mappedBy = "secteur", cascade = CascadeType.ALL)
    private Set<Employes> employes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "secteur_has_filliale",
            joinColumns = @JoinColumn(name = "secteur_id"),
            inverseJoinColumns = @JoinColumn(name = "filliale_id")
    )
    private Set<Filliale> filliales = new HashSet<Filliale>();

    public void addFilliale(Filliale filliale) {
        this.filliales.add(filliale);
    }

    public Set<Employes> getEmployes() {
        return employes;
    }

    public void setEmployes(Set<Employes> employes) {
        this.employes = employes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Set<Filliale> getFilliales() {
        return filliales;
    }

    public void setFilliales(Set<Filliale> filliales) {
        this.filliales = filliales;
    }
}
