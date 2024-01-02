package com.example.kolkokrzyzyk;
import java.io.*;
import java.net.*;
public class Serwer {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket socket = server.accept();
                handleClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()) {
            BufferedReader reader = new BufferedReader(new FileReader("Wyniki4x4.txt"));
            String line;
            StringBuilder data = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
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