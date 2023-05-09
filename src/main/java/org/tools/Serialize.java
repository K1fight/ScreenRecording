package org.tools;

import org.bytedeco.javacv.Frame;

import java.io.IOException;

public interface Serialize{
    public byte[] serialize(Frame frame) throws IOException;
    public Frame deserialize(byte[] bytes) throws IOException, ClassNotFoundException;

}
