import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ifttt.utils.JaninoExpressUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * @author praveenkamath
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JaninoTest {

    @Test
    public void testJaninoUtilIntegerBooleanFalse() {
        String key = "bmi";
        String expression = "a/(b * b * 0.0001) >= 0 && a/(b * b * 0.0001) <= 34.99";
        String variables[] = {"a", "b"};

        JsonNode params = new ObjectMapper().createObjectNode();
        ((ObjectNode) params).put("a", "INTEGER");
        ((ObjectNode) params).put("b", "INTEGER");

        Object subs[] = {300, 160};
        try {
            assertFalse((boolean) JaninoExpressUtils.brew(key, expression, variables, params, subs));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testJaninoUtilIntegerBooleanTrue() {
        String key = "bmi";
        String expression = "a/(b * b * 0.0001) >= 0 && a/(b * b * 0.0001) <= 34.99";
        String variables[] = {"a", "b"};

        JsonNode params = new ObjectMapper().createObjectNode();
        ((ObjectNode) params).put("a", "INTEGER");
        ((ObjectNode) params).put("b", "INTEGER");

        Object subs[] = {60, 160};
        try {
            boolean op = (boolean) JaninoExpressUtils.brew(key, expression, variables, params, subs);
            assertTrue(op);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testJaninoUtilStringEquals() {
        String key = "string_conc_string_equals";
        String expression = "a + b + c + d";
        String variables[] = {"a", "b", "c", "d"};

        JsonNode params = new ObjectMapper().createObjectNode();
        ((ObjectNode) params).put("a", "STRING");
        ((ObjectNode) params).put("b", "STRING");
        ((ObjectNode) params).put("c", "STRING");
        ((ObjectNode) params).put("d", "STRING");


        Object subs[] = {"p", "r", "a", "v"};
        try {
            String op = (String) JaninoExpressUtils.brew(key, expression, variables, params, subs);
            assertEquals("prav", op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJaninoStringNotEquals() {
        String key = "string_conc_string_not_equals";
        String expression = "a + b + c + d";
        String variables[] = {"a", "b", "c", "d"};

        JsonNode params = new ObjectMapper().createObjectNode();
        ((ObjectNode) params).put("a", "STRING");
        ((ObjectNode) params).put("b", "STRING");
        ((ObjectNode) params).put("c", "STRING");
        ((ObjectNode) params).put("d", "STRING");


        Object subs[] = {"p", "r", "a", "v"};
        try {
            String op = (String) JaninoExpressUtils.brew(key, expression, variables, params, subs);
            assertNotEquals("pr", op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJaninoUtilStringBooleanTrue() {
        String key = "string_conc_boolean_true";
        String expression = "(a + b + c + d).equalsIgnoreCase(\"prav\")";
        String variables[] = {"a", "b", "c", "d"};

        JsonNode params = new ObjectMapper().createObjectNode();
        ((ObjectNode) params).put("a", "STRING");
        ((ObjectNode) params).put("b", "STRING");
        ((ObjectNode) params).put("c", "STRING");
        ((ObjectNode) params).put("d", "STRING");


        Object subs[] = {"p", "r", "a", "v"};
        try {
            boolean op = (boolean) JaninoExpressUtils.brew(key, expression, variables, params, subs);
            assertTrue(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJaninoUtilStringBooleanFalse() {
        String key = "string_conc_boolean_false";
        String expression = "(a + b + c + d).equalsIgnoreCase(\"pqrs\")";
        String variables[] = {"a", "b", "c", "d"};

        JsonNode params = new ObjectMapper().createObjectNode();
        ((ObjectNode) params).put("a", "STRING");
        ((ObjectNode) params).put("b", "STRING");
        ((ObjectNode) params).put("c", "STRING");
        ((ObjectNode) params).put("d", "STRING");


        Object subs[] = {"p", "r", "a", "v"};
        try {
            boolean op = (boolean) JaninoExpressUtils.brew(key, expression, variables, params, subs);
            assertFalse(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJaninoUtilStringIntBooleanTrue() {
        String key = "string_int_conc_boolean_true";
        String expression = "(a + b + c + d).equalsIgnoreCase(\"pra10\")";
        String variables[] = {"a", "b", "c", "d"};

        JsonNode params = new ObjectMapper().createObjectNode();
        ((ObjectNode) params).put("a", "STRING");
        ((ObjectNode) params).put("b", "STRING");
        ((ObjectNode) params).put("c", "STRING");
        ((ObjectNode) params).put("d", "INTEGER");


        Object subs[] = {"p", "r", "a", 10};
        try {
            boolean op = (boolean) JaninoExpressUtils.brew(key, expression, variables, params, subs);
            assertTrue(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
