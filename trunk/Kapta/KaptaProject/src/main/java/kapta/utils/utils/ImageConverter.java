package kapta.utils.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.SystemException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageConverter {

    private ImageConverter(){
        //ignore
    }

    private static final int DEFAULT_BUFFER_SIZE = 8192*4;

    public static Image convertToFxImage(BufferedImage image) {
    WritableImage wr = null;
    if (image != null) {
        wr = new WritableImage(image.getWidth(), image.getHeight());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                pw.setArgb(x, y, image.getRGB(x, y));
            }
        }
    }

    return new ImageView(wr).getImage();
}

    public  static void copyInputStreamToFile(InputStream inputStream, File file) throws SystemException {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, false);
        int read;
          byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
          while ((read = inputStream.read(bytes)) != -1) {
              outputStream.write(bytes, 0, read);
          }
        } catch (IOException e) {
            ErrorHandler.getInstance().handleException(e);
        }
    }


    public static Image convertFileToFxImage(File im) throws SystemException {
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(im);
            return convertToFxImage(bf);
        } catch (IOException e) {
            ErrorHandler.getInstance().handleException(e);
        }
        return null;


    }
}
