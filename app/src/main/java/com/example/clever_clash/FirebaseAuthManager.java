package com.example.clever_clash;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {

    private FirebaseAuth mAuth;

    public FirebaseAuthManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    // Método para registrar un nuevo usuario
    public Task<AuthResult> createUserWithEmailAndPassword(String email, String password) {
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    // Método para iniciar sesión con correo electrónico y contraseña
    public Task<AuthResult> signInWithEmailAndPassword(String email, String password) {
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    // Método para obtener el usuario actual
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    // Método para cerrar sesión
    public void signOut() {
        mAuth.signOut();
    }

    // ... Puedes agregar más métodos para otras funciones de autenticación
}
