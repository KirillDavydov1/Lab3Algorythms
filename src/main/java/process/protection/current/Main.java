package process.protection.current;

import iec61850.nodes.custom.LSVC;
import iec61850.nodes.gui.NHMI;
import iec61850.nodes.gui.NHMIP;
import iec61850.nodes.gui.other.NHMIPoint;
import iec61850.nodes.gui.other.NHMISignal;
import iec61850.nodes.measurements.MMXU;
import iec61850.nodes.measurements.MSQI;
import iec61850.nodes.protection.PDIS;
import iec61850.nodes.protection.PTOC;
import iec61850.nodes.protection.RDIR;
import iec61850.nodes.switchCommand.CSWI;
import iec61850.nodes.switchCommand.RPSB;
import iec61850.nodes.switchStatus.XCBR;
import iec61850.objects.protection.ASG;
import iec61850.objects.protection.ING;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static iec61850.objects.protection.dir.Direction.BOTH;
import static iec61850.objects.protection.dir.Direction.FORWARD;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */

//Класс Main в котором происходит модуляция взаимодействия реальных узлов логическиими узлами
public class Main {

    public static void main(String[] args) {

        LSVC lsvc = new LSVC();
        NHMI nhmi = new NHMI();
        NHMIP nhmip = new NHMIP();
        MMXU mmxu = new MMXU();
        RPSB rpsb = new RPSB();

        PDIS pdis1 = new PDIS();
        PDIS pdis2 = new PDIS();
        PDIS pdis3 = new PDIS();
        PDIS pdis4 = new PDIS();

        CSWI cswi = new CSWI();
        XCBR xcbr = new XCBR();


        lsvc.readComtrade("C:\\Users\\1111\\IdeaProjects\\LABA3\\src\\main\\resources\\Опыты\\KZ1");

        // Передаем значения из узла LSVC(который отвечает за считывание из файлов) в узел MMXU
        mmxu.setInstUa(lsvc.getSignals().get(0));   //Напряжение фазы A
        mmxu.setInstUb(lsvc.getSignals().get(1));   //Напряжение фазы B
        mmxu.setInstUc(lsvc.getSignals().get(2));   //Напряжение фазы C

        mmxu.setInstIa(lsvc.getSignals().get(3));   //Ток фазы A
        mmxu.setInstIb(lsvc.getSignals().get(4));   //Ток фазы B
        mmxu.setInstIc(lsvc.getSignals().get(5));   //Ток фазы C

        rpsb.setZ(mmxu.getZ());
        rpsb.getUnBlkTmms().getSetVal().setValue(50);
        pdis1.setBlk(rpsb.getBlkZn());
        pdis2.setBlk(rpsb.getBlkZn());
        pdis3.setBlk(rpsb.getBlkZn());
        pdis4.setBlk(rpsb.getBlkZn());

        pdis1.setZ(mmxu.getZ());
        pdis1.getOpDITmms().getSetVal().setValue(100);
        pdis1.getPhStr().getSetMag().getF().setValue(50f);
        pdis1.setX0(25);
        pdis1.setY0(25);

        pdis2.setZ(mmxu.getZ());
        pdis2.getOpDITmms().getSetVal().setValue(600);
        pdis2.getPhStr().getSetMag().getF().setValue(60f);
        pdis2.setX0(30);
        pdis2.setY0(30);

        pdis3.setZ(mmxu.getZ());
        pdis3.getOpDITmms().getSetVal().setValue(600);
        pdis3.getPhStr().getSetMag().getF().setValue(144f);
        pdis3.setX0(0);
        pdis3.setY0(0);

        pdis4.setZ(mmxu.getZ());
        pdis4.getOpDITmms().getSetVal().setValue(1100);
        pdis4.getPhStr().getSetMag().getF().setValue(171f);
        pdis4.setX0(0);
        pdis4.setY0(0);


        cswi.setStage1(pdis1.getOp());
        cswi.setStage2(pdis2.getOp());
        cswi.setStage3(pdis3.getOp());
        cswi.setStage4(pdis4.getOp());


        cswi.setStVal(xcbr.getPos());
        xcbr.setCtVal(cswi.getCtVal());

        nhmip.addSignals(new NHMISignal("Za",
                mmxu.getZ().getPhsA().getcVal().getX().getF(),
                mmxu.getZ().getPhsA().getcVal().getY().getF()));
        nhmip.addSignals(new NHMISignal("Zb", mmxu.getZ().getPhsB().getcVal().getX().getF(), mmxu.getZ().getPhsB().getcVal().getY().getF()));
        nhmip.addSignals(new NHMISignal("Zc", mmxu.getZ().getPhsC().getcVal().getX().getF(), mmxu.getZ().getPhsC().getcVal().getY().getF()));

        List<NHMIPoint<Double, Double>> points = new ArrayList<>();

        double r = 50, x0 = 25, y0 = 25;
        nhmip.drawCharacteristic("Круговая1", nhmip.fillingList(r, x0, y0));

        nhmip.drawCharacteristic("Круговая2", nhmip.fillingList(60, 30, 30));

        nhmip.drawCharacteristic("Круговая3", nhmip.fillingList(144, 0, 0));

        nhmip.drawCharacteristic("Круговая4", nhmip.fillingList(171, 0, 0));

        nhmi.addSignals(new NHMISignal("Ток фазы A", lsvc.getSignals().get(3).getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("Ток фазы B", lsvc.getSignals().get(4).getInstMag().getF()));
        nhmi.addSignals(new NHMISignal("Ток фазы B", lsvc.getSignals().get(5).getInstMag().getF()));

//        nhmi.addSignals(new NHMISignal("Сопротивление Ra", mmxu.getZ().getPhsA().getcVal().getX().getF()));
//        nhmi.addSignals(new NHMISignal("Сопротивление Xa", mmxu.getZ().getPhsA().getcVal().getY().getF()));
//        nhmi.addSignals(new NHMISignal("Сопротивление Rb", mmxu.getZ().getPhsB().getcVal().getX().getF()));
//        nhmi.addSignals(new NHMISignal("Сопротивление Xb", mmxu.getZ().getPhsB().getcVal().getY().getF()));
//        nhmi.addSignals(new NHMISignal("Сопротивление Rc", mmxu.getZ().getPhsC().getcVal().getX().getF()));
//        nhmi.addSignals(new NHMISignal("Сопротивление Xc", mmxu.getZ().getPhsC().getcVal().getY().getF()));


        nhmi.addSignals(new NHMISignal("CSWI", cswi.getStVal().getStVal()));
        nhmi.addSignals(new NHMISignal("XCBR", xcbr.getPos().getStVal()));

        while (lsvc.hasNext()) {

            lsvc.process();
            nhmip.process();
            nhmi.process();
            mmxu.process();
            rpsb.process();
            pdis1.process();
            pdis2.process();
            pdis3.process();
            pdis4.process();
            cswi.process();
            xcbr.process();

        }

    }


}
