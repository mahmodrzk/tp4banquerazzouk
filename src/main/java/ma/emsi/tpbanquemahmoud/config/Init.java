/*
 * Cliquez sur nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt pour modifier cette licence
 * Cliquez sur nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java pour modifier ce modèle
 */
package ma.emsi.tpbanquemahmoud.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import ma.emsi.tpbanquemahmoud.service.GestionnaireCompte;
import ma.emsi.tpbanquemahmoud.entity.CompteBancaire;

/**
 * La classe Init est utilisée pour initialiser des données au démarrage de l'application.
 * Elle observe le contexte d'application pour déclencher l'initialisation.
 * 
 * @author ADMIN
 */
public class Init {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Initialise les données au démarrage de l'application si aucun compte n'existe.
     * 
     * @param context Le contexte d'application.
     */
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        if (gestionnaireCompte.nbComptes() == 0) {
            gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            gestionnaireCompte.creerCompte(new CompteBancaire("George Harrison", 100000));
        }
    }
}
