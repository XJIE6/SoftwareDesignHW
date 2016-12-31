package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* базовый класс для всех команд*/

public abstract class Cmd {
    /*конструктор принимает и сохраняет параметры команды*/
    public Cmd(List<String> params) {
        this.params = params;
    }

    protected List<String> params = new ArrayList<>();
    /*запускает выполнение этой команды
    * входными данными являются InputStream in и params*/
    abstract void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException;

}


