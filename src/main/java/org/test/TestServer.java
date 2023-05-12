package org.test;


import org.recording.JavacvRecording;

import java.io.IOException;
import java.net.ServerSocket;

public class TestServer {
    public static void main(String[]args) throws IOException, ClassNotFoundException, InterruptedException {
       JavacvRecording recording = new JavacvRecording();
       recording.start();

    }
}
