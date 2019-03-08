package lab_1.model;

import java.util.ArrayList;
import java.util.List;

public class Node {

    String id;
    double x;
    double y;
    List<Item> items;

    public Node() {
        items = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Node: " + id + " {" +
                " (x,y)=" + "(" + x + ", " + y + ")" +
                " items=" + items + '}';
    }
}
