package com.trabajo.crud.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Persona")
@Table(name ="persona")
public class Persona {

    @Id
    @Column(name="codigo", updatable = false, nullable = false)
    private int codigo;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="apellido", nullable = false)
    private String apellido;

    @Column(name="fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="identificacion", nullable = false)
    private int identificacion;

    @Column(name="codigo_tipo_identificacion", nullable = false)
    private int codigoTipoIdentificacion;

    @Column(name="codigo_estado", nullable = false)
    private int codigoEstado;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public int getCodigoTipoIdentificacion() {
        return codigoTipoIdentificacion;
    }

    public void setCodigoTipoIdentificacion(int codigoTipoIdentificacion) {
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Persona(int codigo, String nombre, String apellido, Date fechaNacimiento, String username, String password, int identificacion, int codigoTipoIdentificacion, int codigoEstado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.username = username;
        this.password = password;
        this.identificacion = identificacion;
        this.codigoTipoIdentificacion = codigoTipoIdentificacion;
        this.codigoEstado = codigoEstado;
    }

    public Persona() { super(); }
}
