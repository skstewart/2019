/*
 Name: Shayna Stewart
 CS 3800 HW3
 Description: Connects to server using address and given port. Initiates a conversation to retrieve
    version information. Then closes connection.
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class CS380_Project5 {

    Socket socket;

    CS380_Project5(int port, String server) throws IOException {

        Scanner in = new Scanner(System.in);
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
        line = reader.readLine();    // reads a line of text 
        System.out.println("Server: {" + line + "}");  //reading line of text: x / o?

        PrintWriter writer = new PrintWriter(output, true); //write to server
        writer.println("X\n"); // sending char selection

        line = reader.readLine();    // reads a line of text 
        System.out.println("Server: {" + line + "}");  //reading line of text: charboard

        line = reader.readLine();    // reads a line of text 
        String playerChoice = "";

        while (true) { //while game has not ended

            System.out.println("Server: {" + line + "}");  // asking for player move

            playerChoice = in.next() + "\n"; //reading in player's move
            writer = new PrintWriter(output, true); //write to server the player's move
            writer.println(playerChoice); // send player's move 

            line = reader.readLine();    // reads a line of text 

            if (line.contains("Wins")==true || line.contains("Tie")==true) {
                break;
            }
            
                line = reader.readLine();    // reads a line of text 
                System.out.println("Server: {" + line + "}");  //print: server move
                
                line = reader.readLine();    // reads a line of text 
                System.out.println("Server: {" + line + "}");  //print: charboard
                
                
                line = reader.readLine();    // reads a line of text 
            

        }

        System.out.println("Server: {" + line + "}");  // reading charboard OR declaring winner/tie

        line = reader.readLine();    // reads a line of text 
        System.out.println("Server: {" + line + "}");  //disconnecting

        try {

            socket.close(); //close connection
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public static void main(String[] arg) throws IOException {
        int port = 3800;
        String server = "64.183.98.170";
        new CS380_Project5(port, server);

    }

}
