# Költségkezelő program
A program célja, hogy bekérje és tárolja a felhasználó által megadott fizetését és költségeit.
## Package-ek
### Fio
Ez tartalmazza az xml fájlokhoz tartozó író és olvasó metódusokat.

**Osztálya(i)**: 
- Fio
### Business.os
A business package ős package-e.

**Osztálya(i)**: 
- osszKoltseg
### Business
A program logikai alapját adja meg, a *fio*-n keresztül beolvasott adatokat dolgozza fel és módosítja.

**Osztálya(i)**: 
- BiztosEnum
- GetterFunctionName
- Metodusok
- biztosKoltseg
- pluszKoltseg
### Controller
A User Interface működését biztosítja.

**Osztálya(i)**: 
- Controller

## Osztályok
### osszKoltseg (*ősosztály*), biztosKoltseg, pluszKoltseg
**Feladatuk**:
- Megadják a program alapvető struktúráját 
**Osztályváltozók**:
- osszKoltseg: költség neve [string], hónap sorszáma [int]
- biztosKoltseg: (*költség neve [string], hónap sorszáma [int]*), költség típusa [BiztosEnum]
- pluszKoltseg: (*költség neve [string], hónap sorszáma [int]*), költség mennyisége [int]

**Metódusok**:
- Konstruktorok
- Getter metódusok
- Setter metódus
- mentes
- toString

### BiztosEnum
A rendszeres havi kiadásokat tárolja azok költségével együtt. (Előfizetések->11.000Ft, Utazás->8000Ft, Rezsi->20.000Ft)

### GetterFunctionName
Az XML-be való mentést segíti.

### Metodusok
A logikai műveleteket végzi el a programban.

- ***fizetesBeker***: Itt adhatjuk meg és módosíthatjuk a fizetésünket.
- ***egyenlegMegnez***: Megmutatja az aktuális egyenlegünket.
- ***holepes***: a következő hónapba léptet
- ***pluszKoltsegHozzaad, biztosKoltsegHozzad***: Új költséget ad az aktuális hónaphoz vagy hónaptól.
- ***pluszKoltsegKiir, biztosKoltsegKiir, osszKoltsegKiir***: tételesen kiírja a megadott havi költségeket.
- ***pluszKoltsegOsszead, biztosKoltsegOsszead, osszKoltsegOsszead***: A megadott havi költségek összegét számolja ki.
### Controller
A felhasználóval való kapcsolattartás és a User Interface működését biztosítja.

**Metódusok**:
- ***start***: Üdvözli a felhasználót, majd fizetés olvassa be és módosítja, ha a felhasználó azt úgy kívánja.
- ***mainMenu***: Kilistázza az összes menüpontot a felhasználó számára és be is kéri a választását.
- ***listazas***: Kilistázza az eddig bevitt költségeket a rendszerbe.
- ***osszeg***: Menüpontokat listáz ki, mellyel megadhatjuk a kiszámítandó összeg típusát.
- ***biztoskoltsegKiir, pluszkoltsegKiir, osszkoltsegKiir***: Kiírja az adott hónapban felvitt költségeket
- ***biztosOsszegSzamito, pluszOsszegSzamito, osszOsszegSzamito***: Kiszámítja az adott hónapban felvitt költségeket

### Fio
Beolvasás és mentés műveletek végrehajtása.

**Metódusok**:
- ***beolvasbiztosKoltseg, beolvaspluszKoltseg***: Beolvassa a biztosköltségek és a pluszkoltsegek xml fájlokba mentett adatokat.
- ***mentes***: Menti az adatokat a biztosköltségek és a pluszkoltsegek xml fájlokba.
- ***beolvas***: Beolvassa az 1 adatot tartalmazó xml fájlokat (*honap,fizetes,egyenleg*)
- ***ementes***: Menti az 1 adatot tartalmazó xml fájlokat.

### Koltseg
Összefogja a programot, a *start()* meghívásával innen indul a program.

## XMl fájlok
A programhoz szükséges adatokat itt tárolja.
### biztoskoltsegek.xml
- ***koltsegek***: A fájl gyökere
  - ***haviKiadas***: A havi kiadás típusát tartalmazza.
  - ***nev***: A havi kiadás elnevezését tartalmazza.
  - ***ho***: A havi kiadás első hónapját tartalmazza.
### pluszkoltsegek.xml
- ***koltsegek***: A fájl gyökere
  - ***plusz***: A plusz kiadás összegét tartalmazza.
  - ***nev***: A plusz kiadás elnevezését tartalmazza.
  - ***ho***: A plusz kiadás hónapját tartalmazza.
### egyenleg.xml
- ***egyenleg***: A fájl gyökere és az egyetlen eleme. Az egyenleget tárolja.
### fizetes.xml
- ***fizetes***: A fájl gyökere és az egyetlen eleme. A legutóbb megadott fizetést tárolja.
### honap.xml
- ***honap***: A fájl gyökere és az egyetlen eleme. Az aktuális hónap sorszámát tárolja
