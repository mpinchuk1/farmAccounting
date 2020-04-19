package org.example.UI;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.transform.NonInvertibleTransformException;
import org.example.dao.OrdersDaoImpl;
import org.example.entity.Order;

public class OrderController {

    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December");

    ObservableList<Order> orders;
    FilteredList<Order> filteredList;

    OrdersDaoImpl dao = new OrdersDaoImpl();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField birdField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField weigthField;

    @FXML
    private TextField unitPriceField;

    @FXML
    private TextField totPriceField;


    @FXML
    private TableView<Order> orderTableView;

    @FXML
    private TableColumn<Order, Long> idCol;

    @FXML
    private TableColumn<Order, String> birdCol;

    @FXML
    private TableColumn<Order, Long> numberCol;

    @FXML
    private TableColumn<Order, Double> totWeigtCol;

    @FXML
    private TableColumn<Order, Double> unitPriceCol;

    @FXML
    private TableColumn<Order, Double> totPriceCol;

    @FXML
    private TableColumn<Order, String> dataCol;

    @FXML
    private DatePicker dateField;

    @FXML
    private ComboBox<String> monthBox;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button showButton;
    @FXML
    private Button showAllButton;

    @FXML
    void addRowToTable(ActionEvent event) throws NumberFormatException {

        Order order1 = getOrderParams();

        System.out.println(order1);

        if (order1.getNumber() != null && order1.getTotalWeight() != null) {
            if (dao.addOrder(order1)) {
                orders.add(order1);
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "New order creation failed", ButtonType.CLOSE).showAndWait();
            }
        }


    }

    private void clearFields() {
        numberField.clear();
        weigthField.clear();
        unitPriceField.clear();
        totPriceField.clear();
    }

    private Order getOrderParams() throws NumberFormatException {
        // Calendar calendar = Calendar.getInstance();
        //DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        //String date = dateFormat.format(calendar.getTime());
        if (!numberField.getText().trim().equals("") && !weigthField.getText().trim().equals("") && !unitPriceField.getText().trim().equals("") && !totPriceField.getText().trim().equals("")) {
            String bird = birdField.getText().trim();
            Long number = Long.parseLong(numberField.getText().trim());
            Double totalweight = Double.parseDouble(weigthField.getText().trim());
            Double unitPrice = Double.parseDouble(unitPriceField.getText().trim());
            Double totalPrice = Double.parseDouble(totPriceField.getText().trim());
            String date = dateField.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

            return new Order(bird, number, totalweight, unitPrice, totalPrice, date);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Input data error", ButtonType.CLOSE);
            alert.showAndWait();
            return new Order();
        }

    }

    @FXML
    void deleteRowFromTable(ActionEvent event) {
        List<Order> ordersForDel = orderTableView.getSelectionModel().getSelectedItems();
        StringBuilder delStringBuild = new StringBuilder("Are you sure to delete orders with id ");
        int i = 0;
        for (Order order : ordersForDel) {
            delStringBuild.append(order.getId()).append(", ");
            i++;
        }

        if(i > 0){
            delStringBuild.deleteCharAt(delStringBuild.length() - 1).deleteCharAt(delStringBuild.length() - 1).append("?");
            String string = delStringBuild.toString();
            //System.out.println(string);
            Alert alert = new Alert(Alert.AlertType.WARNING, string, ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                for (Order or : ordersForDel) {
                    Long id = or.getId();
                    dao.deleteOrder(id);
                }
                orderTableView.getItems().removeAll(ordersForDel);
            } else if (buttonType.get() == ButtonType.NO) {

            }
        }


    }

    @FXML
    void showDataOnTable(ActionEvent event) {

        monthBox.valueProperty().addListener((o, ov, nv) -> {
            String temp = getMonthNumber(nv);
            filteredList.setPredicate((Order order) -> order.getDate().substring(0, 2).equals(temp));
        });
        System.out.println(filteredList);
        orderTableView.setItems(filteredList);

    }

    @FXML
    void showAllDataOnTable(ActionEvent event) {
        orders.setAll(dao.selectAllOrders());
        orderTableView.setItems(orders);
    }

    @FXML
    void initialize() {
        initColumns();
        orders = getOrdersDate();
        orderTableView.setItems(orders);

        //monthBox.setValue("");
        monthBox.setItems(months);
        dateField.setValue(LocalDate.now());
        filteredList = new FilteredList<>(orders, e -> true);
    }

    public void initColumns() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        birdCol.setCellValueFactory(cellData -> cellData.getValue().getBirdProperty());
        numberCol.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        totWeigtCol.setCellValueFactory(cellData -> cellData.getValue().getTotalWeightProperty());
        unitPriceCol.setCellValueFactory(cellData -> cellData.getValue().getUnitPriceProperty());
        totPriceCol.setCellValueFactory(cellData -> cellData.getValue().getTotalPriceProperty());
        dataCol.setCellValueFactory(cellDate -> cellDate.getValue().getDateProperty());
    }

    public ObservableList<Order> getOrdersDate() {

        return FXCollections.observableArrayList(dao.selectAllOrders());
    }

    public String getMonthNumber(String inputMonth) {
        switch (inputMonth) {
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
        }
        return "0";
    }
}
