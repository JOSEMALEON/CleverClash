package com.example.clever_clash;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Toolbar toolbar;
    private EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configura el diseño principal
        setContentView(R.layout.activity_main);

        // Inicializa Firebase
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

        // Configurar el Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtener el nombre de usuario
        FirebaseUser user = auth.getCurrentUser();
        String userName = user != null ? user.getDisplayName() : "Usuario";

        // Establecer el nombre del usuario en el Toolbar
        toolbar.setTitle(userName);

        // Obtén referencias a los botones
        Button newGameButton = findViewById(R.id.newGameButton);
        Button dataButton = findViewById(R.id.dataButton);

        // Configura el botón "Nueva Partida"
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para iniciar una nueva partida
                Toast.makeText(MainActivity.this, "Nueva partida iniciada", Toast.LENGTH_SHORT).show();
            }
        });

        // Configura el botón "Datos"
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para manejar datos
                Toast.makeText(MainActivity.this, "Función de datos no implementada", Toast.LENGTH_SHORT).show();

            }
        });

        Button btnIrARuleta = findViewById(R.id.btnRuleta);
        btnIrARuleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RuletaActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú en la barra de herramientas
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_user_name) {
            // Llamar al método para mostrar el cuadro de diálogo
            showEditUserNameDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private void showEditUserNameDialog() {
        // Crear un cuadro de texto para que el usuario ingrese el nuevo nombre
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            userNameEditText = new EditText(MainActivity.this);
            userNameEditText.setText(user.getDisplayName()); // Establecer el nombre actual

            // Configurar un cuadro de diálogo para editar el nombre
            new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle("Editar Nombre de Usuario")
                    .setView(userNameEditText)
                    .setPositiveButton("Guardar", (dialog, which) -> {
                        String newUserName = userNameEditText.getText().toString().trim();
                        if (!newUserName.isEmpty()) {
                            // Actualizar el nombre del usuario en Firebase
                            user.updateProfile(new UserProfileChangeRequest.Builder()
                                            .setDisplayName(newUserName)
                                            .build())
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            // Nombre actualizado con éxito
                                            toolbar.setTitle(newUserName); // Actualizar el nombre en la barra
                                            Toast.makeText(MainActivity.this, "Nombre de usuario actualizado", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Error al actualizar el nombre
                                            Toast.makeText(MainActivity.this, "Error al actualizar el nombre", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(MainActivity.this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        }
    }
}
