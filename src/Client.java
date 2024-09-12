import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;
        String message;

        while (true) {
            try (Socket socket = new Socket(hostname, port)) {

                System.out.println("Client connected to server");

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                Scanner messageScanner = new Scanner(System.in);
                message = messageScanner.nextLine();

                writer.println(message);

                String response = reader.readLine();
                System.out.println("Server response: " + response);

            } catch (FileNotFoundException e) {
                System.out.println("Error: The file was not found: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO error: " + e.getMessage());
                e.printStackTrace(); // for debugging purposes

            }
        }
    }
}