package com.example.listadecontatos;

import android.content.Intent;
import android.os.Bundle;

import com.example.listadecontatos.modelo.Contato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;

public class ListaActivity extends AppCompatActivity {

//Modelo
Contato contato = new Contato();

//Componentes visuais
EditText edtNome, edtSobrenome, edtEmail, edtTelefone, edtCelular;
ListView lvContatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Contatos");
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fabCadastro);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this, CadastroActivity.class);
                startActivity(intent);

            }
        });
        ListView lv = findViewById(R.id.listaContatos);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Arrays.asList("Ola Mundo!","Amanda aquela","Amanda a outra","Lorem Ipsum","ta bom já!!")));

    }
}