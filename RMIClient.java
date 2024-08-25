import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        try {
            ResourceManager resourceManager = (ResourceManager) Naming.lookup("rmi://localhost/ResourceManager");

            resourceManager.setResource("Node1", "Resource1");

            String resource = resourceManager.getResource("Node1");

            System.out.println("Resource from Node1: " + resource);
        } catch (Exception e) {
            System.out.println("Client failed: " + e);
        }
    }
}

