------------------------------------
-- Paso 1. Crear la base de datos --
------------------------------------
USE master
GO
CREATE DATABASE OCN_DB
    -- En mi base de datos existirán 4 esquemas
-- 1. Ubicación
-- 2. grupos
-- 3. orígenes
-- 4. registros
    GO
USE OCN_DB
GO
CREATE SCHEMA ubicacion
    GO
CREATE SCHEMA grupos
    GO
CREATE SCHEMA origenes
    GO
CREATE SCHEMA registros
------------------------------
-- Paso 2. Crear las tablas --
------------------------------
--Origenes
    GO
CREATE TABLE origenes.encuesta(
                                  idencuesta integer NOT NULL IDENTITY(1, 1),
                                  nombre varchar(50) NOT NULL,
                                  CONSTRAINT PK_Encuesta PRIMARY KEY(idencuesta)
)
    GO
CREATE TABLE origenes.entrevista(
                                    identrevista integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                                    nombre varchar(50) NOT NULL,
)
    GO
CREATE TABLE origenes.lider_comunitario(
                                           idlidercomunitario integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                                           nombrelider varchar(50) NOT NULL
)
    GO
CREATE TABLE origenes.observacion(
                                     idobservacion integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                                     observacion varchar(max)
)
    GO
CREATE TABLE origenes.tipo_fuente(
                                     idtipofuente integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                                     nombre varchar(50),
                                     CONSTRAINT CHK_Nombretipofuente CHECK (nombre IN('Encuesta',
                                                                                      'Entrevista',
                                                                                      N'Líder comunitario',
                                                                                      N'Observación'))
)
    GO
CREATE TABLE origenes.fuente(
                                idfuente integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                                idtipofuente integer NOT NULL,
                                CONSTRAINT FK_Fuente_Tipo_Fuente FOREIGN KEY (idtipofuente) REFERENCES origenes.tipo_fuente(idtipofuente) ON DELETE NO ACTION
)
    GO
CREATE TABLE origenes.fuente_encuesta(
                                         idfuente integer NOT NULL,
                                         idencuesta integer NOT NULL,
                                         CONSTRAINT PK_FE PRIMARY KEY (idfuente, idencuesta),
                                         CONSTRAINT FK_FE_Fuente FOREIGN KEY (idfuente) REFERENCES origenes.fuente(idfuente) ON DELETE NO ACTION,
                                         CONSTRAINT FK_FE_Encuesta FOREIGN KEY (idencuesta) REFERENCES origenes.encuesta(idencuesta) ON DELETE NO ACTION
)
    GO
CREATE TABLE origenes.fuente_entrevista(
                                           idfuente integer NOT NULL,
                                           identrevista integer NOT NULL,
                                           CONSTRAINT PK_FEN PRIMARY KEY (idfuente, identrevista),
                                           CONSTRAINT FK_FEN_Fuente FOREIGN KEY (idfuente) REFERENCES origenes.fuente(idfuente) ON DELETE NO ACTION,
                                           CONSTRAINT FK_FEN_Entrevista FOREIGN KEY (identrevista) REFERENCES origenes.entrevista(identrevista) ON DELETE NO ACTION
)
    GO
CREATE TABLE origenes.fuente_lider_comunitario(
                                                  idfuente integer NOT NULL,
                                                  idlidercomunitario integer NOT NULL,
                                                  CONSTRAINT PK_FLC PRIMARY KEY (idfuente, idlidercomunitario),
                                                  CONSTRAINT FK_FLC_Fuente FOREIGN KEY (idfuente) REFERENCES origenes.fuente(idfuente) ON DELETE NO ACTION,
                                                  CONSTRAINT FK_FLC_LC FOREIGN KEY (idlidercomunitario) REFERENCES origenes.lider_comunitario(idlidercomunitario) ON DELETE NO ACTION
)
    GO
CREATE TABLE origenes.fuente_observacion(
                                            idfuente integer NOT NULL,
                                            idobservacion integer NOT NULL,
                                            CONSTRAINT PK_FO PRIMARY KEY (idfuente, idobservacion),
                                            CONSTRAINT FK_FO_Fuente FOREIGN KEY (idfuente) REFERENCES origenes.fuente(idfuente) ON DELETE NO ACTION,
                                            CONSTRAINT FK_FO_Observacion FOREIGN KEY (idobservacion) REFERENCES origenes.observacion(idobservacion) ON DELETE NO ACTION
)
-- Grupos
CREATE TABLE grupos.equipo(
                              idequipo integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                              nombre varchar(50) NOT NULL,
                              descripcion varchar(255)
)
    GO
CREATE TABLE grupos.grupo_etario(
                                    idgrupoetario integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                                    edadminima integer,
                                    edadmaxima integer,
                                    CONSTRAINT U_GE_edadminima UNIQUE (edadminima),
                                    CONSTRAINT U_GE_edadmaxima UNIQUE (edadmaxima),
                                    CONSTRAINT CHK_GE_edadmaxima_more_than_edadminima CHECK (edadmaxima > edadminima)
)
-- Ubicacion
    GO
CREATE TABLE ubicacion.zona(
                               idzona integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                               nombre varchar(50) NOT NULL,
                               descripcion varchar(255)
)
    GO
CREATE TABLE ubicacion.levantamiento(
                                        idlevantamiento integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                                        fecha date NOT NULL,
                                        idzona integer NOT NULL,
                                        idgrupoetario integer NOT NULL,
                                        CONSTRAINT FK_Levantamiento_Zona FOREIGN KEY (idzona) REFERENCES ubicacion.zona(idzona) ON DELETE NO ACTION,
                                        CONSTRAINT FK_RON_GE FOREIGN KEY (idgrupoetario) REFERENCES grupos.grupo_etario(idgrupoetario) ON DELETE NO ACTION
)
    GO
CREATE TABLE ubicacion.levantamientos_por_equipo(
                                                    idlevantamiento integer NOT NULL,
                                                    idequipo integer NOT NULL,
                                                    CONSTRAINT PK_LPE PRIMARY KEY (idlevantamiento, idequipo),
                                                    CONSTRAINT FK_LPE_E FOREIGN KEY (idequipo) REFERENCES grupos.equipo(idequipo) ON DELETE NO ACTION,
                                                    CONSTRAINT FK_LPE_L FOREIGN KEY (idlevantamiento) REFERENCES ubicacion.levantamiento(idlevantamiento) ON DELETE NO ACTION
)
-- Registros
-- Entidad principal de la solución
-- Nota que esta se convertirá en una vista
CREATE TABLE registros.problematica(
                                       idproblematica integer NOT NULL PRIMARY KEY IDENTITY(1, 1),
                                       nombre varchar(50) NOT NULL,
                                       idlevantamiento integer NOT NULL,
                                       descripcion varchar(255),
                                       evidencia varchar(255) NOT NULL,
                                       CONSTRAINT FK_Problematica_Levantamiento FOREIGN KEY (idlevantamiento) REFERENCES ubicacion.levantamiento(idlevantamiento)
)
    GO
CREATE TABLE registros.problematica_fuente(
                                              idproblematica integer NOT NULL,
                                              idfuente integer NOT NULL,
                                              CONSTRAINT PK_PF PRIMARY KEY (idproblematica, idfuente),
                                              CONSTRAINT FK_PF_Problematica FOREIGN KEY (idproblematica) REFERENCES registros.problematica(idproblematica) ON DELETE NO ACTION,
                                              CONSTRAINT FK_PF_Fuente FOREIGN KEY (idfuente) REFERENCES origenes.fuente(idfuente) ON DELETE NO ACTION
)
    GO
    -- Paso 4. insertar datos de prueba
-- Grupos
    GO
INSERT INTO grupos.grupo_etario (edadminima, edadmaxima)
VALUES
    (1, 10),
    (11, 20),
    (21, 30),
    (31, 40),
    (41, 50)
    GO
INSERT INTO grupos.equipo (nombre, descripcion)
VALUES
    (N'Sistemáticos', N'Equipo compuesto de alumnos de la carrera Ingeniería en Sistemas Computacionales'),
    (N'Analíticos', 'Equipo de contadores')

-- Ubicación
    GO
INSERT INTO ubicacion.zona (nombre, descripcion)
VALUES
    ('Allende', 'Ciudad de allende'),
    ('Montemorelos', null),
    ('Linares', null)
    GO
INSERT INTO ubicacion.levantamiento (fecha, idzona, idgrupoetario)
VALUES
    (GETDATE(), (SELECT idzona FROM ubicacion.zona WHERE nombre = 'Montemorelos'), (SELECT idgrupoetario FROM grupos.grupo_etario WHERE edadminima = 21 AND edadmaxima = 30)),
    (GETDATE(), (SELECT idzona FROM ubicacion.zona WHERE nombre = 'Allende'), (SELECT idgrupoetario FROM grupos.grupo_etario WHERE edadminima = 31 AND edadmaxima = 40))
    GO
INSERT INTO ubicacion.levantamientos_por_equipo (idlevantamiento, idequipo)
VALUES
    ((SELECT idlevantamiento FROM ubicacion.levantamiento as l JOIN ubicacion.zona as z ON l.idzona = z.idzona WHERE nombre = 'Montemorelos'),
     (SELECT idequipo from grupos.equipo where nombre = N'Sistemáticos')),
    ((SELECT idlevantamiento FROM ubicacion.levantamiento as l JOIN ubicacion.zona as z ON l.idzona = z.idzona WHERE nombre = 'Allende'),
     (SELECT idequipo from grupos.equipo where nombre = N'Analíticos'))
    GO
insert into ubicacion.levantamientos_por_equipo values (1, 2)
--Origenes
    GO
INSERT INTO origenes.tipo_fuente (nombre)
VALUES
    ('Encuesta'),
    ('Entrevista'),
    (N'Líder comunitario'),
    (N'Observación');
GO
INSERT INTO origenes.encuesta (nombre)
VALUES
    (N'Encuesta de Satisfacción Comunitaria'),
    (N'Encuesta de Necesidades Básicas');
GO
INSERT INTO origenes.entrevista (nombre)
VALUES
    ('Entrevista a Residentes del Sector Norte'),
    (N'Entrevista a Jóvenes del Barrio');
GO
INSERT INTO origenes.lider_comunitario (nombrelider)
VALUES
    (N'María González López'),
    (N'Juan Carlos Ramírez');
GO
INSERT INTO origenes.observacion (observacion)
VALUES
    (N'Se observó gran participación de mujeres en la reunión del 15 de marzo'),
    (N'Durante la visita se detectó acumulación de basura en la esquina noroeste');
GO
INSERT INTO origenes.fuente (idtipofuente)
VALUES
    (1),  -- Encuesta
    (2),  -- Entrevista
    (3),  -- Lider comunitario
    (4);  -- Observación
GO
INSERT INTO origenes.fuente_encuesta (idfuente, idencuesta)
VALUES
    (1, 1),
    (1, 2);
GO
INSERT INTO origenes.fuente_entrevista (idfuente, identrevista)
VALUES
    (2, 1),
    (2, 2);
GO
INSERT INTO origenes.fuente_lider_comunitario (idfuente, idlidercomunitario)
VALUES
    (3, 1),
    (3, 2);
GO
INSERT INTO origenes.fuente_observacion (idfuente, idobservacion)
VALUES
    (4, 1),
    (4, 2);
GO
-- Registros
INSERT INTO registros.problematica
(nombre, idlevantamiento, descripcion, evidencia)
VALUES
    -- Problemática 1: asociada al levantamiento de Montemorelos (id 1)
    ('Falta de agua potable',
     1,
     N'La comunidad lleva más de 15 días sin suministro regular de agua',
     N'Foto del tanque vacío y quejas de vecinos'),

    -- Problemática 2: asociada al levantamiento de Allende (id 2)
    ('Inundaciones frecuentes',
     2,
     N'Las lluvias provocan inundaciones en las calles principales del sector',
     N'Video de la calle inundada después de la lluvia del 20/03')
    go
INSERT INTO registros.problematica_fuente (idproblematica, idfuente)
VALUES
    (1, 1),
    (2, 2)
    GO
--La vista principal
--drop view registros.reportes
create view registros.reportes as(
                                 select
                                     p.idproblematica as idreporte,
                                     p.descripcion,
                                     p.evidencia,
                                     l.fecha,
                                     concat('[', ge.edadminima, ' - ', ge.edadmaxima, ']') as grupoetario,
                                     z.nombre as zona,
                                     equipos.equipos,
                                     fuentes.fuente
                                 from registros.problematica p
                                          join ubicacion.levantamiento l on p.idlevantamiento = l.idlevantamiento
                                          join grupos.grupo_etario ge on l.idgrupoetario = ge.idgrupoetario
                                          join ubicacion.zona z on l.idzona = z.idzona
                                          outer apply (
                                     select string_agg(e.nombre, ', ') as equipos
                                     from ubicacion.levantamientos_por_equipo lpe
                                              left join grupos.equipo e on lpe.idequipo = e.idequipo
                                     where lpe.idlevantamiento = l.idlevantamiento
                                 ) equipos
                                          outer apply (
                                     select string_agg(tf.nombre, ', ') as fuente
                                     from registros.problematica_fuente pf
                                              left join origenes.fuente f on pf.idfuente = f.idfuente
                                              left join origenes.tipo_fuente tf on f.idtipofuente = tf.idtipofuente
                                     where pf.idproblematica = p.idproblematica
                                 ) fuentes)
GO

--Vista 1, Mostrar el número de levantamientos que ha hecho cada equipo
create view registros.conteo_levantamientos_realizados_por_equipo as
(
select
    e.idequipo,
    e.nombre as nombre,
    count(lpe.idlevantamiento) as totallevantamientos
from grupos.equipo e
         left join ubicacion.levantamientos_por_equipo lpe on e.idequipo = lpe.idequipo
group by e.idequipo,
         e.nombre
    )
GO

--Vista 2, Muestra el total de fuentes registrado en el sistema
create view registros.total_fuentes_almacenadas as
(
select f.idtipofuente  as idtipofuente,
       tf.nombre       as nombre,
       count(idfuente) as totalfuentes
from origenes.fuente f
         left join origenes.tipo_fuente tf on f.idtipofuente = tf.idtipofuente
group by f.idtipofuente, tf.nombre
    )
GO

--Vista 3 mostrar el total de levantamientos con por un grupo etario
create view registros.total_levantamientos_por_grupo_etario as
(
select ge.idgrupoetario,
       concat('[', ge.edadminima, ' - ', ge.edadmaxima, ']') as rangoedad,
       count(idlevantamiento)                                as totallevantamientos
from grupos.grupo_etario ge
         left join ubicacion.levantamiento l on ge.idgrupoetario = l.idgrupoetario
group by ge.idgrupoetario, ge.edadminima, ge.edadmaxima
)
GO

--Procedimiento 1: Crear una fuente
--Toma los datos y decide donde se debe insertar y que relaciones crear con base al tipo de fuente
--Decidí encapsular esta lógica porque pienso que sería mucho más facil para el sistema
create or alter procedure origenes.crearFuente
    @idtipofuente int,
    @data varchar(max)
as
begin
insert into origenes.fuente
(idtipofuente)
values
    (@idtipofuente);
declare @idfuente int = scope_identity()
if @idtipofuente = 1 --Encuesta
begin
insert into origenes.encuesta
(nombre)
values
    (@data);
declare @idencuesta int = scope_identity();
insert into origenes.fuente_encuesta
(idfuente, idencuesta)
values
    (@idfuente, @idencuesta)
end
    else if @idtipofuente = 2 --Entrevista
begin
insert into origenes.entrevista
(nombre)
values
    (@data);
declare @identrevista int = scope_identity();
insert into origenes.fuente_entrevista
(idfuente, identrevista)
values
    (@idfuente, @identrevista)
end
    else if @idtipofuente = 3 --Líder Comunitario
begin
insert into origenes.lider_comunitario
(nombrelider)
values
    (@data)
declare @idlidercomunitario int = scope_identity()
insert into origenes.fuente_lider_comunitario
(idfuente, idlidercomunitario)
values
    (@idfuente, @idlidercomunitario)
end
    else if @idtipofuente = 4 --Observación
begin
insert into origenes.observacion
(observacion)
values
    (@data)
declare @idobservacion int = scope_identity()
insert into origenes.fuente_observacion
(idfuente, idobservacion)
values
    (@idfuente, @idobservacion)
end
select @idfuente as idfuente
end
GO


--Así en la aplicación resulta más práctico inicializar una fuente
--exec origenes.crearFuente 4, "Se vió una disminución de la calidad del agua"



--Procedimiento 2: Crear un reporte,
--Toma los datos de entrada e inicializa la tabla problemática y levantamiento.
--Decidí encapsular esta lógica para poder verlo como una sola acción a diferencia
--de crear manualmente las tablas

create procedure registros.crearReporte
    @nombre varchar(50),
    @fecha date,
    @idzona int,
    @idgrupoetario int,
    @descripcion varchar(255) null,
    @evidencia varchar(255)
as
begin
insert into ubicacion.levantamiento
(fecha, idzona, idgrupoetario)
values
    (@fecha, @idzona, @idgrupoetario);
insert into registros.problematica
(nombre, idlevantamiento, descripcion, evidencia)
values
    (@nombre, scope_identity(), @descripcion, @evidencia);
select scope_identity() as idreporte;
end
GO

-- declare @now date = getdate()
-- exec registros.crearReporte
--      "Prueba 1",
--      @now,
--      2,
--      2,
--      "Reporte de prueba",
--      "Fotos adjuntas"
--
--
-- select * from registros.reportes

--Procedimiento 3: Agregar una fuente a un reporte
create procedure registros.agregarFuenteAReporte
    @idReporte int,
    @idfuente int
as
begin
insert into registros.problematica_fuente
(idproblematica, idfuente)
values
    (@idReporte, @idfuente);
end
GO

--Procedimiento 4: Agregar un equipo a un reporte
create procedure registros.agregarEquipoAReporte
    @idReporte int,
    @idEquipo int
as
begin
declare @idLevantamiento int = (
    select idlevantamiento
    from registros.problematica
    where idproblematica = @idReporte
);

insert into ubicacion.levantamientos_por_equipo
(idlevantamiento, idequipo)
values
    (@idLevantamiento, @idEquipo);
end
GO

--Procedimiento 5: Borrar reporte
--Decidí encapsular esta lógica para que sea más fácil para el usuario.

create procedure registros.borrarReporte
    @idReporte int
as
begin
declare @idLevantamiento int = (select idlevantamiento from registros.problematica where idproblematica = @idReporte);
delete from registros.problematica_fuente where idproblematica = @idReporte;
delete from ubicacion.levantamientos_por_equipo where idlevantamiento = @idLevantamiento;
delete from registros.problematica where idproblematica = @idReporte;
delete from ubicacion.levantamiento where idlevantamiento = @idlevantamiento;
end
GO
