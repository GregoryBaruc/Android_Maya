package com.example.entrega_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

/**
 * Activity responsável pelo agendamento de sessões.
 * Permite ao usuário selecionar uma data no calendário e um horário disponível.
 */
public class AgendamentoActivity extends AppCompatActivity {

    // Declaração dos componentes e variáveis globais da classe
    private BottomNavigationView bottomNavigationView;
    private CalendarView calendarView;
    private String dataSelecionada;
    private String horarioSelecionado;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento);

        // Recuperação do nome do usuário para manter a consistência entre telas
        nomeUsuario = getIntent().getStringExtra("NOME_USUARIO");
        
        // Inicialização dos componentes da interface
        calendarView = findViewById(R.id.CalendarioA1);
        bottomNavigationView = findViewById(R.id.bottom_navigation2);

        // Regra de negócio: impede o agendamento em datas que já passaram
        if (calendarView != null) {
            calendarView.setMinDate(System.currentTimeMillis() - 1000);
            
            // Listener para capturar a data selecionada pelo usuário
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    dataSelecionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                }
            });
        }

        // Configuração da data padrão como sendo o dia atual
        Calendar cal = Calendar.getInstance();
        dataSelecionada = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);

        // Mapeamento dos botões de horário e atribuição de seus respectivos valores
        setupHorarioButton(R.id.BotaoA1, "10:00");
        setupHorarioButton(R.id.BotaoA2, "09:00");
        setupHorarioButton(R.id.BotaoA3, "09:30");
        setupHorarioButton(R.id.BotaoA4, "10:30");
        setupHorarioButton(R.id.BotaoA5, "11:00");
        setupHorarioButton(R.id.BotaoA6, "11:30");

        // Gerenciamento da navegação via BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home2) {
                    voltarParaHome();
                    return true;
                } else if (itemId == R.id.nav_exercicios2) {
                    // Direciona para a tela de exercícios mantendo o contexto do usuário
                    Intent intent = new Intent(AgendamentoActivity.this, ExerciciosActivity.class);
                    intent.putExtra("NOME_USUARIO", nomeUsuario);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Método auxiliar para configurar o evento de clique nos botões de horário.
     * @param id ID do botão no layout XML.
     * @param horario String que representa o horário escolhido.
     */
    private void setupHorarioButton(int id, final String horario) {
        Button btn = findViewById(id);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    horarioSelecionado = horario;
                    String mensagem = "Consulta confirmada para o dia " + dataSelecionada + " às " + horario;
                    Toast.makeText(AgendamentoActivity.this, mensagem, Toast.LENGTH_LONG).show();
                    
                    // Retorna para a home após a confirmação
                    voltarParaHome();
                }
            });
        }
    }

    /**
     * Realiza a transição de volta para a tela principal, transportando os dados do agendamento.
     */
    private void voltarParaHome() {
        Intent intent = new Intent(AgendamentoActivity.this, TelaPrincipalActivity.class);
        intent.putExtra("NOME_USUARIO", nomeUsuario);
        if (horarioSelecionado != null) {
            // Envia a string formatada com data e hora para exibição no lembrete da home
            intent.putExtra("DATA_AGENDAMENTO", dataSelecionada + " às " + horarioSelecionado);
        }
        startActivity(intent);
        finish();
    }
}