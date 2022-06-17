package iec61850.nodes.measurements.filter;

import iec61850.objects.measurements.Vector;
import iec61850.objects.samples.SAV;


public class Fourier extends Filter {

    private int count = 0;
    final private int size = 80;
    final private float k = (float) 2 / size;
    private float Fx = 0;
    private float Fy = 0;
    private float[] tmpX = new float[size];
    private float[] tmpY = new float[size];

    @Override
    public void process(SAV sav, Vector vector) {

        Fx += k * (sav.getInstMag().getF().getValue() * Math.sin(2 * Math.PI * count / size) - tmpX[count]);
        Fy += k * (sav.getInstMag().getF().getValue() * Math.cos(2 * Math.PI * count / size) - tmpY[count]);

        tmpX[count] = (float) (sav.getInstMag().getF().getValue() * Math.sin(2 * Math.PI * count / size));
        tmpY[count] = (float) (sav.getInstMag().getF().getValue() * Math.cos(2 * Math.PI * count / size));

        vector.setXY(Fx, Fy);
        if (++count >= size) count = 0;
    }

}
