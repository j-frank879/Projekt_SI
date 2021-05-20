package project_si;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Individual {

    int adaptation;
    String in;
    int x;
    int y;

    Individual(Individual o) {
        this.adaptation = o.adaptation;
        this.in = o.in;
        this.x = o.x;
        this.y = o.y;
    }

    Individual(int a, String s, int x1, int y1) {
        this.adaptation = a;
        this.in = s;
        this.x = x1;
        this.y = y1;
    }

    Individual() {
    }

    //calculating adaptation
    void adaptation(String twoVariablesFunction) {


        Expression e = new ExpressionBuilder(twoVariablesFunction)
                .variables("x", "y")
                .build()
                .setVariable("x", x)
                .setVariable("y", y);

        this.adaptation = (int) e.evaluate();
    }

}
