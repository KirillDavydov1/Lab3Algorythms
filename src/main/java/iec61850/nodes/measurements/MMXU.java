package iec61850.nodes.measurements;

import iec61850.nodes.common.LN;
import iec61850.nodes.measurements.filter.Filter;
import iec61850.nodes.measurements.filter.Fourier;
import iec61850.nodes.measurements.filter.MSD;
import iec61850.nodes.measurements.filter.RMS;
import iec61850.objects.measurements.CMV;
import iec61850.objects.measurements.WYE;
import iec61850.objects.samples.SAV;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */

// Класс, в котором происходит прием значения тока, напряжения и происходит его фильтрация через фильтр
public class MMXU extends LN {

    private SAV instIa = new SAV(); //Ток фазы А
    private SAV instIb = new SAV(); //Ток фазы B
    private SAV instIc = new SAV(); //Ток фазы C

    private SAV instUa = new SAV(); //Напряжение фазы A
    private SAV instUb = new SAV(); //Напряжение фазы B
    private SAV instUc = new SAV(); //Напряжение фазы C

    private WYE A = new WYE(); // Фазные токи
    private WYE V = new WYE(); // Фазный напряжения
    private WYE W = new WYE(); // Мощности
    private WYE Z = new WYE(); //


    private Filter iaF = new Fourier(); //Ток фазы А после фильтра типа Fourier
    private Filter ibF = new Fourier(); //Ток фазы B после фильтра типа Fourier
    private Filter icF = new Fourier(); //Ток фазы C после фильтра типа Fourier

    private Filter uaF = new Fourier(); //Напряжение фазы А после фильтра типа Fourier
    private Filter ubF = new Fourier(); //Напряжение фазы B после фильтра типа Fourier
    private Filter ucF = new Fourier(); //Напряжение фазы C после фильтра типа Fourier

    @Override
    public void process() {

        iaF.process(instIa, A.getPhsA().getcVal());
        ibF.process(instIb, A.getPhsB().getcVal());
        icF.process(instIc, A.getPhsC().getcVal());

        uaF.process(instUa, V.getPhsA().getcVal());
        ubF.process(instUb, V.getPhsB().getcVal());
        ucF.process(instUc, V.getPhsC().getcVal());

        W.getPhsA().getcVal().getMag().getF().setValue(
                (float) (A.getPhsA().getcVal().getMag().getF().getValue() *
                        V.getPhsA().getcVal().getMag().getF().getValue() *
                        (Math.cos(Math.toRadians(V.getPhsA().getcVal().getAng().getF().getValue() -
                                A.getPhsA().getcVal().getAng().getF().getValue())))));
        W.getPhsB().getcVal().getMag().getF().setValue(
                (float) (A.getPhsB().getcVal().getMag().getF().getValue() *
                        V.getPhsB().getcVal().getMag().getF().getValue() *
                        (Math.cos(Math.toRadians(V.getPhsB().getcVal().getAng().getF().getValue() -
                                A.getPhsB().getcVal().getAng().getF().getValue())))));
        W.getPhsC().getcVal().getMag().getF().setValue(
                (float) (A.getPhsC().getcVal().getMag().getF().getValue() *
                        V.getPhsC().getcVal().getMag().getF().getValue() *
                        (Math.cos(Math.toRadians(V.getPhsC().getcVal().getAng().getF().getValue() -
                                A.getPhsC().getcVal().getAng().getF().getValue())))));

        //Фаза A Здесь считаем x составляющую Z - тоесть R

        Z.getPhsA().getcVal().setByMagAndAngle(((V.getPhsA().getcVal().getX().getF().getValue()-V.getPhsB().getcVal().getX().getF().getValue())
                                      *(A.getPhsA().getcVal().getX().getF().getValue()-A.getPhsB().getcVal().getX().getF().getValue())
                                      +(V.getPhsA().getcVal().getY().getF().getValue()-V.getPhsB().getcVal().getY().getF().getValue())
                                      *(A.getPhsA().getcVal().getY().getF().getValue()-A.getPhsB().getcVal().getY().getF().getValue()))/

                                        ((A.getPhsA().getcVal().getX().getF().getValue()-A.getPhsB().getcVal().getX().getF().getValue())
                                      *(A.getPhsA().getcVal().getX().getF().getValue()-A.getPhsB().getcVal().getX().getF().getValue())
                                      +(A.getPhsA().getcVal().getY().getF().getValue()-A.getPhsB().getcVal().getY().getF().getValue())
                                      *(A.getPhsA().getcVal().getY().getF().getValue()-A.getPhsB().getcVal().getY().getF().getValue())),

                                    ((V.getPhsA().getcVal().getY().getF().getValue()-V.getPhsB().getcVal().getY().getF().getValue())
                                      *(A.getPhsA().getcVal().getX().getF().getValue()-A.getPhsB().getcVal().getX().getF().getValue())
                                      +(V.getPhsA().getcVal().getX().getF().getValue()-V.getPhsB().getcVal().getX().getF().getValue())
                                      *(A.getPhsA().getcVal().getY().getF().getValue()-A.getPhsB().getcVal().getY().getF().getValue()))/

                                            ((A.getPhsA().getcVal().getX().getF().getValue()-A.getPhsB().getcVal().getX().getF().getValue())
                                      *(A.getPhsA().getcVal().getX().getF().getValue()-A.getPhsB().getcVal().getX().getF().getValue())
                                      +(A.getPhsA().getcVal().getY().getF().getValue()-A.getPhsB().getcVal().getY().getF().getValue())
                                      *(A.getPhsA().getcVal().getY().getF().getValue()-A.getPhsB().getcVal().getY().getF().getValue())));

        //Фаза B Здесь считаем x составляющую Z - тоесть R
        Z.getPhsB().getcVal().setByMagAndAngle(((V.getPhsB().getcVal().getX().getF().getValue()-V.getPhsC().getcVal().getX().getF().getValue())
                                      *(A.getPhsB().getcVal().getX().getF().getValue()-A.getPhsC().getcVal().getX().getF().getValue())
                                      +(V.getPhsB().getcVal().getY().getF().getValue()-V.getPhsC().getcVal().getY().getF().getValue())
                                      *(A.getPhsB().getcVal().getY().getF().getValue()-A.getPhsC().getcVal().getY().getF().getValue()))/

                                        ((A.getPhsB().getcVal().getX().getF().getValue()-A.getPhsC().getcVal().getX().getF().getValue())
                                      *(A.getPhsB().getcVal().getX().getF().getValue()-A.getPhsC().getcVal().getX().getF().getValue())
                                      +(A.getPhsB().getcVal().getY().getF().getValue()-A.getPhsC().getcVal().getY().getF().getValue())
                                      *(A.getPhsB().getcVal().getY().getF().getValue()-A.getPhsC().getcVal().getY().getF().getValue())),

                                    ((V.getPhsB().getcVal().getY().getF().getValue()-V.getPhsC().getcVal().getY().getF().getValue())
                                      *(A.getPhsB().getcVal().getX().getF().getValue()-A.getPhsC().getcVal().getX().getF().getValue())
                                      +(V.getPhsB().getcVal().getX().getF().getValue()-V.getPhsC().getcVal().getX().getF().getValue())
                                      *(A.getPhsB().getcVal().getY().getF().getValue()-A.getPhsC().getcVal().getY().getF().getValue()))/

                                            ((A.getPhsB().getcVal().getX().getF().getValue()-A.getPhsC().getcVal().getX().getF().getValue())
                                      *(A.getPhsB().getcVal().getX().getF().getValue()-A.getPhsC().getcVal().getX().getF().getValue())
                                      +(A.getPhsB().getcVal().getY().getF().getValue()-A.getPhsC().getcVal().getY().getF().getValue())
                                      *(A.getPhsB().getcVal().getY().getF().getValue()-A.getPhsC().getcVal().getY().getF().getValue())));

        //Фаза C Здесь считаем x составляющую Z - тоесть R
        Z.getPhsC().getcVal().setByMagAndAngle(((V.getPhsC().getcVal().getX().getF().getValue()-V.getPhsA().getcVal().getX().getF().getValue())
                                      *(A.getPhsC().getcVal().getX().getF().getValue()-A.getPhsA().getcVal().getX().getF().getValue())
                                      +(V.getPhsC().getcVal().getY().getF().getValue()-V.getPhsA().getcVal().getY().getF().getValue())
                                      *(A.getPhsC().getcVal().getY().getF().getValue()-A.getPhsA().getcVal().getY().getF().getValue()))/

                                        ((A.getPhsC().getcVal().getX().getF().getValue()-A.getPhsA().getcVal().getX().getF().getValue())
                                      *(A.getPhsC().getcVal().getX().getF().getValue()-A.getPhsA().getcVal().getX().getF().getValue())
                                      +(A.getPhsC().getcVal().getY().getF().getValue()-A.getPhsA().getcVal().getY().getF().getValue())
                                      *(A.getPhsC().getcVal().getY().getF().getValue()-A.getPhsA().getcVal().getY().getF().getValue())),

                                        ((V.getPhsC().getcVal().getY().getF().getValue()-V.getPhsA().getcVal().getY().getF().getValue())
                                      *(A.getPhsC().getcVal().getX().getF().getValue()-A.getPhsA().getcVal().getX().getF().getValue())
                                      +(V.getPhsC().getcVal().getX().getF().getValue()-V.getPhsA().getcVal().getX().getF().getValue())
                                      *(A.getPhsC().getcVal().getY().getF().getValue()-A.getPhsA().getcVal().getY().getF().getValue()))/

                                                ((A.getPhsC().getcVal().getX().getF().getValue()-A.getPhsA().getcVal().getX().getF().getValue())
                                      *(A.getPhsC().getcVal().getX().getF().getValue()-A.getPhsA().getcVal().getX().getF().getValue())
                                      +(A.getPhsC().getcVal().getY().getF().getValue()-A.getPhsA().getcVal().getY().getF().getValue())
                                      *(A.getPhsC().getcVal().getY().getF().getValue()-A.getPhsA().getcVal().getY().getF().getValue())));

    }

    public void setInstIa(SAV instIa) {
        this.instIa = instIa;
    }

    public void setInstIb(SAV instIb) {
        this.instIb = instIb;
    }

    public void setInstIc(SAV instIc) {
        this.instIc = instIc;
    }

    public void setInstUa(SAV instUa) {
        this.instUa = instUa;
    }

    public void setInstUb(SAV instUb) {
        this.instUb = instUb;
    }

    public void setInstUc(SAV instUc) {
        this.instUc = instUc;
    }

    public WYE getA() {
        return A;
    }

    public SAV getInstIa() {
        return instIa;
    }

    public SAV getInstIb() {
        return instIb;
    }

    public SAV getInstIc() {
        return instIc;
    }


    public SAV getInstUa() {
        return instUa;
    }

    public SAV getInstUb() {
        return instUb;
    }

    public SAV getInstUc() {
        return instUc;
    }

    public void setA(WYE a) {
        A = a;
    }

    public WYE getV() {
        return V;
    }

    public void setV(WYE v) {
        V = v;
    }

    public WYE getW() {
        return W;
    }

    public void setW(WYE w) {
        W = w;
    }

    public WYE getZ() {
        return Z;
    }

    public void setZ(WYE z) {
        Z = z;
    }
}
