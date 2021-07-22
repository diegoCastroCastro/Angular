package ec.edu.ups.modelsJSON;

import javax.json.bind.annotation.JsonbProperty;


public class UsuarioJSON {
	private int id;

	
	@JsonbProperty("cedula-user")
	private String cedula;
	@JsonbProperty("nombre-user")
	private String nombre;
	@JsonbProperty("apellido-user")
	private String apellido;
	
	private String correo;

	@JsonbProperty("rol-user")
	private String rol;
	
	public UsuarioJSON() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioJSON(int id, String name) {
		this.id = id;
		this.nombre = name;
	    }

	// Necesario para que pueda ser parmetro: "id,name"
	public static UsuarioJSON valueOf(String s) {
		UsuarioJSON u = new UsuarioJSON();
		try {
			String id = s.substring(0, s.indexOf(","));
			u.setId(Integer.valueOf(id));
			u.setNombre(s.substring(s.indexOf(",") + 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	

	// getters & setters

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "UsuarioJSON [id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", correo=" + correo + ", rol=" + rol + "]";
	}
	
}
