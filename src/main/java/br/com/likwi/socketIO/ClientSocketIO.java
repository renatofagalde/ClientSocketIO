package br.com.likwi.socketIO;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class ClientSocketIO {

    //export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home"
    private static final Logger logger = Logger.getLogger(ClientSocketIO.class.toString());

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 3300);
        logger.info("conexao inicializada");
        //management my threads clients
        ExecutorService executor = Executors.newCachedThreadPool();

        //runnables
        final ComandosEnviar comandosEnviar = new ComandosEnviar(socket);
        final ComandosReceber comandosReceber = new ComandosReceber(socket);

        //start threads
        final Future<?> comandosEnviarFuture = executor.submit(comandosEnviar);
        executor.submit(comandosReceber);

        //this I need to wait
        comandosEnviarFuture.get();

        socket.close();
        logger.info("** conexao finalizada **");

    }
}
