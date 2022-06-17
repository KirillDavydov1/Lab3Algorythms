package iec61850.nodes.measurements;

import iec61850.nodes.common.LN;
import iec61850.nodes.protection.RDIR;
import iec61850.objects.measurements.SEQ;
import iec61850.objects.measurements.WYE;
// Класс расчета симметричных составляющиъ тока и напряжения
public class MSQI extends LN {

    SEQ SeqA = new SEQ();
    SEQ SeqV = new SEQ();

    private WYE I = new WYE();
    private WYE U = new WYE();

    @Override
    public void process() {
        SeqA.getC3().getcVal().setXY(
                (I.getPhsA().getcVal().getX().getF().getValue() +
                        I.getPhsB().getcVal().getX().getF().getValue() +
                        I.getPhsC().getcVal().getX().getF().getValue()) / 3,
                (I.getPhsA().getcVal().getY().getF().getValue() +
                        I.getPhsB().getcVal().getY().getF().getValue() +
                        I.getPhsC().getcVal().getY().getF().getValue()) / 3);

        SeqV.getC3().getcVal().setXY(
                (U.getPhsA().getcVal().getX().getF().getValue() +
                        U.getPhsB().getcVal().getX().getF().getValue() +
                        U.getPhsC().getcVal().getX().getF().getValue()) / 3,
                (U.getPhsA().getcVal().getY().getF().getValue() +
                        U.getPhsB().getcVal().getY().getF().getValue() +
                        U.getPhsC().getcVal().getY().getF().getValue()) / 3);
    }


    public SEQ getSeqA() {
        return SeqA;
    }

    public void setSeqA(SEQ seqA) {
        SeqA = seqA;
    }

    public SEQ getSeqV() {
        return SeqV;
    }

    public void setSeqV(SEQ seqV) {
        SeqV = seqV;
    }

    public WYE getI() {
        return I;
    }

    public void setI(WYE i) {
        I = i;
    }

    public WYE getU() {
        return U;
    }

    public void setU(WYE u) {
        U = u;
    }
}
