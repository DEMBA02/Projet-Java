package Show;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Tables.Client;
import Tables.Local;
import Tables.Reservations;

public interface Iservice{
    public JSONObject createLocal(Local local);
    public JSONObject createClient(Client client);
    public JSONObject createReservation(Reservations reserv);

    public JSONArray searchClient(int nci);
    public JSONArray searchLocal(String ref);
    public JSONArray searchReservation(int id);   

    public void listerReservation();
    public void listerLocaux();
    public void ListerLocalDispo();
    public void showDetailLocal(JSONArray local);
    public void doReservation(Reservations res);
    public void annulerReservation(int id);
	public void addClient(Client client);
	public void ListerLocalReserByClient(int nci);
}
