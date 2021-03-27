package br.com.likwi.socketIO;

import java.io.IOException;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComandosReceber implements Runnable {

    private static final Logger logger = Logger.getLogger(ComandosReceber.class.toString());
    private Socket socket;

    public ComandosReceber(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Scanner response = new Scanner(socket.getInputStream())) {
            while (response.hasNextLine()) {
                logger.info(MessageFormat.format("resposta do servidor {0}",response.nextLine()));
            }
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, ioException.getMessage());
            throw new RuntimeException(ioException);
        }
    }
}
