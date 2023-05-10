package org.tools;

import org.bytedeco.javacv.Frame;

import java.io.IOException;

public interface Serialize{
    byte[] serialize(Frame frame) throws IOException;
    Frame deserialize(byte[] bytes) throws IOException, ClassNotFoundException;

//    void setFrame(Frame frame);
//
//    byte[] getData();
//
//    void start();
}
