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

import dto.ReceitaDTO;
import form.AtualizacaoReceita;
import form.ReceitaForm;
import model.Receita;
import model.TipoReceita;
import repository.ContaRepository;
import repository.ReceitaRepository;

@RestController
@RequestMapping("/Receitas")
public class ReceitaController {
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public List<ReceitaDTO> list(TipoReceita tipoReceita) {
		if(tipoReceita == null) {
			List<Receita> receitas = receitaRepository.findAll();
			return ReceitaDTO.converter(receitas);
		} else {
			List<Receita> receitas = receitaRepository.findByTipoReceita(tipoReceita);
			return ReceitaDTO.converter(receitas);
		}
	}
	
	@PostMapping
	public ResponseEntity<ReceitaDTO> cadastra(@RequestBody @Valid ReceitaForm form, UriComponentsBuilder uriBuilder) {
		Receita receita = form.converter(contaRepository);
		receitaRepository.save(receita);
		
		URI uri = uriBuilder.path("/Receitas/{id}").buildAndExpand(receita.getID()).toUri();
		return ResponseEntity.created(uri).body(new ReceitaDTO(receita));	
	}
	
	@GetMapping("/{id}")
	public ReceitaDTO detalhar(@PathVariable Long id) {
		Receita one = receitaRepository.getById(id);
		return new ReceitaDTO(one);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDTO> atualizacao(@PathVariable Long id,@RequestBody @Valid AtualizacaoReceita form) {
		Receita receita = form.atualizacao(id,receitaRepository);
		return ResponseEntity.ok(new ReceitaDTO(receita));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		receitaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
