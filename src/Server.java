import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int port = 5000;
    private static final int numbersOfThreads = 10;
    public static void main(String[] args) throws IOException {

        ExecutorService threadpool = Executors.newFixedThreadPool(numbersOfThreads);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("new client connected");

                threadpool.submit(new ServerClientHandler(socket));
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}


