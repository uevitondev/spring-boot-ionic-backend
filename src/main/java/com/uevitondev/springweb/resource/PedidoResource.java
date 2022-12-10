package com.uevitondev.springweb.resource;

import com.uevitondev.springweb.domain.Pedido;
import com.uevitondev.springweb.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> lista = pedidoService.listarPedidos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> listarPedidoById(@PathVariable Integer id) {
        Pedido pedido = pedidoService.buscarPedidoById(id);
        return ResponseEntity.ok().body(pedido);
    }
}
