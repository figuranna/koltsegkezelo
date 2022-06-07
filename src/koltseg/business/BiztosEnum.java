package koltseg.business;

public enum BiztosEnum {
    
    ELOFIZETESEK(11000), UTAZAS(8000), REZSI(20000);
    
    private final Integer ar;

    private BiztosEnum(Integer ar) {
        this.ar = ar;
    }

    public Integer getAr() {
        return ar;
    }
    
}
