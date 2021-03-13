package client.view;

import client.tools.ClientConnectServerThreadManager;
import common.Message;
import common.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ChatView extends JFrame implements ActionListener, KeyListener {

    JTextArea jTextArea;
    JTextField jTextField;
    JButton jButton;
    JPanel jPanel;
    JScrollPane jScrollPane;
    String ownerUID;
    String friendUID;


//    public static void main(String[] args) {
//        new ChatView("123","5");
//    }

    ChatView(String ownerUID, String friendUID){
        this.ownerUID = ownerUID;
        this.friendUID = friendUID;

        jTextArea = new JTextArea();
        jTextField = new JTextField(20);
        jTextField.addKeyListener(this);
        jButton = new JButton("Send");
        jButton.addActionListener(this);
        jPanel = new JPanel();
        jPanel.add(jTextField);
        jPanel.add(jButton);
        jTextArea.setEditable(false);

        jScrollPane = new JScrollPane(jTextArea);

        ImageIcon icon = new ImageIcon("src\\com\\IMimages\\qq.gif");
        this.setIconImage(icon.getImage());
        this.setTitle(ownerUID + " is  chatting with friend " + friendUID);
        this.add(jScrollPane, "Center");
        this.add(jPanel, "South");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(400, 300);
        this.setLocation(600, 500);
        this.setVisible(true);

    }

    public void sendMessage(){
        Message mes = new Message();
        mes.setSender(ownerUID);
        mes.setReceiver(friendUID);
        String content = jTextField.getText();
        mes.setContent(content);
        mes.setSentTime(new Date());
        mes.setMesType(MessageType.common_message);
        jTextArea.append(ownerUID +" : "+ content + "\r\n");
        jTextField.setText("");
        try{
            ObjectOutputStream oos = new ObjectOutputStream(ClientConnectServerThreadManager.getClientConnectServerThread(ownerUID).getSocket().getOutputStream());
            oos.writeObject(mes);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showMessage(Message message){
        String info = message.getSender() + " to " + message.getReceiver() + " : " + message.getContent() + " at " + message.getSentTime() + "\r\n";
        jTextArea.append(info);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == jButton){
            sendMessage();
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
            sendMessage();
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
