SELECT * FROM referencias.revisiones;
delete from referencias.revisiones;

insert into referencias.revisiones(componente,tiempo,km,inspeccion) values(
'Aceite del motor',6,10000,'Reemplazo'),
('Filtro de aceite',6,10000,'Reemplazo'),
('Frenos',12,20000,'Inspeccion');