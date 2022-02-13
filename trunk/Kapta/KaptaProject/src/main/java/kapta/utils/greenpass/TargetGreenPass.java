package kapta.utils.greenpass;

import kapta.utils.exception.myexception.GenericException;
import kapta.utils.exception.myexception.InavalidGreenPassException;

//Target
public interface TargetGreenPass {
    public String[] getInfoGreenPass(String pathname) throws  InavalidGreenPassException, GenericException;
}