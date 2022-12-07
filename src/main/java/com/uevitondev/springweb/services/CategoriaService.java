package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.Categoria;
import com.uevitondev.springweb.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;


    public void salvarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }


    public void salvarCategorias(List<Categoria> listaCategoria) {
        categoriaRepository.saveAll(listaCategoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }


    public Categoria listarCategoriaById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }


}
