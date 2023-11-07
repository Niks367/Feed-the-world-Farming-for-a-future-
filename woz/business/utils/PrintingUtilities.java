package business.utils;

public class PrintingUtilities {
    /**
     * Prints out a specified string to the screen, has an exception handling in case of bad input.
     *
     * @param text Text to be printed out.
     */
    public static void printOnScreen(String text) {
        try {
            System.out.println(text);
        } catch (Exception e) {
            System.out.println("Something went wrong with printing");
            e.printStackTrace();
        }
    }
}
