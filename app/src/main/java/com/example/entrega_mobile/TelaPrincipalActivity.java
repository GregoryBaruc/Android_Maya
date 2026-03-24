package com.example.entrega_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TelaPrincipalActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    // Início - fica na mesma tela
                    setTitle("Início");
                    return true;
                } else if (itemId == R.id.nav_agendamento) {
                    // Abre tela de agendamento
                    startActivity(new Intent(TelaPrincipalActivity.this, AgendamentoActivity.class));
                    return true;
                } else if (itemId == R.id.nav_exercicios) {
                    // Abre tela de exercícios (se existir)
                    // startActivity(new Intent(TelaPrincipalActivity.this, ExerciciosActivity.class));
                    return true;
                }
                return false;
            }
        });

        setTitle("Início");
    }
}