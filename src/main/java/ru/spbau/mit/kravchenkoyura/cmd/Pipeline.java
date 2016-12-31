package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * команда предназначена для передачи результатов одной команды на вход другой
 */
public class Pipeline extends Cmd {
    private List<Cmd> cmd;

    public Pipeline(List<Cmd> cmd) {
        super(null);
        this.cmd = cmd;
    }

    /**
     * команды соединяются одна за другой с помощью PipedInputStream и PipedOutputStream
     */
    @Override
    public void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        if (cmd.size() == 0) {
            return;
        }
        InputStream currentIn = in;
        for (int i = 0; i < cmd.size() - 1; i++) {
            PipedOutputStream currentOut = new PipedOutputStream();
            InputStream newIn = new PipedInputStream(currentOut);
            cmd.get(i).eval(currentIn, currentOut, env);
            currentOut.close();
            currentIn = newIn;
        }
        cmd.get(cmd.size() - 1).eval(currentIn, out, env);
        out.flush();
    }
}
