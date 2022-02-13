package kapta.utils.greenpass;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Adaptee
public class QRGreenPass {

    private String semiDecodedString;
    private int buffer_size = 1024;

    public int getBufferSize(){
        return buffer_size;
    }

    public void setSemiDecodedString(String semiDecodedString) {
        this.semiDecodedString = semiDecodedString;
    }

    public String getSemiDecodedString() {
        return semiDecodedString;
    }

    public String retriveString(String pathname) throws IOException, NotFoundException {
        File file = new File(pathname);
        BufferedImage bufferedImage = ImageIO.read(file);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        String text = result.getText();

        this.setSemiDecodedString(text);

        return text;
    }

}
