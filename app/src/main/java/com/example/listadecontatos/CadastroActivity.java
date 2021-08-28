package com.example.listadecontatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadecontatos.modelo.Contato;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.example.listadecontatos.persistencia.BaseDados;

public class CadastroActivity extends AppCompatActivity {

        EditText edtNome,edtSobrenome,edtEmail,edtTelefone,edtCelular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        Button btnSalvar = findViewById(R.id.btnSalvar);
        edtNome = findViewById(R.id.edtNome);
        edtSobrenome = findViewById(R.id.edtSobrenome);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtCelular = findViewById(R.id.edtCelular);

        btnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabVoltar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void salvar(){
        String nome = edtNome.getText().toString();
        String sobrenome = edtSobrenome.getText().toString();
        String email = edtEmail.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String celular = edtCelular.getText().toString();

        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setSobrenome(sobrenome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        contato.setCelular(celular);

                try {

                    BaseDados.rContato.insert(contato);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
                }

        finish();
    }
}