package project_packages;

import java.util.Calendar;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;
//import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
//import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;
//import org.cloudbus.cloudsim.power.PowerDatacenter;
import org.cloudbus.cloudsim.Datacenter;

import entity_packages.VMCreator;
//import entity_packages.customizedVMScheduler;
import entity_packages.VMScheduler;
import entity_packages.brokerCreator;
import entity_packages.hostCreator;
import entity_packages.cloudletsCreator;
import entity_packages.datacenterCreator;

import custom_packages.customizedVM;
import custom_packages.metricPrinting;

public class aa1 {
	// Datacenter Config
	private static final int HOST_NUM = 3;
	private static final int HOST_PES = 10;
	private static final int HOST_MIPS = 5000;
	private static final int HOST_RAM = 8192;
	private static final int HOST_BW = 10000;
	
	// Broker Config
	private static int BROKER_ID = 0;
	
	// VM Config
	private static final int VM_NUM = 20; //30;
	private static final int VM_MIPS = 1000;
	private static final int VM_PE = 4;
	private static final int VM_RAM = 512;
	private static final int VM_BW = 1000;
	private static final int VM_SIZE = 100;
	
	// Cloudlet Config
	private static final int CLOUDLET_NUM = 15;
	private static final int CLOUDLET_LEN = 10;
	private static final int CLOUDLET_PES = 1;
	
	// 
	private DatacenterBroker broker0;
	private List<customizedVM> vmList;
	private List<Cloudlet> cloudletList;
	
	public static void main(String[] args) throws Exception {
		new aa1();
	}
	
	public aa1() throws Exception {
		// Initialize CloudSim
        int numUser = 1;
        Calendar calendar = Calendar.getInstance();
        boolean traceFlag = false;
        CloudSim.init(numUser, calendar, traceFlag);
        
        // Create Hosts
        List<Host> hostList = hostCreator.createHost(HOST_NUM, HOST_PES, 
    			HOST_MIPS, HOST_RAM, HOST_BW);
        // Create Datacenter
    	//PowerDatacenter datacenter0 = powerDatacenterCreator.createDatacenter(HOST_NUM, HOST_PES, 
    	//		HOST_MIPS, HOST_RAM, HOST_BW);
        //DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
        //        "x86", "Linux", "Xen", hostList, 10.0, 1.0, 0.05, 0.001, 0.0);
    	Datacenter datacenter0 = datacenterCreator.createDatacenter(hostList);
        
        // Create Broker
        broker0 = brokerCreator.createBroker();
        BROKER_ID = broker0.getId();
        
        // Create VMs
        //vmList = PowerVMCreator.createVMs(BROKER_ID, VM_NUM, VM_MIPS, VM_PE, VM_RAM, VM_BW, VM_SIZE);
        vmList = VMCreator.createVMs(BROKER_ID, VM_NUM, VM_MIPS, VM_PE, VM_RAM, VM_BW, VM_SIZE);
        
        // Schedule VMs using VMScheduler
        //double initialCpuThreshold = 0.8;
        
        VMScheduler.scheduleVMsRoundRobin(datacenter0, vmList);
        //VMScheduler.scheduleVMsMaxmin(datacenter0, vmList);
        //VMScheduler.scheduleVMsFirstFit(datacenter0, vmList);
        //VMScheduler.scheduleVMsGA(datacenter0, vmList);
        
        broker0.submitVmList(vmList);
        
        
        // Create Cloudlets
        cloudletList = cloudletsCreator.createCloudlets(BROKER_ID, CLOUDLET_NUM, CLOUDLET_LEN, 
        		CLOUDLET_PES);

        // Submit Cloudlet list to broker
        broker0.submitCloudletList(cloudletList);
        
        for (customizedVM v : vmList) {
        	System.out.println("Vm : " + v);
        }

		// Start the simulation
        CloudSim.startSimulation();
        // Stop the simulation
        CloudSim.stopSimulation();

        // Print results
        List<Cloudlet> newList = broker0.getCloudletReceivedList();
        metricPrinting.printCloudletList(newList);
        metricPrinting.printRequestedMIPS(hostList);
        metricPrinting.printUtilizationHistory(hostList);
        metricPrinting.printMaxCpuUtilization(hostList);
        
        metricPrinting.printPercentCpuUtilization(hostList);
        metricPrinting.printTotalCpuUtilization(hostList);
        metricPrinting.printPercentRAMUtilization(hostList);
        metricPrinting.printTotalRAMUtilization(hostList);
        metricPrinting.printPercentBwUtilization(hostList);
        metricPrinting.printTotalBwUtilization(hostList);
       
        List<Host> hostListX = datacenter0.getHostList();
        for (Host host : hostListX) {
            List<customizedVM> vmList = host.getVmList();
            System.out.println("Host " + host.getId() + " has VMs:");
            for (customizedVM vm : vmList) {
                System.out.println("  VM ID: " + vm.getId() + ", MIPS: " + vm.getMips());
                // Print other relevant VM attributes
            }
        }
        
        System.out.println("DONE");
	}
}









