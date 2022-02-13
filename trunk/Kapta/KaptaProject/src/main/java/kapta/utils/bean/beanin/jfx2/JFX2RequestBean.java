package kapta.utils.bean.beanin.jfx2;

import kapta.utils.bean.beanin.RequestBean;

public class JFX2RequestBean extends RequestBean {

    public JFX2RequestBean(Integer numDoses, String vaccinationDate) {
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