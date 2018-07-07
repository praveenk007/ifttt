# ifttt - if this then that
is a simple-to-use rule engine which can be used to keep the conditional business logics out of the code.


## Usage

The base version (V1.0.0.0) provides the functionality where-in if an operation (``` >= ```, ``` > ```, ``` <=```, ``` < ```, ```== ```) evaluates to ``` true```, then ``` true ``` is returned.

Rules can be provided in below ``` JsonNode ``` format

```json
{
        "any" : [
            {
                "any" : [
                    {
                        "fact" : "beer_consumption", 
                        "operator" : "GREATER_THAN_INCLUSIVE", 
                        "value" : 12.0
                    }, 
                    {
                        "fact" : "cigar_consumption", 
                        "operator" : "GREATER_THAN_INCLUSIVE", 
                        "value" : 10.0
                    }
                ]
            }, 
            {
                "all" : [
                    {
                        "fact" : "oil_consumption", 
                        "operator" : "GREATER_THAN_INCLUSIVE", 
                        "value" : 200.0
                    }, 
                    {
                        "fact" : "tobacco_consumption", 
                        "operator" : "GREATER_THAN_INCLUSIVE", 
                        "value" : 10.0
                    }
                ]
            }
        ]
    }
```
Rules defined under ``` any``` block are OR'd with one another and those defined under ``` all``` block are AND with one another.
Result of these rules can be again OR'd or AND (as you can see a parent ``` any``` block in above rule example).

Above rules are run against facts defined in below ``` JAVA``` format

```java
public class RuleModel {

    @Fact("beer_consumption")
    public int beer;

    @Fact("cigar_consumption")
    public int cigar;

    @Fact("oil_consumption")
    public int oil;
    
    @Fact("tobacco_consumption")
    public int tobaccco;

    //... getters/setters
}
```

### Code snippet for usage

```java
// get rules
JsonNode rules          =  ...
// get facts
Object facts            =  ...
RuleEngine engine       =  new RuleEngineBooleanImpl();
//pass the rules and facts to engine
Object object           =  engine.runThis(rules).against(facts).execute();
```

This rule engine also supports nested (multiple levels of) ```any```/```all``` blocks.

### Upcoming features in pipeline
Rule document will have a ``` then``` to execute ``` Math``` operations defined in it. ``` then``` field can be defined something like this ``` then : "($days+1)" ```, where ``` days``` is the fact provided in fact object. The result will be returned to the caller if the condition evaluates to true.

