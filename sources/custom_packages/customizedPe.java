package custom_packages;

import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;

public class customizedPe extends Pe {
    public customizedPe(int id, double mips) {
        super(id, new PeProvisionerSimple(mips)); // Initialize PE allocation with PeProvisionerSimple
    }
}

