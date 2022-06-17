package iec61850.objects.samples;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */

//Класс представляет выборки мгновенных аналоговых значений
public class SAV {

    private AnalogValue instMag = new AnalogValue();


    public AnalogValue getInstMag() {
        return instMag;
    }

    public void setInstMag(AnalogValue instMag) {
        this.instMag = instMag;
    }
}
