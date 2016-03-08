package classes;

import org.junit.Test;
import servlets.UserData;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ReferencesTest {

    //inspect map na null
    @Test
    public void testShowAllRefer() throws Exception {
        Map<Integer, References> map = new HashMap<Integer, References>();
        map = new References().showAllRefer();
        assertNotNull(map);
    }
}