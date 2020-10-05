# Server-de-mesagerie

Server de mesagerie

Un server care poate fi folosit de aplicatii pentru a comunica intre
ele prin mesaje. Prin intermediul serverului pot fi efectuate trei
tipuri de activitati:
- trimitere de mesaje
- receptie de mesaje
- administrare

Mesajele sunt trimise si receptionate prin intermediul a doua tipuri
de resurse oferite de server:
- message queues
- topics

Cozile de mesaje pot tine un numar maxim de mesaje la un moment dat,
iar accesul lor se face dupa princpiul FIFO. Fiecare mesaj specifica
intr-un antet destinatarul mesajului. Daca un program nu este
destinatar al mesajului nu il va prelua. Preluarea unui mesaj implica
scoaterea lui din coada.

Resursele de tip Topic permit publicarea de mesaje care pot fi citite
de un numar nelimitat de clienti. Nu exista destinatari explicit
specificati, dar fiecare mesaj are un anumit tip, memorat sub forma
unui camp in antetul sau. Clientii care citesc mesaje specifica tipul
mesajului pe care il doresc. La citire, mesajele nu sunt sterse, dar
ele vor fi sterse automat dupa ce trece un timp de la
publicarea lor, specificat in antetul mesajului. Serverul poate fi
configurat cu un timp maxim de valabilitate a mesajelor, masurat de la
momentul publicarii. Dupa trecerea acestui
timp, mesajele vor fi sterse indiferent de timpul specificat in
antetul lor.
