package package01;

import java.io.*;
import java.net.*;

public class SimpleTextServer {

    private static final int PORT = 60000;

    private ServerSocket server;
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) {
        SimpleTextServer srv = new SimpleTextServer();
        srv.initialize(PORT);
    }

    public void initialize(int port) {
        try {
            server = new ServerSocket(port);
            client = server.accept();
            System.out.println("Client connected.");
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String text = "";

            while (true) {
                text = in.readLine();

                if (text == null || text.equals("bye")) {
                    out.println("bye");
                    break;
                }

                System.out.println("Message from client: " + text);

                out.println("Message received: " + text);
            }

            in.close();
            out.close();
            client.close();
            server.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

