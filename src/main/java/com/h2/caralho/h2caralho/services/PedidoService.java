package com.h2.caralho.h2caralho.services;

import com.h2.caralho.h2caralho.domain.Categoria;
import com.h2.caralho.h2caralho.domain.Pedido;
import com.h2.caralho.h2caralho.repositories.CategoriaRepository;
import com.h2.caralho.h2caralho.repositories.PedidoRepository;
import com.h2.caralho.h2caralho.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;


    public Pedido buscar(Integer id){
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encotrado! ID: " + id +  ", Tipo: " + Pedido.class.getName()));
    }

}
