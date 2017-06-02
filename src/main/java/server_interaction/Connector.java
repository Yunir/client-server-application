package server_interaction;

import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static main.Main.mainController;

/**
 * Created by Yunicoed on 17.05.2017.
 */
public class Connector {
    Socket socket = null;
    InputStream in = null;
    OutputStream out = null;
    private InetAddress IA;
    private int port;
    public IOFuncs ioFuncs;

    public boolean establishConnection(Stage primaryStage) {
        try {
            socket = new Socket(IA, port);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            ioFuncs = new IOFuncs(new DataInputStream(in), new DataOutputStream(out));
            return true;
        } catch (UnknownHostException e) {
            System.out.println("Host not found");
            return false;
        } catch (IOException e) {
            //TODO: pepe frog
            mainController.showServerUnavailableDialog(primaryStage);
            System.out.println("Server in "+ IA.getHostAddress()+":"+port+" is not available");
            return false;
        }
    }

    /*Getters and setters*/

    public void setIA(InetAddress IA) {
        this.IA = IA;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
