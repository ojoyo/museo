# IO-Project
beacon-museum IO project

TODO:
* map Beacon names to the paintings

# Do slajd�w:
* Nowe u�yte frameworki: JUnit, RoboGuice, Mockito i Robolectric, g��wnie na potrzeby testowania

* JUnita nikomu nie trzeba przedstawia�
	* (Ma�y) Problem: dlaczego @BeforeClass musi by� statyczne?!

* RoboGuice
	* Znakomicie u�atwia (umo�liwia) podmian� klas do mockowania
	* Kod si� upro�ci� (mimo �e i tak by� prosty)
	* U�ycie DI w module A oznacza, �e je�li jaki� modu� B korzysta z modu�u A to B te� musi 	  korzysta� z DI
	* Android Studio nies�usznie podkre�la na czerwono injekcje (�e s� nie zainicjalizowane)
	* Przera�aj�ca wizja konfigurowania XMLi z bindingami

* Mockito
	* Prosty i przyjazny interfejs ale...
	* Nie mo�na mockowa� metod statycznych w klasach (np. w Beacon SDK)
	* K�opoty z klasami zadeklarowanymi jako final?
	* Troch� dziwne wymagania je�li chodzi o mieszanie matcher�w i "raw" warto�ci
	* K�opoty z redukcj� typ�w w templatach do object (wina Javy, istnieje workaround)
	* Mora�: tworz�c jakie� API mie� na uwadze to �e kto� mo�e chcie� je mockowa�...
  

* Robolectric
	* U�atwia troch� �ycie z RoboGuice
	* Testowane metody nie rzucaj� 	  AndroidMethodNotImplementedOnPCPleaseUseRealDeviceAndUSBCableException
	* Nie ma mo�liwo�ci emulowania androidowych klas obs�uguj�cych bluetooth (maj� modyfikator final)

* 100% Code Coverage - nie jest mo�liwe
	* Cienka warstwa abstrakcji mi�dzy SDK a modu�em Beacon�w
	* U�ycie prostych lambd wpuszczanych w SDK
	* Narz�dzie do CC w Android Studio ma fajne mo�liwo�ci, ale jest do�� g�upie - pokazuje coverage klas systemowych...
	* Ostateczny coverage dla beacon�w wynosi 80% linii

* Tutaj machn�� pe�ny, du�y diagram klas modu�u Bekon�w z zale�no�ciami
