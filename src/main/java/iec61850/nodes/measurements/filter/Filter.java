package iec61850.nodes.measurements.filter;

import iec61850.objects.measurements.Vector;
import iec61850.objects.samples.SAV;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */
// Абстрактный класс фильтр ( от него наследуем различные фильтры Fourier,RMS,MSD)
public abstract class Filter {

    public abstract void process(SAV sav, Vector vector);

}
