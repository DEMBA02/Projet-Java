package Tables;
import java.sql.Time;
import java.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Show.Iaffiche;

@SuppressWarnings("unused")
public class Reservations implements Iaffiche{

    /**déclaration des variables  */
    private LocalDate date ;
    private Time duree;
    private boolean etat;
    private int id ;
    public  static int idGenere;

// attribut navigationnel  avec table local
    private Local local;
    public Local getLocal() {
        return local;
    }
// attribut navigationnel 2 avec table client
    private Client client = new Client();
    public Client getClient() {
        return client;
    }
    @SuppressWarnings("unchecked")
	public JSONObject setClient(Client client , JSONObject local) {
        this.client = client;
        JSONObject cl = client.ser.createClient(client);
        JSONObject res = client.ser.createReservation(this);
        JSONObject arr = new JSONObject();
        res.put("client",cl);
        res.put("local",local);
        return res;
        
    }
    @SuppressWarnings("unchecked")
	public JSONObject setClientExist(JSONObject client , JSONObject local) {
    	Client cl = new Client();
        JSONObject res = cl.ser.createReservation(this);
        JSONObject arr = new JSONObject();
        res.put("client",client);
        res.put("local",local);
        return res;
        
    }
    // getters and setters 
    // getters 
    public LocalDate getDate() {
        return date;
    }
    public int getId() {
        return id;
    }
    public boolean isEtat() {
        return etat;
    }
    public Time getDuree() {
        return duree;
    }
    //setters
    public void setEtat(boolean etat) {
        this.etat = etat;
    } 
    public void setDuree(Time duree) {
        this.duree = duree;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setId(int id) {
        this.id = id;
    }
    // Constructeurs 
    public Reservations()
    {
        id =++idGenere;
        setEtat(true);
    }
    public Reservations(LocalDate date, boolean etat)
    {
        id =++idGenere;
        setDate(date);
        setEtat(etat);
    }
    @Override
    public String afficher()
    {
        return "id :"+getId()
        +"\n  date ;"+getDate()
        +"\n duree : "+getDuree()
        +"\n etat : "+isEtat()
        ;
    }
    
}
