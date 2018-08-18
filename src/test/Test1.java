import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifttt.abstracts.impl.RuleEngineBooleanImpl;
import java.util.HashMap;
import java.util.Map;

/**
 * @author praveenkamath
 **/
public class Test1 {

    public static void main(String[] args) {
        String json = "{ \"all\" : [ { \"fact\" : \"portability\", \"operator\" : \"BOOLEAN_EQUALS\", \"value\" : false }, { \"any\" : [ { \"all\" : [ { \"fact\" : \"planId\", \"operator\" : \"STRING_EQUALS\", \"value\" : \"VLHCB\" }, { \"fact\" : \"sumInsured\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ 200000, 300000, 400000, 500000, 600000, 750000, 1000000 ] } ] }, { \"all\" : [ { \"fact\" : \"planId\", \"operator\" : \"STRING_EQUALS\", \"value\" : \"VLHCS\" }, { \"fact\" : \"sumInsured\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ 200000, 300000, 400000, 500000, 600000, 750000, 1000000, 1500000 ] } ] }, { \"all\" : [ { \"fact\" : \"planId\", \"operator\" : \"STRING_EQUALS\", \"value\" : \"VLHCE\" }, { \"fact\" : \"sumInsured\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ 300000, 400000, 500000, 600000, 750000, 1000000, 1500000 ] } ] } ] }, { \"any\" : [ { \"all\" : [ { \"fact\" : \"userType\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ \"self\", \"spouse\", \"father\", \"mother\" ] }, { \"fact\" : \"age\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 18.0 }, { \"fact\" : \"age\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 55.0 } ] }, { \"all\" : [ { \"fact\" : \"userType\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ \"son\", \"son1\", \"son2\", \"son3\", \"son4\", \"daughter\", \"daughter1\", \"daughter2\", \"daughter3\", \"daughter4\" ] }, { \"fact\" : \"age\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 0.3 }, { \"fact\" : \"age\", \"operator\" : \"LESSER_THAN_INCLUSIVE\", \"value\" : 25.0 } ] } ] }, { \"any\" : [ { \"all\" : [ { \"fact\" : \"state\", \"operator\" : \"STRING_EQUALS_IGNORE\", \"value\" : \"Gujarat\" }, { \"fact\" : \"sumInsured\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 300000.0 }, { \"any\" : [ { \"fact\" : \"city\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ \"Ahmedabad\", \"Surat\", \"Jamnagar\", \"Vadodara\" ] }, { \"fact\" : \"pincode\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ \"383345\", \"394716\", \"393002\", \"382421\", \"394715\", \"392310\", \"394163\", \"382424\", \"360490\", \"360590\", \"360110\", \"360480\" ] } ] } ] }, { \"all\" : [ { \"fact\" : \"city\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"Alwar\", \"Ganjam\", \"Faridabad\", \"Gautam Buddha Nagar\", \"Dausa\", \"Sonipat\" ] }, { \"fact\" : \"state\", \"operator\" : \"STRING_NOT_EQUALS\", \"value\" : \"Gujarat\" }, { \"fact\" : \"pincode\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"761146\", \"761037\", \"322240\", \"303305\", \"303004\", \"303504\", \"303507\", \"301604\", \"131039\", \"131409\", \"122022\", \"201310\", \"201304\", \"201308\", \"302004\", \"302007\", \"302010\", \"302011\", \"302023\" ] } ] } ] }, { \"any\" : [ { \"all\" : [ { \"fact\" : \"sumInsured\", \"operator\" : \"GREATER_THAN_INCLUSIVE\", \"value\" : 300000.0 }, { \"any\" : [ { \"fact\" : \"city\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ \"Guntur\", \"Jabalpur\", \"Indore\", \"Nagpur\", \"Udaipur\", \"Ahmedabad\", \"Vadodara\", \"Nashik\", \"Jamnagar\", \"Jaipur\", \"Surat\" ] }, { \"fact\" : \"pincode\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ \"383345\", \"394716\", \"393002\", \"382421\", \"394715\", \"392310\", \"394163\", \"382424\", \"360490\", \"360590\", \"360110\", \"360480\", \"262405\", \"246486\", \"244712\", \"453001\", \"453115\", \"303509\", \"302039\", \"416501\", \"416502\" ] }, { \"fact\" : \"state\", \"operator\" : \"LIST_CONTAINS\", \"value\" : [ \"Uttarakhand\", \"Uttaranchal\", \"Haryana\", \"Punjab\", \"Delhi\" ] } ] } ] }, { \"all\" : [ { \"fact\" : \"city\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"Guntur\", \"Jabalpur\", \"Indore\", \"Nagpur\", \"Udaipur\", \"Ahmedabad\", \"Vadodara\", \"Nashik\", \"Jamnagar\", \"Jaipur\", \"Surat\" ] }, { \"fact\" : \"state\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"Uttarakhand\", \"Uttaranchal\", \"Haryana\", \"Punjab\", \"Delhi\" ] }, { \"fact\" : \"pincode\", \"operator\" : \"LIST_NOT_CONTAINS\", \"value\" : [ \"383345\", \"394716\", \"393002\", \"382421\", \"394715\", \"392310\", \"394163\", \"382424\", \"360490\", \"360590\", \"360110\", \"360480\", \"262405\", \"246486\", \"244712\", \"453001\", \"453115\", \"303509\", \"302039\", \"416501\", \"416502\" ] } ] } ] } ] }";
        try {
            JsonNode jsonNode = new ObjectMapper().readValue(json, JsonNode.class);
            Map<String, Object> facts = new HashMap<>();
            facts.put("planId", "VLHCB");
            facts.put("portability", false);
            facts.put("sumInsured", 500000);
            facts.put("deductible", 0);
            facts.put("pincode", "400601");
            facts.put("city", "Guntur");
            facts.put("state", "Gujarat");
            System.out.println(new RuleEngineBooleanImpl().runThis(jsonNode).against(facts).execute());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
