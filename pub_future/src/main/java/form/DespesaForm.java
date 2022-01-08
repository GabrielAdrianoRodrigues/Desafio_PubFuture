package form;

import java.time.LocalDate;

public class DespesaForm {
	private Long id;
	private double valor;
	private LocalDate dataPagamento;
	private LocalDate dataPagamentoEsperado;
	private String tipoDeConta;
	
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
	
	public String getTipoDeConta() {
		return tipoDeConta;
	}
	
	public void setTipoDeConta(String tipoDeConta) {
		this.tipoDeConta = tipoDeConta.toUpperCase();
	}
	
	
}
