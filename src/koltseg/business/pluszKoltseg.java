package koltseg.business;

import koltseg.business.os.osszKoltseg;
import koltseg.fio.fio;

public class pluszKoltseg extends osszKoltseg{
    
    @GetterFunctionName(name="getPlusz")
    private Integer plusz;

    public pluszKoltseg(Integer plusz, String nev, Integer ho) {
        super(nev, ho);
        this.plusz = plusz;
    }

    public Integer getPlusz() {
        return plusz;
    }

    public void setPlusz(Integer plusz) {
        this.plusz = plusz;
    }
    
    public void mentes(){
        fio<pluszKoltseg> f = new fio<pluszKoltseg>();
        f.mentes(this, pfajlnev);
    }
    
    @Override
    public String toString() {
        return super.toString() + "Költség: " + this.plusz + "\r\n";
    }
}
