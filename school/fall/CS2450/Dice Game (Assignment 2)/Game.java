//Shayna Stewart
//Assignment 2: Dice game
//Due: 10/31/19

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;

public class Game extends Application {

    Image d1;
    Image d2;
    Image d3;
    Image d4;
    Image d5;
    Image d6;
    Image d1h;
    Image d2h;
    Image d3h;
    Image d4h;
    Image d5h;
    Image d6h;

    int heldNum;
    Dice[] diceArr;
    Image images[];
    ImageView[] imageViews;
    int overallScore;
    int roundScore;
    int rollsRemaining;
    boolean win;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage myStage) throws FileNotFoundException {

        FileInputStream input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice1.png");
        d1 = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice2.png");
        d2 = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice3.png");
        d3 = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice4.png");
        d4 = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice5.png");
        d5 = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice6.png");
        d6 = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice1Held.png");
        d1h = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice2Held.png");
        d2h = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice3held.png");
        d3h = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice4held.png");
        d4h = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice5held.png");
        d5h = new Image(input);
        input = new FileInputStream("C:\\Users\\shayn\\OneDrive\\Documents\\NetBeansProjects\\Dice\\src\\resources\\images\\Dice6held.png");
        d6h = new Image(input);
        overallScore = 0;
        roundScore = 0;
        rollsRemaining = 3;
        win = false;
        diceArr = new Dice[5];
        for (int i = 0; i < 5; i++) {
            diceArr[i] = new Dice();
        }
        imageViews = new ImageView[5];
        setupImages();

        Label OverAllScore = new Label("Overall Score: " + overallScore);
        Label RoundScore = new Label("Round Score: " + roundScore);
        Label RollsRemaining = new Label("Rolls Remaining: " + rollsRemaining);

        Button roll = new Button("Roll Dice");
        roll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (rollsRemaining == 0) {

                    //this resets the game but keeps the overall score for the new game
                    win = false;
                    roll.setText("Roll Dice");
                    rollsRemaining = 3;
                    RollsRemaining.setText("Rolls Remaining: " + rollsRemaining);
                    roundScore = 0;
                    RoundScore.setText("Round Score: " + roundScore);
                    for (int i = 0; i < 5; i++) {
                        diceArr[i] = new Dice();
                    }
                    imageViews = new ImageView[5];
                    setupImages();

                    HBox hbox = new HBox();
                    GridPane gridpane = new GridPane();
                    gridpane.setPadding(new Insets(5, 5, 5, 5));
                    gridpane.setHgap(10);
                    gridpane.setVgap(10);

                    hbox.getChildren().addAll(imageViews[0], imageViews[1], imageViews[2], imageViews[3], imageViews[4]);
                    hbox.setSpacing(20);
                    gridpane.getChildren().add(hbox);
                    VBox vbox = new VBox(10, OverAllScore, hbox, roll, RoundScore, RollsRemaining);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.setPadding(new Insets(10));
                    Scene myScene = new Scene(vbox);
                    myScene.getStylesheets().add("Die.css");

                    myStage.setScene(myScene);
                    myStage.setTitle("Dice Game");
                    myStage.show();
                } else if (rollsRemaining == 1) // if user uses all 3 turns, show result instead of rolls remaining, and add to scores
                {
                    rollDice();
                    rollsRemaining--;
                    win = true;
                    String hand = handCalculator();
                    RollsRemaining.setText(hand + "!");
                    roll.setText("New Game");
                    overallScore += roundScore;

                    OverAllScore.setText("Overall Score: " + overallScore);
                    RoundScore.setText("Round Score: " + roundScore);

                } else if (rollsRemaining > 1) {
                    rollDice();
                    rollsRemaining--;
                    RollsRemaining.setText("Rolls Remaining: " + rollsRemaining);

                }

            }

        });

        HBox hbox = new HBox();
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5, 5, 5, 5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        hbox.getChildren().addAll(imageViews[0], imageViews[1], imageViews[2], imageViews[3], imageViews[4]);
        hbox.setSpacing(20);
        gridpane.getChildren().add(hbox);
        VBox vbox = new VBox(10, OverAllScore, hbox, roll, RoundScore, RollsRemaining);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        Scene myScene = new Scene(vbox);
        myScene.getStylesheets().add("Die.css");

        myStage.setScene(myScene);
        myStage.setTitle("Dice Game");
        myStage.show();
    }

    public String handCalculator() {
        String handType;
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        int[] values = new int[5];
        for (int x = 0; x < 5; x++) {
            values[x] = diceArr[x].val;
            switch (values[x]) {
                case 1:
                    ones++;
                    break;
                case 2:
                    twos++;
                    break;
                case 3:
                    threes++;
                    break;
                case 4:
                    fours++;
                    break;
                case 5:
                    fives++;
                    break;
                case 6:
                    sixes++;
                    break;
            }
        }

        //5 of a kind
        if (ones == 5 || twos == 5 || threes == 5 || fours == 5 || fives == 5 || sixes == 5) {
            handType = "Five of a Kind";
            roundScore = 10;
        } //straight
        else if ((ones == 1 && twos == 1 && threes == 1 && fours == 1 && fives == 1) || (sixes == 1 && twos == 1 && threes == 1 && fours == 1 && fives == 1)) {
            handType = "Straight";
            roundScore = 8;
        } //four of a kind
        else if (ones == 4 || twos == 4 || threes == 4 || fours == 4 || fives == 4 || sixes == 4) {
            handType = "Four of a Kind";
            roundScore = 7;
        } //full house
        else if ((ones == 3 || twos == 3 || threes == 3 || fours == 3 || fives == 3 || sixes == 3) && (ones == 2 || twos == 2 || threes == 2 || fours == 2 || fives == 2 || sixes == 2)) {
            handType = "Full House";
            roundScore = 6;
        } //three of a kind
        else if (ones == 3 || twos == 3 || threes == 3 || fours == 3 || fives == 3 || sixes == 3) {
            handType = "Three of a Kind";
            roundScore = 5;
        } //two pair
        else if ((ones == 2 && twos == 2)
                || (ones == 2 && threes == 2)
                || (ones == 2 && fours == 2)
                || (ones == 2 && fives == 2)
                || (ones == 2 && sixes == 2)
                || (twos == 2 && threes == 2)
                || (twos == 2 && fours == 2)
                || (twos == 2 && fives == 2)
                || (twos == 2 && sixes == 2)
                || (threes == 2 && fours == 2)
                || (threes == 2 && fives == 2)
                || (threes == 2 && sixes == 2)
                || (fours == 2 && fives == 2)
                || (fours == 2 && sixes == 2)
                || (fives == 2 && sixes == 2)) {
            handType = "Two Pair";
            roundScore = 4;
        } //two of a kind
        else if (ones == 2 || twos == 2 || threes == 2 || fours == 2 || fives == 2 || sixes == 2) {
            handType = "Two of a Kind";
            roundScore = 1;
        } else {
            handType = "Lose";
        }

        return handType;
    }

    public void setupImages() {

        for (int i = 0; i < 5; i++) {
            imageViews[i] = new ImageView(d1);
            imageViews[i].setFitHeight(150);
            imageViews[i].setFitWidth(150);
            // imageViews[i].setCache(true);
        }

        imageViews[0].addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (rollsRemaining < 3) {
                if (diceArr[0].held == false) {
                    diceArr[0].held();
                    imageViews[0].setImage(held(diceArr[0].val));
                } else if (diceArr[0].held == true) {
                    diceArr[0].unheld();
                    imageViews[0].setImage(assignImg(diceArr[0].val));

                }
            }
        });
        imageViews[1].addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (rollsRemaining < 3) {
                if (diceArr[1].held == false) {
                    diceArr[1].held();
                    imageViews[1].setImage(held(diceArr[1].val));
                } else if (diceArr[1].held == true) {
                    diceArr[1].unheld();
                    imageViews[1].setImage(assignImg(diceArr[1].val));
                }
            }
        });
        imageViews[2].addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (rollsRemaining < 3) {
            }
            if (diceArr[2].held == false) {
                diceArr[2].held();
                imageViews[2].setImage(held(diceArr[2].val));
            } else if (diceArr[2].held == true) {
                diceArr[2].unheld();
                imageViews[2].setImage(assignImg(diceArr[2].val));
            }
        }
        );
        imageViews[3].addEventHandler(MouseEvent.MOUSE_CLICKED,
                (MouseEvent event) -> {
                    if (rollsRemaining < 3) {
                        if (diceArr[3].held == false) {
                            diceArr[3].held();
                            imageViews[3].setImage(held(diceArr[3].val));
                        } else if (diceArr[3].held == true) {
                            diceArr[3].unheld();
                            imageViews[3].setImage(assignImg(diceArr[3].val));
                        }
                    }
                }
        );
        imageViews[4].addEventHandler(MouseEvent.MOUSE_CLICKED,
                (MouseEvent event) -> {
                    if (rollsRemaining < 3) {
                        if (diceArr[4].held == false) {
                            diceArr[4].held();
                            imageViews[4].setImage(held(diceArr[4].val));
                        } else if (diceArr[4].held == true) {
                            diceArr[4].unheld();
                            imageViews[4].setImage(assignImg(diceArr[4].val));
                        }

                    }
                }
        );
    }

    public void rollDice() {
        Random random = new Random();
        for (int x = 0; x < 5; x++) { //for each die
            if (diceArr[x].held == false) { //if the die is not held
                diceArr[x].val = random.nextInt(6) + 1; //assign a random value btwn 1 & 6 to simulate rolling

                imageViews[x].setImage(assignImg(diceArr[x].val)); //update the image to match the rolled value

            }

        }
    }

    public Image held(int n) {
        switch (n) {
            case 1:

                return d1h;

            case 2:
                return d2h;

            case 3:
                return d3h;

            case 4:
                return d4h;

            case 5:
                return d5h;

            case 6:
                return d6h;

        }
        return null;
    }

    public Image assignImg(int n) {

        switch (n) {
            case 1:

                return d1;
            case 2:
                return d2;
            case 3:
                return d3;
            case 4:
                return d4;
            case 5:
                return d5;
            case 6:
                return d6;

        }
        return null;
    }

}
