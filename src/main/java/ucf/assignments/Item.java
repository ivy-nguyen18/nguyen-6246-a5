package ucf.assignments;

import java.math.BigDecimal;

public class Item {
    private BigDecimal value;
    private String serialNumber;
    private String name;

    public Item(BigDecimal value, String serialNumber, String name){
        this.value = value;
        this.serialNumber = serialNumber;
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
