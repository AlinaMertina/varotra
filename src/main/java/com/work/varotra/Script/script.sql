create table produit(
    idproduit bigint primary key,
    nomproduit text,
    numproduit varchar(50)
);

create table client(
    idclient bigint primary key,
    nomclient text,
    numeroclient varchar(50)
);

create table fourniseur(
    idfourniseur bigint primary key,
    nomfourniseur text,
    numerofournieur varchar(50)
);

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
    idTypedepayement bigint,
    datedemande TIMESTAMP,
    delait TIMESTAMP,
    validationsup bigint,
    FOREIGN KEY (idtypedepayement) REFERENCES Typedepayement(idtypedepayement),
    FOREIGN KEY (validationsup) REFERENCES employe(idemploye)
);

create table employe (
    idemploye bigint primary key,
    nomemployer varchar(50),
    idsupe bigint,
    FOREIGN KEY (idemploye) REFERENCES employe(idemploye)
);

create table connexion(
    idconnexion bigint primary key,
    idemploye bigint ,
    password varchar(50),
    FOREIGN KEY (idemploye) REFERENCES employe(idemploye)
);


create table detailledemande(
    iddetailledemande bigint primary key,
    iddemandesociete bigint,
    idproduit bigint,
    idclient bigint,
    iduniter bigint,
    quantiter double precision,
    datedemande TIMESTAMP,
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