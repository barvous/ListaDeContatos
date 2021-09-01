package com.example.listadecontatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadecontatos.modelo.Contato;
import com.example.listadecontatos.modelo.ValidadorUtil;

import com.example.listadecontatos.persistencia.BaseDados;

import org.dizitart.no2.exceptions.UniqueConstraintException;

public class CadastroActivity extends AppCompatActivity {
        EditText edtNome,edtSobrenome,edtEmail,edtTelefone,edtCelular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setTitle("Cadastro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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


    }
    public void salvar(){
        String nome = edtNome.getText().toString();
        String sobrenome = edtSobrenome.getText().toString();
        String email = edtEmail.getText().toString();

        String telefoneStr = edtTelefone.getText().toString();
        String celularStr = edtCelular.getText().toString();
        Integer telefone = (telefoneStr.equals("")) ? null : Integer.parseInt(telefoneStr);
        Integer celular = (celularStr.equals("")) ? null :  Integer.parseInt(celularStr);

        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setSobrenome(sobrenome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        contato.setCelular(celular);

        try {
            if (new ValidadorUtil().contatoValido(contato)) {
                BaseDados.rContato.insert(contato);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Preencha os campos obrigatórios", Toast.LENGTH_LONG)
                        .show();
            }

        }catch (UniqueConstraintException e){
            Toast.makeText(getApplicationContext(), "Este número já existe", Toast.LENGTH_LONG)
                    .show();
        }
    }


}