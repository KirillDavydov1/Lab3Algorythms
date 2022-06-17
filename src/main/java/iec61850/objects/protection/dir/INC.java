package iec61850.objects.protection.dir;
// Класс устанавливающий целочисленное управление и состояние
public class INC {

    private int stVal;

    public int getStVal() {
        return stVal;
    }

    public void setStVal(int stVal) {
        this.stVal = stVal++;
    }
}
