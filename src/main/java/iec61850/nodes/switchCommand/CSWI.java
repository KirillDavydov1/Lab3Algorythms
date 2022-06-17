package iec61850.nodes.switchCommand;

import iec61850.nodes.common.LN;
import iec61850.objects.protection.ACT;
import iec61850.objects.state.DPC;

// Класс регулирующий переключения выключателя (XCBR)
public class CSWI extends LN {

    private DPC Pos = new DPC(); // Устанавливает положение XCBR
    private DPC ctVal = new DPC(); // Получает от XCBR данные о положении
    private DPC stVal = new DPC();
    private ACT stage1 = new ACT(); // Ступень 1 получающая данные о ее срабатывании
    private ACT stage2 = new ACT(); // Ступень 2 получающая данные о ее срабатывании
    private ACT stage3 = new ACT(); //  Ступень 3 получающая данные о ее срабатывании
    private ACT stage4 = new ACT(); // Ступень 4 получающая данные о ее срабатывании
    private ACT stage5 = new ACT(); // Ступень 5 получающая данные о ее срабатывании
    private ACT stage6 = new ACT(); //  Ступень 6 получающая данные о ее срабатывании


    @Override
    public void process() {
        if (!Pos.getStVal().getValue()) {
            if (stage1.getGeneral().getValue() ||
                    stage2.getGeneral().getValue() ||
                    stage3.getGeneral().getValue() ||
                    stage4.getGeneral().getValue()) {
                if (!stVal.getStVal().getValue()) {
                    ctVal.getStVal().setValue(true);
                }
            }
        }else {
            ctVal.getStVal().setValue(false);
        }
    }

    public DPC getPos() {
        return Pos;
    }

    public void setPos(DPC pos) {
        Pos = pos;
    }

    public ACT getStage1() {
        return stage1;
    }

    public ACT getStage2() {
        return stage2;
    }

    public ACT getStage3() {
        return stage3;
    }


    public void setStage1(ACT stage1) {
        this.stage1 = stage1;
    }

    public void setStage2(ACT stage2) {
        this.stage2 = stage2;
    }

    public void setStage3(ACT stage3) {
        this.stage3 = stage3;
    }

    public ACT getStage4() {
        return stage4;
    }

    public void setStage4(ACT stage4) {
        this.stage4 = stage4;
    }

    public ACT getStage5() {
        return stage5;
    }

    public void setStage5(ACT stage5) {
        this.stage5 = stage5;
    }

    public ACT getStage6() {
        return stage6;
    }

    public void setStage6(ACT stage6) {
        this.stage6 = stage6;
    }

    public DPC getCtVal() {
        return ctVal;
    }

    public void setCtVal(DPC ctVal) {
        this.ctVal = ctVal;
    }

    public DPC getStVal() {
        return stVal;
    }

    public void setStVal(DPC stVal) {
        this.stVal = stVal;
    }
}
