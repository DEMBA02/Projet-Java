package Tables;
import org.json.simple.JSONArray;

import Show.Iaffiche;
import Show.Icout;
@SuppressWarnings("unused")
public class Appartements extends Local implements Icout,Iaffiche{
    /** déclaration des variables */
    private int nbrePiece;
    @Override
    public float cout(int prix )
    {
        return prix+ prix*getTauxPoc();
    }
    @Override
    public String afficher()
    {
        return super.afficher()
        +"\n Nombre de Pieces:"+getNbrePiece()
        ;
    }
    public int getNbrePiece() {
        return nbrePiece;
    }
    public void setNbrePiece(int nbrePiece) {
        this.nbrePiece = nbrePiece;
    }
    public Appartements()
    {
        type="appartements";
    }
   public Appartements(String localisation , int prix , float tauxPoc , int nbrePiece)
    {
	   super(prix,localisation,tauxPoc);
        type="appartements";
        setNbrePiece(nbrePiece);
    }  
}
