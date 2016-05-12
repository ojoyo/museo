package org.beaconmuseum.beaconmuseum.map;

/**
 * Interfejs do obiektu rysującego mapę pomieszczenia na ekranie.
 */
public interface MapDrawerInterface {
    /**
     * Służy do wprowadzenia ścian w pomieszczeniu.
     *
     * @param pts Punkty wyznaczające ściany pomieszczenia. Koordynaty sąsiednich punktów
     *            oraz pierwszego z ostatnim tworzą linię obrazującą ścianę.
     */
    public void setRoomBorders(Point[] pts);

    /**
     * Służy do wprowadzenia punktów rezprezentujących beacony i inne ciekawe miejsca
     * na mapie. Na razie bez podpisów.
     *
     * @param pts Punkty, które mają być zaznaczone na mapie.
     */
    public void setPOIs(Point[] pts);

    /**
     * Ustawia pozycję obserwatora (użytkownika aplikacji).
     *
     * @param observer Punkt, w którym ma być zaznaczony obserwator.
     */
    public void setPosition(Point observer);

    /**
     * Rysuje mapę na urządzeniu.
     *
     * Przeprowadza ciężkie obliczenia takie jak:
     * 1. Wyznaczenie otoczki wypukłej i sortowanie kątowe <br>
     * 2. Rotating calipers <br>
     * 3. Ustalenie optymalnego obrotu obrazu dostosowanego do ekranu w taki sposób,
     * by margines jaki zostanie niewykorzystany na ekranie był jak najmniejszy <br>
     * 4. Przeskalowanie obrazu by mieścił się na ekranie <br>
     * 5. Narysowanie wyniku
     */
    public void draw();

    /**
     * Służy do szybkiej aktualizacji położenia użytkownika, bez kalibracji ekranu.
     *
     * Wykorzystuje wcześniej obliczony obrót i współczynnik skalujący.
     *
     * @param observer Nowa pozycja użytkownika.
     */
    public void fastRedraw(Point observer);
}
