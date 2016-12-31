package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;
/*команда выводит свои параметры */
public class Echo extends Cmd {

    public Echo(List<String> params) {
        super(params);
    }
    /*выводит в OutputStream*/
    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(String.join(" ", params));
        printWriter.flush();
    }
}
