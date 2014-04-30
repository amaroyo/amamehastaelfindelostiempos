SET GLOBAL max_allowed_packet=1073741824;

use mysales;

alter table ficheros_servicios add column fechaUltimaDescarga DATETIME;
alter table ficheros_servicios add column descripcion TEXT;
alter table ficheros_servicios modify column datos LONGBLOB;


alter table leads add column usuariosPotenciales VARCHAR(100);
alter table leads add column usuarios VARCHAR(300);

-- 21 de septiembre de 2012 --

CREATE TABLE IF NOT EXISTS servicios_usuarios (
  id_servicioUsuario	INT(3) NOT NULL auto_increment,
  id_usuario			INT(3) NOT NULL,  
  id_servicio			INT(3) NOT NULL,
  PRIMARY KEY (id_servicioUsuario))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

insert into grupos (id_grupo, nombre) values (5, 'Supplier');

CREATE TABLE IF NOT EXISTS logs_usuarios (
  id_logUsuario		INT(3) NOT NULL auto_increment,
  id_usuario		INT(3) NOT NULL,  
  fecha  			DATETIME,
  PRIMARY KEY (id_logUsuario))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

insert into permisos (id_permiso, nombre) values (34, 'View users');
insert into permisos (id_permiso, nombre) values (35, 'Add user');
insert into permisos (id_permiso, nombre) values (36, 'Modify user');
insert into permisos (id_permiso, nombre) values (37, 'Delete user');
insert into permisos (id_permiso, nombre) values (38, 'Export sales reps.');
insert into permisos (id_permiso, nombre) values (39, 'Export responsibles');
insert into permisos (id_permiso, nombre) values (40, 'Export distributors');
insert into permisos (id_permiso, nombre) values (41, 'Export companies');
insert into permisos (id_permiso, nombre) values (42, 'Export services');
insert into permisos (id_permiso, nombre) values (43, 'Export channels');



-- 10de noviembre de 2012 --


CREATE TABLE IF NOT EXISTS ficheros_leads (
  id_fichero			INT(10) NOT NULL auto_increment,
  id_lead				INT(10) NOT NULL,
  nombre				VARCHAR(100) NOT NULL,
  tipoContenido			VARCHAR(100),
  datos					LONGBLOB,
  fechaUltimaDescarga	DATETIME,
  descripcion 			TEXT,
  PRIMARY KEY (id_fichero),
  FOREIGN KEY (id_lead) REFERENCES leads (id_lead))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;



-- 26 de enero de 2013 --

alter table empresas add column direccion VARCHAR(100);