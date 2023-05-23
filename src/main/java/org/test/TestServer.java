package org.test;


import org.recording.JavacvRecording;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[]args) throws IOException, ClassNotFoundException, InterruptedException {
        JavacvRecording jc = JavacvRecording.getInstance();
        jc.start();

    }
}
