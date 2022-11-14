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
És important que, si el sistema et demana un enter per terminal, el que s'introdueixi sigui realment un enter dins el rang que et demanem (1-12), no pot ser buit tampoc.
En qualsevol moment es pot para l'execució total del programa prement ctrl+c.

Quan la terminal et demani les dades necessaries no es poden introduir salts de linia ja que es detecten com a fi d'entrada de dades.

---EXPLICACIÓ DE LES OPCIONS DEL MENU---

1. Alta Document:
    Demana un nom autor, titol i el contingut per tal de donar d'alta un nou document del sistema.
    Si l'autor o titol és null , o bé el document ja està creat s'infoma de l'error.
    Un exemple d'entrada es:
        a1
        t1
        hola, que tal?

2. Baixa Document
    Demana un nom autor i el titol per tal de donar de baixa un document del sistema.
    Si l'autor o titol és null , o bé el document no està al sistema s'infoma de l'error.
    Un exemple d'entrada es:
        a1
        t1

3. Modificar Document
    Demana un nom autor, titol i el contingut per tal de modificar el contingut d'un document del sistema.
    Si l'autor o titol és null , o bé el document no està al sistema s'infoma de l'error.
    Un exemple d'entrada es:
        a1
        t1
        hola, com estas?

4. Consulta títols autor
    Demana un nom autor per tal de retornar tots els titols dels documents de l'autor del sistema.
    Si l'autor és null , o bé l'autor no està al sistema s'infoma de l'error.
    Un exemple d'entrada es:
        a1

5. Consulta autors prefix
    Demana un prefix per tal de retornar el nom dels autors amb el prefix indicat del sistema.
    Un exemple d'entrada es:
        pre

6. Consulta contingut
    Demana un nom autor, titol per tal de que retorni el contingut del document del sistema.
    Si l'autor o titol és null , o bé el document no està al sistema s'infoma de l'error.
    Un exemple d'entrada es:
        a1
        t1

7. Llistar documents semblants
    Demana un nom autor, titol i el nombre de documents semblants que volem (k).
    Seguidament, et demana quin metode de cerca vols utilitzar per buscar els documents del sistema. (0 --> Tf-Idf) (1 --> Tf)
    Per últim, demana com vols que el sistema t'ordeni la sortida de documetnts. (0 --> ordre alfabetic per titol) (1 --> ordre per data de creació més propera) (2 --> ordre per data de modificació més propera)
    Si l'autor o titol és null , o bé el document no està al sistema s'infoma de l'error.
    Si k > el nombre de documents en el sistema s'infoma de l'error.
    Si els valors introduits per escollir metode de cerca o d'ordenació no son els indicats s'infoma de l'error.
    Un exemple d'entrada es:
        a1
        t1
        2
        0
        1

8. Llistar documents que compleixen una expressió
    Demana una query booleana i retorna els documents que contenen almenys una frase que compleix l'expresió booleana
    També, demana com vols que el sistema t'ordeni la sortida de documetnts. (0 --> ordre alfabetic per titol) (1 --> ordre per data de creació més propera) (2 --> ordre per data de modificació més propera)
    Suposem que la query introduida segueix el format de l'exemple, nomes comprovem que no sigui buida, si és així s'infoma de l'error.
    Si els valors introduits per escollir metode d'ordenació no son els indicats s'infoma de l'error.
    També si la query introduida no existia al sistema la dona d'alta.
    Un exemple d'entrada es:
        "20 anys" & 2002 & !octubre
        0

9. Alta Expressio Booleana
     Demana una query booleana per tal de donar-la d'alta al sistema
     Si la query és buida o ja existeix al sistema s'informa de l'error.
     Suposem que la query introduida segueix el format de l'exemple.
     Un exemple d'entrada es:
        "20 anys" & 2002 & !octubre

10. Baixa Expressio Booleana
     Demana una query booleana per tal de donar-la de baixa al sistema
     Si la query és buida o no existeix al sistema s'informa de l'error.
     Suposem que la query introduida segueix el format de l'exemple.
     Un exemple d'entrada es:
        "20 anys" & 2002 & !octubre

11. Modificar Expressio Booleana
     Demana dos querys booleanes per tal demodificar la primera per la segona query al sistema
     Si la query és buida o la primera no existeix  o la segona ja existeix al sistema s'informa de l'error.
     Suposem que la query introduida segueix el format de l'exemple.
     Un exemple d'entrada es:
        "20 anys" & 2002 & !octubre
        "20 anys" & 2002 & octubre

12. Sortir
    Permet abandonar el sistema gestor i acabar l'execució.


---DRIVERS I PROVES---

Hem fet un test unitari JUNIT per cada classe i un driver per el ctrlDomini i poder executar i comprovar totes les funcionalitats ofertes.


---ANOTACIONS I DESCISONS PRESES---

Les paraules amb caractes especials els converteix en lletres simples: ç --> c , ñ --> n

No es poden introduir l·l. Si alguna paraula requereix de l·l escriviu-la com a ll.

Els accents els suprimeix i ens deixa la vocal sense accent, però es poden introduir paraules amb accent que el sistema les reconeix perfectament.

Alguns casos d’ús no els hem pogut codificar, ja que necessitàvem la capa de presentació o perquè encara no eren per a aquesta entrega, però hem vist necessaris incloure’ls en el diagrama de casos d'ús.

Els casos d’ús d’ExportarDocuments, ImportarDocuments i LlistaRellevants encara no estan implementats per aquesta entrega, els codificarem per a la següent entrega.

Els casos d’ús de SeleccionarDocuments, LlistarDocuments, ConsultaDocuments i CercaIndividual els codificarem quan tinguem accés a la capa de presentació, és a dir per a la següent entrega.

En quant als algorismes, s'ha seguit el enunciat publicat a la pagina de l'assignatura i si hi havia coses que el enunciat no especificava com s'havia de fer o quin
criteri s'havia de utilitzar hem acabat usant criteri propi i sentit comu.

