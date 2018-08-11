package test.java;

import com.ifttt.annotations.Fact;

/**
 * @author praveenkamath
 **/
public class RuleModel {

    @Fact("beer_consumption")
    public int beer;

    @Fact("beer_answer")
    public int beerAnswer;

    @Fact("cigar_consumption")
    public int cigar;

    @Fact("oil_consumption")
    public int oil;

    public int getOil() {
        return oil;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public int getTobacco() {
        return tobacco;
    }

    public void setTobacco(int tobacco) {
        this.tobacco = tobacco;
    }

    @Fact("tobacco_consumption")
    public int tobacco;

    public int getBeer() {
        return beer;
    }

    public void setBeer(int beer) {
        this.beer = beer;
    }

    public int getCigar() {
        return cigar;
    }

    public void setCigar(int cigar) {
        this.cigar = cigar;
    }
}
