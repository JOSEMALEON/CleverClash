package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hilo que gestiona la interacción con un jugador en Clever Clash,
 * controlando turnos y respuestas a preguntas.
 */
public class GestionClientes extends Thread {

    private Server servidor;
    private Socket socket;
    private int idJugador;

    public GestionClientes(Server servidor, Socket socket, int idJugador) {
        this.servidor = servidor;
        this.socket = socket;
        this.idJugador = idJugador;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            // Enviar nombres de los jugadores al cliente
            String nombres = servidor.getNombresJugadores();
            out.writeUTF(nombres);
            out.flush();

            while (!servidor.isFinJuego()) {
                synchronized (servidor) {
                    while (servidor.getJugadorEnTurno() != idJugador) {
                        servidor.wait(); // Esperar su turno
                    }
                }

                // Enviar turno al jugador
                out.writeUTF("Es tu turno");
                out.flush();

                // Esperar respuesta del jugador
                boolean respuestaCorrecta = in.readBoolean();

                if (!respuestaCorrecta) {
                    servidor.cambiarTurno(); // Cambia de turno si la respuesta es incorrecta
                }
            }
        } catch (Exception e) {
            Logger.getLogger(GestionClientes.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
