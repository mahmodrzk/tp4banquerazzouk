/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ma.emsi.tbbanquemahmoud.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;



public class Util {

    /**
     * Affiche un message avec un client ID. Pour trouver un client ID, prenez l'exemple suivant : 
     * si l'élément <h:inputText> a un ID "input" et qu'il se trouve dans un formulaire ayant l'ID "form", 
     * le client ID sera "form:input". Vous pouvez également le trouver en inspectant le code source HTML.
     *
     * @param messageDetail message détaillé.
     * @param messageResume message résumé.
     * @param severite sévérité du message.
     * @param clientId ID du client auquel le message est rattaché.
     */
    public static void message(String messageDetail, String messageResume,
            FacesMessage.Severity severite, String clientId) {
        FacesMessage msg
                = new FacesMessage(severite, messageResume, messageDetail);
        FacesContext.getCurrentInstance().addMessage(clientId, msg);
    }

    /**
     * Affiche un message qui n'est pas rattaché à un composant particulier.
     *
     * @param messageDetail message détaillé.
     * @param messageResume message résumé.
     * @param severite sévérité du message.
     */
    public static void message(String messageDetail, String messageResume,
            FacesMessage.Severity severite) {
        FacesMessage msg
                = new FacesMessage(severite, messageResume, messageDetail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Affiche un message d'erreur avec sévérité FacesMessage.SEVERITY_ERROR qui n'est pas rattaché à un composant particulier. 
     * Vous pouvez spécifier un message différent pour le détail et le résumé.
     *
     * @param messageDetail message détaillé.
     * @param messageResume message résumé.
     */
    public static void messageErreur(String messageDetail, String messageResume) {
        message(messageDetail, messageResume, FacesMessage.SEVERITY_ERROR);
    }

    public static void messageErreur(String message) {
        message(message, message, FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Affiche un message d'erreur rattaché à un composant particulier.
     *
     * @param messageDetail message détaillé.
     * @param messageResume message résumé.
     * @param clientId ID du client auquel le message est rattaché.
     */
    public static void messageErreur(String messageDetail, String messageResume, String clientId) {
        message(messageDetail, messageResume, FacesMessage.SEVERITY_ERROR, clientId);
    }

    /**
     * Affiche un message d'information (ou de succès) qui n'est pas rattaché à un composant particulier.
     *
     * @param messageDetail message détaillé.
     * @param messageResume message résumé.
     */
    public static void messageInfo(String messageDetail, String messageResume) {
        message(messageDetail, messageResume, FacesMessage.SEVERITY_INFO);
    }

    public static void messageInfo(String message) {
        message(message, message, FacesMessage.SEVERITY_INFO);
    }

    /**
     * Affiche des messages avec redirection, non rattachés à un composant particulier.
     *
     * @param message message à afficher.
     */
    public static void addFlashMessage(FacesMessage message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        facesContext.addMessage(null, message);
    }

    /**
     * Affiche un message d'erreur avec redirection, non rattaché à un composant particulier.
     *
     * @param message message d'erreur à afficher.
     */
    public static void addFlashErrorMessage(String message) {
        FacesMessage msg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
        addFlashMessage(msg);
    }

    /**
     * Affiche un message d'information avec redirection, non rattaché à un composant particulier.
     *
     * @param message message d'information à afficher.
     */
    public static void addFlashInfoMessage(String message) {
        FacesMessage msg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        addFlashMessage(msg);
    }
}