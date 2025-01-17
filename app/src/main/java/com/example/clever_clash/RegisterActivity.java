package com.example.clever_clash;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;
    private FirebaseAuthManager firebaseAuthManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {
            // Inicializa los componentes de la vista
            usernameEditText = findViewById(R.id.usernameEditText);
            emailEditText = findViewById(R.id.emailEditText);
            passwordEditText = findViewById(R.id.passwordEditText);
            confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
            registerButton = findViewById(R.id.registerButton);

            firebaseAuthManager = new FirebaseAuthManager();

            // Configura el botón de registro
            registerButton.setOnClickListener(view -> {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuthManager.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                finish(); // Cierra la actividad actual
                            } else {
                                Log.e("RegisterActivity", "Error: " + task.getException().getMessage());
                                Toast.makeText(this, "Error al registrar: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            });

        } catch (Exception e) {
            Log.e("RegisterActivity", "Error en onCreate: ", e);
            Toast.makeText(this, "Error al iniciar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
