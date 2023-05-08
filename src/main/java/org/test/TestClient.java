package org.test;


import org.receving.Get;

import java.io.IOException;

public class TestClient {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Get get = new Get();
        get.start();
    }
}
