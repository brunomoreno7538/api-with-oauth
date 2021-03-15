package com.h2.caralho.h2caralho.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.h2.caralho.h2caralho.domain.enums.Perfil;
import com.h2.caralho.h2caralho.domain.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Integer id;
    @Getter @Setter private String nome;
    @Getter @Setter private String email;
    @Getter @Setter private String cpfCnpj;
    @JsonIgnore
    @Getter @Setter private String senha;
    private Integer tipo;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    @Getter private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "telefone")
    @Getter @Setter private Set<String> telefones = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
     private Set<Integer> perfis = new HashSet<>();


    @JsonBackReference
    @OneToMany(mappedBy = "cliente")
    @Getter @Setter private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(){
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String cpfCnpj, TipoCliente tipo, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();
        this.senha = senha;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public Set<Perfil> getPerfis(){
        System.out.println(perfis);
        return perfis.stream().map(p -> Perfil.toEnum(p)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCod());
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }
}
