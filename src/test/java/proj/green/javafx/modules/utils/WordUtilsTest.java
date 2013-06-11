package proj.green.javafx.modules.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class WordUtilsTest {
    
    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testConvertFull2half() {
        String text = "aAＢb！!2２　 ";
        
        String actual = WordUtils.convertFull2half(text);
        assertEquals("aABb!!22  ", actual);
    }
    
    @Test
    public void testConvertHalf2Full() {
        String text = "aAＢb！!2２　 ";
        
        String actual = WordUtils.convertHalf2Full(text);
        assertEquals("ａＡＢｂ！！２２　　", actual);

    }
    
    @Test
    public void testWrapText() {
        String text1 = "1234567890123456789012345678901";
        String actual1 = WordUtils.wrapText(text1, 50);
        assertEquals(text1, actual1);
        
        String text2 = "1234567890123456789012345678901";
        String actual2 = WordUtils.wrapText(text2, 10);
        String expect2 = text2.replaceAll("0", "0" + System.lineSeparator());
        assertEquals(expect2, actual2);
    }
}
