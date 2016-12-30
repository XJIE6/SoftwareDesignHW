package ru.spbau.mit.kravchenkoyura.cmd;


import java.io.*;
import java.util.List;
import java.util.Map;

public class Assign extends Cmd {

    private String varName;

    public Assign(List<String> params, String varName) {
        super(params);
        this.varName = varName;
    }

    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        env.put(varName, params.get(0));
    }
}
