package org.test;

import org.bytedeco.javacv.Frame;
import org.tools.MyThreadspool;
import org.tools.SerializeFrameJava;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Test {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Socket s = new Socket("127.0.0.1",9999);
        ObjectInputStream objectInputStream = new ObjectInputStream(s.getInputStream());
        for(int i= 0;i<300;i++){
            byte[] data = (byte[]) objectInputStream.readObject();
            System.out.println(data.length);
        }
    }
}
