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
import android.widget.Toast;

import java.util.List;

import com.example.listadecontatos.modelo.Contato;
//import com.example.listadecontatos.persistencia.BaseDados;
import com.example.listadecontatos.CadastroActivity;
import com.example.listadecontatos.persistencia.BaseDados;
import com.fasterxml.jackson.databind.ser.Serializers;

import org.dizitart.no2.objects.filters.ObjectFilters;


public class ContatoActivity extends AppCompatActivity {
    //componentes visuais
    EditText nome;
    EditText sobrenome;
    EditText email;
    EditText telefone;
    EditText celular;
    TextView nomeCompleto;
    static Contato c = new Contato();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        Contato contato = (Contato) getIntent().getSerializableExtra("contato");

        nome =  findViewById(R.id.edtNomeContato);
        sobrenome = findViewById(R.id.edtSobrenomeContato);
        email = findViewById(R.id.edtEmailContato);
        telefone = findViewById(R.id.edtTelefoneContato);
        celular = findViewById(R.id.edtCelularContato);
        nomeCompleto = findViewById(R.id.tvNomeContato);

        //Preenchendo objeto statico
        c.setNome(nome.getText().toString());
        c.setSobrenome(sobrenome.getText().toString());
        c.setEmail(email.getText().toString());
        c.setTelefone(telefone.getText().toString());
        c.setCelular(celular.getText().toString());

        getSupportActionBar().setTitle("Contato");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Preenchendo os campos de texto
        nomeCompleto.setText(contato.getNome() + " " + contato.getSobrenome());
        nome.setText(contato.getNome());
        sobrenome.setText(contato.getSobrenome());
        email.setText(contato.getEmail());
        telefone.setText(contato.getTelefone());
        celular.setText(contato.getCelular());
    }


    public void deletarContato(View view) {
        Contato contato;
        //procurando o objeto especifico pelo celular
        //A intenção era usar o c.getCelular porem da erro :(
        contato = BaseDados.rContato.find(ObjectFilters.eq("celular", celular.getText().toString())).firstOrDefault();

        //deletando o contato
        BaseDados.rContato.remove(contato);

        //finalizando a tela
        finish();
    }

    public void salvarEdicao(View view) {
        //Capturando os novos valores do campo de texto
        EditText edtnome = (findViewById(R.id.edtNomeContato));
        EditText edtsobrenome = findViewById(R.id.edtSobrenomeContato);
        EditText edtemail = findViewById(R.id.edtEmailContato);
        EditText edttelefone = findViewById(R.id.edtTelefoneContato);
        EditText edtcelular = findViewById(R.id.edtCelularContato);
        //atribuindo os novos valores a Strings
        String nomeNovo = edtnome.getText().toString();
        String sobrenomeNovo = edtsobrenome.getText().toString();
        String emailNovo = edtemail.getText().toString();
        String telefoneNovo = edttelefone.getText().toString();
        String celularNovo = edtcelular.getText().toString();

        //atribuindo os valores antigos a Strings
        String nomeAntigo = c.getNome();
        String sobrenomeAntigo = c.getSobrenome();
        String emailAntigo = c.getEmail();
        String telefoneAntigo = c.getTelefone();
        String celularAntigo = c.getCelular();

        //Verificando se o usuario mudou algum campo de texto
        if (nomeNovo.equals(nomeAntigo) && sobrenomeNovo.equals(sobrenomeAntigo) && emailNovo.equals(emailAntigo) &&
            telefoneNovo.equals(telefoneAntigo) && celularNovo.equals(celularAntigo)){
            Toast.makeText(getApplicationContext(), "Altere alguma informação para fazer uma mudança.",
                    Toast.LENGTH_LONG);
        }else{
            //criando e preenchendo um novo objeto com os novos valores
            Contato contato = new Contato();
            contato.setNome(nomeNovo);
            contato.setSobrenome(sobrenomeNovo);
            contato.setEmail(emailNovo);
            contato.setTelefone(telefoneNovo);
            contato.setCelular(celularNovo);

            try {
                //atualizando no bando de dados
                BaseDados.rContato.update(ObjectFilters.eq("celular",celular.getText().toString()),contato);
            }catch(Exception e){

            }
            //finalizando a tela
            finish();
        }
    }
}