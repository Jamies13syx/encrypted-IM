package client.view;

import client.tools.ChatViewManager;
import client.tools.ChatViewManager;
import common.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This demo shows friends list
 */

public class FriendList extends JFrame implements ActionListener, MouseListener {
    JPanel main_panel;
    CardLayout cardLayout;

    JPanel friend_main_panel;
    JPanel friend_panel;
    JPanel friend_south_panel;
    JButton friend_button_f, stranger_button_f, blacklist_button_f;
    JScrollPane jScrollPane_friend;// friend set


    JPanel stranger_main_panel;
    JPanel stranger_panel;
    JPanel stranger_north_panel;
    JButton friend_button_s, stranger_button_s, blacklist_button_s;
    JScrollPane jScrollPane_stranger;// stranger set


    JPanel blacklist_main_panel;
    JPanel blacklist_panel;
    JPanel blacklist_north_panel;
    JButton friend_button_b, stranger_button_b, blacklist_button_b;
    JScrollPane jScrollPane_blacklist;// blacklist set

    String ownerUID;
    JLabel[] friends;
    JLabel[] blacklists;
    JLabel[] strangers;

    public FriendList(String ownerUID){
        cardLayout = new CardLayout(2, 2);
        this.ownerUID = ownerUID;

        main_panel = new JPanel(cardLayout);
        main_panel.add(init_friend(), "friend");
        main_panel.add(init_stranger(), "stranger");
        main_panel.add(init_blacklist(), "blacklist");

        this.add(main_panel);

        friend_button_f.addActionListener(this);
        stranger_button_f.addActionListener(this);
        blacklist_button_f.addActionListener(this);

        friend_button_s.addActionListener(this);
        stranger_button_s.addActionListener(this);
        blacklist_button_s.addActionListener(this);

        friend_button_b.addActionListener(this);
        stranger_button_b.addActionListener(this);
        blacklist_button_b.addActionListener(this);



        ImageIcon icon = new ImageIcon("src\\IMimages\\qq.gif");
        this.setIconImage(icon.getImage());
        this.setSize(120,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle(ownerUID);
        this.ownerUID = ownerUID;

    }

    public JPanel init_friend(){

        friend_main_panel = new JPanel(new BorderLayout());

        friend_button_f = new JButton("My friend");
        stranger_button_f = new JButton("Stranger");
        blacklist_button_f = new JButton("Blacklist");

        //assume 50 friends

        friend_panel = new JPanel(new GridLayout(50, 1, 5, 5));
        friend_south_panel = new JPanel(new GridLayout(2, 1));
        friend_south_panel.add(stranger_button_f);
        friend_south_panel.add(blacklist_button_f);

        //init 50 friends
        friends = new JLabel[5];

        for(int i = 0; i <friends.length; i++){
            friends[i] = new JLabel(i + 1 + "", new ImageIcon("src\\IMimages\\mm.jpg"), JLabel.LEFT);
            friends[i].setEnabled(friends[i].getText().equals(ownerUID));

            friends[i].addMouseListener(this);
            friend_panel.add(friends[i]);
        }


        jScrollPane_friend = new JScrollPane(friend_panel);

        friend_main_panel.add(friend_button_f, "North");
        friend_main_panel.add(jScrollPane_friend, "Center");
        friend_main_panel.add(friend_south_panel, "South");

        return friend_main_panel;
    }

    public JPanel init_stranger(){

        stranger_main_panel = new JPanel(new BorderLayout());

        stranger_button_s = new JButton("Stranger");
        friend_button_s = new JButton("My friend");
        blacklist_button_s = new JButton("Blacklist");

        //assume 50 strangers

        stranger_panel = new JPanel(new GridLayout(20, 1, 5, 5));
        stranger_north_panel = new JPanel(new GridLayout(2, 1));
        stranger_north_panel.add(friend_button_s);
        stranger_north_panel.add(stranger_button_s);

        //init 50 strangers
        strangers = new JLabel[20];

        for(int i = 0; i <strangers.length; i++){
            strangers[i] = new JLabel(i+1+"", new ImageIcon("src\\IMimages\\mm.jpg"), JLabel.LEFT);
            strangers[i].addMouseListener(this);
            stranger_panel.add(strangers[i]);
        }


        jScrollPane_stranger = new JScrollPane(stranger_panel);

        stranger_main_panel.add(blacklist_button_s, "South");
        stranger_main_panel.add(jScrollPane_stranger, "Center");
        stranger_main_panel.add(stranger_north_panel, "North");

        return stranger_main_panel;
    }

    public JPanel init_blacklist(){

        blacklist_main_panel = new JPanel(new BorderLayout());

        stranger_button_b = new JButton("Stranger");
        friend_button_b = new JButton("My friend");
        blacklist_button_b = new JButton("Blacklist");

        //assume 50 strangers

        blacklist_panel = new JPanel(new GridLayout(20, 1, 5, 5));
        blacklist_north_panel = new JPanel(new GridLayout(3, 1));
        blacklist_north_panel.add(friend_button_b);
        blacklist_north_panel.add(stranger_button_b);
        blacklist_north_panel.add(blacklist_button_b);

        //init 50 blacklists
        blacklists = new JLabel[15];

        for(int i = 0; i <blacklists.length; i++){
            blacklists[i] = new JLabel(i+1+"", new ImageIcon("src\\IMimages\\mm.jpg"), JLabel.LEFT);
            blacklists[i].addMouseListener(this);
            blacklist_panel.add(blacklists[i]);
        }


        jScrollPane_blacklist = new JScrollPane(blacklist_panel);
        blacklist_main_panel.add(jScrollPane_blacklist, "Center");
        blacklist_main_panel.add(blacklist_north_panel, "North");

        return blacklist_main_panel;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == blacklist_button_f || actionEvent.getSource() == blacklist_button_s || actionEvent.getSource() == blacklist_button_b){
            cardLayout.show(main_panel, "blacklist");
        }
        if(actionEvent.getSource() == friend_button_f || actionEvent.getSource() == friend_button_s || actionEvent.getSource() == friend_button_b){
            cardLayout.show(main_panel, "friend");
        }
        if(actionEvent.getSource() == stranger_button_f || actionEvent.getSource() == stranger_button_s || actionEvent.getSource() == stranger_button_b){
            cardLayout.show(main_panel, "stranger");
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2){
            String friendNo = ((JLabel) mouseEvent.getSource()).getText();
            ChatView chatView = new ChatView(ownerUID, friendNo);
            ChatViewManager.addChat(ownerUID, friendNo, chatView);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        JLabel entered  = (JLabel) mouseEvent.getSource();
        entered.setForeground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        JLabel entered  = (JLabel) mouseEvent.getSource();
        entered.setForeground(Color.black);
    }


    public void updateOnlineStatus(Message mes) {
        String content = mes.getContent();
        String[] needUpdate = content.split(" ");
        for (String s : needUpdate) {
            friends[Integer.parseInt(s) - 1].setEnabled(true);
        }
    }
}
