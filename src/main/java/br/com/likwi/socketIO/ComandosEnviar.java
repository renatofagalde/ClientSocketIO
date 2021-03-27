package br.com.likwi.socketIO;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComandosEnviar implements Runnable {

    private static final String SAIR = "";
    private static final Logger logger = Logger.getLogger(ComandosEnviar.class.toString());
    private Socket socket;


    public ComandosEnviar(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintStream requestEnviarComando = new PrintStream(socket.getOutputStream())) {
            final Scanner comando = new Scanner(System.in);
            while (comando.hasNextLine()) {
                String c = comando.nextLine();
                if (c.trim().equals(SAIR)) {
                    logger.info("Finalizando cliente as VI command");
                    break;
                }
                requestEnviarComando.println(c);
            }
            comando.close();
            //requestEnviarComando.close(); redudant
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, ioException.getMessage());
            throw new RuntimeException(ioException);
        } finally {
            logger.info("ComandoEnviar finalizado");
        }

    }
}
