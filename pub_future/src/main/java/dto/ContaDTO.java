package dto;

import java.util.List;
import java.util.stream.Collectors;

import model.Conta;

//Classe de DTO(data transfer object) ela seleciona os principais dados a serem tranferidos e converte objetos
public class ContaDTO {
	private Long id;
	private double saldo;
	private String instituicaoFinanceira;
	
	//Construtor responsavel por obter os dados
	public ContaDTO(Conta conta) {
		this.id = conta.getID();
		this.saldo = conta.getSaldo();
		this.instituicaoFinanceira = conta.getInstituicaoFinanceira();
	}
	
	//Metodo que faz a converção
	public static List<ContaDTO> converter(List<Conta> contas) {
		return contas.stream().map(ContaDTO::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public String getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}
}
