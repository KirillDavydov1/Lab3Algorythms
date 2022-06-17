package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.ACD;
import iec61850.objects.protection.dir.Direction;
//Класс отвечающий за направленность защиты
public class RDIR extends LN {

    ACD Dir = new ACD();    //Направление передаем в PTOC
    WYE W = new WYE();      //Принимает знак мощности

    @Override
    public void process() {
        if (W.getPhsA().getcVal().getMag().getF().getValue() > 0) {
            Dir.getDirPhsA().setValue(Direction.FORWARD);
        } else {
            Dir.getDirPhsA().setValue(Direction.BACKWARD);
        }

        if (W.getPhsB().getcVal().getMag().getF().getValue() > 0) {
            Dir.getDirPhsB().setValue(Direction.FORWARD);
        } else {
            Dir.getDirPhsB().setValue(Direction.BACKWARD);
        }

        if (W.getPhsC().getcVal().getMag().getF().getValue() > 0) {
            Dir.getDirPhsC().setValue(Direction.FORWARD);
        } else {
            Dir.getDirPhsC().setValue(Direction.BACKWARD);
        }
    }

    public ACD getDir() {
        return Dir;
    }

    public void setDir(ACD dir) {
        Dir = dir;
    }

    public WYE getW() {
        return W;
    }

    public void setW(WYE w) {
        W = w;
    }
}
