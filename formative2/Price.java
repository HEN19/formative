public class Price {

    private int id;
    private double amount;
    private String valuta;
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Price(int id, double amount, String valuta, int productId) {
        this.id = id;
        this.amount = amount;
        this.valuta = valuta;
        this.productId = productId;
    }
}
