use enfermeria;


-- Grupos
insert into grupos (nombre) values ('Super Admin');
insert into grupos (nombre) values ('Coordinador');
insert into grupos (nombre) values ('Profesor');
insert into grupos (nombre) values ('Alumno');
insert into grupos (nombre) values ('Virtual Tour');
insert into grupos (nombre) values ('Indefinido');


-- Usuarios 

-- user/pass: profeFantasma/realmadrid ****************** NO ELIMINAR!***************************************************
insert into usuarios (id_grupo, correo, contrasenya, nombre, apellido1, apellido2, dni) values (3, 'pffff@ucm.es', 'e8c522a4c9bdc5ea1f7a0483d965e196','Profesor No Asignado', '', '' ,'00000000X' );
-- **********************************************************************************************************************

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






-- Permisos
insert into permisos (nombre, descripcion) values ('Todas las acciones permitidas.', 'Sólo para el súper admin.');
insert into permisos (nombre, descripcion) values ('Permisos de coordinador.', 'Todas las acciones permitidas del súper admin menos editar años pasados.');
insert into permisos (nombre, descripcion) values ('Permisos de profesor.', 'Todas las acciones permitidas del coordinador menos ver cursos, administrar, grupos, usuarios y años pasados.');

insert into permisos (nombre, descripcion) values ('Editar años pasados.', 'En principio sólo puede el súper admin.');
insert into permisos (nombre, descripcion) values ('Ver años pasados.', 'En principio sólo puede el súper admin y el coordinador.');
insert into permisos (nombre, descripcion) values ('Ver cursos.', 'En principio sólo puede el súper admin y el coordinador.');
insert into permisos (nombre, descripcion) values ('Ver administrar.', 'En principio sólo puede el súper admin y el coordinador.');
insert into permisos (nombre, descripcion) values ('Ver grupos.', 'En principio sólo puede el súper admin y el coordinador.');
insert into permisos (nombre, descripcion) values ('Ver usuarios.', 'En principio sólo puede el súper admin y el coordinador.');
insert into permisos (nombre, descripcion) values ('Ver años pasados.', 'En principio sólo puede el súper admin y el coordinador.');
insert into permisos (nombre, descripcion) values ('Editar nombre, apellidos del perfil propio.', 'En principio sólo puede el súper admin, el coordinador y el profesor.');
insert into permisos (nombre, descripcion) values ('Editar dni del perfil propio.', 'En principio sólo puede el súper admin, el coordinador y el profesor.');
insert into permisos (nombre, descripcion) values ('Ver alumnos.', 'En principio sólo puede el súper admin, el coordinador y el profesor.');
insert into permisos (nombre, descripcion) values ('Modificar información de la asignatura.', 'En principio sólo puede el súper admin y el coordinador.');
insert into permisos (nombre, descripcion) values ('Editar nombre, apellidos del perfil alumno.', 'En principio sólo puede el súper admin, el coordinador y el profesor.');
insert into permisos (nombre, descripcion) values ('Editar dni del perfil alumno.', 'En principio sólo puede el súper admin, el coordinador y el profesor.');
insert into permisos (nombre, descripcion) values ('Editar estancia del alumno.', 'En principio sólo puede el súper admin, el coordinador y el profesor.');
insert into permisos (nombre, descripcion) values ('Editar seminarios', 'Permiso para poder editar la información de un seminario.');
insert into permisos (nombre, descripcion) values ('Editar Estancia Unidad Clinica', 'Permiso para poder editar la Estancia en Unidad Clinica.');
insert into permisos (nombre, descripcion) values ('Crear Trabajo de Campo', 'Permiso para crear Trabajos de Campo y modificar las fechas de entrega.');						





-- Usuarios Permisos




-- Grupos Permisos

-- Grupo Super Admin 1:
-- Todas las acciones permitidas
insert into grupos_permisos (id_grupo, id_permiso) values (1, 1);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 4);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 5);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 6);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 7);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 8);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 9);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 10);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 11);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 12);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 13);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 14);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 15);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 16);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 17);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 18);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 19);
insert into grupos_permisos (id_grupo, id_permiso) values (1, 20);

-- Grupo Coordinador 2:


insert into grupos_permisos (id_grupo, id_permiso) values (2, 2);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 5);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 6);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 7);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 8);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 9);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 10);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 11);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 12);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 13);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 14);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 15);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 16);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 17);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 18);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 19);
insert into grupos_permisos (id_grupo, id_permiso) values (2, 20);

-- Grupo Profesores 3:
insert into grupos_permisos (id_grupo, id_permiso) values (3, 3);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 10);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 11);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 12);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 13);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 14);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 15);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 16);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 17);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 18);
insert into grupos_permisos (id_grupo, id_permiso) values (3, 19);




-- Asignaturas
insert into asignaturas (nombre, codigo, curso, descripcion) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA: CUIDADOS BÁSICOS', '801148', 2, 'Esta asignatura aportará los conocimientos básicos y las habilidades y destrezas necesarias para el manejo de las intervenciones y actividades, relacionadas con los cuidados, que se corresponden con las necesidades básicas del ser humano y sus posibles alteraciones.\n\nLa planificación de dichos cuidados tendrá como referencia la metodología de la Guía de la Práctica. Así mismo se abordarán los aspectos básicos relativos al conocimiento de las unidades de prácticas, trabajo en equipo, comunicación y relaciones interpersonales.');

insert into asignaturas (nombre, codigo, curso, descripcion) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA: METODOLOGÍA ENFERMERA', '801149', 2, 'Esta asignatura facilitará la integración  de la metodología enfermera en la práctica preprofesional, como método que permite identificar los fenómenos enfermeros, establecer juicios clínicos, planificar, aplicar y evaluar los cuidados.\n\nLa planificación de dichos cuidados tendrá como base la valoración integral de la persona, los diagnósticos de enfermería y las intervenciones necesarias para tratar dichos diagnósticos o para abordar procedimientos, tratamientos o técnicas básicas de cuidados.');

insert into asignaturas (nombre, codigo, curso, descripcion) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA I', '801150', 3, '');

insert into asignaturas (nombre, codigo, curso) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA II: ATENCIÓN ESPECIALIZADA', '801151', 3);

insert into asignaturas (nombre, codigo, curso) values ('PRÁCTICAS CLÍNICAS DE ENFERMERÍA II: ATENCIÓN PRIMARIA', '801151', 3);

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
insert into seminarios_asignaturas (id_asignatura, nombre, codigo, descripcion) values (1, 'LOREM IPSUMPrueba', 'cod8', 'pruebaaaa');



-- Rúbricas
insert into rubricas (id_asignatura, competencias, numero_criterios, anexo) values (1, 'Consecución en un nivel inicial:\n\n- Sabe prestar una atención sanitaria técnica y profesional adecuada a las necesidades de salud de las personas que atienden\n\n- Conoce el comportamiento interactivo de la persona dentro de su contexto social y multicultural\n\n- Relaciona las intervenciones de enfermería con la evidencia científica y con los medios disponibles\n\n- Comprende sin prejuicios a las personas, considerando sus aspectos físicos, psicológicos y sociales, como individuos autónomos e independientes, asegurando el respeto a sus opiniones, creencias y valores, garantizando el derecho a la intimidad, a través de la confidencialidad y el secreto  profesional\n\n- Favorece el derecho de participación, información, autonomía y el consentimiento informado en la toma de decisiones de las personas atendidas\n\n- Fomenta estilos de vida saludables, el autocuidado, apoyando el mantenimiento de conductas preventivas y terapéuticas\n\n- Protege la salud y el bienestar de las personas, familia o grupos atendidos, garantizando su seguridad\n\n- Sabe realizar una comunicación eficaz con pacientes, familia y compañeros\n\n- Conoce el código ético y deontológico de la enfermería española\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe trabajar con el equipo de profesionales', 19, 'CUIDADOS BÁSICOS RELACIONADOS CON:');

insert into rubricas (id_asignatura, competencias, numero_criterios, anexo) values (2, 'Consecución en un nivel inicial:\n\n- Es capaz de designar, explicar y aplicar las distintas etapas del proceso de atención de enfermería en la práctica clínica\n\n- Aplica el proceso de enfermería en todos sus términos, garantizando el bienestar y la seguridad de las personas y teniendo en cuenta los aspectos éticos y deontológicos de la profesión\n\n- Pone especial cuidado en la obtención de los datos de valoración de las necesidades de cuidados enfermeros de los individuos, utilizando una metodología propia de la ciencia enfermera', 18, 'DIAGNÓSTICOS ENFERMEROS SOBRE LOS QUE EL ALUMNO HA DEMOSTRADO UN MANEJO ADECUADO:');

insert into rubricas (id_asignatura, competencias, numero_criterios, anexo) values (3, 'Consecución en un nivel intermedio:\n\n- Reconoce las alteraciones de salud del adulto e identifica las áreas que requieren cuidados enfermeros\n\n- Conoce y aplica los fundamentos y principios teóricos y metodológicos de la enfermería\n\n- Comprende sin prejuicios a las personas, considerando sus aspectos físicos, psicológicos y sociales, como individuos autónomos e independientes, asegurando el respeto a sus opiniones, creencias y valores, garantizando el derecho a la intimidad, a través de la confidencialidad y el secreto profesional\n\n- Realiza cuidados de enfermería dirigidos a las personas que presentan problemas de salud médicos y quirúrgicos, orientados a los resultados en salud evaluando su impacto, a través de guías de práctica clínica y asistencial\n\n- Realiza adecuadamente técnicas y procedimientos enfermeros, entablando una correcta relación terapéutica con las personas o grupos objeto de su atención\n\n- Es capaz de mantener y promover una buena relación con los restantes miembros del equipo asistencial\n\n- Sabe realizar una comunicación eficaz con pacientes, familia y compañeros\n\n- Conoce el código ético y deontológico de la enfermería española\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe trabajar con el equipo de profesionales\n\n', 19, 'DIAGNÓSTICOS ENFERMEROS SOBRE LOS QUE EL ALUMNO HA DEMOSTRADO UN MANEJO ADECUADO:');

insert into rubricas (id_asignatura, competencias, numero_criterios, anexo) values (4, 'Consecución en un nivel intermedio:\n\n- Aplica en todas sus etapas el proceso de enfermería en el ámbito de los cuidados materno-infantiles\n\n- Aplica con destreza los cuidados integrales a los niños y a las mujeres en las diferentes etapas de su vida\n\n- Protege la salud y el bienestar de las personas, familia o grupos atendidos, garantizando su seguridad\n\n- Sabe realizar una comunicación eficaz con pacientes, familia y compañeros\n\n- Conoce el código ético y deontológico de la enfermería española\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe trabajar con el equipo de profesionales', 19, 'DIAGNÓSTICOS ENFERMEROS SOBRE LOS QUE EL ALUMNO HA DEMOSTRADO UN MANEJO ADECUADO:');

insert into rubricas (id_asignatura, competencias, numero_criterios, anexo) values (5, 'Consecución en un nivel intermedio:\n\n- Aplica en todas sus etapas el proceso de enfermería en el ámbito de los cuidados comunitarios\n\n- Aplica los cuidados enfermeros adecuados a la realidad de los individuos y grupos que conforman la comunidad\n\n- Describe y comprende los fundamentos de los cuidados en el nivel primario de salud\n\n- Fomenta estilos de vida saludables y el autocuidado, apoyando el mantenimiento de conductas preventivas y terapéuticas\n\n- Protege la salud y el bienestar de las personas, familia o grupos atendidos, garantizando su seguridad\n\n- Sabe realizar una comunicación eficaz con pacientes, familia y compañeros\n\n- Conoce el código ético y deontológico de la enfermería española\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe trabajar con el equipo de profesionales ', 20, 'DIAGNÓSTICOS ENFERMEROS SOBRE LOS QUE EL ALUMNO HA DEMOSTRADO UN MANEJO ADECUADO:');

insert into rubricas (id_asignatura, competencias, numero_criterios, anexo) values (6, 'Consecución en un nivel avanzado:\n\n- Sabe prestar una atención sanitaria técnica y profesional adecuada a las necesidades de salud de las personas que atienden\n\n- Realiza cuidados de enfermería dirigidos a las personas, familia o grupos, orientados a los resultados en salud\n\n- Desarrolla sistemas de cuidados dirigidos a las personas, familia o grupos, evaluando su impacto y estableciendo las modificaciones oportunas\n\n- Fomenta estilos de vida saludables, el autocuidado, apoyando el mantenimiento de conductas preventivas y terapéuticas\n\n- Protege la salud y el bienestar de las personas, familia o grupos atendidos, garantizando su seguridad\n\n- Sabe realizar una comunicación eficaz con pacientes, familia, grupos sociales y compañeros y fomentar la educación para la salud\n\n- Sabe trabajar con el equipo de profesionales\n\n- Realiza los cuidados de enfermería basándose en la atención integral de salud, que supone la cooperación multiprofesional, la integración de los procesos y la continuidad asistencial\n\n- Conoce las estrategias para adoptar medidas de confortabilidad y atención de síntomas, dirigidas al paciente y familia, en la aplicación de cuidados paliativos que contribuyan a aliviar la situación de enfermos avanzados y terminales', 24, 'DIAGNÓSTICOS ENFERMEROS SOBRE LOS QUE EL ALUMNO HA DEMOSTRADO UN MANEJO ADECUADO:');

insert into rubricas (id_asignatura, competencias, numero_criterios, anexo) values (7, 'Consecución en un nivel avanzado:\n\n-Sabe prestar una atención sanitaria técnica y profesional adecuada a las necesidades de salud de las personas que atienden\n\n- Realiza cuidados de enfermería dirigidos a las personas, familia o grupos, orientados a los resultados en salud\n\n- Relaciona las intervenciones de Enfermería con la evidencia científica y los medios disponibles\n\n- Comprende sin prejuicios a las personas, considerando sus aspectos físicos, psicológicos y sociales como individuos autónomos e independientes\n\n- Favorece el derecho de participación, información, autonomía y consentimiento informado en la toma de decisiones de las personas atendidas\n\n- Conoce el código ético y deontológico, comprendiendo las implicaciones éticas de la salud\n\n- Desarrolla mecanismos de evaluación\n\n- Sabe realizar una comunicación eficaz con pacientes, familia, grupos sociales y compañeros y fomentar la educación para la salud\n\n- Sabe trabajar con el equipo de profesionales\n\n- Realiza los cuidados de enfermería basándose en la atención integral de salud, que supone la cooperación multiprofesional, la integración de los procesos y la continuidad asistencial\n\n- Comprende sin prejuicios a las personas, considerando sus aspectos físicos, psicológicos y sociales, como individuos autónomos e independientes, asegurando el respeto a sus opiniones, creencias y valores, garantizando el derecho a la intimidad, a través de la confidencialidad y el secreto profesional', 24, 'DIAGNÓSTICOS ENFERMEROS SOBRE LOS QUE EL ALUMNO HA DEMOSTRADO UN MANEJO ADECUADO:');


-- Grupos Criterios Rúbricas
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'CONOCIMIENTOS (SABE-SABE CÓMO)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'HABILIDADES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'ACTITUDES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'PERCEPCIÓN/MANEJO DE LA SALUD', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'NUTRICIONAL–METABÓLICO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'ELIMINACIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'ACTIVIDAD–EJERCICIO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'DESCANSO–SUEÑO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (1, 'HABILIDADES, DESTREZAS, PROCEDIMIENTOS Y TÉCNICAS BÁSICAS ', 'TEXTO');

insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'CONOCIMIENTOS (SABE-SABE CÓMO)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'HABILIDADES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'ACTITUDES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'PERCEPCIÓN/MANEJO DE LA SALUD', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'NUTRICIONAL–METABÓLICO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'ELIMINACIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'ACTIVIDAD–EJERCICIO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'DESCANSO–SUEÑO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'COGNITIVO–PERCEPTUAL', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'AUTOPERCEPCIÓN-AUTOCONCEPTO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'ROL–RELACIONES', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'SEXUALIDAD–REPRODUCCIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'ADAPTACIÓN–TOLERANCIA AL ESTRÉS', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (2, 'VALORES–CREENCIAS', 'TEXTO');



insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'CONOCIMIENTOS (SABE-SABE CÓMO)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'HABILIDADES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'ACTITUDES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'PERCEPCIÓN/MANEJO DE LA SALUD', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'NUTRICIONAL–METABÓLICO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'ELIMINACIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'ACTIVIDAD–EJERCICIO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'DESCANSO–SUEÑO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'COGNITIVO–PERCEPTUAL', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'AUTOPERCEPCIÓN-AUTOCONCEPTO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'ROL–RELACIONES', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'SEXUALIDAD–REPRODUCCIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'ADAPTACIÓN–TOLERANCIA AL ESTRÉS', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (3, 'VALORES–CREENCIAS', 'TEXTO');

insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'CONOCIMIENTOS (SABE-SABE CÓMO)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'HABILIDADES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'ACTITUDES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'PERCEPCIÓN/MANEJO DE LA SALUD', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'NUTRICIONAL–METABÓLICO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'ELIMINACIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'ACTIVIDAD–EJERCICIO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'DESCANSO–SUEÑO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'COGNITIVO–PERCEPTUAL', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'AUTOPERCEPCIÓN-AUTOCONCEPTO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'ROL–RELACIONES', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'SEXUALIDAD–REPRODUCCIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'ADAPTACIÓN–TOLERANCIA AL ESTRÉS', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (4, 'VALORES–CREENCIAS', 'TEXTO');

insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'CONOCIMIENTOS (SABE-SABE CÓMO)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'HABILIDADES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'ACTITUDES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'PERCEPCIÓN/MANEJO DE LA SALUD', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'NUTRICIONAL–METABÓLICO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'ELIMINACIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'ACTIVIDAD–EJERCICIO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'DESCANSO–SUEÑO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'COGNITIVO–PERCEPTUAL', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'AUTOPERCEPCIÓN-AUTOCONCEPTO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'ROL–RELACIONES', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'SEXUALIDAD–REPRODUCCIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'ADAPTACIÓN–TOLERANCIA AL ESTRÉS', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (5, 'VALORES–CREENCIAS', 'TEXTO');

insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'CONOCIMIENTOS (SABE-SABE CÓMO)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'HABILIDADES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'ACTITUDES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'PERCEPCIÓN/MANEJO DE LA SALUD', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'NUTRICIONAL–METABÓLICO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'ELIMINACIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'ACTIVIDAD–EJERCICIO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'DESCANSO–SUEÑO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'COGNITIVO–PERCEPTUAL', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'AUTOPERCEPCIÓN-AUTOCONCEPTO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'ROL–RELACIONES', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'SEXUALIDAD–REPRODUCCIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'ADAPTACIÓN–TOLERANCIA AL ESTRÉS', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (6, 'VALORES–CREENCIAS', 'TEXTO');

insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'CONOCIMIENTOS (SABE-SABE CÓMO)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'HABILIDADES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'ACTITUDES (MUESTRA CÓMO-HACE)', 'NOTA');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'PERCEPCIÓN/MANEJO DE LA SALUD', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'NUTRICIONAL–METABÓLICO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'ELIMINACIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'ACTIVIDAD–EJERCICIO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'DESCANSO–SUEÑO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'COGNITIVO–PERCEPTUAL', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'AUTOPERCEPCIÓN-AUTOCONCEPTO', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'ROL–RELACIONES', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'SEXUALIDAD–REPRODUCCIÓN', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'ADAPTACIÓN–TOLERANCIA AL ESTRÉS', 'TEXTO');
insert into grupos_criterios_rubricas (id_asignatura, nombre, tipo) values (7, 'VALORES–CREENCIAS', 'TEXTO');


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


-- -Grupo PERCEPCIÓN/MANEJO DE LA SALUD
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 4, 'Identificación y control de riesgos');

-- -Grupo NUTRICIONAL–METABÓLICO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 5, 'Alimentación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 5, 'Temperatura corporal');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 5, 'Piel y mucosas');

-- -Grupo ELIMINACIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 6, 'Intestinal');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 6, 'Urinaria');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 6, 'Otros');

-- -Grupo ACTIVIDAD–EJERCICIO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 7, 'Movilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 7, 'Función cardiorrespiratoria');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 7, 'Autocuidado');

-- -Grupo DESCANSO–SUEÑO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 8, 'Cuidados para conciliar o fomentar el sueño /descanso: confort, bienestar...');

-- -Grupo HABILIDADES, DESTREZAS, PROCEDIMIENTOS Y TÉCNICAS BÁSICAS 
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 9, 'Administración de medicación vía oral');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 9, 'Administración de medicación vía subcutánea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 9, 'Administración de medicación intradérmica');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 9, 'Administración de medicación intramuscular');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 9, 'Administración de medicación rectal');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (1, 9, 'Administración de medicación tópica');


-- #Practicas Clinicas de enfermeria - Metodologia Enfermera

-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 10, 'Sabe resolver problemas aplicando el pensamiento crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 10, 'Sabe aplicar el juicio clínico en la toma de decisiones');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 10, 'Dispone de los conocimientos para utilizar la metodología adecuada');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 10, 'Sabe como obtener e interpretar con precisión los datos');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 10, 'Conoce como establecer prioridades en la planificación de los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 10, 'Sabe transmitir la información de forma clara y precisa');

-- -Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 11, 'Realiza una valoración de enfermería pertinente y sistemática');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 11, 'Analiza los datos recogidos en la valoración identificando los diagnósticos enermeros');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 11, 'Identifica los resultados previstos');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 11, 'Planifica y aplica las intervenciones adeciadas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 11, 'Evalua los planes de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 11, 'Documenta y registra los cuidados');

-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 12, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 12, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 12, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 12, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 12, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 12, 'Saber estar');

-- -Grupo PERCEPCIÓN/MANEJO DE LA SALUD
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 13, 'Riesgo de Infección');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 13, 'Riesgo de caídas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 13, 'Mantenimiento inefectivo de la salud');

-- -Grupo NUTRICIONAL–METABÓLICO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 14, 'Deterioro de la deglución');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 14, 'Deterioro de la integridad cutánea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 14, 'Deterioro de la mucosa oral');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 14, 'Hipertermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 14, 'Hipotermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 14, 'Riesgo de aspiración');

-- -Grupo ELIMINACIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 15, 'Deterioro de la eliminación urinaria');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 15, 'Diarrea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 15, 'Estreñimiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 15, 'Incontinencia fecal');

-- -Grupo ACTIVIDAD–EJERCICIO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 16, 'Déficit de autocuidado: alimentación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 16, 'Déficit de autocuidado: baño-higiene');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 16, 'Déficit de autocuidado: uso del WC');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 16, 'Déficit del autocuidado: vestido-acicalamiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 16, 'Deterioro de la movilidad física');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 16, 'Limpieza ineficaz de las vías aéreas');

-- -Grupo DESCANSO–SUEÑO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 17, 'Deterioro del patrón del sueño');

-- -Grupo COGNITIVO–PERCEPTUAL
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 18, 'Dolor Agudo');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 18, 'Dolor Crónico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 18, 'Conocimientos deficientes');

-- -Grupo AUTOPERCEPCIÓN-AUTOCONCEPTO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 19, 'Temor');

-- -Grupo ROL–RELACIONES
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 20, 'Deterioro de la comunicación verbal');

-- -Grupo SEXUALIDAD–REPRODUCCIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 21, 'Disfunción sexual');

-- -Grupo ADAPTACIÓN–TOLERANCIA AL ESTRÉS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 22, 'Afrontamiento inefectivo');

-- -Grupo VALORES–CREENCIAS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 23, 'Sufrimiento espiritual');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (2, 23, 'Riesgo de sufrimiento espiritual');


-- #Practicas Clinicas de enfermeria I

-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 24, 'Conoce las alteraciones de salud del adulto, identifica sus manifestaciones y las necesidades de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 24, 'Identifica y controla los posibles riesgos en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 24, 'Sabe como planificar los cuidados: analizar los datos, definir los problemas, establecer, ejecutar y evaluar el plan de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 24, 'Sabe como interpretar con precisión los datos objetivos y subjetivos y su importancia para la presteción segura de los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 24, 'Conoce como establecer prioridades en su trabajo y gestionar el tiempo eficazmente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 24, 'Sabe proporcionar la información de manera clara y sucinta');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 24, 'Conoce la estructura y funcionamiento de la unidad de prácticas');

-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 25, 'Realiza los cuidados de enfermería');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 25, 'Demuestra habilidad y destreza');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 25, 'Realiza las técnicas y procedimientos corresponientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 25, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 25, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 25, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');

-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 26, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 26, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 26, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 26, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 26, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 26, 'Saber estar');

-- -Grupo PERCEPCIÓN/MANEJO DE LA SALUD
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 27, 'Riesgo de Infección');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 27, 'Riesgo de caídas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 27, 'Mantenimiento inefectivo de la salud');

-- -Grupo NUTRICIONAL–METABÓLICO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 28, 'Deterioro de la deglución');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 28, 'Deterioro de la integridad cutánea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 28, 'Deterioro de la mucosa oral');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 28, 'Hipertermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 28, 'Hipotermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 28, 'Riesgo de aspiración');

-- -Grupo ELIMINACIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 29, 'Deterioro de la eliminación urinaria');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 29, 'Diarrea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 29, 'Estreñimiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 29, 'Incontinencia fecal');

-- -Grupo ACTIVIDAD–EJERCICIO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 30, 'Déficit de autocuidado: alimentación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 30, 'Déficit de autocuidado: baño-higiene');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 30, 'Déficit de autocuidado: uso del WC');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 30, 'Déficit del autocuidado: vestido-acicalamiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 30, 'Deterioro de la movilidad física');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 30, 'Limpieza ineficaz de las vías aéreas');

-- -Grupo DESCANSO–SUEÑO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 31, 'Deterioro del patrón del sueño');

-- -Grupo COGNITIVO–PERCEPTUAL
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 32, 'Dolor Agudo');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 32, 'Dolor Crónico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 32, 'Conocimientos deficientes');

-- -Grupo AUTOPERCEPCIÓN-AUTOCONCEPTO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 33, 'Temor');

-- -Grupo ROL–RELACIONES
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 34, 'Deterioro de la comunicación verbal');

-- -Grupo SEXUALIDAD–REPRODUCCIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 35, 'Disfunción sexual');

-- -Grupo ADAPTACIÓN–TOLERANCIA AL ESTRÉS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 36, 'Afrontamiento inefectivo');

-- -Grupo VALORES–CREENCIAS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 37, 'Sufrimiento espiritual');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (3, 37, 'Riesgo de sufrimiento espiritual');

-- #Practicas Clinicas de enfermeria II Atención Especializada

-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 38, 'Conoce las alteraciones de la salud de la mujer, identifica sus manifestaciones y las necesidades de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 38, 'Identifica y controla los posibles riesgos en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 38, 'Sabe como planificar y ejecutar los cuidados de la mujer en las diferentes etapas del ciclo reproductivo y en el climaterio');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 38, 'Sabe como planificar y ejecutar los cuidados de Enfermería durante la infancia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 38, 'Conoce como establecer prioridades en su trabajo y gestionar el tiempo eficazmente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 38, 'Sabe proporcionar la información de manera clara y sucinta');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 38, 'Conoce la estructura y funcionamiento de la unidad de prácticas');

-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 39, 'Realiza los cuidados de enfermería');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 39, 'Demuestra habilidad y destreza');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 39, 'Realiza las técnicas y procedimientos corresponientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 39, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 39, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 39, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');

-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 40, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 40, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 40, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 40, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 40, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 40, 'Saber estar');


-- -Grupo PERCEPCIÓN/MANEJO DE LA SALUD
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 41, 'Riesgo de Infección');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 41, 'Riesgo de caídas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 41, 'Mantenimiento inefectivo de la salud');

-- -Grupo NUTRICIONAL–METABÓLICO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 42, 'Deterioro de la deglución');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 42, 'Deterioro de la integridad cutánea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 42, 'Deterioro de la mucosa oral');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 42, 'Hipertermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 42, 'Hipotermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 42, 'Riesgo de aspiración');

-- -Grupo ELIMINACIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 43, 'Deterioro de la eliminación urinaria');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 43, 'Diarrea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 43, 'Estreñimiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 43, 'Incontinencia fecal');

-- -Grupo ACTIVIDAD–EJERCICIO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 44, 'Déficit de autocuidado: alimentación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 44, 'Déficit de autocuidado: baño-higiene');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 44, 'Déficit de autocuidado: uso del WC');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 44, 'Déficit del autocuidado: vestido-acicalamiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 44, 'Deterioro de la movilidad física');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 44, 'Limpieza ineficaz de las vías aéreas');

-- -Grupo DESCANSO–SUEÑO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 45, 'Deterioro del patrón del sueño');

-- -Grupo COGNITIVO–PERCEPTUAL
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 46, 'Dolor Agudo');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 46, 'Dolor Crónico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 46, 'Conocimientos deficientes');

-- -Grupo AUTOPERCEPCIÓN-AUTOCONCEPTO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 47, 'Temor');

-- -Grupo ROL–RELACIONES
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 48, 'Deterioro de la comunicación verbal');

-- -Grupo SEXUALIDAD–REPRODUCCIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 49, 'Disfunción sexual');

-- -Grupo ADAPTACIÓN–TOLERANCIA AL ESTRÉS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 50, 'Afrontamiento inefectivo');

-- -Grupo VALORES–CREENCIAS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 51, 'Sufrimiento espiritual');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (4, 51, 'Riesgo de sufrimiento espiritual');


-- #Practicas Clinicas de enfermeria II Atención Primaria

-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 52, 'Sabe como evaluar el aprendizaje de las prácticas de salud de los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 52, 'Sabe planificar los cuidados en colaboración con los pacientes, y en su caso con el cuidador principal');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 52, 'Sabe identificar los resultados previstos y el tiempo para conseguirlos, en colaboración con los pacientes o el cuidador principal');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 52, 'Propone y colabora en iniciativas de promoción de la salud y de prevención, así como su evaluación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 52, 'Promueve estilos de vida sanos para la persona, la familia y la comunidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 52, 'Conoce como establecer prioridades en su trabajo y gestionar el tiempo eficazmente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 52, 'Sabe proporcionar la información de manera clara y sucinta');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 52, 'Conoce la estructura y funcionamiento de la unidad de prácticas');


-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 53, 'Realiza los cuidados de enfermería');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 53, 'Demuestra habilidad y destreza');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 53, 'Realiza las técnicas y procedimientos corresponientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 53, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 53, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 53, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');


-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 54, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 54, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 54, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 54, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 54, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 54, 'Saber estar');


-- -Grupo PERCEPCIÓN/MANEJO DE LA SALUD
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 55, 'Riesgo de Infección');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 55, 'Riesgo de caídas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 55, 'Mantenimiento inefectivo de la salud');


-- -Grupo NUTRICIONAL–METABÓLICO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 56, 'Deterioro de la deglución');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 56, 'Deterioro de la integridad cutánea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 56, 'Deterioro de la mucosa oral');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 56, 'Hipertermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 56, 'Hipotermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 56, 'Riesgo de aspiración');

-- -Grupo ELIMINACIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 57, 'Deterioro de la eliminación urinaria');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 57, 'Diarrea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 57, 'Estreñimiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 57, 'Incontinencia fecal');


-- -Grupo ACTIVIDAD–EJERCICIO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 58, 'Déficit de autocuidado: alimentación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 58, 'Déficit de autocuidado: baño-higiene');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 58, 'Déficit de autocuidado: uso del WC');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 58, 'Déficit del autocuidado: vestido-acicalamiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 58, 'Deterioro de la movilidad física');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 58, 'Limpieza ineficaz de las vías aéreas');

-- -Grupo DESCANSO–SUEÑO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 59, 'Deterioro del patrón del sueño');

-- -Grupo COGNITIVO–PERCEPTUAL
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 60, 'Dolor Agudo');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 60, 'Dolor Crónico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 60, 'Conocimientos deficientes');

-- -Grupo AUTOPERCEPCIÓN-AUTOCONCEPTO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 61, 'Temor');

-- -Grupo ROL–RELACIONES
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 62, 'Deterioro de la comunicación verbal');

-- -Grupo SEXUALIDAD–REPRODUCCIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 63, 'Disfunción sexual');

-- -Grupo ADAPTACIÓN–TOLERANCIA AL ESTRÉS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 64, 'Afrontamiento inefectivo');

-- -Grupo VALORES–CREENCIAS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 65, 'Sufrimiento espiritual');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (5, 65, 'Riesgo de sufrimiento espiritual');



-- #Practicas Clinicas de enfermeria III 
-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 66, 'Conoce las alteraciones de salud e identifica sus manifestaciones');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 66, 'Conoce las necesidades de cuidado derivadas de los problemas de salud');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 66, 'Dispone de los conocimientos para valorar, diagnosticar, planificar y evaluar');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 66, 'Conoce las técnicas y procedimientos utilizados en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 66, 'Conoce los sistemas de información sanitaria');

-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Realiza una valoración  de enfermería pertinente y sistemática');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Analiza los datos recogidos en la valoración identificando los diagnósticos enfermeros');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Identifica los resultados previstos ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Planifica y aplica las intervenciones adecuadas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Evalúa los planes de cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Aplica y realiza las técnicas y procedimientos de forma adecuada');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Responde eficazmente en situaciones imprevistas o ante urgencias ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Identifica comportamientos desafiantes, ansiedad, estrés, depresión e identifica la necesidad de intervención del personal especializado');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Planifica y aplica cuidados para aliviar la situación de los ancianos, enfermos avanzados y terminales');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Identifica las características del proceso de morir y de duelo así como las necesidades y estrategias de intervención mediante cuidados enfermeros');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 67, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');


-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 68, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 68, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 68, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 68, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 68, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 68, 'Saber estar');



-- -Grupo PERCEPCIÓN/MANEJO DE LA SALUD
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 69, 'Riesgo de Infección');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 69, 'Riesgo de caídas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 69, 'Mantenimiento inefectivo de la salud');


-- -Grupo NUTRICIONAL–METABÓLICO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 70, 'Deterioro de la deglución');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 70, 'Deterioro de la integridad cutánea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 70, 'Deterioro de la mucosa oral');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 70, 'Hipertermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 70, 'Hipotermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 70, 'Riesgo de aspiración');

-- -Grupo ELIMINACIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 71, 'Deterioro de la eliminación urinaria');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 71, 'Diarrea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 71, 'Estreñimiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 71, 'Incontinencia fecal');


-- -Grupo ACTIVIDAD–EJERCICIO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 72, 'Déficit de autocuidado: alimentación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 72, 'Déficit de autocuidado: baño-higiene');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 72, 'Déficit de autocuidado: uso del WC');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 72, 'Déficit del autocuidado: vestido-acicalamiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 72, 'Deterioro de la movilidad física');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 72, 'Limpieza ineficaz de las vías aéreas');

-- -Grupo DESCANSO–SUEÑO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 73, 'Deterioro del patrón del sueño');

-- -Grupo COGNITIVO–PERCEPTUAL
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 74, 'Dolor Agudo');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 74, 'Dolor Crónico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 74, 'Conocimientos deficientes');

-- -Grupo AUTOPERCEPCIÓN-AUTOCONCEPTO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 75, 'Temor');

-- -Grupo ROL–RELACIONES
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 76, 'Deterioro de la comunicación verbal');

-- -Grupo SEXUALIDAD–REPRODUCCIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 77, 'Disfunción sexual');

-- -Grupo ADAPTACIÓN–TOLERANCIA AL ESTRÉS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 78, 'Afrontamiento inefectivo');

-- -Grupo VALORES–CREENCIAS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 79, 'Sufrimiento espiritual');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (6, 79, 'Riesgo de sufrimiento espiritual');



-- #Practicas Clinicas de enfermeria IV
-- -Grupo Conocimientos
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 80, 'Conoce las alteraciones de salud e identifica sus manifestaciones ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 80, 'Conoce las necesidades de cuidado derivadas de los problemas de salud');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 80, 'Dispone de los conocimientos para valorar, diagnosticar, planificar y evaluar ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 80, 'Conoce las técnicas y procedimientos utilizados en la atención a los pacientes');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 80, 'Sabe prestar una atención sanitaria integral, técnica y profesional adecuada que incluya todas las necesidades de salud de las personas que atiende');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 80, 'Conoce todos los aspectos que implica la asistencia a un determinado paciente');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 80, 'Conoce los sistemas de información sanitaria');



-- Grupo Habilidades
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Realiza una valoración  de enfermería pertinente y sistemática');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Analiza los datos recogidos en la valoración identificando los diagnósticos enfermeros');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Identifica los resultados previstos ');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Planifica y aplica las intervenciones adecuadas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Evalúa los planes de cuidados, su ejecución y sus resultados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Aplica y realiza las técnicas y procedimientos de forma adecuada');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Demuestra habilidad en el uso de la comunicación con el paciente y familia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Planifica y realiza el plan de cuidados considerando todos los aspectos: físicos, psicológicos, sociales, culturales y espirituales');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Documenta y registra los cuidados');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Realiza los cuidados de enfermería basándose en la atención integral de salud, que supone la cooperación multiprofesional, la integración de los procesos y la continuidad asistencial');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 81, 'Establece y mantiene relaciones de trabajo constructivas con el equipo de enfermería y el equipo multidisciplinar');


-- -Grupo Actitudes
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 82, 'Juicio crítico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 82, 'Aprendizaje y mejora continua');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 82, 'Responsabilidad');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 82, 'Respeto');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 82, 'Iniciativa');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 82, 'Saber estar');



-- -Grupo PERCEPCIÓN/MANEJO DE LA SALUD
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 83, 'Riesgo de Infección');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 83, 'Riesgo de caídas');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 83, 'Mantenimiento inefectivo de la salud');


-- -Grupo NUTRICIONAL–METABÓLICO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 84, 'Deterioro de la deglución');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 84, 'Deterioro de la integridad cutánea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 84, 'Deterioro de la mucosa oral');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 84, 'Hipertermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 84, 'Hipotermia');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 84, 'Riesgo de aspiración');

-- -Grupo ELIMINACIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 85, 'Deterioro de la eliminación urinaria');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 85, 'Diarrea');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 85, 'Estreñimiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 85, 'Incontinencia fecal');


-- -Grupo ACTIVIDAD–EJERCICIO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 86, 'Déficit de autocuidado: alimentación');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 86, 'Déficit de autocuidado: baño-higiene');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 86, 'Déficit de autocuidado: uso del WC');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 86, 'Déficit del autocuidado: vestido-acicalamiento');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 86, 'Deterioro de la movilidad física');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 86, 'Limpieza ineficaz de las vías aéreas');

-- -Grupo DESCANSO–SUEÑO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 87, 'Deterioro del patrón del sueño');

-- -Grupo COGNITIVO–PERCEPTUAL
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 88, 'Dolor Agudo');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 88, 'Dolor Crónico');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 88, 'Conocimientos deficientes');

-- -Grupo AUTOPERCEPCIÓN-AUTOCONCEPTO
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 89, 'Temor');

-- -Grupo ROL–RELACIONES
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 90, 'Deterioro de la comunicación verbal');

-- -Grupo SEXUALIDAD–REPRODUCCIÓN
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 91, 'Disfunción sexual');

-- -Grupo ADAPTACIÓN–TOLERANCIA AL ESTRÉS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 92, 'Afrontamiento inefectivo');

-- -Grupo VALORES–CREENCIAS
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 93, 'Sufrimiento espiritual');
insert into criterios_rubricas (id_asignatura, id_grupo_criterio, nombre) values (7, 93, 'Riesgo de sufrimiento espiritual');


-- -------------------- FIN ASIGNATURAS------------------------------------------------------



-- Profesores Asociados
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (4, 1, '2012/2013', 'HUGM Hospital Gregorio Marañon', 'M - Mañana');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (4, 2, '2012/2013', '12 de Octubre', 'T - Tarde');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (4, 1, '2013/2014', 'HUGM Hospital Gregorio Marañon', 'M - Mañana');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (4, 2, '2013/2014', '12 de Octubre', 'T - Tarde');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (5, 3, '2013/2014', '12 de Octubre', 'T - Tarde');
insert into profesores_asociados (id_profesor, id_asignatura, anyo_academico, centro_asociado, turno) values (1, 1, '', '', '');

-- Portafolios PROFESOR 1 NO ASIGNADO
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (6, 1, 1, '2012/2013'); -- id1
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (6, 1, 2, '2012/2013'); -- id2

insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (6, 1, 1, '2013/2014'); -- id3
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (6, 1, 2, '2013/2014'); -- id4

insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (7, 4, 2, '2013/2014'); -- id5
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (7, 1, 3, '2013/2014'); -- id6
insert into portafolios (id_alumno, id_profesor, id_asignatura, anyo_academico) values (7, 1, 1, '2012/2013'); -- id7





-- Estancias_unidad_clinica
insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (1,'HUGM Hospital Gregorio Marañon', 'Urgencias', 'M - Mañana', '2012-09-01', '2013-01-31');
insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (2,'12 de Octubre', 'UCI', 'T - Tarde', '2013-02-01', '2013-07-26');

insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (3,'HUGM Hospital Gregorio Marañon', 'Urgencias', 'M - Mañana', '2013-09-01', '2014-03-31');
insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (4,'12 de Octubre', 'UCI', 'T - Tarde', '2013-09-01', '2014-05-31');

insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (5,'12 de Octubre', 'Parto', 'T - Tarde', '2013-09-01', '2014-02-15');
insert into estancias_unidad_clinica (id_portafolio, centro_asociado, unidad_clinica, turno, fecha_inicio, fecha_fin) values (6,'12 de Octubre', 'AA', 'T - Tarde', '2013-09-01', '2014-06-26');




-- Trabajos de Campo info
insert into trabajos_de_campo_info (nombre, enunciado, descripcion) values ('801148 - Trabajo de Campo I', null, 'Este 1er trabajo servira...');
insert into trabajos_de_campo_info (nombre, enunciado, descripcion) values ('801148 - Trabajo de Campo II', null, 'Este 2do trabajo servira...');
insert into trabajos_de_campo_info (nombre, enunciado, descripcion) values ('801148 - Trabajo de Campo III', null, 'Este 3er trabajo servira...');

insert into trabajos_de_campo_info (nombre, enunciado, descripcion) values ('801149 - Trabajo de Campo I', null, 'Este 1er trabajo servira...');

insert into trabajos_de_campo_info (nombre, enunciado, descripcion) values ('801149 - Trabajo de Campo II', null, 'Este 2do trabajo servira...');

insert into trabajos_de_campo_info (nombre, enunciado, descripcion) values ('801149 - Trabajo de Campo III', null, 'Este 3er trabajo servira...');

insert into trabajos_de_campo_info (nombre, enunciado, descripcion) values ('801150 - Trabajo de Campo I', null, 'Este 1er trabajo servira...');


-- Trabajos de Campo
insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (3, 1, null, null, '2018-10-15 23:59:59');
insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (3, 2, null, null, '2019-10-13 23:59:59');
insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (3, 3, null, null, '2020-10-13 23:59:59');

insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (4, 4, null, null, '2012-10-05 12:59:59');
insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (5, 4, null, null, '2012-10-05 12:59:59');

insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (4, 5, null, null, '2025-10-15 23:59:59');
insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (5, 5, null, null, '2025-10-15 23:59:59');

insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (4, 6, null, null, '2088-10-05 23:59:59');
insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (5, 6, null, null, '2088-10-05 23:59:59');

insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (6, 7, null, null, '2013-10-13 23:59:59');

insert into trabajos_de_campo (id_portafolio, id_trabajo_info, trabajo_de_campo, correccion_trabajo, fecha_limite) values (1, 7, null, null, '2013-10-13 23:59:59');




-- Seminarios Realizados
insert into seminarios_realizados (id_portafolio, id_seminario) values (1,1);
insert into seminarios_realizados (id_portafolio, id_seminario) values (3,2);
insert into seminarios_realizados (id_portafolio, id_seminario) values (3,3);
insert into seminarios_realizados (id_portafolio, id_seminario) values (5,4);
insert into seminarios_realizados (id_portafolio, id_seminario) values (6,5);
insert into seminarios_realizados (id_portafolio, id_seminario) values (7,3);



-- Diarios Reflexivos
insert into diarios_reflexivos (id_portafolio, nombre, diario_reflexivo, fecha_subida) values (5, 'Diario I', null, '2013-10-13 23:59:59');
insert into diarios_reflexivos (id_portafolio, nombre, diario_reflexivo, fecha_subida) values (5, 'Diario II', null, '2014-10-13 23:59:59');
insert into diarios_reflexivos (id_portafolio, nombre, diario_reflexivo, fecha_subida) values (5, 'Diario III', null, '2015-10-13 23:59:59');
insert into diarios_reflexivos (id_portafolio, nombre, diario_reflexivo, fecha_subida) values (4, 'Diario I', null, '2016-10-13 23:59:59');
insert into diarios_reflexivos (id_portafolio, nombre, diario_reflexivo, fecha_subida) values (4, 'Diario II', null, '2017-10-13 23:59:59');
insert into diarios_reflexivos (id_portafolio, nombre, diario_reflexivo, fecha_subida) values (6, 'Diario I', null, '2018-10-13 23:59:59');


-- Anexos
insert into anexos (id_portafolio, nombre, anexo, fecha_subida) values (6, 'a1', null, '2013-10-13 23:59:59');
insert into anexos (id_portafolio, nombre, anexo, fecha_subida) values (4, 'a1', null, '2014-10-13 23:59:59');
insert into anexos (id_portafolio, nombre, anexo, fecha_subida) values (4, 'a2', null, '2015-10-13 23:59:59');
insert into anexos (id_portafolio, nombre, anexo, fecha_subida) values (3, 'a1', null, '2016-10-13 23:59:59');
insert into anexos (id_portafolio, nombre, anexo, fecha_subida) values (3, 'a2', null, '2017-10-13 23:59:59');
insert into anexos (id_portafolio, nombre, anexo, fecha_subida) values (3, 'a3', null, '2018-10-13 23:59:59');

-- Casos Clinicos
insert into casos_clinicos (id_portafolio, nombre, caso_clinico, fecha_subida) values (4, 'Caso Clinico I', null, '2013-10-13 23:59:59');
insert into casos_clinicos (id_portafolio, nombre, caso_clinico, fecha_subida) values (6, 'Caso Clinico I', null, '2014-10-13 23:59:59');
insert into casos_clinicos (id_portafolio, nombre, caso_clinico, fecha_subida) values (3, 'Caso Clinico I', null, '2015-10-13 23:59:59');
insert into casos_clinicos (id_portafolio, nombre, caso_clinico, fecha_subida) values (3, 'Caso Clinico II', null, '2016-10-13 23:59:59');	
	

-- Puntuacion Criterios 
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (1, 1, '2');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (1, 2, '1');

insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 37, '3');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 38, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 39, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 40, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 41, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 42, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 43, '3');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 44, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 45, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 46, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 47, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 48, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 49, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 50, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 51, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 52, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 53, '2');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (2, 54, '1');


insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 1, '3');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 2, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 3, '3');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 4, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 5, '3');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 6, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 7, '3');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 8, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 9, '3');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 10, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 11, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 12, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 13, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 14, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 15, '5');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 16, '4');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 17, '3');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 18, '1');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 19, '1');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 20, 'BIEN');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 21, 'MAL');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 22, 'REGULAR');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 23, 'FATAL');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 24, 'NO PRESTA ATENCIÓN');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 25, 'NO TIENE CUIDADO');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 26, 'OK');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 27, 'APRENDE RÁPIDO');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 28, 'OK');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 29, 'DEBE MEJORAR');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 30, 'SIN COMENTARIOS');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 31, 'APROBADO');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 32, 'SI');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 33, 'OK');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 34, 'EXCELENTE');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 35, 'NO');
insert into puntuacion_criterios (id_portafolio, id_criterio, nota) values (3, 36, 'ACEPTABLE');

-- errores log
insert into errores_log (tipo, fecha) values ('anyo_academico', '2013-01-01 00:00:01');
