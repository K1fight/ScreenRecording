package org.test;


import org.recording.JavacvRecording;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[]args) throws IOException, ClassNotFoundException, InterruptedException {
        Socket s = new Socket("127.0.0.1",12000);
        DataInputStream input = new DataInputStream(s.getInputStream());
        for(int i = 0;i<10000;i++){
            System.out.println("2");
            System.out.println(input.readUTF());
        }

    }
}
