package org.test;


import org.receving.Client;
import org.receving.Get;

import java.io.IOException;

public class TestClient {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        client.start();
    }
}
