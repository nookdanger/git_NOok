
package ejbs;

import java.rmi.Remote;

public interface SimpleSessionService extends Remote {
	
	public String getMessage() throws java.rmi.RemoteException;

}
