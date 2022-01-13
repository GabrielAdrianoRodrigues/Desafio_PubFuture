package form;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import model.Conta;
import model.Receita;
import model.TipoConta;
import model.TipoReceita;
import repository.ContaRepository;

public class ReceitaForm {
	private Long id;
	@NotNull @NotEmpty
	private double valor;
	@NotNull @NotEmpty
	private LocalDate dataRecebimento;
	@NotNull @NotEmpty
	private LocalDate dataRecebimentoEsperado;
	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private TipoConta tipoDeConta;
	@NotNull @NotEmpty
	private TipoReceita tipoDeReceita;
	
	public Receita converter(ContaRepository contaRepository) {
		List<Conta> contas = contaRepository.findByTipoConta(tipoDeConta);
		Conta conta = contas.get(0);
		return new Receita(this.valor, this.dataRecebimento, this.dataRecebimentoEsperado, conta, this.descricao, this.tipoDeReceita);
	}
	
	public Long getID() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TipoConta getTipoDeConta() {
		return tipoDeConta;
	}

	public void setTipoDeConta(TipoConta tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}

	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}
	
	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}
	
	public LocalDate getDataRecebimentoEsperado() {
		return dataRecebimentoEsperado;
	}
	
	public void setDataRecebimentoEsperado(LocalDate dataRecebimentoEsperado) {
		this.dataRecebimentoEsperado = dataRecebimentoEsperado;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
