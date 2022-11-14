-----IMPORTANT LLEGIR AQUEST DOCUMENT ABANS D'EXECUTAR EL PROGRAMA!!!-----

---COM COMPILAR I EXECUTAR---

Trobaras dins de la carpeta FONTS d'aquest directori un archiu anomenat make.bat
A la terminal s'ha de executar, dins del directori FONTS ./make.bat i aixo generarà tots els .class per les classes del programa,
els drivers i els test amb JUnit.

Els fichers creats pel make es guardaran automaticament a la carpeta EXE d'aquest directori.

EXE conte la carpeta CLASS, on els .class aniran a parar.

No hi ha un driver per classe ja que el tutor ens va comentar que un sol driver general que llegis comandes seria correcte. Aquest driver
es pot provar executant ExeDriver.bat, situat a EXE.

Per exectuar el fitxer .bat nomes cal que et coloquis desde la terminal a la carpeta on es troba el fitxer i entris:
./NomFitxer.bat.

Quan executis al ExeDriver.bat et sortira un menú que al següent apartat d'aquest document explicarem com funciona. Però en la carpeta de EXE,
trobaras un document anomenat JocDeProvaAltesDocumentsDriver.txt que si copies el contingut amb ctrl^C i l'enganxes a la terminal amb ctrl^V o boto dret, i per ultim pitjes Enter,
el sistema donarà d'alta una serie de documents que amb ells podràs fer totes les consultes i jocs de prova que vulguis.


---COM USAR EL PROGRAMA---

Només exectuar el programa es mostra per pantalla el menú amb les opcions que l'usuari té disponible.

El menú és el següent:

-- MENU --
1. Alta Document
2. Baixa Document
3. Modificar Document
4. Consulta títols autor
5. Consulta autors prefix
6. Consulta contingut
7. Llistar documents semblants
8. Llistar documents que compleixen una expressió
9. Alta Expressio Booleana
10. Baixa Expressio Booleana
11. Modificar Expressio Booleana
12. Sortir


Per a escollir una de les funcionalitats només cal entrar un dels numeros a la terminal.
Des d'aquest punt s'anirà guiant l'ús del sistema amb missatges per terminal, i en acabar una funcionalitat es podra tornar a visualitzar el menu i tornar a escollir un altre opció.
És important que, si el sistema et demana un enter per terminal, el que s'introdueixi sigui realment un enter dins el rang que et demanem (1-12).
En qualsevol moment es pot para l'execució total del programa prement ctrl+c.

Quan la terminal et demani les dades necessaries no es poden introduir salts de linia ja que es detecten com a fi d'entrada de dades.

---EXPLICACIÓ DE LES OPCIONS DEL MENU---

1. Alta Document:
    Demana un nom autor, titol
2. Baixa Document
3. Modificar Document
4. Consulta títols autor
5. Consulta autors prefix
6. Consulta contingut
7. Llistar documents semblants
8. Llistar documents que compleixen una expressió
9. Alta Expressio Booleana
10. Baixa Expressio Booleana
11. Modificar Expressio Booleana
12. Sortir





1. Alta
    Aquesta opcio permet demanar una recomanacio per a un usuari concret indicant el seu identificador (que es el nom d'usuari) i el nombre d'items que es vol a la
    sortida. Cal indicar aquests parametres en linies diferents, cal que l'usuari existeixi i que el nombre d'items que es vol a la sortida sigui menor o igual al nombre
    d'items que consta en el sistema.
    La recomanacio es dura a terme amb les estrategies configurades en aquell moment i amb els parametres k establerts en aquell moment.
    La recomanacio estudiara tots els items del sistema.
    Un exemple d'entrada es:
    143
    5

2. Demanar recomanacio per un usuari i per uns items donats.
    Aquesta opcio permet demanar una recomanacio per a un usuari concret i tinguent com a items d'estudi un conjunt concret.
    Cal indicar l'identificador del usuari (que es el nom d'usuari) i el nombre d'items que es vol a la sortida, el nombre d'items que s'entrara i finalment els ids
    d'aquests items.
    Cal indicar tots aquests parametres en linies diferents, cal que l'usuari existeixi, que el nombre d'items que es vol a la sortida sigui menor o igual al nombre
    d'items que s'entra, i a la vegada que aquest nombre d'items entrats sigui menor o igual al total d'items, i finalment que aquests items entrats existeixin.
    La recomanacio es dura a terme amb les estrategies configurades en aquell moment i amb els parametres k establerts en aquell moment.
    La recomanacio estudiara nomes els items entrats.
    Un exemple d'entrada es:
    143
    3
    5
    296
    480
    111
    185
    292

3. Comprovar la qualitat de una o mes recomanacions
    Aquesta opcio permet provar la qualitat de les recomanacions i per tant provar el funcionament dels algorismes, usant com entrada un fitxer al que hem anomenat
    inputqueries.txt. La terminal especifica quins requisits ha de tenir aquest fitxer i demana que s'entri el nom del fitxer amb l'extensio inclosa per poder llegir-lo.
    Basicament el que es fa es demanar n recomanacions per n usuaris diferents del estil recomanacions de l'opcio 2 del menu. Per aquestes recomanacions es calcula
    un DCG promig, que indica lo be que s'ha ordenat en mitjana els items a predir.
    Aquesta funcionalitat necessita les dades de known i les de unknown, de manera que els usuaris entrats per demanar les recomanacions pertanyen a aquests dos fitxers,
    i la resta d'informacio a entrar es coherent tambe amb aquests fitxers.
    El fitxer ha de seguir el format:
    nusuaris a demanar recomanacions
    (per cada usuari de nusuaris:)
    idUsuari numValoracionsConegudes numValoracionsDesconegudes numItemsARetornar
    tantes linies amb parelles <idItem puntuacio> com numValoracionsConegudes
    tantes linies amb iditem com numValoracionsDesconegudes

    Pots trobar un exemple del fitxer inputqueries al directori DATA.

4. Consultar i/o canviar l'estrategia de recomanacio
    Aquesta opcio permet consultar quina estrategia de filtering hi ha configurada en aquell moment, i si es vol, permet canviar l'estrategia.

5. Consultar i/o canviar l'estrategia del calcul de distancies
    Aquesta opcio permet consultar quina estrategia de calcul de distancies entre usuaris hi ha configurada en aquell moment, i si es vol, permet canviar l'estrategia.


6. Consultar i/o canviar els parametres k dels algorismes
    Aquesta opcio permet consultar quins parametres k hi ha configurats en aquell moment per als alogrismes, i si es vol, permet canviar aquests valors.

7. Recalcular clusters k-means
    Aquesta opcio recalcula els clusters de k-means, usant el valor k configuart en aquell moment. El calcul de les particions no es fa per cada cop que es demana
    una recomanacio ja que es bastant costos. Es per aixo que sempre que es vulgui canviar els clusters i recalcularlos es pot escollir aquesta opcio. Els clusters
    es calculen per primer cop al inici d'execucio del programa.

8. Sortir
    Permet abandonar el sistema recomanador i acabar l'execucio.