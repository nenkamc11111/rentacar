Projekt: Nanobots Modull ASYS WS 2020/2021
Tamo nenkam Cedric
Matrikelnummer 199933

# rentacar
 
rentacar.sql importieren in PostgreSql
Service :
- Kunde
- Auto
- Reservierung
- Payment
- Healthrentacar
importieren 


Inhalte:
- "Documentation"
- "src" rentacar wo Source Code im Ordner  ist
- paar Diagramm 
- Vortrag Tamo_nenkam.pdf 
- README

Architektur 
Rentacar - Mieten Szenario 

Service Kunde 
Service auto
Service reservierung
Service Payment
Service healthrentacar
     kunde , Auto , Reservierung , Payment sind so gebaut :
     - Controller
     - model 
     - Repository 
     - Service 
 und sie kommunitieren mitanerder 
bounded context Diagramm 

Sequenz Diagramm

Architektur von ganz System (Mieten Szenario)

Architektur mit Archunit getestet 

JUnit für unit- Test 

Sercice health mit Spring Boot Management 

Die log des Apps ist zentralisiert mit ELK erstellt
elastic  ---port 9200
kibana --- port 5601

Alle Sequenz von dem Szenario sind mit Postman (Backend)

------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------------

#Checkliste eines Services
• Readme  ->  OK
• Dokumentation von Schnittstellen basierend auf dem Code(z.B. via Swagger) ->
- http://127.0.0.1:8083/swagger-ui.html----> mieten 
- http://127.0.0.1:8082/swagger-ui.html----> Auto
- http://127.0.0.1:8081/swagger-ui.html----> Kunde
- http://127.0.0.1:8084/swagger-ui.html----> Kayment

• Logs werden in STDOUT/ERR geschrieben  mit Log4J
• Logs in JSON Format--> OK mit log4j
• CorrelationId TraceId vorhanden --> Spring Boot Admin durch port:8000 
• Zentralisierter Logging ----> OK  mit ELK •
Versionen von Dependensies sind festgelegt ----> OK
• Konfiguration via Umgebungsvariablen------> OK in der Datei application.properties und wird mit SpringBootAdmin monitoren.
• Health/Info endpoint------> OK in Spring boot Admin sieht man die Metriken wie Heap, CPU, Speicher, garbage Collector ...
• Graceful shutdown
• Connection zu Datenbank regulär geprüft---- > OK connecion-pool-DB PostgrSql mit timeout 6000
• Service horizontal skalierbar d.h. s tatelos oder der Zustand muss nach N eustart automatisch
wiederhergestellt werden ----> OK
• Tolerant Reader Pattern anwenden-----> OK



