package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;
/**
 * команда выводит свои параметры
 */
public class Echo extends Cmd {

    public Echo(List<String> params) {
        super(params);
    }

    /**
     * @param out - сюда выводятся параметры
     */
    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(String.join(" ", params));
        printWriter.flush();
    }
}
