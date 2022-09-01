package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dao.DaoGenerico;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;
import br.com.repositorio.IDaoLancamento;
import br.com.repositorio.IDaoLancamentoImpl;
import jakarta.annotation.PostConstruct;

@ViewScoped
@ManagedBean(name = "lancamentobean")
public class LancamentoBean {
	
	private Lancamento lancamento = new Lancamento();
	private DaoGenerico<Lancamento> daoGenerico = new DaoGenerico<Lancamento>();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private IDaoLancamento daoLancamento = new IDaoLancamentoImpl(); 
		
		
	
	
	public String salvar() {
		 FacesContext context = FacesContext.getCurrentInstance();
	     ExternalContext externalContext = context.getExternalContext();
	     Pessoa pessoaUsuario = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
	     lancamento.setUsuario(pessoaUsuario);
	     daoGenerico.salvar(lancamento);
	     
	     carregarLancamentos();
	     
	     return "";
	}
	
	@PostConstruct
	private void carregarLancamentos() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = context.getExternalContext();
	    Pessoa pessoaUsuario = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
	    
		lancamentos = daoLancamento.consultar(pessoaUsuario.getId());		
	}


	public String novo() {
		lancamento = new Lancamento();
		return "";
	}
	
	public String remove() {
		daoGenerico.deletePorId(lancamento);
		lancamento = new Lancamento();
		carregarLancamentos();
		return "";
	}
	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	public DaoGenerico<Lancamento> getDaoGenerico() {
		return daoGenerico;
	}
	public void setDaoGenerico(DaoGenerico<Lancamento> daoGenerico) {
		this.daoGenerico = daoGenerico;
	}
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	
}
