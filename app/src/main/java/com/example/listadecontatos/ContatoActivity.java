package com.example.listadecontatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import com.example.listadecontatos.modelo.Contato;
//import com.example.listadecontatos.persistencia.BaseDados;
import com.example.listadecontatos.CadastroActivity;


public class ContatoActivity extends AppCompatActivity {
    //componentes visuais
    EditText nome;
    EditText sobrenome;
    EditText email;
    EditText telefone;
    EditText celular;
    TextView nomeCompleto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        Contato contato = (Contato) getIntent().getSerializableExtra("contato");

        nomeCompleto = findViewById(R.id.tvNomeContato);

        nome = findViewById(R.id.edtNomeContato);
        sobrenome = findViewById(R.id.edtSobrenomeContato);
        email = findViewById(R.id.edtEmailContato);
        telefone = findViewById(R.id.edtTelefoneContato);
        celular = findViewById(R.id.edtCelularContato);


        nomeCompleto.setText(contato.getNome()+ " " +contato.getSobrenome());
        nome.setText(contato.getNome());
        sobrenome.setText(contato.getSobrenome());
        email.setText(contato.getEmail());
        telefone.setText(contato.getTelefone());
        celular.setText(contato.getCelular());
    }
}