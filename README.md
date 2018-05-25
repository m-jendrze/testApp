# TestApp

Przykładowa aplikacja do zarządzania umowami utrzymaniowymi systemów. Aplikacja pozwala na dodawanie nowych umów, informacji o systemach, wyłączenia umów jako nieaktywne, oraz import umów z pliku xlsx.

### Środowisko

Aplikacja buduje się do paczki WAR, uruchamiamy na kontenerze aplikacji Java Servlets (Tomcat 8). Do uruchomienia wymagana zainstalowana Java 8.
Aplikacja wymaga zainstalowanej bazy danych PostgreSQL.

### Instalacja

W głównym folderze aplikacji wywołujemy:
```
mvn install
```
aby zbudować paczkę WAR w folderze target/. Paczkę WAR przenosimy do kontenera aplikacji. Przed uruchomieniem aplikacji w razie potrzeb konfigurujemy plik [database.properties](src/main/resources/database.properties), oraz uruchamiamy skrypty tworzące bazy danych [create.sql](create.sql)

### Opis aplikacji

Aplikacja napisana z wykorzystaniem Java 8 oraz frameworków Spring Framework 5, Hibernate 4.

Backend aplikacji wydzielony jest na pakiety:
* configuration - konfiguracja frameworków (Spring, Hibernate)
* controller - kontrolery do widoków web (implementacja widoku z Apache Tiles), oraz api RESTowe dla operacji CRUD na bazie danych
* dao - obiekty do komunikacji z bazą danych implementujące operacje CRUD
* exceptions - wyjątki aplikacji
* mappers - mapery do przekształcania obiektów encji do DTO w obie strony
* model - obiekty reprezentujące dane aplikacji, zarówno encje bazy danych jak i DTO
* service - główne obiekty z logiką biznesową aplikacji

Mechanizm importu danych z pliku xlsx pozwala na podanie kolumn z danymi w dowolnej kolejnosci, wymagana jest jednak zachowana kompletna ilosc kolumn. Przykladowy plik importu - [umowy_2016.xlsx](src/test/resources/umowy_2016.xlsx)

Frontend aplikacji wykorzystuje Apache Tiles, do struktury widoku, oraz jQuery + jTable do reprezentacji danych. Aplikacja posiada 3 widoki, pozwalające na przeglądane, edycje oraz dodawanie danych:
* active contracts - tabela aktywnych kontraktów - z poziomu tego widoku możemy dezaktywować kontrakty
* all contracts - tabela wszystkich kontraktów znajdujących się na bazie danych - z poziomu tego widoku można importować pliki z danymi kontraktów
* systems - tabela systemów

### Testy

Testy obejmują mappery aplikacji, oraz mechanizm konwersji pliku xlsx do obiektów domenowych aplikacji

## Autor

* **Michał Jendrzejek**