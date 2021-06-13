package Main;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Library.Validator;
import Tables.Appartements;
import Tables.Chambres;
import Tables.Client;
import Tables.Local;
import Tables.Reservations;

import java.util.List;

@SuppressWarnings("unused")
public class Main 
{
    public static Scanner scanner = new Scanner(System.in);
    @SuppressWarnings({ "unchecked", "static-access" })
	public static void main(String args[])
    {

        Service service = new Service();
        Validator val = new Validator();
        int choix;
        do
        {
        	System.out.println("): \n \n Bienvenu dans notre application"
        		+ "\n"
        		+ " \n"
        		+ "\n Que puissions nous faire pour vous ?"
        		+ "\n"
        		+ "\n");
            choix=menu();
			switch(choix)
            {	
                case 1:
                	do 
                	{
                		choix=menu1ocal();
                    	switch(choix)
                    	{
                    	case 1:
                    		System.out.println("Veuillez saisir la localisation ");
                        	String t = scanner.nextLine();
                        	if(!val.estVide(t)) {
                            	t = scanner.nextLine();
                        	}
                        	String localisation = t;
                        	System.out.println("Veuillez saisir le prix  ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.isNumeric(t)) {
                        		t =scanner.nextLine();
                        	}
                        	int prix = Integer.parseInt(t);
                        		
                        	System.out.println("Veuillez saisir le taux ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.isNumeric(t)) {
                        		t =scanner.nextLine();
                        	}
                        	float  taux = Float.parseFloat(t);
                        	System.out.println("Veuillez saisir la dimension de la chambre ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.isNumeric(t)) {
                        		t =scanner.nextLine();
                        	}
                        	int dimension =Integer.parseInt(t);
                    		Local chambre = new Chambres(localisation,prix,taux,dimension);
                    		JSONObject  l = service.createLocal(chambre);
                    		l.put("dimension", ((Chambres) chambre).getDimension());
                            JSONArray json =  new JSONArray();
                            JSONArray data = service.readFile("Locaux.json",json);
                            data.add(l);
                            service.saveFile(data,"Locaux.json");
                            System.out.println(" ):"
                            		+ "\n Merci d'avoir ajouter un local dans notre application"
                            		+ "\n :(");
                    		break;
                    	case 2:
                    		System.out.println("Veuillez saisir la localisation ");
                        	t = scanner.nextLine();
                        	if(!val.estVide(t)) {
                            	t = scanner.nextLine();
                        	}
                        	localisation = t;
                        	System.out.println("Veuillez saisir le prix  ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.isNumeric(t)) {
                        		t =scanner.nextLine();
                        	}
                        	prix = Integer.parseInt(t);
                        		
                        	System.out.println("Veuillez saisir le taux ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.isNumeric(t)) {
                        		t =scanner.nextLine();
                        	}
                            taux = Float.parseFloat(t);
                        	System.out.println("Veuillez saisir le nombre de piece de la chambre ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.isNumeric(t)) {
                        		t =scanner.nextLine();
                        	}
                        	int nbrePiece =Integer.parseInt(t);
                    		Local app = new Appartements(localisation,prix,taux,nbrePiece);
                    		JSONObject  ll = service.createLocal(app);
                    		ll.put("nombre_de_piece", ((Appartements) app).getNbrePiece());
                            json =  new JSONArray();
                            data = service.readFile("Locaux.json",json);
                            data.add(ll);
                            service.saveFile(data,"Locaux.json");
                            System.out.println(" ):"
                            		+ "\n Merci d'avoir ajouter un local dans notre application"
                            		+ "\n :(");
                    		break;
                    	case 3:
                    		break;
                    	default :
                    		System.out.println("Mauvais choix :( ");
                            break;
                    	}
                	}while(choix!=3);
                    break;
                case 2:
                	do 
                	{
                		choix=menulister();
                    	switch(choix)
                    	{
                    	case 1:
							
							  JSONArray dataByTypeLocalCh = service.searchLocal("Chambres"); 
							  if(!dataByTypeLocalCh.isEmpty()) 
							  { 
								  dataByTypeLocalCh.forEach( emp -> {
									  System.out.println(emp);
								  } );
							  }	else {
								  System.out.println("Cette chambre n'existe pas ");
							  }
                    		break;
                    	case 2:
                    		
                    		JSONArray dataByTypeLocalApp = service.searchLocal("appartements");
                    		if (!dataByTypeLocalApp.isEmpty())
                    		{
                    			 dataByTypeLocalApp.forEach( emp -> {
									  System.out.println(emp);
								  } );
                    		}else {
                    			System.out.println("Cet appartement n'existe pas ");
                    		}
                    		break;
                    	case 3:
                    		break;
                    	default :
                    		System.out.println("Mauvais choix :( ");
                            break;
                    	}
                	}while(choix!=3);
                    break;
                case 3:
                	System.out.println("veuillez entrez le nci ");	
                	String t = scanner.nextLine();
                	if (!val.estVide(t) || !val.isNci(t)) {
                		t =scanner.nextLine();
                	}
                	int nci = Integer.parseInt(t);
                	service.ListerLocalReserByClient(nci);
                    break;
                case 4:
                	System.out.println("veuillez entrez la reference du local en format RefCCCC ");
                	t = scanner.nextLine();
                	if (!val.estVide(t)) {
                		t =scanner.nextLine();
                	}
                	String ref = t;
                	JSONArray data = service.searchLocalByRef(ref);
                	service.showDetailLocal(data);
                    break;
                case 5:
                	Client client = new Client();
                    Reservations res = new Reservations();   
                    JSONArray js =  service.ListerLocalDisp();
                    if(!js.isEmpty()) 
                    {
                    	System.out.println("Veuillez saisir le nci du client");
                    	t = scanner.nextLine();
                    	if (!val.estVide(t) || !val.isNci(t)) {
                    		t =scanner.nextLine();
                    	}
                        nci  = Integer.parseInt(t);
	                    System.out.println("Veuillez saisir la reference du local ");
	                    ref  = scanner.nextLine();
	                    js	=  service.searchClient(nci);
	                    if (js.isEmpty()) 
	                    {
	                    	System.out.println("Veuillez vous inscrire "
	                    			+ "\n Veuillez saisir votre nci ");
                        	t = scanner.nextLine();
                        	if(!val.estVide(t) || !val.isNci(t)) {
                            	t= scanner.nextLine();
                        	}
                        	nci = Integer.parseInt(t); 
                        	System.out.println("Veuillez saisir votre nom Complet ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.valName(t)) {
                        		t =scanner.nextLine();
                        	}
                        	String nom = t;
                        		
                        	System.out.println("Veuillez saisir votre numéro de téléphone ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.isTel(t)) {
                        		t =scanner.nextLine();
                        	}
                            int tel = Integer.parseInt(t); 
                        	System.out.println("Veuillez saisir l'adresse ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t)) {
                        		t =scanner.nextLine();
                        	}
                        	String adresse =t;
                        	System.out.println("Veuillez saisir votre email ");
                        	t =scanner.nextLine();
                        	if(!val.estVide(t) || !val.isEmail(t)) {
                        		t =scanner.nextLine();
                        	}
                        	String email =t;
	                    	
	                    	
		                    client = new Client(nci,nom,tel,adresse,email);
		                    js	=  service.searchLocal(ref);
		                    if (js.isEmpty()) 
		                    {
		                    	 System.out.println("le local n'existe pas ");
		                    	 break;
		                    }else
		                    {
		                    	 JSONObject local = (JSONObject) js.get(0);
		                    JSONObject obj = res.setClient(client,local);
		                   
		                    JSONArray json =  new JSONArray();
		                    JSONArray jsonR =  new JSONArray();
		                    JSONArray jsonL =  new JSONArray();
		                    
		                    
		                    JSONArray dataR = service.readFile("Reservations.json",json);
		                    dataR.add(obj);
		                    service.saveFile(dataR,"Reservations.json");
		                    JSONArray jsonArray = service.searchReservationByclient(client.getNci());
		                    JSONObject cli = service.createClient(client);
		                    cli.put("reservation", jsonArray);
		                    
		                    JSONArray jsonArrayL = service.searchLocalByRes(client.getNci());
		                    
		                    local.put("reservation", jsonArrayL);
		                    
		                    
		                    JSONArray donnee = service.readFile("Clients.json", jsonR);
		                    donnee.add(cli);
		                    service.saveFile(donnee,"Clients.json");
		                    
		                    JSONArray dataL = service.readFile("Locaux.json", jsonL);
		                    dataL.add(local);
		                    service.saveFile(dataL,"Locaux.json");
		                    }
		                    break;
	                   }else 
	                   {
	                    	  JSONObject cl = (JSONObject) js.get(0);
	                    	  js	=  service.searchLocal(ref);
	                          if (js.isEmpty()) 
	                          {
	                          	 System.out.println("le local n'existe pas ");
	                          	 break;
	                          }else 
	                          {
	                        	  JSONObject l = (JSONObject) js.get(0);
	                          JSONObject obj = res.setClientExist(cl,l);
	                         
	                          JSONArray json =  new JSONArray();
	                          JSONArray jsonR =  new JSONArray();
	                          JSONArray jsonL =  new JSONArray();
	                          
	                          
	                          JSONArray dataR = service.readFile("Reservations.json",json);
	                          dataR.add(obj);
	                          service.saveFile(dataR,"Reservations.json");
	                          String nc = (String) cl.get("nci").toString();
	                          nci= Integer.parseInt(nc);
	                          JSONArray jsonArray = service.searchReservationByclient(nci);
	                          cl.put("reservation", jsonArray);
	                          
	                          JSONArray jsonArrayL = service.searchLocalByRes(nci);
	                          
	                          l.put("reservation", jsonArrayL);
	                          
	                          JSONArray donnee = service.readFile("Clients.json", jsonR);
	                          donnee.add(cl);
	                          service.saveFile(donnee,"Clients.json");
	                          
	                          JSONArray dataL = service.readFile("Locaux.json", jsonL);
	                          dataL.add(l);
	                          service.saveFile(dataL,"Locaux.json");
	                          }
	                    }
                    }else 
                    {
                    	System.out.println("Pas de local disponible");
                    }
                    break;
                case 6:
                	
                	service.listerReservation();
                	System.out.println("\n\nVeuillez saisir l' id de la reservation ");
                	t = scanner.nextLine();
                	if (val.estVide(t) || !val.isNumeric(t)) {
                		t =scanner.nextLine();
                	}
                	int id = Integer.parseInt(t);
                	service.annulerReservation(id);
                    break;
                case 7:
                	service.ListerLocalDispo();
                    break;
                case 8:
                	 System.out.println(
                			 " \n ):"
                	 		+ "\n Merci d'avoir utliser notre application"
                	 		+ "\n :(");
                    break;
                default:
                    System.out.println("Mauvais choix :( ");
                    break;
                
            }
        }while(choix!=8);
    }

    @SuppressWarnings("static-access")
	public static int menu()
    {
    	 Validator val = new Validator();
        int choix;
        System.out.println
        ("\n Menu" 
        + "\n 1- Ajouter un local "  
        + "\n 2- Lister les locaux par type "
        + "\n 3- Lister les locaux réservés par un client"
        + "\n 4- Voir les détails d'un local "
        + "\n 5- Faire une réservation "
        + "\n 6- Annuler une réservation "
        + "\n 7- Lister les locaux disponibles "
        + "\n 8- Quitter "
        + "\n \n :("
        + "\n"
        + "\n");
        System.out.println("Faites votre choix : ");
        String t = scanner.nextLine();
    	if (!val.estVide(t) || !val.isNumeric(t)) {
    		t =scanner.nextLine();
    	}
        choix = Integer.parseInt(t);
        return choix;
    } 
    @SuppressWarnings("static-access")
	public static int menu1ocal()
    {
    	 Validator val = new Validator();
    	int type;
        System.out.println
        ("):\n \nle type de local"
        		+ "\n" 
        + "\n 1- chambres "  
        + "\n 2- Appartements"
        + "\n 3- Quitter"
        + "\n\n :("
        + "\n");
        System.out.println("Faites votre choix : ");
        String t = scanner.nextLine();
    	if (!val.estVide(t) || !val.isNumeric(t)) {
    		t =scanner.nextLine();
    	}
        type = Integer.parseInt(t);
        return type;
    }
    @SuppressWarnings("static-access")
	public static int menulister()
    {
    	 Validator val = new Validator();
    	int typeLocal;
        System.out.println
        ("):\n Choisissez le type de local"
        		+ "\n" 
        + "\n 1- Lister les locaux de type chambres "  
        + "\n 2- Lister les locaux de type appartements"
        + "\n 3- Quitter"
        + "\n :( "
        + "\n");
        System.out.println("Faites votre choix : ");
        String t = scanner.nextLine();
    	if (!val.estVide(t) || !val.isNumeric(t)) {
    		t =scanner.nextLine();
    	}
        typeLocal = Integer.parseInt(t);
        return typeLocal;  
    }
}
