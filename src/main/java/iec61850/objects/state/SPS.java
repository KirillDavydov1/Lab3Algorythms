package iec61850.objects.state;

import iec61850.objects.samples.Attribute;

public class SPS {

    private Attribute<Boolean> stVal = new Attribute<>(true);


    public Attribute<Boolean> getStVal() {
        return stVal;
    }

    public void setStVal(Attribute<Boolean> stVal) {
        this.stVal = stVal;
    }
}
