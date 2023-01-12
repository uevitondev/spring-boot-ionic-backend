package com.uevitondev.springweb.resource;

import com.uevitondev.springweb.domain.Cliente;
import com.uevitondev.springweb.dto.ClienteDTO;
import com.uevitondev.springweb.dto.ClienteNewDTO;
import com.uevitondev.springweb.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @PreAuthorize("hasAnyRole('ADMIN')")
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

    @GetMapping(value = "/email")
    public ResponseEntity<Cliente> findByEmail(@RequestParam(value = "email") String email) {
        Cliente cliente = clienteService.findByEmail(email);
        return ResponseEntity.ok().body(cliente);
    }


    @PostMapping
    public ResponseEntity<Void> insertCliente(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {
        Cliente cliente = clienteService.fromDto(clienteNewDTO);
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


    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Integer id) {
        clienteService.deleteClienteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPages", defaultValue = "24") Integer linesPerPages,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> list = clienteService.findPage(page, linesPerPages, orderBy, direction);
        Page<ClienteDTO> listDTO = list.map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping(value = "/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = clienteService.uploadProfilePicture(file);
        return ResponseEntity.created(uri).build();
    }
}
