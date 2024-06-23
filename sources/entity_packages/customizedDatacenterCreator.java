package entity_packages;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.power.models.PowerModelSpecPowerHpProLiantMl110G4Xeon3040;
import org.cloudbus.cloudsim.power.PowerHost;

import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

import custom_packages.customizedPe;
import custom_packages.customizedPowerDatacenter;

public class customizedDatacenterCreator {
	public static customizedPowerDatacenter createDatacenter(int host_num, int host_pes, 
		int host_mips, int host_ram, int host_bw) throws Exception {
    	System.out.println("========== Create PowerDatacenter ==========");
    	List<PowerHost> hostList = new ArrayList<>();
    	int ram = host_ram;
        int bw = host_bw;
        long storage = 100000;
    	for (int i=0; i<host_num; i++) {
	    	List<Pe> peList = new ArrayList<>();
	        for (int j=0; j<host_pes; j++) {
	            peList.add(new customizedPe((int) j, (double) host_mips));
	        }
	        PowerHost host = new PowerHost(
	            i,
	            new RamProvisionerSimple(ram),
	            new BwProvisionerSimple(bw),
	            storage,
	            peList,
	            new VmSchedulerTimeShared(peList),
	            new PowerModelSpecPowerHpProLiantMl110G4Xeon3040() // power model
	        );
	        hostList.add(host);
    	}

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
            "x86", "Linux", "Xen", hostList, 10.0, 1.0, 0.05, 0.001, 0.0);
        VmAllocationPolicySimple vmAllocationPolicy = new VmAllocationPolicySimple(hostList);
        List<Storage> storageList = new ArrayList<>();
        
        customizedPowerDatacenter dc = new customizedPowerDatacenter(
    		"Datacenter0",
    	    characteristics,
    	    vmAllocationPolicy,
    	    storageList,
    	    2.0  //default
    	);
        return dc;
    }
}