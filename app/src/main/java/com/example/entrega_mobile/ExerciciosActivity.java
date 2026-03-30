package com.example.entrega_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Activity que gerencia a listagem de categorias de exercícios.
 * Implementada para permitir a escolha de diferentes séries de fortalecimento.
 */
public class ExerciciosActivity extends AppCompatActivity {

    // Variável para persistência do nome do usuário entre as transições de tela
    private String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflagem do layout correspondente à listagem de exercícios
        setContentView(R.layout.exercicios);

        // Recuperação do dado enviado via Intent para personalização da experiência
        nomeUsuario = getIntent().getStringExtra("NOME_USUARIO");

        // Configuração do botão de retorno para a Activity anterior
        ImageView btnVoltar = findViewById(R.id.btnVoltar);
        if (btnVoltar != null) {
            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Encerra a Activity atual e retorna à pilha anterior
                    finish();
                }
            });
        }

        /* 
         * Configuração dos botões de ação (Iniciar).
         * Cada botão está vinculado a um array de recursos drawable específicos.
         */
        
        // Categoria 1: Fortalecimento das Pernas
        setupStartButton(R.id.btn1, "Fortalecimento das Pernas", new int[]{
                R.drawable.agachamento,
                R.drawable.agachamento2,
                R.drawable.step
        });
        
        // Categoria 2: Fortalecimento do Quadril
        setupStartButton(R.id.btn2, "Fortalecimento do Quadril", new int[]{
                R.drawable.abducao,
                R.drawable.abducaope,
                R.drawable.elevacao
        });
        
        // Categoria 3: Fortalecimento Articular
        setupStartButton(R.id.btn3, "Fortalecimento Articular", new int[]{
                R.drawable.maos,
                R.drawable.tornozerlos,
                R.drawable.cervical
        });

        // Inicialização e configuração da lógica de navegação inferior
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_exercicios);
        if (bottomNavigationView != null) {
            // Marca o item de exercícios como selecionado por padrão nesta tela
            bottomNavigationView.setSelectedItemId(R.id.nav_exercicios_ex);
            bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();
                    if (itemId == R.id.nav_home_ex) {
                        // Navegação para a Home
                        Intent intent = new Intent(ExerciciosActivity.this, TelaPrincipalActivity.class);
                        intent.putExtra("NOME_USUARIO", nomeUsuario);
                        startActivity(intent);
                        finish();
                        return true;
                    } else if (itemId == R.id.nav_agendamento_ex) {
                        // Navegação para a tela de Agendamento
                        Intent intent = new Intent(ExerciciosActivity.this, AgendamentoActivity.class);
                        intent.putExtra("NOME_USUARIO", nomeUsuario);
                        startActivity(intent);
                        finish();
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    /**
     * Método modularizado para configurar os botões de início de exercício.
     * Facilita a manutenção e evita repetição de código (conceito de DRY).
     * 
     * @param resId ID do botão no layout XML.
     * @param titulo Título que será exibido na tela de carrossel.
     * @param imagens Array de IDs das imagens que compõem o exercício.
     */
    private void setupStartButton(int resId, final String titulo, final int[] imagens) {
        Button btn = findViewById(resId);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Transição para a tela de visualização (Carrossel) enviando os dados necessários
                    Intent intent = new Intent(ExerciciosActivity.this, CarouselExercicioActivity.class);
                    intent.putExtra("TITULO_EXERCICIO", titulo);
                    intent.putExtra("IMAGENS_EXERCICIO", imagens);
                    startActivity(intent);
                }
            });
        }
    }
}