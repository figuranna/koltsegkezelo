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
A felhasználói interface működését biztosítja.

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

- *fizetesBeker*: Itt adhatjuk meg és módosíthatjuk a fizetésünket.
- *egyenlegMegnez*: Megmutatja az aktuális egyenlegünket.
- *holepes*: a következő hónapba léptet
- *pluszKoltsegHozzaad, biztosKoltsegHozzad*: Új költséget ad az aktuális hónaphoz vagy hónaptól.
- *pluszKoltsegKiir, biztosKoltsegKiir, osszKoltsegKiir*: tételesen kiírja a megadott havi költségeket.
- *pluszKoltsegOsszead, biztosKoltsegOsszead, osszKoltsegOsszead*: A megadott havi költségek összegét számolja ki.
