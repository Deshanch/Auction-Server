/*
Author:-Deshan L.A.C
Date:-2020/06/27
Part:-socket and gui of the client side

here the current price is send by the server directly to output stream, then to the input stream of the client.
No array or any type of data stucture is passed to the inputstream of the client by server when symbol is
sent to the server(this is how i tested).only current price or -1 is passed from the server(when symbol is sent to the server).
one can bid several times for one symbol.sending the "refresh" command to know whether that another client has started to bid.
sending the "EXIT" command to know whether that system is ending the bidding process, means say server to stop the process.
when refreshed, new user can bid again to any symbol,this means window is newly started and can enter name then symbol etc.
here buttons and textfields get activated step by step
(i)only name text and first enter button is enable
(ii)only symbol text and second enter button is enable
       .
       .
       .
like this they get activated or enabled, in other steps they are disabled.
*/
package com.client;
//import packages
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class ClientApp extends JFrame implements ActionListener{
    //naming of components
    private JButton b1, b2, b3, b4, b5;
    private JTextField t1, t2, t3, t4;
    private JPanel p1, p2, p3, p4, p5;
    private JLabel l1, l2, l3, l4;
    private JFrame window = new JFrame();
    //initializing the connecting components
    Socket s = null;
    OutputStreamWriter os  = null;
    PrintWriter out = null;
    BufferedReader br = null;
    String currentPrice = null;
    String ip = null;
    int port;
    private int dialogResult=0;
    
    public ClientApp(){
        //initializing the components(gui)
        l1 = new JLabel("Enter Name : ");l1.setFont(new Font("Serif", Font.BOLD, 30));
        l2 = new JLabel("Enter Symbol : ");l2.setFont(new Font("Serif", Font.BOLD, 30));
        l3 = new JLabel("Current Price : ");l3.setFont(new Font("Serif", Font.BOLD, 30));
        l4 = new JLabel("Bidding Price : ");l4.setFont(new Font("Serif", Font.BOLD, 30));
        b1 = new JButton("ENTER");b1.setFont(new Font("Serif", Font.BOLD, 30));
        b2 = new JButton("ENTER");b2.setFont(new Font("Serif", Font.BOLD, 30));
        b3 = new JButton("ENTER");b3.setFont(new Font("Serif", Font.BOLD, 30));
        b4 = new JButton("BID AGAIN");b4.setFont(new Font("Serif", Font.BOLD, 30));
        b5 = new JButton("EXIT");b5.setFont(new Font("Serif", Font.BOLD, 30));
        p1 = new JPanel();//set a plane to keep components
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        t1 = new JTextField(20);t1.setFont(new Font("Serif", Font.BOLD, 30));
        t2 = new JTextField(20);t2.setFont(new Font("Serif", Font.BOLD, 30));
        t3 = new JTextField(20);t3.setFont(new Font("Serif", Font.BOLD, 30));
        t4 = new JTextField(20);t4.setFont(new Font("Serif", Font.BOLD, 30));
        //setting the main frame
        setTitle("BIDDING BOARD");
        setSize(800,800);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));
        //adding components to planes
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        p1.add(l1);p1.add(t1);p1.add(b1);
        p2.add(l2);p2.add(t2);p2.add(b2);
        p3.add(l3);p3.add(t3);
        p4.add(l4);p4.add(t4);p4.add(b3);
        p5.add(b4);p5.add(b5);
        
        ip = "localhost";//ip address
        port = 2000;//port
        
        t2.setEnabled(false);//enabling the actions of the textfields
        t3.setEnabled(false);//enabling the actions of the textfields
        t4.setEnabled(false);//enabling the actions of the textfields
        b2.setEnabled(false);//enabling the actions of the buttons
        b3.setEnabled(false);//enabling the actions of the buttons
        //calling to method in button clicking
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        
    } 
    //action method of button clicking
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            s = new Socket(ip, port);//making the socket
            os  = new OutputStreamWriter(s.getOutputStream());//making object for the output stream
            out = new PrintWriter(os);//setting to put to output stream
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));//making input stream
        } 
        catch (IOException ex) {
            System.out.println("error is "+ex);
        }
        if(e.getSource() == b1){ //if the name is entered
            try {
                out.println(t1.getText());
                os.flush();
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            t1.setText(t1.getText());//value is set to the text 
            t1.setEnabled(false);//enabling the actions of the textfields
            b1.setEnabled(false);//enabling the actions of the buttons
            t2.setEnabled(true);//enabling the actions of the textfields
            b2.setEnabled(true);//enabling the actions of the buttons
        }
        if(e.getSource() == b2){//if the symbol is sent 
            try {
                out.println(t2.getText());
                os.flush();
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            try {//reading the current price from the buffered reader
                currentPrice = br.readLine();
                System.out.println(""+currentPrice);
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            t2.setText(t2.getText());//setting the main frame
            t2.setEnabled(false);
            b2.setEnabled(false);
            t3.setEnabled(true);//enabling the actions of the textfields
            t4.setEnabled(true);//enabling the actions of the textfields
            b3.setEnabled(true);//enabling the actions of the button
            //for inputting
            
            if ("-1".equals(currentPrice)) {//if symbol entered is wrong
                //System.out.println("Symbol is invalid");
                dialogResult = JOptionPane.showConfirmDialog(null,"Symbol is invalid\nBid Again?","Warning",dialogResult);//option dialog
			if(dialogResult == JOptionPane.YES_OPTION){//if want to bit again
                            try {//have to initialize the connection to send the exit
                                s = new Socket(ip, port);
                                os  = new OutputStreamWriter(s.getOutputStream());
                                out = new PrintWriter(os);
                                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            } 
                                catch (IOException ex) {
                                System.out.println("error is "+ex);
                            }
                            out.println("REFRESH");//sending the "refresh" command to know whether that new user has to bid.
                            try {
                                os.flush();
                            } catch (IOException ex) {
                                System.out.println("error is "+ex);
                            }
                            setVisible(false);
                            window.dispose();
                            try {
                                out.close();
                                br.close();
                                s.close();
                            } catch (IOException ex) {
                                System.out.println("error is "+ex);
                            }
                            new ClientApp();
			}
			else{	//if want to exit
                            try {//have to initialize the connection to send the exit
                                s = new Socket(ip, port);
                                os  = new OutputStreamWriter(s.getOutputStream());
                                out = new PrintWriter(os);
                                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            } 
                                catch (IOException ex) {
                                System.out.println("error is "+ex);
                            }
                            out.println("EXIT");//sending the exit command
                            try {
                                os.flush();
                            } catch (IOException ex) {
                                System.out.println("error is "+ex);
                            }
                            try {
                                out.close();
                                br.close();
                                s.close();
                            } catch (IOException ex) {
                                System.out.println("error is "+ex);
                            }
                            System.exit(0);//system is exit
                            dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));//close the frame
			}
            }
            else{//this current price is directly send by the server not any type of array
                t3.setText(currentPrice);//if there is a matching symbol then current price is set
                //t3.setEnabled(false);
            }
        }
        if(e.getSource() == b3){//sending bitted price
            try {
                out.println(t4.getText());
                os.flush();
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            try {//reading the current price from the buffered reader
                currentPrice = br.readLine();
                System.out.println(""+currentPrice);
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            t4.setText(t4.getText());
            t3.setText(currentPrice);//one can bid several times for a one symbol
            //t4.setEnabled(false);
            //b3.setEnabled(false);
        }
        if(e.getSource() == b4){//if want to play again
            out.println("REFRESH");//sending the "refresh" command to know whether that new user has to bid.
            try {
                os.flush();
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            setVisible(false);
            try {
                out.close();
                br.close();
                s.close();
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            new ClientApp();//call to again
        }
        if(e.getSource() == b5){//if want to totally exit
            out.println("EXIT");
            try {
                os.flush();
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            try {
                out.close();
                br.close();
                s.close();
            } catch (IOException ex) {
                System.out.println("error is "+ex);
            }
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new ClientApp();//from main calling to make the gui	
    }

}




