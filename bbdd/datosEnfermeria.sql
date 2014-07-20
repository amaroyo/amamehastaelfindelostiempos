use enfermeria;


-- Grupos
insert into grupos (nombre) values ('Super Admin');
insert into grupos (nombre) values ('Coordinador');
insert into grupos (nombre) values ('Profesor');
insert into grupos (nombre) values ('Alumno');
insert into grupos (nombre) values ('Virtual Tour');


-- Usuarios 
-- user/pass: admin@ucm.es/realmadrid
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (1, 'admin@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Admin', 'Ad1', 'Ad2','12544996C' );

-- user/pass: coordinador@ucm.es/realmadrid
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (2, 'coordinador@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Alex', 'Aroyo', '' ,'65344996C' );

-- user/pass: profesor1@ucm.es/realmadrid
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (3, 'profesor1@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Profe1', 'pA1', 'pAA1' ,'65344988X' );

-- user/pass: profesor2@ucm.es/realmadrid
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (3, 'profesor2@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Profe2', 'pA2', 'pAA2' ,'65333988X' );

-- user/pass: alumno1@ucm.es/realmadrid
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (4, 'alumno1@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Alumno1', 'Apellido11', 'Apellido12' ,'12300996C' );

-- user/pass: alumno2@ucm.es/realmadrid
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (4, 'alumno2@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Alumno2', 'Apellido21', 'Apellido22' ,'12345596C' );

-- user/pass: alumno2@ucm.es/realmadrid
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (4, 'alumno3@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Alumno3', 'Apellido31', '' ,'17744996C' );

-- user/pass: profeFantasma/realmadrid
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (3, 'pffff@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Profesor No Asignado', '', '' ,'00000000X' );




-- Permisos
-- De momento a lo fácil esto ya se cambiará
insert into permisos (nombre, descripcion) values ('Todas las acciones permitidas.', 'El Super Admin tendrá todos los privilegios.');
insert into permisos (nombre, descripcion) values ('Todas las acciones permitidas menos editar cursos pasados.', 'El Coordinador podrá actuar como un super admin pero no modificar datos antiguos.');
insert into permisos (nombre, descripcion) values ('Permiso para modificar datos personales de los usuarios.', 'Este permiso lo tendrá el grupo Super Admin y se podrá otorgar a diferentes usuarios.');


/*insert into permisos (nombre, descripicion) values (2, 'View leads');
insert into permisos (nombre, descripicion) values (3, 'Add lead');
insert into permisos (nombre, descripicion) values (4, 'Delete lead');
insert into permisos (nombre, descripicion) values (5, 'Modify lead');
insert into permisos (nombre, descripicion) values (6, 'Import leads');
insert into permisos (nombre, descripicion) values (7, 'Export leads');
insert into permisos (nombre, descripicion) values (8, 'View sales reps.');
insert into permisos (nombre, descripicion) values (9, 'Add sales rep.');
insert into permisos (nombre, descripicion) values (10, 'Delete sales rep.');
insert into permisos (nombre, descripicion) values (11, 'Modify sales rep.');
insert into permisos (nombre, descripicion) values (38, 'Export sales reps.');
insert into permisos (nombre, descripicion) values (12, 'View responsibles');
insert into permisos (nombre, descripicion) values (13, 'Add responsible');
insert into permisos (nombre, descripicion) values (14, 'Delete responsible');
insert into permisos (nombre, descripicion) values (15, 'Modify responsible');
insert into permisos (nombre, descripicion) values (39, 'Export responsibles');
insert into permisos (nombre, descripicion) values (16, 'View distributors');
insert into permisos (nombre, descripicion) values (17, 'Add distributor');
insert into permisos (nombre, descripicion) values (18, 'Delete distributor');
insert into permisos (nombre, descripicion) values (19, 'Modify distributor');
insert into permisos (nombre, descripicion) values (40, 'Export distributors');
insert into permisos (nombre, descripicion) values (20, 'View companies');
insert into permisos (nombre, descripicion) values (21, 'Add company');
insert into permisos (nombre, descripicion) values (22, 'Delete company');
insert into permisos (nombre, descripicion) values (23, 'Modify company');
insert into permisos (nombre, descripicion) values (41, 'Export companies');
insert into permisos (nombre, descripicion) values (24, 'View services');
insert into permisos (nombre, descripicion) values (25, 'Add service');
insert into permisos (nombre, descripicion) values (26, 'Delete service');
insert into permisos (nombre, descripicion) values (27, 'Modify service');
insert into permisos (nombre, descripicion) values (42, 'Export services');
insert into permisos (nombre, descripicion) values (28, 'View channels');
insert into permisos (nombre, descripicion) values (29, 'Add channel');
insert into permisos (nombre, descripicion) values (30, 'Delete channel');
insert into permisos (nombre, descripicion) values (31, 'Modify channel');
insert into permisos (nombre, descripicion) values (43, 'Export channels');
insert into permisos (nombre, descripicion) values (32, 'Lock distributor');
insert into permisos (nombre, descripicion) values (33, 'Unlock distributor');
insert into permisos (nombre, descripicion) values (34, 'View users');
insert into permisos (nombre, descripicion) values (35, 'Add user');
insert into permisos (nombre, descripicion) values (36, 'Modify user');
insert into permisos (nombre, descripicion) values (37, 'Delete user');*/


-- Usuarios Permisos

-- insert into grupos_permisos (id_grupo, id_permiso) values (1, 1);



-- Grupos Permisos

-- Grupo Super Admin : Todas las acciones permitidas
insert into grupos_permisos (id_grupo, id_permiso) values (1, 1);
-- Permiso para modificar los datos de cualquier usuario
insert into grupos_permisos (id_grupo, id_permiso) values (1, 3);


-- Grupo Coordinador : Todas las acciones permitidas menos editar datos antiguos
insert into grupos_permisos (id_grupo, id_permiso) values (2, 2);



-- Asignaturas
insert into asignaturas (nombre, codigo, curso, descripcion) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA: CUIDADOS BÁSICOS', '801148', 1, 'Esta asignatura aportará los conocimientos básicos y las habilidades y destrezas necesarias para el manejo de las intervenciones y actividades, relacionadas con los cuidados, que se corresponden con las necesidades básicas del ser humano y sus posibles alteraciones.\n\nLa planificación de dichos cuidados tendrá como referencia la metodología de la Guía de la Práctica. Así mismo se abordarán los aspectos básicos relativos al conocimiento de las unidades de prácticas, trabajo en equipo, comunicación y relaciones interpersonales.');

insert into asignaturas (nombre, codigo, curso, descripcion) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA: METODOLOGÍA ENFERMERA', '801149', 1, 'Esta asignatura facilitará la integración  de la metodología enfermera en la práctica preprofesional, como método que permite identificar los fenómenos enfermeros, establecer juicios clínicos, planificar, aplicar y evaluar los cuidados.\n\nLa planificación de dichos cuidados tendrá como base la valoración integral de la persona, los diagnósticos de enfermería y las intervenciones necesarias para tratar dichos diagnósticos o para abordar procedimientos, tratamientos o técnicas básicas de cuidados.');

insert into asignaturas (nombre, codigo, curso, descripcion) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA I', '801150', 2, '');

insert into asignaturas (nombre, codigo, curso) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA II: ATENCIÓN ESPECIALIZADA', '801151', 2);

insert into asignaturas (nombre, codigo, curso) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA II: ATENCIÓN PRIMARIA', '801151', 2);

insert into asignaturas (nombre, codigo, curso, descripcion) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA III', '801152', 4, 'Esta asignatura aportará los conocimientos, habilidades y destrezas necesarias para abordar los aspectos relacionados con la atención enfermera en pacientes ingresados en unidades hospitalarias especializadas y de educación para la salud en el contexto de la Atención Primaria. De igual manera tienen cabida aspectos más concretos y específicos en la atención a pacientes ancianos, pacientes con problemas de salud mental y/o adicciones, o que por su enfermedad están incluidos en programas de cuidados paliativos y/o  de tratamiento del dolor, bien sea en el ámbito de Atención Hospitalaria o de Atención Primaria.\n\nPara ello se realizara una valoración estandarizada de enfermería, siguiendo el esquema de los patrones funcionales de salud de Margory Gordon, que le permita al alumno establecer diagnósticos enfermeros (reales y de riesgo de personas, familia y/o grupos), planteando los criterios de resultados a conseguir, definiendo posteriormente las intervenciones enfermeras a realizar para la consecución de tal fin.');

insert into asignaturas (nombre, codigo, curso, descripcion) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA IV', '801153', 4, 'Esta asignatura va dirigida a la prestación de cuidados de enfermería  integrales una vez adquiridos en los conocimientos, habilidades y destrezas necesarias para tal fin. Se contemplan todos los aspectos relacionados con la atención enfermera generalista, no especializada, en pacientes ingresados en unidades hospitalarias y/o Atención Primaria.\n\nEn esta asignatura el alumno se integra por completo en la dinámica de trabajo de la unidad y pasa a adquirir más responsabilidades en la atención de los pacientes, siempre bajo la tutela y supervisión directa del enfermero colaborador. Será responsable de toda la atención que precisen los pacientes que le asigne el enfermero colaborador donde el número va a depender tanto de la complejidad de los pacientes como de la progresión en el aprendizaje del alumno. La supervisión directa por parte del enfermero colaborador es condición indispensable, de igual manera que todas las decisiones antes de ser ejecutadas tienen que ser conocidas por el enfermero colaborador.\n\nEn definitiva se pretende que el alumno viva y participe en la realidad. Supone el máximo acercamiento al mundo laboral, desechando el modelo de trabajo de enfermería basado en la tradición, sustituyéndolo por el basado en el conocimiento y la evidencia científica en enfermería.');






-- Seminarios
insert into seminarios_asignaturas (id_asignatura, nombre, codigo, descripcion) values (1, 'LOREM IPSUM 1', 'cod1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis suscipit urna at semper ullamcorper. Suspendisse potenti. Vivamus condimentum sem non nulla molestie, sit amet adipiscing nisl hendrerit. Morbi ac laoreet odio. Vestibulum in erat ligula. Nulla eget condimentum justo, sit amet sollicitudin est. Integer malesuada nunc eget sapien elementum, sed vehicula metus malesuada.');

insert into seminarios_asignaturas (id_asignatura, nombre, codigo, descripcion) values (1, 'LOREM IPSUM 2', 'cod2', 'Etiam porttitor enim ut augue convallis luctus. Donec tempus porta pellentesque. In non ornare nulla. Vestibulum dapibus metus eget erat blandit egestas. Nullam malesuada vehicula turpis at tempor. Curabitur sed mattis est. Ut in vulputate risus. Cras facilisis erat dui, et adipiscing purus ullamcorper id. Etiam et vulputate nibh. Pellentesque venenatis dui tellus, sed cursus elit lobortis ut. Donec id odio aliquet arcu elementum volutpat. Praesent consectetur velit magna, et fringilla massa pellentesque et.');

insert into seminarios_asignaturas (id_asignatura, nombre, codigo, descripcion) values (1, 'LOREM IPSUM 3', 'cod3', '');
insert into seminarios_asignaturas (id_asignatura, nombre, codigo) values (2, 'LOREM IPSUM 4', 'cod4');
insert into seminarios_asignaturas (id_asignatura, nombre, codigo, descripcion) values (3, 'LOREM IPSUM 5', 'cod5', '');
insert into seminarios_asignaturas (id_asignatura, nombre, codigo, descripcion) values (4, 'LOREM IPSUM 6', 'cod6', '');
insert into seminarios_asignaturas (id_asignatura, nombre, codigo, descripcion) values (5, 'LOREM IPSUM 7', 'cod7', '');



-- Rúbricas
insert into rubricas (id_asignatura, competencias, numero_criterios) values (1, 'Consecución en un nivel inicial:\n\n- Sabe prestar una atención sanitaria técnica y profesional adecuada a las necesidades de salud de las personas que atienden\n\n- Conoce el comportamiento interactivo de la persona dentro de su contexto social y multicultural\n\n- Relaciona las intervenciones de enfermería con la evidencia científica y con los medios disponibles\n\n- Comprende sin prejuicios a las personas, considerando sus aspectos físicos, psicológicos y sociales, como individuos autónomos e independientes, asegurando el respeto a sus opiniones, creencias y valores, garantizando el derecho a la intimidad, a través de la confidencialidad y el secreto  profesional\n\n- Favorece el derecho de participación, información, autonomía y el consentimiento informado en la toma de decisiones de las personas atendidas\n\n- Fomenta estilos de vida saludables, el autocuidado, apoyando el mantenimiento de conductas preventivas y terapéuticas\n\n- Protege la salud y el bienestar de las personas, familia o grupos atendidos, garantizando su seguridad\n\n- Sabe realizar una comunicación eficaz con pacientes, familia y compañeros\n\n- Conoce el código ético y deontológico de la enfermería española\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe trabajar con el equipo de profesionales', 19);

insert into rubricas (id_asignatura, competencias, numero_criterios) values (2, '- Es capaz de designar, explicar y aplicar las distintas etapas del proceso de atención de enfermería en la práctica clínica\n\n- Aplica el proceso de enfermería en todos sus términos, garantizando el bienestar y la seguridad de las personas y teniendo en cuenta los aspectos éticos y deontológicos de la profesión\n\n- Pone especial cuidado en la obtención de los datos de valoración de las necesidades de cuidados enfermeros de los individuos, utilizando una metodología propia de la ciencia enfermera', 18);

insert into rubricas (id_asignatura, competencias, numero_criterios) values (3, 'Consecución en un nivel intermedio:\n\n- Reconoce las alteraciones de salud del adulto e identifica las áreas que requieren cuidados enfermeros\n\n- Conoce y aplica los fundamentos y principios teóricos y metodológicos de la enfermería\n\n- Comprende sin prejuicios a las personas, considerando sus aspectos físicos, psicológicos y sociales, como individuos autónomos e independientes, asegurando el respeto a sus opiniones, creencias y valores, garantizando el derecho a la intimidad, a través de la confidencialidad y el secreto profesional\n\n- Realiza cuidados de enfermería dirigidos a las personas que presentan problemas de salud médicos y quirúrgicos, orientados a los resultados en salud evaluando su impacto, a través de guías de práctica clínica y asistencial\n\n- Realiza adecuadamente técnicas y procedimientos enfermeros, entablando una correcta relación terapéutica con las personas o grupos objeto de su atención\n\n- Es capaz de mantener y promover una buena relación con los restantes miembros del equipo asistencial\n\n- Sabe realizar una comunicación eficaz con pacientes, familia y compañeros\n\n- Conoce el código ético y deontológico de la enfermería española\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe trabajar con el equipo de profesionales\n\n', 19);

insert into rubricas (id_asignatura, competencias, numero_criterios) values (4, 'Consecución en un nivel intermedio:\n\n- Aplica en todas sus etapas el proceso de enfermería en el ámbito de los cuidados materno-infantiles\n\n- Aplica con destreza los cuidados integrales a los niños y a las mujeres en las diferentes etapas de su vida\n\n- Protege la salud y el bienestar de las personas, familia o grupos atendidos, garantizando su seguridad\n\n- Sabe realizar una comunicación eficaz con pacientes, familia y compañeros\n\n- Conoce el código ético y deontológico de la enfermería española\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe trabajar con el equipo de profesionales', 19);

insert into rubricas (id_asignatura, competencias, numero_criterios) values (5, 'Consecución en un nivel intermedio:\n\n- Aplica en todas sus etapas el proceso de enfermería en el ámbito de los cuidados comunitarios\n\n- Aplica los cuidados enfermeros adecuados a la realidad de los individuos y grupos que conforman la comunidad\n\n- Describe y comprende los fundamentos de los cuidados en el nivel primario de salud\n\n- Fomenta estilos de vida saludables y el autocuidado, apoyando el mantenimiento de conductas preventivas y terapéuticas\n\n- Protege la salud y el bienestar de las personas, familia o grupos atendidos, garantizando su seguridad\n\n- Sabe realizar una comunicación eficaz con pacientes, familia y compañeros\n\n- Conoce el código ético y deontológico de la enfermería española\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe trabajar con el equipo de profesionales ', 20);

insert into rubricas (id_asignatura, competencias, numero_criterios) values (6, '- Sabe prestar una atención sanitaria técnica y profesional adecuada a las necesidades de salud de las personas que atienden\n\n- Realiza cuidados de enfermería dirigidos a las personas, familia o grupos, orientados a los resultados en salud\n\n- Desarrolla sistemas de cuidados dirigidos a las personas, familia o grupos, evaluando su impacto y estableciendo las modificaciones oportunas\n\n- Fomenta estilos de vida saludables, el autocuidado, apoyando el mantenimiento de conductas preventivas y terapéuticas\n\n- Protege la salud y el bienestar de las personas, familia o grupos atendidos, garantizando su seguridad\n\n- Sabe realizar una comunicación eficaz con pacientes, familia, grupos sociales y compañeros y fomentar la educación para la salud\n\n- Sabe trabajar con el equipo de profesionales\n\n- Realiza los cuidados de enfermería basándose en la atención integral de salud, que supone la cooperación multiprofesional, la integración de los procesos y la continuidad asistencial\n\n- Conoce las estrategias para adoptar medidas de confortabilidad y atención de síntomas, dirigidas al paciente y familia, en la aplicación de cuidados paliativos que contribuyan a aliviar la situación de enfermos avanzados y terminales', 24);

insert into rubricas (id_asignatura, competencias, numero_criterios) values (7, '-Sabe prestar una atención sanitaria técnica y profesional adecuada a las necesidades de salud de las personas que atienden\n\n- Realiza cuidados de enfermería dirigidos a las personas, familia o grupos, orientados a los resultados en salud\n\n- Relaciona las intervenciones de Enfermería con la evidencia científica y los medios disponibles\n\n- Comprende sin prejuicios a las personas, considerando sus aspectos físicos, psicológicos y sociales como individuos autónomos e independientes\n\n- Favorece el derecho de participación, información, autonomía y consentimiento informado en la toma de decisiones de las personas atendidas\n\n- Conoce el código ético y deontológico, comprendiendo las implicaciones éticas de la salud\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe realizar una comunicación eficaz con pacientes, familia, grupos sociales y compañeros y fomentar la educación para la salud\n\n- Sabe trabajar con el equipo de profesionales\n\n- Realiza los cuidados de enfermería basándose en la atención integral de salud, que supone la cooperación multiprofesional, la integración de los procesos y la continuidad asistencial\n\n- Comprende sin prejuicios a las personas, considerando sus aspectos físicos, psicológicos y sociales, como individuos autónomos e independientes, asegurando el respeto a sus opiniones, creencias y valores, garantizando el derecho a la intimidad, a través de la confidencialidad y el secreto profesional', 24);


-- Grupos Criterios Rúbricas
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (1, 'CONOCIMIENTOS (SABE-SABE CÓMO)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (1, 'HABILIDADES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (1, 'ACTITUDES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (2, 'CONOCIMIENTOS (SABE-SABE CÓMO)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (2, 'HABILIDADES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (2, 'ACTITUDES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (3, 'CONOCIMIENTOS (SABE-SABE CÓMO)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (3, 'HABILIDADES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (3, 'ACTITUDES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (4, 'CONOCIMIENTOS (SABE-SABE CÓMO)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (4, 'HABILIDADES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (4, 'ACTITUDES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (5, 'CONOCIMIENTOS (SABE-SABE CÓMO)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (5, 'HABILIDADES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (5, 'ACTITUDES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (6, 'CONOCIMIENTOS (SABE-SABE CÓMO)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (6, 'HABILIDADES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (6, 'ACTITUDES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (7, 'CONOCIMIENTOS (SABE-SABE CÓMO)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (7, 'HABILIDADES (MUESTRA CÓMO-HACE)');
insert into grupos_criterios_rubricas (id_asignatura, nombre) values (7, 'ACTITUDES (MUESTRA CÓMO-HACE)');


-- Criterios Rúbricas

-- #Practicas Clinicas de enfermeria - Cuidados Básicos

-- -Grupo Conocimientos Sabe Sabe como
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 1, 'Sabe como prestar cuidados básicos');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 1, 'Identifica y controla los posibles riesgos en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 1, 'Conoce los procedimientos y técnicas básicas de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 1, 'Sabe como interpretar con precisión los datos objetivos y subjetivos y su importancia para la prestación segura de los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 1, 'Conoce como establecer prioridades en su trabajo y gestionar el tiempo eficazmente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 1, 'Sabe proporcionar la información de manera clara y sucinta');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 1, 'Conoce la estructura y funcionamiento de la unidad de prácticas');

-- -Grupo Habilidades Muestra Como hace 
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 2, 'Realiza cuidados básicos');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 2, 'Demuestra habilidad y destreza');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 2, 'Realiza las técnicas y procedimientos correspondientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 2, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 2, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 2, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y equipo multidisciplinar');

-- -Grupo Actitudes Muestra Como Hace
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 3, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 3, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 3, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 3, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 3, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 3, 'Saber estar');


-- #Practicas Clinicas de enfermeria - Metodologia Enfermera

-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 1, 'Sabe resolver problemas aplicando el pensamiento crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 1, 'Sabe aplicar el juicio clínico en la toma de decisiones');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 1, 'Dispone de los conocimientos para utilizar la metodología adecuada');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 1, 'Sabe como obtener e interpretar con precisión los datos');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 1, 'Conoce como establecer prioridades en la planificación de los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 1, 'Sabe transmitir la información de forma clara y precisa');

-- -Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 2, 'Realiza una valoración de enfermería pertinente y sistemática');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 2, 'Analiza los datos recogidos en la valoración identificando los diagnósticos enermeros');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 2, 'Identifica los resultados previstos');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 2, 'Planifica y aplica las intervenciones adeciadas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 2, 'Evalua los planes de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 2, 'Documenta y registra los cuidados');

-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 3, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 3, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 3, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 3, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 3, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 3, 'Saber estar');


-- #Practicas Clinicas de enfermeria I

-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 1, 'Conoce las alteraciones de salud del adulto, identifica sus manifestaciones y las necesidades de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 1, 'Identifica y controla los posibles riesgos en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 1, 'Sabe como planificar los cuidados: analizar los datos, definir los problemas, establecer, ejecutar y evaluar el plan de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 1, 'Sabe como interpretar con precisión los datos objetivos y subjetivos y su importancia para la presteción segura de los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 1, 'Conoce como establecer prioridades en su trabajo y gestionar el tiempo eficazmente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 1, 'Sabe proporcionar la información de manera clara y sucinta');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 1, 'Conoce la estructura y funcionamiento de la unidad de prácticas');

-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 2, 'Realiza los cuidados de enfermería');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 2, 'Demuestra habilidad y destreza');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 2, 'Realiza las técnicas y procedimientos corresponientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 2, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 2, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 2, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');

-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 3, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 3, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 3, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 3, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 3, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 3, 'Saber estar');



-- #Practicas Clinicas de enfermeria II Atención Especializada

-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 1, 'Conoce las alteraciones de la salud de la mujer, identifica sus manifestaciones y las necesidades de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 1, 'Identifica y controla los posibles riesgos en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 1, 'Sabe como planificar y ejecutar los cuidados de la mujer en las diferentes etapas del ciclo reproductivo y en el climaterio');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 1, 'Sabe como planificar y ejecutar los cuidados de Enfermería durante la infancia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 1, 'Conoce como establecer prioridades en su trabajo y gestionar el tiempo eficazmente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 1, 'Sabe proporcionar la información de manera clara y sucinta');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 1, 'Conoce la estructura y funcionamiento de la unidad de prácticas');

-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 2, 'Realiza los cuidados de enfermería');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 2, 'Demuestra habilidad y destreza');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 2, 'Realiza las técnicas y procedimientos corresponientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 2, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 2, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 2, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');

-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 3, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 3, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 3, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 3, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 3, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 3, 'Saber estar');


-- #Practicas Clinicas de enfermeria II Atención Especializada

-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 1, 'Sabe como evaluar el aprendizaje de las prácticas de salud de los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 1, 'Sabe planificar los cuidados en colaboración con los pacientes, y en su caso con el cuidador principal');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 1, 'Sabe identificar los resultados previstos y el tiempo para conseguirlos, en colaboración con los pacientes o el cuidador principal');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 1, 'Propone y colabora en iniciativas de promoción de la salud y de prevención, así como su evaluación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 1, 'Promueve estilos de vida sanos para la persona, la familia y la comunidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 1, 'Conoce como establecer prioridades en su trabajo y gestionar el tiempo eficazmente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 1, 'Sabe proporcionar la información de manera clara y sucinta');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 1, 'Conoce la estructura y funcionamiento de la unidad de prácticas');


-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 2, 'Realiza los cuidados de enfermería');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 2, 'Demuestra habilidad y destreza');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 2, 'Realiza las técnicas y procedimientos corresponientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 2, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 2, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 2, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');


-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 3, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 3, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 3, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 3, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 3, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 3, 'Saber estar');


-- #Practicas Clinicas de enfermeria III 
-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 1, 'Conoce las alteraciones de salud e identifica sus manifestaciones');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 1, 'Conoce las necesidades de cuidado derivadas de los problemas de salud');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 1, 'Dispone de los conocimientos para valorar, diagnosticar, planificar y evaluar');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 1, 'Conoce las técnicas y procedimientos utilizados en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 1, 'Conoce los sistemas de información sanitaria');

-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Realiza una valoración  de enfermería pertinente y sistemática');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Analiza los datos recogidos en la valoración identificando los diagnósticos enfermeros');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Identifica los resultados previstos ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Planifica y aplica las intervenciones adecuadas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Evalúa los planes de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Aplica y realiza las técnicas y procedimientos de forma adecuada');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Responde eficazmente en situaciones imprevistas o ante urgencias ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Identifica comportamientos desafiantes, ansiedad, estrés, depresión e identifica la necesidad de intervención del personal especializado');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Planifica y aplica cuidados para aliviar la situación de los ancianos, enfermos avanzados y terminales');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Identifica las características del proceso de morir y de duelo así como las necesidades y estrategias de intervención mediante cuidados enfermeros');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 2, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');


-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 3, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 3, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 3, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 3, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 3, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 3, 'Saber estar');


-- #Practicas Clinicas de enfermeria IV
-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 1, 'Conoce las alteraciones de salud e identifica sus manifestaciones ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 1, 'Conoce las necesidades de cuidado derivadas de los problemas de salud');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 1, 'Dispone de los conocimientos para valorar, diagnosticar, planificar y evaluar ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 1, 'Conoce las técnicas y procedimientos utilizados en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 1, 'Sabe prestar una atención sanitaria integral, técnica y profesional adecuada que incluya todas las necesidades de salud de las personas que atiende');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 1, 'Conoce todos los aspectos que implica la asistencia a un determinado paciente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 1, 'Conoce los sistemas de información sanitaria');



-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Realiza una valoración  de enfermería pertinente y sistemática');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Analiza los datos recogidos en la valoración identificando los diagnósticos enfermeros');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Identifica los resultados previstos ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Planifica y aplica las intervenciones adecuadas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Evalúa los planes de cuidados, su ejecución y sus resultados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Aplica y realiza las técnicas y procedimientos de forma adecuada');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Planifica y realiza el plan de cuidados considerando todos los aspectos: físicos, psicológicos, sociales, culturales y espirituales');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Realiza los cuidados de enfermería basándose en la atención integral de salud, que supone la cooperación multiprofesional, la integración de los procesos y la continuidad asistencial');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 2, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');


-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 3, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 3, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 3, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 3, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 3, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 3, 'Saber estar');


-- -------------------- FIN ASIGNATURAS------------------------------------------------------



-- Profesores Asociados
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (3, 1, '2012/2013', 'HUGM Hospital Gregorio Marañon', 'M - Mañana');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (3, 2, '2012/2013', '12 de Octubre', 'T - Tarde');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (3, 1, '2013/2014', 'HUGM Hospital Gregorio Marañon', 'M - Mañana');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (3, 2, '2013/2014', '12 de Octubre', 'T - Tarde');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (4, 3, '2013/2014', '12 de Octubre', 'T - Tarde');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (8, 1, '', '', '');

-- Portafolios PROFESOR 8 NO ASIGNADO
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (5, 8, 1, '2012/2013'); -- id1
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (5, 8, 2, '2012/2013'); -- id2

insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (5, 8, 1, '2013/2014'); -- id3
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (5, 8, 2, '2013/2014'); -- id4

insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (6, 3, 2, '2013/2014'); -- id5
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (6, 8, 3, '2013/2014'); -- id6


-- Estancias_unidad_clinica
insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (1,'HUGM Hospital Gregorio Marañon', 'Urgencias', 'M - Mañana', '2012-09-01', '2013-01-31');
insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (2,'12 de Octubre', 'UCI', 'T - Tarde', '2013-02-01', '2013-06-26');

insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (3,'HUGM Hospital Gregorio Marañon', 'Urgencias', 'M - Mañana', '2013-09-01', '2014-01-31');
insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (4,'12 de Octubre', 'UCI', 'T - Tarde', '2013-09-01', '2014-01-31');

insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (5,'12 de Octubre', 'Parto', 'T - Tarde', '2013-09-01', '2014-01-31');
insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (6,'12 de Octubre', 'AA', 'T - Tarde', '2013-09-01', '2014-01-31');



-- Trabajos de Campo
insert into trabajos_de_campo (id_portafolio, trabajo_de_campo, correccion_trabajo, fecha_limite) values (1, null, null, '2012-10-15');
insert into trabajos_de_campo (id_portafolio, trabajo_de_campo, correccion_trabajo, fecha_limite) values (2, null, null, '2012-10-05');
insert into trabajos_de_campo (id_portafolio, trabajo_de_campo, correccion_trabajo, fecha_limite) values (3, null, null, '2013-10-13');
insert into trabajos_de_campo (id_portafolio, trabajo_de_campo, correccion_trabajo, fecha_limite) values (4, null, null, '2012-10-15');
insert into trabajos_de_campo (id_portafolio, trabajo_de_campo, correccion_trabajo, fecha_limite) values (5, null, null, '2012-10-05');
insert into trabajos_de_campo (id_portafolio, trabajo_de_campo, correccion_trabajo, fecha_limite) values (6, null, null, '2013-10-13');

-- Seminarios Realizados
insert into seminarios_realizados (id_portafolio, id_seminario) values (1,1);
insert into seminarios_realizados (id_portafolio, id_seminario) values (2,4);
insert into seminarios_realizados (id_portafolio, id_seminario) values (3,2);
insert into seminarios_realizados (id_portafolio, id_seminario) values (3,3);
insert into seminarios_realizados (id_portafolio, id_seminario) values (5,4);
insert into seminarios_realizados (id_portafolio, id_seminario) values (6,5);



-- Diarios Reflexivos
insert into diarios_reflexivos (id_portafolio, diario_relfexivo) values (1, null);
insert into diarios_reflexivos (id_portafolio, diario_relfexivo) values (2, null);
insert into diarios_reflexivos (id_portafolio, diario_relfexivo) values (3, null);


-- Anexos
insert into anexos (id_portafolio, anexo) values (1, null);
insert into anexos (id_portafolio, anexo) values (2, null);
insert into anexos (id_portafolio, anexo) values (2, null);
insert into anexos (id_portafolio, anexo) values (3, null);
insert into anexos (id_portafolio, anexo) values (3, null);
insert into anexos (id_portafolio, anexo) values (3, null);

-- Casos Clinicos
insert into casos_clinicos (id_portafolio, caso_clinico) values (1, null);
insert into casos_clinicos (id_portafolio, caso_clinico) values (2, null);
insert into casos_clinicos (id_portafolio, caso_clinico) values (3, null);
insert into casos_clinicos (id_portafolio, caso_clinico) values (3, null);	
	

-- Puntuacion Criterios 
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (1, 1, 2);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (1, 2, 1);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (1, 3, 0);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (1, 4, 0);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (1, 5, 0);

insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 20, 3);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 21, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 22, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 23, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 25, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 26, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 27, 3);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 28, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 29, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 30, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 31, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 32, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 33, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 34, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 35, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 36, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 37, 2);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (2, 38, 1);


insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 1, 3);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 2, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 3, 3);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 4, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 5, 3);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 6, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 7, 3);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 8, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 9, 3);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 10, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 11, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 12, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 13, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 14, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 15, 5);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 16, 4);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 17, 3);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 18, 1);
insert into puntuacion_cirterios (id_portafolio, id_criterio, nota) values (3, 19, 1);
