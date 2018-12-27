package sprites;
/**
 * @author David Abramov.
 * Counter class implementation.
 */
public class Counter {
    private int value;
    /**
     * Counter object constructor.
     * @param num **integer**
     */
    public Counter(int num) {
        this.value = num;
    }
    /**
     * increases current value by inputed number.
     * @param number **integer**
     */
    public void increase(int number) {
        this.value += number;
    }
    /**
     * decreases current value by inputed number.
     * @param number **integer**
     */
    public void decrease(int number) {
        this.value -= number;
    }
    /**
     * returns current value.
     * @return **integer**
     */
    public int getValue() {
        return this.value;
    }
}