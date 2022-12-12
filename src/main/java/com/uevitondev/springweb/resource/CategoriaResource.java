package com.uevitondev.springweb.resource;


import com.uevitondev.springweb.domain.Categoria;
import com.uevitondev.springweb.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> findCategorias() {
        List<Categoria> lista = categoriaService.findCategorias();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findCategoriaById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> insertCategoria(@RequestBody Categoria categoria) {
        categoria = categoriaService.insertCategoria(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        categoria = categoriaService.updateCategoria(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Integer id) {
        categoriaService.deleteCategoriaById(id);
        return ResponseEntity.noContent().build();
    }


}
