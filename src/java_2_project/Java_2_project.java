package java_2_project;

import java.util.*;

public class Java_2_project {

    static Payment visa = new Visa(400, 123456789, "Mohammed");
    static Payment paypal = new Paypal(200, "Mohammed Abo Aqlain", "mo7mednafez@gmail.com", "0595481052");
    static Payment masterCard = new MasterCard(300, 123456789, "Mohammed Abo Aqlain");
    static Carts cart = new Carts();
    static ArrayList<Products> products = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        products.add(new Products(1, "rice", 10, 20, null));
        products.add(new Products(2, "chocolate", 12, 20, null));
        products.add(new Products(3, "milk", 17, 20, null));
        products.add(new Products(4, "salt", 2, 20, null));
        products.add(new Products(5, "biscuit", 3, 20, null));
        products.add(new Products(6, "juice", 3, 20, "mango"));

        int n;
        int count = 0;
        outer:
        while (true) {
            displayMenu();
            n = input.nextInt();
            System.out.println();

            switch (n) {
                case 1:
                    listAllProducts();
                    break;
                case 2:
                    productDetails(products);
                    break;
                case 3:
                    filterProducts();
                    break;
                case 4:
                    System.out.println(cart.ListProducts());
                    break;
                case 5:
                    addProduct();
                    break;
                case 6:
                    removeProduct();
                    break;
                case 7:
                    cart.emptyCart(cart);
                    System.out.println("The cart now is empty!\n");
                    break;
                case 8:
                    System.out.print("Enter Payment method : \n"
                            + "1. Visa.\n"
                            + "2. Paypal.\n"
                            + "3. MasterCard.\n\n"
                            + "Enter a number : ");
                    switch (input.nextInt()) {
                        case 1:
                            pay(visa);
                            break;
                        case 2:
                            pay(paypal);
                            break;
                        case 3:
                            pay(masterCard);
                            break;
                        default:
                            System.out.println("invalid input!!\n");
                            break;
                    }
                    break;

                case 9:
                    System.out.print("Are you sure? Do you want to exit the program ?"
                            + "\n    1. Yes     2. No"
                            + "\n Enter a number for verifying : ");
                    n = input.nextInt();
                    if (n == 2) {
                        System.out.println();
                        continue;
                    } else {
                        System.out.println("********END OF PROGRAM*********");
                        break outer;
                    }
                default:
                    System.out.print("invalid input!!");
                    count++;
                    if (count == 3) {
                        System.out.println("\n********END OF PROGRAM*********");
                        break outer;
                    }
                    System.out.println(" please try again!\n");

            }
        }

    }

    public static void displayMenu() {
        System.out.print("    ***********    \n"
                + "1. List all products.\n"
                + "2. Product details.\n"
                + "3. Filter products.\n"
                + "4. List of products from cart.\n"
                + "5. Add product to cart.\n"
                + "6. Remove product from cart.\n"
                + "7. Empty cart.\n"
                + "8. Pay method.\n"
                + "9. Exit the program.\n\n"
                + "Enter an integer : ");
    }

    public static void productDetails(ArrayList<Products> products) {
        if (products.isEmpty()) {
            System.out.println("there are no products!!\n");
            return;
        }
        System.out.print("Enter the id number for the product : ");
        int id = input.nextInt();

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                System.out.println("the ID is           :  " + id
                        + "\nThe name is         :  " + products.get(i).getName()
                        + "\nThe price is        :  " + products.get(i).getPrice()
                        + "\nThe description is  :  " + products.get(i).getDescription()
                        + "\nThe quantity is     :  " + products.get(i).getQuantity()
                        + "\n");
                return;
            }
        }
        System.out.println("\nThe id number is not found!!\n");
    }

    public static void listAllProducts() {
        if (products.isEmpty()) {
            System.out.println("there are no products!!\n");
        }
        System.out.println("ID\tNAME");
        for (int i = 0; i < products.size(); i++) {
            System.out.print(products.get(i).getId() + "\t" + products.get(i).getName() + "\n");
        }
        System.out.println();
    }

    public static void filterProducts() {
        if (products.isEmpty()) {
            System.out.println("there are no products!!\n");
            return;
        }
        System.out.print("Filter using :\n"
                + "1. Name or part of name.\n"
                + "2. Range of price.\n\n"
                + "Enter a numder : ");
        int n = input.nextInt();
        switch (n) {
            case 1:
                filterName();
                break;
            case 2:
                filterRangeOfPrice();
                break;
            default:
                System.out.println("invalid input!!\n");
                break;
        }
    }

    public static void pay(Payment payment) {

        if (payment.withdraw(cart.getPrices())) {
            System.out.println("\nPayment is done.\n");
            cart.emptyCart(cart);
        } else {
            System.out.println("the balance is not enough for payment!!"
                    + "\n remove some products or add a balance in your card.\n");
        }
    }

    static void addProduct() {
        System.out.print("Add product using :\n"
                + "1. its id.\n"
                + "2. its name.\n"
                + "Enter a number : ");
        int n = input.nextInt();

        outer:
        switch (n) {
            case 1:
                System.out.print("Enter its id : ");
                int id = input.nextInt();
                int cartLength = cart.getProducts().size();
                for (Products i : products) {
                    if (i.getId() == id) {
                        System.out.print("Enter the quantity : ");
                        int quantity = input.nextInt();
                        if (i.getQuantity() >= quantity) {
                            cart.addProduct(id, i.getName(), i.getPrice(), i.getDescription(), quantity);
                            i.setQuantity(i.getQuantity() - quantity);
                            System.out.println("\nThe product was successfully added.\n");
                            break;
                        }
                        System.out.println("\nthe quantity is too large, only " + i.getQuantity() + " is available\n");
                        break outer;
                    }
                }
                if (cartLength == cart.getProducts().size()) {
                    System.out.println("\nthe id is not found.\n");
                }
                break;

            case 2:
                System.out.print("Enter its name : ");
                String name = input.next();
                int cartLength1 = cart.getProducts().size();
                for (Products i : products) {
                    if (i.getName().equals(name)) {
                        System.out.print("Enter the quantity : ");
                        int quantity = input.nextInt();
                        if (i.getQuantity() >= quantity) {
                            cart.addProduct(i.getId(), name, i.getPrice(), i.getDescription(), quantity);
                            i.setQuantity(i.getQuantity() - quantity);
                            System.out.println("\nThe product was successfully added.\n");
                            break;
                        }
                        System.out.println("the quantity is too large, only " + i.getQuantity() + " is available");
                    }
                }
                if (cartLength1 == cart.getProducts().size()) {
                    System.out.println("\nThe name is not found.\n");
                }
                break;
            default : 
                System.out.println("\ninvalid input!!\n");
        }
    }

    static void removeProduct() {
        System.out.print("1. Remove using its name.\n"
                + "2. Remove using its ID.\n\n"
                + "Enter a number : ");
        int n = input.nextInt();

        switch (n) {
            case 1:
                int cartLength = cart.getProducts().size();
                System.out.print("Enter the product's name : ");
                String name = input.next();
                for (Products i : cart.getProducts()) {
                    if (i.getName().equals(name)) {
                        products.add(new Products(i.getId(), name, i.getPrice(), i.getQuantity(), i.getDescription()));
                        cart.removeProduct(i);
                        System.out.println("The product is removed.\n");
                        break;
                    }
                }
                if(cart.getProducts().size() == cartLength){
                    System.out.println("\ninvalid input!!\n");
                    break;
                }
            case 2:
                int cartLength1 = cart.getProducts().size();
                System.out.print("Enter the product's ID number : ");
                int id = input.nextInt();
                for (Products i : cart.getProducts()) {
                    if (i.getId() == id) {
                        products.add(new Products(id, i.getName(), i.getPrice(), i.getQuantity(), i.getDescription()));
                        cart.removeProduct(i);
                        System.out.println("The product is removed.\n");
                        break;
                    }
                }
                if(cart.getProducts().size() == cartLength1){
                    System.out.println("\ninvalid input!!\n");
                    break;
                }
            default:
                System.out.println("invalid input!!\n");
        }
    }

    static void filterName() {

        System.out.print("Enter a name or part of name : ");

        String name = input.next();
        int count = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().contains(name)) {
                count++;
                if (count == 1) {
                    System.out.printf("\n%-10s %-10s %-10s %-10s\n", "ID", "NAME", "PRICE", "QUANTITY");
                }

                System.out.printf("%-10s %-10s %-10s %-10s\n", products.get(i).getId(),
                        products.get(i).getName(), products.get(i).getPrice(),
                        products.get(i).getQuantity());
            }
        }
        if (count == 0) {
            System.out.println("\nThere are no products in such a name!!\n");
        }

        System.out.println();
    }

    static void filterRangeOfPrice() {
        System.out.print("Enter the range of price, ex, 10 - 25 : ");

        double fromPrice = input.nextDouble();
        input.next();
        double toPrice = input.nextDouble();

        int count = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getPrice() >= fromPrice
                    && products.get(i).getPrice() <= toPrice) {
                count++;
                if (count == 1) {
                    System.out.printf("\n%-10s %-10s %-10s %-10s\n", "ID", "NAME", "PRICE", "QUANTITY");
                }

                System.out.printf("%-10s %-10s %-10s %-10s\n", products.get(i).getId(),
                        products.get(i).getName(), products.get(i).getPrice(),
                        products.get(i).getQuantity());
            }
        }
        if (count == 0) {
            System.out.println("\nThere are no products in such a range!!\n");
        }

        System.out.println();
    }
}
