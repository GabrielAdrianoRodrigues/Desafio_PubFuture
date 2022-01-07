package br.com.pub_future.Modelo;
import java.time.LocalDate;

public class Receita {
	//Encapsulamento
	private Long id;
	private double valor;
	private LocalDate dataRecebimento;
	private LocalDate dataRecebimentoEsperado;
	private Conta conta;
	private TipoReceita tipoReceita;
	private String descricao;
	
	//Construtor
	public Receita(double valor, LocalDate dataRecebimento, LocalDate dataRecebimentoEsperado,Conta conta, TipoReceita tipoReceita, String descricao) {
		setValor(valor);
		setDataRecebimento(dataRecebimento);
		setDataRecebimentoEsperado(dataRecebimentoEsperado);
		setConta(conta);
		setTipoReceita(tipoReceita);
		setDescricao(descricao);
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
		Receita other = (Receita) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//Getters and Setters
	
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
			throw new IllegalArgumentException("Imposs�vel existir uma receita negativa");
		}
		this.valor = valor;
	}
	
	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}
	
	public void setDataRecebimento(LocalDate dataRecebimento) {
		if(dataRecebimento == null) {
			throw new IllegalArgumentException("Deve ser informado a data de recebimento");
		}
		this.dataRecebimento = dataRecebimento;
	}
	
	public LocalDate getDataRecebimentoEsperado() {
		return dataRecebimentoEsperado;
	}
	
	public void setDataRecebimentoEsperado(LocalDate dataRecebimentoEsperado) {
		if (dataRecebimentoEsperado == null) {
			throw new IllegalArgumentException("Deve ser informado o dia de recebimento esperado");
		}
		this.dataRecebimentoEsperado = dataRecebimentoEsperado;
	}
	
	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}
	
	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
