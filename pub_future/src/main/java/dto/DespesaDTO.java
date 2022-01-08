package dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import model.Despesa;

public class DespesaDTO {
	private Long id;
	private double valor;
	private LocalDate dataPagamento;
	private LocalDate dataPagamentoEsperado;
	
	public DespesaDTO(Despesa despesa) {
		this.id = despesa.getID();
		this.valor = despesa.getValor();
		this.dataPagamento = despesa.getDataPagamento();
		this.dataPagamentoEsperado = despesa.getDataPagamentoEsperado();
	}
	
	public static List<DespesaDTO> converter(List<Despesa> despesas) {
		return despesas.stream().map(DespesaDTO::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	
	public double getValor() {
		return valor;
	}
	
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public LocalDate getDataPagamentoEsperado() {
		return dataPagamentoEsperado;
	}
}
