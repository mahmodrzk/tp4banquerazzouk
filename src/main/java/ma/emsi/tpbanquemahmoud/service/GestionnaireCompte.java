/*
 * Cette classe GestionnaireCompte est responsable de la gestion des opérations sur les comptes bancaires.
 * Elle utilise l'EntityManager pour interagir avec la base de données et effectue des opérations telles que la création, la mise à jour, et la suppression de comptes.
 * Les méthodes de cette classe sont transactionnelles, assurant l'intégrité des données lors des opérations.
 * Le DataSourceDefinition spécifie les paramètres de la source de données pour la connexion à la base de données MySQL.
 */
package ma.emsi.tpbanquemahmoud.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import ma.emsi.tpbanquemahmoud.entity.CompteBancaire;

/**
 * Cette classe GestionnaireCompte est responsable de la gestion des opérations sur les comptes bancaires.
 * Elle utilise l'EntityManager pour interagir avec la base de données et effectue des opérations telles que la création, la mise à jour, et la suppression de comptes.
 * Les méthodes de cette classe sont transactionnelles, assurant l'intégrité des données lors des opérations.
 * Le DataSourceDefinition spécifie les paramètres de la source de données pour la connexion à la base de données MySQL.
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "Mahmoud@2023", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@Named(value = "gestionnaireCompte")
@ApplicationScoped
public class GestionnaireCompte {

    public GestionnaireCompte() {
    }

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public CompteBancaire getCompte(long id) {
        return em.find(CompteBancaire.class, id);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    public long nbComptes() {
        Query query = em.createNamedQuery("CompteBancaire.count");
        return (long) query.getSingleResult();
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination, int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    /**
     * Dépôt d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.deposer(montant);
        update(compteBancaire);
    }

    /**
     * Retrait d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.retirer(montant);
        update(compteBancaire);
    }

    @Transactional
    public void supprimerCompte(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }
}
