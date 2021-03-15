package com.h2.caralho.h2caralho.services;

import com.h2.caralho.h2caralho.domain.Cidade;
import com.h2.caralho.h2caralho.domain.Cliente;
import com.h2.caralho.h2caralho.domain.Cliente;
import com.h2.caralho.h2caralho.domain.Endereco;
import com.h2.caralho.h2caralho.domain.enums.TipoCliente;
import com.h2.caralho.h2caralho.dto.ClienteCriacaoDTO;
import com.h2.caralho.h2caralho.dto.ClienteDTO;
import com.h2.caralho.h2caralho.repositories.CidadeRepository;
import com.h2.caralho.h2caralho.repositories.ClienteRepository;
import com.h2.caralho.h2caralho.repositories.ClienteRepository;
import com.h2.caralho.h2caralho.repositories.EnderecoRepository;
import com.h2.caralho.h2caralho.services.exceptions.DataIntegrityException;
import com.h2.caralho.h2caralho.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.h2.caralho.h2caralho.domain.Endereco_.cidade;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PasswordEncoder pe;


    public Cliente buscar(Integer id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encotrado! ID: " + id +  ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente inserir(Cliente obj){
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente atualizar(Cliente obj){
        Cliente objEncontrado = buscar(obj.getId());
        atualizarDados(objEncontrado, obj);
        return repo.save(objEncontrado);
    }

    public void deletar(Integer id){
        buscar(id);
        try{
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Cliente com associações!");
        }
    }

    public List<Cliente> buscarTudo(){
        List<Cliente> objs = repo.findAll();
        return objs;
    }

    public Page<Cliente> acharPagina(Integer pagina, Integer linhas, String orderBy, String direcao){
        PageRequest pageRequest = PageRequest.of(pagina, linhas, Sort.Direction.valueOf(direcao), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente doDTO(ClienteDTO obj){
        return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null, null);
    }

    public Cliente doDTO(ClienteCriacaoDTO obj){
        Cliente cliente = new Cliente(null, obj.getNome(), obj.getEmail(), obj.getCpfCnpj(), TipoCliente.toEnum(obj.getTipo()), pe.encode(obj.getSenha()));
        Cidade cidade = new Cidade(obj.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(obj.getTelefone1());
        return cliente;
    }


    public void atualizarDados(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}
