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

    void setIsInFarm(boolean z);

    boolean getIsInFarm();

    void reduceFieldsForPurchase();
    int getFieldsForPurchase();
}