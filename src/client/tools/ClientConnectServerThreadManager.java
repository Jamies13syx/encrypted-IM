package client.tools;

import java.util.HashMap;
/**
 * this manager manage client part socket to server
 */

public class ClientConnectServerThreadManager {
    public static HashMap<String, ClientConnectServerThread> hashMap = new HashMap<>();

    public static void addClientConnectServerThread(String UID, ClientConnectServerThread cct){
        hashMap.put(UID, cct);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String UID){
        return hashMap.get(UID);
    }
}
