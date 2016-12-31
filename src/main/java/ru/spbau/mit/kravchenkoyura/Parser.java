package ru.spbau.mit.kravchenkoyura;

import ru.spbau.mit.kravchenkoyura.cmd.*;

import java.io.IOException;
import java.util.*;


public class Parser {
    private static int index;
    /*метод выбирает параметры команды до первого пайплайна*/
    private static List<String> parseArgs(List<String> tokens) throws IOException {
        List<String> args = new ArrayList<>();
        List<String> argsInQuote = new ArrayList<>();
        boolean inQuote = false;
        while (!tokens.get(index).equals("|")) {
            if (tokens.get(index).equals("'") || tokens.get(index).equals("\"")) {
                inQuote = !inQuote;
                if (!inQuote) {
                    args.add(String.join("", argsInQuote));
                    argsInQuote.clear();
                }
            }
            else {
                if (inQuote) {
                    argsInQuote.add(tokens.get(index));
                }
                else {
                    args.add(tokens.get(index));
                }
            }
            index++;
        }
        if (argsInQuote.size() != 0) {
            args.add(String.join(" ", argsInQuote));
        }
        return args;
    }
    /*метод выбирает первую строку как команду, запускает получение её параметров и возвращает результирующую команду
    * с набором параметров*/
    private static Cmd parseCmd(List<String> tokens) throws IOException {
        String cmdName = tokens.get(index);
        index++;

        if (tokens.get(index).equals("=")) {
            index++;
            List<String> args = parseArgs(tokens);
            return new Assign(args, cmdName);
        }

        List<String> args = parseArgs(tokens);
        switch (cmdName) {
            case "cat":
                return new Cat(args);
            case "echo":
                return new Echo(args);
            case "pwd":
                return new Pwd(args);
            case "wc":
                return new Wc(args);
            default:
                return new Exec(args, cmdName);
        }
    }

    /*возврящает список команд, отделённых пайплайном*/
    public static List<Cmd> parseCommands(List<String> tokens) throws IOException {
        List<Cmd> cmds = new ArrayList<>();
        if (tokens.size() == 0) {
            return cmds;
        }
        index = 0;
        tokens.add("|");
        while (index < tokens.size()) {
            Cmd command = parseCmd(tokens);
            if (!tokens.get(index).equals("|")) {
                throw new IOException("Pipeline needed");
            }
            index++;
            cmds.add(command);
        }
        return cmds;
    }
}
