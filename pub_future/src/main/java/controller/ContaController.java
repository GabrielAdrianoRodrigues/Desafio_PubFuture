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

import dto.ContaDTO;
import form.AtualizacaoConta;
import form.ContaForm;
import model.Conta;
import model.TipoConta;
import repository.ContaRepository;

//Classe do package Controller ultilizada para realizar o CRUD de conta
@RestController
@RequestMapping("/Contas")
public class ContaController {
	
	//Cria um objeto de repository uma interface do spring
	@Autowired
	private ContaRepository contaRepository;
	
	//Metodo responsavel por listar todas as contas, ela nao retorna as contas em si, e sim seus DTO(data transferer objects)
	@GetMapping
	public List<ContaDTO> lista(TipoConta tipoConta) {
		if (tipoConta == null) {
			//se nao for especificado o tipo da conta o metodo retornara todas as contas
			List<Conta> contas = contaRepository.findAll();
			return ContaDTO.converter(contas);
		} else {
			//caso seja especificado retornara as contas do tipo determinado
			List<Conta> contas =  contaRepository.findByTipoConta(tipoConta);
			return ContaDTO.converter(contas);
		}
	}
	
	@PostMapping 
	@Transactional
	//Metodo responsavel por cadastrar novas contas no banco de dados
	public ResponseEntity<ContaDTO> cadastrar(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
		//Cria a conta a partir de um form
		Conta conta = form.converter();
		//Salva no banco de dados
		contaRepository.save(conta);
		
		//Gera o URI da conta criada
		URI uri = uriBuilder.path("/Contas/{id}").buildAndExpand(conta.getID()).toUri();
		//Faz o retorno de uma resposta positiva 
		return ResponseEntity.created(uri).body(new ContaDTO(conta));
	}
	
	//Metodo reponsavel por detalhar somente uma conta em especifico
	//Busca de acordo com o id 
	@GetMapping("/{id}")
	public ResponseEntity<ContaDTO> detalhar(@PathVariable Long id) {
		//Tratamento para caso de notFound criando um optional e checando se ele existe
		Optional<Conta> one = contaRepository.findById(id);
		if(one.isPresent()) {
			return ResponseEntity.ok(new ContaDTO(one.get()));
		}	
		//Caso não exista retorna um error 404
		return ResponseEntity.notFound().build();
	}
	
	//Metodo responsalvel por alterar e atualizar uma conta no banco de dados
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContaDTO> atualizacao(@PathVariable Long id,@RequestBody @Valid AtualizacaoConta form) {
		//Mesmo tratamento para notFound
		Optional<Conta> optional = contaRepository.findById(id);
		if(optional.isPresent()) {
			Conta conta = form.atualizacao(id,contaRepository);
			return ResponseEntity.ok(new ContaDTO(conta));
		}	
		return ResponseEntity.notFound().build();
	}
	
	//Metodo de delete simples
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		//Novamente tratando o error 404
		Optional<Conta> optional = contaRepository.findById(id);
		if(optional.isPresent()) {
			contaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
