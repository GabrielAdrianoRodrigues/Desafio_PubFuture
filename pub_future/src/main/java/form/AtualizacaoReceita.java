package form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import model.Receita;
import repository.ReceitaRepository;

public class AtualizacaoReceita {
	@NotNull @NotEmpty
	private double valor;
	
	public Receita atualizacao(Long id, ReceitaRepository receitaRepository) {
		Receita receita = receitaRepository.getById(id);
		receita.setValor(this.valor);
		return receita;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
