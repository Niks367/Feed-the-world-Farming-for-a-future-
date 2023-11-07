package business.interfaces;

public interface Farm {
    /**
     * Gets the amount of phosphor.
     *
     * @return Returns the amount of phosphor.
     */
    int getScale();

    void runDay(String input);

    /**
     * Ends the day and adds the day counter.
     */
    void endDay();

    public void setIsInFarm(boolean z);

    public boolean getIsInFarm();

    void dayCount(String input);
    public void reduceFieldsForPurchase();
    public int getFieldsForPurchase();
}