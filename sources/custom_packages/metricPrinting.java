package custom_packages;

import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.Host;

public class metricPrinting {

	public static void printCloudletList(List<Cloudlet> list) { // ok
        String indent = "    ";
        System.out.println();
        System.out.println("========== OUTPUT ==========");
        System.out.println("Cloudlet ID" + indent + "STATUS" + indent +
                "Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" 
        		+ indent + "Finish Time");

        for (Cloudlet cloudlet : list) {
            System.out.print(indent + cloudlet.getCloudletId() + indent + indent);

            if (cloudlet.getStatus() == Cloudlet.SUCCESS) {
                System.out.print("SUCCESS");

                System.out.println(indent + indent + cloudlet.getResourceId() + indent + indent 
                		+ indent + cloudlet.getVmId() + indent + indent + cloudlet.getActualCPUTime() 
                		+ indent + indent + cloudlet.getExecStartTime() + indent + indent 
                		+ cloudlet.getFinishTime());
            }
        }
	 }
	
	public static void printRequestedMIPS(List<Host> hostList) { // don't touch
    	System.out.println();
        System.out.println("========== Resquested MIPS ==========");
        for (Host host : hostList) {
            List<customizedVM> vmsOnHost = host.getVmList();
            System.out.println("VMs on Host " + host.getId() + ":");
            for (customizedVM vm : vmsOnHost) {
                double currentVmCpuUtilization = vm.getCurrentRequestedTotalMips();
                System.out.println("VM ID: " + vm.getId());
                System.out.println("Requested MIPS: " + currentVmCpuUtilization);
            }
        }
    }
	
	public static void printUtilizationHistory(List<Host> hostList) { // don't touch
	    // Example of iterating through hostList to print CPU utilization
		System.out.println("========== CPU Utilization History ==========");
        for (Host host : hostList) {
            System.out.println("Host ID: " + host.getId());
            System.out.println();
            List<customizedVM> vmsOnHost = host.getVmList();
            System.out.println("VMs on Host " + host.getId() + ":");
            for (customizedVM vm : vmsOnHost) {
                //double currentVmCpuUtilization = vm.getCurrentRequestedTotalMips();
            	List<Double> utilizationHistory = vm.getUtilizationHistory();
                System.out.println("VM ID: " + vm.getId());
                System.out.println("Current CPU Utilization History: " + utilizationHistory);
                System.out.println();
            }
        }
    }
    
	public static void printMaxCpuUtilization(List<Host> hostList) {
        // Example of iterating through hostList to print CPU utilization
           System.out.println("========== Host CPU Utilization ==========");
           for (Host host : hostList) {
               System.out.println("Host ID: " + host.getId());
               double totalMIPS = host.getMaxAvailableMips();
               double sum = 0;
               List<customizedVM> vmsOnHost = host.getVmList();
               for (customizedVM vm : vmsOnHost) {
            	   sum += vm.getCurrentRequestedTotalMips();
               }
               System.out.println("Max Utilization: " + sum/totalMIPS);
           }
       }
	 
	public static void printPercentCpuUtilization(List<Host> hostList) { // don't touch
    	System.out.println();
        System.out.println("========== CPU Utilization Percentage ==========");
        for (Host host : hostList) {
            //List<customizedVM> vmsOnHost = host.getVmList();
        	List<customizedVM> vmsOnHost = host.getVmList();
            System.out.println("VMs on Host " + host.getId() + ":");
            //for (customizedVM vm : vmsOnHost) {
            for (customizedVM vm : vmsOnHost) {
                double averageUtilization = vm.getAverageUtilization();
                System.out.printf("VM ID: %d, Average CPU Utilization: %.2f%%\n", vm.getId(), averageUtilization * 100);
            }
        }
    }
	
	public static void printTotalCpuUtilization(List<Host> hostList) { // don't touch
    	System.out.println();
        System.out.println("========== Total CPU Utilization ==========");
        for (Host host : hostList) {
            //List<customizedVM> vmsOnHost = host.getVmList();
        	List<customizedVM> vmsOnHost = host.getVmList();
            System.out.println("VMs on Host " + host.getId() + ":");
            //for (customizedVM vm : vmsOnHost) {
            for (customizedVM vm : vmsOnHost) {
                double averageUtilization = vm.getAverageUtilization();
                double currentVmCpuUtilization = vm.getCurrentRequestedTotalMips();
                System.out.printf("VM ID: %d, Average CPU Utilization: %.2f\n", vm.getId(), 
                	averageUtilization * currentVmCpuUtilization);
            }
        }
    }
    
	public static void printPercentRAMUtilization(List<Host> hostList) { // don't touch
    	System.out.println();
    	System.out.println("========== RAM Utilization Percentage ==========");
        for (Host host : hostList) {
            List<customizedVM> vmsOnHost = host.getVmList();
            System.out.println("VMs on Host " + host.getId() + ":");
            for (customizedVM vm : vmsOnHost) {
                double currentVmCpuUtilization = vm.getRamUtilization(); //vm.getUpdateAllocatedRam();
                System.out.println("VM ID: " + vm.getId());
                System.out.println("Requested Ram: " + currentVmCpuUtilization  * 100 + "%");
            }
        }
    }
	
	public static void printTotalRAMUtilization(List<Host> hostList) { // don't touch
    	System.out.println();
    	System.out.println("========== Total RAM Utilization ==========");
        for (Host host : hostList) {
            List<customizedVM> vmsOnHost = host.getVmList();
            System.out.println("VMs on Host " + host.getId() + ":");
            for (customizedVM vm : vmsOnHost) {
                double currentVmCpuUtilization = vm.getRamUtilization() * vm.getUpdateAllocatedRam();
                System.out.println("VM ID: " + vm.getId());
                System.out.println("Requested Ram: " + currentVmCpuUtilization);
            }
        }
    }
    
	public static void printPercentBwUtilization(List<Host> hostList) { // don't touch
    	System.out.println();
    	System.out.println("========== Bw Utilization Percentage ==========");
        for (Host host : hostList) {
            List<customizedVM> vmsOnHost = host.getVmList();
            System.out.println("VMs on Host " + host.getId() + ":");
            for (customizedVM vm : vmsOnHost) {
                double currentVmCpuUtilization = vm.getBwUtilization();
                System.out.println("VM ID: " + vm.getId());
                System.out.println("Requested Bw: " + currentVmCpuUtilization * 100 + "%");
            }
        }
    }
	
	public static void printTotalBwUtilization(List<Host> hostList) { // don't touch
    	System.out.println();
    	System.out.println("========== Total Bw Utilization ==========");
        for (Host host : hostList) {
            List<customizedVM> vmsOnHost = host.getVmList();
            System.out.println("VMs on Host " + host.getId() + ":");
            for (customizedVM vm : vmsOnHost) {
                double currentVmCpuUtilization = vm.getBwUtilization() * vm.getUpdateAllocatedBw();
                System.out.println("VM ID: " + vm.getId());
                System.out.println("Requested Bw: " + currentVmCpuUtilization);
            }
        }
    }
}


