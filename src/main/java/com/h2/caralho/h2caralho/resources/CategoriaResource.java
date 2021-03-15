package com.h2.caralho.h2caralho.resources;

import com.h2.caralho.h2caralho.domain.Categoria;
import com.h2.caralho.h2caralho.dto.CategoriaDTO;
import com.h2.caralho.h2caralho.services.CategoriaService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> procurar(@PathVariable Integer id){
        Categoria obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserirCategoria(@Valid @RequestBody CategoriaDTO objDTO){
        Categoria objCategoria = service.doDTO(objDTO);
        objCategoria = service.inserir(objCategoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
        Categoria obj = service.doDTO(objDTO);
        obj.setId(id);
        obj = service.atualizar(obj);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> procurarTudo(){
        List<Categoria> obj = service.buscarTudo();
        List<CategoriaDTO> objDto = obj.stream().map(objLista -> new CategoriaDTO(objLista)).collect(Collectors.toList());
        // pega todos os obj da lista e instancia um novo dto pra cada um
        return ResponseEntity.ok().body(objDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pagina")
    public ResponseEntity<Page<CategoriaDTO>> paginarCategorias(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                                @RequestParam(value = "linhas", defaultValue = "25") Integer linhas,
                                                                @RequestParam(value = "orderBy", defaultValue = "categoria")String orderBy,
                                                                @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Categoria> obj = service.acharPagina(pagina, linhas, orderBy, direcao);
        Page<CategoriaDTO> objDto = obj.map(objLista -> new CategoriaDTO(objLista));
        return ResponseEntity.ok().body(objDto);
    }
}
