package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


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

        if (a == null){

            throw new NullPointerException("Tabellen a er null!");
        }

        if (a.length > 0){

            int teller = 0;

            while (teller < a.length){

                if (a[teller] != null){
                    hode = new Node<>(a[teller]);
                    antall++;
                    break;
                }
                teller++;
            }

            hale = hode;


            if (hale != null){
                teller++;


                while (teller < a.length){

                    if (a[teller] != null){

                        hale = hale.neste = new Node<>(a[teller],hale,null);
                        //(hale.neste).forrige = hale;
                        //hale = hale.neste;
                        antall++;

                    }
                    teller++;

                }


            }

        }

        //throw new UnsupportedOperationException();
    } // Generisk Metode



    public Liste<T> subliste(int fra, int til) {

        fraTilKontroll(antall,fra,til);

        Liste<T> liste = new DobbeltLenketListe<>();
        int tabellengde = til-fra;

        if (tabellengde < 1) return liste;

        Node<T> current = finnNode(fra);

        for (int i = fra; i < til; i++){ // int i = tabellengde; i > 0; i--
            liste.leggInn(current.verdi);
            current = current.neste;

        }

        return liste;


        //throw new UnsupportedOperationException();
    }



    @Override
    public int antall() {
        return antall;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {
        return antall == 0;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {

        if (indeksTil(verdi) == -1) {
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

        if (verdi == null) return -1;

        Node<T> indeks = hode;

        for (int i = 0; i < antall; i++){

            if (indeks.verdi.equals(verdi)){
                return i;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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

        s.append('[');

        if (!tom() && hale != null)
        {
            Node<T> current = hale;
            s.append(current.verdi);

            //current = current.forrige;

            while (current.forrige != null)  // tar med resten hvis det er noe mer
            {
                current = current.forrige;
                s.append(',').append(' ').append(current.verdi);

            }
        }

        s.append(']');

        return s.toString();

        //throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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
