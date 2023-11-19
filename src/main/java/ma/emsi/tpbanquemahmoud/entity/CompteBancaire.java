/*
 * Ce fichier représente la classe CompteBancaire qui modélise un compte bancaire avec des propriétés
 * telles que l'identifiant, le nom du titulaire et le solde.
 * Cette classe fournit des méthodes pour déposer et retirer de l'argent, ainsi que des méthodes de base
 * pour la gestion du compte.
 */
package ma.emsi.tpbanquemahmoud.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import ma.emsi.tbbanquemahmoud.util.Util;
import ma.emsi.tpbanquemahmoud.service.GestionnaireCompte;

/**
 * Cette classe représente un compte bancaire avec des propriétés telles que
 * l'identifiant, le nom du titulaire et le solde. Elle fournit des méthodes
 * pour déposer et retirer de l'argent, ainsi que des méthodes de base pour la
 * gestion du compte.
 *
 * L'entité est annotée avec @Entity pour indiquer qu'elle est une entité JPA.
 * Elle implémente l'interface Serializable pour permettre la sérialisation. La
 * classe utilise des requêtes nommées (NamedQueries) pour effectuer des
 * requêtes spécifiques dans la base de données.
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.count", query = "SELECT COUNT(c) FROM CompteBancaire c")
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OperationBancaire> operations = new ArrayList<>();

    private String nom;
    private int solde;

    /**
     * Constructeur par défaut nécessaire pour JPA. Ajoute automatiquement une
     * opération de création du compte.
     */
    public CompteBancaire() {
        operations.add(new OperationBancaire("Création du compte", solde));
    }

    /**
     * Constructeur pour créer un compte avec un nom et un solde initial. Ajoute
     * automatiquement une opération de création du compte.
     *
     * @param nom Nom du titulaire du compte.
     * @param solde Solde initial du compte.
     */
    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        operations.add(new OperationBancaire("Création du compte", solde));
    }

    /**
     * Getter pour l'identifiant du compte.
     *
     * @return L'identifiant du compte.
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter pour le nom du titulaire du compte.
     *
     * @return Le nom du titulaire du compte.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter pour le nom du titulaire du compte.
     *
     * @param nom Le nouveau nom du titulaire du compte.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour le solde du compte.
     *
     * @return Le solde du compte.
     */
    public int getSolde() {
        return solde;
    }

    /**
     * Setter pour le solde du compte.
     *
     * @param solde Le nouveau solde du compte.
     */
    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Getter pour la liste des opérations associées au compte.
     *
     * @return La liste des opérations du compte.
     */
    public List<OperationBancaire> getOperations() {
        return operations;
    }

    /**
     * Méthode pour déposer de l'argent sur le compte. Ajoute une opération de
     * dépôt à la liste des opérations.
     *
     * @param montant Le montant à déposer.
     */
    public void deposer(int montant) {
        solde += montant;
        operations.add(new OperationBancaire("Dépôt", montant));
    }

    /**
     * Méthode pour retirer de l'argent du compte. Ajoute une opération de
     * retrait à la liste des opérations. Le solde ne peut pas devenir négatif.
     *
     * @param montant Le montant à retirer.
     */
    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
            operations.add(new OperationBancaire("Retrait", montant));
        } else {
            solde = 0;
        }
    }

    /**
     * Implémentation de la méthode hashCode() pour permettre une utilisation
     * correcte dans les structures de données.
     *
     * @return La valeur de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Implémentation de la méthode equals() pour permettre la comparaison
     * d'objets.
     *
     * @param object L'objet à comparer.
     * @return True si les objets sont égaux, sinon False.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    /**
     * Implémentation de la méthode toString() pour obtenir une représentation
     * textuelle de l'objet.
     *
     * @return La représentation textuelle de l'objet.
     */
    @Override
    public String toString() {
        return "ma.emsi.tpbanquemahmoud.entity.CompteBancaire[ id=" + id + " ]";
    }
   
}
