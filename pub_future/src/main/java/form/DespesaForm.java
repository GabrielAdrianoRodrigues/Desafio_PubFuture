package form;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import model.Conta;
import model.Despesa;
import model.TipoConta;
import model.TipoDespesa;
import repository.ContaRepository;

public class DespesaForm {
	private Long id;
	@NotNull @NotEmpty
	private double valor;
	@NotNull @NotEmpty
	private LocalDate dataPagamento;
	@NotNull @NotEmpty
	private LocalDate dataPagamentoEsperado;
	@NotNull @NotEmpty
	private TipoConta tipoDeConta;
	@NotNull @NotEmpty
	private TipoDespesa tipoDeDespesa;
	
	public Despesa converter(ContaRepository contaRepository) {
		List<Conta> contas = contaRepository.findByTipoConta(tipoDeConta);
		Conta conta = contas.get(0);
		return new Despesa(this.valor, this.dataPagamento, this.dataPagamentoEsperado, conta , this.tipoDeDespesa);
	}
	
	public TipoDespesa getTipoDeDespesa() {
		return tipoDeDespesa;
	}

	public void setTipoDeDespesa(TipoDespesa tipoDeDespesa) {
		this.tipoDeDespesa = tipoDeDespesa;
	}

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public LocalDate getDataPagamentoEsperado() {
		return dataPagamentoEsperado;
	}
	
	public void setDataPagamentoEsperado(LocalDate dataPagamentoEsperado) {
		this.dataPagamentoEsperado = dataPagamentoEsperado;
	}
	
	public TipoConta getTipoDeConta() {
		return tipoDeConta;
	}
	
	public void setTipoDeConta(TipoConta tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}
	
	
}
