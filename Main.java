

public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException {
	// write your code here

    csvcontrol csv = new csvcontrol() ;
    csv.start();


    Server server = new Server() ;
    server.start();


    ServerGUI gui = new ServerGUI();
    gui.ServerGUI() ;
    gui.start();




    }


}
