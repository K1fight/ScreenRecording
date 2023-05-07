package org.test;

import org.bytedeco.javacv.Frame;
import org.receving.Get;
import org.recording.Post;

import java.io.IOException;

public class Test {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Frame frame = new Frame();
        Post post = new Post();
        post.start(frame);
        Get get = new Get();
        get.start();

    }
}
