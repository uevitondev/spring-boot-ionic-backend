package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.Pedido;
import com.uevitondev.springweb.exceptions.ObjectNotFoundException;
import com.uevitondev.springweb.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }


    public Pedido buscarPedidoById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


}
