package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Pwd extends Cmd {

    public Pwd(List<String> params) {
        super(params);
    }

    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(System.getProperty("user.dir"));
        printWriter.flush();
    }
}
