package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idrole;
    String nom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="membre_has_role",joinColumns = {@JoinColumn(name="role_idrole")},inverseJoinColumns = {@JoinColumn(name="Membre_idMembre")})
    Set<Membre> membreSet;

    public Role(String nom) {
        this.nom = nom;
        membreSet = new HashSet<Membre>();
    }

    public Role() {
    }

    public Set<Membre> getMembreSet() {
        return membreSet;
    }

    public void setMembreSet(Set<Membre> membreSet) {
        this.membreSet = membreSet;
    }

    public int getIdrole() {
        return idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Role{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
