package albino.entity;

import java.util.Date;

public class PessoaEntity {


	private Integer id;
	private String nome;
	private String celular;
	private String telefone;
	private String email;
	private Date aniversario;
	private String pais;
	private String endereco;
	private String cidade;
	private String uf;

	//CONSTRUTORES
	public PessoaEntity(Integer id, String nome, String celular, String telefone, String email, Date aniversario, String pais,
			String endereco, String cidade, String uf) {
		super();
		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.telefone = telefone;
		this.email = email;
		this.aniversario = aniversario;
		this.pais = pais;
		this.endereco = endereco;
		this.cidade = cidade;
		this.uf = uf;
	}
	public PessoaEntity() {
		super();
		this.nome = "";
		this.celular = "";
		this.telefone = "";
		this.email = "";
		this.pais = "";
		this.endereco = "";
		this.cidade = "";
		this.uf = "";
	}
	
	// GETS N SETS
	
	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date date) {
		this.aniversario = date;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	//EQUALS
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
		PessoaEntity other = (PessoaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return nome;
	}

}
