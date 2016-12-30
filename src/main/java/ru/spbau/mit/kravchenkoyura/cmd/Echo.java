package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Echo extends Cmd {

    public Echo(List<String> params) {
        super(params);
    }

    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(params.get(0));
        printWriter.flush();
    }
}
