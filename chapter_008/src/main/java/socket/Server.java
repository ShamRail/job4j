package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask;
        do {
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else if (!ask.equals("exit")) {
                out.println("I don't understand.");
                out.println();
            }
        } while (!"exit".equals(ask));
    }

    public static void main(String[] args) throws IOException {
       try (ServerSocket serverSocket = new ServerSocket(4004)) {
           new Server(serverSocket.accept()).start();
       }
    }

}
