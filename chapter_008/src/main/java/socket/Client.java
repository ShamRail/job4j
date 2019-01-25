package socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String consoleAsk;
        do {
            consoleAsk = console.nextLine();
            out.println(consoleAsk);
            printServerAnswer(consoleAsk, in);
        } while (!"exit".equals(consoleAsk));
    }

    private void printServerAnswer(String consoleInput, BufferedReader in) throws IOException {
        if (!"exit".equals(consoleInput)) {
            String str = in.readLine();
            while (str != null && (!str.isEmpty())) {
                System.out.println(str);
                str = in.readLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4004);
        new Client(socket).start();
    }

}
