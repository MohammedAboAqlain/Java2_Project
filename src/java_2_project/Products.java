
package java_2_project;

public class Products {
    
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String description;

    public Products(int id, String name, double price) {
        this(id, name, price, 1, "no description");
    }
    
    public Products(int id, String name, double price, String description) {
        this(id, name, price, 1, description);
    }
    
    public Products(int id, String name, double price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
