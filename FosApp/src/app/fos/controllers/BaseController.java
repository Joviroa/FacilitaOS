package app.fos.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.PrimeFaces;

import app.fos.utils.JSFUtils;
import ejb.fos.bo.EmpresaBO;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.governors.MaxPagesGovernor;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class BaseController implements Serializable {
	
	@EJB
	private EmpresaBO empresaBO;
	
	private List<String> listaMensagemErro = new ArrayList<String>();
	private String mensagemErro;
	
	public void redirecionarHome() {
		redireciona("/pages/home.xhtml");
	}
	
	public void redirecionarClientes() {
		redireciona("/pages/clientes.xhtml");
	}
	
	public void redirecionarServicos() {
		redireciona("/pages/servicos.xhtml");
	}
	
	public void redirecionarOS() {
		redireciona("/pages/os.xhtml");
	}
	
	public void redirecionarConfiguracoes() {
		redireciona("/pages/config.xhtml");
	}
	
	//public Empresa getEmpresa() {
	//	return empresaBO.buscarEmpresa();
	//}
	
	public void redireciona(String pagina) {
		try {
			JSFUtils.redireciona(JSFUtils.getRequest().getContextPath() + pagina);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void setarObjetoNoFlash(Object object, String nomeParametro) {
		JSFUtils.put(nomeParametro, object);
	}
	
	public void redirecionaPaginaExterna(String url) {		
        FacesContext context = FacesContext.getCurrentInstance();	      
        try {
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        	System.out.println("Erro ao redirecionar para a url: " + url +".");
        }	    
	}
	
	public void exibirImagem(byte[] bytesRelatorio, String nomeComExtensao) {
		try {
			exibeArquivoNoNavegador(bytesRelatorio, "application/pdf", nomeComExtensao);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possível exivir o documento neste momento.");
			//Mensagem.errorSemBundle("Não foi possível exibir o documento nesse momento.");
		}
	}

	public void downloadPDF(byte[] bytesArquivo, String nomeComExtensao) throws Exception {
		downloadArquivo(bytesArquivo, "application/pdf", nomeComExtensao);
	}

	private void downloadArquivo(byte[] bytesArquivo, String mimeType, String nomeComExtensao) throws Exception {
		if (bytesArquivo != null) {
			JSFUtils.getResponse().setContentType(mimeType);
			JSFUtils.getResponse().setContentLength(bytesArquivo.length);
			JSFUtils.getResponse().addHeader("Content-disposition", "attachment; filename=" + nomeComExtensao);
			OutputStream outputStream = JSFUtils.getResponse().getOutputStream();
			outputStream.write(bytesArquivo, 0, bytesArquivo.length);
			outputStream.flush();

			if (outputStream != null) {
				outputStream.close();
			}

			bytesArquivo = null;
			FacesContext.getCurrentInstance().responseComplete();
		}
	}

	private void exibeArquivoNoNavegador(byte[] bytesArquivo, String mimeType, String nomeArquivoComExtensao) throws Exception {
		if (bytesArquivo != null) {
			JSFUtils.getResponse().setContentType(mimeType);
			JSFUtils.getResponse().setContentLength(bytesArquivo.length);
			JSFUtils.getResponse().setHeader("Content-disposition", "inline;filename=" + nomeArquivoComExtensao);
			OutputStream outputStream = JSFUtils.getResponse().getOutputStream();
			outputStream.write(bytesArquivo, 0, bytesArquivo.length);
			outputStream.flush();

			if (outputStream != null) {
				outputStream.close();
			}

			bytesArquivo = null;
			FacesContext.getCurrentInstance().responseComplete();
		}
	}

	public byte[] gerarRelatorio(String relatorio, List<? extends Object> beanList, HashMap<String, Object> parametros) throws Exception {
		JRBeanCollectionDataSource dsrel = new JRBeanCollectionDataSource(beanList);
		String pathJasper = JSFUtils.getRealPath() + relatorio;
		JasperPrint impressao = JasperFillManager.fillReport(pathJasper, parametros, dsrel);
		impressao.setProperty(MaxPagesGovernor.PROPERTY_MAX_PAGES_ENABLED, Boolean.TRUE.toString());
		impressao.setProperty(MaxPagesGovernor.PROPERTY_MAX_PAGES, "300");
		return JasperExportManager.exportReportToPdf(impressao);
	}

	public void downloadJPEG(byte[] arquivo, String nomeArquivo) throws Exception {
		download(arquivo, nomeArquivo, "image/jpeg");
	}

	public void download(byte[] arquivo, String nomeArquivo, String mime) throws Exception {
		HttpServletResponse response = JSFUtils.getResponse();
		response.setContentType(mime);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\"");
		response.setContentLength(arquivo.length);
		OutputStream out = response.getOutputStream();
		out.write(arquivo);
		out.flush();
		out.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

	
	
	public void fechaStatusDialog() {
		PrimeFaces.current().executeScript("PF('statusDialog').hide();");
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public List<String> getListaMensagemErro() {
		return listaMensagemErro;
	}

	public void setListaMensagemErro(List<String> listaMensagemErro) {
		this.listaMensagemErro = listaMensagemErro;
	}
	
}
