package com.example.entrega_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Activity que representa a tela principal (Dashboard) do sistema.
 * Apresenta as informações de boas-vindas e o feedback da Maya.
 */
public class TelaPrincipalActivity extends AppCompatActivity {

    // Componentes de interface declarados para manipulação de dados dinâmicos
    private BottomNavigationView bottomNavigationView;
    private TextView txtNomeUsuario, lembrete, feedbackMaya;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Vinculação do layout XML correspondente
        setContentView(R.layout.tela_principal);

        // Referenciando os componentes do XML pelos IDs definidos
        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);
        lembrete = findViewById(R.id.lembrete);
        feedbackMaya = findViewById(R.id.Feedback_Maya);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Recuperação dos dados enviados pela MainActivity via Intent
        nomeUsuario = getIntent().getStringExtra("NOME_USUARIO");
        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
            // Personalização da mensagem de boas-vindas
            txtNomeUsuario.setText("Seja bem vindo, " + nomeUsuario);
            
            // Atualização do texto de feedback utilizando o nome capturado no login
            String mensagemFeedback = "Olá, " + nomeUsuario + "! Passando para saber como você se sentiu após a nossa sessão de RPG de hoje. Lembre-se de manter a atenção na sua postura e na respiração profunda que praticamos. Se sentir algum desconforto muscular leve, é normal devido ao trabalho de alongamento. Nos vemos na próxima sessão! 🧘‍♀️✨";
            feedbackMaya.setText(mensagemFeedback);
        } else {
            txtNomeUsuario.setText("Seja bem vindo");
        }

        // Verificação e exibição de agendamentos realizados
        String dataAgendamento = getIntent().getStringExtra("DATA_AGENDAMENTO");
        if (dataAgendamento != null && !dataAgendamento.isEmpty()) {
            lembrete.setText(dataAgendamento);
        }

        // Implementação da lógica de navegação do menu inferior
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    // Já está na tela inicial, nenhuma ação necessária
                    return true;
                } else if (itemId == R.id.nav_agendamento) {
                    // Navega para a tela de agendamento passando o contexto do usuário
                    Intent intent = new Intent(TelaPrincipalActivity.this, AgendamentoActivity.class);
                    intent.putExtra("NOME_USUARIO", nomeUsuario);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_exercicios) {
                    // Navega para a lista de exercícios
                    Intent intent = new Intent(TelaPrincipalActivity.this, ExerciciosActivity.class);
                    intent.putExtra("NOME_USUARIO", nomeUsuario);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        // Configuração do título da Toolbar (opcional, dependendo do tema)
        setTitle("Seja Bem Vindo");
    }
}