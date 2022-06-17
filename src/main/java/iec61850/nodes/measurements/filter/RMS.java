package iec61850.nodes.measurements.filter;

import iec61850.objects.measurements.Vector;
import iec61850.objects.samples.SAV;

// Класс описывающий фильтр RMS(фильтр средне квадратичного значения)
public class RMS extends Filter {

    private int size = 20;// Сумма выборки значений 20
    private float[] buffer = new float[size];
    private float sum = 0;
    private int count = 0;


    @Override
    public void process(SAV sav, Vector vector) {

        sum += (float) Math.pow((sav.getInstMag().getF().getValue()), 2) - buffer[count];
        buffer[count] = (float) Math.pow(sav.getInstMag().getF().getValue(), 2);
        vector.getMag().getF().setValue((float) Math.sqrt(sum / size));

        if (++count >= size) count = 0;
    }

}


