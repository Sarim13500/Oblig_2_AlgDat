# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. Medlemmer i gruppen:

Sarim Asher Saeed: s362123

Saqlain Rehman: s364567

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Sarim har hatt hovedansvar for oppgave 6 og 7. 
* Saqlain har hatt hovedansvar for oppgave 3, 4, og 8.
* Vi har i fellesskap løst oppgave 1, 2 og 5. 

# Oppgavebeskrivelse

Oppgave 1:
    Antall metoden, tom metoden og standardkonstruktøren gikk ganske fort. Den generiske metoden ble løst ved hjelp
    av to while løkker der første løkke lager en Noden hode på den indeksen i arrayet der verdien ikke er null.
    Den andre while løkken lager de neste nodene ved hjelp av hale pekereren. Her passes det på at forrige og neste
    pekerne blir satt riktig. I den andre løkken passes det også på at verdien ikke null før noden lages.

Oppgave 2:
    Kommentarene i koden viser tankegangen vår når vi løste toString og omvendtSring metoden. toString metodene 
    ble løst ved hjelp av if setninger som sjekket om listen var tom og en while løkke som skulle forsette å legge til
    verdien i en Stringbuilder så lenge verdien lista ikke hadde blitt skrevet gjennom.

b)
    Legg Inn metoden kom fra programkode 3.3.2 f)

Oppgave 3a:
    finnNode metoden ble løst ved å se på videoer lagt ut på Canvas. Oppgaven ble løst ved hjelp av if-setninger
    som sjekket om indeksen var større eller mindre enn antall/2. Deretter gikk indeksen gjennom en av to for løkker
    som henter ut noden basert på indeksen.

b)
    subListe metoden blir løst ved hjelp av en for løkke som legger til verdiene mellom intervallet [fra:til>. Før 
    for løkken kjøres blir det satt opp en ny DobbeltLenketListe kalt subliste og flere tester blir kjørt.

Oppgave 4:
    indeksTil metoden blir løst av en for løkke som kjører gjennom den lenkede lista helt til den finner verdien som
    blir satt inn som parameter. Dersom for løkken finn verdien returneres indeksen og hvis ikke returneres -1.
    boolean inneholder sjekker bare resultatet fra indeksTil metoden og returnerer true eller false basert på det.

Oppgave 5:
    Metoden er basert på programkode 3.3.2 g). Oppgaven løses for 3 situasjoner, om det legges til foran, i mellom eller
    helt til slutt. Oppgaven løses hovedsaklig av if setninger som sjekker hvor verdien skal legges til. Når indeksen
    går gjennom passende if setning lages det en ny Node på det punktet samtidig som pekerene blir tatt hånd om.
    Dette gjelder pekerne på både den nye noden og nodene som er før og etter den nye noden.

Oppgave 6:
    fjern metodene løses veldig likt som oppgave 5. Her er det flere if setninger som sjekker om man vil fjerne første
    node, en node imellom, eller siste node. Når indeks går gjennom passende if setning bruker vi finnNode metoden
    for å finne noden på indeksen. Deretter nulles ut pekerne til nodene rundt noden. Garbage collector fikser resten.
    Boolean metoden av fjern fungerer på veldig like måte bare at her returneres true eller false basert på de ulike
    situasjonene.

Oppgave 7:
    Her starter vi på starten av lista med en node current satt til hode. Deretter brukes en while løkke til å kjøre 
    gjennom lista og nulle ut alle verdiene og pekerne samtidig. Til slutt sette hode og hale lik null og  antall blir
    satt til 0;

Oppgave 8a:
    Løses ved sette nye verdier på det oppgaven ber om og tilslutt returnere denne.verdi.

b)
    Returnerer en ny instans av iteratorklassen.

c)
    Satte bare 'denne' til å være lik finnNode(indeks)

d)
    Sjekker om indeksen er lovlig. Til slutt returneres en ny instans av iteratorklassen med indeks som parameter. 

    
    


