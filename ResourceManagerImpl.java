import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ResourceManagerImpl extends UnicastRemoteObject implements ResourceManager {
    private Map<String, String> resources;

    protected ResourceManagerImpl() throws RemoteException {
        super();
        resources = new HashMap<>();
    }

    @Override
    public String getResource(String name) throws RemoteException {
        return resources.get(name);
    }

    @Override
    public void setResource(String name, String value) throws RemoteException {
        resources.put(name, value);
    }
}

