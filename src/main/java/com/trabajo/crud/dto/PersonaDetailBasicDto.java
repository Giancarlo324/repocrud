package com.trabajo.crud.dto;


import java.util.Date;

public interface PersonaDetailBasicDto {

    String getNombre();

    String getApellido();

    String getTipoIdentificacion();

    int getIdentificacion();

    Date getFechaNacimiento();

    String getEstado();

}
