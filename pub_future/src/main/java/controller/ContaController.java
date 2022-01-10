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

import dto.ContaDTO;
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
	public ResponseEntity<ContaDTO> cadastrar(@RequestBody ContaForm form, UriComponentsBuilder uriBuilder) {
		Conta conta = form.converter();
		contaRepository.save(conta);
		
		URI uri = uriBuilder.path("/Contas/{id}").buildAndExpand(conta.getID()).toUri();
		return ResponseEntity.created(uri).body(new ContaDTO(conta));
	}
}
