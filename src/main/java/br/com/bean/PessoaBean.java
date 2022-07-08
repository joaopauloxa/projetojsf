package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dao.DaoGenerico;
import br.com.entidades.Pessoa;
import br.com.repositorio.IDaoPessoa;
import br.com.repositorio.IDaoPessoaImpl;

@ViewScoped
@ManagedBean(name="pessoaBean")
public class PessoaBean {
	private Pessoa pessoa = new Pessoa();
	private DaoGenerico<Pessoa> daoGenerico = new DaoGenerico<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	private IDaoPessoa idaoPessoa = new IDaoPessoaImpl();
			
	public String salvar() {
		pessoa = daoGenerico.merge(pessoa);
		carregarPessoas();
		return "";
	}
	
	public String novo() {
		pessoa = new Pessoa();
		return "";
	}
	
	public String remove() {
		daoGenerico.deletePorId(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		return "";
	}
	
	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGenerico.getListEntity(Pessoa.class);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGenerico<Pessoa> getDaoGenerico() {
		return daoGenerico;
	}

	public void setDaoGenerico(DaoGenerico<Pessoa> daoGenerico) {
		this.daoGenerico = daoGenerico;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public String logar() {
		Pessoa pessoaUsuario = idaoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
		if (pessoaUsuario != null) { // achou usuário
		    
			//adiciona o usuario na sessão usuariologado
			 FacesContext contexto = FacesContext.getCurrentInstance();
		     ExternalContext externalContext = contexto.getExternalContext();
		     externalContext.getSessionMap().put("usuariologado", pessoaUsuario.getLogin());
			return "primeirapagina.jsf";
		}
		return "index.jsf";
	}
	
}
