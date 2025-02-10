package com.example.clever_clash.Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Cliente que se conecta al servidor de Clever Clash y gestiona turnos de juego.
 */
public class Cliente extends Thread {

    private String nombre;
    private boolean fin;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.fin = false;
    }

    @Override
    public void run() {
        try {
            // Conexión al servidor
            Socket socket = new Socket("10.200.116.249", 5559);
            System.out.println("[" + nombre + "] Conectado al servidor en puerto local: " + socket.getLocalPort());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // 1) Enviamos nuestro nombre
            out.writeUTF(nombre);
            out.flush();

            // 2) Recibimos "aceptado"
            String respuesta = in.readUTF();
            if ("aceptado".equals(respuesta)) {
                System.out.println("[" + nombre + "] El servidor me ha aceptado en la partida.");
            }

            // 3) Recibimos la lista de jugadores
            String listaJugadores = in.readUTF();
            System.out.println("[" + nombre + "] Lista de jugadores: " + listaJugadores);

            // 4) Bucle de juego basado en turnos
            while (!fin) {
                String mensaje = in.readUTF();
                if (mensaje.equals("Es tu turno")) {
                    System.out.println("[" + nombre + "] Es mi turno. Respondiendo...");
                    boolean respuestaCorrecta = Math.random() > 0.5; // Simulación de respuesta
                    out.writeBoolean(respuestaCorrecta);
                    out.flush();
                }
            }

            socket.close();
            System.out.println("[" + nombre + "] Cliente finalizado.");

        } catch (Exception e) {
            System.out.println("Error en cliente " + nombre + ": " + e);
        }
    }
}
