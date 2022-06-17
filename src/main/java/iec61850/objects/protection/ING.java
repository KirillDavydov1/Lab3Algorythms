package iec61850.objects.protection;

import iec61850.objects.samples.Attribute;
// Класс установки (integer) целочисленного значения
public class ING {


    private Attribute<Integer> setVal = new Attribute<>(0);

    public Attribute<Integer> getSetVal() {
        return setVal;
    }

    public void setSetVal(Attribute<Integer> setVal) {
        this.setVal = setVal;
    }
}
