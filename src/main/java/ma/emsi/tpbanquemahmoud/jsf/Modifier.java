/*
 * Cette classe "Modifier" est utilisée pour gérer la modification des informations d'un compte bancaire.
 * Elle permet de charger un compte à partir de son ID, de modifier le nom du compte, puis d'enregistrer la modification.
 */
package ma.emsi.tpbanquemahmoud.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import ma.emsi.tbbanquemahmoud.util.Util;
import ma.emsi.tpbanquemahmoud.entity.CompteBancaire;
import ma.emsi.tpbanquemahmoud.service.GestionnaireCompte;

/**
 * La classe Modifier est utilisée pour gérer la modification des informations d'un compte bancaire.
 * Elle permet de charger un compte à partir de son ID, de modifier le nom du compte, puis d'enregistrer la modification.
 * 
 * @author ADMIN
 */
@Named(value = "modifier")
@ViewScoped
public class Modifier implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private String nom;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    
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

  
    public CompteBancaire getCompte() {
        return compte;
    }

    /**
     * Charge le compte bancaire à partir de son identifiant.
     */
    public void loadCompte() {
        compte = gestionnaireCompte.getCompte(id);
    }

    /**
     * Enregistre la modification du nom du compte.
     * 
     * @return La redirection vers la page "listeComptes" après l'enregistrement de la modification.
     */
    public String enregistrerModification() {
        Util.addFlashInfoMessage("Nom " + compte.getNom() + " changé en " + nom);
        compte.setNom(nom);
        gestionnaireCompte.update(compte);
        return "listeComptes?faces-redirect=true";
    }
}
