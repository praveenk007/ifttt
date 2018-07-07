package main.com.ifttt.abstracts;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author praveenkamath
 **/
public abstract class RuleEngine {

    public abstract RuleEngine runThis(JsonNode jsonNode) throws Exception;

    public abstract RuleEngine against(Object object);

    public abstract boolean execute() throws Exception;
}
