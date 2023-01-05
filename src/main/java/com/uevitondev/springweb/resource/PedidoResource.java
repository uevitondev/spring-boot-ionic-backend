package com.uevitondev.springweb.resource;

import com.uevitondev.springweb.domain.Pedido;
import com.uevitondev.springweb.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findPedidoById(@PathVariable Integer id) {
        Pedido pedido = pedidoService.findPedidoById(id);
        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping
    public ResponseEntity<Void> insertPedido(@Valid @RequestBody Pedido pedido) {
        pedido = pedidoService.insertPedido(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "linesPerPages", defaultValue = "24") Integer linesPerPages,
                                                 @RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
                                                 @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Pedido> list = pedidoService.findPage(page, linesPerPages, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

}
