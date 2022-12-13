package com.uevitondev.springweb.resource;

import com.uevitondev.springweb.domain.Cliente;
import com.uevitondev.springweb.dto.ClienteDTO;
import com.uevitondev.springweb.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAllClientes() {
        List<Cliente> list = clienteService.findAllClientes();
        List<ClienteDTO> listDTO = list.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findClienteById(id);
        return ResponseEntity.ok().body(cliente);
    }


    @PostMapping
    public ResponseEntity<Void> insertCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.fromDto(clienteDTO);
        cliente = clienteService.insertCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clieteDTO) {
        Cliente cliente = clienteService.fromDto(clieteDTO);
        cliente.setId(id);
        cliente = clienteService.updateCliente(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Integer id) {
        clienteService.deleteClienteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPages", defaultValue = "24") Integer linesPerPages,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> list = clienteService.findPage(page, linesPerPages, orderBy, direction);
        Page<ClienteDTO> listDTO = list.map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok().body(listDTO);
    }
}
