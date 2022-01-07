package br.com.pub_future.Modelo;

public class Conta {
	//Encapsulamento
	private Long id;
	private double saldo;
	private TipoConta tipoConta;
	private String instituicaoFinanceira;
	
	public Conta(double saldo, TipoConta tipoConta, String instituicaoFinaceira) {
		setSaldo(saldo);
		setTipoConta(tipoConta);
		setInstituicaoFinanceira(instituicaoFinaceira);
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
		Conta other = (Conta) obj;
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
	
	public double getSaldo() {
		return saldo;
	}
	
	//Resolvi n�o tratar um saldo negativo pois acredito que a pessoa possa ficar devendo
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public TipoConta getTipoConta() {
		return tipoConta;
	}
	
	public void setTipoConta(TipoConta tipoConta) {
		if(tipoConta == null) {
			throw new IllegalArgumentException("Deve ser expecificado o tipo de conta");
		}
		this.tipoConta = tipoConta;
	}
	
	public String getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}
	
	public void setInstituicaoFinanceira(String instituicaoFinanceira) {
		if(instituicaoFinanceira == null) {
			throw new IllegalArgumentException("Deve ser expecificado a institui��o financeira");
		}
		this.instituicaoFinanceira = instituicaoFinanceira;
	}
	
	public void transferirEntreContas(Conta conta, double valor) {
		if (conta.getSaldo() > valor) {
			setSaldo(this.saldo + valor);
			conta.setSaldo(conta.getSaldo() - valor);
		}	
	}
		
}
