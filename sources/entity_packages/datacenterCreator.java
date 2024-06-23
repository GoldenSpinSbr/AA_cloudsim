package entity_packages;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;


public class datacenterCreator {
	// NOT USED IN THIS TASKS
	public static Datacenter createDatacenter(List<Host> hostList) throws Exception {
    	System.out.println("========== Create Datacenter ==========");

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
            "x86", "Linux", "Xen", hostList, 10.0, 1.0, 0.05, 0.001, 0.0);
        
        VmAllocationPolicySimple vmAllocationPolicy = new VmAllocationPolicySimple(hostList);
        
        //customizedVMAllocationPolicy vmAllocationPolicy = new customizedVMAllocationPolicy(hostList);
        List<Storage> storageList = new ArrayList<>();

        Datacenter dc = new Datacenter(
			"Datacenter0",
	        characteristics,
	        vmAllocationPolicy,
	        storageList,
	        0);//SCHEDULING_INTERNAL);
        return dc;
    }
}



