package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double saldo;
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta;
	private String instituicaoFinanceira;
	
	/*public Conta(double saldo, TipoConta tipoConta, String instituicaoFinaceira) {
		setSaldo(saldo);
		setTipoConta(tipoConta);
		setInstituicaoFinanceira(instituicaoFinaceira);
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
		Conta other = (Conta) obj;
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
	
	public double getSaldo() {
		return saldo;
	}
	
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
