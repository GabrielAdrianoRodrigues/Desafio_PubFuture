package form;

import java.time.LocalDate;
import java.util.List;

import model.Conta;
import model.Receita;
import model.TipoConta;
import repository.ContaRepository;

public class ReceitaForm {
	
	private double valor;
	private LocalDate dataRecebimento;
	private LocalDate dataRecebimentoEsperado;
	private String descricao;
	private TipoConta tipoDeConta;
	
	public Receita converter(ContaRepository contaRepository) {
		List<Conta> conta = contaRepository.findByTipoConta(tipoDeConta);s
		return new Receita(this.valor, this.dataRecebimento, this.dataRecebimentoEsperado, conta, descricao);
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
