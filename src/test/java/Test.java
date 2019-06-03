import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author praveenkamath
 **/
public class Test {

    public static void main(String[] args) {
        List<String> s = new ArrayList<>();
        s.add("pk");
        System.out.println(s.contains("pff"));
        Field[] fields = HDFCPaymentRequest.class.getFields();
        HDFCPaymentRequest paymentRequest = new HDFCPaymentRequest();
        paymentRequest.setAdditionalInfo1("1");
        paymentRequest.setAdditionalInfo2("2");
        paymentRequest.setAdditionalInfo3("3");
        paymentRequest.setEmiMode("emi");
        Map<String, String> params = new HashMap<>();
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            Broker brokerAnnotation = field.getAnnotation(Broker.class);
            Key keyAnnotation = field.getAnnotation(Key.class);
            if(brokerAnnotation == null || (brokerAnnotation.value().equalsIgnoreCase("bajaj"))) {
                String fieldValue = (String) getFieldValue(paymentRequest, field);
                params.put(keyAnnotation.value(), fieldValue);
            }
        });
        System.out.println(params);
    }

    private static Object getFieldValue(HDFCPaymentRequest paymentRequest, Field field){
        try {
            return field.get(paymentRequest);
        } catch (IllegalAccessException e) {
        } return null;
    }
}
