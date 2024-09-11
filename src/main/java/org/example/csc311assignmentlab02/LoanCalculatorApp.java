package com.example.loancalculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Loan Calculator");

        // Create labels and text fields
        Label loanAmountLabel = new Label("Loan Amount:");
        TextField loanAmountField = new TextField();

        Label annualInterestRateLabel = new Label("Annual Interest Rate:");
        TextField annualInterestRateField = new TextField();

        Label numberOfYearsLabel = new Label("Number of Years:");
        TextField numberOfYearsField = new TextField();

        Label monthlyPaymentLabel = new Label("Monthly Payment:");
        TextField monthlyPaymentField = new TextField();
        monthlyPaymentField.setEditable(false);

        Label totalPaymentLabel = new Label("Total Payment:");
        TextField totalPaymentField = new TextField();
        totalPaymentField.setEditable(false);

        Button computeButton = new Button("Compute Payment");

        // Create grid pane layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Add controls to the grid
        grid.add(loanAmountLabel, 0, 0);
        grid.add(loanAmountField, 1, 0);
        grid.add(annualInterestRateLabel, 0, 1);
        grid.add(annualInterestRateField, 1, 1);
        grid.add(numberOfYearsLabel, 0, 2);
        grid.add(numberOfYearsField, 1, 2);
        grid.add(computeButton, 1, 3);
        grid.add(monthlyPaymentLabel, 0, 4);
        grid.add(monthlyPaymentField, 1, 4);
        grid.add(totalPaymentLabel, 0, 5);
        grid.add(totalPaymentField, 1, 5);

        // Set button action
        computeButton.setOnAction(e -> computePayment(
                loanAmountField.getText(),
                annualInterestRateField.getText(),
                numberOfYearsField.getText(),
                monthlyPaymentField,
                totalPaymentField
        ));

        // Set the scene
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void computePayment(String loanAmountStr, String annualInterestRateStr, String numberOfYearsStr, TextField monthlyPaymentField, TextField totalPaymentField) {
        try {
            double loanAmount = Double.parseDouble(loanAmountStr);
            double annualInterestRate = Double.parseDouble(annualInterestRateStr);
            int numberOfYears = Integer.parseInt(numberOfYearsStr);

            double monthlyInterestRate = annualInterestRate / 1200;
            int numberOfPayments = numberOfYears * 12;

            double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
            double totalPayment = monthlyPayment * numberOfPayments;

            monthlyPaymentField.setText(String.format("%.2f", monthlyPayment));
            totalPaymentField.setText(String.format("%.2f", totalPayment));
        } catch (NumberFormatException e) {
            monthlyPaymentField.setText("Invalid input");
            totalPaymentField.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
