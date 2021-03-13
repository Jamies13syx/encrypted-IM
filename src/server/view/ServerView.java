package server.view;

import server.model.ServerConnectClient;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ServerView extends JFrame implements MouseListener {
    JPanel main_panel;
    JButton start_button, close_button;
    ServerConnectClient scc;
    boolean serviceStatus;


    public static void main(String[] args)  {
        new ServerView();
    }

    public ServerView(){
        serviceStatus = false;
        main_panel = new JPanel();
        start_button = new JButton("Start server");
        start_button.addMouseListener(this);
        close_button = new JButton("Close server");
        close_button.addMouseListener(this);
        main_panel.add(start_button);
        main_panel.add(close_button);
        this.add(main_panel);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        new Thread(() -> {
            if(e.getSource() == start_button){
                if(!serviceStatus){
                    JOptionPane.showMessageDialog(null, "Service starts");
                    scc = new ServerConnectClient();
                    serviceStatus = true;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Service already starts");
                }

            }
            if(e.getSource() == close_button){
                JOptionPane.showMessageDialog(null, "Service terminates");
                System.exit(0);
            }
        }).start();


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
