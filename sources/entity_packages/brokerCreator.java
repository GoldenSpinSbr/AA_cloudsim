package entity_packages;

import org.cloudbus.cloudsim.DatacenterBroker;

public class brokerCreator {
    public static DatacenterBroker createBroker() {
    	System.out.println("========== Create Broker ==========");
        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return broker;
    }
}



