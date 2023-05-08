package org.tools;

import java.awt.image.BufferedImage;

public class FakeBufferedImage {
    BufferedImage bufferedImage;
    public FakeBufferedImage(){
         bufferedImage = new BufferedImage(200,300,5);
    }
//    public FakeBufferedImage(BufferedImage bufferedImage){
//        this.bufferedImage = bufferedImage;
//    }
    public BufferedImage getBufferedImage(){
        return this.bufferedImage;
    }
}
