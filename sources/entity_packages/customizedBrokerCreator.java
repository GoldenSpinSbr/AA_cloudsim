package entity_packages;

import org.cloudbus.cloudsim.power.PowerDatacenterBroker;

public class customizedBrokerCreator {
    public static PowerDatacenterBroker createBroker() {
    	System.out.println("========== Create Broker ==========");
        PowerDatacenterBroker broker = null;
        try {
            broker = new PowerDatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return broker;
    }
}