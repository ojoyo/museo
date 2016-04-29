# IO-Project
beacon-museum IO project

TODO:
* map Beacon names to the paintings

# Do slajdów:
* Nowe u¿yte frameworki: JUnit, RoboGuice, Mockito i Robolectric, g³ównie na potrzeby testowania

* JUnita nikomu nie trzeba przedstawiaæ
	* (Ma³y) Problem: dlaczego @BeforeClass musi byæ statyczne?!

* RoboGuice
	* Znakomicie u³atwia (umo¿liwia) podmianê klas do mockowania
	* Kod siê uproœci³ (mimo ¿e i tak by³ prosty)
	* U¿ycie DI w module A oznacza, ¿e jeœli jakiœ modu³ B korzysta z modu³u A to B te¿ musi 	  korzystaæ z DI
	* Android Studio nies³usznie podkreœla na czerwono injekcje (¿e s¹ nie zainicjalizowane)
	* Przera¿aj¹ca wizja konfigurowania XMLi z bindingami

* Mockito
	* Prosty i przyjazny interfejs ale...
	* Nie mo¿na mockowaæ metod statycznych w klasach (np. w Beacon SDK)
	* K³opoty z klasami zadeklarowanymi jako final?
	* Trochê dziwne wymagania jeœli chodzi o mieszanie matcherów i "raw" wartoœci
	* K³opoty z redukcj¹ typów w templatach do object (wina Javy, istnieje workaround)
	* Mora³: tworz¹c jakieœ API mieæ na uwadze to ¿e ktoœ mo¿e chcieæ je mockowaæ...
  

* Robolectric
	* U³atwia trochê ¿ycie z RoboGuice
	* Testowane metody nie rzucaj¹ 	  AndroidMethodNotImplementedOnPCPleaseUseRealDeviceAndUSBCableException
	* Nie ma mo¿liwoœci emulowania androidowych klas obs³uguj¹cych bluetooth (maj¹ modyfikator final)

* 100% Code Coverage - nie jest mo¿liwe
	* Cienka warstwa abstrakcji miêdzy SDK a modu³em Beaconów
	* U¿ycie prostych lambd wpuszczanych w SDK
	* Narzêdzie do CC w Android Studio ma fajne mo¿liwoœci, ale jest doœæ g³upie - pokazuje coverage klas systemowych...
	* Ostateczny coverage dla beaconów wynosi 80% linii

* Tutaj machn¹æ pe³ny, du¿y diagram klas modu³u Bekonów z zale¿noœciami
