package com.example.listadecontatos;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;

import com.example.listadecontatos.modelo.Contato;
import com.example.listadecontatos.persistencia.BaseDados;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.dizitart.no2.FindOptions;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.util.List;
import java.util.function.Predicate;

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

    public void atualizarLista(){
        try {
            listContato = BaseDados.rContato.find().toList();
            lv.setAdapter(new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, listContato));
        }catch(Exception e){

        }

    }

    public void pesquisarContato(){
            String procuraNome = edtPesquisar.getText().toString();
            String procuraSobrenome = procuraNome;
            listContato= BaseDados.rContato.find(ObjectFilters.regex("nome", procuraNome)).toList();
            lv.setAdapter(new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, listContato));
    }
}