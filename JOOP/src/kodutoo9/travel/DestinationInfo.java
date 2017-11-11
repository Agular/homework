package kodutoo9.travel;

public class DestinationInfo {

    private int area;
    private String currency;
    private String currencyCode;
    private int GDPperCapita;

    public DestinationInfo(int area, String currency, String currencyCode, int GDPperCapita) {
        this.area = area;
        this.currency = currency;
        this.currencyCode = currencyCode;
        this.GDPperCapita = GDPperCapita;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getGDPperCapita() {
        return GDPperCapita;
    }

    public void setGDPperCapita(int GDPperCapita) {
        this.GDPperCapita = GDPperCapita;
    }
}
