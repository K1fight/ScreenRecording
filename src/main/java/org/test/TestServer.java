package org.test;


import org.recording.JavacvRecording;

import java.io.IOException;

public class TestServer {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        JavacvRecording javacvRecording = new JavacvRecording();
        javacvRecording.start();

    }
}
