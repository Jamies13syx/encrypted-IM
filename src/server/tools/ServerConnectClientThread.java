package server.tools;

import common.Message;
import common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * this class function as server connect to client part socket running thread
 * do re-post common message and return online status to user(help with manager)
 */

public class ServerConnectClientThread extends Thread{

    Socket s;
    public ServerConnectClientThread(Socket s){
        this.s = s;

    }
    public Socket getSocket(){
        return this.s;
    }

    //once one user login successfully, server sent this user is online to other users
    public void notifyOthers(String ownerUID){
        HashMap<String, ServerConnectClientThread> hashMap = ServerConnectClientThreadManager.hashMap;

        for (String onlineUID : hashMap.keySet()) {
            try {
                Message message = new Message();
                message.setContent(ownerUID);
                message.setMesType(MessageType.server_return_online);
                message.setReceiver(onlineUID);
                ObjectOutputStream oos = new ObjectOutputStream(hashMap.get(onlineUID).getSocket().getOutputStream());
                oos.writeObject(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (true){
            // server receive client message
            try{
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());//pending
                Message mes = (Message) ois.readObject();
//                System.out.println(mes.getSender()+" to "+ mes.getReceiver() + " t: " + mes.getMesType() + " c: " + mes.getContent());
                //testing message

                if(mes.getMesType().equals(MessageType.common_message)){
                    ServerConnectClientThread receiver_thread = ServerConnectClientThreadManager.getServerConnectClientThread(mes.getReceiver());
                    // server repost to receiver thread socket
                    ObjectOutputStream oos = new ObjectOutputStream(receiver_thread.s.getOutputStream());
                    oos.writeObject(mes);
                }
                //once login success
                else if(mes.getMesType().equals(MessageType.client_get_online)){

                    String res = ServerConnectClientThreadManager.getOnlineUser();
                    Message ret_mes = new Message();
                    ret_mes.setContent(res);
                    ret_mes.setMesType(MessageType.server_return_online);
                    ret_mes.setReceiver(mes.getSender());
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());//send back to sender
                    oos.writeObject(ret_mes);
                }

            }
            catch (Exception e){

            }

        }
    }

}
