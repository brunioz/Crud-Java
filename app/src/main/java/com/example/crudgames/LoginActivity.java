package com.example.crudgames;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText senha;
    Button btnLogin;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.user);
        senha = (EditText) findViewById(R.id.senha);
        btnLogin = (Button) findViewById(R.id.btn);
        btnLogin.setOnClickListener((View) -> {
            if (user.getText().toString().equals("admin") && senha.getText().toString().equals("123")) {
                startActivity(new Intent(this, ListarJogadoresActivity.class));
            } else {
                Toast.makeText(this, "Usuario ou senha inválidos", Toast.LENGTH_SHORT).show();
            }

        });
    }
}