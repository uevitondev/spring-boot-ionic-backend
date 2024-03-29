package com.uevitondev.springweb.dto;

import com.uevitondev.springweb.domain.Estado;

import java.io.Serializable;

public class EstadoDTO implements Serializable {

    private Integer id;
    private String nome;

    public EstadoDTO() {
    }

    public EstadoDTO(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
