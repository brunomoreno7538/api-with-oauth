package com.h2.caralho.h2caralho.resources;

import com.h2.caralho.h2caralho.domain.Cliente;
import com.h2.caralho.h2caralho.domain.Cliente;
import com.h2.caralho.h2caralho.domain.enums.Perfil;
import com.h2.caralho.h2caralho.dto.ClienteCriacaoDTO;
import com.h2.caralho.h2caralho.dto.ClienteDTO;
import com.h2.caralho.h2caralho.services.ClienteService;
import com.h2.caralho.h2caralho.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> procurar(@PathVariable Integer id){
        Cliente obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserirCliente(@Valid @RequestBody ClienteCriacaoDTO objDTO){
        Cliente objCliente = service.doDTO(objDTO);
        objCliente = service.inserir(objCliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objCliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
        Cliente obj = service.doDTO(objDTO);
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
    public ResponseEntity<List<ClienteDTO>> procurarTudo(){
        List<Cliente> obj = service.buscarTudo();
        List<ClienteDTO> objDto = obj.stream().map(objLista -> new ClienteDTO(objLista)).collect(Collectors.toList());
        // pega todos os obj da lista e instancia um novo dto pra cada um
        return ResponseEntity.ok().body(objDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pagina")
    public ResponseEntity<Page<ClienteDTO>> paginarClientes(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                                @RequestParam(value = "linhas", defaultValue = "25") Integer linhas,
                                                                @RequestParam(value = "orderBy", defaultValue = "Cliente")String orderBy,
                                                                @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Cliente> obj = service.acharPagina(pagina, linhas, orderBy, direcao);
        Page<ClienteDTO> objDto = obj.map(objLista -> new ClienteDTO(objLista));
        return ResponseEntity.ok().body(objDto);
    }
}
