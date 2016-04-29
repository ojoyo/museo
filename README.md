# IO-Project
beacon-museum IO project

TODO:
* map Beacon names to the paintings

# Do slajdów:
* Nowe użyte frameworki: JUnit, RoboGuice, Mockito i Robolectric, głównie na potrzeby testowania

* JUnita nikomu nie trzeba przedstawiać
** (Mały) Problem: dlaczego @BeforeClass musi być statyczne?!

* RoboGuice
** Znakomicie ułatwia (umożliwia) podmianę klas do mockowania
** Kod się uprościł (mimo że i tak był prosty)
** Użycie DI w module A oznacza, że jeśli jakiś moduł B korzysta z modułu A to B też musi korzystać z DI
** Android Studio niesłusznie podkreśla na czerwono injekcje (że są nie zainicjalizowane)
** Przerażająca wizja konfigurowania XMLi z bindingami

* Mockito
..* Prosty i przyjazny interfejs ale...
..* Nie można mockować metod statycznych w klasach (np. w Beacon SDK)
..* Kłopoty z klasami zadeklarowanymi jako final?
..* Troch� dziwne wymagania je�li chodzi o mieszanie matcher�w i "raw" warto�ci
..* K�opoty z redukcj� typ�w w templatach do object
..* Mora�: tworz�c jakie� API mie� na uwadze to �e kto� mo�e chcie� je mockowa�...

* Robolectric
** Ułatwia trochę życie z RoboGuice
** Testowane metody nie rzucają AndroidMethodNotImplementedOnPCPleaseUseRealDeviceAndUSBCableException
** Nie ma możliwości emulowania androidowych klas obsługujących bluetooth (mają modyfikator final)

* 100% Code Coverage - nie jest możliwe
..* Cienka warstwa abstrakcji między SDK a modułem Beaconów
..* Użycie prostych lambd wpuszczanych w SDK
..* Narzędzie do CC w Android Studio ma fajne możliwości, ale jest dość głupie - pokazuje coverage klas systemowych...
..* Ostateczny coverage wynosi ok. 80%

* Tutaj machnąć pełny diagram klas modułu Bekonów z zależnościami - duży
