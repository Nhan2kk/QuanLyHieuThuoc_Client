package ui.cell;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface TableActionEvent {

    public void onEdit(int row) throws RemoteException, MalformedURLException, NotBoundException;

    public void onDelete(int row);

    public void onView(int row);
}
