package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;
/**
 * команда выводит текущую директорию
 */
public class Pwd extends Cmd {

    public Pwd(List<String> params) {
        super(params);
    }
    /**
     * System.getProperty("user.dir") возвращает название текущей директории
     */
    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println(System.getProperty("user.dir"));
        printWriter.flush();
    }
}
