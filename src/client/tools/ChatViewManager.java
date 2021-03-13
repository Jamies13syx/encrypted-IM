package client.tools;

import client.view.ChatView;

import java.util.HashMap;

public class ChatViewManager {

    private static HashMap<String, ChatView> hashMap = new HashMap<>();

    public static void addChat(String loginUID, String friendUID, ChatView chatView){
        hashMap.put(loginUID+":"+friendUID, chatView);
    }
    public static ChatView getChat(String loginUID, String friendUID){
        return hashMap.get(loginUID+":"+friendUID);
    }
}
