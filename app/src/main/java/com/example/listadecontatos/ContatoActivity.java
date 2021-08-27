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
import java.util.List;

import com.example.listadecontatos.modelo.Contato;
//import com.example.listadecontatos.persistencia.BaseDados;
import com.example.listadecontatos.CadastroActivity;


public class ContatoActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
    }
}