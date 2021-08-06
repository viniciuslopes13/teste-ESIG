package group.esig.teste.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import group.esig.teste.config.BDConfig;
import group.esig.teste.models.Pessoa;
import group.esig.teste.models.Tarefa;

public class TarefaDAO {

	public void inserir(Tarefa tarefa) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "INSERT INTO tarefa(idresponsavel,titulo,descricao,deadline,status) VALUES (?,?,?,?,?)";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, tarefa.getResponsavel().getId());
		statement.setString(2, tarefa.getTitulo());
		statement.setString(3, tarefa.getDescricao());
		statement.setDate(4, (Date) tarefa.getDeadline());
		statement.setString(5, "Em andamento");
		statement.execute();
	}

	public void update(Tarefa tarefa) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "UPDATE tarefa SET idresponsavel=?, titulo=?, descricao=?, deadline=?, status=? WHERE id=?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, tarefa.getResponsavel().getId());
		statement.setString(2, tarefa.getTitulo());
		statement.setString(3, tarefa.getDescricao());
		statement.setDate(4, (Date) tarefa.getDeadline());
		statement.setString(5, tarefa.getStatus());
		statement.setInt(6, tarefa.getId());
		statement.execute();
	}

	public void delete(Tarefa tarefa) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "DELETE FROM tarefa WHERE id=?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, tarefa.getId());
		statement.execute();
	}

	public List<Tarefa> listar() throws Exception {
		List<Tarefa> list = new ArrayList<>();
		Connection conexao = BDConfig.getConnection();
		String sql = "SELECT * FROM tarefa";
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Tarefa tarefa = new Tarefa();
			tarefa.setId(rs.getInt("id"));
			tarefa.setTitulo(rs.getString("titulo"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setDeadline(rs.getDate("deadline"));
			tarefa.setStatus(rs.getString("status"));
			Pessoa responsavel = new Pessoa();
			responsavel.setId(rs.getInt("idresponsavel"));
			tarefa.setResponsavel(responsavel);
			list.add(tarefa);
		}
		return list;
	}
	
	public void concluirTarefa(Tarefa tarefa) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "UPDATE tarefa SET status=? WHERE id=?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, "Concluída");
		statement.setInt(2, tarefa.getId());
		statement.execute();		
	}
	
	public List<Tarefa> buscar() throws Exception{
		List<Tarefa> list = new ArrayList<>();
		
		
		
		return list;
	}

}
