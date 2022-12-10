package com.uevitondev.springweb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uevitondev.springweb.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento implements Serializable {
    @Serial
    private static final long serialVersionUID = 7529511784925876778L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;


    public PagamentoComBoleto() {

    }

    public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
