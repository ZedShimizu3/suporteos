package com.curso.resources;

import com.curso.domains.GrupoProduto;
import com.curso.domains.dtos.GrupoProdutoDTO;
import com.curso.repositories.GrupoProdutoRepository;
import com.curso.services.GrupoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/grupoproduto")
public class GrupoProdutoResource {

    @Autowired
    private GrupoProdutoService grupoProdutoService;

    @GetMapping
    public ResponseEntity<List<GrupoProdutoDTO>> findAll(){
        return ResponseEntity.ok().body(grupoProdutoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GrupoProdutoDTO> findByID(@PathVariable Integer id){
        GrupoProduto obj = this.grupoProdutoService.findbyId(id);
        return ResponseEntity.ok().body(new GrupoProdutoDTO(obj));
    }

    @PostMapping
    public ResponseEntity<GrupoProdutoDTO> create(@RequestBody GrupoProdutoDTO dto){
        GrupoProduto grupoProduto = grupoProdutoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(grupoProduto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}

