package group.esig.teste.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import group.esig.teste.dao.PessoaDAO;
import group.esig.teste.models.Pessoa;

@ManagedBean
@SessionScoped
public class CadastroResponsavelMBean {

	private Pessoa responsavel;
	private List<Pessoa> listaResponsaveis;

	public CadastroResponsavelMBean() {
		responsavel = new Pessoa();
		listaResponsaveis = new ArrayList<Pessoa>();
	}

	public void inserirPessoa() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		try {
			pessoaDAO.inserir(responsavel);
			responsavel = new Pessoa();
			FacesMessage msg = new FacesMessage("Responsável cadastrado com sucesso!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage("", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public List<Pessoa> getListaResponsaveis() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		try {
			listaResponsaveis = pessoaDAO.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaResponsaveis;
	}

	public void setListaResponsaveis(List<Pessoa> listaResponsaveis) {
		this.listaResponsaveis = listaResponsaveis;
	}

}
