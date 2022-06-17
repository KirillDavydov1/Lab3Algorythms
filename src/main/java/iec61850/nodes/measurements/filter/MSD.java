package iec61850.nodes.measurements.filter;

import iec61850.objects.measurements.Vector;
import iec61850.objects.samples.SAV;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */

public class MSD extends Filter {

    private int size = 80;
    private float[] buffer = new float[size];
    private float sum = 0;
    private int count = 0;


    @Override
    public void process(SAV sav, Vector vector) {

        sum += Math.abs(sav.getInstMag().getF().getValue()) - buffer[count];
        buffer[count] = Math.abs(sav.getInstMag().getF().getValue());
        vector.getMag().getF().setValue(sum / size);

        if (++count >= size) count = 0;
    }

}
