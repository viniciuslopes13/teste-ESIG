package group.esig.teste.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import group.esig.teste.dao.TarefaDAO;
import group.esig.teste.models.Pessoa;
import group.esig.teste.models.Tarefa;

@ManagedBean
@SessionScoped
public class CadastroTarefaMBean {

	private Tarefa tarefa;
	private Pessoa responsavel;
	private String prioridade[] = new String[3];

	public CadastroTarefaMBean() {
		tarefa = new Tarefa();
		responsavel = new Pessoa();
		this.prioridade[0] = "Baixa";
		this.prioridade[1] = "Media";
		this.prioridade[2] = "Alta";
	}

	public void cadastrarTarefa() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		tarefa.setResponsavel(responsavel);
		try {
			tarefaDAO.inserir(tarefa);
			tarefa = new Tarefa();
			FacesMessage msg = new FacesMessage("Tarefa cadastrada com sucesso!");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage("", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public String[] getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String[] prioridade) {
		this.prioridade = prioridade;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}
}
