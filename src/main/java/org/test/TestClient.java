package org.test;


import org.receving.Client;
import org.receving.Get;

import java.io.IOException;
import java.io.Serial;
import java.net.Socket;

public class TestClient {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("192.168.100.112",9999);
    }
}
