package ru.spbau.mit.kravchenkoyura;

import java.io.IOException;
import java.util.*;


public class Lexer {
    private static boolean check(Character c) {
        return c == '\'' || c == '"' || c == '$' || c == '|' || c == '=';
    }
    /**
     * @param line - входная строка
     * @return - список строк, разбитых по пробелу и системным символам
     */
    public static List<String> getTokens(String line) {
        List<String> tokens = new ArrayList<>();
        String[] words = line.split(" ");
        for (String word : words) {
            String token = "";
            for (Character letter: word.toCharArray()) {
                if (check(letter)) {
                    tokens.add(token);
                    tokens.add(letter.toString());
                    token = "";
                }
                else {
                    token += letter;
                }
            }
            tokens.add(token);
        }
        List<String> res =  new ArrayList<>();
        for (String token : tokens) {
            if (!token.equals("") && !token.equals(" ")) {
                res.add(token);
            }
        }
        return res;
    }
    /**
     * @param tokens - список строк
     * @return - заменены строки вида "$", "abc" на значение abc или на "$abc", если они находятся в одинарных ковычках
     */
    public static List<String> getVars(List<String> tokens, Map<String, String> env) throws IOException {
        List<String> res = new ArrayList<>();

        boolean inQuote = false;
        boolean varName = false;
        for (String token : tokens) {
            switch (token) {
                case "'":
                    inQuote = !inQuote;
                    break;
                case "$":
                    varName = true;
                    break;
                default:
                    if (varName) {
                        if (inQuote) {
                            res.add("$" + token);
                        }
                        else {
                            if (env.get(token) != null) {
                                res.addAll(getTokens(env.get(token)));
                            }
                            else {
                                throw new IOException(token + ": no such variable");
                            }
                        }
                        varName = false;
                    }
                    else {
                        res.add(token);
                    }
            }
        }
        return res;
    }
}
