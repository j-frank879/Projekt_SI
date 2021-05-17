package project_si;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleTest {
    @Test
    void firstTestToCheckIfThereIsNoErrors(){
        assertTrue(true);
    }


    @Test
    void inBinaryShouldBe1111FromDecimal(){
        int x = 15;
        Data data = new Data();
        String result = data.decimal_to_binary(x);

        assertTrue(result.equals("1111"));
    }
}
