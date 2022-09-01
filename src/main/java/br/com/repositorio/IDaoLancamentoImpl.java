package br.com.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.entidades.Lancamento;
import br.com.jpautil.JPAUtil;

public class IDaoLancamentoImpl implements IDaoLancamento {

	@Override
	public List<Lancamento> consultar(Long codUser) {
		List<Lancamento> lista = null;
		
		EntityManager entityManger = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManger.getTransaction();
		transaction.begin();
		
		lista = entityManger.createQuery("from lancamento where usuario.id = " + codUser).getResultList();
		transaction.commit();
		entityManger.close();
		
		return lista;
		
	}

}
