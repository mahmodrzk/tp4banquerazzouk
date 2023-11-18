/*
 * Ce fichier représente la classe CompteBancaire qui modélise un compte bancaire avec des propriétés
 * telles que l'identifiant, le nom du titulaire et le solde.
 * Cette classe fournit des méthodes pour déposer et retirer de l'argent, ainsi que des méthodes de base
 * pour la gestion du compte.
 */
package ma.emsi.tpbanquemahmoud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import java.io.Serializable;

/**
 * La classe CompteBancaire représente un compte bancaire avec des fonctionnalités de base telles que
 * le dépôt, le retrait et la gestion du solde.
 *
 * @author ADMIN
 */
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.count", query = "SELECT COUNT(c) FROM CompteBancaire c")
})
@Entity
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private int solde;

    /**
     * Constructeur par défaut de la classe CompteBancaire.
     */
    public CompteBancaire() {
    }

    /**
     * Constructeur paramétré de la classe CompteBancaire.
     *
     * @param nom   Le nom du titulaire du compte.
     * @param solde Le solde initial du compte.
     */
    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }

    /**
     * Obtient l'identifiant du compte.
     *
     * @return L'identifiant du compte.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtient le nom du titulaire du compte.
     *
     * @return Le nom du titulaire du compte.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom du titulaire du compte.
     *
     * @param nom Le nouveau nom du titulaire du compte.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le solde actuel du compte.
     *
     * @return Le solde du compte.
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Modifie le solde du compte.
     *
     * @param solde Le nouveau solde du compte.
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Dépose un montant sur le compte.
     *
     * @param montant Le montant à déposer.
     */
    public void deposer(int montant) {
        solde += montant;
    }

    /**
     * Retire un montant du compte.
     *
     * @param montant Le montant à retirer.
     */
    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
        } else {
            solde = 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ma.emsi.tpbanquemahmoud.entity.CompteBancaire[ id=" + id + " ]";
    }
}
