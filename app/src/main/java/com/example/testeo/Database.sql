create schema referencias;
CREATE schema app;
use app;

-- Creamos la tabla revisiones
-- la tabla revisiones sera usada unicamente como fuente de datos de distintos componentes a modo de referencia para obtener los datos personalizados de cada componente en la app
CREATE TABLE referencias.revisiones(marca VARCHAR(30),modelo VARCHAR(30),componente VARCHAR(50),tiempo int,km int,inspeccion VARCHAR(50) DEFAULT 'Reemplazo');



-- usuarios sera la tabla donde colocaremos la BD de usuarios existentes en la app
CREATE TABLE usuarios(id INT PRIMARY KEY AUTO_INCREMENT,email VARCHAR(40) NOT NULL,password varchar(20) NOT NULL,nombre VARCHAR(60) NOT NULL);


-- la tabla coches contendra los distintos vehiculos que poseera cada usuario
create table coches (marca VARCHAR(30),modelo VARCHAR(30),matricula varchar(10) PRIMARY KEY NOT NULL,año_matriculacion YEAR,owner_name varchar(60),owner_id int,km INT DEFAULT 0,CONSTRAINT fk_id FOREIGN KEY (owner_id) REFERENCES usuarios(id));
--ALTER TABLE coches DROP CONSTRAINT coches_ibfk_1;ALTER TABLE COCHES ADD CONSTRAINT fk_id FOREIGN KEY (owner_id) REFERENCES usuarios(id);*/


CREATE TABLE componentes(matricula VARCHAR(10),componente VARCHAR(60),tiempo int,tiempo_revsion int,km int,km_revision int,CONSTRAINT fk_matricula FOREIGN KEY (matricula) REFERENCES coches(matricula));
-- ALTER TABLE componentes ADD CONSTRAINT fk_matricula FOREIGN KEY (matricula) REFERENCES coches(matricula);



