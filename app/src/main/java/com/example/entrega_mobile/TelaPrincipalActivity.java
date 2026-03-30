package com.example.entrega_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TelaPrincipalActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TextView txtNomeUsuario, lembrete;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);
        lembrete = findViewById(R.id.lembrete);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Recupera o nome do usuário
        nomeUsuario = getIntent().getStringExtra("NOME_USUARIO");
        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
            txtNomeUsuario.setText("Seja bem vindo, " + nomeUsuario);
        } else {
            txtNomeUsuario.setText("Seja bem vindo");
        }

        // Recupera a data do agendamento
        String dataAgendamento = getIntent().getStringExtra("DATA_AGENDAMENTO");
        if (dataAgendamento != null && !dataAgendamento.isEmpty()) {
            lembrete.setText(dataAgendamento);
        }

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    return true;
                } else if (itemId == R.id.nav_agendamento) {
                    Intent intent = new Intent(TelaPrincipalActivity.this, AgendamentoActivity.class);
                    intent.putExtra("NOME_USUARIO", nomeUsuario);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_exercicios) {
                    return true;
                }
                return false;
            }
        });

        setTitle("Seja Bem Vindo");
    }
}