create table produit(
    idproduit bigint primary key,
    nomproduit text,
    numproduit varchar(50)
);
ALTER TABLE produit
ADD COLUMN iduniter bigint,
ADD CONSTRAINT fk_table2_id -- Nom de la contrainte de clé étrangère
FOREIGN KEY (iduniter) -- Colonne table2_id
REFERENCES uniter(iduniter); 



create table client(
    idclient bigint primary key,
    nomclient text,
    numeroclient varchar(50)
);
ALTER TABLE client
ADD COLUMN address text;
ALTER TABLE client
ADD COLUMN tel varchar(50);
ALTER TABLE client
ADD COLUMN email varchar(50);

update client set address='Andoharanofotsy',tel='034 05 300 32 - 033 15 300 40 – 032 05 300 40',email='ituniversity@moov.mg' where idclient=1;

update client set address='	74, Avenue du 26 juin Analakely BP 174 101 Antananarivo',tel='+261 20 22 228 00 | +261 20 22 239 51 ',email='info@bni.mg' where idclient=2;


create table fourniseur(
    idfourniseur bigint primary key,
    nomfourniseur text,
    numerofournieur varchar(50)
);
ALTER TABLE fourniseur
ADD COLUMN address text;
ALTER TABLE fourniseurOverViewProformat
ADD COLUMN tel varchar(50);
ALTER TABLE fourniseur
ADD COLUMN email varchar(50);



update fourniseur set address='Jumbo Score, Enceinte Cora, Ankorondrano, Antananarivo 101',tel=' 020 22 210 00',email='jumbo@score.mg' where idfourniseur=1;
update fourniseur set address='24 rue Radama Ier, Antananarivo, Madagascar',tel='020 22 223 73',email='jb@basan.mg' where idfourniseur=2;
update fourniseur set address='Rue Docteur Joseph Raseta, 101 Antananarivo',tel='(+261) 20 23 277 11',email='star.star-contact@castel-afrique.com' where idfourniseur=3;




create table actionetat(
    idaction bigint primary key,
    nomaction varchar(10)
);



create table uniter(
    iduniter bigint primary key,
    numerouniter varchar(50),
    nomuniter text
);

create table Typedepayement(
    idtypedepayement bigint primary key,
    nompayement varchar(50)
);
insert into Typedepayement values (1,'Especes');
insert into Typedepayement values (2,'Carte de credit/debit');
insert into Typedepayement values (3,'Virement bancaire');
insert into Typedepayement values (4,'Cheque');


delete from detailledemande;
delete from stock;
delete from demandesociete;


create table stock(
    idstock bigint primary key,
    idfourniseur bigint,
    idproduit bigint,
    idaction bigint,
    iduniter bigint,
    quantiter double precision,
    prixunitaireachat double precision,
    prixunitairevente double precision,
    date TIMESTAMP,
    FOREIGN KEY (idfourniseur) REFERENCES fourniseur(idfourniseur),
    FOREIGN KEY (idproduit) REFERENCES produit(idproduit),
    FOREIGN KEY (idaction) REFERENCES actionetat(idaction),
    FOREIGN KEY (iduniter) REFERENCES uniter(iduniter)
);
ALTER TABLE stock
ADD COLUMN idfstock bigint,
ADD CONSTRAINT fk_table2_id -- Nom de la contrainte de clé étrangère
FOREIGN KEY (idfstock) -- Colonne table2_id
REFERENCES stock(idstock); 

ALTER TABLE stock
ADD COLUMN iddemandesociete bigint;







select * from stock where idfourniseur=2 and idproduit=2 and idaction=3 and quantiter!=0 and date>=(select date from stock where idaction=3 and idproduit=2 and idfourniseur=2 order by date desc limit 1 ) order by idfstock;

select * from stock where idfourniseur=1 and idproduit=2 and idaction=3 and quantiter!=0 and date>=(select date from stock where idaction=3 and idproduit=2 and idfourniseur=1 order by date desc limit 1 ) order by idfstock

select distinct(idproduit)  from stock where idfourniseur=:idfourniseur


select nomproduit from stock join produit on stock.idproduit=produit.idproduit where nomproduit.idproduit=:idproduit ;

select nomuniter from stock join uniter on stock.iduniter=produit.iduniter where produit.iduniter=:iduniter ;

drop table TVAetat;

create table Tvaetat(
    idtva bigint primary key,
    valeur double precision,
    date TIMESTAMP
);
insert into Tvaetat values (1,0.2,'2023-11-19');

select valeur from Tvaetat  order by date desc limit 1;

select (AVG(prixunitaireachat)-AVG(prixunitairevente)) * (select valeur from TVAetat limit 1) as tvam from stock where idfourniseur=1 and idproduit=1 and idaction=1;

select nompayement from demandesociete join typedepayement on demandesociete.idtypedepayement=typedepayement.idtypedepayement;

select nomclient from demandesociete join typedepayement on demandesociete.idtypedepayement=typedepayement.idtypedepayement;

select nomclient from demandesociete join employe on employe.idemploye=demandesociete.validationsup join client on employe.idclient=client.idclient where iddemandeclient=:iddemandeclient

select idemploye  from employe where idsupe=2;

select * from demandesociete where idsupe in ( select idemploye  from employe where idsupe=2 );

create table detaillestock(
    iddetaillestock bigint primary key,
    idstock bigint,
    idclient bigint,
    datedelivraison TIMESTAMP,
    idTypedepayement bigint,
    FOREIGN KEY (idstock) REFERENCES stock(idstock),
    FOREIGN KEY (idclient) REFERENCES client(idclient),
    FOREIGN KEY (idtypedepayement) REFERENCES Typedepayement(idtypedepayement)
);

create table demandeclientfourniseur(
    iddemandeclient bigint primary key,
    idproduit bigint,
    idclient bigint,
    idfourniseur bigint,
    iduniter bigint,
    quantiter double precision,
    datedemande TIMESTAMP,
    datefindemande TIMESTAMP,
    FOREIGN KEY (idproduit) REFERENCES produit(idproduit),
    FOREIGN KEY (idclient) REFERENCES client(idclient),
    FOREIGN KEY (idfourniseur) REFERENCES fourniseur(idfourniseur),
    FOREIGN KEY (iduniter) REFERENCES uniter(iduniter)
);

create table demandesociete(
    iddemandesociete bigint primary key,
    idtypedepayement bigint,
    datedemande TIMESTAMP,
    delait TIMESTAMP,
    validationsup bigint,
    FOREIGN KEY (idtypedepayement) REFERENCES Typedepayement(idtypedepayement),
    FOREIGN KEY (validationsup) REFERENCES employe(idemploye)
);



create table departement(
    iddepartement bigint primary key,
    nomdepartment varchar(50)
);

drop table reponsefourniseur;
drop table detailledemande;
drop table  demandesociete;
drop table connexion;
drop table employe;


create table employe (
    idemploye bigint primary key,
    nomemployer varchar(50),
    idsupe bigint,
    idclient bigint,
    iddepartement bigint,
    FOREIGN KEY (idemploye) REFERENCES employe(idemploye),
    FOREIGN KEY (idclient) REFERENCES client(idclient)
);
-- Ajout de la colonne table2_id comme clé étrangère dans table1
ALTER TABLE employe
ADD COLUMN idfourniseur bigint, -- Type de la colonne
ADD CONSTRAINT fk_table2_id -- Nom de la contrainte de clé étrangère
FOREIGN KEY (idfourniseur) -- Colonne table2_id
REFERENCES fourniseur(idfourniseur); -- Référence à la colonne id de table2


select nomdepartment from employe join departement on employe.iddepartement=departement.iddepartement where idemploye=:idemploye

insert into employe values (1,'samira',null,1,1);
insert into employe values (2,'rodrigo',1,1,1);
insert into employe values (3,'roberto',2,1,1);

insert into employe values (4,'biclady',2,null,1,1);
insert into employe values (5,'score',null,null,null,2);
insert into employe values (6,'star',null,null,null,3);





create table connexion(
    idconnexion bigint primary key,
    idemploye bigint ,
    password varchar(50),
    FOREIGN KEY (idemploye) REFERENCES employe(idemploye)
);

insert into connexion values (1,1,'mertina50');
insert into connexion values (2,2,'mertina50');
insert into connexion values (3,3,'mertina50');
insert into connexion values (4,4,'mertina50');
insert into connexion values (5,5,'mertina50');

insert into connexion values (6,6,'mertina50');





drop table detailledemande;



create table detailledemande(
    iddetailledemande bigint primary key,
    iddemandesociete bigint,
    idproduit bigint,
    idclient bigint,
    iduniter bigint,
    quantiter double precision,
    FOREIGN KEY (iddemandesociete) REFERENCES demandesociete(iddemandesociete),
    FOREIGN KEY (idproduit) REFERENCES produit(idproduit),
    FOREIGN KEY (idclient) REFERENCES client(idclient),
    FOREIGN KEY (iduniter) REFERENCES uniter(iduniter)
);


create table reponsefourniseur(
    idreponse bigint primary key,
    iddemandesociete bigint,
    idfourniseur bigint,
    iduniter bigint,
    prixunitaireachat double precision,
    tva double precision,
    quantiterdisponible double precision,
    FOREIGN KEY (iddemandesociete) REFERENCES demandesociete(iddemandesociete),
    FOREIGN KEY (idfourniseur) REFERENCES fourniseur(idfourniseur),
    FOREIGN KEY (iduniter) REFERENCES uniter(iduniter)
);