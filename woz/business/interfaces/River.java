package business.interfaces;

public interface River {
    /**
     * Gets the status of the river.
     *
     * @return Returns an integer value of the river status
     */
    int getRiverStatus();

    /**
     * Sets a river status.
     *
     * @param status Status to be added.
     */
    void setRiverStatus(int status);

    /**
     * Sets up some system prints on the screen for each time the player visits the river.
     */
    void visitRiver();

}