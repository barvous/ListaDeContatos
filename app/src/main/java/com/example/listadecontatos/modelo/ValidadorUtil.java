package com.example.listadecontatos.modelo;

public class ValidadorUtil {


    public boolean contatoValido(Contato contato) {
        return contato.getNome() != null && contato.getCelular() != null
                && !contato.getNome().equals("") && !contato.getCelular().equals("");
    }
}
