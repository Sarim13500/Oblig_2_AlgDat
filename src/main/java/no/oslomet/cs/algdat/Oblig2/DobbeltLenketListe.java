package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    private Node<T> finnNode(int indeks){
        Node<T> current;
        if(indeks < antall/2){
            current = hode;
            for (int i = 0; i < indeks; i++){
                current = current.neste;
            }
            return current;
        }

        current = hale;
        for (int i = antall - 1; i > indeks; i--){
            current = current.forrige;
        }
        return current;
    }

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
        //throw new UnsupportedOperationException();
    }

    private void fraTilKontroll(int tabellengde, int fra, int til){

        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tabellengde)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + tabellengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");

    }


    public DobbeltLenketListe(T[] a) {

        if (a == null){ //Kastes unntak dersom a er null

            throw new NullPointerException("Tabellen a er null!");
        }

        if (a.length > 0){  // utfører koden dersom lengden til a er større enn 0

            int teller = 0;

            while (teller < a.length){ // I denne løkken settes opp hode-verdien

                if (a[teller] != null){ //Vil bare lage en ny node dersom verdien i arrayet ikke er null
                    hode = new Node<>(a[teller]); //
                    antall++;
                    break;
                }
                teller++;
            }

            hale = hode; // Setter begge pekerne på første node


            if (hale != null){
                teller++;


                while (teller < a.length){ // I denne løkken lages resten av nodene ved hjelp av hale pekeren

                    if (a[teller] != null){

                        hale = hale.neste = new Node<>(a[teller],hale,null); //Passer på at siste noden er hale
                        antall++;

                    }
                    teller++;

                }


            }

        }

        //throw new UnsupportedOperationException();
    } // Generisk Metode


    public Liste<T> subliste(int fra, int til) {

        fraTilKontroll(antall,fra,til); //Sjekker om parameterne er riktig

        Liste<T> subliste = new DobbeltLenketListe<>(); // Setter opp ny liste som skal være sublisten
        int tabellengde = til-fra; //

        if (tabellengde < 1) return subliste; //Dersom intervallet er tomt sendes en tom liste tilbake

        Node<T> current = finnNode(fra); //Starter å lage sublisten fra "fra-variablen"

        for (int i = fra; i < til; i++){ // legger til verdiene i sublisten
            subliste.leggInn(current.verdi);
            current = current.neste;

        }

        return subliste;


        //throw new UnsupportedOperationException();
    }



    @Override
    public int antall() {
        return antall;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {
        return antall == 0; //return antall > 0 ? false:true
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        if (antall == 0)  hode = hale = new Node<>(verdi);  // tom liste
        else hale = hale.neste = new Node<>(verdi, hale, null);

        antall++;// en mer i listen
        endringer++;
        return true;               

        //throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);  // Se Liste, true: indeks = antall er lovlig

        if (indeks == 0)                     // ny verdi skal ligge først
        {
            if(tom()) {
                hode = new Node<T>(verdi, null,null);
                hale = hode;      // hode og hale går til samme node
            }
            else {
                hode = hode.forrige = new Node<T>(verdi, null, hode);// legges først
            }
        }
        else if (indeks == antall)           // ny verdi skal ligge bakerst
        {
            hale = hale.neste = new Node<T>(verdi, hale, null);
        }


        else
        {
            Node<T> p = finnNode(indeks-1);// p flyttes indeks - 1 ganger
            Node<T> q = new Node<T>(verdi,p,p.neste); //Oppdaterer pekere til q
            p.neste.forrige = q;
            p.neste = q;

        }

        antall++;// listen har fått en ny verdi
        endringer++;

        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {

        if (indeksTil(verdi) == -1) { //Hvis indeksTil metoden returnerer -1 vil det si at verdien ikke er i lista
            return false;
        }

        return true;

        //throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {

        indeksKontroll(indeks,false);
        if (finnNode(indeks) != null){
            return finnNode(indeks).verdi;
        }
        return null;

        //throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {

        if (verdi == null) return -1; // Returnerer -1 dersom verdien er null

        Node<T> indeks = hode;

        for (int i = 0; i < antall; i++){

            if (indeks.verdi.equals(verdi)){ //Søker gjennom den lenkete lista
                return i;                    //returnerer indeken i dersom verdiene er lik hver andre
            }
            indeks = indeks.neste;
        }

        return -1;

        //throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {

        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = nyverdi;
        endringer++;
        return gammelVerdi;

        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        //throw new UnsupportedOperationException();

        if (verdi == null) return false;

        Node<T> current = hode;
        if (verdi.equals(current.verdi)){ // Situasjon 1: Første verdi i lista fjernes
            if (current.neste == null){  //  Dersom det ikke er flere verdier/noder i lista settes hode og hale peker
                hode = null;              // til null
                hale = null;
            }
            else {
                hode = current.neste; //Hvis det er flere verdier i lista fjernes første verdien og den neste verdien
                hode.forrige = null;  // settes som hode
            }

            endringer++;
            antall--;
            return true;
        }

        current = hode.neste;
        while (current != null && current.neste != null){ // Situasjon 2: Mellomste fjernes
            if (verdi.equals(current.verdi)){
                current.forrige.neste = current.neste; //Fjerner forrige og neste peker til noden som skal fjernes
                current.neste.forrige = current.forrige;//garbageCollector fikser resten
                endringer++;
                antall--;
                return true;
            }
            current = current.neste;
        }

        current = hale;
        if (verdi.equals(current.verdi)) { //Situasjon 3: Siste verdien fjernes
            hale = current.forrige;
            hale.neste = null;
            antall--;
            endringer++;
            return true;
        }
        return false;
    }

    @Override
    public T fjern(int indeks) {

        //Objects.requireNonNull(indeks, "Ikke tillatt med null-verdier!");

        if (tom()){                                            //Hvis listen er tom vil ikke metoden gjøre noe
            //System.out.println("Listen er tom");
            throw new IndexOutOfBoundsException("Listen er tom");
            //throw new IndexOutOfBoundsException();

        }

        if (indeks>antall-1 || indeks<0){

            // System.out.println("Ikke lovlig indeks valg");                   //Sjekker om indeksen gjelder for listen
            indeksKontroll(indeks, false);
        }
        else {
            if (indeks == 0) {                                  //Hvis hode fjernes

                T verdi = finnNode(indeks).verdi;
                Node<T> fjerne = finnNode(indeks);

                Node<T> temp = finnNode(indeks + 1);
                temp.forrige = null;                           //Oppdaterer pekere
                hode = temp;                                   //Oppdaterer hode
                fjerne.neste = null;

                antall--;                              // reduserer antallet
                endringer++;
                return verdi;                         // returner fjernet verdi

            }
            else if (indeks == antall - 1) {              //Fjerner hale

                T verdi = finnNode(indeks).verdi;
                Node<T> fjerne = finnNode(indeks);


                Node<T> temp = finnNode(indeks - 1);
                temp.neste = null;                           //Oppdaterer pekere
                hale = temp;                                 //Oppdaterer hale
                fjerne.forrige = null;

                antall--;                             // reduserer antallet
                endringer++;
                return verdi;                         // returner fjernet verdi

            }
            else {
                T verdi = finnNode(indeks).verdi;              //Fjerner en verdi i midten
                Node<T> fjerne = finnNode(indeks);


                Node<T> temp = finnNode(indeks - 1);
                Node<T> temp2 = finnNode(indeks + 1);
                temp.neste = temp2;
                temp2.forrige = temp;                             //Oppdaterer pekere
                fjerne.neste = fjerne.forrige = null;


                antall--;                   // reduserer antallet
                endringer++;
                return verdi;                         // returner fjernet verdi

            }

        }
        return null;  //Må returnere noe annet, dette er bare for å komme forbi feil
        // throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {

        Node<T> current = hode;

        while (current != null){ // Vi vil fortsette å nulle ut nodene så lenge current ikke er null

            current.verdi = null;
            current.forrige = null;
            current.neste = null;
            current = current.neste;

        }

        hode = null;
        hale = null; // Til slutt nulles ut hode og hale-peker og antall
        antall = 0;
        endringer++;

        /* Dette er den andre metoden basert på fjern metoden som vi ikke valgte å bruke. Tar lenger tid
        for (Node<T> current = hode; current != null; current=current.neste){
            fjern(0);
        }
         */

        //throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder(); // String ut = "";

        s.append('['); // ut += [;

        if (!tom() && hode != null)
        {

            s.append(hode.verdi); // ut += hode.verdi
            Node<T> current = hode.neste;

            while (current != null)  // tar med resten hvis det er noe mer
            {
                s.append(',').append(' ').append(current.verdi); // EKS: ut += ", " + current.verdi
                current = current.neste; // i++
            }
        }

        s.append(']'); // ut += ]

        return s.toString();

        //throw new UnsupportedOperationException();
    }

    public String omvendtString() {

        StringBuilder s = new StringBuilder(); // String ut = "";

        s.append('['); // ut += "["

        if (!tom() && hale != null) // sjekker om lista ikke er tom og om hale har en verdi
        {
            Node<T> current = hale;
            s.append(current.verdi);

            while (current.forrige != null)  // tar med resten hvis det er noe mer
            {
                current = current.forrige;
                s.append(',').append(' ').append(current.verdi); // ut += ", " + current.verdi

            }
        }

        s.append(']'); // ut += "]"

        return s.toString();

        //throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
        //throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
        //throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;
            fjernOK = false;
            iteratorendringer = endringer;
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne=finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
            //throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {

            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");
            if (iteratorendringer != endringer) throw new ConcurrentModificationException();

            fjernOK = true;
            T tempverdi= denne.verdi;
            denne=denne.neste;

            return tempverdi;
            //throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe
