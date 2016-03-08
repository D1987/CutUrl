package classes;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class QRCodeGeneratoeTest {

    //create qr code
    @Test
    public void testQrGeneration() throws Exception {
       File qr = QRCodeGeneratoe.qrGeneration("jkfhsdjk");
        assertTrue(qr.exists());
    }
}