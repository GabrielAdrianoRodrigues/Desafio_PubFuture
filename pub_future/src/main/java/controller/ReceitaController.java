package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public void cadastra(@RequestBody ReceitaForm form) {
		Receita receita = form.converter(contaRepository);
		receitaRepository.save(receita);
	}
	
}
