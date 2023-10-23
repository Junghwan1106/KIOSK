public class Item extends Menu {
    private Double price;

    public Item(String name, Double price, String description) {
        super(name, description);
        this.price = price;
    }

    public Double getPrice() {
        return this.price;
    }
}