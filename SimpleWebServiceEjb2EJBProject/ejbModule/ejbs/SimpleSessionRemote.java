
package ejbs;

import javax.ejb.EJBObject;

public interface SimpleSessionRemote extends EJBObject {

	public String getMessage() throws java.rmi.RemoteException;
	
}
