package package01;

import java.io.*;
import java.net.*;


public class SimpleTextClient {
    private Socket connection;
    private PrintWriter out;
    private BufferedReader in;

    public void initialize(String ipadd, int port) {
        try {
            connection = new Socket(ipadd, port);
            connection.setKeepAlive(true);
            out = new PrintWriter(connection.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            in.close();
            out.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String send(String msg) {
        out.println(msg);
        String response = "";
        try {
            response = in.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }

}
