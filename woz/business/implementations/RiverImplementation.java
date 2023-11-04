package business.implementations;
import business.interfaces.River;

public class RiverImplementation implements River{
    // info on status for the pollution of the river, from 0 to 100,
    private int riverStatus = 0;
    boolean isDanger = false;
    @Override
    public int getRiverStatus() {
        return riverStatus;
    }
    @Override
    public void setRiverStatus(int status) {
        if (riverStatus + status < 100) {
            riverStatus += status;
        }
        if (riverStatus + status >= 100) {
            riverStatus = 100;
            isDanger = true;
        }

    }
    @Override
    public void visitRiver() {
        if (riverStatus < 20) {
            System.out.println("\033[3mThe birds are singing and as you cross the river towards the city,\nyou notice that the fresh water in the river is so clean that you see a fish standing in the shade under the bridge.\033[0m");
        }
        else if (riverStatus < 40) {
            System.out.println("\033[3mWalking over the bridge towards the city, you notice that the water is slightly more green than usual..\033[0m");
        }
        else if (riverStatus < 60) {
            System.out.println("\033[3mWalking over the bridge towards the city, you notice that the water is QUITE more green than usual..\033[0m");
        }
        else if (riverStatus < 80) {
            System.out.println("\033[3mThere is a undefined smell as you cross the bridge towards the city, the water is quite unclear!\033[0m");
        }
        else if (riverStatus < 100) {
            System.out.println("\033[3mThere is a ROTTEN smell as you cross the bridge towards the city, the water is very unclear!!!\033[0m");
        }
        else if (riverStatus == 100) {
            System.out.println("There is a ROTTEN smell as you cross the bridge towards the city, you see DEAD fish floating on the river!!!!");
        }

    }
}
