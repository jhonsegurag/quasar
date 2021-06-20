
# Introduction - {microservice template}
---

Este microservicio tiene como responsabilidad gestionar la administración de <Entity>, cuenta con funcionalidades para administracion de <Entitiy>.

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
|nameVariable| value|

## 2. Funcionalities
---
Esta sección esta dedicada a conocer en detalle las funcionalidades soportadas en el micro-servicio, a continuación se listan las funcionalidades y la responsabilidad de cada una:

### Method Details
##### ***handlerController***

`handler(transactionId, event, context, callback)`: Esta función se encarga de gestionar el flujo principal del microservicio.
***Params***
 `transactionId`: Representa el id de la transaccíon para la peticion.
 `event`: Contiene la informacion central que interviene en la lógica de negocio del microservicio
 `context`: Permite ingresar infromacion de contexto proporcionada por AWS.
 `callback`: 
***Returns***

##### ***nameController***

`nameMethod(params)`: Esta función  recibe la solicitud para consultar la información y puede contener lógica de negocios, desde aqui se arma el filtro que se lanza a la base de datos. 
***Params***
 `params`: Params
***Returns***
`promise`: promesa

## 3. Schema and Validation
---
A continuación una descripcion sobre esquema que se aplica para la validación del payload del servicio `microserviceTemplate`.
 ~~~
  
 ~~~
 
## 4. Request and Response HTTP 
---

Api donde se expone los recuersos  **/api/**.
El recurso al que se accede es **/api/template**.
`path`: `/api/template/action`

**Method**
 : **Post** : description.
 **Headers** : `Authorization`, comprueba que el usuario este logeado.
 **Response**: 
  * `201`: Transaction successfully
  * `400`: Bad request
  * `500`: Internal Error

**Method**
: **Get** : description.
:  **Querystrings**: Este servicio requiere de los siguientes parametros: 
 * `querystring`: description.`required`.
 **Headers** : `Authorization`, comprueba que el usuario este logeado.
 **Response**: 
  * `200`: Transaction successfully
  * `400`: bad request
  * `404`: Resource not found
  * `500`: Internal Error

[Swagger {microservice name}]()
  
## 5. Installation
---
### Prerequisites
---
Instalar las siguientes herramientas:

- [Node 8.10](http://nodejs.org)
- [Serverless 1.2x](https://serverless.com/)
- AWS Access Key
- [Visual Studio Code](https://code.visualstudio.com/) ó IDE de preferencia.
- [Postman](https://www.getpostman.com/downloads/)

### Welcome to the code
--- 
Este apartado tiene como objetivo dar conocer a cada integrante del equipo la configuración e instalación de herramientas necesarias para escribir la primera linea de código, estructura o plantilla general de los micro-servicios, acompañada de la ejecución de algunos comandos vitales para el  ciclo de vida de desarrollo.

#### Download Repository
---
Para iniciar el proceso de implementación de un micro-servicio es necesario realizar los siguientes pasos:

 1. **Clonar repositorio:**
Para este paso es necesario contar con una cuenta en Azure Devops en caso de ser un repositorio privado Banistmo.
A continuación ejecutar el siguiente comando:
```sh
	git clone url
```
 2. **Crear una rama de implementación** 
 El siguiente comando permite crear una nueva rama para implementacion de una nueva funcionalidad:
 - {Id} Identificador de historia de usuario.
 - {Sprint} Sprint en curso.
 - DDMMYY Día-Mes-Año 
 
 ```sh
	git checkout -b feature/HU{Id}_SP{Sprint}_DDMMYY
```

 4. **Seleccionar  la rama de implementación**
 Este comando permite hacer focus a la rama en la que se desea realizar el desarrollo.
 
 ```sh
	git checkout feature/HU{Id}_SP{Sprint}_DDMMYY
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
	git push origin feature/HU{Id}_SP{Sprint}_DDMMYY
```
 10. **Solución de conflictos**
Este apartado esta dedicado a la solución de conflictos, los cuales pueden presentarse en el proceso de PULL/PUSH.
 11. **Creacion de pull request**


 12. **Validacion de despliegue** 


#### Branching strategy
---
La estrategia de branching seleccionada para la ejecución del ciclo de vida de las diferentes funcionalidades es "git-flow" la cual consiste en creación de ramas por funcionalidad, con el objetivo de responder a la entrega continua.

#### Project Structure
---
A continuación se presenta la estructura estándar de cada proyecto, en donde se puede apreciar la ubicación de la lógica de negocio, algunas reglas de implementación, pruebas unitarias y despliegue del micro-servicio.

##### Controllers
Este directorio tiene como objetivo almacenar toda la logica de negocio del microservicio, cada controlador tiene una responsabilidad espeficica conservando la filosofía de artefacto "**reutilizable**" y "**escalable**".

Cada artefacto se recomienda que hable por si solo, por tal  motivo se requiere asociar nombre nemónico, **el cual permita un análisis y diagnostico ágil, para la creación de nuevas funcionalidades, solucion de incidencias**.

Cada controlador se crea teniendo en cuenta la siguiente estructura "NombreControlador+Controller.js"

##### Models
En esta ubicación se encuentran la rpresentación de algunos modelos los cuales encapsulan información para apoyar los flujos de implementación.

##### Schemas
En esta ubicación se encuentran la representación de los esquemas válidos para apoyar los flujos de implementación.

##### Node_modules
Esta carpeta contiene los diferentes módulos necesarios para la ejecución del micro-servicio, realizar pruebas locales, ejecutar pruebas unitarias, entre otras necesidades.

##### Coverage
Esta sección permite dar a conocer la cobertura de código luego de la ejecución de pruebas unitarias.

La configuración necesaria para ejecutar esta herramienta se puede encontrar en el **package.json** de cada micro-servicio en la sección "**scripts**", la cual cuenta con diferentes configuraciones de acuerdo al sistema operativo.
~~~
"scripts": {

"test": "VARIABLE_ENTORNO=variable_entorno mocha --reporter=nyan",

"test-win": "SET VARIABLE_ENTORNO=variable_entorno&& mocha --reporter=nyan",

"coverage": "VARIABLE_ENTORNO=variable_entorno ./node_modules/.bin/istanbul cover ./node_modules/mocha/bin/_mocha -R ./test/*.js",

"coverage-win": "SET VARIABLE_ENTORNO=variable_entorno&& istanbul cover ./node_modules/mocha/bin/_mocha -R ./test/*.js"
}
~~~

##### Test
Este directorio cuenta con la responsabilidad de almacenar la configuración y desarrollo de pruebas unitarias

##### index.js
El archivo "**index.js**" es considerado la puerta de entrada al micro-servicio, desde esta ubicación se puede conocer el flujo de cada petición al core de la lógica de negocio.

##### package.json
Este archivo "**package.json**" tiene como objetivo presentar toda la configuración para la ejecución de diferentes tareas en el micro-servicio, cuenta con información de versiones, scripts, engines, dependencias de producción y desarrollo.

##### serverless.yml
Este fichero contiene la información necesaria para el despliegue del micro-servicio, en este caso en la infraestructura de **AWS**.

#### Test Units
---
Esta sección tiene como objetivo presentar el flujo de creación, ejecución y validación de pruebas unitarias.

#### Debugger
---
Esta sección esta dedicada a la depuración de funcionalidades con el objetivo de disminuir a una cantidad mínima el origen de incidencias

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
Fluid Attacks 
Solicitud de cuenta desarrollador.
Plataforma reportes vulnerabilidades.

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://www.visualstudio.com/en-us/docs/git/create-a-readme). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)