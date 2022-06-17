package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.*;
import iec61850.objects.samples.Attribute;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */

// Класс описывающий максимальную токовую защиту
public class PTOC extends LN {

    private WYE A = new WYE(); // 3 Вектора
    private ACT Op = new ACT(); //Срабатывание
    private ACD Str = new ACD(); // Направление мощности приходит от RDIR
    private ASG StrVal = new ASG(); // Токовая уставка
    private ING OpDITmms = new ING(); //Выдержка времени
    private int counter = 0; // Счетчик для выдержки времени

    private Attribute<Boolean> accelerat = new Attribute<>(false); // Ускорение

    private ENG DirMod = new ENG();

    @Override
    public void process() {

        if(accelerat.getValue()) {
            OpDITmms.getSetVal().setValue(10);
        }

        boolean phsA = false;
        boolean phsB = false;
        boolean phsC = false;

        if (DirMod.getDir().getValue().ordinal() == 1) {
            phsA = A.getPhsA().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() &&
                    Str.getDirPhsA().getValue() == DirMod.getDir().getValue();
            phsB = A.getPhsB().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() &&
                    Str.getDirPhsB().getValue() == DirMod.getDir().getValue();
            phsC = A.getPhsC().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue() &&
                    Str.getDirPhsC().getValue() == DirMod.getDir().getValue();
        } else {
            phsA = A.getPhsA().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
            phsB = A.getPhsB().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();
            phsC = A.getPhsC().getcVal().getMag().getF().getValue() > StrVal.getSetMag().getF().getValue();


        }


        boolean general = phsA || phsB || phsC;

        if (general) {
            if (++counter >= OpDITmms.getSetVal().getValue()) {
                Op.getGeneral().setValue(general);
                Op.getPhsA().setValue(phsA);
                Op.getPhsB().setValue(phsB);
                Op.getPhsC().setValue(phsC);
            }
        } else {
            counter = 0;
        }
    }

    public WYE getA() {
        return A;
    }

    public void setA(WYE a) {
        A = a;
    }

    public ACT getOp() {
        return Op;
    }

    public void setOp(ACT op) {
        Op = op;
    }

    public ACD getStr() {
        return Str;
    }

    public void setStr(ACD str) {
        Str = str;
    }

    public ASG getStrVal() {
        return StrVal;
    }

    public void setStrVal(Float strVal) {

        StrVal.getSetMag().getF().setValue(strVal);
    }

    public ING getOpDITmms() {
        return OpDITmms;
    }

    public void setOpDITmms(ING opDITmms) {
        OpDITmms = opDITmms;
    }

    public ENG getDirMod() {
        return DirMod;
    }

    public void setDirMod(ENG dirMod) {
        DirMod = dirMod;
    }

    public Attribute<Boolean> getAccelerat() {
        return accelerat;
    }

    public void setAccelerat(Attribute<Boolean> accelerat) {
        this.accelerat = accelerat;
    }
}
