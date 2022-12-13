package com.uevitondev.springweb.services;

import com.uevitondev.springweb.domain.Categoria;
import com.uevitondev.springweb.domain.Cliente;
import com.uevitondev.springweb.dto.CategoriaDTO;
import com.uevitondev.springweb.exceptions.DataintegrityViolationException;
import com.uevitondev.springweb.exceptions.ObjectNotFoundException;
import com.uevitondev.springweb.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findCategoriaById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insertCategoria(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Categoria categoria) {
        Categoria newCategoria = findCategoriaById(categoria.getId());
        updateData(newCategoria, categoria);
        return categoriaRepository.save(newCategoria);
    }

    public void deleteCategoriaById(Integer id) {
        findCategoriaById(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataintegrityViolationException("Não é possível excluir uma Categoria que possui produtos!");
        }
    }

    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPages, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPages, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDto(CategoriaDTO categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    private void updateData(Categoria newCategoria, Categoria categoria) {
        newCategoria.setNome(categoria.getNome());
    }


}
