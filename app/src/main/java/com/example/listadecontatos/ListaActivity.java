package com.example.listadecontatos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.listadecontatos.modelo.Contato;
import com.example.listadecontatos.persistencia.BaseDados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.dizitart.no2.objects.filters.ObjectFilters;
import org.intellij.lang.annotations.RegExp;

import java.util.Collections;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    List<Contato> listContato;
    ListView lv;
    EditText edtPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lista de Contatos");
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = findViewById(R.id.toolbarLista);
        setSupportActionBar(toolbar);

        BaseDados.init(getFilesDir() + "Base.db");

        FloatingActionButton fab = findViewById(R.id.fabCadastro);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this, CadastroActivity.class);
                startActivity(intent);

            }
        });

        lv = findViewById(R.id.listaContatos);


        edtPesquisar = findViewById(R.id.edtPesquisar);
        edtPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pesquisarContato();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    public void atualizarLista() {
        try {
            listContato = BaseDados.rContato.find().toList();

            Collections.sort(listContato, (v1, v2) -> v1.getNome().compareToIgnoreCase(v2.getNome()));

            lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listContato));

            lv.setOnItemClickListener((parent, view, position, id) -> {
                Contato contato = listContato.get(position);
                Intent intent = new Intent(ListaActivity.this, ContatoActivity.class);
                intent.putExtra("contato", contato);
                startActivity(intent);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void pesquisarContato() {

        String procuraNome = "(?i)" + edtPesquisar.getText().toString();

        listContato = BaseDados.rContato.find(ObjectFilters.or(
                ObjectFilters.regex("nome", procuraNome),
                ObjectFilters.regex("sobrenome", procuraNome)
        )).toList();

        Collections.sort(listContato, (v1, v2) -> v1.getNome().compareToIgnoreCase(v2.getNome()));

        lv.setAdapter(new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, listContato));
    }
}