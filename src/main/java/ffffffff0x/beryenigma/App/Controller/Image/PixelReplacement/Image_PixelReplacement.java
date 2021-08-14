package ffffffff0x.beryenigma.App.Controller.Image.PixelReplacement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * from https://github.com/yuchenxi2000/PicEncrypt
 * @author: RyuZUSUNC
 * @create: 2021/8/12 22:17
 **/
public class Image_PixelReplacement {
    public static BufferedImage image_transform(double key, File fi, Boolean encrypt, String mode) {
        try {
            BufferedImage bi = ImageIO.read(fi);
            int h = bi.getHeight();
            int w = bi.getWidth();
            int[] buffer = new int[w*h];

            bi.getRGB(0, 0, w, h, buffer, 0, w);

            // 一维转二维
            int[][] buffer_2d = new int[h][w];
            ArrayFunctions af = new ArrayFunctions();
            af.change(buffer, buffer_2d, h, w);

            // 解密
            Algorithms ma = new Algorithms();
            if (encrypt) {
                switch (mode) {
                    case "rc" -> ma.encrypt(buffer_2d, key, h, w);
                    case "r" -> ma.allRowEncrypt(buffer_2d, key, h, w);
                    default -> {
                        System.out.println("未知的模式，可能的模式：r/rc");
                        return null;
                    }
                }
            } else {
                switch (mode) {
                    case "rc" -> ma.decrypt(buffer_2d, key, h, w);
                    case "r" -> ma.allRowDecrypt(buffer_2d, key, h, w);
                    default -> {
                        System.out.println("未知的模式，可能的模式：r/rc");
                        return null;
                    }
                }
            }

            // 二维转一维
            af.recovery(buffer_2d, buffer, h, w);

            bi.setRGB(0, 0, w, h, buffer, 0, w);

            return  bi;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}