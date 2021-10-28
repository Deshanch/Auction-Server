

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Server extends Thread {

    private Object DataInputStream;
    public static int port = 2000 ;                                                                 // client port 2000

    public void run() {



        try (ServerSocket serverSocket = new ServerSocket(port)) {                                  // welcome to new serverSocket

            System.out.println("Server is listening on port " + port);                              // connection

            while (true) {                                                                          //
                Socket socket = serverSocket.accept();                                              // client welcome
                System.out.println("New client connected");

                new ServerThread(socket).start();                                                   // connect thread with client

               // if(ServerThread.activeCount() > 1000){ }                                            // over 1000 client limit
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());                             // error
            ex.printStackTrace();
        }

    }

}

class ServerThread extends Thread {                                                                 // thread socket
    private Socket socket;
    ServerGUI serverGUI = new ServerGUI();
    boolean read = false;
    boolean write = false;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        try {
            InputStream input = socket.getInputStream();                                            // input
            DataInputStream reader = new DataInputStream(input);

            OutputStream output = socket.getOutputStream();                                         // output
            DataOutputStream writer = new DataOutputStream(output);
            writer.flush();

            String name = reader.readUTF();

            databasae data = new databasae();                                               // read database and modify

            while (true) {


                String Symbol = null;

                Symbol = reader.readUTF();
                System.out.println(Symbol);
                if (Symbol.equals("^c")) break;

                String data_send = "";
                data_send = "Price :-" + "\t" + data.bid_current_price(Symbol) + "\n";                   // current price print
                System.out.println(data.cost);
                sleep(50);
                if (data.cost > -1) {
                    data_send = data_send + "default" + "\t" + data.st.price + "\t"  + data.st.time + "\n";
                    if (data.st.Chain != null) {
                        for (int i = 0; i < data.st.Chain.size(); i++) {
                            data_send = data_send + data.st.Chain.get(i).client + "\t" + data.st.Chain.get(i).price + "\t" + data.st.Chain.get(i).time + "\n";
                        }
                    }

                    writer.writeUTF(data_send);


                    String value = reader.readUTF();
                    if (value.equals("^c")) break;

                    if (value == null) {
                        break;
                    }


                    float newvalue = Float.valueOf(value);

                    if (data.cost < newvalue) {
                        data.bid_price(name, newvalue, LocalDateTime.now());
                        serverGUI.addRow(Symbol);

                    }
                    if (socket.isClosed()) {
                        break;
                    }
                } else {
                    writer.writeUTF("Price :-" + "\t" + "-1" + "\n");
                }


                sleep(10);

            }

            writer.flush();
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException | InterruptedException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
/*
    public void run(){
        try {
            InputStream input = socket.getInputStream();                                            // input
            DataInputStream reader = new DataInputStream(input);

            OutputStream output = socket.getOutputStream();                                         // output
            DataOutputStream writer = new DataOutputStream(output);
            writer.flush();
            writer.writeUTF("name :- ");

            String name = reader.readUTF();

            System.out.println(name);
            databasae data = new databasae();
            read = true ;

            while (true){

                if ( read ) {
                    String Symbol = reader.readUTF();
                    System.out.println(Symbol);
                    read = false ;

                }
                if( write ){



                }

                sleep(10);
            }


        }catch  (IOException | InterruptedException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }*/

/*
    public void run() {
        try {
            InputStream input = socket.getInputStream();                                            // input
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();                                         // output
            PrintWriter writer = new PrintWriter(output, true);

            writer.print("name :- ");                                                               // client name
            writer.flush() ;
            String name = reader.readLine();
            System.out.println(name);
            databasae data = new databasae();                                               // read database and modify


            while ( true ){
                    if ( name.equals("^C") ) break;
                    writer.print("Symbol :- ");                                                     // symbol input
                    writer.flush();
                    String Symbol = reader.readLine();

                    if ( Symbol.equals("^C") ) break;


                    writer.println("Price :- " + data.bid_current_price(Symbol));                   // current price print

                    if ( data.cost > -1 ) {


                        writer.flush();
                        writer.println( "default" +"\t"+ data.st.price +"\t"+ data.st.time  );
                        if( data.st.Chain != null ){
                            for ( int i = 0 ; i < data.st.Chain.size()  ; i++ ) {
                                writer.println(  data.st.Chain.get(i).client + "\t" + data.st.Chain.get(i).price + "\t" + data.st.Chain.get(i).time ) ;
                            }
                        }

                        writer.print("New bid value :- ");
                        writer.flush();
                        String value = reader.readLine() ;
                        if ( value.equals("^C") ) break;
                        if ( value == null ) {
                            break;
                        }


                        float newvalue = Float.valueOf(value);

                        //System.out.println(data.cost);
                        if (data.cost < newvalue) {
                            data.bid_price(name, newvalue , LocalDateTime.now() );
                            serverGUI.addRow( Symbol  );

                        }
                        if (reader == null) {
                            break;
                        }
                    }


            }
            writer.flush();
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
*/