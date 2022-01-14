package form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import model.Despesa;
import repository.DespesaRepository;

//Classe responsalvel por alterar os valores que podem sofrer mudanças
public class AtualizacaoDespesa {
	@NotNull @NotEmpty
	private double valor;
	
	
	public Despesa atualizacao(Long id, DespesaRepository despesaRepository) {
		Despesa despesa = despesaRepository.getById(id);
		despesa.setValor(this.valor);
		return despesa;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
