package server.model;

import common.Message;
import common.MessageType;
import common.User;
import server.tools.ServerConnectClientThread;
import server.tools.ServerConnectClientThreadManager;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class init server to client socket
 * if login success, create this uid socket thread (thread do most part)
 * then notify all other online users
 */
public class ServerConnectClient{
    ServerSocket ss;
    Socket s;

    public static void main(String[] args) {
        new ServerConnectClient();
    }

    public ServerConnectClient(){

        try{
            ss = new ServerSocket(9999);
            loginCheck();//first time always be login check

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Network error");
        }
        finally {

        }
    }

    public void loginCheck(){

        while (true){
            try {
                s = ss.accept();

                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User user = (User) ois.readObject();

                Message mes = new Message();

                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                //need go to database rework
                if(user.getPassword().equals("123456")){
                    mes.setMesType(MessageType.login_success);
                    oos.writeObject(mes);
                    // create new thread if login success
                    ServerConnectClientThread scct = new ServerConnectClientThread(s);
                    ServerConnectClientThreadManager.addServerConnectClientThread(user.getUID(), scct);
                    scct.start();
                    scct.notifyOthers(user.getUID()); //notify other users
                }
                else {
                    mes.setMesType(MessageType.login_fail);
                    oos.writeObject(mes);
                    s.close();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {

            }
        }



    }
}
