package iec61850.objects.measurements;

import iec61850.objects.samples.AnalogValue;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */
// Класс характеризующий вектор
public class Vector {

    private AnalogValue mag = new AnalogValue(); // Модуль значения
    private AnalogValue ang = new AnalogValue(); // Угол значения

    private AnalogValue X = new AnalogValue(); // Составляющая вектора X
    private AnalogValue Y = new AnalogValue(); // Составляющая вектора Y

    public AnalogValue getMag() {
        return mag;
    }

    public void setMag(AnalogValue mag) {
        this.mag = mag;
    }

    public AnalogValue getAng() {
        return ang;
    }

    public void setAng(AnalogValue ang) {
        this.ang = ang;
    }

    public void setXY(float X, float Y) {
        getX().getF().setValue(X);
        getY().getF().setValue(Y);
        mag.getF().setValue((float) Math.sqrt(X * X + Y * Y));
        ang.getF().setValue((float) Math.toDegrees(Math.atan2(Y, X)));
    }

    public void setByMagAndAngle(float Mag, float Angle) {
        mag.getF().setValue(Mag);
        ang.getF().setValue(Angle);
        X.getF().setValue((float) (mag.getF().getValue() * Math.cos(Math.toRadians(ang.getF().getValue()))));
        Y.getF().setValue((float) (mag.getF().getValue() * Math.sin(Math.toRadians(ang.getF().getValue()))));
    }

    public AnalogValue getX() {
        return X;
    }

    public void setX(AnalogValue x) {
        X = x;
    }

    public AnalogValue getY() {
        return Y;
    }

    public void setY(AnalogValue y) {
        Y = y;
    }
}
