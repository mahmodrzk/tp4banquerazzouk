/*
 * Ce fichier représente la classe AjoutCompte, utilisée pour gérer l'ajout de nouveaux comptes bancaires.
 * Elle permet de saisir le nom et le montant initial du compte, puis de créer un nouveau compte en utilisant le GestionnaireCompte.
 */
package ma.emsi.tpbanquemahmoud.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import ma.emsi.tbbanquemahmoud.util.Util;
import ma.emsi.tpbanquemahmoud.entity.CompteBancaire;
import ma.emsi.tpbanquemahmoud.service.GestionnaireCompte;

/**
 * La classe AjoutCompte gère l'ajout de nouveaux comptes bancaires en permettant la saisie du nom et du montant initial.
 * Elle utilise le GestionnaireCompte pour créer un nouveau compte.
 * 
 * @author ADMIN
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    private String nom;
    @PositiveOrZero
    private int montant;
    
    @Inject
    private GestionnaireCompte gc;

    /**
     * Obtient le nom du compte.
     *
     * @return Le nom du compte.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du compte.
     *
     * @param nom Le nom du compte.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le montant initial du compte.
     *
     * @return Le montant initial du compte.
     */
    public int getMontant() {
        return montant;
    }

    /**
     * Définit le montant initial du compte.
     *
     * @param montant Le montant initial du compte.
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    /**
     * Ajoute un nouveau compte bancaire avec les informations fournies.
     *
     * @return La page de la liste des comptes après la création du compte.
     */
    @Transactional
    public String ajouter() {
        gc.creerCompte(new CompteBancaire(nom, montant));
        Util.addFlashInfoMessage("Compte créé avec succès");
        return "listeComptes?faces-redirect=true";
    }
    
    /**
     * Crée une nouvelle instance de la classe AjoutCompte.
     */
    public AjoutCompte() {
    }
}