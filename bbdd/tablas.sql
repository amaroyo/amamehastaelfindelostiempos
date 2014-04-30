use mysales;

drop table IF EXISTS leads;
drop table IF EXISTS ficheros_servicios;
drop table IF EXISTS ficheros_leads;
drop table IF EXISTS servicios_usuarios;
drop table IF EXISTS servicios;
drop table IF EXISTS estados;
drop table IF EXISTS responsables_comerciales;
drop table IF EXISTS comerciales;
drop table IF EXISTS responsables;
drop table IF EXISTS empresas;
drop table IF EXISTS marketing_activities;
drop table IF EXISTS contactos_distribuidores;
drop table IF EXISTS distribuidores;
drop table IF EXISTS actions;
drop table IF EXISTS leads_actions_history;
drop table IF EXISTS contactos_canales;
drop table IF EXISTS canales;
drop table IF EXISTS logs_usuarios;
drop table IF EXISTS usuarios;
drop table IF EXISTS permisos_grupos;
drop table IF EXISTS leads_history;
drop table IF EXISTS permisos;
drop table IF EXISTS grupos;


CREATE TABLE IF NOT EXISTS canales (
  id_canal 					INT(10) NOT NULL auto_increment,  
  nombre		 			VARCHAR(100),
  telefono					VARCHAR(15),
  telefonoMovil				VARCHAR(15),
  direccion					VARCHAR(100),
  codigoPostal				VARCHAR(10),
  ciudad					VARCHAR(100),
  pais						VARCHAR(100),
  email						VARCHAR(100),
  direccionFacturacion		VARCHAR(100),
  codigoPostalFacturacion	VARCHAR(10),
  ciudadFacturacion			VARCHAR(100),
  paisFacturacion			VARCHAR(100),
  nombreFacturacion 		VARCHAR(100),
  telefonoFacturacion		VARCHAR(15),
  telefonoMovilFacturacion	VARCHAR(15),
  emailFacturacion			VARCHAR(100),
  direccionWeb				VARCHAR(100),
  mrmResponsable			VARCHAR(100),
  PRIMARY KEY (id_canal))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS contactos_canales (
  id_contacto		INT(10) NOT NULL auto_increment,
  id_canal			INT(10) NOT NULL,  
  nombre			VARCHAR(100) NOT NULL,
  telefono			VARCHAR(15),
  telefonoMovil		VARCHAR(15),
  direccion			VARCHAR(100),
  codigoPostal		VARCHAR(10),
  ciudad			VARCHAR(100),
  pais				VARCHAR(100),
  email				VARCHAR(100),
  cargo				VARCHAR(100),
  comentarios		TEXT,
  PRIMARY KEY (id_contacto),
  FOREIGN KEY (id_canal) REFERENCES canales (id_canal))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS distribuidores (
  id_distribuidor 			INT(10) NOT NULL auto_increment,
  id_canal		 			INT(10) NOT NULL,
  nombre		 			VARCHAR(100),
  telefono					VARCHAR(15),
  telefonoMovil				VARCHAR(15),
  direccion					VARCHAR(100),
  codigoPostal				VARCHAR(10),
  ciudad					VARCHAR(100),
  pais						VARCHAR(100),
  email						VARCHAR(100),
  direccionFacturacion		VARCHAR(100),
  codigoPostalFacturacion	VARCHAR(10),
  ciudadFacturacion			VARCHAR(100),
  paisFacturacion			VARCHAR(100),
  nombreFacturacion 		VARCHAR(100),
  telefonoFacturacion		VARCHAR(15),
  telefonoMovilFacturacion	VARCHAR(15),
  emailFacturacion			VARCHAR(100),
  bloqueado					VARCHAR(3) DEFAULT 'NO',
  PRIMARY KEY (id_distribuidor))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS contactos_distribuidores (
  id_contacto		INT(10) NOT NULL auto_increment,
  id_distribuidor	INT(10) NOT NULL,  
  nombre			VARCHAR(100) NOT NULL,
  telefono			VARCHAR(15),
  telefonoMovil		VARCHAR(15),
  direccion			VARCHAR(100),
  codigoPostal		VARCHAR(10),
  ciudad			VARCHAR(100),
  pais				VARCHAR(100),
  email				VARCHAR(100),
  cargo				VARCHAR(100),
  comentarios		TEXT,
  PRIMARY KEY (id_contacto),
  FOREIGN KEY (id_distribuidor) REFERENCES distribuidores (id_distribuidor))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS servicios (
  id_servicio		INT(3) NOT NULL auto_increment,  
  nombre			VARCHAR(100) NOT NULL,
  personaContacto	VARCHAR(100),
  proveedor			VARCHAR(100),
  PRIMARY KEY (id_servicio))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS ficheros_servicios (
  id_fichero			INT(10) NOT NULL auto_increment,
  id_servicio			INT(10) NOT NULL,
  nombre				VARCHAR(100) NOT NULL,
  tipoContenido			VARCHAR(100),
  datos					LONGBLOB,
  fechaUltimaDescarga	DATETIME,
  descripcion 			TEXT,
  PRIMARY KEY (id_fichero),
  FOREIGN KEY (id_servicio) REFERENCES servicios (id_servicio))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS actions (
  id_action INT(3) NOT NULL,  
  nombre 	VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_action))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS marketing_activities (
  id_marketing_activity  INT(3) NOT NULL,  
  nombre 	VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_marketing_activity))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS estados (
  id_estado INT(3) NOT NULL auto_increment,  
  nombre 	VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_estado))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;



CREATE TABLE IF NOT EXISTS comerciales (
  id_comercial 		INT(10) NOT NULL auto_increment, 
  id_distribuidor	INT(10) NOT NULL, 
  id_canal 			INT(10) NOT NULL, 
  nombre			VARCHAR(100) NOT NULL,
  telefono			VARCHAR(15),
  telefonoMovil		VARCHAR(15),
  direccion			VARCHAR(100),
  codigoPostal		VARCHAR(10),
  ciudad			VARCHAR(100),
  pais				VARCHAR(100),
  email				VARCHAR(100),
  comentarios		TEXT,
  PRIMARY KEY (id_comercial))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS responsables (
  id_responsable	INT(10) NOT NULL auto_increment,  
  nombre			VARCHAR(100) NOT NULL,
  telefono			VARCHAR(15),
  telefonoMovil		VARCHAR(15),
  direccion			VARCHAR(100),
  codigoPostal		VARCHAR(10),
  ciudad			VARCHAR(100),
  pais				VARCHAR(100),
  email				VARCHAR(100),
  comentarios		TEXT,
  PRIMARY KEY (id_responsable))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS responsables_comerciales (
  id_responsable INT(10) NOT NULL auto_increment,  
  id_comercial   INT(10) NOT NULL,
  PRIMARY KEY (id_responsable,id_comercial))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS empresas (
  id_empresa					INT(10) NOT NULL auto_increment,  
  nombre  		 				VARCHAR(100) NOT NULL,
  orgn							VARCHAR(100),
  telefono						VARCHAR(15),
  telefonoMovil					VARCHAR(15),
  email							VARCHAR(100),
  nombre_persona_contacto		VARCHAR(255),
  direccion						VARCHAR(100),
  PRIMARY KEY (id_empresa),
  UNIQUE(orgn))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS leads (
  id_lead 		INT(10) NOT NULL auto_increment,    
  fecha_visita  DATETIME,
  COMENTARIOS   TEXT,
  fecha_creacion	datetime,
  id_estado   		INT(3),
  id_servicio   		INT(3),
  id_comercial   	INT(10),
  id_responsable   	INT(10),
  id_empresa		   	INT(10),  
  id_marketing_activity INT(3),
  id_distribuidor 		INT(10),
  id_canal 		INT(10),
  usuariosPotenciales		VARCHAR(100),
  usuarios					VARCHAR(300),
  PRIMARY KEY (id_lead),
  FOREIGN KEY (id_distribuidor) REFERENCES distribuidores (id_distribuidor),
  FOREIGN KEY (id_servicio) REFERENCES servicios (id_servicio),
  FOREIGN KEY (id_comercial) REFERENCES comerciales (id_comercial),
  FOREIGN KEY (id_responsable) REFERENCES responsables (id_responsable),
  FOREIGN KEY (id_empresa) REFERENCES empresas (id_empresa),
  FOREIGN KEY (id_canal) REFERENCES canales (id_canal)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS leads_actions_history (
  id_action_history INT(10) NOT NULL auto_increment,
  id_lead   INT(10) NOT NULL,
  id_action	INT(3) NOT NULL,
  comments 	VARCHAR(100),
  date		datetime,
  PRIMARY KEY (id_action_history))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS leads_history (
  id_history 		INT(10) NOT NULL auto_increment,
  id_lead   		INT(10) NOT NULL,
  tipo  		 	VARCHAR(100),
  fecha  			DATETIME,
  campo  		 	VARCHAR(100),
  valorAnterior  	TEXT,
  valorPosterior 	TEXT,
  PRIMARY KEY (id_history)
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;






CREATE TABLE IF NOT EXISTS grupos (
  id_grupo 		INT(3) NOT NULL auto_increment,  
  nombre 		VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_grupo))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS permisos (
  id_permiso	INT(3) NOT NULL auto_increment,  
  nombre 		VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_permiso))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS permisos_grupos (
  id_permisoGrupo	INT(3) NOT NULL auto_increment,
  id_grupo			INT(3) NOT NULL,  
  id_permiso		INT(3) NOT NULL,
  PRIMARY KEY (id_permisoGrupo))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS usuarios (
  id_usuario		INT(3) NOT NULL auto_increment, 
  id_grupo			INT(3) NOT NULL,
  id_asociado		INT(3) NOT NULL,
  nombre 			VARCHAR(100),
  telefono			VARCHAR(15),
  telefonoMovil		VARCHAR(15),
  direccion			VARCHAR(100),
  codigoPostal		VARCHAR(10),
  ciudad			VARCHAR(100),
  pais				VARCHAR(100),
  email				VARCHAR(100),
  comentarios		TEXT,
  user 				VARCHAR(100) NOT NULL,
  pass 				VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_usuario),
  UNIQUE(user))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;



CREATE TABLE IF NOT EXISTS servicios_usuarios (
  id_servicioUsuario	INT(3) NOT NULL auto_increment,
  id_usuario			INT(3) NOT NULL,  
  id_servicio			INT(3) NOT NULL,
  PRIMARY KEY (id_servicioUsuario))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


CREATE TABLE IF NOT EXISTS logs_usuarios (
  id_logUsuario		INT(3) NOT NULL auto_increment,
  id_usuario		INT(3) NOT NULL,  
  fecha  			DATETIME,
  PRIMARY KEY (id_logUsuario))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


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