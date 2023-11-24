insert into  uniter  values (1,'UNIT00011','kilogramme');
insert into  uniter  values (2,'UNIT00021','litre');
insert into  uniter  values (3,'UNIT00031','piece');

insert into actionetat values (1,'entree');
insert into actionetat values (2,'sortie');
insert into actionetat values (3,'inventaire');
insert into actionetat values (4,'demande');


insert into fourniseur values (1,'SCORE','FOR00001');
insert into fourniseur values (2,'JB','FOR00001');
insert into fourniseur values (3,'STAR','FOR00001');
insert into fourniseur values (4,'BIC','FOR00001');

insert into client values (1,'ITU','CL0001');
insert into client values (2,'BNI','CL0002');

insert into produit values (1,'regle','PRO0001',3);
insert into produit values (2,'ordinateur','PRO0002',3);
insert into produit values (3,'stylo','PRO0003',3);

insert into departement values (1,'Informatique');
insert into departement values (2,'securite');


delete from detailledemande;
delete from stock;
delete from demandesociete;

--donner produit 


-- donner ordinateur
--fourniseur 1

insert into stock values (1,1,2,1,3,3,1000.0,1500.0,'2023-11-19',null);
insert into stock values (2,1,2,1,3,4,1000.0,1700.0,'2023-11-19',null);
insert into stock values (3,1,2,1,3,5,1000.0,1500.0,'2023-11-19',null);
insert into stock values (4,1,2,1,3,6,1000.0,1300.0,'2023-11-19',null);

insert into stock values (5,1,2,3,3,3,1000.0,1500.0,'2023-11-19',1);
insert into stock values (6,1,2,3,3,4,1000.0,1700.0,'2023-11-19',2);
insert into stock values (7,1,2,3,3,5,1000.0,1500.0,'2023-11-19',3);
insert into stock values (8,1,2,3,3,6,1000.0,1300.0,'2023-11-19',4);
--fourniseur 2
insert into stock values (9,2,2,1,3,3,1000.0,1600.0,'2023-11-19',null);
insert into stock values (10,2,2,1,3,4,1000.0,1300.0,'2023-11-19',null);
insert into stock values (11,2,2,1,3,5,1000.0,1800.0,'2023-11-19',null);
insert into stock values (12,2,2,1,3,6,1000.0,1700.0,'2023-11-19',null);

insert into stock values (13,2,2,3,3,3,1000.0,1600.0,'2023-11-19',9);
insert into stock values (14,2,2,3,3,4,1000.0,1300.0,'2023-11-19',10);
insert into stock values (15,2,2,3,3,5,1000.0,1800.0,'2023-11-19',11);
insert into stock values (16,2,2,3,3,6,1000.0,1700.0,'2023-11-19',12);
--fourniseur 3
insert into stock values (17,3,2,1,3,3,1000.0,1650.0,'2023-11-19',null);
insert into stock values (18,3,2,1,3,4,1000.0,1350.0,'2023-11-19',null);
insert into stock values (19,3,2,1,3,5,1000.0,1850.0,'2023-11-19',null);
insert into stock values (20,3,2,1,3,6,1000.0,1750.0,'2023-11-19',null);

insert into stock values (21,3,2,3,3,3,1000.0,1650.0,'2023-11-19',17);
insert into stock values (22,3,2,3,3,4,1000.0,1350.0,'2023-11-19',18);
insert into stock values (23,3,2,3,3,5,1000.0,1850.0,'2023-11-19',19);
insert into stock values (24,3,2,3,3,6,1000.0,1750.0,'2023-11-19',20);

--donner regle
--fourniseur 1
insert into stock values (25,1,1,1,3,3,100.0,500.0,'2023-11-19',null);
insert into stock values (26,1,1,1,3,4,100.0,700.0,'2023-11-19',null);

insert into stock values (27,1,1,3,3,3,100.0,500.0,'2023-11-19',25);
insert into stock values (28,1,1,3,3,4,100.0,700.0,'2023-11-19',26);
--fourniseur 2
insert into stock values (29,2,1,1,3,5,100.0,800.0,'2023-11-19',null);
insert into stock values (30,2,1,1,3,6,100.0,600.0,'2023-11-19',null);

insert into stock values (31,2,1,3,3,3,100.0,600.0,'2023-11-19',29);
insert into stock values (32,2,1,3,3,4,100.0,300.0,'2023-11-19',30);
--fourniseur 3 
insert into stock values (33,3,1,1,3,5,100.0,850.0,'2023-11-19',null);
insert into stock values (34,3,1,1,3,6,100.0,750.0,'2023-11-19',null);

insert into stock values (35,3,1,3,3,3,100.0,650.0,'2023-11-19',33);
insert into stock values (36,3,1,3,3,4,100.0,350.0,'2023-11-19',34);


--donner stylo
insert into stock values (37,1,3,1,3,3,10.0,50.0,'2023-11-19',null);
insert into stock values (38,1,3,1,3,4,10.0,70.0,'2023-11-19',null);

insert into stock values (39,1,3,3,3,3,10.0,50.0,'2023-11-19',37);
insert into stock values (40,1,3,3,3,4,10.0,70.0,'2023-11-19',38);
--fourniseur 2
insert into stock values (41,2,3,1,3,5,10.0,80.0,'2023-11-19',null);
insert into stock values (42,2,3,1,3,6,10.0,60.0,'2023-11-19',null);

insert into stock values (43,2,3,3,3,3,10.0,60.0,'2023-11-19',41);
insert into stock values (44,2,3,3,3,4,10.0,30.0,'2023-11-19',42);
--fourniseur 3 
insert into stock values (45,3,3,1,3,5,10.0,85.0,'2023-11-19',null);
insert into stock values (46,3,3,1,3,6,10.0,75.0,'2023-11-19',null);

insert into stock values (47,3,3,3,3,3,10.0,65.0,'2023-11-19',45);
insert into stock values (48,3,3,3,3,4,10.0,35.0,'2023-11-19',46);


