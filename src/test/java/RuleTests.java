import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifttt.abstracts.impl.RuleEngineBooleanImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

/**
 * @author praveenkamath
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RuleTests {

    @Test
    public void rule_test() {
        String rule = "{ \"all\" : [ { \"id\" : \"BMI\", \"operator\" : \"EXPRESSION\", \"fact\" : \"bmi\", \"params\" : \"a,b\", \"paramTypes\" : { \"a\" : \"INTEGER\", \"b\" : \"INTEGER\" }, \"exp\" : \"a/(b * b * 0.0001) >= 18 && a/(b * b * 0.0001) < 35\" } ] }";

        Map<String, Object> facts = new HashMap<>();
        Integer[] bmi = {50, 160};
        facts.put("bmi", bmi);

        try {
            boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
            System.out.println(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rule_test_hdfc() {
        String rule = "{ \"any\" : [ { \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"EQUALS\", \"value\" : 1 }, { \"fact\" : \"childCount\", \"operator\" : \"EQUALS\", \"value\" : 0 }, { \"fact\" : \"sumInsured\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 500000 } ] }, { \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"EQUALS\", \"value\" : 1 }, { \"fact\" : \"childCount\", \"operator\" : \"GREATER_THAN\", \"value\" : 0 }, { \"fact\" : \"sumInsured\", \"operator\" : \"NOT_EQUALS\", \"value\" : 300000 }, { \"any\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 500000 }, { \"all\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"EQUALS\", \"value\" : 400000 }, { \"fact\" : \"city\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"NEW DELHI\", \"South West Delhi\", \"North West\", \"North Delhi\", \"New Delhi\", \"North East\", \"East Delhi\", \"West Delhi\", \"Central Delhi\", \"Delhi\", \"South Delhi\", \"Gautam Buddha Nagar\", \"GAUTAM BUDDHA NAGAR\", \"GHAZIABAD\", \"Ghaziabad\", \"GHAZIPUR\", \"FARIDABAD\", \"GURGAON\", \"Faridabad\", \"Gurgaon\" ] } ] } ] } ] }, { \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 2 }, { \"any\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 500000 }, { \"all\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"LESSER_THAN\", \"value\" : 500000 }, { \"fact\" : \"city\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"NEW DELHI\", \"South West Delhi\", \"North West\", \"North Delhi\", \"New Delhi\", \"North East\", \"East Delhi\", \"West Delhi\", \"Central Delhi\", \"Delhi\", \"South Delhi\", \"Gautam Buddha Nagar\", \"GAUTAM BUDDHA NAGAR\", \"GHAZIABAD\", \"Ghaziabad\", \"GHAZIPUR\", \"FARIDABAD\", \"GURGAON\", \"Faridabad\", \"Gurgaon\" ] } ] } ] } ] } ] }";

        Map<String, Object> facts = new HashMap<>();
        facts.put("sumInsured", 400000);
        facts.put("city", "Mumbai");
        facts.put("adultCount", 1);
        facts.put("childCount", 1);

        try {
            boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
            System.out.println(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
