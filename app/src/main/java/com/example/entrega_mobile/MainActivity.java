package com.example.entrega_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUser;
    private Button botaoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        botaoLogin = findViewById(R.id.botaoLogin);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUser.getText().toString();
                
                Intent intent = new Intent(MainActivity.this, TelaPrincipalActivity.class);
                // Passa o nome do usuário para a próxima tela
                intent.putExtra("NOME_USUARIO", usuario);
                startActivity(intent);
                finish();
            }
        });
    }
}