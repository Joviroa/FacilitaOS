package app.fos.utils;

import javax.faces.application.FacesMessage;

import org.primefaces.context.PrimeFacesContext;

public class Mensagem {

    private static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        PrimeFacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(severity, summary, detail));
    }

    public static void info(String info) {
        addMessage(FacesMessage.SEVERITY_INFO, "Info", info);
    }

    public static void aviso(String aviso) {
        addMessage(FacesMessage.SEVERITY_WARN, "Aviso", aviso);
    }

    public static void erro(String erro) {
        addMessage(FacesMessage.SEVERITY_ERROR, "Erro", erro);
    }

}
