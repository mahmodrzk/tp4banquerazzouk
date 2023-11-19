/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.tpbanquemahmoud.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entité représentant une opération bancaire.
 * Cette classe est annotée avec @Entity pour indiquer qu'elle est une entité JPA.
 * Elle implémente l'interface Serializable pour permettre la sérialisation.
 *
 * @author MOHAMED
 */
@Entity

public class OperationBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime dateOperation;
    private int montant;
    
    /**
     * Constructeur par défaut nécessaire pour JPA.
     */
    public OperationBancaire() { }
    
    /**
     * Constructeur pour créer une nouvelle opération bancaire avec une description et un montant.
     *
     * @param description Description de l'opération.
     * @param montant Montant de l'opération.
     */
    public OperationBancaire(String description, int montant) {
        this.description = description;
        this.montant = montant;
        dateOperation = LocalDateTime.now();
    }
    
    /**
     * Getter pour l'identifiant de l'opération.
     *
     * @return L'identifiant de l'opération.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter pour l'identifiant de l'opération.
     *
     * @param id Le nouvel identifiant de l'opération.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Implémentation de la méthode hashCode() pour permettre une utilisation correcte dans les structures de données.
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
     * Implémentation de la méthode equals() pour permettre la comparaison d'objets.
     *
     * @param object L'objet à comparer.
     * @return True si les objets sont égaux, sinon False.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OperationBancaire)) {
            return false;
        }
        OperationBancaire other = (OperationBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Implémentation de la méthode toString() pour obtenir une représentation textuelle de l'objet.
     *
     * @return La représentation textuelle de l'objet.
     */
    @Override
    public String toString() {
        return "com.mohamed.tpbanquemohamed.entity.OperationBancaire[ id=" + id + " ]";
    }

    /**
     * Getter pour le numéro de version de la classe (utilisé dans la sérialisation).
     *
     * @return Le numéro de version.
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Getter pour la description de l'opération.
     *
     * @return La description de l'opération.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter pour la date de l'opération.
     *
     * @return La date de l'opération.
     */
    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    /**
     * Getter pour le montant de l'opération.
     *
     * @return Le montant de l'opération.
     */
    public int getMontant() {
        return montant;
    }

    /**
     * Setter pour la description de l'opération.
     *
     * @param description La nouvelle description de l'opération.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter pour la date de l'opération.
     *
     * @param dateOperation La nouvelle date de l'opération.
     */
    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    /**
     * Setter pour le montant de l'opération.
     *
     * @param montant Le nouveau montant de l'opération.
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
}