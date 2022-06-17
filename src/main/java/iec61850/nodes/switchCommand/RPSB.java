package iec61850.nodes.switchCommand;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.ACD;
import iec61850.objects.protection.ACT;
import iec61850.objects.protection.ING;
import iec61850.objects.state.SPS;

import java.util.Deque;
import java.util.LinkedList;

//Класс Обеспечивающий блокировку при качаниях
public class RPSB extends LN {

    WYE Z = new WYE();

    ACD Str = new ACD();
    ACT Op = new ACT();
    SPS BlkZn = new SPS();
    ING UnBlkTmms = new ING();


    ING MaxNumSlp = new ING(); // Число проскальзываний
    ING EvTmms = new ING(); // Временной интервал допустимый. Фактическое число проскальзываний обнуляют либо после выключения,
    //либо по истечении времени оценки.
    private float k = 10;
    int counter = 0;

    private Deque<Float> AX = new LinkedList<>();
    private Deque<Float> BX = new LinkedList<>();
    private Deque<Float> CX = new LinkedList<>();
    private Deque<Float> AY = new LinkedList<>();
    private Deque<Float> BY = new LinkedList<>();
    private Deque<Float> CY = new LinkedList<>();

    @Override
    public void process() {

        if (AX.size() <= k) {
            AX.addLast(Z.getPhsA().getcVal().getX().getF().getValue());
            BX.addLast(Z.getPhsB().getcVal().getX().getF().getValue());
            CX.addLast(Z.getPhsC().getcVal().getX().getF().getValue());
            AY.addLast(Z.getPhsA().getcVal().getY().getF().getValue());
            BY.addLast(Z.getPhsB().getcVal().getY().getF().getValue());
            CY.addLast(Z.getPhsC().getcVal().getY().getF().getValue());
        } else {
            AX.addLast(Z.getPhsA().getcVal().getX().getF().getValue());
            AX.removeFirst();
            BX.addLast(Z.getPhsB().getcVal().getX().getF().getValue());
            BX.removeFirst();
            CX.addLast(Z.getPhsC().getcVal().getX().getF().getValue());
            CX.removeFirst();
            AY.addLast(Z.getPhsA().getcVal().getY().getF().getValue());
            AY.removeFirst();
            BY.addLast(Z.getPhsB().getcVal().getY().getF().getValue());
            BY.removeFirst();
            CY.addLast(Z.getPhsC().getcVal().getY().getF().getValue());
            CY.removeFirst();
        }

        if ((Math.abs((AX.getLast() - AX.getFirst()) / 2500) > .0001f) ||
                (Math.abs((BX.getLast() - BX.getFirst()) / 2500) > .0001f) ||
                (Math.abs((CX.getLast() - CX.getFirst()) / 2500) > .0001f) ||
                (Math.abs((AY.getLast() - AY.getFirst()) / 2500) > .0001f) ||
                (Math.abs((BY.getLast() - BY.getFirst()) / 2500) > .0001f) ||
                (Math.abs((CY.getLast() - CY.getFirst()) / 2500) > .0001f)) {
            BlkZn.getStVal().setValue(false);
        }
        if (!BlkZn.getStVal().getValue() && ++counter >= UnBlkTmms.getSetVal().getValue()) {
            BlkZn.getStVal().setValue(true);
            counter = 0;
        }


    }


    public WYE getZ() {
        return Z;
    }

    public void setZ(WYE z) {
        Z = z;
    }

    public ACD getStr() {
        return Str;
    }

    public void setStr(ACD str) {
        Str = str;
    }

    public ACT getOp() {
        return Op;
    }

    public void setOp(ACT op) {
        Op = op;
    }

    public SPS getBlkZn() {
        return BlkZn;
    }

    public void setBlkZn(SPS blkZn) {
        BlkZn = blkZn;
    }

    public ING getMaxNumSlp() {
        return MaxNumSlp;
    }

    public void setMaxNumSlp(ING maxNumSlp) {
        MaxNumSlp = maxNumSlp;
    }

    public ING getEvTmms() {
        return EvTmms;
    }

    public void setEvTmms(ING evTmms) {
        EvTmms = evTmms;
    }

    public ING getUnBlkTmms() {
        return UnBlkTmms;
    }

    public void setUnBlkTmms(ING unBlkTmms) {
        UnBlkTmms = unBlkTmms;
    }
}


