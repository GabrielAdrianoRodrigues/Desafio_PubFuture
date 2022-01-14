package config;

//Essa classe e ultilizada para receber o erro informado para servidor e simplifica-lo para ser mais legivel ao programador
public class ErrorFormDTO {
	private String campo;
	private String erro;
	
	public ErrorFormDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public String getErro() {
		return erro;
	}
	
	public void setErro(String erro) {
		this.erro = erro;
	}
	
}
