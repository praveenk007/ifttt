# ifttt - if this then that
is a simple-to-use rule engine which can be used to keep the conditional business logics outside the codebase.

### V1.0.0.6
Added NOT_EQUALS operator for numeric values. It checks if value in fact is not equal to specified value in rule.

### V1.0.0.4
Used [Janino](https://janino-compiler.github.io/janino/) to evaluate expressions. This JAVA utility offers a great feature where-in you can compile the expressions once (like ```a + b``` where ```a``` and ```b``` are variables), then pass dynamic parameters on runtime. This saves time (typical engines compiles the expression all the time which isn't required, thus, costing time)

#### Usage
The rule will be something like below
```json
{
        "id" : "BMI", 
        "operator" : "EXPRESSION", 
        "fact" : "bmi", 
        "params" : "a,b", 
        "paramTypes" : {
            "a" : "INTEGER", 
            "b" : "INTEGER"
        }, 
        "exp" : "a/(b * b * 0.0001) >= 0 && a/(b * b * 0.0001) < 35"
}
```
Remember, here the ```id``` value has to be unique throughout the project, as the compiled expression is stored in memory using this value as the key.
Here, you need to pass fact in below manner
```java
map.put("bmi", new Integer(){60, 160});
.
.
.
```

## Usage

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

Above rules are run against facts defined either in below format

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

or

```java
Map<String, Object> facts = new HashMap<String, Object>();
facts.put("beer_consumption", 10);
facts.put("cigar_consumption", 10);
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

### Operators supported

#### 1. EQUALS
Checks if provided fact is equal to the rule value.
Ex.
```json
{
   "fact" : "age", 
   "operator" : "EQUALS", 
   "value" : 22
}
```
#### 2. NOT_EQUALS
Checks if provided fact is equal to the rule value.
Ex.
```json
{
   "fact" : "age", 
   "operator" : "EQUALS", 
   "value" : 22
}
```
#### 3. LESSER_THAN
Checks if provided fact is lesser than the rule value.
Ex.
```json
{
   "fact" : "age", 
   "operator" : "LESSER_THAN", 
   "value" : 22
}
```
#### 4. LESSER_THAN_INCLUSIVE
Checks if provided fact is lesser than or equals the rule value.
Ex.
```json
{
   "fact" : "age", 
   "operator" : "LESSER_THAN_INCLUSIVE", 
   "value" : 22
}
```
#### 5. GREATER_THAN
Checks if provided fact is greater than the rule value.
Ex.
```json
{
   "fact" : "age", 
   "operator" : "GREATER_THAN", 
   "value" : 22
}
```
#### 6. GREATER_THAN_INCLUSIVE
Checks if provided fact is greater than or equals the rule value.
Ex.
```json
{
   "fact" : "age", 
   "operator" : "GREATER_THAN_INCLUSIVE", 
   "value" : 22
}
```

#### 7. STRING_EQUALS
Checks if 2 strings are equal or not.
Ex.
```json
{
   "fact" : "state", 
   "operator" : "STRING_EQUALS", 
   "value" : "Abc"
}
```
#### 8. STRING_NOT_EQUALS
Checks if 2 strings are not equal.
Ex.
```json
{
   "fact" : "state", 
   "operator" : "STRING_NOT_EQUALS", 
   "value" : "Abc"
}
```
#### 9. STRING_EQUALS_IGNORE
Checks if 2 strings are equal or not, irrespactive of their cases.
Ex.
```json
{
   "fact" : "state", 
   "operator" : "STRING_EQUALS_IGNORE", 
   "value" : "ABC"
}
```
#### 10. LIST_CONTAINS
Checks if provided fact is present in the rule values.
Ex.
```json
{
   "fact" : "state", 
   "operator" : "LIST_CONTAINS", 
   "value" : ["abc", "xyz"]
}
```
#### 11. LIST_NOT_CONTAINS
Checks if provided fact is NOT present in the rule values.
Ex.
```json
{
   "fact" : "state", 
   "operator" : "LIST_NOT_CONTAINS", 
   "value" : ["abc", "xyz"]
}
```
#### 12. BOOLEAN_EQUALS
Checks if provided boolean fact is equal to the rule value.
Ex.
```json
{
   "fact" : "state", 
   "operator" : "BOOLEAN_EQUALS", 
   "value" : true
}
```

### Upcoming features in pipeline
Rule document will have a ``` then``` to execute ``` Math``` operations defined in it. ``` then``` field can be defined something like this ``` then : "($days+1)" ```, where ``` days``` is the fact provided in fact object. The result will be returned to the caller if the condition evaluates to true.


