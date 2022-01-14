package form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import model.Conta;
import repository.ContaRepository;

//Classe responsalvel por alterar os valores que podem sofrer mudanças
public class AtualizacaoConta {
	//BeanValidations 
	@NotNull @NotEmpty
	private double saldo;
	
	public Conta atualizacao(Long id, ContaRepository contaRepository) {
		Conta conta = contaRepository.getById(id);
		conta.setSaldo(this.saldo);
		return conta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}
