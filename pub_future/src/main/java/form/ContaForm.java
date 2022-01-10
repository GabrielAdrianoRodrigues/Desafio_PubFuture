package form;

import model.Conta;
import model.TipoConta;

public class ContaForm {
	private Long id;
	private double saldo;
	private String instituicaoFinanceira;
	private TipoConta tipoDeConta;
	
	public Conta converter() {
		return new Conta(this.saldo,this.tipoDeConta ,this.instituicaoFinanceira);
	}
	
	
	public TipoConta getTipoDeConta() {
		return tipoDeConta;
	}

	public void setTipoDeConta(TipoConta tipoDeConta) {
		this.tipoDeConta = tipoDeConta;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}
	
	public void setInstituicaoFinanceira(String instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}
	
}
