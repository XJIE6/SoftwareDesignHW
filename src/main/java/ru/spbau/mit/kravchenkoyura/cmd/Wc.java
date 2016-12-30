package ru.spbau.mit.kravchenkoyura.cmd;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Wc extends Cmd{

    public Wc(List<String> params) {
        super(params);
    }

    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        if (params.size() > 0) {
            in = new FileInputStream(new File(params.get(0)));
        }
        Scanner scanner = new Scanner(in);
        PrintWriter printWriter = new PrintWriter(out);
        int lines = 0, words = 0;
        int bytes = in.available();
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            lines++;
            words += line.split(" ").length;
        }
        printWriter.print(lines);
        printWriter.print(' ');
        printWriter.print(words);
        printWriter.print(' ');
        printWriter.println(bytes);
        printWriter.flush();
    }
}
