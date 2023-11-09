package app.fos.utils;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ejb.fos.bo.ClienteBO;
import ejb.fos.entidade.Cliente;

@SuppressWarnings("rawtypes")
@FacesConverter( value="clienteConverter", managed = true)
public class ClienteConverter implements Converter {
 
    @EJB 
    private ClienteBO clienteBO;
     
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return clienteBO.listarTodosClientes().get(Integer.parseInt(value));
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
