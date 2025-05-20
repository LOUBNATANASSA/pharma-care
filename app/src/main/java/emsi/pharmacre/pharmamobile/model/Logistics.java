package emsi.pharmacre.pharmamobile.model;

public class Logistics {
    public long id;
    public String operationType; // e.g., "Shipping", "Receiving"
    public String date;
    public Lot lot;
    public int quantity;
    public String destination;
    public String source;
}