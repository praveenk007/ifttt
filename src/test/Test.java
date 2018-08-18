import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifttt.abstracts.impl.RuleEngineBooleanImpl;
import org.codehaus.janino.ExpressionEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author praveenkamath
 **/
public class Test {

    public static void main(String[] args) {
        String json = "{ \"all\" : [ { \"id\" : \"BMI\", \"operator\" : \"EXPRESSION\", \"fact\" : \"bmi\", \"params\" : \"a,b\", \"exp\"  : \"a/(b * b * 0.0064516) >= 0 && a/(b * b * 0.00064516) < 34.99\" } ] }";
        try {
            JsonNode jsonNode = new ObjectMapper().readValue(json, JsonNode.class);
            Map<String, Object> facts = new HashMap<>();
            facts.put("planId", "VLHCB");
            facts.put("portability", false);
            facts.put("sumInsured", 500000);
            facts.put("deductible", 0);
            facts.put("pincode", "400601");
            facts.put("city", "Thane");
            facts.put("state", "Maharashtra");
            Integer ints[] = {150, 78};
            facts.put("bmi", ints);
            System.out.println(new RuleEngineBooleanImpl().runThis(jsonNode).against(facts).execute());
        } catch (Exception e) {
            e.printStackTrace();
        }





        /*String expr = "a/(b * b * 0.0064516) >= 0 && a/(b * b * 0.0064516) < 34.99";
        ExpressionEvaluator ee1 = new ExpressionEvaluator();
        ee1.setParameters(new String[] { "a", "b" }, new Class[] { int.class, int.class });

        try {
            ee1.cook(expr);
            System.out.println("here :: " + ee1.evaluate(new Object[] { 60, 200 }));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
