package com.h2.caralho.h2caralho.dto;

import com.h2.caralho.h2caralho.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Length(min = 5, max = 80, message = "Tamanho entre 5 e 80 caracteres!")
    private String categoria;

    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        categoria = obj.getCategoria();
    }
}
