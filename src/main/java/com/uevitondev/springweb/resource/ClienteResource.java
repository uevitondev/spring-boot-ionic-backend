package com.uevitondev.springweb.resource;

import com.uevitondev.springweb.domain.Cliente;
import com.uevitondev.springweb.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findClientes() {
        List<Cliente> lista = clienteService.findClientes();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findClienteById(id);
        return ResponseEntity.ok().body(cliente);
    }
}
