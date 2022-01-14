package controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

//Esta classe faz basicamente a mesma função do ContaController que ja esta documentada
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
	public ResponseEntity<ReceitaDTO> detalhar(@PathVariable Long id) {
		Optional<Receita> one = receitaRepository.findById(id);
		if(one.isPresent()) {
			return ResponseEntity.ok(new ReceitaDTO(one.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDTO> atualizacao(@PathVariable Long id,@RequestBody @Valid AtualizacaoReceita form) {
		Optional<Receita> opticional = receitaRepository.findById(id);
		if (opticional.isPresent()) {
			Receita receita = form.atualizacao(id,receitaRepository);
			return ResponseEntity.ok(new ReceitaDTO(receita));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Receita> opticional = receitaRepository.findById(id);
		if (opticional.isPresent()) {
			receitaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
