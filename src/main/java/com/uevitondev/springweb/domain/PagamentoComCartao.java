package com.uevitondev.springweb.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.uevitondev.springweb.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1535784052099885261L;

    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
