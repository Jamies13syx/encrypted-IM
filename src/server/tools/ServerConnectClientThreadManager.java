package server.tools;

import java.util.HashMap;
import java.util.Iterator;

/**
 * this manager manage server part socket to client
 */

public class ServerConnectClientThreadManager {
    public static HashMap<String, ServerConnectClientThread> hashMap = new HashMap<>();

    public static void addServerConnectClientThread(String UID, ServerConnectClientThread scct){
        hashMap.put(UID, scct);

    }

    public static ServerConnectClientThread getServerConnectClientThread(String UID){
        return hashMap.get(UID);
    }

    public static void removeServerConnectClientThread(String UID){
        hashMap.remove(UID);
    }

    public static String getOnlineUser(){
        Iterator<String> iterator = hashMap.keySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()){
            sb.append(iterator.next()).append(" ");
        }
        return sb.toString();
    }
}
