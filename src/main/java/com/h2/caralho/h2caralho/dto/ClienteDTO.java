package com.h2.caralho.h2caralho.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h2.caralho.h2caralho.domain.Cliente;
import com.h2.caralho.h2caralho.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min = 5, max = 80, message = "Forneça um nome válido!")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email
    private String email;

    public ClienteDTO(Cliente obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }

}