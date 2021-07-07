use azienda_videogiochi;

USE Azienda_Videogiochi;

select * from azienda;
select * from editore;
select * from progettista;
select * from sede;
select * from team;
select * from videogioco;



/*1) Elencare i giochi con PEGI maggiori o uguali a 16(Codice, Titolo, Genere) ordinati per titolo;
SELECT Codice,Titolo,Genere
FROM Videogioco
WHERE PEGI>= 16
ORDER BY Titolo;*/

/*2) Elencare per ogni team il numero di progettisti appartenenti (ID, Nome, Num_Progettisti);
SELECT T.ID, T.Nome, count(*) AS Num_Progettisti
FROM Team AS T JOIN Progettista AS P ON T.ID= P.Team
GROUP BY T.Nome;*/

/*3) Determinare quanti team lavorano in un'azienda e la sede principale delle aziende (Nome, Citta, Num_Team)
SELECT A.Nome, S.Citta, count(*) AS Num_Team
FROM Sede AS S JOIN (Azienda AS A JOIN Team AS T ON A.Nome= T.Azienda) ON S.Azienda = A.Nome
GROUP BY A.Nome;*/

/*4) L'azienza che ha prodotto tutti i videogiochi di genere sport
SELECT *
FROM Azienda A
WHERE NOT EXISTS (SELECT *
					FROM Videogioco V
					WHERE V.Genere= "Sport" AND NOT EXISTS( SELECT *
															FROM Editore E
                                                            WHERE E.ID = V.IDEditore AND E.Nome = A.Nome));*/

/*5) Individuare il Team che ha progettato il maggior numero di giochi (ID, Nome, Max_Giochi)
CREATE VIEW Temp (ID,Nome, Num_Giochi) AS
SELECT tempT.ID, T.Nome, count(*)
FROM Team AS T JOIN Videogioco AS V ON T.ID = V.Team
GROUP BY T.ID;

SELECT *
FROM Temp
WHERE Num_Giochi= (SELECT Max(Num_Giochi)
					FROM Temp);*/
                                        
/* 6) Visualizzare gli editori di cui non si conosce il numeri di giochi rilasciati (ID, Nome, Tipo, Azienda)                    
SELECT ID, Nome, Tipo , Azienda                   
FROM Editore
WHERE Num_Giochi IS NULL;*/

/*7) Individuare il Team che ha prodotto videogiochi di genere avventura ma non di sport (IDTeam,NomeTeam, NomeVideogioco, Genere)
SELECT T.ID, T.Nome, V.Titolo, V.Genere
FROM Videogioco V JOIN Team T ON V.Team= T.ID
WHERE V.Genere="Avventura" AND T.ID NOT IN (SELECT T.ID
									FROM Videogioco V1 JOIN Team T ON V1.Team= T.ID
                                    WHERE V1.Genere="Sport");*/                                                         