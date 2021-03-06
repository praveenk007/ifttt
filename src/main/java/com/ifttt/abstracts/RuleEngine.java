package com.ifttt.abstracts;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author praveenkamath
 **/
public abstract class RuleEngine {

    public abstract RuleEngine runThis(JsonNode jsonNode) throws Exception;

    public abstract RuleEngine against(Object object) throws Exception;

    public abstract Boolean execute() throws Exception;
}
