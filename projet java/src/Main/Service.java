package Main;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Show.Iservice;
import Tables.Client;
import Tables.Local;
import Tables.Reservations;

public class Service implements Iservice
{
    private  JSONArray listeReservation= new  JSONArray();
    private  JSONArray listeClient= new  JSONArray();
    private  JSONArray listeLocal = new  JSONArray();
            
    public void saveFile(JSONArray array, String file)
    {   
      try {
            FileWriter fi = new FileWriter(file);
            fi.write(array.toString());
            fi.flush();
            fi.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JSONArray readFile( String file , JSONArray json)
    {
    	JSONParser jsonParser = new JSONParser();       
    	try{
        FileReader reader = new FileReader(file);
         Object obj = jsonParser.parse(reader);
         JSONArray lList = (JSONArray) obj;
	         json = lList;
	
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     } catch (ParseException e) {
	         e.printStackTrace();
	     }
    	return json;
 }	
    @Override
    @SuppressWarnings("unchecked")
	public void addClient(Client client) 
	 {
	    	JSONObject  l = createClient(client);
	        JSONArray json =  new JSONArray();
	        JSONArray data = readFile("Clients.json",json);
	        data.add(l);
	        this.saveFile(data,"Clients.json");
	 }
        
 
 @SuppressWarnings({ "unchecked" })
 	private static void parseObject(JSONObject object, JSONArray data , String typeLocal ) 
 	{
	     String type = (String) object.get("type");
	     if (type.compareToIgnoreCase(typeLocal)==0)
	     {
	    	 data.add(object);
	     }
    }
 @SuppressWarnings("unchecked")
 	private static void parseObjectL(JSONObject object, JSONArray data , String ref ) 
 	{
	     String rf = (String) object.get("ref");
	     if (rf.compareToIgnoreCase(ref)==0)
	     { 
	    	 data.add(object);
	     }
    }
 @SuppressWarnings("unchecked")
 	private static void parseObjectCl(JSONObject object, JSONArray data , int nci ) 
 	{   
	     String ncI = (String) object.get("nci").toString();
	     int cle = Integer.parseInt(ncI);
	     if (cle == nci)
	     {
	    	 data.add(object);
	     }
    }
 @SuppressWarnings("unchecked")
 	private static void parseObjectRs(JSONObject object, JSONArray data , int id) 
 	{
	     String iD = (String) object.get("id").toString();
	     int cle = Integer.parseInt(iD);
	     if (cle == id)
	     {
	    	 data.add(object);
	     }
 	}
	 @SuppressWarnings("unchecked")
	private static void parseObjectRsCl(JSONObject object, JSONArray data , int nci) 
		{
		     JSONObject ob = (JSONObject) object.get("client");
		     JSONObject obL = (JSONObject) object.get("local");
		     String nc = (String) ob.get("nci").toString();
		     int cle = Integer.parseInt(nc);
		     if (cle == nci)
		     {
		    	  Object id =  object.get("id").toString();
		    	  Object duree =  object.get("duree");
		    	  Object etat = object.get("etat");
		    	  Object date =  object.get("date");
				    	 JSONObject Obt = new JSONObject();
				    	 Obt.put("id",id);
				    	 Obt.put("duree",duree);
				    	 Obt.put("etat",etat);
				    	 Obt.put("date",date);
				    	 JSONObject Ob = new JSONObject();
				    	 Ob.put("reservation", Obt);
				    	 Ob.put("local",obL);
				    	 data.add(Ob);
		     }
		}
	private static void parseObjectRsClShow(JSONObject object , int nci) 
		{
		     JSONObject ob = (JSONObject) object.get("client");
		     JSONObject obL = (JSONObject) object.get("local");
		     String nc = (String) ob.get("nci").toString();
		     int cle = Integer.parseInt(nc);
		     if (cle == nci)
		     {	    	
				 System.out.println(obL);
		     }
		}
	 @SuppressWarnings("unchecked")
	private static void parseObjectRsL(JSONObject object,JSONArray data , int nci) 
	{
	     JSONObject ob = (JSONObject) object.get("client");
	     String nc = (String) ob.get("nci").toString();
	     int cle = Integer.parseInt(nc);
	     if (cle == nci)
	     {
	    	  Object id =  object.get("id").toString();
	    	  Object duree =  object.get("duree");
	    	  Object etat = object.get("etat");
	    	  Object date =  object.get("date");
			    	 JSONObject Obt = new JSONObject();
			    	 Obt.put("id",id);
			    	 Obt.put("duree",duree);
			    	 Obt.put("etat",etat);
			    	 Obt.put("date",date);
			    	 JSONObject Ob = new JSONObject();
			    	 Ob.put("reservation", Obt);
			    	 Ob.put("client", ob);			    	
			    	 data.add(Ob);
	     }
	}
 @SuppressWarnings("unchecked")
 	private static void modify(JSONObject emp) 
 	{
 		emp.replace("etat", "false");
 	}
 	public static void parseObjectInfo(JSONObject object) 
 	{
		String ref = (String) object.get("ref");
		String localisation = (String) object.get("localisation"); 
		Object prix =  object.get("prix"); 
		Object tauxPoc = object.get("tauxPoc");
		String type = (String) object.get("type");
		System.out.println
		(		"):"
				+ "\n \t ref : " + ref 
				+"\n \t localisation : "+localisation
				+"\n \t prix : "+prix
				+"\n \t tauxPoc : "+tauxPoc
				+"\n \t type : "+type
				+"\n :("
		);
 	}
 	public static void parseObjectAll(JSONObject object) 
 	{
	     System.out.println(object);
    }
 	@SuppressWarnings("unchecked")
	public static void parseObjectLDisp(JSONObject object , JSONArray data) 
 	{
	     Object key =  object.get("reservation");
	     if (key == null) {
	    	 System.out.println("):"
	    	 		+ "\n"
	    	 		+ "Les locaux disponibles "
	    	 		+ "\n"
	    	 		+ ":("
	    	 		+ "\n\n");
	    	 System.out.println("\t\t"+object);
	    	 
	    	 
	    	 System.out.println(" \n\n :(");
	    	 data.add(object);
	     }
    }
 	public static void parseObjectLDispo(JSONObject object) 
 	{
 		 Object key =  object.get("reservation");
	     if (key == null) {
	    	 System.out.println("):"
	    	 		+ "\n"
	    	 		+ "Les locaux disponibles "
	    	 		+ "\n"
	    	 		+ ":("
	    	 		+ "\n\n");
	    	 System.out.println("\t\t"+object);
	    	 System.out.println(" \n\n :(");
	     }
	}
	
 	
 	
 	@SuppressWarnings("unchecked")
	@Override
	public JSONObject createLocal(Local local) 
	{
		 	JSONObject l = new JSONObject();
	        l.put("ref", local.getRef());
	        l.put("localisation", local.getLocalisation());
	        l.put("prix", local.getPrix());
	        l.put("tauxPoc", local.getTauxPoc());
	        l.put("type", local.getType());
	        return l;
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject createClient(Client client) 
	{
		 	JSONObject c = new JSONObject();
	        c.put("nci", client.getNci());
	        c.put("nomComplet", client.getNomComplet());
	        c.put("tel", client.getTel());
	        c.put("adresse", client.getAdresse());
	        c.put("email", client.getEmail());
	        return c;	
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject createReservation(Reservations reserv) 
	{
		 	JSONObject r = new JSONObject();
	        r.put("id", reserv.getId());
	        r.put("duree", reserv.getDuree());
	        r.put("etat", reserv.isEtat());
	        r.put("date", reserv.getDate());
	  	    return r;
	}
	@SuppressWarnings({ "unchecked" })
	@Override
	public JSONArray searchClient(int nci) 
	{
		JSONArray dataClient = new JSONArray();
		JSONArray lList = this.readFile("Clients.json", listeClient);
		lList.forEach( emp -> Service.parseObjectCl( (JSONObject) emp , dataClient, nci));
		return dataClient;
	}
	@SuppressWarnings({ "unchecked" })
	@Override
	public JSONArray searchLocal(String type) 
	{
		JSONArray dataLocal = new JSONArray();
		JSONArray lList = this.readFile("Locaux.json", listeLocal);
		lList.forEach( emp -> Service.parseObject( (JSONObject) emp , dataLocal, type));
		return dataLocal;
	}
	@SuppressWarnings({ "unchecked" })
	public JSONArray searchLocalByRef(String ref) {
		JSONArray data = new JSONArray();
		JSONArray lList = this.readFile("Locaux.json", listeLocal);
		lList.forEach( emp -> Service.parseObjectL( (JSONObject) emp , data, ref));
		return data;
	}
	@SuppressWarnings({ "unchecked" })
	@Override
	public JSONArray searchReservation(int id) {
		JSONArray dataReser = new JSONArray();
		JSONArray lList = this.readFile("Reservations.json", listeReservation);
		lList.forEach( emp -> Service.parseObjectRs( (JSONObject) emp , dataReser , id));
		return dataReser;
	}
	@SuppressWarnings("unchecked")
	public JSONArray searchReservationByclient(int nci) {
		JSONArray dataReser = new JSONArray();
		JSONArray lList = this.readFile("Reservations.json", listeReservation);
		lList.forEach( emp -> Service.parseObjectRsCl( (JSONObject) emp , dataReser , nci));
		return dataReser;
	}
	@SuppressWarnings("unchecked")
	public JSONArray searchLocalByRes(int nci) {
		JSONArray dataReser = new JSONArray();
		JSONArray lList = this.readFile("Reservations.json", listeReservation);
		lList.forEach( emp -> Service.parseObjectRsL( (JSONObject) emp , dataReser , nci));
		return dataReser;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void ListerLocalReserByClient(int nci) {
		JSONArray lList = this.readFile("Reservations.json", listeReservation);
		lList.forEach( emp -> Service.parseObjectRsClShow( (JSONObject) emp , nci));
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void ListerLocalDispo() {
		JSONArray lList = this.readFile("locaux.json", listeLocal);
        lList.forEach( emp -> Service.parseObjectLDispo( (JSONObject) emp) ); 
	}
	@SuppressWarnings("unchecked")
	public JSONArray ListerLocalDisp() {
		JSONArray dataLoc = new JSONArray();
		JSONArray lList = this.readFile("locaux.json", listeLocal);
        lList.forEach( emp -> Service.parseObjectLDisp( (JSONObject) emp , dataLoc)); 
        return dataLoc;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void showDetailLocal(JSONArray local) 
	{
		local.forEach( emp -> Service.parseObjectInfo( (JSONObject) emp) );
	}
	@SuppressWarnings("unchecked")
	@Override
    public void listerReservation() 
	{
		JSONArray lList = this.readFile("Reservations.json", listeLocal);
        lList.forEach( emp -> Service.parseObjectAll( (JSONObject) emp) );
        
    }
    @SuppressWarnings("unchecked")
	@Override
    public void listerLocaux() 
    {
        JSONArray lList = this.readFile("locaux.json", listeLocal);
        lList.forEach( emp -> Service.parseObjectAll( (JSONObject) emp) ); 
    }
    @SuppressWarnings("unchecked")
	@Override
    public void doReservation(Reservations res){
    	JSONObject  l = createReservation(res);
        JSONArray json =  new JSONArray();
        JSONArray data = readFile("Reservations.json",json);
        data.add(l);
        this.saveFile(data,"Reservations.json");
        System.out.println("): "
        		+ "\n Merci d'avoir fait votre réservation sur notre application"
        		+ "\n :(");
    }
    @SuppressWarnings({ "unchecked" })
	public void annulerReservation(int idCancel)
    {
    	JSONArray obj = searchReservation(idCancel);
    	if ( obj != null)
		{
    		obj.forEach( emp -> Service.modify((JSONObject) emp) );    		
		}
    }
}

