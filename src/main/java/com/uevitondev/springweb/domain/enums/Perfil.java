package com.uevitondev.springweb.domain.enums;

public enum Perfil {

    ADMIM(1, "ROLE_ADMIM"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int codigo;
    private String descricao;

    private Perfil(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new  IllegalArgumentException("Id inválido: " + cod);
    }
}
