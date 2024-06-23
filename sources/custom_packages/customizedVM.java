package custom_packages;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.CloudletScheduler;
import org.cloudbus.cloudsim.Vm;

import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.UtilizationModelNull;

public class customizedVM extends Vm {
	private List<Double> utilizationHistory;
	//private List<Double> RAMutilizationHistory;
	
	private UtilizationModel utilizationModelRam;
	private UtilizationModel utilizationModelBw;

    public customizedVM(int id, int userId, double mips, int numberOfPes, int ram, long bw, long size, String vmm, 
    	CloudletScheduler cloudletScheduler) {
        super(id, userId, mips, numberOfPes, ram, bw, size, vmm, cloudletScheduler);
        this.utilizationHistory = new ArrayList<>();
        //this.RAMutilizationHistory = new ArrayList<>();
        this.utilizationModelRam = new UtilizationModelNull();
        this.utilizationModelRam = new UtilizationModelNull();
    }

    public void addUtilization(double utilization) { //, double RAMutilization) {
        this.utilizationHistory.add(utilization);
        //this.RAMutilizationHistory.add(RAMutilization);
    }

    public List<Double> getUtilizationHistory() {
        return this.utilizationHistory;
    }
    
    //public List<Double> getRAMUtilizationHistory() {
    //    //return this.RAMutilizationHistory;
    //}

    public double getAverageUtilization() {
        if (utilizationHistory.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double utilization : utilizationHistory) {
            sum += utilization;
        }
        return sum / utilizationHistory.size();
    }
    /*
    public double getAverageRAMUtilization() {
        if (utilizationHistory.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double utilization : RAMutilizationHistory) {
            sum += utilization;
        }
        return sum / RAMutilizationHistory.size();
    }
	*/
    
    @Override
    public double updateVmProcessing(double currentTime, List<Double> mipsShare) {
        double utilization = getTotalUtilizationOfCpu(currentTime);
        utilization = Math.min(Math.max(utilization, 0), 1.0);
        this.addUtilization(utilization);
        
        //double RAMutilization = getTotalUtilizationOfCpu(currentTime);
        //RAMutilization = Math.min(Math.max(RAMutilization, 0), 1.0);
        //this.addUtilization(utilization, RAMutilization);
        return super.updateVmProcessing(currentTime, mipsShare);
    }

    /*
    @Override
    public double getTotalUtilizationOfCpu(double time) {
        double utilization = getCloudletScheduler().getTotalUtilizationOfCpu(time);
        // Ensure utilization is between 0 and 1
        return Math.min(Math.max(utilization, 0), 1.0);
    }
    */
    
    public int getUpdateAllocatedRam() {
    	return getRam();
    }
    
    public long getUpdateAllocatedBw() {
    	return getBw();
    }
    
    public void setUtilizationModelRam(UtilizationModel utilizationModelRam) {
        this.utilizationModelRam = utilizationModelRam;
    }

    public double getRamUtilization() {
        return utilizationModelRam.getUtilization(0);// * getUpdateAllocatedRam(); // Assuming time 0 for current utilization
    }
    
    public void setUtilizationModelBw(UtilizationModel utilizationModelBw) {
        this.utilizationModelBw = utilizationModelBw;
    }

    public double getBwUtilization() {
        return utilizationModelBw.getUtilization(0);// * getUpdateAllocatedBw(); // Assuming time 0 for current utilization
    }
    
}




