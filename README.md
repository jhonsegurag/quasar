
# Introduction - Quasar API Service
---

Este microservicio tiene como responsabilidad gestionar la administración de funcionalidades para Quasar en cual cuenta con funciones de Trilateracion y Descifrado

# Getting Started
---
## Contenido:
1.	Environment Variables.
2.	Functionalities
3.	Schema and Validation
4.	Request and Response HTTP
5.  Installation
6.	Testing
7.	Documentos referenciados

## 1. Environment Variables
---

| Nombre  | Valor  |
| ---------- | ---------- |
|EXCEPTION500| Has occurred an exception, please contact to helpdesk.|
|EXCEPTION400| Bad request, please verify the current input from consumer.|
|EXCEPTION200| Has occurred an exception, please contact to helpdesk.|
|SUCCESS200| Transaction completed successfully.|
|quasar.sattelite.0.position | -500.0,-200.0|
|quasar.sattelite.1.position | 100.0,-100.0|
|quasar.sattelite.2.position | 500.0,100.0|
|server.port| 5000|

## 2. Funcionalities
---
Esta sección esta dedicada a conocer en detalle las funcionalidades soportadas en el servicio, a continuación se listan las funcionalidades y la responsabilidad de cada una:

### Method Details
##### ***QuasarController***

***Method: POST***
`topsecret(RequestEntity<Galaxy> requestEntity)`: Esta función se encarga de gestionar el descifrado y la posicion de un nodo en espacio.
***Params***
`RequestEntity<Galaxy> requestEntity`: Representa todo el contexto en este caso la galaxia en la cual interactuan los componentes.
***Returns***

***Method: POST***
`topsecretsplit(String name, RequestEntity<TopSecretSplitRequest> requestEntity)`: Esta función se encarga de gestionar la inserción de un nuevo satelite para soportar el proceso en secuencia
***Params***
`RequestEntity<Galaxy> requestEntity`: Representa todo el contexto en este caso la galaxia en la cual interactuan los componentes.
***Returns***

***Method: GET***
`topsecretsplit()`: Esta función se encarga de gestionar el calculo y descifrado de los satelites ingresados hasta el momento

***Returns***


## 3. Schema and Validation
---
A continuación una descripcion sobre esquema que se aplica para la validación del payload del servicio.
 ~~~
  N/A
 ~~~

## 4. Request and Response HTTP
---

Api donde se expone los recuersos  
`hostname LOCAL`: `http://localhost:5000/`
`hostname CLOUD`: ``

`path`: `/topsecret`
**Method**
: **Post** : topsecret.
**Headers** : `N/A`
**Request**
~~~
{
    "satellites": [
        {
            "name":"Kenobi" ,
            "distance": 100.0,
            "message": ["este", "", "", "mensaje", ""]
        },
        {
            "name":"Skywalker" ,
            "distance": 115.5,
            "message": ["", "es", "", "", "secreto"]
        },
        {
            "name":"Sato" ,
            "distance": 142.7,
            "message": ["este", "", "un", "", ""]
        }
    ]
}
~~~

**Response**:
~~~
{
    "position": {
        "x": -58.315252587138595,
        "y": -69.55141837312165
    },
    "message": "este es un mensaje secreto"
}
~~~

`path`: `/topsecretsplit/{satellite_name}`
**Method**
: **Post** : topsecretsplit.
:  **Querystrings**: Este servicio requiere de los siguientes parametros:
* `satellite_name`: nombre del satelite a crea
  **Headers** : `N/A`
  **Request**
~~~
{
    "distance": 142.7,
    "message": ["este", "", "un", "", ""]
}
~~~
**Response**:
~~~
{
    "position": null,
    "name": "Sato",
    "distance": 142.7,
    "message": [
        "este",
        "",
        "un",
        "",
        ""
    ]
}
~~~

`path`: `/topsecretsplit`
**Method**
: **Get** : topsecretsplit.
**Headers** : `N/A`
**Request**:
~~~
N/A
~~~
**Response**:
 ~~~
{
    "position": {
        "x": -174.66380462086514,
        "y": -129.14133981599926
    },
    "message": "este es un mensaje secreto"
}
~~~

## 5. Installation
---
### Prerequisites
---
Instalar las siguientes herramientas:

- [Java 8](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Intellij IDEA](https://www.jetbrains.com/es-es/idea/download/#section=windows) ó IDE de preferencia.
- [Postman](https://www.getpostman.com/downloads/)

### Welcome to the code
--- 
Este apartado tiene como objetivo dar conocer a cada integrante del equipo la configuración e instalación de herramientas necesarias para escribir la primera linea de código, estructura o plantilla general de los micro-servicios, acompañada de la ejecución de algunos comandos vitales para el  ciclo de vida de desarrollo.

#### Download Repository
---
Para iniciar el proceso de implementación de un micro-servicio es necesario realizar los siguientes pasos:

1. **Clonar repositorio:**
   Para este paso es necesario contar con acceso a un repositorio git el cual cuenta con la gestion de cambios.
   A continuación ejecutar el siguiente comando:
```sh
	git clone https://github.com/jhonsegurag/quasar.git
```
2. **Crear una rama de implementación**
   El siguiente comando permite crear una nueva rama para implementacion de una nueva funcionalidad:
- {Id} Identificador de feature.
- {Project} Sprint en curso.
- DDMMYY Día-Mes-Año

 ```sh
	git checkout -b feature/HU{Id}_{Project}_DDMMYY
```

4. **Seleccionar  la rama de implementación**
   Este comando permite hacer focus a la rama en la que se desea realizar el desarrollo.

 ```sh
	git checkout feature/HU{Id}_{Project}_DDMMYY
```

6. **Descargar cambios realizados**
   Este comando permite obtener los cambios generales de todo el repositorio:

 ```sh
	git pull
```

Esta instrucción permite descargar cambios de una rama especifica:

 ```sh
	git pull origin branch
```

8. **Integrar cambios realizados**
   El siguiente comando permite subir los cambios realizados a una rama específica:

 ```sh
	git push origin feature/HU{Id}_{Project}_DDMMYY
```
10. **Solución de conflictos**
    Este apartado esta dedicado a la solución de conflictos, los cuales pueden presentarse en el proceso de PULL/PUSH.
11. **Creacion de pull request**
    Esta seccion esta dedicada al uso de una estrategia segura para la integración de nuevos cambios de acuerdo a las reglas establecidas por la organización, luego de la creación de:
- features
- bugfix
- hotfix
  Es necesario la integracion con el uso de PR -> Pull request a ramas como:
- develop
- release
- master

#### Deploy project
---
Para iniciar el proceso de despliegue del API servicio es necesario realizar los siguientes pasos:

1. **Importar repositorio**
   Para este paso es necesario contar con un clone del repositorio e importar a nuestro IDE de preferencia, el cual lo identifica con un proyecto "Maven".

2. **Instalacion de dependencias**
   A continuación se requiere instalar las dependencias necesarias para la ejecucion del proyecto".
~~~
mvn clean install
~~~
3. **Compilacion**
   El siguiente paso es compilar para la ejecucion del proyecto".
~~~
mvn clean package
~~~
4 **Ejecucion**
   Por ultimo se realiza la ejecución del artefacto".
~~~
mvn spring-boot:run
~~~

5 **Hello Quasar**
Para validar el despliegue satisfactorio se debe ingresar a:".

- [HelloQuasar -> http://localhost:5000/greet ](http://localhost:5000/greet)

#### Branching strategy
---
La estrategia de branching seleccionada para la ejecución del ciclo de vida de las diferentes funcionalidades es "git-flow" la cual consiste en creación de ramas por funcionalidad, con el objetivo de responder a la entrega continua.

#### Project Structure
---
A continuación se presenta la estructura estándar de cada proyecto, en donde se puede apreciar la ubicación de la lógica de negocio, algunas reglas de implementación, pruebas unitarias y despliegue del micro-servicio.

##### Controllers
Este directorio tiene como objetivo almacenar toda la logica de negocio del microservicio, cada controlador tiene una responsabilidad espeficica conservando la filosofía de artefacto "**reutilizable**" y "**escalable**".

Cada artefacto se recomienda que hable por si solo, por tal  motivo se requiere asociar nombre nemónico, **el cual permita un análisis y diagnostico ágil, para la creación de nuevas funcionalidades, solucion de incidencias**.

Cada controlador se crea teniendo en cuenta la siguiente estructura {NombreControlador}Controller.java"

- QuasarControler.java

##### Entity
En esta ubicación se encuentran la rpresentación de algunos modelos los cuales encapsulan información para apoyar los flujos de implementación.

- Galaxy
- Node
- Position
- Satelite
- Space
- WordNode

##### Exception
En esta ubicación se encuentran la representación de algunos modelos los cuales encapsulan las exepciones personalizadas para soportar posibles errores en tiempo de ejecución

Cada excepcion se crea teniendo en cuenta la siguiente estructura {NombreException}Exception.java”

- LocationException
- MessageException
- QuasarException
- SateliteException

##### Service
En esta ubicación se encuentran la representación de los servicios que hacen parte de la solucion conservando el modelo de interface e implementacion.

Cada Interface se crea teniendo en cuenta la siguiente estructura I{NombreInterface}Service.java”
###### Interfaces
- ILocationService
- IMessageService
- IProccesorService

Cada implementacion de servicio se crea teniendo en cuenta la siguiente estructura {NombreInterface}ServiceImpl.java”
###### Implementations
- LocationServiceImpl
- MessageServiceImpl
- ProccesorServiceImpl

## 6. Testing

Esta sección esta dedicada en conocer todo el proceso de pruebas realizado para cada micro-servicio.

### Pruebas unitarias

#### Herramientas
Ampliar informacion sobre herramientas utilizadas para la ejecucion de pruebas unitarias.
### Pruebas de integración:

#### Ciclo de vida de una prueba de integración
Esquema de ciclo de vida de una prueba de integración básica.
#### Funcionalidad
- Petición de token
- Inserción de {Entity}
- Consulta de {Entity}

#### Herramientas
Ampliar información sobre herramientas utilizadas para la ejecución de pruebas de integración.

### Pruebas de seguridad
Ampliar informacion sobre herramientas de validacion para la ejecución de pruebas de seguridad

# Contribute
TODO: Explain how other users and developers can contribute to make your code better.

If you want to learn more about creating good readme files then refer the following [guidelines](https://www.visualstudio.com/en-us/docs/git/create-a-readme). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)