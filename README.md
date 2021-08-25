# Repository
Zadanie rekrutacyjne backend - "repository" zostało zrealizowane w technologiin Spring Boot v2.5, Java v11

Maven build:

mvn clean install

mvn spring-boot:run


HTTP API 
--
 
GET /users/{login}

przykład:
http://localhost:8080/users/octocat

API zgodne z https://api.github.com/users/{login} zawiera dodatkowy parametr calculations

```
{
"id": "...",
"login": "...",
"name": "…",
"type": "...",
"avatarUrl": „”,
"createdAt": "..."
"calculations": "..."
}

```

calculations - wynik działania 6 / liczba_followers * (2 + liczba_public_repos).

---

# SWAGGER - REST CLIENT GUI
Dokumentacja API znajduje się pod adresem:
http://localhost:8080/swagger-ui.html#/

Swagger pozwala na testowanie aplikacji zadania rekrutacyjnego. Aplikacja pokazuje pełny zestaw metod, modele i przykładowe obiekty w strukturze JSON.

![image](https://user-images.githubusercontent.com/14261389/130838856-592ae7c1-fd09-4dd1-93ed-ec215aeaa107.png)


Rys 1. Widok z aplikacji Swagger w aplikacji zadanie rekrutacyjne.


# H2 - CLIENT  
Klient bazy danych znajduje się pod adresem:
http://localhost:8080/h2-console

login:task

hasło:task

JSBC URL=jdbc:h2:mem:testdb

![image](https://user-images.githubusercontent.com/14261389/130858091-c6248a59-f74e-4e51-8d28-42111cf71390.png)

Rys 2. Widok z aplikacji H2 console - strona logowania.

H2 to baza pozwalająca na zapis do pliku. Baza pozwala na zapis danych i przechowywanie ich w pamięci - na potrzeby prezentacji został wybrany zapis danych w pamięci.

![image](https://user-images.githubusercontent.com/14261389/130838932-433db220-10d8-4798-88e3-b121edaa7879.png)


Rys 3. Widok z aplikacji H2 console prezentujące dane.



