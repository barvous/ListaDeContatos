package com.example.listadecontatos.modelo;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.util.Objects;

@Indices(value = {
        //impede que sejam criadas duas montadoras com o mesmo nome
        @Index(value = "nome", type = IndexType.Unique)
})

public class Contato {
    @Id
    public NitriteId id;
    private String nome, sobrenome, email;
    private int telefone, celular;

    public Contato() {
    }

    public Contato(String nome, String sobrenome, String email, int telefone, int celular) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
    }

    public Contato(String nome, int celular) {
        this.nome = nome;
        this.celular = celular;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }
}


