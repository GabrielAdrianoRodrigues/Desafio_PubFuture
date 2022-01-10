package controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dto.DespesaDTO;
import form.DespesaForm;
import model.Despesa;
import model.TipoDespesa;
import repository.ContaRepository;
import repository.DespesaRepository;

@RestController
@RequestMapping("/Despesas")
public class DespesasController {
	
	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private ContaRepository contaRepository;
		
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
	
	@PostMapping
	public ResponseEntity<DespesaDTO> cadastrar(@RequestBody DespesaForm form, UriComponentsBuilder uriBuilder) {
		Despesa despesa = form.converter(contaRepository);
		despesaRepository.save(despesa);
		
		URI uri = uriBuilder.path("/Despesas/{id}").buildAndExpand(despesa.getID()).toUri();
		return ResponseEntity.created(uri).body(new DespesaDTO(despesa));
	}
}
