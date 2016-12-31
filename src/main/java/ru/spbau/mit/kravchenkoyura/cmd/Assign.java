package ru.spbau.mit.kravchenkoyura.cmd;


import java.io.*;
import java.util.List;
import java.util.Map;
/**
 * команда присвоения
 */
public class Assign extends Cmd {

    private String varName;

    /**
     * @param varName - имя переменной, которой мы присваиваем значение
     */
    public Assign(List<String> params, String varName) {
        super(params);
        this.varName = varName;
    }
    /**
     * присваивает переменной с именем varName значение первого параметра
     */
    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        env.put(varName, params.get(0));
    }
}
