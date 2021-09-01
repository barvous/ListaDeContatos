package com.example.listadecontatos.modelo;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.io.Serializable;

@Indices(value = {
        //impede que sejam criadas duas montadoras com o mesmo nome
        @Index(value = "celular", type = IndexType.Unique)
})
public class Contato implements Serializable {
    @Id
    public NitriteId id;
    private String nome;
    private String sobrenome;
    private String email;
    private Integer telefone;
    private Integer celular;


    public Contato() {
    }

    public Contato(String nome, String sobrenome, String email, Integer telefone, Integer celular) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
    }

    public Contato(String nome, Integer celular) {
        this.nome = nome;
        this.celular = celular;
    }

    public NitriteId getId() {
        return id;
    }

    public void setId(NitriteId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
}


