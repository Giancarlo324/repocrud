package com.trabajo.crud.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;



@Entity
@Table(name ="estado")
@RestResource(rel="estados", path="estado")
public class Estado {
	
	@Id
	@Column(name="codigo", updatable = false, nullable = false)
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;

	@CreationTimestamp
	@Column(name="fecha_creacion", updatable = false)
	private Timestamp fechaCreacion;
	
	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@UpdateTimestamp
	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;
	
	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	public Estado() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	
	public Estado(int codigo, String nombre, Timestamp fechaCreacion, String usuarioCreacion, Timestamp fechaModificacion,
				  String usuarioModificacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
	}
}
