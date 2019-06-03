/**
 * @author praveenkamath
 **/
public class HDFCPaymentRequest {

    @Key(value = "CustomerID")
    private String customerId;

    @Key(value = "TxnAmount")
    private String transactionAmount;

    @Key(value = "AdditionalInfo1")
    private String additionalInfo1;

    @Key(value = "AdditionalInfo2")
    private String additionalInfo2;

    @Key(value = "AdditionalInfo3")
    private String additionalInfo3;

    @Key(value = "ProductCd")
    private String productCode;

    @Key(value = "hdnPayMode")
    private String payMode;

    @Key(value = "hdnEMIMode")
    @Broker(value = "hdfcbank")
    private String emiMode;

    @Key(value = "UserName")
    private String username;

    @Key(value = "UserMailId")
    private String emailId;

    @Key(value = "ProducerCd")
    private String producerCd;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getAdditionalInfo1() {
        return additionalInfo1;
    }

    public void setAdditionalInfo1(String additionalInfo1) {
        this.additionalInfo1 = additionalInfo1;
    }

    public String getAdditionalInfo2() {
        return additionalInfo2;
    }

    public void setAdditionalInfo2(String additionalInfo2) {
        this.additionalInfo2 = additionalInfo2;
    }

    public String getAdditionalInfo3() {
        return additionalInfo3;
    }

    public void setAdditionalInfo3(String additionalInfo3) {
        this.additionalInfo3 = additionalInfo3;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getEmiMode() {
        return emiMode;
    }

    public void setEmiMode(String emiMode) {
        this.emiMode = emiMode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getProducerCd() {
        return producerCd;
    }

    public void setProducerCd(String producerCd) {
        this.producerCd = producerCd;
    }
}

