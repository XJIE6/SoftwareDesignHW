package ru.spbau.mit.kravchenkoyura.cmd;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GrepTest {

    @Test
    public void testEval() throws Exception {
        Grep grep = Grep.create(Arrays.asList("-i", "heLLow"));
        String input = "hi, Hellow \n i'm fine \n abracahellowdabra \nheLLow hellow \n\n what now\n end\n";
        String res = "hi, Hellow \n abracahellowdabra \nheLLow hellow \n";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        OutputStream out = new OutputStream() {
            private byte[] bytes = new byte[1000];
            private int size = 0;
            @Override
            public void write(int b) throws IOException {
                bytes[size] = (byte)b;
                size++;
            }
            @Override
            public String toString() {
                return new String(Arrays.copyOf(bytes, size));
            }
        };
        grep.eval(in, out, null);
        assertEquals(res, out.toString());
    }
}