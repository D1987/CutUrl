package classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class MailServiceTest {

    //get domain 2 level
    @Test
    public void testGetDomaim() throws Exception {
        MailService mailSer = new MailService();
        String domain = mailSer.parsingMail("Legenda2010@yandex.ru");
        assertEquals("yandex.ru",domain);
    }
}