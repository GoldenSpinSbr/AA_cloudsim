package entity_packages;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.CloudletSchedulerSpaceShared;

//import custom_packages.CloudletScheduler;
//import org.cloudbus.cloudsim.Vm;
import custom_packages.customizedVM;

import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.UtilizationModelNull;
import org.cloudbus.cloudsim.UtilizationModelStochastic;

public class VMCreator {
	public static List<customizedVM> createVMs(int broker_id, int vm_num, int vm_mips, int vm_pe, int vm_ram, 
		int vm_bw, int vm_size) {
    	System.out.println("========== Create VMs ==========");
    	List<customizedVM> list = new ArrayList<>();
        for (int i = 0; i < vm_num; i++) {
            customizedVM vm = new customizedVM(
                i+1000,
                broker_id,
                vm_mips,
                vm_pe,
                vm_ram,
                vm_bw,
                vm_size,
                "Xen",
                new CloudletSchedulerSpaceShared()
                //new CloudletSchedulerTimeShared()
            );
            //vm.setUtilizationModelRam(new UtilizationModelFull());
            vm.setUtilizationModelRam(new UtilizationModelStochastic());
            vm.setUtilizationModelBw(new UtilizationModelStochastic());
            list.add(vm);
        }
        return list;
    }
}



