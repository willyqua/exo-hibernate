package entity;


import javax.persistence.*;

@Entity
@Table(name="entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long entrepriseId;
    private String name;

    public Entreprise(long entrepriseId, String name) {
        this.entrepriseId = entrepriseId;
        this.name = name;
    }

    public long getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(long entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
