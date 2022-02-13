package kapta.utils.bean.beanin.jfx1;

import kapta.utils.bean.beanin.RequestBean;

public class JFX1RequestBean  extends RequestBean {

    public JFX1RequestBean(Integer numDoses, String vaccinationDate) {
        setNumDoses(numDoses);
        setVaccinationDate(vaccinationDate);
    }

    public void setNumDoses(Integer numDoses) {
        this.numDoses = numDoses;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
}
