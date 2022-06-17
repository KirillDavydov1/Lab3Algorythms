package iec61850.nodes.protection;

import iec61850.nodes.common.LN;
import iec61850.objects.measurements.WYE;
import iec61850.objects.protection.*;
import iec61850.objects.protection.dir.INC;
import iec61850.objects.state.SPS;

public class PDIS extends LN {

    WYE Z = new WYE();

    ACD Str = new ACD();
    ACT Op = new ACT();

    SPS Blk = new SPS();
    ENG DirMod = new ENG();
    ING OpDITmms = new ING(); //Выдержка времени
    ASG PhStr = new ASG();

    INC OpCntRs = new INC(); // счетчик операций
    int counter = 0;
    double X0 = 0;
    double Y0 = 0;

    @Override
    public void process() {
        Op.getGeneral().setValue(false);
        Op.getPhsA().setValue(false);
        Op.getPhsB().setValue(false);
        Op.getPhsC().setValue(false);

        boolean phsA = Math.pow(PhStr.getSetMag().getF().getValue(), 2) >= Math.pow(Z.getPhsA().getcVal().getX().getF().getValue() - X0, 2)
                + Math.pow(Z.getPhsA().getcVal().getY().getF().getValue() - Y0, 2);
        boolean phsB = Math.pow(PhStr.getSetMag().getF().getValue(), 2) >= Math.pow(Z.getPhsB().getcVal().getX().getF().getValue() - X0, 2)
                + Math.pow(Z.getPhsB().getcVal().getY().getF().getValue() - Y0, 2);
        boolean phsC = Math.pow(PhStr.getSetMag().getF().getValue(), 2) >=
                Math.pow(Z.getPhsC().getcVal().getX().getF().getValue() - X0, 2)
                        + Math.pow(Z.getPhsC().getcVal().getY().getF().getValue() - Y0, 2);

        boolean general = phsA || phsB || phsC;

        if (!Blk.getStVal().getValue()) {
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

    public ENG getDirMod() {
        return DirMod;
    }

    public void setDirMod(ENG dirMod) {
        DirMod = dirMod;
    }

    public ING getOpDITmms() {
        return OpDITmms;
    }

    public void setOpDITmms(ING opDITmms) {
        OpDITmms = opDITmms;
    }

    public ASG getPhStr() {
        return PhStr;
    }

    public void setPhStr(ASG phStr) {
        PhStr = phStr;
    }

    public INC getOpCntRs() {
        return OpCntRs;
    }

    public void setOpCntRs(INC opCntRs) {
        OpCntRs = opCntRs;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public SPS getBlk() {
        return Blk;
    }

    public void setBlk(SPS blk) {
        Blk = blk;
    }

    public double getX0() {
        return X0;
    }

    public void setX0(double x0) {
        X0 = x0;
    }

    public double getY0() {
        return Y0;
    }

    public void setY0(double y0) {
        Y0 = y0;
    }
}
