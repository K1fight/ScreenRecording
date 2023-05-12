package org.test;


import org.recording.JavacvRecording;

import java.io.IOException;
import java.net.ServerSocket;

public class TestServer {
    public static void main(String[]args) throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket server = new ServerSocket(9999);
        server.accept();

    }
}
