package business.implementations;
import business.interfaces.River;

public class RiverImplementation implements River{
    // info on status for the pollution of the river, from 0 to 100,
    private int riverStatus = 0;
    boolean isDanger = false;
    public int getRiverStatus() {
        return riverStatus;
    }
    public void setRiverStatus(int status) {
        if (riverStatus + status < 100) {
            riverStatus += status;
        }
        if (riverStatus + status >= 100) {
            riverStatus = 100;
            isDanger = true;
        }

    }

    public void visitRiver() {
        if (riverStatus < 20) {
            System.out.println("The birds are singing and as you cross the river towards the city, you notice that the fresh water in the river is so clean that you see a fish standing in the shade under the bridge");
        }
        else if (riverStatus < 40) {
            System.out.println("Walking over the bridge towards the city, you notice that the water is slightly more green than usual..");
        }
        else if (riverStatus < 60) {
            System.out.println("Walking over the bridge towards the city, you notice that the water is QUITE more green than usual..");
        }
        else if (riverStatus < 80) {
            System.out.println("There is a undefined smell as you cross the bridge towards the city, the water is quite unclear!");
        }
        else if (riverStatus < 100) {
            System.out.println("There is a ROTTEN smell as you cross the bridge towards the city, the water is very unclear!!!");
        }
        else if (riverStatus == 100) {
            System.out.println("There is a ROTTEN smell as you cross the bridge towards the city, you see DEAD fish floating on the river!!!!");
        }

    }
}
