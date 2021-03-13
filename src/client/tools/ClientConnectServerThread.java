package client.tools;

import client.view.ChatView;
import client.view.FriendList;
import common.Message;
import common.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 1.calls chatViewManager to get right chat and show message
 * 2.calls friendListManager to update online status
 */
public class ClientConnectServerThread extends Thread{
    private Socket s;

    public ClientConnectServerThread(Socket s){
        this.s = s;
    }

    public Socket getSocket() {
        return s;
    }

    public void run(){
        while (true){// constantly read content from server
            try{
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message mes = (Message) ois.readObject();
                // check message type
                if(mes.getMesType().equals(MessageType.common_message)){
                    ChatView chatView = ChatViewManager.getChat(mes.getReceiver(), mes.getSender());//from chat manager get corresponding chat view
                    chatView.showMessage(mes);//show regular message
                }
                else if (mes.getMesType().equals(MessageType.server_return_online)){
                    // return type "5" message, content is who are online(format example: 1 3 5 6
                    String receiver = mes.getReceiver();
                    //update friend list online status
                    FriendList friendList = FriendListManager.getFriendList(receiver);
                    if(friendList != null){
                        friendList.updateOnlineStatus(mes);
                    }
                }

            }
            catch (Exception e){

            }
        }
    }
}
