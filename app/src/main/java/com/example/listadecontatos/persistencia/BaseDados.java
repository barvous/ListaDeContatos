package com.example.listadecontatos.persistencia;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import com.example.listadecontatos.modelo.Contato;

public class BaseDados {
    public static ObjectRepository<Contato> rContato;

    public static void init(String file) {
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath(file)
                .openOrCreate("root", "123");

        rContato = db.getRepository(Contato.class);
    }
}