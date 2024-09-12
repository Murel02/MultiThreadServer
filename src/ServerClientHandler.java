import java.io.*;
import java.net.Socket;

public class ServerClientHandler implements Runnable {
    private Socket socket;

    public ServerClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)){

            while (true) {
                String text;


                while ((text = reader.readLine()) != null) {
                    System.out.println("Received from client: " + text);
                    writer.println("Echo: " + text);
                }
            }

        }catch (IOException ex){
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
