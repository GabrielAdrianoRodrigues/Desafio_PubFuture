package br.com.pub_future.Modelo;
import java.time.LocalDate;

public class Despesa {
	//Encapsulamento
	private Long id;
	private double valor;
	private LocalDate dataPagamento;
	private LocalDate dataPagamentoEsperado;
	private TipoDespesa tipoDespesa;
	private Conta conta;
	
	//Construtor
	public Despesa(double valor, LocalDate dataPagamento, LocalDate dataPagamentoEsperado, TipoDespesa tipoDespesa, Conta conta) {
		setValor(valor);
		setDataPagamento(dataPagamento);
		setDataPagamentoEsperado(dataPagamentoEsperado);
		setConta(conta);
	}
	
	//Sobrescrevi os metodos hashCode e equals para que não exista objetos repetidos 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//Getter and Setters
	
	public void setID(long id) {
		this.id = id;
	}
	
	public long getID() {
		return this.id;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		if(valor < 1) {
			throw new IllegalArgumentException("Imposs�vel existir um despesa negativa");
		}
		this.valor = valor;
	}
	
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(LocalDate dataPagamento) {
		if (dataPagamento == null) {
			throw new IllegalArgumentException("Deve ser informado o dia de pagamento");
		}
		this.dataPagamento = dataPagamento;
	}
	
	public LocalDate getDataPagamentoEsperado() {

		return dataPagamentoEsperado;
	}
	
	public void setDataPagamentoEsperado(LocalDate dataPagamentoEsperado) {
		if (dataPagamentoEsperado == null) {
			throw new IllegalArgumentException("Deve ser informado o dia de pagamento esperado");
		}
		this.dataPagamentoEsperado = dataPagamentoEsperado;
	}
	
	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}
	
	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		if(tipoDespesa == null) {
			throw new IllegalArgumentException("Deve ser expecificado o tipo de despesa");
		}
		this.tipoDespesa = tipoDespesa;
	}
	
	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		if (conta == null) {
			throw new IllegalArgumentException("Deve ser informado uma conta");
		}
		this.conta = conta;
	}
		
}
