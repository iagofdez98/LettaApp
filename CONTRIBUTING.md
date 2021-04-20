# Tabla de contenido

## Empezando
Aplicación desarrollada por el Team A para la asignatura Desarrollo Ágil
de Aplicaciones del Grado en Ingeniería Informática de la Escuela Superior de 
Ingeniería Informática de la Universidad de Vigo.

## Estructura del proyecto
* Test: Directorio que contiene utilidades para realizar los tests de JUnit.
* Entities: Directorio que contiene las clases de dominio (entidades).
* REST: Directorio que contiene una capa de servicios REST.
* DAO: Directorio que contiene los objetos DAO.
* View: Directorio que contiene las vistas y los DAOs en JS.

## Entorno de desarrollo
* Eclipse JEE: Entorno de desarrollo a utilizar, el cual también permite la ejecución del proyecto.
* Maven 3: Maven es un entorno de construcción de proyectos para Java. Esta será una herramienta clave, ya que es quien dirigirá todo el proyecto. Es necesario que tengas instalado Maven 3 en tu equipo de desarrollo para poder construir el proyecto.
* Kunagi: Es una herramienta de gestión de proyectos Scrum. En ella encontrarás toda la información sobre las funcionalidades desarrolladas y por desarrollar, el alcance de las publicaciones, el estado de desarrollo, etc. Puedes acceder a través del siguiente enlace.
* Git y Gitlab: Git es el sistema de control de versiones que se utiliza en el proyecto. Es un sistema de control de versiones distribuido que facilita la colaboración entre desarrolladores. Es necesario que tengas instalado Git en tu sistema para poder realizar cambios en el proyecto y colaborar con el resto del equipo. Por otro lado, Gitlab es un front-end del repositorio Git común. Esta herramienta facilita la visualización de los commits y ficheros del proyecto, además de proporcionar alguna otra funcionalidad que mejora la colaboración. Puedes acceder a través del siguiente enlace.
* MySQL: MySQL es el sistema gestor de base de datos (SGDB) que utilizará el sistema definitivo. En la explicación de cómo ejecutar el sistema en local utilizaremos este SGBD, por lo que deberás tenerlo instalado en tu equipo.

## Control de versiones
El modelo de control de versiones que utilizaremos inicialmente será muy sencillo ya que solo utilizaremos dos ramas:

* master: A esta rama solo se enviarán los commits cuando se llegue a una versión estable y publicable (una release).
* develop: Esta será la rama principal de trabajo. Aquí se subirán las funcionalidades una vez finalizadas.


### Commits
El formato de los commits será el siguiente:

    [Nombre tarea] - [Titulo commit (cambio realizado)]
    La descripción del commit debe añadirse una descripción breve de los cambios.
    Los commits que supongan la finalización de una funcionalidad o el arreglo de errores/bugs, deberán tener asignado su correspondiente tag.

### Pulls
Para hacer un pull del código, deberá hacerse un pull rebase.

## Guía de estilo
### Código fuente
* Idioma: Todo el código (incluyendo la documentación) debe desarrollarse en inglés.
* Formato de código: El código debe estar formateado utilizando el formato de código de Eclipse (Ctrl+Mayus+F).
* Comentarios: Debe evitarse completamente el código comentado y, en la medida de lo posible, los comentarios en el código.

*Contenido de los commits:* 
* Los commits deben ser completos en el sentido de que no deben romper la construcción. Además, el código debe estar probado para que el resto de desarrolladores puedan confiar en el código existente.
* El formato de los commits debe seguir el ya comentado en la seccion Commits.
* El contenido de los commits se hará en castellano.