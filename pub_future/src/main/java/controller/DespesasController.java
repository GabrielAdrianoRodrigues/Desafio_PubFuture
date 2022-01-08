package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dto.DespesaDTO;
import model.Despesa;
import model.TipoDespesa;
import repository.DespesaRepository;

@RestController
@RequestMapping("/Despesas")
public class DespesasController {
	
	@Autowired
	private DespesaRepository despesaRepository;
		
		@GetMapping
		public List<DespesaDTO> list(TipoDespesa tipoDespesa) {
		if(tipoDespesa == null) {
			List<Despesa> despesas = despesaRepository.findAll();
			return DespesaDTO.converter(despesas);	
		} else {
			List<Despesa> despesas = despesaRepository.findByTipoDespesa(tipoDespesa);
			return DespesaDTO.converter(despesas);	
		}
	}
}
