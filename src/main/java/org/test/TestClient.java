package org.test;


import org.receving.Get;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serial;
import java.net.ServerSocket;
import java.net.Socket;

public class TestClient {
    public static void main(String[]args) throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket ss = new ServerSocket(12000);
        Socket s = ss.accept();
        String test = "";
        for(int i = 0;i<65535;i++){
            test += 'a';
        }
        System.out.println("start");
        DataOutputStream output = new DataOutputStream(s.getOutputStream());
        Thread.sleep(100);
        for(int i = 0;i<10000;i++){
            System.out.println("1");
            output.writeUTF(test);
            output.flush();
            Thread.sleep(100);
        }

    }
}
