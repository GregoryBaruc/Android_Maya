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

public class AgendamentoActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private CalendarView calendarView;
    private String dataSelecionada;
    private String horarioSelecionado;
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendamento);

        nomeUsuario = getIntent().getStringExtra("NOME_USUARIO");
        
        calendarView = findViewById(R.id.CalendarioA1);
        bottomNavigationView = findViewById(R.id.bottom_navigation2);

        // Bloqueia datas passadas
        if (calendarView != null) {
            calendarView.setMinDate(System.currentTimeMillis() - 1000);
            
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    dataSelecionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                }
            });
        }

        // Inicializa com a data de hoje
        Calendar cal = Calendar.getInstance();
        dataSelecionada = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);

        // Configura os botões de horário
        setupHorarioButton(R.id.BotaoA1, "10:00");
        setupHorarioButton(R.id.BotaoA2, "09:00");
        setupHorarioButton(R.id.BotaoA3, "09:30");
        setupHorarioButton(R.id.BotaoA4, "10:30");
        setupHorarioButton(R.id.BotaoA5, "11:00");
        setupHorarioButton(R.id.BotaoA6, "11:30");

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home2) {
                    voltarParaHome();
                    return true;
                }
                return false;
            }
        });
    }

    private void setupHorarioButton(int id, final String horario) {
        Button btn = findViewById(id);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    horarioSelecionado = horario;
                    String mensagem = "Consulta confirmada para o dia " + dataSelecionada + " às " + horario;
                    Toast.makeText(AgendamentoActivity.this, mensagem, Toast.LENGTH_LONG).show();
                    
                    voltarParaHome();
                }
            });
        }
    }

    private void voltarParaHome() {
        Intent intent = new Intent(AgendamentoActivity.this, TelaPrincipalActivity.class);
        intent.putExtra("NOME_USUARIO", nomeUsuario);
        if (horarioSelecionado != null) {
            intent.putExtra("DATA_AGENDAMENTO", dataSelecionada + " às " + horarioSelecionado);
        }
        startActivity(intent);
        finish();
    }
}