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
            Socket socket = new Socket("localhost", 5559);
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
                try {
                    String mensaje = in.readUTF(); // Esperar mensaje del servidor

                    if (mensaje.equals("Es tu turno")) {
                        System.out.println("[" + nombre + "] Es mi turno. Respondiendo...");
                        boolean respuestaCorrecta = Math.random() > 0.5; // Simulación de respuesta
                        out.writeBoolean(respuestaCorrecta);
                        out.flush();
                    } else if (mensaje.equals("ACTUALIZAR")) {
                        System.out.println("[" + nombre + "] Recibiendo actualización del juego...");
                        int estado = in.readInt(); // Ejemplo de actualización recibida
                        System.out.println("[" + nombre + "] Nuevo estado: " + estado);
                    } else if (mensaje.equals("FIN DEL JUEGO")) {
                        System.out.println("[" + nombre + "] El juego ha terminado.");
                        fin = true;
                    }
                } catch (Exception e) {
                    System.out.println("[" + nombre + "] Error en la comunicación: " + e.getMessage());
                    break;
                }
            }

            // Cerrar conexión cuando termine el juego
            in.close();
            out.close();
            socket.close();
            System.out.println("[" + nombre + "] Cliente finalizado.");

        } catch (Exception e) {
            System.out.println("Error en cliente " + nombre + ": " + e);
        }
    }
}