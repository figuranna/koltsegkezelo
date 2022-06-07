package koltseg.business;

import koltseg.business.os.osszKoltseg;
import koltseg.fio.fio;

public class biztosKoltseg extends osszKoltseg{
    
    @GetterFunctionName(name="getHaviKiadas")
    private BiztosEnum haviKiadas;

    public biztosKoltseg(BiztosEnum haviKiadas, String nev, Integer ho) {
        super(nev, ho);
        this.haviKiadas = haviKiadas;
    }

    public BiztosEnum getHaviKiadas() {
        return haviKiadas;
    }
    
    public void mentes(){
        fio<biztosKoltseg> f = new fio<biztosKoltseg>();
        f.mentes(this, bfajlnev);
    }
    
    @Override
    public String toString() {
        return super.toString() + "Költség típusa: " + this.haviKiadas + "\r\n";
    }
}
