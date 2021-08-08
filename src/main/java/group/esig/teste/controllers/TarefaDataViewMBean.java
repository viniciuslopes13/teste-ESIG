package group.esig.teste.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import group.esig.teste.dao.TarefaDAO;
import group.esig.teste.models.Tarefa;

@SessionScoped
@ManagedBean
public class TarefaDataViewMBean {

	private String busca;
	private String[] statusBusca = new String[3];
	private String statusEscolhido;
	private List<Tarefa> listTarefas;
	private Tarefa tarefaSelecionada;

	public TarefaDataViewMBean() {
		tarefaSelecionada = new Tarefa();
		listTarefas = new ArrayList<Tarefa>();
		this.busca = "";
		statusBusca[0] = "Em andamento";
		statusBusca[1] = "Concluída";
		statusBusca[2] = "Todas";
		listTarefas = listar();
	}

	public List<Tarefa> filtrar() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		if(statusEscolhido.equals(statusBusca[2])&& (busca.equals(null)||busca.equals("")||busca.isEmpty())) {
			return this.listar();
		}
		try {
			listTarefas = tarefaDAO.buscar(busca, statusEscolhido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTarefas;
	}

	public List<Tarefa> listar() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		try {
			listTarefas = tarefaDAO.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTarefas;
	}

	public void deletarTarefa() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		try {
			tarefaDAO.deletar(tarefaSelecionada);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmado", "Tarefa deletada com sucesso!");
		FacesContext.getCurrentInstance().addMessage("", msg);
		this.filtrar();
	}

	public void concluirTarefa() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		try {
			tarefaDAO.concluirTarefa(tarefaSelecionada);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmado",
				"A tarefa selecionada foi concluída!");
		FacesContext.getCurrentInstance().addMessage("", msg);
		this.filtrar();
	}
	
	public void atualizarTarefa() {
		TarefaDAO tarefaDAO = new TarefaDAO();
		try {
			tarefaDAO.atualizar(tarefaSelecionada);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tarefaSelecionada=new Tarefa();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmado",
				"A tarefa foi atualizada!");
		FacesContext.getCurrentInstance().addMessage("", msg);	
	}

	public String editar() {
		return "editar_tarefa?faces-redirect=true";
	}

	public String cancelar() {
		return "";
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String[] getStatusBusca() {
		return statusBusca;
	}

	public void setStatusBusca(String[] statusBusca) {
		this.statusBusca = statusBusca;
	}

	public String getStatusEscolhido() {
		return statusEscolhido;
	}

	public void setStatusEscolhido(String statusEscolhido) {
		this.statusEscolhido = statusEscolhido;
	}

	public List<Tarefa> getListTarefas() {
		return listTarefas;
	}

	public void setListTarefas(List<Tarefa> listTarefas) {
		this.listTarefas = listTarefas;
	}

	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}

}
