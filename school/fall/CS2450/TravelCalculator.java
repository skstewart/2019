
/**
 * Name: Shayna Stewart
 * CS2450.02
 * HW1: Travel Cost Calculator
 * Summary: Calculates total cost spent on business trip, reimbursement owed, and final costs owed by the business-person
 * after reimbursements
 * */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class TravelCalculator extends Application {

    private Label totalCharge;
    private Label totalReimbursement;
    private Label finalCharge;
    private TextField totalChargeField;
    private TextField totalReimbursementField;
    private TextField finalChargeField;

    private TextField tripLength;
    private TextField dailyLodge;
    private TextField airFare;
    private TextField miles;
    private TextField registrationFees;
    private TextField foodFees;
    //  private Button calcButton;

    public static void main(String[] args) {
        // Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        totalCharge = new Label("Total Charge Incurred: ");
        totalReimbursement = new Label("Reimbursement: ");
        finalCharge = new Label("Final Charge Due: ");
        totalChargeField = new TextField();
        totalReimbursementField = new TextField();
        finalChargeField = new TextField();

        tripLength = new TextField();
        dailyLodge = new TextField();
        airFare = new TextField();
        miles = new TextField();
        registrationFees = new TextField();
        foodFees = new TextField();

        Label tripLengthLabel = new Label("Trip Length (in days):  ");
        Label dailyLodgeLabel = new Label("Daily Lodge Fees:  ");
        Label airFareLabel = new Label("Air Fare:  ");
        Label milesLabel = new Label("Miles Driven:  ");
        Label registrFeesLabel = new Label("Total Registration Fees:  ");
        Label foodFeesLabel = new Label("Total Food Fees:  ");

        Button calcButton = new Button("Calculate");
        calcButton.setOnAction(new CalcButtonHandler());

        GridPane gridPane = new GridPane();

        gridPane.setMinSize(400, 200);

        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(25);
        gridPane.setHgap(25);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(tripLengthLabel, 0, 0);
        gridPane.add(tripLength, 1, 0);
        gridPane.add(dailyLodgeLabel, 2, 0);
        gridPane.add(dailyLodge, 3, 0);
        gridPane.add(foodFeesLabel, 0, 1);
        gridPane.add(foodFees, 1, 1);
        gridPane.add(airFareLabel, 2, 1);
        gridPane.add(airFare, 3, 1);
        gridPane.add(milesLabel, 0, 2);
        gridPane.add(miles, 1, 2);
        gridPane.add(registrFeesLabel, 2, 2);
        gridPane.add(registrationFees, 3, 2);
        gridPane.add(calcButton, 1, 3);
        gridPane.add(totalCharge, 2, 3);
        gridPane.add(totalChargeField, 3, 3);
        gridPane.add(totalReimbursement, 0, 4);
        gridPane.add(totalReimbursementField, 1, 4);
        gridPane.add(finalCharge, 2, 4);
        gridPane.add(finalChargeField, 3, 4);

        //Show window
        Scene myScene = new Scene(gridPane);
        primaryStage.setTitle("Travel Calculator");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    class CalcButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            double days;
            double mealValue;
            double lodgeValue;
            double milesValue;
            double airFareValue;
            double registrCost;
            double totalCost;
            double totalDue; //after reimbursements
            double reimbursed;

            if (tripLength.getText().isEmpty() || foodFees.getText().isEmpty() || dailyLodge.getText().isEmpty() || registrationFees.getText().isEmpty()) {
                //checking to make sure user enters all fields

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter input for all fields. For transportation, please put input for one method and leave the other blank.");

                alert.showAndWait();

                totalChargeField.setText("error");
                totalReimbursementField.setText("error");
                finalChargeField.setText("error");

            } else {

                days = Double.parseDouble(tripLength.getText());

                mealValue = Double.parseDouble(foodFees.getText());

                lodgeValue = Double.parseDouble(dailyLodge.getText());

                registrCost = Double.parseDouble(registrationFees.getText());

                if ((airFare.getText().isEmpty() == false) && (miles.getText().isEmpty() == false)) {
                    //if both airfare and miles are entered, then an error pops up

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter only miles or air fare, and leave the other blank.");

                    alert.showAndWait();

                    totalChargeField.setText("error");
                    totalReimbursementField.setText("error");
                    finalChargeField.setText("error");

                } else if (airFare.getText().isEmpty() && miles.getText().isEmpty() == false) {

                    milesValue = Double.parseDouble(miles.getText());

                    totalCost = mealValue + (lodgeValue * days) + (milesValue * .42) + registrCost;

                    reimbursed = registrCost + (milesValue * .42); //reimburse $0.42 per mile driven, and entire registration cost

                    if (lodgeValue <= 195) { //reimburse the lodging cost ($195 per day)
                        reimbursed += (lodgeValue * days); //if lodging was less than 195 cap, reimburse entire lodging cost
                    } else {
                        reimbursed += (days * 195); //else reimburse 195 per day
                    }

                    if (mealValue <= (47 * days)) { //reimburse the meal cost (up to $47 per day)
                        reimbursed += mealValue; //if food cost was less than 47 per day, reimburse entire thing
                    } else {
                        reimbursed += (days * 47); //else reimburse 47 per day
                    }

                    totalDue = totalCost - reimbursed;

                    totalChargeField.setText("$" + totalCost + "");
                    totalReimbursementField.setText("$" + reimbursed + "");
                    finalChargeField.setText("$" + totalDue + "");

                } else if (airFare.getText().isEmpty() == false && miles.getText().isEmpty()) {
                    airFareValue = Double.parseDouble(airFare.getText());

                    totalCost = mealValue + (lodgeValue * days) + airFareValue + registrCost;

                    reimbursed = registrCost + airFareValue; //reimburse $0.42 per mile driven, and entire registration cost

                    if (lodgeValue <= 195) { //reimburse the lodging cost ($195 per day)
                        reimbursed += (lodgeValue * days); //if lodging was less than 195 cap, reimburse entire lodging cost
                    } else {
                        reimbursed += (days * 195); //else reimburse 195 per day
                    }

                    if (mealValue <= (47 * days)) { //reimburse the meal cost (up to $47 per day)
                        reimbursed += mealValue; //if food cost was less than 47 per day, reimburse entire thing
                    } else {
                        reimbursed += (days * 47); //else reimburse 47 per day
                    }

                    totalDue = totalCost - reimbursed;

                    totalChargeField.setText("$" + totalCost + "");
                    totalReimbursementField.setText("$" + reimbursed + "");
                    finalChargeField.setText("$" + totalDue + "");

                }
            }
        }

    }
}
