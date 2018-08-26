import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ExpressionEvaluator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author praveenkamath
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestIfttt {

    @Test
    public void testJanino() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            String s[] = {"s", "a"};
            evaluator.setParameters(new String[] {"a","b"}, new Class[] { String.class, String.class });
            evaluator.cook("a + b");
            System.out.println(evaluator.evaluate(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
