package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.entidad.Usuario;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
	
	@PersistenceContext(unitName = "ERP")
	private EntityManager em;

	public UsuarioFacade() {
		super(Usuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public int contarFacturas(Usuario u) {

		String jpql = "SELECT COUNT(u.cedula) FROM Usuario u INNER JOIN FacturaCabecera f ON u.cedula = f.usuario.cedula WHERE u.cedula ='"
				+ u.getCedula() + "' ";

		Object obj = em.createQuery(jpql).getSingleResult();
		if (obj != null) {
			return Integer.valueOf(String.valueOf(obj));
		} else {
			return 0;
		}
	}

	public Usuario finByEmailAndPass(String correo, String pass) throws Exception {
		
		Usuario usu = new Usuario();
		usu.setCedula("");
		usu.setNombre("");
		usu.setApellido("");
		usu.setCorreo("");
		usu.setCambioPassword(false);
		usu.setPassword("");
		usu.setRol("");
		
		try {
			String jpql = "FROM Usuario u WHERE u.correo =  ?1 AND u.password = ?2";
			Query query = em.createQuery(jpql);
			query.setParameter(1, correo);
			query.setParameter(2, pass);
			System.out.println(jpql);
			Usuario x = new Usuario();
			x = (Usuario)query.getSingleResult();
			return x; 

		} catch (Exception e) {
			System.out.println("Error: " + e);
			return usu;
		}

	}
	
	public Usuario findByCedula(String cedula) {
		
		Usuario usu = new Usuario();
		usu.setCedula("");
		usu.setNombre("");
		usu.setApellido("");
		usu.setCorreo("");
		usu.setCambioPassword(false);
		usu.setPassword("");
		usu.setRol("");
		
		try {
			String jpql = "FROM Usuario u WHERE u.cedula = ?1";
			Query query = em.createQuery(jpql);
			query.setParameter(1, cedula);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return usu;
		}
	}
	
	public List<Usuario> findClientes() {
		String jpql = "FROM Usuario u WHERE u.rol = 'cliente' ORDER BY u.nombre DESC";
		return (List<Usuario>) em.createQuery(jpql).getResultList();
	}

	public List<Usuario> findEmpleados() {
		String jpql = "FROM Usuario u WHERE u.rol = 'empleado' ORDER BY u.nombre DESC";
		return (List<Usuario>) em.createQuery(jpql).getResultList();
	}
}
