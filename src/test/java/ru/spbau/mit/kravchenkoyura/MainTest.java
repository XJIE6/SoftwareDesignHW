package ru.spbau.mit.kravchenkoyura;

import org.junit.Test;
import ru.spbau.mit.kravchenkoyura.cmd.Pipeline;

import java.io.*;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testMain() throws Exception {
        String test = "echo 3";
        OutputStream out = new OutputStream() {
            private boolean ok = false;
            @Override
            public void write(int b) throws IOException {
                if (b == '\n') {
                    return;
                }
                assertEquals('3', b);
                if (ok) {
                    assertTrue(false);
                }
                ok = true;
            }
        };
        new Pipeline(Parser.parseCommands(Lexer.getVars(Lexer.getTokens(test), null))).eval(null, out, null);
    }
}