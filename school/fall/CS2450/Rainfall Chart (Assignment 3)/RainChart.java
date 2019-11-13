/*
Name: Shayna Stewart
CS2450 Assignment 3
Description: Rain Fall chart calculator. Allows user to enter four numerical values for the rainfall during each month, and upon submitting these values
    generates charts corresponding to the monthly values. Allows user to view three different types of chart (available in the top menu bar under view), 
    as well as the options to exit or create a new form (also in the top menu bar under file). Forces user to enter correct numeral values (ie, negative is not allowed,
    user must enter numerical digits, and cannot enter letters or other characters, and must fill each form.)
 */

import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class RainChart extends Application {

    private Menu fileMenu;
    private Menu viewMenu;
    boolean viewMenuEnabled = false;
    boolean show = true;
    private TextField janField;
    private TextField febField;
    private TextField marField;
    private TextField aprField;
    private Label janLabel;
    private Label febLabel;
    private Label marLabel;
    private Label aprLabel;

    public static void main(String[] args) {
        // Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        fileMenu = new Menu("File");
        viewMenu = new Menu("View");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, viewMenu);
        viewMenu.setDisable(true); //initially set the view menu as disabled

        MenuItem clearField = MenuItemBuilder.create()
                .text("New")
                .accelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN))
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        start(primaryStage);
                    }
                })
                .build();

        MenuItem exitField = MenuItemBuilder.create()
                .text("Exit")
                .accelerator(new KeyCodeCombination(KeyCode.ESCAPE))
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        System.exit(0);
                    }

                })
                .build();

        MenuItem bar = MenuItemBuilder.create()
                .text("Bar Chart")
                .accelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN))
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        primaryStage.setTitle("Bar Chart");
                        CategoryAxis xAxis = new CategoryAxis();
                        final NumberAxis yAxis = new NumberAxis();
                        final BarChart<String, Number> bc
                                = new BarChart<>(xAxis, yAxis);
                        bc.setTitle("Rain Fall");
                        xAxis.setLabel("Month");
                        yAxis.setLabel("Inches");

                        XYChart.Series series1 = new XYChart.Series();
                        bc.setLegendVisible(false);
                        series1.getData().add(new XYChart.Data("jan", Float.parseFloat(janField.getText())));
                        series1.getData().add(new XYChart.Data("feb", Float.parseFloat(febField.getText())));
                        series1.getData().add(new XYChart.Data("march", Float.parseFloat(marField.getText())));
                        series1.getData().add(new XYChart.Data("april", Float.parseFloat(aprField.getText())));

                        BorderPane root = new BorderPane();
                        root.setTop(menuBar);
                        bc.getData().addAll(series1);
                        root.setCenter(bc);

                        Scene scene = new Scene(root, 400, 400);
                        scene.getStylesheets().add("Style.css");

                        primaryStage.setScene(scene);
                        primaryStage.show();
                        viewMenu.setDisable(false); //enable the viewmenu 
                    }

                })
                .build();

        MenuItem area = MenuItemBuilder.create()
                .text("Area Chart")
                .accelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN))
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        primaryStage.setTitle("Area Chart");
                        CategoryAxis xAxis = new CategoryAxis();
                        final NumberAxis yAxis = new NumberAxis();
                        final AreaChart<String, Number> ac
                                = new AreaChart<>(xAxis, yAxis);
                        ac.setTitle("Rain Fall");
                        xAxis.setLabel("Month");
                        yAxis.setLabel("Inches");

                        XYChart.Series series1 = new XYChart.Series();
                        ac.setLegendVisible(false);
                        series1.getData().add(new XYChart.Data("jan", Float.parseFloat(janField.getText())));
                        series1.getData().add(new XYChart.Data("feb", Float.parseFloat(febField.getText())));
                        series1.getData().add(new XYChart.Data("march", Float.parseFloat(marField.getText())));
                        series1.getData().add(new XYChart.Data("april", Float.parseFloat(aprField.getText())));

                        BorderPane root = new BorderPane();
                        root.setTop(menuBar);
                        ac.getData().addAll(series1);
                        root.setCenter(ac);

                        Scene scene = new Scene(root, 400, 400);
                        scene.getStylesheets().add("Style.css");

                        primaryStage.setScene(scene);
                        primaryStage.show();
                        viewMenu.setDisable(false); //enable the viewmenu 
                    }

                })
                .build();

        MenuItem line = MenuItemBuilder.create()
                .text("Line Chart")
                .accelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN))
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        primaryStage.setTitle("Area Chart");
                        CategoryAxis xAxis = new CategoryAxis();
                        final NumberAxis yAxis = new NumberAxis();
                        final LineChart<String, Number> lc = new LineChart<>(xAxis, yAxis);
                        lc.setTitle("Rain Fall");
                        xAxis.setLabel("Month");
                        yAxis.setLabel("Inches");

                        XYChart.Series series1 = new XYChart.Series();
                        lc.setLegendVisible(false);
                        series1.getData().add(new XYChart.Data("jan", Float.parseFloat(janField.getText())));
                        series1.getData().add(new XYChart.Data("feb", Float.parseFloat(febField.getText())));
                        series1.getData().add(new XYChart.Data("march", Float.parseFloat(marField.getText())));
                        series1.getData().add(new XYChart.Data("april", Float.parseFloat(aprField.getText())));

                        BorderPane root = new BorderPane();
                        root.setTop(menuBar);
                        lc.getData().addAll(series1);
                        root.setCenter(lc);

                        Scene scene = new Scene(root, 400, 400);
                        scene.getStylesheets().add("Style.css");

                        primaryStage.setScene(scene);
                        primaryStage.show();
                        viewMenu.setDisable(false); //enable the viewmenu 
                    }

                })
                .build();

        fileMenu.getItems().addAll(clearField, exitField);
        viewMenu.getItems().addAll(bar, area, line);

        janLabel = new Label("January: ");
        febLabel = new Label("February: ");
        marLabel = new Label("March: ");
        aprLabel = new Label("April: ");
        janField = new TextField();
        febField = new TextField();
        marField = new TextField();
        aprField = new TextField();

        Button button = new Button("Submit");

        button.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e
            ) {

                String regex = "(.)*(\\d)(.)*";
                Pattern pattern = Pattern.compile(regex);
                Alert inputAlert = new Alert(AlertType.INFORMATION);
                inputAlert.setTitle("Error");
                inputAlert.setHeaderText(null);
                inputAlert.setContentText("Please enter input for all fields. Enter the value 0 for months with no rainfall.");

                Alert numericalAlert = new Alert(AlertType.INFORMATION);
                numericalAlert.setTitle("Error");
                numericalAlert.setHeaderText(null);
                numericalAlert.setContentText("Please enter only numerical values.");

                Alert negativeAlert = new Alert(AlertType.INFORMATION);
                negativeAlert.setTitle("Error");
                negativeAlert.setHeaderText(null);
                negativeAlert.setContentText("Please enter positive values, or the value 0.");
                if (!pattern.matcher(janField.getText()).matches() || !pattern.matcher(febField.getText()).matches() || !pattern.matcher(marField.getText()).matches() || !pattern.matcher(aprField.getText()).matches()) {
                    show = false;
                    numericalAlert.showAndWait();

                } else {
                    show = true;
                }
                if (pattern.matcher(janField.getText()).matches() || pattern.matcher(febField.getText()).matches() || pattern.matcher(marField.getText()).matches() || pattern.matcher(aprField.getText()).matches()) {

                    if (janField.getText() == "" || febField.getText() == "" || marField.getText() == "" || aprField.getText() == "") {
                        show = false;
                        inputAlert.showAndWait();
                    } else {
                        show = true;
                    }
                }
                if (pattern.matcher(janField.getText()).matches() || pattern.matcher(febField.getText()).matches() || pattern.matcher(marField.getText()).matches() || pattern.matcher(aprField.getText()).matches()) {

                    if (Float.parseFloat(janField.getText()) < 0 || Float.parseFloat(febField.getText()) < 0 || Float.parseFloat(marField.getText()) < 0 || Float.parseFloat(aprField.getText()) < 0) {
                        show = false;
                        negativeAlert.showAndWait();
                    } else {
                        show = true;
                    }
                }

                if (show == true) {

                    primaryStage.setTitle("Bar Chart");
                    CategoryAxis xAxis = new CategoryAxis();
                    final NumberAxis yAxis = new NumberAxis();
                    final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
                    bc.setTitle("Rain Fall");
                    xAxis.setLabel("Month");
                    yAxis.setLabel("Inches");

                    XYChart.Series series1 = new XYChart.Series();
                    bc.setLegendVisible(false);
                    series1.getData().add(new XYChart.Data("jan", Float.parseFloat(janField.getText())));
                    series1.getData().add(new XYChart.Data("feb", Float.parseFloat(febField.getText())));
                    series1.getData().add(new XYChart.Data("march", Float.parseFloat(marField.getText())));
                    series1.getData().add(new XYChart.Data("april", Float.parseFloat(aprField.getText())));

                    BorderPane root = new BorderPane();
                    root.setTop(menuBar);
                    bc.getData().addAll(series1);
                    root.setCenter(bc);

                    Scene scene = new Scene(root, 400, 400);
                    scene.getStylesheets().add("Style.css");

                    primaryStage.setScene(scene);
                    primaryStage.show();
                    viewMenu.setDisable(false); //enable the viewmenu 

                }
            }
        }
        );
        BorderPane root = new BorderPane();

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(0, 0, 0, 45));
        gridPane.setVgap(25);
        gridPane.setHgap(25);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(janLabel,
                0, 0);
        gridPane.add(janField,
                1, 0);
        gridPane.add(febLabel,
                0, 1);
        gridPane.add(febField,
                1, 1);
        gridPane.add(marLabel,
                0, 2);
        gridPane.add(marField,
                1, 2);
        gridPane.add(aprLabel,
                0, 3);
        gridPane.add(aprField,
                1, 3);

        HBox buttons = new HBox();

        buttons.getChildren().addAll(button);
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        GridPane innergrid = new GridPane();

        innergrid.setAlignment(Pos.CENTER);

        innergrid.add(buttons, 0, 0);
        innergrid.setPadding(new Insets(0, 45, 0, 0));
        gridPane.add(innergrid, 0, 4, 4, 1);

        root.setTop(menuBar);

        root.setCenter(gridPane);
        Scene myScene = new Scene(root, 400, 400);

        myScene.getStylesheets().add("Style.css");

        primaryStage.setScene(myScene);

        primaryStage.setTitle("Rain Fall");
        primaryStage.show();

    }

}
