package ui.menu;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface MenuEvent {
    public void selected(int index, int subIndex) throws Exception;
     
}
