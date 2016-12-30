package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Pipeline extends Cmd {
    private List<Cmd> cmd;

    public Pipeline(List<Cmd> cmd) {
        super(null);
        this.cmd = cmd;
    }

    @Override
    public void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
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
