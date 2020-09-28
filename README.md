<h1>Crud con Spring-boot y Cassandra</h1>

En este proyecto se utilizo con Spring-boot para la creación de la apiweb y Cassandra para la base de datos, 
tambien se usa Swagger2 y Swagger UI para la documentación y pruebas de los endpoints.

<details>
  <summary>Ver enlaces de Swagger</summary>
  
  [Swagger2](https://mvnrepository.com/artifact/io.springfox/springfox-swagger2) <br>
  [Swagger UI](https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui)
</details>

## Pasos para Cassandra

- Creacion de keyspace (spring_backend_crud_apirest_cassandra): <br>
`CREATE KEYSPACE spring_backend_crud_apirest_cassandra WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':1};`
- Se verifica que el Keyspace se cree de forma satisfactoria: `DESC KEYSPACES;` debe verse algo como:
```
    system_schema  system              spring_backend_crud_apirest_cassandra
```
- Indicamos que queremos usar el keyspace <b>spring_backend_crud_apirest_cassandra</b>: <br>
`USE spring_backend_crud_apirest_cassandra;`
- Se crea la tabla productos:<br>
```sh
    CREATE TABLE productos(
       id timeuuid PRIMARY KEY,
       nombre text,
       precio double,
       activo boolean,
       cantidad int
    );
```
- Se crea la tabla clientes:<br>
```sh
    CREATE TABLE clientes(
       id timeuuid PRIMARY KEY,
       nombre text,
       apellido text,
       email text,
       create_at date,
       update_at date
    );
```
- Para obtener más información de la tabla que acabamos de crear simplemente ejecutamos:
`DESC SCHEMA;`
- Para ver la tabla que creamos se usa: `SELECT * FROM productos;`

## Spring

### EndPoints Productos

- Crear Producto: `{{HOST_SPRING}}/api/producto/add`
- Ver todos los productos: `{{HOST_SPRING}}/api/producto/ver`
- Ver producto por id: `{{HOST_SPRING}}/api/producto/ver/{id}`
- Ver los productos activos: `{{HOST_SPRING}}/api/producto/ver/activo`
- Actualizar producto pot ID: `{{HOST_SPRING}}/api/producto/update/{id}`
- Eliminar producto pot ID: `{{HOST_SPRING}}/api/producto/delete/{id}`
- Eliminar todos los productos: `{{HOST_SPRING}}/api/producto/delete/all`

