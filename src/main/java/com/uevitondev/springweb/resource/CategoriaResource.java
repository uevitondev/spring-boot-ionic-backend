package com.uevitondev.springweb.resource;


import com.uevitondev.springweb.domain.Categoria;
import com.uevitondev.springweb.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping(value = "/salvar")
    public ResponseEntity<List<Categoria>> salvarCategoria() {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        List<Categoria> listaCategoria = Arrays.asList(cat1, cat2);
        categoriaService.salvarCategorias(listaCategoria);

        return ResponseEntity.ok().body(categoriaService.listarCategorias());

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> listarCategoriaById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.listarCategoriaById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> lista = categoriaService.listarCategorias();
        return ResponseEntity.ok().body(lista);
    }


}
