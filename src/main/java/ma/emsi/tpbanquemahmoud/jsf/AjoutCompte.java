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

   
    public AjoutCompte() {
    }

   
    public String getNom() {
        return nom;
    }

   
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    public int getMontant() {
        return montant;
    }

   
    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Méthode pour ajouter un nouveau compte en utilisant le GestionnaireCompte.
     * Elle crée un nouveau compte avec le nom et le montant initial saisis.
     * 
     */
    @Transactional
    public String ajouter() {
        gc.creerCompte(new CompteBancaire(nom, montant));
        Util.addFlashInfoMessage("Compte créé avec succès");
        return "listeComptes?faces-redirect=true";
    }
}
