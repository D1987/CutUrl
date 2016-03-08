package servlets;

import classes.References;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserDataTest extends Mockito{

    //select person data for person cabinet
    @Test
    public void testVuborkaPersonData() throws ServletException, Exception {
        Map<Integer, References> map = new HashMap<Integer, References>();
        Map<Integer, References> map1 = new HashMap<Integer, References>();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute("id")).thenReturn("78");
        map1 = new UserData().vuborkaPersonData(request);
        assertNotSame(map,map1);
    }
}