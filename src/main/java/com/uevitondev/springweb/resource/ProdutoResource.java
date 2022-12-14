package com.uevitondev.springweb.resource;

import com.uevitondev.springweb.domain.Produto;
import com.uevitondev.springweb.dto.ProdutoDTO;
import com.uevitondev.springweb.resource.utils.URL;
import com.uevitondev.springweb.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findProdutos() {
        List<Produto> lista = produtoService.findProdutos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findProdutoById(@PathVariable Integer id) {
        Produto produto = produtoService.findProdutoById(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                     @RequestParam(value = "categorias", defaultValue = "") String categorias,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPages", defaultValue = "24") Integer linesPerPages,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> listaCategoriasIds = URL.decodeIntList(categorias);
        Page<Produto> list = produtoService.search(nomeDecoded, listaCategoriasIds, page, linesPerPages, orderBy, direction);
        Page<ProdutoDTO> listDTO = list.map(produto -> new ProdutoDTO(produto));
        return ResponseEntity.ok().body(listDTO);
    }


}
