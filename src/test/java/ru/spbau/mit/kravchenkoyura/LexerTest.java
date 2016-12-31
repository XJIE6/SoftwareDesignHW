package ru.spbau.mit.kravchenkoyura;

import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.kravchenkoyura.Lexer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void testGetTokens() throws Exception {
        Assert.assertEquals(Lexer.getTokens("echo 2"), Arrays.asList("echo", "2"));
        assertEquals(Lexer.getTokens("echo \"2\""), Arrays.asList("echo", "\"", "2", "\""));
        assertEquals(Lexer.getTokens("cat $abc | wc"), Arrays.asList("cat", "$", "abc", "|", "wc"));
    }

    @Test
    public void testGetVars() throws Exception {
        Map<String, String> env = new HashMap<>();
        env.put("a", "pwd");

        assertEquals(Lexer.getVars(Arrays.asList("echo", "2"), env), Arrays.asList("echo", "2"));
        assertEquals(Lexer.getVars(Arrays.asList("$", "a"), env), Arrays.asList("pwd"));
        assertEquals(Lexer.getVars(Arrays.asList("echo", "'", "$", "a", "'"), env), Arrays.asList("echo", "$a"));
        assertEquals(Lexer.getVars(Arrays.asList("echo", "\"", "$", "a", "\""), env), Arrays.asList("echo", "\"", "pwd", "\""));
    }
}