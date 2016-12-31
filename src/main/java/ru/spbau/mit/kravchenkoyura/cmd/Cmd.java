package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * базовый класс для всех команд
 */

public abstract class Cmd {
    /**
     * @param params - параметры команды
     */
    public Cmd(List<String> params) {
        this.params = params;
    }

    protected List<String> params = new ArrayList<>();

    /**
     * запускает выполение команды
     * @param in - является входными данными как и параметры
     */
    abstract void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException;

}


