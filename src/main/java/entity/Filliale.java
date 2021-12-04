package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="filliale")
public class Filliale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private int nbre_employe;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @ManyToMany(mappedBy = "filliales")
    private Set<Secteur> secteurs = new HashSet<Secteur>();

    public void addSecteur(Secteur secteur){
        this.secteurs.add(secteur);
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

    public int getNbre_employe() {
        return nbre_employe;
    }

    public void setNbre_employe(int nbre_employe) {
        this.nbre_employe = nbre_employe;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Set<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(Set<Secteur> secteurs) {
        this.secteurs = secteurs;
    }
}
