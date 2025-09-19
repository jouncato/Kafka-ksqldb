# Comandos ksqlDB Corregidos y Organizados

## Comandos de Conexión y Configuración

```sql
-- Conectar a ksqlDB
docker exec -it kafka-ksqldb ksql

-- Configurar offset reset
SET 'auto.offset.reset'='earliest';
```

## 1. Comandos de Exploración y Metadatos

```sql
-- Imprimir contenido de topic
PRINT `topic-ksql-basic-data-one`;
PRINT `topic-ksql-basic-data-one` FROM BEGINNING;

-- Mostrar información del sistema
SHOW TOPICS;
SHOW STREAMS;
DESCRIBE `s-basic-data-three`;
DESCRIBE `s-basic-data-two`;
```

## 2. Creación de Streams - Tipos Básicos

### Stream con tipos primitivos
```sql
CREATE STREAM `s_ksql_basic_data_one` (
    myBoolean BOOLEAN,
    myString VARCHAR,
    myAnotherString VARCHAR,
    myInteger INT,
    myLong BIGINT,
    myFloat DOUBLE,
    myDouble DOUBLE,
    myBigDecimal DOUBLE
) WITH (
    KAFKA_TOPIC = 'topic-ksql-basic-data-one',
    VALUE_FORMAT = 'JSON'
);
```

### Stream mejorado con DECIMAL y tipos específicos
```sql
CREATE STREAM `s-basic-data-one` (
    myString STRING,
    myBoolean BOOLEAN,
    myInteger INT,
    myFloat DOUBLE,
    myDouble DOUBLE,
    myBigDecimal DECIMAL(20,12),
    myLong BIGINT,
    myAnotherString VARCHAR 
) WITH (
    KAFKA_TOPIC = 'topic-ksql-basic-data-one',
    VALUE_FORMAT = 'JSON'
);
```

## 3. Streams con Tipos de Fecha y Tiempo

```sql
CREATE OR REPLACE STREAM `s-basic-data-two` (
    myEpochDay DATE,
    myMillisOfDay TIME,
    myEpochInMillis TIMESTAMP
) WITH (
    KAFKA_TOPIC = 'topic-ksql-basic-data-two',
    VALUE_FORMAT = 'JSON'
);
```

### Consulta con funciones de fecha
```sql
SELECT 
    `MYEPOCHDAY`, 
    DATEADD(DAYS, 7, `MYEPOCHDAY`) AS `aWeekAfterMyEpochDay`,
    `MYMILLISOFDAY`,
    TIMESUB(HOURS, 2, `MYMILLISOFDAY`) AS `twoHoursBeforeMyMillisOfDay`,
    `MYEPOCHINMILLIS`,
    FORMAT_TIMESTAMP(`MYEPOCHINMILLIS`, 'dd-MM-yyyy HH:mm:ss Z', 'Asia/Jakarta') AS `epochMillisAtJakartaTimezone`
FROM `s-basic-data-two`
EMIT CHANGES;
```

## 4. Streams con Parsing de Fechas

```sql
CREATE OR REPLACE STREAM `s-basic-data-three` (
    `myLocalDateCustomFormat` VARCHAR,
    `myLocalDate` VARCHAR,
    `myLocalTime` VARCHAR,
    `myLocalTimeCustomFormat` VARCHAR,
    `myLocalDateTime` VARCHAR,
    `myLocalDateTimeCustomFormat` VARCHAR,
    `myOffsetDateTime` VARCHAR,
    `myOffsetDateTimeCustomFormat` VARCHAR
) WITH (
   KAFKA_TOPIC = 'topic-ksql-basic-data-three',
   VALUE_FORMAT = 'JSON'
);
```

### Consulta con funciones de parsing
```sql
SELECT 
    PARSE_DATE(`myLocalDate`, 'yyyy-MM-dd') AS `parsedLocalDate`,
    PARSE_DATE(`myLocalDateCustomFormat`, 'd MMMM yyyy') AS `parsedLocalDateCustomFormat`,
    PARSE_TIME(`myLocalTime`, 'HH:mm:ss') AS `parsedLocalTime`,
    PARSE_TIME(`myLocalTimeCustomFormat`, 'hh:mm:ss') AS `parsedLocalTimeCustomFormat`,
    PARSE_TIMESTAMP(`myLocalDateTime`, 'yyyy-MM-dd''T''HH:mm:ss') AS `parsedLocalDateTime`,
    PARSE_TIMESTAMP(`myLocalDateTimeCustomFormat`, 'dd-MM-yyyy HH:mm:ss') AS `parsedLocalDateTimeCustomFormat`,
    PARSE_TIMESTAMP(`myOffsetDateTime`, 'yyyy-MM-dd''T''HH:mm:ss.SSSZ') AS `parsedOffsetDateTime`,
    PARSE_TIMESTAMP(`myOffsetDateTimeCustomFormat`, 'dd-MMM-yyyy hh:mm:ss.SSS') AS `parsedOffsetDateTimeCustomFormat`
FROM `s-basic-data-three`
EMIT CHANGES;
```

## 5. Streams con Arrays

```sql
CREATE STREAM `s-basic-data-four` (
    `myStringList` ARRAY<VARCHAR>,
    `myIntegerList` ARRAY<INT>,
    `myDoubleSet` ARRAY<DOUBLE>
) WITH (
    KAFKA_TOPIC = 'topic-ksql-basic-data-four',
    VALUE_FORMAT = 'JSON'
);
```

### Consulta con funciones de arrays
```sql
SELECT 
    ARRAY_LENGTH(`myStringList`) AS `lengthMyStringList`,
    ARRAY_CONCAT(`myIntegerList`, ARRAY[999, 998, 997]) AS `concatMyIntegerList`,
    ARRAY_SORT(`myDoubleSet`, 'DESC') AS `sortedDescMyDoubleSet`
FROM `s-basic-data-four`
EMIT CHANGES;
```

## 6. Streams con Maps

```sql
CREATE STREAM `s-basic-data-five` (
   `myMapAlpha` MAP<VARCHAR, VARCHAR>,
   `myMapBeta` MAP<VARCHAR, VARCHAR>
) WITH (
   KAFKA_TOPIC = 'topic-ksql-basic-data-five',
   VALUE_FORMAT = 'JSON'
);
```

### Consulta con funciones de maps
```sql
SELECT 
    MAP_VALUES(`myMapAlpha`) AS `valuesAtMyMapAlpha`, 
    MAP_KEYS(`myMapBeta`) AS `keysAtMyMapBeta`
FROM `s-basic-data-five`
EMIT CHANGES;
```

## 7. Streams con Estructuras Complejas (STRUCT)

```sql
CREATE STREAM `s-basic-data-person` (
   `firstName` VARCHAR,
   `lastName` VARCHAR,
   `birthDate` VARCHAR,
   `contacts` MAP<VARCHAR, VARCHAR>,
   `passport` STRUCT<
     `number` VARCHAR,
     `expirationDate` VARCHAR
   >,
   `addresses` ARRAY<STRUCT<
     `streetAddress` VARCHAR,
     `country` VARCHAR,
     `location` STRUCT<
       `latitude` DOUBLE,
       `longitude` DOUBLE
     >
   >>
) WITH (
   KAFKA_TOPIC = 'topic-ksql-basic-data-person',
   VALUE_FORMAT = 'JSON'
);
```

### Consultas con estructuras anidadas

#### Acceso a Maps
```sql
SELECT 
    `contacts`['email'] AS `emailFromContactsMap`,
    `contacts`['phoneHome'] AS `phoneHomeFromContactsMap`,
    `contacts`['phoneWork'] AS `phoneWorkFromContactsMap`
FROM `s-basic-data-person`
EMIT CHANGES;
```

#### Acceso a STRUCT
```sql
SELECT 
    `passport`->`number` AS `passportNumber`,
    `passport`->`expirationDate` AS `passportExpirationDate`
FROM `s-basic-data-person`
EMIT CHANGES;
```

#### EXPLODE de Arrays con estructuras anidadas
```sql
SELECT 
    `firstName`, `lastName`,
    EXPLODE(`addresses`)->`streetAddress`,
    EXPLODE(`addresses`)->`country`,
    EXPLODE(`addresses`)->`location`->`latitude` AS `latitude`,
    EXPLODE(`addresses`)->`location`->`longitude` AS `longitude`
FROM `s-basic-data-person`
EMIT CHANGES;
```

## 8. Streams Derivados (CREATE STREAM AS SELECT)

### Stream con transformaciones de strings
```sql
CREATE STREAM `s-sample-basic-data-one` 
AS SELECT UCASE(MYSTRING) AS uppercaseMYSTRING 
FROM `s_ksql_basic_data_one`
EMIT CHANGES;
```

### Stream completo con múltiples transformaciones
```sql
CREATE STREAM `s-basic-data-person-complete`
AS SELECT 
  `firstName`,
  `lastName`,
  PARSE_DATE(`birthDate`, 'yyyy-MM-dd') AS `birthDate`,
  `contacts`['email'] AS `emailFromContactsMap`,
  `passport`->`number` AS `passportNumber`,
  `passport`->`expirationDate` AS `passportExpirationDate`,
  EXPLODE(`addresses`)->`streetAddress`,
  EXPLODE(`addresses`)->`country`,
  EXPLODE(`addresses`)->`location`->`latitude` AS `latitude`,
  EXPLODE(`addresses`)->`location`->`longitude` AS `longitude`
FROM `s-basic-data-person`
EMIT CHANGES;
```

## 9. Creación de Tables

### Table con agregación
```sql
CREATE TABLE `tbl-basic-data-country`
AS SELECT 
  `countryName`, 
  SUM(`population`) AS `totalPopulation`
FROM `s-basic-data-country`
GROUP BY `countryName`
EMIT CHANGES;
```

### Stream con re-keying
```sql
CREATE STREAM `s-basic-data-country-rekeyed-json` 
WITH (
  KEY_FORMAT = 'JSON'
) AS
SELECT 
  STRUCT(`countryName` := `countryName`, `currencyCode` := `currencyCode`) AS `jsonKey`,
  `currencyCode`,
  `population`
FROM `s-basic-data-country`
PARTITION BY STRUCT(`countryName` := `countryName`, `currencyCode` := `currencyCode`)
EMIT CHANGES;
```

## 10. Streams con Keys Definidas

```sql
CREATE STREAM `s-commodity-order` (
   `rowkey` VARCHAR KEY,
   `creditCardNumber` VARCHAR,
   `itemName` VARCHAR,
   `orderDateTime` VARCHAR,
   `orderLocation` VARCHAR,
   `orderNumber` VARCHAR,
   `price` INT,
   `quantity` INT
) WITH (
   KAFKA_TOPIC = 't-commodity-order',
   VALUE_FORMAT = 'JSON'
);
```

## 11. Comandos INSERT

### INSERT básico
```sql
INSERT INTO `s-basic-data-one` (
   `MYBOOLEAN`,
   `MYSTRING`,
   `MYANOTHERSTRING`,
   `MYFLOAT`,
   `MYDOUBLE`,
   `MYBIGDECIMAL`,
   `MYINTEGER`,
   `MYLONG`
) VALUES (
   false,
   'This is a string',
   'And this is another string',
   52.918,
   58298.581047,
   4421672.5001855,
   1857,
   2900175
);
```

### INSERT con funciones de fecha
```sql
INSERT INTO `s-basic-data-two` (
   `MYEPOCHDAY`,
   `MYMILLISOFDAY`,
   `MYEPOCHINMILLIS`
) VALUES (
   FROM_DAYS(29967),
   PARSE_TIME('18:47:15', 'HH:mm:ss'),
   FROM_UNIXTIME(1725559761000)
);
```

### INSERT con arrays
```sql
INSERT INTO `s-basic-data-four` (
  `myStringList`
) VALUES (
  ARRAY[
    'Hello',
    'from',
    'ksqlDb',
    'I hope you like it',
    'and enjoy the course'
  ]
);
```

### INSERT con maps
```sql
INSERT INTO `s-basic-data-five` (
   `myMapAlpha`,
   `myMapBeta`
) VALUES (
   MAP(
     '489' := 'four zero nine',
     '152' := 'one five two',
     '736' := 'seven three six',
     '827' := 'eight two seven'
   ),
   MAP(
     'd2c1b963-c1bc-4c6e-b85f-3ebc44b93cec' := 'The first element',
     '4edf4394-fd33-4643-9ed8-f3354fe96c28' := 'The second element',
     '720ecc9e-c81f-4fac-a4d5-752c1d3f3f4f' := 'The third element'
   )
);
```

### INSERT con estructuras complejas
```sql
INSERT INTO `s-basic-data-person` (
   `firstName`,
   `lastName`,
   `birthDate`,
   `contacts`,
   `passport`,
   `addresses`
) VALUES (
   'Kate',
   'Bishop',
   '2002-11-25',
   MAP(
     'email' := 'kate.bishop@marvel.com',
     'phone' := '999888777'
   ),
   STRUCT(
     'number' := 'MCU-PASS-957287759',
     'expirationDate' := '2029-08-18'
   ),
   ARRAY[
     STRUCT(
       'streetAddress' := 'Somewhere in New York',
       'country' := 'USA',
       'location' := STRUCT(
         'latitude' := 48.83063426849765,
         'longitude' := -74.14751581646931
       )
     ),
     STRUCT(
       'streetAddress' := 'Tokyo, just there',
       'country' := 'Japan',
       'location' := STRUCT(
         'latitude' := 35.734878460795104,
         'longitude' := 139.62821562631277
       )
     )
   ]
);
```

## 12. Ejemplo de Streams Unificados

### Streams individuales
```sql
-- Stream para compras móviles
CREATE STREAM `s-commodity-customer-purchase-mobile` (
    `purchaseNumber` VARCHAR,
    `purchaseAmount` INT,
    `mobileAppVersion` VARCHAR,
    `operatingSystem` VARCHAR,
    `location` STRUCT<
        `latitude` DOUBLE,
        `longitude` DOUBLE
    >
) WITH (
    KAFKA_TOPIC = 'topic-commodity-customer-purchase-mobile',
    VALUE_FORMAT = 'JSON'
);

-- Stream para compras web
CREATE STREAM `s-commodity-customer-purchase-web` (
   `purchaseNumber` VARCHAR,
   `purchaseAmount` INT,
   `browser` VARCHAR,
   `operatingSystem` VARCHAR
) WITH (
   KAFKA_TOPIC = 'topic-commodity-customer-purchase-web',
   VALUE_FORMAT = 'JSON'
);
```

### Stream unificado
```sql
CREATE STREAM `s-commodity-customer-purchase-all` (
   `purchaseNumber` VARCHAR,
   `purchaseAmount` INT,
   `mobileAppVersion` VARCHAR,
   `operatingSystem` VARCHAR,
   `location` STRUCT<
     `latitude` DOUBLE,
     `longitude` DOUBLE
   >,
   `browser` VARCHAR,
   `source` VARCHAR
) WITH (
   KAFKA_TOPIC = 'topic-ksql-commodity-customer-purchase-all',
   PARTITIONS = 2,
   VALUE_FORMAT = 'JSON'
);
```

### INSERT INTO desde otros streams
```sql
-- Insertar desde stream móvil
INSERT INTO `s-commodity-customer-purchase-all`
SELECT 
   `purchaseNumber`,
   `purchaseAmount`,
   `mobileAppVersion`,
   `operatingSystem`,
   `location`,
   CAST(null AS VARCHAR) AS `browser`,
   'mobile' AS `source`
FROM `s-commodity-customer-purchase-mobile`
EMIT CHANGES;

-- Insertar desde stream web
INSERT INTO `s-commodity-customer-purchase-all`
SELECT 
   `purchaseNumber`,
   `purchaseAmount`,
   CAST(null AS VARCHAR) AS `mobileAppVersion`,
   `operatingSystem`,
   CAST(null AS STRUCT<`latitude` DOUBLE, `longitude` DOUBLE>) AS `location`,
   `browser`,
   'web' AS `source`
FROM `s-commodity-customer-purchase-web`
EMIT CHANGES;
```

## 13. Ejemplo de JOINs

### Streams para votación
```sql
CREATE STREAM `s-commodity-web-vote-color` (
   `username` VARCHAR,
   `color` VARCHAR,
   `voteDateTime` VARCHAR
) WITH (
   KAFKA_TOPIC = 't-commodity-web-vote-color',
   VALUE_FORMAT = 'JSON'
);

CREATE STREAM `s-commodity-web-vote-layout` (
   `username` VARCHAR,
   `layout` VARCHAR,
   `voteDateTime` VARCHAR
) WITH (
   KAFKA_TOPIC = 't-commodity-web-vote-layout',
   VALUE_FORMAT = 'JSON'
);
```

### Tables de agregación
```sql
CREATE TABLE `tbl-commodity-web-vote-username-color` 
AS SELECT 
   `username`, 
   LATEST_BY_OFFSET(`color`) AS `color`
FROM `s-commodity-web-vote-color`
GROUP BY `username`
EMIT CHANGES;

CREATE TABLE `tbl-commodity-web-vote-username-layout` 
AS SELECT 
   `username`, 
   LATEST_BY_OFFSET(`layout`) AS `layout`
FROM `s-commodity-web-vote-layout`
GROUP BY `username`
EMIT CHANGES;
```

### JOIN entre tables
```sql
CREATE TABLE `t-commodity-web-vote-one-result-color` 
AS SELECT 
  `color`, 
  COUNT(`tbl-commodity-web-vote-username-color`.`username`) AS `votesCount`
FROM `tbl-commodity-web-vote-username-color`
INNER JOIN `tbl-commodity-web-vote-username-layout`
  ON `tbl-commodity-web-vote-username-color`.`username` = `tbl-commodity-web-vote-username-layout`.`username`
GROUP BY `color`
EMIT CHANGES;
```

## 14. Scripts de Limpieza

```sql
-- Limpiar streams existentes
DROP STREAM IF EXISTS `s-commodity-pattern-from-script`;
DROP STREAM IF EXISTS `s-commodity-reward-from-script`;
DROP STREAM IF EXISTS `s-commodity-storage-from-script`;
DROP STREAM IF EXISTS `s-commodity-order-from-script`;
```

## 15. Streams Derivados con Filtros

```sql
-- Stream con filtros de productos
CREATE OR REPLACE STREAM `s-commodity-pattern-from-script`
AS
SELECT 
    `itemName`, 
    `orderDateTime`, 
    `orderLocation`, 
    `orderNumber`, 
    (`price` * `quantity`) AS `totalItemAmount`
FROM `s-commodity-order-from-script`
WHERE LCASE(`itemName`) LIKE 'wooden%' 
   OR LCASE(`itemName`) LIKE 'metal%' 
EMIT CHANGES;

-- Stream con filtros de cantidad y precio
CREATE OR REPLACE STREAM `s-commodity-reward-from-script`
AS
SELECT 
    `itemName`, 
    `orderDateTime`, 
    `orderLocation`,
    `orderNumber`, 
    `price`, 
    `quantity`
FROM `s-commodity-order-from-script`
WHERE `quantity` >= 100
  AND `price` >= 500
EMIT CHANGES;

-- Stream con particionado
CREATE OR REPLACE STREAM `s-commodity-storage-from-script`
AS
SELECT 
    `itemName`, 
    `orderDateTime`, 
    `orderLocation`,
    `orderNumber`, 
    `price`, 
    `quantity`
FROM `s-commodity-order-from-script`
PARTITION BY `orderLocation`
EMIT CHANGES;
```

## 16. Consultas de Selección

```sql
-- Consultas básicas
SELECT * FROM `s_ksql_basic_data_one` EMIT CHANGES;
SELECT * FROM `s-sample-basic-data-one` EMIT CHANGES;
SELECT * FROM `s-basic-data-one` EMIT CHANGES LIMIT 15;
SELECT * FROM `s-basic-data-four` EMIT CHANGES;

-- Consulta con filtro específico
SELECT `MYEPOCHDAY`, `MYMILLISOFDAY`, `MYEPOCHINMILLIS` 
FROM `s-basic-data-two` 
WHERE `MYEPOCHDAY` = '2052-01-18';
```

## Notas Importantes

### Características de ksqlDB:
- **Inmutabilidad**: Los streams son inmutables, no se pueden hacer UPDATE ni DELETE directos
- **Consistencia de comillas**: Usar siempre backticks (`) para identificadores
- **Tipos de datos**: ksqlDB soporta tipos primitivos, arrays, maps y structs anidados
- **Particionado**: Las claves determinan la partición en Kafka

### Mejores Prácticas:
1. Usar `IF EXISTS` en DROP statements para evitar errores
2. Definir siempre el `VALUE_FORMAT` en los streams
3. Usar nombres descriptivos y consistentes
4. Validar tipos de datos antes de crear streams
5. Usar `EMIT CHANGES` para consultas push (tiempo real)