package custom_packages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.Vm;


public class customizedVMAllocationPolicy extends VmAllocationPolicy {
	private final Map<String, Host> vmTable;

    public customizedVMAllocationPolicy(List<? extends Host> list) {
        super(list);
        this.vmTable = new HashMap<>();
    }

    @Override
    public boolean allocateHostForVm(Vm vm) {
        for (Host host : getHostList()) {
            if (allocateHostForVm(vm, host)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean allocateHostForVm(Vm vm, Host host) {
        if (host.isSuitableForVm(vm)) {
            if (host.vmCreate(vm)) {
                vm.setHost(host);
                vmTable.put(vm.getUid(), host);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deallocateHostForVm(Vm vm) {
        Host host = vm.getHost();
        if (host != null) {
            host.vmDestroy(vm);
            vmTable.remove(vm.getUid());
        }
    }

    @Override
    public Host getHost(Vm vm) {
        return vmTable.get(vm.getUid());
    }

    @Override
    public Host getHost(int vmId, int userId) {
        for (Host host : getHostList()) {
            for (Vm vm : host.getVmList()) {
                if (vm.getId() == vmId && vm.getUserId() == userId) {
                    return host;
                }
            }
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> optimizeAllocation(List<? extends Vm> vmList) {
        // Implement optimization logic if needed
        return null;
    }
}
