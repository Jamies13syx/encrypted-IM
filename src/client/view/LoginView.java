package client.view;

import client.model.ClientConnectServer;
import client.tools.ClientConnectServerThreadManager;
import client.tools.FriendListManager;
import common.Message;
import common.MessageType;
import common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

/**
 * This demo shows easyIm login view
 */

public class LoginView extends JFrame implements ActionListener {
    JLabel head_label;//north area

    JButton login_button, cancel_button, help_button;
    JPanel south_panel;
    //south area

    JTabbedPane jtp;

    JPanel uid_panel, phone_panel, mail_panel;

    JLabel uid_label, password_label, forget_label, url_help_label;
    JTextField jtf;
    JPasswordField jpf;
    JButton clear_button;
    JCheckBox jcb1, jcb2;

    JLabel phone_label, phone_password_label, phone_forget_label, phone_url_help_label;
    JTextField phone_jtf;
    JPasswordField phone_jpf;
    JButton phone_clear_button;
    JCheckBox phone_jcb1;
    JCheckBox phone_jcb2;

    JLabel mail_label, mail_password_label, mail_forget_label, mail_url_help_label;
    JTextField mail_jtf;
    JPasswordField mail_jpf;
    JButton mail_clear_button;
    JCheckBox mail_jcb1;
    JCheckBox mail_jcb2;


    public static void main(String[] args) {
        new LoginView();
    }

    public LoginView(){

        // center part init

        uid_label = new JLabel("User ID", JLabel.CENTER);
        password_label = new JLabel("Password", JLabel.CENTER);
        forget_label = new JLabel("<html><a>Forget Password</a>", JLabel.CENTER);

        forget_label.setFont(new Font("Time New Roman", Font.PLAIN, 10));
        forget_label.setForeground(Color.blue);
        forget_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        url_help_label = new JLabel("<html><a href=‘www.qq.com’>Protect password</a>", JLabel.CENTER);
        url_help_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        jtf = new JTextField();
        jpf = new JPasswordField();

        clear_button = new JButton(new ImageIcon("src\\IMimages\\clear.gif"));
        clear_button.setPreferredSize(new Dimension(70, 20));

        jcb1 = new JCheckBox("Login invisible");
        jcb2 = new JCheckBox("Remember me");


        phone_label = new JLabel("Phone Number", JLabel.CENTER);
        phone_password_label = new JLabel("Password", JLabel.CENTER);
        phone_forget_label = new JLabel("<html><a>Forget Password</a>", JLabel.CENTER);
        phone_forget_label.setFont(new Font("Time New Roman", Font.PLAIN, 10));
        phone_forget_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        phone_forget_label.setForeground(Color.blue);
        phone_url_help_label = new JLabel("<html><a href=‘www.qq.com’>Protect password</a>", JLabel.CENTER);
        phone_url_help_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        phone_jtf = new JTextField();
        phone_jpf = new JPasswordField();
        phone_clear_button = new JButton(new ImageIcon("src\\IMimages\\clear.gif"));
        phone_jcb1 = new JCheckBox("Login invisible");
        phone_jcb2 = new JCheckBox("Remember me");

        mail_label = new JLabel("Mail Address", JLabel.CENTER);
        mail_password_label = new JLabel("Password", JLabel.CENTER);
        mail_forget_label = new JLabel("<html><a>Forget Password</a>", JLabel.CENTER);
        mail_forget_label.setFont(new Font("Time New Roman", Font.PLAIN, 10));
        mail_forget_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mail_forget_label.setForeground(Color.blue);
        mail_url_help_label = new JLabel("<html><a href=‘www.qq.com’>Protect password</a>", JLabel.CENTER);
        mail_url_help_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mail_jtf = new JTextField();
        mail_jpf = new JPasswordField();
        mail_clear_button = new JButton(new ImageIcon("src\\IMimages\\clear.gif"));
        mail_jcb1 = new JCheckBox("Login invisible");
        mail_jcb2 = new JCheckBox("Remember me");




        // north part

        head_label = new JLabel(new ImageIcon("src\\IMimages\\tou.gif"));

        //south part

        south_panel = new JPanel();
        login_button = new JButton(new ImageIcon("src\\IMimages\\denglu.gif"));
        login_button.setPreferredSize(new Dimension(70, 20));
        login_button.addActionListener(this);

        cancel_button = new JButton(new ImageIcon("src\\IMimages\\quxiao.gif"));
        cancel_button.setPreferredSize(new Dimension(70, 20));
        help_button = new JButton(new ImageIcon("src\\IMimages\\xiangdao.gif"));
        help_button.setPreferredSize(new Dimension(70, 20));

        south_panel.add(login_button);
        south_panel.add(cancel_button);
        south_panel.add(help_button);

        //card panel

        jtp = new JTabbedPane();

        uid_panel = new JPanel();
        uid_panel.setLayout(new GridLayout(3,3));
        //center part layout
        uid_panel.add(uid_label);
        uid_panel.add(jtf);
        uid_panel.add(clear_button);
        uid_panel.add(password_label);
        uid_panel.add(jpf);
        uid_panel.add(forget_label);
        uid_panel.add(jcb1);
        uid_panel.add(jcb2);
        uid_panel.add(url_help_label);


        phone_panel = new JPanel();
        phone_panel.setLayout(new GridLayout(3, 3));
        phone_panel.add(phone_label);
        phone_panel.add(phone_jtf);
        phone_panel.add(phone_clear_button);
        phone_panel.add(phone_password_label);
        phone_panel.add(phone_jpf);
        phone_panel.add(phone_forget_label);
        phone_panel.add(phone_jcb1);
        phone_panel.add(phone_jcb2);
        phone_panel.add(phone_url_help_label);

        mail_panel = new JPanel();
        mail_panel.setLayout(new GridLayout(3, 3));
        mail_panel.add(mail_label);
        mail_panel.add(mail_jtf);
        mail_panel.add(mail_clear_button);
        mail_panel.add(mail_password_label);
        mail_panel.add(mail_jpf);
        mail_panel.add(mail_forget_label);
        mail_panel.add(mail_jcb1);
        mail_panel.add(mail_jcb2);
        mail_panel.add(mail_url_help_label);

        jtp.add("UID", uid_panel);
        jtp.add("Phone", phone_panel);
        jtp.add("Email", mail_panel);


        //total lauout

        this.add(south_panel, BorderLayout.SOUTH);
        this.add(head_label, BorderLayout.NORTH);
        this.add(jtp, BorderLayout.CENTER);

        ImageIcon icon = new ImageIcon("src\\IMimages\\qq.gif");
        this.setIconImage(icon.getImage());
        this.setSize(350,240);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == login_button){
            User user = new User();
            user.setUID(jtf.getText().trim());
            user.setPassword(new String(jpf.getPassword()));

            ClientConnectServer clientConnectServer = new ClientConnectServer();// connect to server
            if(clientConnectServer.sendLoginInfoToServer(user)){
                //true -> login success
                try{
                    FriendList friendList = new FriendList(user.getUID());//init friendlist
                    FriendListManager.addFriendList(user.getUID(), friendList);
                    //acquire friend's online status from server
                    ObjectOutputStream oos = new ObjectOutputStream(ClientConnectServerThreadManager.getClientConnectServerThread(user.getUID()).getSocket().getOutputStream());
                    Message mes = new Message();
                    mes.setMesType(MessageType.client_get_online);
                    mes.setSender(user.getUID());
                    oos.writeObject(mes);// Server part: ServerConnectClientThread
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,"Wrong password");
            }


        }
    }
}
