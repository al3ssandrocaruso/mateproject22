package kapta.utils.utils;

import kapta.utils.exception.myexception.InputDateException2;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HelperSetDuration {
    private HelperSetDuration(){
        //ignored
    }
    public static long conv(Double partyDuration) throws InputDateException2 {
        String durationStr="0"+partyDuration.toString().charAt(0)+":";
        String support=""+partyDuration.toString().charAt(2);
        if(support.equals("0")){durationStr=durationStr.concat("00");}
        else{durationStr=durationStr.concat("30");}

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm"); //e.g 12:15

        long ms= 0;
        try {
            ms = sdf.parse(durationStr).getTime();
        } catch (ParseException e) {
           throw new InputDateException2(partyDuration.toString(), "Duration","hh:mm");
        }
        return ms;

    }
}
