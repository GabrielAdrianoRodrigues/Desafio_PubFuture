package controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dto.DespesaDTO;
import form.AtualizacaoDespesa;
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
	@Transactional
	public ResponseEntity<DespesaDTO> cadastrar(@RequestBody @Valid DespesaForm form, UriComponentsBuilder uriBuilder) {
		Despesa despesa = form.converter(contaRepository);
		despesaRepository.save(despesa);
		
		URI uri = uriBuilder.path("/Despesas/{id}").buildAndExpand(despesa.getID()).toUri();
		return ResponseEntity.created(uri).body(new DespesaDTO(despesa));
	}
	
	@GetMapping("/{id}")
	public DespesaDTO detalhar(@PathVariable Long id) {
		Despesa one = despesaRepository.getById(id);
		return new DespesaDTO(one);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDTO> atualizacao(@PathVariable Long id,@RequestBody @Valid AtualizacaoDespesa form) {
		Despesa despesa = form.atualizacao(id,despesaRepository);
		return ResponseEntity.ok(new DespesaDTO(despesa));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		despesaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
