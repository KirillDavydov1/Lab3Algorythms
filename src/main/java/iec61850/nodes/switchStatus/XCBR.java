package iec61850.nodes.switchStatus;

import iec61850.nodes.common.LN;
import iec61850.objects.protection.dir.INC;
import iec61850.objects.state.DPC;
import iec61850.objects.state.SPS;

// Класс описывающий Выключатель
public class XCBR extends LN {

    private DPC Pos = new DPC(); // Положение выключателя
    private SPS BlkOpn = new SPS(); // Блокировка отключения выключателя
    private SPS BlkCls = new SPS(); // Блокировка включения выключателя
    private SPS Loc = new SPS(); // Местное управление
    private INC OpCnt = new INC(); // Счетчик операций
    private DPC ctVal = new DPC(); // Сигналы управления от CSWI

    @Override
    public void process() {
        if (BlkOpn.getStVal().getValue()) {
            if ((ctVal.getStVal().getValue() && !Pos.getStVal().getValue())) {
                Pos.getStVal().setValue(true);
            }
        } else {
            Pos.getStVal().setValue(false);
        }
    }

    public DPC getPos() {
        return Pos;
    }

    public void setPos(DPC pos) {
        Pos = pos;
    }

    public SPS getBlkOpn() {
        return BlkOpn;
    }

    public void setBlkOpn(SPS blkOpn) {
        BlkOpn = blkOpn;
    }

    public SPS getBlkCls() {
        return BlkCls;
    }

    public void setBlkCls(SPS blkCls) {
        BlkCls = blkCls;
    }

    public SPS getLoc() {
        return Loc;
    }

    public void setLoc(SPS loc) {
        Loc = loc;
    }

    public INC getOpCnt() {
        return OpCnt;
    }

    public void setOpCnt(INC opCnt) {
        OpCnt = opCnt;
    }

    public DPC getCtVal() {
        return ctVal;
    }

    public void setCtVal(DPC ctVal) {
        this.ctVal = ctVal;
    }


}
