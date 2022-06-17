package iec61850.objects.state;

import iec61850.objects.protection.dir.CodedEnum;
import iec61850.objects.samples.Attribute;

// Класс дублированного состояния
public class DPC {

    private Attribute<Boolean> stVal = new Attribute<>(false);

    public Attribute<Boolean> getStVal() {
        return stVal;
    }

    public void setStVal(Attribute<Boolean> stVal) {
        this.stVal = stVal;
    }
    }

