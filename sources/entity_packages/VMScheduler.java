package entity_packages;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.cloudbus.cloudsim.power.PowerDatacenter;
import org.cloudbus.cloudsim.power.PowerHost;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;

import custom_packages.customizedVM;

public class VMScheduler {
	
	// ROUND_ROBIN
    public static void scheduleVMsRoundRobin(Datacenter datacenter, List<customizedVM> vmList) {
    	clearVmAllocations(datacenter);
    	
        List<Host> hostList = datacenter.getHostList();
        int hostIndex = 0;
        for (customizedVM vm : vmList) {
            Host host = hostList.get(hostIndex);
            System.out.println(vm);
            boolean success = host.vmCreate(vm);
            if (success) {
                vm.setHost(host);
            } else {
                System.out.println("Failed to allocate VM " + vm.getId() + " to host " + host.getId());
            }

            hostIndex = (hostIndex + 1) % hostList.size();
        }
    }
    
    // MAXMIN
    public static void scheduleVMsMaxmin(Datacenter datacenter, List<customizedVM> vmList) {
        List<Host> hostList = datacenter.getHostList();

        // Sort VMs based on MIPS (descending order)
        vmList.sort((vm1, vm2) -> Double.compare(vm2.getMips(), vm1.getMips()));

        for (customizedVM vm : vmList) {
            Host bestHost = findMaxmin(hostList, vm);
            if (bestHost != null) {
                boolean success = bestHost.vmCreate(vm); // Allocate VM to the best host
                if (success) {
                    vm.setHost(bestHost);   // Set VM's host attribute
                } else {
                    System.out.println("Failed to allocate VM " + vm.getId() + " to host " + bestHost.getId());
                }
            } else {
                System.out.println("No suitable host found for VM " + vm.getId());
                // Handle this scenario as per your application's requirements
            }
        }
    }
    private static Host findMaxmin(List<Host> hostList, Vm vm) {
        Host selectedHost = null;
        double maxRemainingCapacity = Double.MIN_VALUE;

        for (Host host : hostList) {
            if (host.isSuitableForVm(vm)) {
                // Example: Calculate a metric based on host's current state
                double hostMetric = calculateHostMetric(host);

                // Update selectedHost if current host has higher remaining capacity
                if (hostMetric > maxRemainingCapacity) {
                    maxRemainingCapacity = hostMetric;
                    selectedHost = host;
                }
            }
        }
        return selectedHost;
    }
    
    // First Fits
    public static void scheduleVMsFirstFit(Datacenter datacenter, List<customizedVM> vmList) {
        List<Host> hostList = datacenter.getHostList();
        
        for (customizedVM vm : vmList) {
            Host selectedHost = findFirstFit(hostList, vm);
            if (selectedHost != null) {
                boolean success = selectedHost.vmCreate(vm); // Allocate VM to the selected host
                if (success) {
                    vm.setHost(selectedHost);   // Set VM's host attribute
                } else {
                    System.out.println("Failed to allocate VM " + vm.getId() + " to host " + selectedHost.getId());
                    // Handle failure as needed
                }
            } else {
                System.out.println("No suitable host found for VM " + vm.getId());
                // Handle no suitable host found scenario
            }
        }
    }

    private static Host findFirstFit(List<Host> hostList, Vm vm) {
        for (Host host : hostList) {
            if (host.isSuitableForVm(vm)) { // Check if the host can accommodate the VM
                return host; // Return the first host that can accommodate the VM
            }
        }
        return null; // Return null if no suitable host is found
    }
    

    // Example method to calculate a metric for host selection
    private static double calculateHostMetric(Host host) {
        // Example: Calculate a metric based on available resources or utilization
        // Here, you might consider factors like remaining MIPS, RAM, or BW
        double remainingMips = host.getVmScheduler().getAvailableMips();
        double remainingRam = host.getRamProvisioner().getAvailableRam();
        double remainingBw = host.getBwProvisioner().getAvailableBw();

        // Combine these metrics based on your allocation strategy
        return remainingMips + remainingRam + remainingBw;
    }
    
    // Genetic Algorithm
    public static void scheduleVMsGA(Datacenter datacenter, List<customizedVM> vmList) {
    	// Clear any existing VM allocations
    	clearVmAllocations(datacenter);
    	List<Host> hostList = datacenter.getHostList();

        // Manually assign VMs to hosts
    	assignVMsManually(vmList.get(0), hostList.get(2)); // VM 0 to Host 2
    	assignVMsManually(vmList.get(1), hostList.get(1)); // VM 1 to Host 1
    	assignVMsManually(vmList.get(2), hostList.get(2)); // VM 2 to Host 2
    	assignVMsManually(vmList.get(3), hostList.get(1)); // VM 3 to Host 1
    	assignVMsManually(vmList.get(4), hostList.get(2)); // VM 4 to Host 2
    	assignVMsManually(vmList.get(5), hostList.get(1)); // VM 5 to Host 1
    	assignVMsManually(vmList.get(6), hostList.get(2)); // VM 6 to Host 2
    	assignVMsManually(vmList.get(7), hostList.get(1)); // VM 7 to Host 1
    	assignVMsManually(vmList.get(8), hostList.get(2)); // VM 8 to Host 2
    	assignVMsManually(vmList.get(9), hostList.get(1)); // VM 9 to Host 1
    	assignVMsManually(vmList.get(10), hostList.get(2)); // VM 10 to Host 2
    	assignVMsManually(vmList.get(11), hostList.get(0)); // VM 11 to Host 0
    	assignVMsManually(vmList.get(12), hostList.get(2)); // VM 12 to Host 2
    	assignVMsManually(vmList.get(13), hostList.get(0)); // VM 13 to Host 0
    	assignVMsManually(vmList.get(14), hostList.get(2)); // VM 14 to Host 2
    	assignVMsManually(vmList.get(15), hostList.get(0)); // VM 15 to Host 0
    	assignVMsManually(vmList.get(16), hostList.get(1)); // VM 16 to Host 1
    	assignVMsManually(vmList.get(17), hostList.get(0)); // VM 17 to Host 0
    	assignVMsManually(vmList.get(18), hostList.get(1)); // VM 18 to Host 1
    	assignVMsManually(vmList.get(19), hostList.get(0)); // VM 19 to Host 0

    }
    
    private static void assignVMsManually(customizedVM vm, Host host) {
        boolean success = host.vmCreate(vm);
        if (success) {
            System.out.println("Allocated VM " + vm.getId() + " to host " + host.getId());
        } else {
            System.out.println("Failed to allocate VM " + vm.getId() + " to host " + host.getId());
        }
    }

    // CLEAR ALLOCATION
    private static void clearVmAllocations(Datacenter datacenter) {
    	List<Host> hostList = datacenter.getHostList();
        for (Host host : hostList) {
            List<Vm> vmList = host.getVmList();
            for (Vm vm : new ArrayList<>(vmList)) {
            	System.out.println(vm);
                host.vmDestroy(vm);
            }
        }
    }
}



