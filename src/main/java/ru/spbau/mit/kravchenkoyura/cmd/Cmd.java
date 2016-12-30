package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Cmd {

    public Cmd(List<String> params) {
        this.params = params;
    }

    protected List<String> params = new ArrayList<>();

    abstract void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException;

}


