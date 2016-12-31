package ru.spbau.mit.kravchenkoyura.cmd;

import com.beust.jcommander.Parameter;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Grep extends Cmd {
    public Grep(List<String> params) {
        super(params);
    }

    @Parameter(names = "-i")
    private boolean caseInsensitive = false;

    @Parameter(names = "-w")
    private boolean wholeWord = false;

    @Parameter(names = "-A")
    private Integer linesAfterMatch = 0;

    @Override
    void eval(InputStream in, OutputStream out, Map<String, String> env) throws IOException {
        if (params.size() == 0) {
            throw new IOException("Too few params for grep");
        }
        if (params.size() > 1) {
            in = new FileInputStream(new File(params.get(0)));
        }
        String regex;
        if (wholeWord) {
            regex = ".*\\b" + params.get(0) + "\\b.*";
        } else {
            regex = ".*" + params.get(0) + ".*";
        }
        Pattern pattern;
        if (caseInsensitive) {
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(regex);
        }
        Scanner scanner = new Scanner(in);
        PrintWriter printWriter = new PrintWriter(out);

        int linesToWrite = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (pattern.matcher(line).matches()) {
                linesToWrite = linesAfterMatch + 1;
            }
            if (linesToWrite > 0) {
                printWriter.println(line);
            }
            linesToWrite--;
        }
        printWriter.flush();
    }

}