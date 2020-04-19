package org.example.entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "bird")
    private String bird;
    @Column(name = "number")
    private Long number;
    @Column(name = "totalWeight")
    private Double totalWeight;
    @Column(name = "unitPrice")
    private Double unitPrice;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @Column(name = "date")
    private String date;

    public Order() {
    }

    public Order(String bird, Long number, Double totalWeight, Double unitPrice, Double totalPrice, String date) {
        this.bird = bird;
        this.number = number;
        this.totalWeight = totalWeight;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public ObservableValue<Long> getIdProperty() {
        return new SimpleLongProperty(id).asObject();
    }

    public String getBird() {
        return bird;
    }

    public ObservableValue<String> getBirdProperty() {
        return new SimpleStringProperty(bird);
    }

    public void setBird(String bird) {
        this.bird = bird;
    }

    public Long getNumber() {
        return number;
    }

    public ObservableValue<Long> getNumberProperty() {

        return new SimpleLongProperty(Objects.requireNonNullElse(number, 0L)).asObject();
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public ObservableValue<Double> getTotalWeightProperty() {
        return new SimpleDoubleProperty(Objects.requireNonNullElse(totalWeight, 0d)).asObject();
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public ObservableValue<Double> getUnitPriceProperty() {
        return new SimpleDoubleProperty(Objects.requireNonNullElse(unitPrice, 0d)).asObject();
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public ObservableValue<Double> getTotalPriceProperty() {
        return new SimpleDoubleProperty(Objects.requireNonNullElse(totalPrice, 0d)).asObject();
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }
     public ObservableValue<String> getDateProperty(){
        return new SimpleObjectProperty<>(date);
     }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", bird='" + bird + '\'' +
                ", number=" + number +
                ", totalWeight=" + totalWeight +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getBird(), order.getBird()) &&
                Objects.equals(getNumber(), order.getNumber()) &&
                Objects.equals(getTotalWeight(), order.getTotalWeight()) &&
                Objects.equals(getUnitPrice(), order.getUnitPrice()) &&
                Objects.equals(getTotalPrice(), order.getTotalPrice()) &&
                Objects.equals(getDate(), order.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBird(), getNumber(), getTotalWeight(), getUnitPrice(), getTotalPrice(), getDate());
    }
}
