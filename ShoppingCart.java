import java.util.*;

class ShoppingCart {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Store item prices
        HashMap<String, Double> priceMap = new HashMap<>();
        priceMap.put("Apple", 50.0);
        priceMap.put("Banana", 10.0);
        priceMap.put("Milk", 40.0);
        priceMap.put("Bread", 30.0);

        // Store item quantities
        HashMap<String, Integer> quantityMap = new HashMap<>();

        // Store cart items
        ArrayList<String> cart = new ArrayList<>();

        while (true) {
            System.out.println("\n===== ONLINE SHOPPING CART =====");
            System.out.println("1. View Available Items");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Calculate Total Price");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                // View items
                case 1:
                    System.out.println("\n--- Available Items ---");
                    for (String item : priceMap.keySet()) {
                        System.out.println(item + " - ₹" + priceMap.get(item));
                    }
                    break;

                // Add item
                case 2:
                    System.out.print("\nEnter item name: ");
                    sc.nextLine();  // consume newline
                    String item = sc.nextLine();

                    if (priceMap.containsKey(item)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();

                        cart.add(item);
                        quantityMap.put(item, quantityMap.getOrDefault(item, 0) + qty);

                        System.out.println("✅ Item added to cart!");
                    } else {
                        System.out.println("❌ Item not found!");
                    }
                    break;

                // View cart
                case 3:
                    System.out.println("\n--- Your Cart ---");
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty!");
                    } else {
                        for (String cartItem : quantityMap.keySet()) {
                            System.out.println(cartItem + " x " + quantityMap.get(cartItem));
                        }
                    }
                    break;

                // Calculate total
                case 4:
                    double total = 0;

                    for (String cartItem : quantityMap.keySet()) {
                        int qty = quantityMap.get(cartItem);
                        double price = priceMap.get(cartItem);
                        total += qty * price;
                    }

                    System.out.println("\n💰 Total Amount = ₹" + total);
                    break;

                // Exit
                case 5:
                    System.out.println("\nThank you for shopping!");
                    System.exit(0);

                default:
                    System.out.println("⚠ Invalid choice!");
            }
        }
    }
}
