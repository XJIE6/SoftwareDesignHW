package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Exec extends Cmd{

    private String name;

    public Exec(List<String> params, String name) {
        super(params);
        this.name = name;
    }

    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        Runtime.getRuntime().exec(name, params.toArray(new String[params.size()]));
    }
}
