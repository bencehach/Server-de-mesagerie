# Server-de-mesagerie

Server de mesagerie

<h2> Scopul proiectului </h2>
Un server care poate fi folosit de aplicatii pentru a comunica intre ele prin mesaje. Prin intermediul serverului pot fi efectuate trei tipuri de activitati:
- trimiterea mesajelor
- receptia mesajelor
- administrare

<h2> Instalare si rulare </h2>

Dupa extragerea din formatul zip, se deschide proiectul in IDE-ul preferat si se ruleaza clasa Server. Apoi pentru a crea un nou user, se scrie in terminal comanda

**$ telnet localhost 9991**

Sidenote: asigurati-va ca aveti TelnetClient turned on pe computerul dvs.

<h2> Exemplu utilizare </h2>

Pentru a trimite un mesaj unui alt user, se scrie in terminal comanda:

**$ message:<user_id>:<message_text>** 
  
 Pentru a trimite un mesaj pe un anumit subiect (Topic), folosim comanda:
 
 **$ topic:<topic_name>:<message_text>**
 
 Pentru a lista toate topicurile:
 
 **$ topic:list:all**
 
 Pentru a incheia sesiunea unui user, ii inchidem fereastra terminalului.
 
 <h2> Arhitectura sistemului </h2>
	
Clasa principala (main) este clasa Server. Aici se apeleaza run() pe 2 clase: Pinger si Server. 
	
 
 
 




