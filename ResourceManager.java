import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ResourceManager extends Remote {
    String getResource(String name) throws RemoteException;
    void setResource(String name, String value) throws RemoteException;
}

