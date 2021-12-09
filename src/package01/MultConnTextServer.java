package package01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class MultConnTextServer {

    private static final int PORT = 60000;

    private ServerSocket server;

    /**
     * @param args
     */
    public static void main(String[] args) {
        MultConnTextServer srv = new MultConnTextServer();
        srv.initialize(PORT);
    }

    public static void makeServerPrint(SingleConnTextServer server, String text){
        server.print(text);
    }

    public void initialize(int port) {
        try {
            server = new ServerSocket(port);

            while(true) {
                new SingleConnTextServer(server.accept()).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private class SingleConnTextServer extends Thread {
        private Socket client;
        private PrintWriter out;
        private BufferedReader in;

        public SingleConnTextServer(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
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

                    out.print("Message received: ");
                    makeServerPrint(this, text);
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
        public void print(String output){
            out.println(output);
        }
    }

}
