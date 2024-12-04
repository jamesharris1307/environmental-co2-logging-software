package com.example.envlog.server;

import com.example.envlog.service.Login;
import com.example.envlog.util.Tools;
import com.example.envlog.util.WriteCSV;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler implements Runnable {
        private Socket client;

        public ClientHandler (Socket c) {
            client = c;
        }

        WriteCSV wCSV = new WriteCSV();

        Tools.UserInputData tu = new Tools.UserInputData();

//Run method
        public void run () {
//          Initialising variables
            BufferedReader clientIn = null;
            PrintWriter outClient = null;
            String input = null;
            float input2;
            String userID;

            try {
//              Getting client input
                clientIn = new BufferedReader (new InputStreamReader(client.getInputStream ()));
//              Print something to the client
                outClient = new PrintWriter (client.getOutputStream (), true);
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace ();
            }
            outClient.println("Welcome to the server !");


            do {

                try {

                    userID = clientIn.readLine ();

                    input = clientIn.readLine ();
                    outClient.println (input);

                    input2 = Float.parseFloat(clientIn.readLine());
                    outClient.println (input2);
                    wCSV.writeDataCSV(userID,input, input2);

                    input = clientIn.readLine ();
                }

                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace ();
                }

            }


            while (!input.equals ("close"));

            try {
                clientIn.close ();
                outClient.close ();
                client.close ();
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace ();
            }
        }
}
