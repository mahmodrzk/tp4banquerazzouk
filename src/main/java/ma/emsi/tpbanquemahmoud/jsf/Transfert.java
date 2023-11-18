/*
 * Cette classe "Transfert" permet de gérer le transfert d'argent entre deux comptes bancaires.
 * Elle vérifie la validité des opérations, telles que l'existence des comptes source et destination,
 * ainsi que le solde du compte source, avant de procéder au transfert.
 */
package ma.emsi.tpbanquemahmoud.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import ma.emsi.tbbanquemahmoud.util.Util;
import ma.emsi.tpbanquemahmoud.service.GestionnaireCompte;
import ma.emsi.tpbanquemahmoud.entity.CompteBancaire;

@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    private int idSource;
    private int idDestination;
    private int montant;

    public Transfert() {
    }

    @Inject
    private GestionnaireCompte gc;

    public int getIdSource() {
        return idSource;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Transactional
    public String transferer() {
        boolean erreur = false;
        CompteBancaire source = gc.getCompte(idSource);
        if (source == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Solde du compte source est insuffisant !", "Solde du compte source est insuffisant !", "form:montant");
                erreur = true;
            }
        }
        CompteBancaire destination = gc.getCompte(idDestination);
        if (destination == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }
        if (erreur) {
            return null;
        }
        gc.transferer(source, destination, montant);
        Util.addFlashInfoMessage("Transfert correctement effectué : Source = " + source.getNom() + " - Destination = " + destination.getNom() + " - Montant = " + montant);
        return "listeComptes?faces-redirect=true";
    }

}
