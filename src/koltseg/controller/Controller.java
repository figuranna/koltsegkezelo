package koltseg.controller;

import java.util.ArrayList;
import java.util.Scanner;
import koltseg.business.Metodusok;
import static koltseg.business.Metodusok.biztosKoltsegHozzaad;
import koltseg.business.biztosKoltseg;
import koltseg.business.pluszKoltseg;
import koltseg.fio.fio;

public class Controller {
    private static final Scanner scn = new Scanner(System.in);
    private static final ArrayList<pluszKoltseg> plusKoltseg = fio.beolvaspluszKoltseg();
    private static final ArrayList<biztosKoltseg> biztosKoltseg = fio.beolvasbiztosKoltseg();

    public static void start(){
        System.out.println("Üdvözöllek a költségkezelő programban!\n\n");
        Metodusok.fizetesBeker();
        mainMenu();
    }
    public static void mainMenu(){
            int tmp;
            
            try{
                System.out.println("1 -> Egyenleg lekérése"+"\r\n2 -> Havi kiadás hozzáadása" + "\r\n3 -> Plusz kiadás hozzáadása" + "\r\n4 -> Hónap lépése" + "\r\n5 -> Kiadások listázása" + "\r\n6 -> Kiadások összege" + "\r\n7 -> Program leállítása\n" );
                tmp = scn.nextInt();
                switch(tmp){
                    case 1:Metodusok.egyenlegMegnez();mainMenu();break;
                    case 2:Metodusok.biztosKoltsegHozzaad(biztosKoltseg);mainMenu();break;
                    case 3:Metodusok.pluszKoltsegHozzaad(plusKoltseg);mainMenu();break;
                    case 4:Metodusok.hoLepes(biztosKoltseg);mainMenu();break;
                    case 5:listazas();mainMenu();break;
                    case 6:osszeg();mainMenu();break;
                    case 7:System.out.println("Program leállítása...");break;
                    default: System.out.println("A program nem tudja értelmezni a kérését.\n");mainMenu();break;
                }
            }
            catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                mainMenu();
            }
    }
    private static void listazas(){
        int tmp;
        
        try{
        System.out.println("1 -> Havi költség listázása" + "\r\n2 -> Plusz költség listázása" + "\r\n3 -> Összes költség listázása\n");
        tmp = scn.nextInt();
        switch(tmp){
            case 1:
                biztoskoltsegKiir(biztosKoltseg);
                mainMenu();
                break;
            case 2:
                pluszkoltsegKiir(plusKoltseg);
                mainMenu();
                break;
            case 3:
                osszkoltsegKiir(plusKoltseg, biztosKoltseg);
                mainMenu();
                break;
            default: 
                System.out.println("A program nem tudja értelmezni a kérését.\n");
                mainMenu();
                break;
            }
        }
         catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                listazas();
            }
    }
    private static void osszeg(){
        int tmp;
        
        try{
        System.out.println("1 -> Havi költségek összege" + "\r\n2 -> Plusz költségek összege" + "\r\n3 -> Összes költség összege\n");
        tmp = scn.nextInt();
        switch(tmp){
            case 1:
                biztosOsszegSzamito(biztosKoltseg);
                mainMenu();
                break;
            case 2:
                pluszOsszegSzamito(plusKoltseg);
                mainMenu();
                break;
            case 3:
                osszOsszegSzamito(plusKoltseg, biztosKoltseg);
                mainMenu();
                break;
            default: System.out.println("A program nem tudja értelmezni a kérését.\n");mainMenu();break;
            }
        }
        catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                osszeg();
            }
    }
    
    private static void biztoskoltsegKiir(ArrayList lista){
        int ho;

        try{
            System.out.println("\r\nKérem adja meg a vizsgálni kívánt hónap sorszámát: ");
            ho = scn.nextInt();
        
            Metodusok.biztosKoltsegKiir(lista, ho);
        }
        catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                biztoskoltsegKiir(lista);
            }
    }
    
    private static void pluszkoltsegKiir(ArrayList lista){
        int ho;

        try{
            System.out.println("\r\nKérem adja meg a vizsgálni kívánt hónap sorszámát: ");
            ho = scn.nextInt();

            Metodusok.pluszKoltsegKiir(lista, ho);
        }
        catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                pluszkoltsegKiir(lista);
            }
        
    }
    
    private static void osszkoltsegKiir(ArrayList lista, ArrayList listaa){
        int ho;
        
        try{
            System.out.println("\r\nKérem adja meg a vizsgálni kívánt hónap sorszámát: ");
            ho = scn.nextInt();

            Metodusok.osszKoltsegKiir(lista ,listaa ,ho);
        }
        catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                osszkoltsegKiir(lista, listaa);
            }

    }
    
    private static void biztosOsszegSzamito(ArrayList lista){
        int ho;

        try{
            System.out.println("\r\nKérem adja meg a vizsgálni kívánt hónap sorszámát: ");
            ho = scn.nextInt();

            if(Metodusok.biztosKoltsegOsszead(lista, ho).equals(-1)){
                System.out.println("\r\nNincs ilyen számú hónap.");
            }
            System.out.println("\nHavi költségek összege " + ho + ". hónapban: " + Metodusok.biztosKoltsegOsszead(lista, ho) + "\r\n");
        }
         catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                biztosOsszegSzamito(lista);
            }

    }
    
    private static void pluszOsszegSzamito(ArrayList lista){
        int ho;

        try{
            System.out.println("\r\nKérem adja meg a vizsgálni kívánt hónap sorszámát: ");
            ho = scn.nextInt();

            if(Metodusok.pluszKoltsegOsszead(lista, ho).equals(-1)){
                System.out.println("\r\nNincs ilyen számú hónap.");
            }
            System.out.println("\nPlusz költségek összege " + ho + ". hónapban: " + Metodusok.pluszKoltsegOsszead(lista, ho) + "\r\n");
        }
        catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                pluszOsszegSzamito(lista);
            }

    }
    
    private static void osszOsszegSzamito(ArrayList lista, ArrayList listaa){
        int ho;

        try{
            System.out.println("\r\nKérem adja meg a vizsgálni kívánt hónap sorszámát: ");
            ho = scn.nextInt();

            if(Metodusok.osszKoltsegOsszead(lista ,listaa ,ho).equals(-1)){
                System.out.println("\r\nNincs ilyen számú hónap.");
            }
            System.out.println("\nÖsszes költség összege " + ho + ". hónapban: " + Metodusok.osszKoltsegOsszead(lista, listaa, ho) + "\r\n");
        }
        catch(Exception e){
                System.out.println("Nem számkaraktert adott meg!");
                scn.next();
                osszOsszegSzamito(lista, listaa);
            }
    }
}
