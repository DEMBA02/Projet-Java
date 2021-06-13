package Tables;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Main.Service;
import Show.Iaffiche;
@SuppressWarnings("unused")
public abstract class Local  implements Iaffiche{
    /**local abstract 
     * classe fille 
     * chambres 
     * appartements
    */
    /** déclaration des variables  */
    protected String ref;
    protected String localisation;
    protected int prix;
    protected float tauxPoc;
    private final int FORMAT=4;
    private static int refGenere;
    protected String type; 
    // instantiation de la classe service
    public Service ser = new Service();
    // getters and setters
    //getters 
    public String getType()
    {
        return type;
    }
    public String getRef() 
    {
        return ref;
    }
    public float getTauxPoc() 
    {
        return tauxPoc;
    }
    public int getPrix() 
    {
        return prix;
    }
    public String getLocalisation() 
    {
        return localisation;
    }
    //setters 
    public void setType(String type)
    {
        this.type=type;
    }
    public void setPrix(int prix) 
    {
        this.prix = prix;
    }
    public void setLocalisation(String localisation) 
    {
        this.localisation = localisation;
    }
    public void setRef(String ref) 
    {
        this.ref = ref;
    }
    public void setTauxPoc(float tauxPoc) 
    {
        this.tauxPoc = tauxPoc;
    }
    //fonction de génération de ref sous format RefCCCC
    private String generateRef()
    {
        String nombreC = "";
        String nombreDeRefString = String.valueOf(++refGenere);
        for(int i=1; i<=(FORMAT - nombreDeRefString.length()); i++)
        {
            nombreC += "0";
        }
        return "Ref"+ nombreC + nombreDeRefString ;
    }
    //Constructeurs
    public Local()
    {
    	ref=generateRef();
    }
    public Local(int prix ,String localisation , float tauxPoc)
    {
        ref=generateRef();
        this.setPrix(prix);
        this.setLocalisation(localisation);
        this.setTauxPoc(tauxPoc);
    }   
    @Override
    public String afficher()
    {
    return "ref :"+getRef()
        +"\n Localisation ;"+getLocalisation()
        +"\n Prix:"+getPrix()
        +"\n tauxPoc"+getTauxPoc()
        +"\n type:"+getType();
    }
}

