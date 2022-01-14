package dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import model.Receita;

//Tem a mesma função da ContaDTO que ja foi documentada
public class ReceitaDTO {
	private Long id;
	private double valor;
	private LocalDate dataRecebimento;
	private LocalDate dataRecebimentoEsperado;
	private String descricao;
	
	public ReceitaDTO(Receita receita) {
		this.id = receita.getID();
		this.valor = receita.getValor();
		this.dataRecebimento = receita.getDataRecebimento();
		this.dataRecebimentoEsperado = receita.getDataRecebimentoEsperado();
		this.descricao = receita.getDescricao();
	}
	
	public static List<ReceitaDTO> converter(List<Receita> receitas) {
		return receitas.stream().map(ReceitaDTO::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	
	public double getValor() {
		return valor;
	}
	
	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}
	
	public LocalDate getDataRecebimentoEsperado() {
		return dataRecebimentoEsperado;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
