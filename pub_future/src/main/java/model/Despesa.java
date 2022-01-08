package model;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Despesa {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double valor;
	private LocalDate dataPagamento;
	private LocalDate dataPagamentoEsperado;
	@Enumerated(EnumType.STRING)
	private TipoDespesa tipoDespesa;
	@ManyToOne
	private Conta conta;
	
	/*public Despesa(double valor, LocalDate dataPagamento, LocalDate dataPagamentoEsperado, TipoDespesa tipoDespesa, Conta conta) {
		setValor(valor);
		setDataPagamento(dataPagamento);
		setDataPagamentoEsperado(dataPagamentoEsperado);
		setConta(conta);
	}*/
	
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
