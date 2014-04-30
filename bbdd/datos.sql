use mysales;

-- Marketing activities
insert into marketing_activities (id_marketing_activity, nombre) values (1, 'Direct marketing');
insert into marketing_activities (id_marketing_activity, nombre) values (2, 'Website');
insert into marketing_activities (id_marketing_activity, nombre) values (3, 'Mobile marketing');
insert into marketing_activities (id_marketing_activity, nombre) values (4, 'Telemarketing');
insert into marketing_activities (id_marketing_activity, nombre) values (5, 'Event');
insert into marketing_activities (id_marketing_activity, nombre) values (6, 'Recommendation');


-- Estados
insert into estados (id_estado, nombre) values (1, 'New');
insert into estados (id_estado, nombre) values (2, 'Accepted');
insert into estados (id_estado, nombre) values (3, 'Contacted');
insert into estados (id_estado, nombre) values (4, 'Ongoing');
insert into estados (id_estado, nombre) values (5, 'Offert');
insert into estados (id_estado, nombre) values (6, 'Won');
insert into estados (id_estado, nombre) values (7, 'Lost');


-- Actions
insert into actions (id_action, nombre) values (1, 'Call');
insert into actions (id_action, nombre) values (2, 'Meeting');
insert into actions (id_action, nombre) values (3, 'Mail');
insert into actions (id_action, nombre) values (4, 'Sponsor letter');
insert into actions (id_action, nombre) values (5, 'Offert');





-- Grupos
insert into grupos (id_grupo, nombre) values (1, 'Super user');
insert into grupos (id_grupo, nombre) values (2, 'Channel');
insert into grupos (id_grupo, nombre) values (3, 'Distributor');
insert into grupos (id_grupo, nombre) values (4, 'Sales Rep.');
insert into grupos (id_grupo, nombre) values (5, 'Supplier');

-- Permisos
insert into permisos (id_permiso, nombre) values (1, 'All actions allowed');
insert into permisos (id_permiso, nombre) values (2, 'View leads');
insert into permisos (id_permiso, nombre) values (3, 'Add lead');
insert into permisos (id_permiso, nombre) values (4, 'Delete lead');
insert into permisos (id_permiso, nombre) values (5, 'Modify lead');
insert into permisos (id_permiso, nombre) values (6, 'Import leads');
insert into permisos (id_permiso, nombre) values (7, 'Export leads');
insert into permisos (id_permiso, nombre) values (8, 'View sales reps.');
insert into permisos (id_permiso, nombre) values (9, 'Add sales rep.');
insert into permisos (id_permiso, nombre) values (10, 'Delete sales rep.');
insert into permisos (id_permiso, nombre) values (11, 'Modify sales rep.');
insert into permisos (id_permiso, nombre) values (38, 'Export sales reps.');
insert into permisos (id_permiso, nombre) values (12, 'View responsibles');
insert into permisos (id_permiso, nombre) values (13, 'Add responsible');
insert into permisos (id_permiso, nombre) values (14, 'Delete responsible');
insert into permisos (id_permiso, nombre) values (15, 'Modify responsible');
insert into permisos (id_permiso, nombre) values (39, 'Export responsibles');
insert into permisos (id_permiso, nombre) values (16, 'View distributors');
insert into permisos (id_permiso, nombre) values (17, 'Add distributor');
insert into permisos (id_permiso, nombre) values (18, 'Delete distributor');
insert into permisos (id_permiso, nombre) values (19, 'Modify distributor');
insert into permisos (id_permiso, nombre) values (40, 'Export distributors');
insert into permisos (id_permiso, nombre) values (20, 'View companies');
insert into permisos (id_permiso, nombre) values (21, 'Add company');
insert into permisos (id_permiso, nombre) values (22, 'Delete company');
insert into permisos (id_permiso, nombre) values (23, 'Modify company');
insert into permisos (id_permiso, nombre) values (41, 'Export companies');
insert into permisos (id_permiso, nombre) values (24, 'View services');
insert into permisos (id_permiso, nombre) values (25, 'Add service');
insert into permisos (id_permiso, nombre) values (26, 'Delete service');
insert into permisos (id_permiso, nombre) values (27, 'Modify service');
insert into permisos (id_permiso, nombre) values (42, 'Export services');
insert into permisos (id_permiso, nombre) values (28, 'View channels');
insert into permisos (id_permiso, nombre) values (29, 'Add channel');
insert into permisos (id_permiso, nombre) values (30, 'Delete channel');
insert into permisos (id_permiso, nombre) values (31, 'Modify channel');
insert into permisos (id_permiso, nombre) values (43, 'Export channels');
insert into permisos (id_permiso, nombre) values (32, 'Lock distributor');
insert into permisos (id_permiso, nombre) values (33, 'Unlock distributor');
insert into permisos (id_permiso, nombre) values (34, 'View users');
insert into permisos (id_permiso, nombre) values (35, 'Add user');
insert into permisos (id_permiso, nombre) values (36, 'Modify user');
insert into permisos (id_permiso, nombre) values (37, 'Delete user');



-- Permisos de grupo

insert into permisos_grupos (id_permisoGrupo, id_grupo, id_permiso) values (1, 1, 1);

-- Usuarios 

-- user/pass: admin/admin
-- insert into usuarios (id_usuario, id_grupo, id_asociado, nombre, user, pass) values (1, 1, 0, 'Administrator', 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- user/pass: admin/realmadrid
insert into usuarios (id_usuario, id_grupo, id_asociado, nombre, user, pass) values (1, 1, 0, 'Administrator', 'admin', 'e8c522a4c9bdc5ea1f7a0483d965e196');

