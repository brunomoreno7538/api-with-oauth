package com.h2.caralho.h2caralho.repositories;


import com.h2.caralho.h2caralho.domain.Cliente;
import com.h2.caralho.h2caralho.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {


}
