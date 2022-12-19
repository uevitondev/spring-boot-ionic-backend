package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.ItemPedido;
import com.uevitondev.springweb.domain.PagamentoComBoleto;
import com.uevitondev.springweb.domain.Pedido;
import com.uevitondev.springweb.domain.enums.EstadoPagamento;
import com.uevitondev.springweb.exceptions.ObjectNotFoundException;
import com.uevitondev.springweb.repositories.ItemPedidoRepository;
import com.uevitondev.springweb.repositories.PagamentoRepository;
import com.uevitondev.springweb.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;


    public List<Pedido> findPedidos() {
        return pedidoRepository.findAll();
    }


    public Pedido findPedidoById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insertPedido(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstant(new Date());
        pedido.setCliente(clienteService.findClienteById(pedido.getCliente().getId()));
        pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        PagamentoComBoleto pagB = null;
        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            pagB = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagB, pedido.getInstant());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.findProdutoById(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        emailService.sendOrderConfirmationHtmlEmail(pedido);

        return pedido;
    }


}
