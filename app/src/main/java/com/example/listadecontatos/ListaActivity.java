package com.example.listadecontatos;

import android.content.Intent;
import android.os.Bundle;

import com.example.listadecontatos.modelo.Contato;
import com.example.listadecontatos.persistencia.BaseDados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    List<Contato> listContato;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Contatos");
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BaseDados.init(getFilesDir()+"Base.db");


        FloatingActionButton fab = findViewById(R.id.fabCadastro);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this, CadastroActivity.class);

                startActivity(intent);

            }
        });
        listContato = BaseDados.rContato.find().toList();
        lv = findViewById(R.id.listaContatos);

        lv.setAdapter(new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, listContato));

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Contato contato = listContato.get(position);
            Intent intent = new Intent(ListaActivity.this, ContatoActivity.class);
            intent.putExtra("contato",contato);
            startActivity(intent);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    public void atualizarLista(){
        listContato = BaseDados.rContato.find().toList();
        lv.setAdapter(new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, listContato));
    }
}