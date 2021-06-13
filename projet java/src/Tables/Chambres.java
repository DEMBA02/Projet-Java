package Tables;

import Show.Iaffiche;
import Show.Icout;

public class Chambres extends Local implements Icout,Iaffiche 
{
    /** déclaration des variables */
    private int dimension ;

    public int getDimension() {
        return dimension;
    }
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    public Chambres(String localisation , int prix , float tauxPoc ,int dimension )
    {
        super(prix,localisation,tauxPoc);
        type="Chambres";
        setDimension(dimension);
    }
    public Chambres()
    {
    	super();
        type="Chambres";
    }
    @Override
    public float cout(int prix)
    {   
        return  prix + prix*getTauxPoc();
    }
    @Override
    public String afficher()
    {
        return super.afficher()
        +"\n Dimension:"+getDimension()
        ;
    }
}
