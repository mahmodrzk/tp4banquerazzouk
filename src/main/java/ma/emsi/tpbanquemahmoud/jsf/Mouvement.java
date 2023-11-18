/*
 * Cette classe "Mouvement" est utilisée pour gérer les mouvements (dépôt ou retrait) sur un compte bancaire.
 * Elle permet de valider les opérations en fonction du type de mouvement (dépôt ou retrait) et du solde du compte.
 */
package ma.emsi.tpbanquemahmoud.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import ma.emsi.tbbanquemahmoud.util.Util;
import ma.emsi.tpbanquemahmoud.entity.CompteBancaire;
import ma.emsi.tpbanquemahmoud.service.GestionnaireCompte;

/**
 * La classe Mouvement est utilisée pour gérer les mouvements (dépôt ou retrait) sur un compte bancaire.
 * Elle permet de valider les opérations en fonction du type de mouvement (dépôt ou retrait) et du solde du compte.
 * 
 * @author ADMIN
 */
@Named(value = "mouvement")
@ViewScoped
public class Mouvement implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private String typeMouvement;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

   
    public int getMontant() {
        return montant;
    }

 
    public void setMontant(int montant) {
        this.montant = montant;
    }


    public String getTypeMouvement() {
        return typeMouvement;
    }


    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

  
    public CompteBancaire getCompte() {
        return compte;
    }

    /**
     * Charge le compte bancaire associé au mouvement.
     */
    public void loadCompte() {
        compte = gestionnaireCompte.getCompte(id);
    }

    /**
     * Valide le solde du compte en fonction du type de mouvement.
     * Si c'est un retrait, vérifie que le retrait est inférieur au solde du compte.
     * 
     * @param fc Le contexte Faces.
     * @param composant Le composant associé.
     * @param valeur La valeur du mouvement.
     * @throws ValidatorException Si la validation échoue.
     */
    public void validateSolde(FacesContext fc, UIComponent composant, Object valeur) {
        UIInput composantTypeMouvement = (UIInput) composant.findComponent("typeMouvement");

        String valeurTypeMouvement = (String) composantTypeMouvement.getLocalValue();
        if (valeurTypeMouvement == null) {
            return;
        }
        if (valeurTypeMouvement.equals("retrait")) {
            int retrait = (int) valeur;
            if (compte.getSolde() < retrait) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Le retrait doit être inférieur au solde du compte",
                        "Le retrait doit être inférieur au solde du compte");
                throw new ValidatorException(message);
            }
        }
    }

    /**
     * Enregistre le mouvement (ajout ou retrait) sur le compte.
     * 
     * @return La redirection vers la page "listeComptes" après l'enregistrement du mouvement.
     */
    public String enregistrerMouvement() {
        if (typeMouvement.equals("ajout")) {
            gestionnaireCompte.deposer(compte, montant);
        } else {
            gestionnaireCompte.retirer(compte, montant);
        }
        Util.addFlashInfoMessage("Mouvement enregistré sur compte de " + compte.getNom());
        return "listeComptes?faces-redirect=true";
    }
}
