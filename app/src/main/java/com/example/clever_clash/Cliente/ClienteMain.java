package com.example.clever_clash.Cliente;

public class ClienteMain {
    public static void main(String[] args) {
        // Lanzamos 2 clientes
        Cliente c1 = new Cliente("Jugador 1");
        c1.start();

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        Cliente c2 = new Cliente("Jugador 2");
        c2.start();
    }
}
