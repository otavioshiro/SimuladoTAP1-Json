package modelo;

public class Produto {

	private Integer id = 0;
	private String descricao;
	private Double valor;
	
	public Produto(Integer id, String descricao, Double valor){
		this.id += 1;
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
