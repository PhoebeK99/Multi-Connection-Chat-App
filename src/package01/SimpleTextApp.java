package package01;

import java.util.Scanner;

public class SimpleTextApp {

    private static final int PORT = 60000;

    /**
     * @param args
     */
    public static void main(String[] args) {
        SimpleTextClient client = new SimpleTextClient();
        client.initialize("127.0.0.1", PORT);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Simple Text Network App");
        String msg = "";
        String response = "";

        while (!response.equals("bye")) {
            System.out.println("Enter Message for server: ");
            msg = sc.nextLine();
            response = client.send(msg);
            System.out.println(response);
        }

        client.shutdown();

    }

}
