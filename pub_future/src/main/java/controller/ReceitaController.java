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

import dto.ReceitaDTO;
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
	public ResponseEntity<ReceitaDTO> cadastra(@RequestBody ReceitaForm form, UriComponentsBuilder uriBuilder) {
		Receita receita = form.converter(contaRepository);
		receitaRepository.save(receita);
		
		URI uri = uriBuilder.path("/Receitas/{id}").buildAndExpand(receita.getID()).toUri();
		return ResponseEntity.created(uri).body(new ReceitaDTO(receita));	
	}
	
}
