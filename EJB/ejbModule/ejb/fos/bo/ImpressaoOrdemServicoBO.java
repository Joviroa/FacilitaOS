package ejb.fos.bo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import ejb.fos.entidade.OrdemServico;
import ejb.fos.vo.OrdemServicoVO;

@Stateless
public class ImpressaoOrdemServicoBO {

	public List<OrdemServicoVO> montaRelatorioOrdemServicoVO(List<OrdemServico> listaOrdemServico){
		List<OrdemServicoVO> listaVO = new ArrayList<>();
		for(OrdemServico OS : listaOrdemServico) {
			listaVO.add(new OrdemServicoVO(OS));
		}
		return listaVO;
	}
}
