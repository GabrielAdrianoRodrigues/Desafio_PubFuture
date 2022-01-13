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

import dto.ContaDTO;
import form.AtualizacaoConta;
import form.ContaForm;
import model.Conta;
import model.TipoConta;
import repository.ContaRepository;


@RestController
@RequestMapping("/Contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public List<ContaDTO> lista(TipoConta tipoConta) {
		if (tipoConta == null) {
			List<Conta> contas = contaRepository.findAll();
			return ContaDTO.converter(contas);
		} else {
			List<Conta> contas =  contaRepository.findByTipoConta(tipoConta);
			return ContaDTO.converter(contas);
		}
	}
	
	@PostMapping 
	@Transactional
	public ResponseEntity<ContaDTO> cadastrar(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
		Conta conta = form.converter();
		contaRepository.save(conta);
		
		URI uri = uriBuilder.path("/Contas/{id}").buildAndExpand(conta.getID()).toUri();
		return ResponseEntity.created(uri).body(new ContaDTO(conta));
	}
	
	@GetMapping("/{id}")
	public ContaDTO detalhar(@PathVariable Long id) {
		Conta one = contaRepository.getById(id);
		return new ContaDTO(one);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContaDTO> atualizacao(@PathVariable Long id,@RequestBody @Valid AtualizacaoConta form) {
		Conta conta = form.atualizacao(id,contaRepository);
		return ResponseEntity.ok(new ContaDTO(conta));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		contaRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
