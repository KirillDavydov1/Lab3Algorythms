package iec61850.objects.samples;


/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */

// Класс получения аналогово значения
public class AnalogValue {

    private Attribute<Float> f = new Attribute<>(0f);

    public Attribute<Float> getF() {
        return f;
    }

    public void setF(Attribute<Float> f) {
        this.f = f;
    }
}
