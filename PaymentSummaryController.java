package com.burritoking;

import com.burritoking.model.FoodItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentSummaryController {

    @FXML
    private TableView<FoodItem> orderSummaryTable;

    @FXML
    private TableColumn<FoodItem, String> foodItemColumn;

    @FXML
    private TableColumn<FoodItem, Integer> quantityColumn;

    @FXML
    private TableColumn<FoodItem, Double> priceColumn;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button proceedToPaymentButton;

    private ObservableList<FoodItem> orderData;

    private String totalPrice;

    @FXML
    public void initialize() {
        foodItemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderData = FXCollections.observableArrayList();
        orderSummaryTable.setItems(orderData);

        proceedToPaymentButton.setOnAction(event -> navigateToTransaction());
    }

    public void setOrderData(ObservableList<FoodItem> orderData, String totalPrice) {
        this.orderData.setAll(orderData);
        this.totalPriceLabel.setText(totalPrice);

    }

    private void navigateToTransaction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/burritoking/view/Transaction.fxml"));
            Stage stage = (Stage) proceedToPaymentButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
