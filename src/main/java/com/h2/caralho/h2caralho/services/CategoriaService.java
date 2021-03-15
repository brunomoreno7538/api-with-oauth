package com.h2.caralho.h2caralho.services;

import com.h2.caralho.h2caralho.domain.Categoria;
import com.h2.caralho.h2caralho.dto.CategoriaDTO;
import com.h2.caralho.h2caralho.repositories.CategoriaRepository;
import com.h2.caralho.h2caralho.services.exceptions.DataIntegrityException;
import com.h2.caralho.h2caralho.services.exceptions.ObjectNotFoundException;
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
    private CategoriaRepository repo;


    public Categoria buscar(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encotrado! ID: " + id +  ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria inserir(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria atualizar(Categoria obj){
        buscar(obj.getId());
        return repo.save(obj);
    }

    public void deletar(Integer id){
        buscar(id);
        try{
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Categoria com associações!");
        }
    }

    public List<Categoria> buscarTudo(){
        List<Categoria> objs = repo.findAll();
        return objs;
    }

    public Page<Categoria> acharPagina(Integer pagina, Integer linhas, String orderBy, String direcao){
        PageRequest pageRequest = PageRequest.of(pagina, linhas, Sort.Direction.valueOf(direcao), orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria doDTO(CategoriaDTO obj){
        return new Categoria(obj.getId(), obj.getCategoria());
    }

}
