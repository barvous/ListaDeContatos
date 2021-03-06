package com.example.listadecontatos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listadecontatos.modelo.Contato;
import com.example.listadecontatos.modelo.ValidadorUtil;
import com.example.listadecontatos.persistencia.BaseDados;


public class ContatoActivity extends AppCompatActivity {
    //componentes visuais
    EditText edtNome;
    EditText edtSobrenome;
    EditText edtEmail;
    EditText edtTelefone;
    EditText edtCelular;
    TextView txtNomeCompleto;

    Button btnDeletar;
    Button btnSalvar;

    Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        this.contato = (Contato) getIntent().getSerializableExtra("contato");

        inicializarViews();

        getSupportActionBar().setTitle("Contato");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Botão de deletar
        btnDeletar = findViewById(R.id.btnExcluir);
        btnDeletar.setOnClickListener(v -> {
            deletarContato();
        });

        //Botão de salvar a edição
        btnSalvar = findViewById(R.id.btnSalvarEdicao);

        btnSalvar.setOnClickListener(view -> {
            salvarEdicao();
        });

    }

    public void deletarContato() {

        AlertDialog dialog = new AlertDialog.Builder(ContatoActivity.this)
                .setTitle("Aviso")
                .setMessage("Tem certeza que deseja excluir?")
                .setPositiveButton("SIM", (dialogInterface, i) -> {
                    BaseDados.rContato.remove(contato);
                    dialogInterface.dismiss();
                    finish();
                }).setNegativeButton("CANCELAR", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }).show();


    }


    public void salvarEdicao() {
        //atribuindo os novos valores a Strings
        String nomeNovo = edtNome.getText().toString();
        String sobrenomeNovo = edtSobrenome.getText().toString();
        String emailNovo = edtEmail.getText().toString();

        String telefoneStr = edtTelefone.getText().toString();
        String celularStr = edtCelular.getText().toString();
        Integer telefoneNovo = (telefoneStr.equals("")) ? null : Integer.parseInt(telefoneStr);
        Integer celularNovo = (celularStr.equals("")) ? null : Integer.parseInt(celularStr);

        //criando e preenchendo um novo objeto com os novos valores
        contato.setNome(nomeNovo);
        contato.setSobrenome(sobrenomeNovo);
        contato.setEmail(emailNovo);
        contato.setTelefone(telefoneNovo);
        contato.setCelular(celularNovo);

        ValidadorUtil validador = new ValidadorUtil();

        if (validador.contatoValido(contato)) {
            if (validador.contatoExistente(contato)){
                Toast.makeText(getApplicationContext(), "Já existe um contato com esse número.", Toast.LENGTH_LONG)
                        .show();
                return;
            }
            BaseDados.rContato.update(contato);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Preencha os campos obrigatórios", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void inicializarViews() {
        txtNomeCompleto = findViewById(R.id.tvNomeContato);
        edtNome = findViewById(R.id.edtNomeContato);
        edtSobrenome = findViewById(R.id.edtSobrenomeContato);
        edtEmail = findViewById(R.id.edtEmailContato);
        edtTelefone = findViewById(R.id.edtTelefoneContato);
        edtCelular = findViewById(R.id.edtCelularContato);

        //Preenchendo os campos de texto
        txtNomeCompleto.setText(String.format("%s %s", contato.getNome(), contato.getSobrenome()));
        edtNome.setText(contato.getNome());
        edtSobrenome.setText(contato.getSobrenome());
        edtEmail.setText(contato.getEmail());
        edtTelefone.setText(contato.getTelefone().toString());
        edtCelular.setText(contato.getCelular().toString());
    }
}