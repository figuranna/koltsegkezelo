package koltseg.business;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import koltseg.business.os.osszKoltseg;
import koltseg.fio.fio;

public class Metodusok {
    
    private static final Scanner scn = new Scanner(System.in);
    private static final Scanner scn2 = new Scanner(System.in);
    private static Integer ossz = 0;
    private static Integer ossz1 = 0;
    private static Integer ossz2 = 0;
    
    private static Integer fizetes = fio.beolvasas(osszKoltseg.fizetes);
    private static Integer egyenleg = fio.beolvasas(osszKoltseg.egyenleg);
    private static Integer ho = fio.beolvasas(osszKoltseg.honap);
    
    public static void fizetesBeker(){
        if(fizetes == 0){
            try{
                System.out.println("Kérem adja meg a havi jövedelmét: ");
                fizetes = scn.nextInt();
                if(fizetes < 0){
                        System.out.println("Nem lehet negatív a fizetése!");
                    }
                egyenleg = fizetes;
                fio.ementes(fizetes, osszKoltseg.fizetes);
                fio.ementes(egyenleg, osszKoltseg.egyenleg);
            }
            catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                fizetesBeker();
            }
        }
        else{
            int tmp; 
            try{
                System.out.println("Jelenlegi havi jövedelme: " + fizetes + "\n\nMódosítani kívánja?" + "\r\n 1 -> Igen" + "\r\n 2 -> Nem");
                tmp = scn.nextInt();

                switch(tmp){
                    case 1:
                        System.out.println("Kérem adja meg a havi jövedelmét: ");
                        fizetes = scn.nextInt();
                        if(fizetes < 0){
                            System.out.println("Nem lehet negatív a fizetése!");
                        }
                        fio.ementes(fizetes, osszKoltseg.fizetes);
                        fio.ementes(egyenleg, osszKoltseg.egyenleg);
                        break;
                    case 2:
                        fizetes = fio.beolvasas(osszKoltseg.fizetes);
                        break;
                    default: 
                        System.out.println("A program nem tudja értelmezni a kérését.\n");
                        fizetesBeker();
                        break;
                }
            }
            catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                fizetesBeker();
            }
        }
        
    }
    
    public static void egyenlegMegnez(){
        System.out.println("\r\nEgynelege: " + egyenleg + "\r\n");
    }
    
    public static void hoLepes(ArrayList<biztosKoltseg> biztosMinusz){
        System.out.println("\nÚj hónap kezdete.\n");
        ho++;
        egyenleg += fizetes;
        for(biztosKoltseg biztos : biztosMinusz){
            if(egyenleg >= biztos.getHaviKiadas().getAr()){
                egyenleg -=  biztos.getHaviKiadas().getAr();
            }
            else{
                System.out.println("\r\nNincsen elegendő pénze.");
            }
        }
        fio.ementes(fizetes, osszKoltseg.fizetes);
        fio.ementes(ho, osszKoltseg.honap);
    }
    
    public static void pluszKoltsegHozzaad(ArrayList<pluszKoltseg> pluszMinusz){
        int h = 0;
        try{
            System.out.println("\nAdja meg a plusz költségének árát: ");
            Integer plusz;
            plusz = scn.nextInt();

            if(egyenleg < plusz){
                System.out.println("\r\nNincsen elegendő pénze.");
            }
            else{
                egyenleg -= plusz;
                fio.ementes(egyenleg, osszKoltseg.egyenleg);
                System.out.println("\n" + plusz + "Ft le lett vonva a pénzösszegből.");

                System.out.println("\nAdja meg a plusz költségének nevét: ");
                String nev;
                nev = scn2.nextLine();
                h = -1;

                pluszKoltseg pk = new pluszKoltseg(plusz, nev, ho);
                pk.mentes();
                pluszMinusz.add(pk);
                }
        }
        catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                pluszKoltsegHozzaad(pluszMinusz);
            }
        
    }
    
    public static void biztosKoltsegHozzaad(ArrayList<biztosKoltseg> biztosMinusz){
        
        try{
            System.out.println("\nKiadás típusa: " + "\r\n1 -> Előfizetések" + "\r\n2 -> Utazás" + "\r\n3 -> Rezsi");
            Integer biztos;
            BiztosEnum biz = null;
            biztos = scn.nextInt();
            switch(biztos){
                case 1: biz = BiztosEnum.ELOFIZETESEK;break;
                case 2: biz = BiztosEnum.UTAZAS;break;
                case 3: biz = BiztosEnum.REZSI;break;
                default: System.out.println("A program nem tudja értelmezni a kérését.\n");biztosKoltsegHozzaad(biztosMinusz);
            }

            if(egyenleg < biztos){
                System.out.println("\r\nNincsen elegendő pénze.");
            }
            else{
                egyenleg -= biz.getAr();
                fio.ementes(egyenleg, osszKoltseg.egyenleg);
                System.out.println("\n" + biz.getAr() + "Ft le lett vonva a pénzösszegből.");

                System.out.println("\nAdja meg a havi költségének nevét: ");
                String nev;
                nev = scn2.nextLine();

                biztosKoltseg bk = new biztosKoltseg(biz, nev, ho);
                bk.mentes();
                biztosMinusz.add(bk);
            }
        }
        catch(Exception e){
            System.out.println("Nem számkaraktert adott meg!");
            scn.next();
            biztosKoltsegHozzaad(biztosMinusz);
        }
    }
    
    public static void pluszKoltsegKiir(ArrayList<pluszKoltseg> pluszMinusz, Integer ho){
        int a = 0, aa = 0;

        for(pluszKoltseg plus : pluszMinusz){
            if(pluszMinusz.isEmpty()&&ho.equals(plus.getHo())){
                a = -1;
            }
            else{
                if(ho.equals(plus.getHo())){
                    System.out.println(plus);
                } 
            }
            if(!ho.equals(plus.getHo())){
                aa = -1;
            }
        }
        if(a == -1){
            System.out.println("Nincsenek plusz költségei ebben a hónapban.\r\n");
        }
        if(aa == -1){
            System.out.println("\r\nNincs ilyen számú hónap.\r\n");
        }
    }
    
    public static void biztosKoltsegKiir(ArrayList<biztosKoltseg> biztosMinusz, Integer ho){
        int b = 0, bb = 0;
        
        for(biztosKoltseg biztos : biztosMinusz){
            if(biztosMinusz.isEmpty()&&ho.equals(biztos.getHo())){
                System.out.println("Nincsenek havi költségei ebben a hónapban.\r\n");
            }
            else{
                if(ho.equals(biztos.getHo())){
                    System.out.println(biztos);
                }
            }
            if(!ho.equals(biztos.getHo())){
                bb = -1;
            }
        }
        if(b == -1){
                System.out.println("Nincsenek havi költségei ebben a hónapban.\r\n");
        }
        if(bb == -1){
            
        }
    }
    
    public static void osszKoltsegKiir(ArrayList<pluszKoltseg> pluszMinusz, ArrayList<biztosKoltseg> biztosMinusz, Integer ho){
        int c = 0, cc = 0;

        for(pluszKoltseg plus : pluszMinusz){
            if(!ho.equals(plus.getHo())){
                c = -1; 
            }
        }
        for(biztosKoltseg biztos : biztosMinusz){
            if(!ho.equals(biztos.getHo())){
                cc = -1;
            }
        }
        if(c == -1 && cc == -1){
            System.out.println("\r\nNincs ilyen számú hónap.\r\n");
        }
        if(pluszMinusz.isEmpty() && biztosMinusz.isEmpty()){
            System.out.println("Nincsenek költsége ebben a hónapban.\r\n");
        }
        else{
            pluszKoltsegKiir(pluszMinusz, ho);
            biztosKoltsegKiir(biztosMinusz, ho);
        }
    }
    
    public static Integer pluszKoltsegOsszead(ArrayList<pluszKoltseg> pluszMinusz, Integer ho){
        
        ossz = 0;
        
        for(pluszKoltseg plus : pluszMinusz){
            if(Objects.equals(ho, fio.beolvasas(osszKoltseg.honap))){
                ossz += plus.getPlusz();
                return ossz;
            }      
        }
            return -1;
        
    }
    
    public static Integer biztosKoltsegOsszead(ArrayList<biztosKoltseg> biztosMinusz, Integer ho){
        
        ossz1 = 0;
        
        for(biztosKoltseg biztos : biztosMinusz){
            if(Objects.equals(ho, fio.beolvasas(osszKoltseg.honap))){
                ossz1 += biztos.getHaviKiadas().getAr();
                return ossz1;
            }
        }    
        return -1; 
        
    }
    
    public static Integer osszKoltsegOsszead(ArrayList<pluszKoltseg> pluszMinusz, ArrayList<biztosKoltseg> biztosMinusz, Integer ho){
        
        ossz2 = 0;
        
        if(Objects.equals(ho, fio.beolvasas(osszKoltseg.honap))){
            ossz2 = pluszKoltsegOsszead(pluszMinusz, ho) + biztosKoltsegOsszead(biztosMinusz, ho);
        return ossz2;
        }
        else{
            return -1;
        }
    }
}
