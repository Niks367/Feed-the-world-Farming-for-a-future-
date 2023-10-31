package business.interfaces;

public interface Farm {
    /**
     * Gets the amount of phosphor.
     *
     * @return      Returns the amount of phosphor.
     */
    int getScale();
    void runDay();
    void endDay();
}