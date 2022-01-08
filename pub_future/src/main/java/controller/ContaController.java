package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dto.ContaDTO;
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
}
