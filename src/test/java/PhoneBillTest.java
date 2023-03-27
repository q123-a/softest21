import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.*;
import java.text.ParseException;

public class PhoneBillTest {
    PhoneBill phoneBill = new PhoneBill();
    @ParameterizedTest
    @CsvFileSource(resources = "/time.csv")
    void test(String a, String b,String c) throws ParseException {
        assertEquals(c, phoneBill.getBill(a,b));
    }

}
