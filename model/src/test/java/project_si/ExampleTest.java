package project_si;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
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
        Data data = new Data.Builder().build();
        String result = data.decimal_to_binary(x);

        assertTrue(result.equals("1111"));
    }

    @Test
    void Library(){
        String equation = "2cos(xy)";

        Expression e = new ExpressionBuilder(equation)
                .variables("x", "y")
                .build()
                .setVariable("x", 0.5d)
                .setVariable("y", 0.25d);
        double result = e.evaluate();

        assertEquals(2d * Math.cos(0.5d * 0.25d), result);
    }
}
