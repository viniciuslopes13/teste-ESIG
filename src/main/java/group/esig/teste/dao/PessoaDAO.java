package group.esig.teste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import group.esig.teste.config.BDConfig;
import group.esig.teste.models.Pessoa;

public class PessoaDAO {

	public void inserir(Pessoa pessoa) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "INSERT INTO pessoa(nome) VALUES (?)";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, pessoa.getNome());
		statement.execute();
	}

	public List<Pessoa> listar() throws Exception {
		List<Pessoa> list = new ArrayList<Pessoa>();
		Connection conexao = BDConfig.getConnection();
		String sql = "SELECT * FROM pessoa";
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Pessoa pessoa = new Pessoa();
			pessoa.setId(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			list.add(pessoa);
		}
		return list;
	}

}
