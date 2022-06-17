package iec61850.nodes.common;

/**
 * @author Александр Холодов
 * @created 03.2022
 * @project Programming2022
 * @description
 */
//Абстрактный класс от которого наследуются все узлы
public abstract class LN {

    /** Выполнить функцию РЗА */
    public abstract void process();
}
