package albino.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import albino.entity.PessoaEntity;
import albino.dao.Conexao;

public class PessoaDao {

	static final String TABELA = "PUBLIC.PESSOA";

	public static Boolean salvar(PessoaEntity entidade) throws SQLException {

		if(Conexao.getCon()==null){
			Conexao.conectar();
		}

		Statement stm = Conexao.con.createStatement();
		StringBuffer comando = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// "ID", "NOME", "CELULAR", "TELEFONE", "EMAIL", "ANIVERSARIO", "PAIS", "ENDERECO", "CIDADE", "UF" 

		if (entidade.getId()!=null) {
			//UPDATE
			comando.append("UPDATE "+TABELA+" SET ");
			comando.append("NOME = '"+entidade.getNome()+"', ");
			comando.append("CELULAR = '"+entidade.getCelular()+"', ");
			comando.append("TELEFONE = '"+entidade.getTelefone()+"', ");
			comando.append("EMAIL = '"+entidade.getEmail()+"', ");
			comando.append("ANIVERSARIO = '"+sdf.format(entidade.getAniversario())+"', ");
			comando.append("PAIS = '"+entidade.getPais()+"', ");
			comando.append("ENDERECO = '"+entidade.getEndereco()+"', ");
			comando.append("CIDADE = '"+entidade.getCidade()+"', ");
			comando.append("UF = '"+entidade.getUf()+"'");
			comando.append(" WHERE ID ="+entidade.getId());
		} else {
			//INSERT OK
			System.out.println(entidade);
			comando.append("INSERT INTO "+TABELA+ " (ID , NOME, CELULAR, TELEFONE, EMAIL, ANIVERSARIO, PAIS, ENDERECO, CIDADE, UF");
			comando.append(") VALUES (");

			comando.append(entidade.getId());
			comando.append(", '");
			comando.append(entidade.getNome());
			comando.append("', '");
			comando.append(entidade.getCelular());
			comando.append("', '");
			comando.append(entidade.getTelefone());
			comando.append("', '");
			comando.append(entidade.getEmail());
			comando.append("', '");
			comando.append(sdf.format(entidade.getAniversario()));
			comando.append("', '");
			comando.append(entidade.getPais());
			comando.append("', '");
			comando.append(entidade.getEndereco());
			comando.append("', '");
			comando.append(entidade.getCidade());
			comando.append("', '");
			comando.append(entidade.getUf());
			comando.append("')");
		}

		try {
			stm.execute(comando.toString());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stm.close();
		return false;

	}

	public static Boolean remover(Integer id) throws SQLException{
		if(Conexao.getCon()==null){
			Conexao.conectar();
		}
		Statement stm = Conexao.con.createStatement();
		StringBuffer comando = new StringBuffer();
		comando.append("DELETE FROM "+TABELA+" WHERE ID="+id);
	
		System.out.println(comando);
		
		try {
			stm.execute(comando.toString());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stm.close();
		return false;
		
	
	}

	public static List<PessoaEntity> buscar() throws SQLException{
		if(Conexao.getCon()==null){
			Conexao.conectar();
		}
		Statement stm = Conexao.con.createStatement();

		List<PessoaEntity> resultados = new ArrayList<PessoaEntity>();
		PessoaEntity entidade = new PessoaEntity();

		try {
			ResultSet rs = stm.executeQuery("SELECT * FROM "+TABELA+" ORDER BY NOME");
			while (rs.next()) {
				entidade = new PessoaEntity();
				entidade.setId(rs.getInt(1));
				entidade.setNome(rs.getString(2));
				entidade.setCelular(rs.getString(3));
				entidade.setTelefone(rs.getString(4));
				entidade.setEmail(rs.getString(5));
				entidade.setAniversario(rs.getDate(6));
				entidade.setPais(rs.getString(7));
				entidade.setEndereco(rs.getString(8));
				entidade.setCidade(rs.getString(9)); 
				entidade.setUf(rs.getString(10));
				resultados.add(entidade);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stm.close();
		return resultados;
	}
	
	public static List<PessoaEntity> buscar(String nome) throws SQLException{
		if(Conexao.getCon()==null){
			Conexao.conectar();
		}
		Statement stm = Conexao.con.createStatement();

		List<PessoaEntity> resultados = new ArrayList<PessoaEntity>();
		PessoaEntity entidade = new PessoaEntity();

		try {
			ResultSet rs = stm.executeQuery("SELECT * FROM "+TABELA+" P WHERE P.NOME LIKE '%"+nome+"%' ORDER BY NOME");
			while (rs.next()) {
				entidade = new PessoaEntity();
				entidade.setId(rs.getInt(1));
				entidade.setNome(rs.getString(2));
				entidade.setCelular(rs.getString(3));
				entidade.setTelefone(rs.getString(4));
				entidade.setEmail(rs.getString(5));
				entidade.setAniversario(rs.getDate(6));						
				entidade.setPais(rs.getString(7));
				entidade.setEndereco(rs.getString(8));
				entidade.setCidade(rs.getString(9)); 
				entidade.setUf(rs.getString(10));
				resultados.add(entidade);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stm.close();
		return resultados;
	}

}



