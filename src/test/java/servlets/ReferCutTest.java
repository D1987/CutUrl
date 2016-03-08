package servlets;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ReferCutTest {

    //inspect existance long ref
    @Test
    public void testProverkaExLongRef() throws Exception {
        ReferCut r = new ReferCut();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Map<Integer, Integer> b = r.provRef("http://devcolibri.com/1250");
        assertEquals(map, b);
    }

    //inspect regex long ref
    @Test
    public void testProverkaRegexLongRef() throws Exception {
        ReferCut r = new ReferCut();
        boolean b = r.provURL("https://htmlacademy.ru/courses/42/run/18");
        assertEquals(true, b);
    }

    //inspect length cut reference (not more 22 symbol for localhost:81/by/......)
    @Test
    public void testProverkaMail() throws Exception {
        ReferCut r = new ReferCut();
        int b = r.sokrashtel().length();
        assertEquals(22, b);
    }

}