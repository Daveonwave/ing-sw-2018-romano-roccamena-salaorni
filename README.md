# Prova finale di Ingegneria del Software 2018 
 
## Membri del gruppo:	

	- Roccamena Massimiliano
		Codice Persona : 10499005
		Matricola : 847275
	- Romanò Lorenzo : 
		Codice Persona : 10522287
		Matricola : 848033
	- Salaorni Davide :
		Codice Persona : 10526800
		Matricola : 846534
	
## Coverage dei test:





## Diagramma UML delle classi:
![alt text](https://github.com/Daveonwave/ing-sw-2018-romano-roccamena-salaorni/blob/master/docs/UML%20completo.png)

Il diagramma UML delle classi mostrato in figura è il diagramma totale della struttura del progetto e comprende: implementazione del pattern mvc, componente di connessione (socket e RMI), classi dell'interfaccia grafica di gioco e package utilizzati funzioni ausiliarie.
Nel diagramma non sono state inserite le classi che fungono da meri contenitori di costanti utili per il gioco. Inoltre abbiamo scelto di non aggiungere classi le cui funzionalità non sono state implementate per tempo; tuttavia, tali classi, sono state lasciate nel codice in virtù del fatto che è stato compiuto lavoro anche intorno ad esse e, nonostante non siano complete, le riteniamo valide e parzialmente funzionanti.

La struttura base della nostra architettura mvc nasce a partire dalle seguenti interfacce:

![alt text](https://github.com/Daveonwave/ing-sw-2018-romano-roccamena-salaorni/blob/master/docs/UML%20mvc%20interfaces.png) 

Le due interfacce principali sono AppViewStub e AppControllerStub che svolgono le funzioni:
- AppViewStub : osservarzione del model da parte della view
- AppControllerStub : aggiornamento dello stato della view attraverso le chiamate del model. 


L'implementazione delle interfacce è stata sviluppata nelle seguenti classi:

![alt text](https://github.com/Daveonwave/ing-sw-2018-romano-roccamena-salaorni/blob/master/docs/UML%20mvc%20structure.png)

Esse comprendo il model, il controller e una classe astratta chiamata AppView che verrà estesa dalle interfacce grafiche.


L'interfaccia grafica sviluppata attraverso JavaFx ha assunto una struttura di questo tipo:

![alt text](https://github.com/Daveonwave/ing-sw-2018-romano-roccamena-salaorni/blob/master/docs/UML%20gui.png)


Infine la parte di connessione è stata implementata nel seguente modo:

![alt text](https://github.com/Daveonwave/ing-sw-2018-romano-roccamena-salaorni/blob/master/docs/UML%20connection.png)

Abbiamo adottato i pattern visitor e command per l'implementazione della connessione tramite socket. Rmi invece è strutturato come spiegato in classe.

## Funzionalità implementate: 

Le funzionalità implementate nel nostro progetto sono: regole del gioco, GUI, Rmi, socket e, come funzionalità aggiuntiva, il multi partita.
Tuttavia, è presente nel nostro codice una parziale implementazione di CLI e single player, funzionalità purtroppo non portate a termine per questioni di tempo dovuti a problemi riscontrati nelle altre parti del progetto, che ci hanno costretto ad abbandonarne l'implementazione. 


## Limitazioni e scelte di design:  

Il nostro progetto è stato strutturato per svolgere un'intera partita a Sagrada.
Prevede 2 file di configurazione esterni nella cartella "src/main/java/config/" in cui è possibile settare indirizzo ip e porta di client e server e timer collocati all'interno del gioco.
Abbiamo creato due file jar esterni per far partire server e client e, in aggiunta, abbiamo offerto la possibilità di usare l'applicazione attraverso due script appositi.

La struttura, come spiegato nella presentazione dei diagrammi UML, si sviluppa a partire dalle due interfacce sopracitate in cui abbiamo racchiuso tutte le potenziali azioni attuabili dall'utente e gli eventi che si sarebbero potuti verificare nel corso del gioco.
L'implementazione del model è stata avviata tenendo subito conto della possibilità di sviluppare le funzionalità di multiplayer e singleplayer contemporaneamente, senza dover separare troppo l'implementazione delle due modalità di gioco. Per questo si può notare che nella classe MatchCreator sono presenti flag e metodi con riferimenti alla partita single player.
L'interfaccia grafica è stata sviluppata attraverso il software "SceneBuilder" e l'implementazione delle classi mostrate nel corrispondente diagramma UML. E' stata divisa in quattro finestre che racchiudono tutte le funzionalità utili durante la partita, tra cui la gestione del multi partita e la possibilità di ripartecipare ad una partita una volta chiusa volontariamente.
Infine è possibile notare nel codice la presenza di un package "console" in cui è stata parzialmente implementata la Cli.

La limitazione del nostro progetto, oltre a quelle relative alle funzionalità non implementate, è quella della disconnessione involontaria che non viene gestita, o solo parzialmente nella connessione socket.  
