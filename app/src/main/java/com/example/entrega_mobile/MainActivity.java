package com.example.entrega_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity responsável pela tela de login do aplicativo.
 * Desenvolvida para validar a entrada do usuário antes de acessar a tela principal.
 */
public class MainActivity extends AppCompatActivity {

    // Declaração dos componentes da interface para manipulação no código
    private EditText editTextUser;
    private EditText editTextPassWord;
    private Button botaoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout XML que será inflado nesta Activity
        setContentView(R.layout.activity_main);

        // Inicialização das variáveis através da busca por ID no layout
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassWord = findViewById(R.id.editTextPassWord);
        botaoLogin = findViewById(R.id.botaoLogin);

        // Configuração do Listener para o evento de clique no botão de acesso
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Captura dos valores digitados e remoção de espaços desnecessários
                String usuario = editTextUser.getText().toString().trim();
                String senha = editTextPassWord.getText().toString().trim();

                // Verificação de segurança para garantir que os campos obrigatórios foram preenchidos
                if (usuario.isEmpty()) {
                    editTextUser.setError("O usuário é obrigatório");
                    Toast.makeText(MainActivity.this, "Por favor, preencha o campo de usuário", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (senha.isEmpty()) {
                    editTextPassWord.setError("A senha é obrigatória");
                    Toast.makeText(MainActivity.this, "Por favor, preencha o campo de senha", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                // Criação da Intent para transição de tela, enviando o nome do usuário como parâmetro
                Intent intent = new Intent(MainActivity.this, TelaPrincipalActivity.class);
                intent.putExtra("NOME_USUARIO", usuario);
                startActivity(intent);
                // Finalização da Activity atual para evitar retorno à tela de login via botão voltar
                finish();
            }
        });
    }
}