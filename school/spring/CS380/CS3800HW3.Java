/*
 Name: Shayna Stewart
 CS 3800 HW3
 Description: Connects to server using address and given port. Initiates a conversation to retrieve
    version information. Then closes connection.
 */
package cs3800.hw3;

import java.net.*;
import java.io.*;

public class CS3800HW3 {

    Socket socket;

    CS3800HW3(int port,String server) throws IOException {

        try {

            socket = new Socket(server, port);

        } catch (Exception e) {

            System.out.println("Error connectiong to server:" + e);

            return;

        }

        System.out.print("Connecting to "
                + socket.getInetAddress() + ":"
                + socket.getPort() + "...");

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input)); //read from server
        String line = reader.readLine();    // reads a line of text
        System.out.println(line);  

        PrintWriter writer = new PrintWriter(output, true); //write to server
        writer.println("version\n");
        
        line = reader.readLine();    // reads a line of text 
        System.out.println("Version info: {" + line+"}");  

        try {

            socket.close(); //close connection
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public static void main(String[] arg) throws IOException {
        int port = 3800;
        String server = "64.183.98.170";
        new CS3800HW3(port,server);

    }

}
