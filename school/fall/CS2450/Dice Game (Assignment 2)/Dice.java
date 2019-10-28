public class Dice {

    int val;
    boolean held;

    Dice() {
        val = 1;
        held = false;

    }

    public void held() {

        held = true;
    }

    public void unheld() {

        held = false;
    }

}
