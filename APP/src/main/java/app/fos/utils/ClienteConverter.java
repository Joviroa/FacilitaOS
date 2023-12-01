package app.fos.utils;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;

import ejb.fos.dao.ClienteDAO;
import ejb.fos.entidade.Cliente;

@SuppressWarnings("rawtypes")
@FacesConverter( value="clienteConverter", managed = true) 
public class ClienteConverter implements Converter {
	
    private ClienteDAO clienteDAO;
     
    public ClienteConverter() {
        try {
            Context context = new InitialContext();
            clienteDAO = (ClienteDAO) context.lookup("java:global/EAR/EJB/ClienteDAO!ejb.fos.dao.ClienteDAO");
        } catch (Exception e) {
            System.out.println("Erro ao inicializar o clienteBO no converter.");
        }
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return clienteDAO.busca(Cliente.class, Long.valueOf(value));
            } catch(Exception e) {
            	System.out.println("Erro ao converter o cliente.");
            	return null;
            }
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Cliente) object).getId());
        }
        else {
            return null;
        }
    }   
}
