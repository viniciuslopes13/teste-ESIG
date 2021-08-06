package group.esig.teste.dao;

import group.esig.teste.models.Pessoa;

public class testePessoaDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Francisco Vinicius");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		try {
			pessoaDAO.inserir(pessoa);
			System.out.println("Registro inserido!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
