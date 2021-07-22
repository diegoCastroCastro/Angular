package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.FacturaDetalle;

@Stateless
public class FacturaDetalleFacade extends AbstractFacade<FacturaDetalle> {

	@PersistenceContext(unitName = "ERP")
	private EntityManager em;

	public FacturaDetalleFacade() {
		super(FacturaDetalle.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<FacturaDetalle> findByPedido(int codigo){
		String jpql = "FROM FacturaDetalle f WHERE f.facturacabecera.codigo == "+codigo;
		
		return (List<FacturaDetalle>) em.createQuery(jpql).getResultList();
	}

}
