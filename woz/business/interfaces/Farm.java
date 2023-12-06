package business.interfaces;

public interface Farm {
    /**
     * Gets the amount of phosphor.
     *
     * @return Returns the amount of phosphor.
     */
    int getScale();

    /**
     * Ends the day and adds the day counter.
     */
    void endSeason();

    public void setIsInFarm(boolean z);

    public boolean getIsInFarm();

    public void reduceFieldsForPurchase();
    public int getFieldsForPurchase();
}