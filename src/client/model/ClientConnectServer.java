package client.model;

import client.tools.ClientConnectServerThread;
import client.tools.ClientConnectServerThreadManager;
import common.Message;
import common.MessageType;
import common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This class init client part socket connect to server, send login info, once login successfully create thread
 * thread do most part of job
 * this acts like bridge and proxy
 */
public class ClientConnectServer {
    public Socket s;

    public boolean sendLoginInfoToServer(Object o){
        boolean ret = false;

        try{
            //send login info
            s = new Socket(InetAddress.getLocalHost(), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);
            //read back from server
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Message mes = (Message) ois.readObject();
            //check
            if(mes.getMesType().equals(MessageType.login_success)){//login access
                ClientConnectServerThread cct = new ClientConnectServerThread(s);// create this uid's thread
                ClientConnectServerThreadManager.addClientConnectServerThread(((User)o).getUID(), cct);
                cct.start();//once login successfully start its personal thread connect to server
                //inform other users new online
                ret = true;
            }
            else if(mes.getMesType().equals(MessageType.login_fail)){
                s.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();

        }
        finally {

        }
        return ret;
    }
}
