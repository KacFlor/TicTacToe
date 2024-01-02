package com.example.kolkokrzyzyk;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket server1 = new ServerSocket(port)) {
            while (true) {
                Socket socket = server1.accept();
                handleReceivedMessage(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleReceivedMessage(Socket socket) {
        try (InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()) {
            BufferedReader reader = new BufferedReader(new FileReader("wyniki.txt"));
            String result;
            StringBuilder data = new StringBuilder();

            while ((result = reader.readLine()) != null) {
                data.append(result).append("\n");
            }

            String responseData = data.toString();
            System.out.println(responseData);

            output.write(responseData.getBytes());
            output.flush();

            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





