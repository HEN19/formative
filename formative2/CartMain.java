import java.util.ArrayList;
import java.util.List;

public class CartMain {
    public static void main(String[] args) {
        List<Product> products = createProductList();
        List<Price> prices = createPriceList(products);

        // Scenario 1: Purchase 5 items of the same product
        purchaseSameProduct(products.get(0), prices);

        // Scenario 2: Purchase 5 different products
        purchaseDifferentProducts(products.subList(0, 5), prices);
    }

    private static List<Product> createProductList() {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            products.add(new Product(i, "Product " + i, "Description " + i));
        }
        return products;
    }

    private static List<Price> createPriceList(List<Product> products) {
        List<Price> prices = new ArrayList<>();
        for (Product product : products) {
            double amount = Math.random() * 5000 + 5000; // Random amount between 5000 and 10000
            prices.add(new Price(product.getId(), amount, "Rp", product.getId()));
        }
        return prices;
    }

    private static void purchaseSameProduct(Product product, List<Price> prices) {
        System.out.println("Purchasing 5 items of the same product:");
        for (int i = 1; i <= 6; i++) {
            double discount = (i == 6) ? 0.2 : 0.0;
            double discountedAmount = applyDiscount(prices.get(0).getAmount(), discount);
            System.out.println("Item " + i + ": " + product.getName() + " - Amount: " + discountedAmount);
        }
        System.out.println();
    }

    private static void purchaseDifferentProducts(List<Product> products, List<Price> prices) {
        if (products.size() < 5) {
            System.out.println("Not enough different products to make a purchase.");
            return;
        }

        System.out.println("Purchasing 5 different products:");
        double totalAmount = 0;
        for (int i = 0; i < 5; i++) {
            totalAmount += prices.get(i).getAmount();
            System.out.println(
                    "Item " + (i + 1) + ": " + products.get(i).getName() + " - Amount: " + prices.get(i).getAmount());
        }

        // Get the cheapest product from the remaining products and add it as a free
        // item
        Product cheapestProduct = findCheapestProduct(products.subList(5, products.size()),
                prices.subList(5, prices.size()));
        if (cheapestProduct != null) {
            System.out.println("Free Item: " + cheapestProduct.getName() + " (Cheapest Product)");
            totalAmount += prices.get(prices.size() - 1).getAmount();
        }

        System.out.println("Total Amount (including free item): " + totalAmount);
    }

    private static double applyDiscount(double amount, double discount) {
        return amount - (amount * discount);
    }

    private static Product findCheapestProduct(List<Product> products, List<Price> prices) {
        if (products.isEmpty() || prices.isEmpty()) {
            System.out.println("No products or prices available.");
            return null;
        }

        double minAmount = Double.MAX_VALUE;
        Product cheapestProduct = null;

        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i).getAmount() < minAmount) {
                minAmount = prices.get(i).getAmount();
                cheapestProduct = products.get(i);
            }
        }
        return cheapestProduct;
    }

}
