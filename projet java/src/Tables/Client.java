package Tables;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Main.Service;
import Show.Iaffiche;
@SuppressWarnings("unused")
public class Client implements Iaffiche{
/** définition des variables */
    private int nci;
    private String nomComplet;
    private int tel ;
    private String adresse;
    private String email;
    public Service ser = new Service();
// getters and setters 
    //getters 
    public String getEmail() 
    {
        return email;
    }
    public String getAdresse() 
    {
        return adresse;
    }
    public int getTel() 
    {
        return tel;
    }
    public String getNomComplet() 
    {
        return nomComplet;
    }
    public int getNci() 
    {
        return nci;
    }
    //setters 
    public void setNci(int nci) 
    {
        this.nci = nci;
    }   
    public void setEmail(String email) 
    {
            this.email = email;
    }
    public void setAdresse(String adresse) 
    {
            this.adresse = adresse;
    }
    public void setTel(int tel) 
    {
        this.tel = tel;
    }
    public void setNomComplet(String nomComplet) 
    {
        this.nomComplet = nomComplet;
    }
// Constructeurs 
    public Client()
    {
    }
    public Client(int nci , String nomComplet ,int tel, String adresse,String email )
    {
        setNci(nci);
        setNomComplet(nomComplet);
        setTel(tel);
        setAdresse(adresse);
        setEmail(email);
    }
    @Override
    public String afficher()
    {
        return "Nci :"+getNci()
        +"\n NomComplet : "+getNomComplet()
        +"\n Tel : "+getTel()
        +"\n Adresse : "+getAdresse()
        +"\n Email : "+getEmail()
        ;
    }
}
