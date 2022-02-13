package kapta.utils.greenpass;

import COSE.CoseException;
import COSE.Message;
import com.google.iot.cbor.CborMap;
import com.google.iot.cbor.CborParseException;
import com.google.zxing.*;
import kapta.utils.exception.ErrorHandler;
import kapta.utils.exception.myexception.GenericException;
import kapta.utils.exception.myexception.InavalidGreenPassException;
import nl.minvws.encoding.Base45;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import static COSE.Message.DecodeFromBytes;

//Adapter
public class AdapterGreenPass implements TargetGreenPass {

    // riferimento all'istanza di Adaptee
    private QRGreenPass greenPass;


    public AdapterGreenPass(){
        this.greenPass = new QRGreenPass();
    }

    @Override
    public String [] getInfoGreenPass(String pathname) throws InavalidGreenPassException, GenericException {
        return decode(pathname);
    }

    private String[] decode(String pathname) throws InavalidGreenPassException, GenericException {

        String strName;
        String strSurname;
        String strDate;
        String strNumDosi;

        String[] ret = null ;
        try {
             ret = new String[4];

            String text = greenPass.retriveString(pathname);

            byte[] bytecompressed = Base45.getDecoder().decode(text.substring(4));
            Inflater inflater = new Inflater();
            inflater.setInput(bytecompressed);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bytecompressed.length);
            byte[] buffer = new byte[greenPass.getBufferSize()];
            while (!inflater.finished()) {
                final int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            Message a = DecodeFromBytes(outputStream.toByteArray());
            CborMap cborMap = CborMap.createFromCborByteArray(a.GetContent());
            String str = cborMap.toString(2);

            String support;

            //nome
            support = str.substring(str.indexOf("gnt") + 6);
            strName = support.substring(0, support.indexOf(",") - 1);
            ret[0] = strName;

            //cognome
            support = str.substring(str.indexOf("fnt") + 6);
            strSurname = support.substring(0, support.indexOf(",") - 1);
            ret[1] = strSurname;

            //data
            support = str.substring(str.indexOf("dt") + 5);
            strDate = support.substring(0, support.indexOf(",") - 1);
            ret[2] = strDate;

            //dose
            support = str.substring(str.indexOf("dn") + 4);
            strNumDosi = support.substring(0, support.indexOf(","));
            ret[3] = strNumDosi;

        } catch (CoseException | CborParseException |DataFormatException| IOException e) {
            ErrorHandler.getInstance().reportGeneric(e);
        } catch (NotFoundException e) {
            ErrorHandler.getInstance().reportInvelidGreenPass(e);
        }

        return ret;
    }

}
