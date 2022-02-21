package machine;

public enum CoffeTypes {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCINO(200, 100, 12, 6);

    private final int water;
    private final int milk;
    private final int coffe;
    private final int price;

    CoffeTypes(int water, int milk, int coffe, int price) {
        this.water = water;
        this.milk = milk;
        this.coffe = coffe;
        this.price = price;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffe() {
        return coffe;
    }

    public int getPrice() {
        return price;
    }
}
