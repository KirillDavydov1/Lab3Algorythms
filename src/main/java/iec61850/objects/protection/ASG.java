package iec61850.objects.protection;


import iec61850.objects.samples.AnalogValue;

// Класс установки аналогово значения
public class ASG {

    private AnalogValue setMag = new AnalogValue();


    public AnalogValue getSetMag() {
        return setMag;
    }

    public void setSetMag(AnalogValue setMag) {
        this.setMag = setMag;
    }


}
