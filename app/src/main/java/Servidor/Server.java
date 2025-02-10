package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Servidor que gestiona una partida de Clever Clash vía TCP,
 * controlando turnos entre dos jugadores y cambiando el turno cuando uno falla.
 */
public class Server {

    private final int NUM_MAX_JUGADORES = 2; // Solo 2 jugadores
    private ArrayList<Socket> socketsClientes;
    private int jugadorEnTurno;
    private boolean finJuego;
    private String nombresJugadores;

    public void ejecutarServidor() {
        jugadorEnTurno = 0;
        finJuego = false;
        nombresJugadores = "";
        socketsClientes = new ArrayList<>();

        System.out.println("Servidor de Clever Clash iniciado en puerto 5555...");

        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            while (socketsClientes.size() < NUM_MAX_JUGADORES) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("¡Nuevo jugador conectado! " + socketCliente.getInetAddress());

                DataInputStream in = new DataInputStream(socketCliente.getInputStream());
                DataOutputStream out = new DataOutputStream(socketCliente.getOutputStream());

                String nombreJugador = in.readUTF();  // Recibe el nombre del jugador
                if (!nombresJugadores.isEmpty()) {
                    nombresJugadores += ",";
                }
                nombresJugadores += nombreJugador;

                out.writeUTF("aceptado");
                socketsClientes.add(socketCliente);
            }

            for (int i = 0; i < NUM_MAX_JUGADORES; i++) {
                Socket s = socketsClientes.get(i);
                GestionClientes hilo = new GestionClientes(this, s, i);
                hilo.start();
            }

            while (!finJuego) {
                synchronized (this) {
                    wait();
                }
            }

            System.out.println("Fin de la partida. Servidor cerrando.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cambia el turno del jugador cuando uno falla una pregunta.
     */
    public synchronized void cambiarTurno() {
        jugadorEnTurno = (jugadorEnTurno == 0) ? 1 : 0;
        notifyAll();
    }

    /**
     * Obtiene el ID del jugador que tiene el turno.
     */
    public synchronized int getJugadorEnTurno() {
        return jugadorEnTurno;
    }

    public synchronized boolean isFinJuego() {
        return finJuego;
    }

    public synchronized String getNombresJugadores() {
        return nombresJugadores;
    }
}
