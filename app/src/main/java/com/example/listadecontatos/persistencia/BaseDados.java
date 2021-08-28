package com.example.listadecontatos.persistencia;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import com.example.listadecontatos.modelo.Contato;

import java.util.List;

public class BaseDados {
    public static ObjectRepository<Contato> rContato;

    public static void init(String file) {
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath(file)
                .openOrCreate("root", "123");

        rContato = db.getRepository(Contato.class);
    }


    public List<Contato> findByNome(String nome){
        return rContato.find(ObjectFilters.regex("nome", nome)).toList();
    }

    public Contato findByCelular(String celular){
        return rContato.find(ObjectFilters.eq("celular", celular)).firstOrDefault();
    }
}