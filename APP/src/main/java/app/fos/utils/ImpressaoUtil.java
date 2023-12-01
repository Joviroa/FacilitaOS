package app.fos.utils;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;

import ejb.fos.bo.ImpressaoOrdemServicoBO;
import ejb.fos.entidade.OrdemServico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ImpressaoUtil {

	@EJB
	private ImpressaoOrdemServicoBO impressaoOrdemServicoBO;
	
	public byte[] exportarParaPdf(List<OrdemServico> listaOrdemServico) throws JRException {
		if (listaOrdemServico == null || listaOrdemServico.isEmpty()) {
			return null;
		}

		HashMap<String, Object> parametros = adicionarParametrosRelatorio(listaOrdemServico);
		JRBeanCollectionDataSource dsrel = new JRBeanCollectionDataSource(impressaoOrdemServicoBO.montaRelatorioOrdemServicoVO(listaOrdemServico));
		String pathJasper = JSFUtils.getRealPath() + "/resources/relatorio/OrdemServico.jasper";
		JasperPrint impressao = JasperFillManager.fillReport(pathJasper, parametros, dsrel);
		return JasperExportManager.exportReportToPdf(impressao);
	}
	
	private HashMap<String, Object> adicionarParametrosRelatorio(List<OrdemServico> listaOrdemServico) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		String caminhoImagem = JSFUtils.getRealPath() + "/resources/img/logoEmpresa.png";
		parametros.put("logoEmpresa", caminhoImagem);
		return parametros;
	}
	
//	private String obterPathImagemEmpresa() {
//		
//		return impressaoOrdemServicoBO.obterPathLogoEmpresa();
//	}
}
