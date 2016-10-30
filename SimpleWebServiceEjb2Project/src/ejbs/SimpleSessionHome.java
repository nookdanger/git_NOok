
package ejbs;

import javax.ejb.EJBHome;

public interface SimpleSessionHome extends EJBHome {
	
	public ejbs.SimpleSessionRemote create()throws javax.ejb.CreateException,java.rmi.RemoteException;

}
