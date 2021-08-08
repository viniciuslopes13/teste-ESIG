package group.esig.teste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import group.esig.teste.config.BDConfig;
import group.esig.teste.models.Pessoa;
import group.esig.teste.models.Tarefa;

public class TarefaDAO {

	public void inserir(Tarefa tarefa) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "INSERT INTO tarefa(idresponsavel,titulo,descricao,deadline,status,prioridade) VALUES (?,?,?,?,?,?)";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, tarefa.getResponsavel().getId());
		statement.setString(2, tarefa.getTitulo());
		statement.setString(3, tarefa.getDescricao());
		statement.setObject(4, tarefa.getDeadline());
		statement.setString(5, "Em andamento");
		statement.setString(6, tarefa.getPrioridade());
		statement.execute();
	}

	public void atualizar(Tarefa tarefa) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "UPDATE tarefa SET idresponsavel=?, titulo=?, descricao=?, deadline=?, status=?, prioridade=? WHERE id=?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, tarefa.getResponsavel().getId());
		PessoaDAO peDao = new PessoaDAO();
		Pessoa p = peDao.getPessoa(tarefa.getResponsavel().getId());
		tarefa.setResponsavel(p);
		statement.setString(2, tarefa.getTitulo());
		statement.setString(3, tarefa.getDescricao());
		statement.setObject(4, tarefa.getDeadline());
		statement.setString(5, tarefa.getStatus());
		statement.setString(6, tarefa.getPrioridade());
		statement.setInt(7, tarefa.getId());
		statement.execute();
	}

	public void deletar(Tarefa tarefa) throws Exception {
		Connection conexao = BDConfig.getConnection();
		String sql = "DELETE FROM tarefa WHERE id=?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, tarefa.getId());
		statement.execute();
	}

	public List<Tarefa> listar() throws Exception {
		List<Tarefa> list = new ArrayList<>();
		Connection conexao = BDConfig.getConnection();
		String sql = "SELECT * FROM tarefa t INNER JOIN pessoa p ON t.idresponsavel=p.idpessoa";
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Tarefa tarefa = new Tarefa();
			tarefa.setId(rs.getInt("id"));
			tarefa.setTitulo(rs.getString("titulo"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setDeadline(rs.getObject("deadline", LocalDate.class));
			tarefa.setStatus(rs.getString("status"));
			tarefa.setPrioridade(rs.getString("prioridade"));
			Pessoa responsavel = new Pessoa();
			responsavel.setId(rs.getInt("idresponsavel"));
			responsavel.setNome(rs.getString("nome"));
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

	public List<Tarefa> buscar(String busca, String status) throws Exception {
		List<Tarefa> list = new ArrayList<>();
		Connection con = BDConfig.getConnection();
		String sql;
		PreparedStatement ps;
		if (status.equals("Todas")) {
			sql = "SELECT * FROM tarefa INNER JOIN pessoa ON idresponsavel=idpessoa "
					+ "WHERE (id::varchar LIKE ? OR titulo LIKE ? OR nome LIKE ? OR descricao LIKE ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + busca + "%");
			ps.setString(2, "%" + busca + "%");
			ps.setString(3, "%" + busca + "%");
			ps.setString(4, "%" + busca + "%");
		} else {
			sql = "SELECT * FROM tarefa INNER JOIN pessoa ON idresponsavel=idpessoa "
					+ "WHERE (id::varchar LIKE ? OR titulo LIKE ? OR nome LIKE ? OR descricao LIKE ?) AND status=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + busca + "%");
			ps.setString(2, "%" + busca + "%");
			ps.setString(3, "%" + busca + "%");
			ps.setString(4, "%" + busca + "%");
			ps.setString(5, status);
		}
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Tarefa tarefa = new Tarefa();
			tarefa.setId(rs.getInt("id"));
			tarefa.setTitulo(rs.getString("titulo"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setDeadline(rs.getObject("deadline", LocalDate.class));
			tarefa.setStatus(rs.getString("status"));
			tarefa.setPrioridade(rs.getString("prioridade"));
			Pessoa responsavel = new Pessoa();
			responsavel.setId(rs.getInt("idresponsavel"));
			responsavel.setNome(rs.getString("nome"));
			tarefa.setResponsavel(responsavel);
			list.add(tarefa);
		}
		return list;
	}

}
