package iec61850.objects.samples;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */
// Класс для обертки любых типов атрибутов(данных)
public class Attribute<T> {

    private T value;

    public Attribute(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
