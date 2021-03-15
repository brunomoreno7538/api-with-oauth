package com.h2.caralho.h2caralho.repositories;


import com.h2.caralho.h2caralho.domain.Estado;
import com.h2.caralho.h2caralho.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {


}
