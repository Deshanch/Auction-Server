import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bid_data_structure {

    public String[] csv_data ;              // csv one line
    public float price ;                    // initial price
    public LocalDateTime time ;
    public ArrayList< Chain_node > Chain ;  // new bid

    Bid_data_structure( String[] csv_data , float price , LocalDateTime time ){     // initiate one security stock data
        this.csv_data = (csv_data) ;
        this.price = price ;
        this.time = time ;

    }

    public void Bidding( Chain_node node ){                     // new bid
        this.Chain.add(node) ;
    }

    public void creat_chain( Chain_node node ){                 // 1st bid
        Chain = new ArrayList<>() ;
        Chain.add( node );
    }
}


