package koltseg.business.os;

import koltseg.business.GetterFunctionName;

public abstract class osszKoltseg {
    
    public static final String pfajlnev = "pluszkoltsegek.xml";
    public static final String bfajlnev = "biztoskoltsegek.xml";
    public static final String fizetes = "fizetes.xml";
    public static final String honap = "honap.xml";
    public static final String egyenleg = "egyenleg.xml";
    
    @GetterFunctionName(name="getNev")
    private String nev;
    @GetterFunctionName(name="getHo")
    private Integer ho;

    public osszKoltseg() {
    }

    public osszKoltseg(String nev, Integer ho) {
        this.nev = nev;
        this.ho = ho;
    }

    public String getNev() {
        return nev;
    }

    public Integer getHo() {
        return ho;
    }
    
    
    @Override
    public String toString() {
        return "\r\nNév: " + this.nev + "\r\nHónap: " + this.ho +"\r\n";
    }
}
