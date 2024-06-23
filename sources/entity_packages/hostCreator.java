package entity_packages;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;

import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import custom_packages.customizedPe;

public class hostCreator {
	// NOT USED IN THIS TASKS
	public static List<Host> createHost(int host_num, int host_pes, int host_mips, int host_ram, 
		int host_bw) throws Exception {
    	System.out.println("========== Create Host ==========");
    	List<Host> hostList = new ArrayList<>();
    	
    	int ram = host_ram;
        int bw = host_bw;
        long storage = 100000;
        
    	for (int i=0; i<host_num; i++) {
	    	List<Pe> peList = new ArrayList<>();
	        for (int j=0; j<host_pes; j++) {
	            peList.add(new customizedPe((int) j, (double) host_mips));
	        }
	        Host host = new Host(
	            i,
	            new RamProvisionerSimple(ram),
	            new BwProvisionerSimple(bw),
	            storage,
	            peList,
	            new VmSchedulerTimeShared(peList)
	        );
	        hostList.add(host);
    	}

        return hostList;
    }
}


