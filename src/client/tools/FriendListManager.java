package client.tools;

import client.view.FriendList;

import java.util.HashMap;

/**
 * this class manages login UID and its corresponding friendlist
 */

public class FriendListManager {
    private static HashMap<String, FriendList> hashMap = new HashMap<>();

    public static void addFriendList(String UID, FriendList friendList){
        hashMap.put(UID, friendList);
    }

    public static FriendList getFriendList(String UID){
        return hashMap.get(UID);
    }
}
