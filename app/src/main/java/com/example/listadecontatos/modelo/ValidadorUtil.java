package com.example.listadecontatos.modelo;

import com.example.listadecontatos.persistencia.BaseDados;

import org.dizitart.no2.objects.filters.ObjectFilters;

public class ValidadorUtil {


    public boolean contatoValido(Contato contato) {
        return contato.getNome() != null && contato.getCelular() != null
                && !contato.getNome().equals("") && !contato.getCelular().equals("");
    }
    public boolean contatoExistente(Contato contato){
         Contato contatoExistente = BaseDados.rContato.find(ObjectFilters.eq("celular",contato.getCelular())).firstOrDefault();
        if(contatoExistente!=null){
            if(contatoExistente.getId().equals(contato.getId())){
                return false;
            }
            return true;
        }
        return false;
    }
}
