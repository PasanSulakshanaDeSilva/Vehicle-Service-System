package model;

public class Item {
    private String item_Id;
    private String item_Name;
    private double item_Price;
    private int item_Qty;

    public Item(String item_id, String item_name, String item_price, int item_qty) {
    }

    public Item(String item_Id, String item_Name, double item_Price, int item_Qty) {
        this.setItem_Id(item_Id);
        this.setItem_Name(item_Name);
        this.setItem_Price(item_Price);
        this.setItem_Qty(item_Qty);
    }

    public String getItem_Id() {
        return item_Id;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public String getItem_Price() {
        return String.valueOf(item_Price);
    }

    public int getItem_Qty() {
        return item_Qty;
    }

    public void setItem_Id(String item_Id) {
        this.item_Id = item_Id;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public void setItem_Price(double item_Price) {
        this.item_Price = item_Price;
    }

    public void setItem_Qty(int item_Qty) {
        this.item_Qty = item_Qty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_Id='" + item_Id + '\'' +
                ", item_Name='" + item_Name + '\'' +
                ", item_Price=" + item_Price +
                ", item_Qty=" + item_Qty +
                '}';
    }
}
