package com.example.entrega_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUser, editTextPassword;
    private Button botaoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassWord);
        botaoLogin = findViewById(R.id.botaoLogin);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUser.getText().toString();
                String senha = editTextPassword.getText().toString();

                if (usuario.equals("admin") && senha.equals("123")) {
                    Intent intent = new Intent(MainActivity.this, TelaPrincipalActivity.class);
                    startActivity(intent);
                    finish(); // Fecha a tela de login
                } else {
                    Toast.makeText(MainActivity.this, "Usuario ou senha invalidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}