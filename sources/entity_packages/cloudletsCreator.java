package entity_packages;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.UtilizationModelNull;
//import org.cloudbus.cloudsim.UtilizationModelStochastic;

public class cloudletsCreator {
	public static List<Cloudlet> createCloudlets(int broker_id, int cloudlet_num, int cloudlet_len, int cloudlet_pes) {
    	System.out.println("========== Create Cloudlets ==========");
        List<Cloudlet> list = new ArrayList<> ();
        for (int i=0; i<cloudlet_num; i++) {
        	UtilizationModel utilization_cpu = new UtilizationModelFull();
        	//UtilizationModel utilization_cpu = new UtilizationModelNull();
            UtilizationModel utilization_ram = new UtilizationModelFull();
            //UtilizationModel utilization_ram = new UtilizationModelNull();
            UtilizationModel utilization_bw = new UtilizationModelFull();
            //UtilizationModel utilization_bw = new UtilizationModelNull();
        	//UtilizationModel utilization_cpu = new UtilizationModelStochastic();
            //UtilizationModel utilization_ram = new UtilizationModelStochastic();
            //UtilizationModel utilization_bw = new UtilizationModelStochastic();
            long length = i < cloudlet_num / 2 ? cloudlet_len : cloudlet_len * 2;
            Cloudlet cloudlet = new Cloudlet(
                i,
                length,
                cloudlet_pes,
                100,
                100,
                utilization_cpu,
                utilization_ram,
                utilization_bw
            );
            cloudlet.setUserId(broker_id);
            list.add(cloudlet);
        }
        return list;
    }
}





