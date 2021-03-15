package com.h2.caralho.h2caralho.repositories;


import com.h2.caralho.h2caralho.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {


}
