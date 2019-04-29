import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ifttt.abstracts.impl.RuleEngineBooleanImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

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

    @Test
    public void rule_test_hdfc_1() {
        final String rule = "{ \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"GREATER_THAN\", \"value\" : 0 }, { \"fact\" : \"age\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 50 }, { \"any\" : [ { \"fact\" : \"userType\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"son\", \"son1\", \"son2\", \"son3\", \"son4\", \"daughter\", \"daughter1\", \"daughter2\", \"daughter3\", \"daughter4\" ] }, { \"fact\" : \"age\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 21 } ] }, { \"any\" : [ { \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"EQUALS\", \"value\" : 1.0 }, { \"fact\" : \"childCount\", \"operator\" : \"EQUALS\", \"value\" : 0.0 }, { \"fact\" : \"sumInsured\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 500000.0 } ] }, { \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"EQUALS\", \"value\" : 1.0 }, { \"fact\" : \"childCount\", \"operator\" : \"GREATER_THAN\", \"value\" : 0.0 }, { \"fact\" : \"sumInsured\", \"operator\" : \"NOT_EQUALS\", \"value\" : 300000.0 }, { \"any\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 500000.0 }, { \"all\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"EQUALS\", \"value\" : 400000.0 }, { \"fact\" : \"city\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"NEW DELHI\", \"South West Delhi\", \"North West\", \"North Delhi\", \"New Delhi\", \"North East\", \"East Delhi\", \"West Delhi\", \"Central Delhi\", \"Delhi\", \"South Delhi\", \"Gautam Buddha Nagar\", \"GAUTAM BUDDHA NAGAR\", \"GHAZIABAD\", \"Ghaziabad\", \"GHAZIPUR\", \"FARIDABAD\", \"GURGAON\", \"Faridabad\", \"Gurgaon\" ] } ] } ] } ] }, { \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 2.0 }, { \"any\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 500000.0 }, { \"all\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"LESSER_THAN\", \"value\" : 500000.0 }, { \"fact\" : \"city\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"NEW DELHI\", \"South West Delhi\", \"North West\", \"North Delhi\", \"New Delhi\", \"North East\", \"East Delhi\", \"West Delhi\", \"Central Delhi\", \"Delhi\", \"South Delhi\", \"Gautam Buddha Nagar\", \"GAUTAM BUDDHA NAGAR\", \"GHAZIABAD\", \"Ghaziabad\", \"GHAZIPUR\", \"FARIDABAD\", \"GURGAON\", \"Faridabad\", \"Gurgaon\" ] } ] } ] } ] } ] } ] }";

        Map<String, Object> facts = new HashMap<>();
        facts.put("childCount", 0);
        facts.put("adultCount", 1);
        facts.put("sumInsured", 500000);
        facts.put("city", "Thane");
        facts.put("userType", "self");
        facts.put("age", 24);
        try {
            boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
            assertTrue(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rule_test_apollo_positive() {
        final String rule = "{ \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"GREATER_THAN\", \"value\" : 0 }, { \"any\" : [ { \"fact\" : \"planId\", \"operator\" : \"STRING_NOT_EQUALS\", \"value\" : \"AOSE\" }, { \"fact\" : \"userType\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"son\", \"son1\", \"son2\", \"son3\", \"son4\", \"daughter\", \"daughter1\", \"daughter2\", \"daughter3\", \"daughter4\" ] }, { \"fact\" : \"age\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 21.0 } ] } ] }";

        Map<String, Object> facts = new HashMap<>();
        facts.put("childCount", 0);
        facts.put("adultCount", 1);
        facts.put("planId", "AOSE");
        facts.put("userType", "son");
        facts.put("age", 21);

        try {
            boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
            assertTrue(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rule_test_apollo_negative() {
        final String rule = "{ \"all\" : [ { \"fact\" : \"adultCount\", \"operator\" : \"GREATER_THAN\", \"value\" : 0 }, { \"any\" : [ { \"fact\" : \"planId\", \"operator\" : \"STRING_NOT_EQUALS\", \"value\" : \"AOSE\" }, { \"fact\" : \"userType\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"son\", \"son1\", \"son2\", \"son3\", \"son4\", \"daughter\", \"daughter1\", \"daughter2\", \"daughter3\", \"daughter4\" ] }, { \"fact\" : \"age\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 21.0 } ] } ] }";

        Map<String, Object> facts = new HashMap<>();
        facts.put("childCount", 0);
        facts.put("adultCount", 1);
        facts.put("planId", "AOSE");
        facts.put("userType", "son");
        facts.put("age", 22);

        try {
            boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
            assertFalse(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rule_test_iffco_positive_1() {
        final String rule = "{ \"all\" : [ { \"fact\" : \"memberCount\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 6 }, { \"fact\" : \"adultCount\", \"operator\" : \"GREATER_THAN\", \"value\" : 0 }, { \"any\" : [ { \"fact\" : \"planId\", \"operator\" : \"STRING_EQUALS\", \"value\" : \"ITFHP\" }, { \"id\" : \"IFCCO_PINCODE\", \"operator\" : \"EXPRESSION\", \"fact\" : \"pincode\", \"params\" : \"a,b\", \"paramTypes\" : { \"a\" : \"STRING\", \"b\" : \"STRING\" }, \"exp\" : \"a == b\" } ] } ] }";

        Map<String, Object> facts = new HashMap<>();
        facts.put("memberCount", 5);
        facts.put("adultCount", 1);
        facts.put("planId", "AVCC");
        facts.put("age", 22);
        String arr[] = {"400601", "400601"};
        facts.put("pincode", arr);


        try {
            boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
            assertTrue(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rule_test_iffco_positive_2() {
        final String rule = "{ \"all\" : [ { \"fact\" : \"memberCount\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 6 }, { \"fact\" : \"adultCount\", \"operator\" : \"GREATER_THAN\", \"value\" : 0 }, { \"any\" : [ { \"fact\" : \"planId\", \"operator\" : \"STRING_EQUALS\", \"value\" : \"ITFHP\" }, { \"id\" : \"IFCCO_PINCODE\", \"operator\" : \"EXPRESSION\", \"fact\" : \"pincode\", \"params\" : \"a,b\", \"paramTypes\" : { \"a\" : \"STRING\", \"b\" : \"STRING\" }, \"exp\" : \"a == b\" } ] } ] }";

        Map<String, Object> facts = new HashMap<>();
        facts.put("memberCount", 6);
        facts.put("adultCount", 1);
        facts.put("planId", "ITFHP");
        facts.put("age", 22);
        String arr[] = {"40060", "400601"};
        facts.put("pincode", arr);


        try {
            boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
            assertTrue(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rule_test_iffco_negative() {
        final String rule = "{ \"all\" : [ { \"fact\" : \"memberCount\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 6 }, { \"fact\" : \"adultCount\", \"operator\" : \"GREATER_THAN\", \"value\" : 0 }, { \"any\" : [ { \"fact\" : \"planId\", \"operator\" : \"STRING_EQUALS\", \"value\" : \"ITFHP\" }, { \"id\" : \"IFCCO_PINCODE\", \"operator\" : \"EXPRESSION\", \"fact\" : \"pincode\", \"params\" : \"a,b\", \"paramTypes\" : { \"a\" : \"STRING\", \"b\" : \"STRING\" }, \"exp\" : \"a == b\" } ] } ] }";

        Map<String, Object> facts = new HashMap<>();
        facts.put("memberCount", 6);
        facts.put("adultCount", 1);
        facts.put("planId", "ABC");
        facts.put("age", 22);
        String arr[] = {"40060", "400601"};
        facts.put("pincode", arr);


        try {
            boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
            assertFalse(op);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
