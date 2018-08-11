import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifttt.abstracts.impl.RuleEngineJsonImpl;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

/**
 * @author praveenkamath
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RulesTest {

    @Test
    public void testJsonImplRuleSuccess() throws Exception {
        String jsonString = "{ \"ifall\" : [ { \"id\" : \"SUMI\", \"fact\" : \"sumInsured\", \"operator\" : \"EQUALS\", \"value\" : 200000.0 }, { \"id\" : \"ADULTC\", \"fact\" : \"adultCount\", \"operator\" : \"EQUALS\", \"value\" : 1.0 }, { \"id\" : \"CHILDC\", \"fact\" : \"childCount\", \"operator\" : \"EQUALS\", \"value\" : 3.0 } ], \"then\" : { \"return\" : { \"matched\" : true, \"data\" : { \"code\" : 1234.0 } } } }";
        JsonNode ruleNode = new ObjectMapper().readValue(jsonString, JsonNode.class);
        Map<String, Object> facts = new HashMap<>();
        facts.put("sumInsured", 200000);
        facts.put("adultCount", 1);
        facts.put("childCount", 3);
        JsonNode result = execute(ruleNode, facts);
        System.out.println("Op :: "  + result);
        assertTrue(result.get("op").get("return").get("matched").asBoolean());
    }

    @Test
    public void testJsonImplRuleFailure() throws Exception {
        String jsonString = "{ \"ifall\" : [ { \"id\" : \"SUMI\", \"fact\" : \"sumInsured\", \"operator\" : \"EQUALS\", \"value\" : 300000.0 }, { \"id\" : \"ADULTC\", \"fact\" : \"adultCount\", \"operator\" : \"EQUALS\", \"value\" : 1.0 }, { \"id\" : \"CHILDC\", \"fact\" : \"childCount\", \"operator\" : \"EQUALS\", \"value\" : 3.0 } ], \"then\" : { \"return\" : { \"matched\" : true, \"data\" : { \"code\" : 1234.0 } } } }";
        JsonNode ruleNode = new ObjectMapper().readValue(jsonString, JsonNode.class);
        Map<String, Object> facts = new HashMap<>();
        facts.put("sumInsured", 200000);
        facts.put("adultCount", 1);
        facts.put("childCount", 3);
        JsonNode result = execute(ruleNode, facts);
        System.out.println("Op :: "  + result);
        assertEquals(null, result.get("op").get("return"));
    }

    private JsonNode execute(JsonNode ruleNode, Map<String, Object> facts) throws Exception {
        return new RuleEngineJsonImpl().runThis(ruleNode).against(facts).execute();
    }
}
