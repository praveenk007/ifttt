import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifttt.abstracts.impl.RuleEngineBooleanImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author praveenkamath
 **/
public class ListFactsTest {

    @Test
    public void positive() throws Exception {
        String rule = "{\n" +
                "\t\"all\" :[\n" +
                "\t  {\n" +
                "\t  \t\"fact\" : \"list\",\n" +
                "\t\t\"operator\" : \"INPUT_LIST_HAS\",\n" +
                "\t\t\"value\" : \"CERT2\"\n" +
                "\t  }\n" +
                "\t]\n" +
                "}";
        Map<String, Object> facts = new HashMap<>();
        List<String> l = new ArrayList();
        l.add("CERT2");
        l.add("CERT4");
        l.add("CERT3");
        facts.put("list", l);
        boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
        Assert.assertTrue(op);
    }

    @Test
    public void negative() throws Exception {
        String rule = "{\n" +
                "\t\"all\" :[\n" +
                "\t  {\n" +
                "\t  \t\"fact\" : \"list\",\n" +
                "\t\t\"operator\" : \"INPUT_LIST_HAS\",\n" +
                "\t\t\"value\" : \"CERT1\"\n" +
                "\t  }\n" +
                "\t]\n" +
                "}";
        Map<String, Object> facts = new HashMap<>();
        List<String> l = new ArrayList();
        l.add("CERT2");
        l.add("CERT4");
        l.add("CERT3");
        facts.put("list", l);
        boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
        Assert.assertFalse(op);
    }

    @Test
    public void nullList() throws Exception {
        final String rule = "{\"all\":[{\"fact\":\"partnerCerts\",\"operator\":\"INPUT_LIST_HAS\",\"value\":\"pospgi\"},{\"fact\":\"productCerts\",\"operator\":\"INPUT_LIST_HAS\",\"value\":\"pospgi\"}]}";
        Map<String, Object> facts = new HashMap<>();
        List<String> partnerCerts = new ArrayList();
        partnerCerts.add("pospgi");
        partnerCerts.add("spgli");
        facts.put("partnerCerts", partnerCerts);
        facts.put("productCerts", null);

        boolean op = new RuleEngineBooleanImpl().runThis(new ObjectMapper().readValue(rule, JsonNode.class)).against(facts).execute();
        Assert.assertFalse(op);
    }

}
