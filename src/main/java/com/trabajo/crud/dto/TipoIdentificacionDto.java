package com.trabajo.crud.dto;

public class TipoIdentificacionDto {

    public TipoIdentificacionDto(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    private int codigo;
    private String nombre;

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
}
