package ru.spbau.mit.kravchenkoyura.cmd;


import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Cat extends Cmd {

    public Cat(List<String> params) {
        super(params);
    }

    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        if (params.size() > 0) {
            in = new FileInputStream(new File(params.get(0)));
        }
        IOUtils.copy(in, out);
    }
}
