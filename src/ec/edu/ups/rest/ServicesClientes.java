package ec.edu.ups.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidad.*;
import ec.edu.ups.modelsJSON.RespuestaJSON;
import ec.edu.ups.modelsJSON.UsuarioJSON;

@Path("/cliente/")
public class ServicesClientes {
	UsuarioFacade user = new UsuarioFacade();

	@EJB
	private UsuarioFacade ejbUsuario;

	@EJB
	private FacturaCabeceraFacade ejbFactura;
	
	@EJB
	private FacturaDetalleFacade ejbDetalle;

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCliente(@PathParam("id") Integer id) {
		return "User [" + id + ",PathParam]";
	}

	@GET
	@Path("/query")
	@Produces(MediaType.TEXT_PLAIN)
	public String parametros(@DefaultValue("666") @QueryParam("id") Integer id,
			@DefaultValue("666,deamon") @QueryParam("person") Person u) {
		return "Id: " + id + "\n" + u.toString();
	}

	// Ejemplo con JSON

	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response get(@QueryParam("user") String usuario, @QueryParam("pass") String pass) throws Exception {

		Usuario x = new Usuario();
		x = ejbUsuario.finByEmailAndPass(usuario, pass);

		if (x != null && x.isActivo()) {
			return Response.ok("!INICIO DE SESION CORRECTO").build();
		} else {
			return Response.status(404).entity("Credenciales incorrectas o usuario no esta activo ").build();
		}
	}
	
	/**
	 * 1 Iniciar Sesión con base a un usuario y contraseña
	 * @param usuario
	 * @param pass
	 * @return
	 * @throws Exception
	 */

	@POST
	@Path("/log")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsu(@FormParam("user") String usuario, @FormParam("pass") String pass) throws Exception {
		Usuario x = new Usuario();
		
		Jsonb jsonb = JsonbBuilder.create();
		RespuestaJSON res = new RespuestaJSON();
		
		x = ejbUsuario.finByEmailAndPass(usuario, pass);
		System.out.println("Usuario "+x.toString());
		if ( !x.getNombre().isEmpty() && x.isActivo()) {
			res.setEstado("ok");
			res.setMensaje("Login Correcto");
			
		} else if (x.getCedula().isEmpty()){
			res.setEstado("Fallido");
			res.setMensaje("Login Inorrecto");
		} 
		return Response.ok(jsonb.toJson(res)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}

	
	/*
	 * Lista pedidos por numero de cedula
	 */
	@GET
	@Path("/estadoPedido/{cedula}")
	@Produces(MediaType.TEXT_HTML)
	public Response listaPedidos(@PathParam("cedula") String ced) {

		Jsonb jsonb = JsonbBuilder.create();
		
		
		
		List<FacturaCabecera> list;
		list = ejbFactura.findByCedula(ced);

		if (list.size() != 0) {
			
		
		return Response.ok(jsonb.toJson(list)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
		}else {
			return Response.status(500).entity("No hay pedido para la cedula ingresada : " + ced).build();
		}
	}

	/*
	 * Servicio que lista los pedidos por numero de cedula
	 */

	@GET
	@Path("/estadoPedido2/{ced}")
	@Produces(MediaType.TEXT_HTML)
	public String html(@PathParam("ced") String ced) {
		List<FacturaCabecera> list;
		list = ejbFactura.findByCedula(ced);
		String nombre = list.get(0).getUsuario().getNombre() + list.get(0).getUsuario().getApellido();

		return "<html>\n" + "<header>" + "<title>Lista  de Pedidos de : </title>" + "</header>\n" + "<body>\n" + ""
				+ nombre + " Hello world\n" + "</body>\n" + "</html>";
	}

	/**
	 * 2 Registrar cuenta del cliente con base al número de cédula
	 * 
	 */
	@POST
	@Path("/registro")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postCliente(@FormParam("cedula") String cedula, @FormParam("correo") String email,
			@FormParam("clave") String clave) throws IOException {

		Usuario x = ejbUsuario.findByCedula(cedula);
		System.out.println("cliente encontrado : ->" + x.getCorreo());

		if (!x.getCorreo().isEmpty() && x.isActivo() && x.getRol().equals("cliente")) {
			x.setActivo(true);
			x.setCorreo(email);
			x.setPassword(clave);
			System.out.println("REST/client:usuario-->" + x.getCorreo());
			System.out.println("cliente ... " + x.toString());
			ejbUsuario.edit(x);
			return Response.ok("Usuario creado correctamente correo : " + x.getCorreo() + " clave : "  + x.getPassword()).build();
		}else {
			return Response.status(500).entity("Usuario no consta en la bd").build();
		}
		
	}
	
	@GET
	@Path("/estadoPedido{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaPedidos1(@QueryParam("cedula") String ced) {
		Jsonb jsonb = JsonbBuilder.create();
		
		List<FacturaCabecera> list;
		list = ejbFactura.findByCedula(ced);
		if (list.size() != 0) {
		return Response.ok(jsonb.toJson(list)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
		}else {
			return Response.status(500).entity("No hay pedido para la cedula ingresada : " + ced).build();
		}
	}
	
	
	
	/**
	 * 3 Modificar datos de la cuenta y personales del cliente
	 * 
	 */
	
	@PUT
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postCliente(@FormParam("cedula") String cedula, @FormParam("correo") String email,
			@FormParam("clave") String clave, @FormParam("nombre") String nombre, @FormParam("apellido") String apellido ) throws IOException {

		Usuario x = ejbUsuario.findByCedula(cedula);
		System.out.println("cliente encontrado : ->" + x.getCorreo());

		if (!x.getCorreo().isEmpty() && x.isActivo() && x.getRol().equals("cliente")) {
			x.setActivo(true);
			x.setCorreo(email);
			x.setPassword(clave);
			x.setNombre(nombre);
			x.setApellido(apellido);
			
			System.out.println("REST/client:usuario-->" + x.getCorreo());
			System.out.println("cliente ... " + x.toString());
			ejbUsuario.edit(x);
			return Response.ok("Usuario actualizado correctamente : " + x.toString()).build();
		}else {
			return Response.status(500).entity("Usuario no consta en la bd").build();
		}
		
	}
	
	
	

	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putJSON(String jsonPerson) {
		Jsonb jsonb = JsonbBuilder.create();
		Person person = jsonb.fromJson(jsonPerson, Person.class);

		System.out.println("REST/client:putJSON-->" + person);
		return Response.status(204).entity("Usuario actualizaado..." + person).build();
	}

	
	/**
	 * 4 Anular cuenta del cliente. (eliminado lógico)
	 *
	 */
	@PUT
	@Path("/anular")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response anularCuenta(@FormParam("cedula") String cedula) {

		Usuario x = ejbUsuario.findByCedula(cedula);
		
		Jsonb jsonb = JsonbBuilder.create();
		RespuestaJSON res = new RespuestaJSON();
		
		if (!x.getNombre().isEmpty()) {
			
			res.setEstado("ok");
			res.setMensaje("Anulado Exitoso");
			x.setActivo(false);
			ejbUsuario.edit(x);
			
		}else {
			res.setEstado("Fallido");
			res.setMensaje("Anulado Incorrecto");
			
		}
		return Response.ok(jsonb.toJson(res)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	

	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@PathParam("id") Integer id) {
		System.out.println("REST/client:delete-->" + id);
		return Response.status(204).entity("Usuario borrado..." + id).build();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPersons() {

		Jsonb jsonb = JsonbBuilder.create();
		List<Person> list = new ArrayList<Person>();
		Person person1 = new Person(1, "Pepito", "pepito@test.com", 20, LocalDate.of(2020, 6, 30),
				BigDecimal.valueOf(123.7));
		Person person2 = new Person(2, "Juanito", "juanito@test.com", 21, LocalDate.of(2020, 6, 30),
				BigDecimal.valueOf(223.1));
		list.add(person1);

		// para evitar el error del CORS se agregan los headers
		return Response.ok(jsonb.toJson(list)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	@GET
	@Path("/list-users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listUsers() {
		List<Usuario> list = ejbUsuario.findAll();	
		Jsonb jsonb = JsonbBuilder.create();
		return Response.ok(jsonb.toJson(list)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	@POST
	@Path("/registro")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postCliente1(@FormParam("cedula") String cedula) {

		Usuario x = ejbUsuario.findByCedula(cedula);
		
		Jsonb jsonb = JsonbBuilder.create();
		RespuestaJSON res = new RespuestaJSON();
		
		if (!x.getCorreo().isEmpty() && x.isActivo() && x.getRol().equals("cliente")) {
			x.setActivo(true);
			res.setEstado("ok");
			res.setMensaje("Registro exitoso");
		}else {
			res.setEstado("fail");
			res.setMensaje("No fue posible el registro del usuario con la cedula"+cedula);
		}
		
		return Response.ok(jsonb.toJson(res)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	/**
	 * REVISAR SERVICIO
	 * @param id
	 * @return
	 */
	@GET
	@Path("/productosPedido")
	@Produces(MediaType.APPLICATION_JSON)
	public Response productos (@QueryParam("id") int id){
		
		List<FacturaDetalle> lista = ejbDetalle.findByPedido(id);
		
		Jsonb jsonb = JsonbBuilder.create();
		RespuestaJSON res = new RespuestaJSON();
		
		return Response.ok(jsonb.toJson(lista)).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}

}

