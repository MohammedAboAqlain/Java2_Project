package java_2_project;

import java.util.ArrayList;

public class Carts {

    private ArrayList<Products> products = new ArrayList<>();
    private double prices;

    public Carts() {
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public void addProduct(Products product) {
        if(isProductExist(product.getId(), product.getName(), product.getPrice(), product.getQuantity()))
            return;
        products.add(product);
        prices += product.getPrice() * product.getQuantity();
    }

    public void addProduct(int id, String name, double price) {
        int quantity = 1;
        if(isProductExist(id, name, price, quantity))
            return;
        products.add(new Products(id, name, price));
        prices += price;
    }

    public void addProduct(int id, String name, double price, String description) {
        int quantity = 1;
        if (isProductExist(id, name, price, quantity))
            return;
        products.add(new Products(id, name, price, description));
        prices += price;
    }
    
    public void addProduct(int id, String name, double price, String description, int quantity){
        if(isProductExist(id, name, price, quantity))
            return;
        products.add(new Products(id, name, price, quantity, description));
        prices += price * quantity;
    }

    //to eliminate the last product
    public void removeProduct() {
        prices -= products.get(products.size() - 1).getPrice() * products.get(products.size() -1).getQuantity();
        products.remove(products.size() - 1);
    }

    //to eliminate a product using its id
    public void removeProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                prices -= products.get(i).getPrice() * products.get(i).getQuantity();
                products.remove(i);
            }
        }
    }

    //to eliminate a product using its name
    public void removeProduct(String name){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getName().equals(name.trim())){
                prices -= products.get(i).getPrice() * products.get(i).getQuantity();
                products.remove(i);
            }
        }
    }
    
    //to eliminate a specified product
    public void removeProduct(Products product) {
        prices -= product.getPrice() * product.getQuantity();
        products.remove(product);
    }

    public String ListProducts() {
        if (this.products.isEmpty()) {
            return "there are no products in the cart\n";
        }
        String str = "";

        for (int i = 0; i < products.size(); i++) {
            str += products.get(i).getId() + "\t" + products.get(i).getName() +"\n";
        }
        return "ID\tNAME\n" +str;
    }

    public boolean isEmptyCart() {
        return products.isEmpty();
    }

    public void emptyCart(Carts cart) {
        products.removeAll(cart.getProducts());
        setPrices(0);
    }
    
    private boolean isProductExist(int id, String name, double price, int quantity){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id 
                    && products.get(i).getName().equals(name)
                    && products.get(i).getPrice() == price){
                
                prices += quantity * price;
                products.get(i).setQuantity(products.get(i).getQuantity() + quantity);
                return true;
            }
        }
        return false;
    }
}
