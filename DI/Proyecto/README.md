# Proyecto DI

## 1. Introducción:

Se realiza una app con PyQt6 que se encarga de gestionar una colección de libros.
Dentro de esta app, podemos realizar las siguientes operaciones:

- Añadir libro
- Editar libro
- Eliminar libro
- Ver detalles de libro
___
## 2. Instalación:

### 2.1 Clonar el proyecto

Para instalar la aplicación hay que colocarse en la carpeta donde deseemos y ejecutar el siguiente comando para clonar el proyecto:

```sh
git clone  https://www.github.com/marcomorenilla/proyectos-dam/
```

Una vez clonado desplázate hasta dentro de la carpeta proyecto dentro de DI/Proyecto:

```sh
cd proyectos-dam/DI/Proyecto/
```

### 2.2 Configuración entorno virtual y ejecución.

Necesitarás instalar todas las dependencias necesarias para el proyecto:

1. Crea un entorno virtual ejemplo Windows 11 busca documentación para tu SO en caso de problemas:

```sh
python -m venv .venv

```
2. Activa el entorno virtual

```sh
source .venv\bin\activate.bat
```

verás que el prompt ha cambiado añadiendo delante  **`(.venv)`**

3. Descarga dependencias

```sh
pip install pyside6
```

4. Con el entorno virual activo ejecuta la app

```sh
python src/Proyecto.py
```

### 2.3 Cierre de entorno virtual

Una vez hayas finalizado puedes quitar el entorno virtual  ejecutando:

```sh
deactivate
```

___

## 3. Uso

Las operaciones se pueden realizar desde el menú tanto como desde los iconos.

Hay un menú y un icono para cada acción.

### 3.1 Añadir libro

Cuando la app esté abierta selecciona la opción de añadir libro para empezar a crear tu colección.

Esto desplegará un formulario del que habrá que introducir de manera obligatoria por lo menos los campos Título y Autor.

En caso que no estén introducidos, no se pasará de pantalla.

Si el libro ha sido añadido correctamente, saltará un mensaje indicándolo, en caso que no se añada por favor dímelo para que lo revise.

### 3.2 Editar libro

Para editar libro lo primero que debemos hacer es seleccionar una fila de la tabla.

Si no hubiese ninguna seleccionada, la app nos avisará.

Una vez que se selecciona la fila y pulsa sobre la acción, se abre el mismo formulario que para añadir libro.

En el caso que pulsemos en enviar, esta vez saltará un aviso diciendo que estamos editando una fila y que la acción no se podrá deshacer.

Una vez confirmamos, el libro queda editado en la interfaz.

### 3.3 Eliminar libro

Para eliminar algún libro, lo único que tenemos que hacer es seleccionar una fila de la tabla y pulsar sobre la acción eliminar libro.

Esto hará que salte un mensaje para que confirmemos que realmente queremos realizar esta operación, en caso que aceptemos, el libro queda eliminado.

### 3.4 Ver detalles

Esta acción, muestra una pantalla con toda la información que hemos rellenado en el formulario.

Se incluye una opción para editar el libro desde esta pantalla sin necesidad de volver a la principal.

Para editar el libro hemos de seguir los mismos pasos que en el punto [**3.2**](#32-Editar-libro)

___

## 4. Extra

La applicación viene con una serie de iconos e imágenes precargadas dentro de la carpeta assets, siéntete libre de cambiarlos respetando los nombres para no afectara al código.

Para seleccionar una imagen para tu libro puedes moverla dentro de una carpeta dentro de tu S.O y utilizarla.

Formatos de imagen soportados **`.jpg`** y **`.png`**

Si mueves la imagen, tendrás que volver a cargarla en la interfaz con la acción de editar.
___