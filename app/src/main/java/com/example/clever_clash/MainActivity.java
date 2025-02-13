package com.example.clever_clash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Toolbar toolbar;
    private EditText userNameEditText;
    private Switch switchLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargar el idioma guardado antes de aplicar setContentView()
        loadLanguage();




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
        String userName = (user != null) ? user.getDisplayName() : "Usuario";
        toolbar.setTitle(userName);

        // Botones
        Button newGameButton = findViewById(R.id.newGameButton);
        Button dataButton = findViewById(R.id.dataButton);
        Button btnIrARuleta = findViewById(R.id.btnRuleta);

        // ACTUALIZA LOS TEXTOS PARA REFLEJAR EL NUEVO IDIOMA
        newGameButton.setText(getString(R.string.new_game));
        dataButton.setText(getString(R.string.future_options));
        btnIrARuleta.setText("Ir a la Ruleta"); // Si tienes traducción, usa getString()

        newGameButton.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Nueva partida iniciada", Toast.LENGTH_SHORT).show()
        );

        dataButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VisorPreguntasActivity.class);
            startActivity(intent);
        });


        btnIrARuleta.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RuletaActivity.class);
            startActivity(intent);
        });

        // Switch para cambio de idioma
        switchLanguage = findViewById(R.id.switchLanguage);

        // Cargar estado guardado del switch
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("Language", "es"); // Default: Español
        switchLanguage.setChecked(language.equals("en"));
        switchLanguage.setText(language.equals("en") ? getString(R.string.language_english) : getString(R.string.language_spanish));

        switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchLanguage.setText("English");
                changeLanguage("en");
            } else {
                switchLanguage.setText("Español");
                changeLanguage("es");
            }
        });
    }

    private void changeLanguage(String languageCode) {
        // Guardar idioma en SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("Language", languageCode);
        editor.apply();

        // Aplicar idioma
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // REINICIAR ACTIVIDAD COMPLETAMENTE
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



    private void loadLanguage() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("Language", "es"); // Español por defecto

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_user_name) {
            showEditUserNameDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showEditUserNameDialog() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            userNameEditText = new EditText(MainActivity.this);
            userNameEditText.setText(user.getDisplayName());

            new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle("Editar Nombre de Usuario")
                    .setView(userNameEditText)
                    .setPositiveButton("Guardar", (dialog, which) -> {
                        String newUserName = userNameEditText.getText().toString().trim();
                        if (!newUserName.isEmpty()) {
                            user.updateProfile(new UserProfileChangeRequest.Builder()
                                    .setDisplayName(newUserName)
                                    .build()).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    toolbar.setTitle(newUserName);
                                    Toast.makeText(MainActivity.this, "Nombre de usuario actualizado", Toast.LENGTH_SHORT).show();
                                } else {
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
