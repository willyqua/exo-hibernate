package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="filliale")
public class Filliale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int nbre_employe;
    @ManyToMany(mappedBy = "filliales")
    private Set<Secteur> secteurs = new HashSet<Secteur>();

    public void addSecteur(Secteur secteur){
        this.secteurs.add(secteur);
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

    public int getNbre_employe() {
        return nbre_employe;
    }

    public void setNbre_employe(int nbre_employe) {
        this.nbre_employe = nbre_employe;
    }

    public Set<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(Set<Secteur> secteurs) {
        this.secteurs = secteurs;
    }
}
