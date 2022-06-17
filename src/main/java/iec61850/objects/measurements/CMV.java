package iec61850.objects.measurements;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */

// Класс комплексных значений
public class CMV {

    private Vector cVal = new Vector();

    public Vector getcVal() {
        return cVal;
    }

    public void setcVal(Vector cVal) {
        this.cVal = cVal;
    }
}
