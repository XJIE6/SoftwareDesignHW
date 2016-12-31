package ru.spbau.mit.kravchenkoyura;

import ru.spbau.mit.kravchenkoyura.cmd.Cmd;
import ru.spbau.mit.kravchenkoyura.cmd.Pipeline;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Map<String, String> env = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String current = scanner.nextLine();
        while (!current.equals("exit")) {
            try {
                List<String> tokens = Lexer.getTokens(current);
                List<String> tokensWithoutVars = Lexer.getVars(tokens, env);
                List<Cmd> commands = Parser.parseCommands(tokensWithoutVars);
                new Pipeline(commands).eval(System.in, System.out, env);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            current = scanner.nextLine();
        }
    }
}
