package business.implementations;

import business.interfaces.Farm;

public class FarmImplementation implements Farm {
    int scalePhosphor = 0;
    @Override
    public int getScale() {
        return this.scalePhosphor;
    }
}
