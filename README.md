# Database
```
Pontificia Universidad Javeriana
Departamento de Ingeniería de Sistemas
Base de Datos Proyecto 3
```
**Kiosco de Libros.**

- Se requiere hacer un programa orientado a objetos que funcionará en kioscos.
- Al finalizar el día los prestamos se envían a un servidor central y se limpian los préstamos en el kiosco.
- El kiosco tiene ahora una pantalla más amigable al usuario.

## UML

<ins> Cada capa UML es un .jar </ins>

![Image of Design](https://github.com/monotera/Database/blob/master/Images/Diseno.png)

Para este proyecto se solicita implementar las siguientes funcionalidades en la clase ‘Kiosco’


## Interfaz Gráfica de Usuario
![Image of Interface](https://github.com/monotera/Database/blob/master/Images/Interface.png)



**El proceso de préstamo se resume de la siguiente manera:**

1. **[ 20 ]** Al iniciar el día se debe:
    a. crear la colección de libros llamada 'catalogo' (método en el controlador ‘IGestionLibro’ que crea la lista de
       libros), cree el método ‘CargarLibros()’ en iGestionLibro, cuya funcionalidad es leer los libros desde la tabla
       de Libros y devolver una Lista de Libros para que sea asignada al ‘catalogo’
    b. La clase Libreria debe invocar en su constructor el método anterior
2. **[10]** Crear Préstamo: Inicialmente la máquina crea un nuevo préstamo y se queda esperando por la introducción de
    monedas. (método ‘crearNuevoPrestamo’ de la clase ‘Libreria’ que no recibe parámetros, retorna booleano
    indicando si se pudo crear el préstamo)
       a. Este préstamo se maneja en la relación ‘prestamos’
       b. El último préstamo pasa a ser manejado con la relación ‘prestamoActual’
       c. Se toma la fecha y hora del sistema (use LocalDate)
       d. El número del préstamo no se puede repetir
       e. No se puede crear un nuevo préstamo sino existen unidades disponibles de ningún libro
          i Retorna falso
       f. Se debe desplegar un mensaje en pantalla indicando si se pudo o no crear el préstamo.



<ins> Notas: </ins>
- Este método se invoca al presionar el botón ‘Nuevo Prestamo’, al oprimir el botón se deben limpiar todas las
    etiquetas dejarlas en cero, limpiar la tabla de líneas y la caja de cantidad ponerla en cero.

Existe una clase ‘DtoResumen’ (usted debe crear esta nueva clase) que se utilizara para devolver datos desde todos los
métodos que vienen a continuación, cada método debe diligenciar los atributos correspondientes.

El Dto tiene:

- Un atributo ‘mensaje’ de tipo cadena con mensajes de error
- La colección de objetos de Líneas conteniendo:
    o Objeto Libro
    o cantidad
    o El valor total del libro (precio)
    o subtotal de la línea
- Un atributo de tipo booleano que indica si se pudo agregar la línea al préstamo
- El total de todo el préstamo
- El saldo de las monedas ingresadas
- La cantidad de vueltos del préstamo actual
- Agregue todos los demás atributos que requiera para devolver y poder refrescar la GUI.

<ins> NOTA: se debe persistir el préstamo en las tablas usando el ‘RepositorioPrestamo’ </ins>
3. **[ 20 ]** Agregar Línea: El usuario va agregando líneas al préstamo

Método ‘agregarLinea’ que recibe un objeto libro del catálogo y una cantidad de libros para crear una nueva línea;
retorna un ‘DtoResumen’ que contiene:

- Un atributo ‘mensaje’ de tipo cadena con mensajes de error
- La colección de objetos de Líneas conteniendo:
    o Objeto Libro
    o cantidad
    o El valor total del libro (precio)
    o subtotal de la línea
- La anterior colección tiene la nueva línea creada
- Un atributo de tipo booleano que indica si se pudo agregar la línea al préstamo
- El total de todo el préstamo

El código del método ’agregarLinea’ tiene que:

```
a. Verificar Libro en Catalogo (método privado)
i. El sistema verifica que el libro que llega como parámetro se encuentra en el catalogo
ii. Si el libro existe se vincula en la relación ‘libroEnPrestamo’
```

```
iii. Si el libro no existe debe diligenciar el atributo ‘mensaje’ del ‘DtoResumen’ de retorno
b. Verificar Existencias Libro. (método privado)
i. El sistema valida que la existencia del libro sea suficiente (atributo ‘unidadesDisponibles’ de la clase
libro.
```
1. Si no hay existencia debe diligenciar el atributo ‘mensaje’ del ’DtoResumen’ de retorno
ii. Si un libro ya existe en el préstamo o sea ya está en una ‘línea’ se acumula la cantidad existente con la
solicitada.
c. Crear Linea (método privado)
i. Crea la línea y la introduce en la lista de ‘líneas’ del préstamo actual
d. Calcula el valor del libro (método privado)
i. Precio base + (número imágenes * valor imagen) + (numero de videos * valor video)
e. Calcula el subtotal de una línea (método privado)
i. Multiplica el valor del libro (calculado en el método anterior) por la cantidad de libros de la línea
f. Calcula el total del préstamo (método privado)
i. sumatoria de los subtotales de cada línea (calculados en el método anterior)
g. Crear el ‘DtoResumen’ que va a retornar
i. Use los métodos ya implementados anteriormente.

<ins> Notas: </ins>

- Este método ’agregarLinea’ se invoca cuando se presiona el botón ‘Agregar Linea’, para los parámetros de
    entrada del método: el libro se debe tomar del combo de ‘Seleccionar Libro’, la cantidad se toma de la caja de
    texto ‘Cantidad’
- Se debe refrescar la GUI, esto es, refrescar la grilla y los totales del préstamo, use el ‘DtoResumen’ retornado
    por el método; se deben mostrar ‘mensaje’ de error si lo hay.
       o El objeto línea que devuelve el método debe ser vinculado a la grilla de libros del préstamo.

<ins> NOTA: se debe persistir la línea en las tablas y consultar las líneas desde la tabla para devolverlas a la lógica (usando
el ‘RepositorioPrestamo’)</ins>
4. **[10]** Eliminar una línea del Préstamo

Método publico eliminarLinea recibe un objeto de tipo ‘Linea’ y retorna un ‘DtoResumen’ que contiene:

- Un atributo ‘mensaje’ de tipo cadena con mensajes de error
- La colección de objetos de Líneas conteniendo:
    o Objeto Libro
    o cantidad
    o El valor total del libro (precio)
    o subtotal de la línea
- La anterior colección sin la línea borrada
- Un atributo de tipo booleano que indica si se pudo eliminar la línea del préstamo
- El total de todo el préstamo

El código de ‘eliminarLinea’ tiene que:
a. Verificar Línea (método privado)


```
i. Si el objeto de tipo Linea que llega esta nulo se diligencia el ‘mensaje’ del ‘DtoResumen’
b. Buscar la línea y quitarla de la colección de líneas del préstamo actual
i. Si no se encuentra la línea se diligencia el ‘mensaje’ del ‘DtoResumen’
c. Crear el ‘DtoResumen’ que va a retornar.
i. Reutilice los métodos ya implementados en el controlador.
```
<ins>Notas:</ins>

- Para Eliminar se debe seleccionar en la grilla de la GUI la línea a Eliminar y presionar el botón ‘Eliminar Linea’
- Se debe refrescar la GUI, esto es, refrescar la grilla y los totales del préstamo, use el ‘DtoResumen’ retornado
    por el método; se deben mostrar ‘mensaje’ de error si lo hay.

<ins>NOTA: se debe persistir la eliminación de la línea en las tablas y consultar las líneas desde la tabla para devolverlas a
la lógica (usando el ‘RepositorioPrestamo’).</ins>
5. **[10]** Introducir Monedas

Método publico introducirMoneda recibe un enumerado de tipo ‘Denominacion’ y una cantidad de moneda de la
denominación; y retorna un ‘DtoResumen’ con el atributo de ’saldo de monedas ingresadas’ ya diligenciado con el total
de monedas de ‘pagoMonedas’ del préstamo.

El código de ‘introducirMoneda’ tiene que:
a. Validar que exista el enumerado que llega como parámetro
i. Si no se encuentra se diligencia el ‘mensaje’ del ‘DtoResumen’
b. Crear una nueva ‘Moneda’, vinculando el enumerado que llega como parámetro
i. Se asume la cantidad como 1 una moneda
c. Agregar la moneda creada a la colección ‘pagoMonedas’ del préstamo
d. Crear el ‘DtoResumen’ que va a retornar.

<ins>Notas:</ins>

- Para Agregar una moneda se debe digitar el número de monedas, la denominacion y presionar el botón ‘Agregar
    Moneda’
- Se debe refrescar la GUI, esto es, refrescar la etiqueta de la pantalla cuyo nombre es ‘saldo disponible de
    monedas ingresadas’, use el ‘DtoResumen’ retornado por el método; se deben mostrar ‘mensaje’ de error si lo
    hay.


<ins>NOTA: se debe persistir la moneda introducida en las tablas y consultar las monedas desde la tabla para devolverlas a
la lógica (usando el ‘RepositorioPrestamo’).</ins>
6. **[ 20 ]** Terminar Préstamo

Metodo público ‘terminarPrestamo’ no recibe parámetros y retorna un ‘DtoResumen’ con el atributo valor de los vueltos
diligenciado.


El código de ‘terminarPrestamo’ tiene que:

```
a. Verificar Saldo (método privado)
i. Si el saldo disponible (total de monedas introducidas relación ‘pagoMonedas’) no es inferior al valor
total del libro seleccionado entonces: se dispensan los libros.
```
1. En caso contrario diligenciar el mensaje del ‘DtoResumen’
b. Actualizar Existencias (método privado)
i. Se actualizan las existencias del libro restando en unidades disponibles la cantidad de libros de cada
línea del préstamo.
e. Devolver Saldo (método privado)
i. Si hay saldo restante la máquina lo devuelve
ii. Se debe retornar los vueltos (un double)

```
f. Crear el ‘DtoResumen’ que va a retornar.
```
<ins>Notas:</ins>

- Para invocar este método se debe presionar el botón ‘Terminar Prestamo’
- Se debe refrescar la GUI, esto es, refrescar la etiqueta de la pantalla cuyo nombre es ‘vueltos’, use el
    ‘DtoResumen’ retornado por el método; se deben mostrar ‘mensaje’ de error si lo hay.

<ins>NOTA: se debe persistir la terminación de la línea en las tablas y consultar las líneas desde la tabla para devolverlas a
la lógica (usando el ‘RepositorioPrestamo’).</ins>

7. **[ 20 ]** Consultar Préstamo

```
Método público en Librería que recibe un número de préstamo; el método busca el número del préstamo y retorna null
o un DTO con todos los datos que se necesitan para llenar los elementos visuales de la pantalla referidos al préstamo
encontrado: fecha, numero, líneas, total del préstamo.
```
```
Agregue en la interfaz gráfica una caja de texto en donde se pueda introducir el número de un préstamo a consultar. Si
lo considera necesario redistribuya la pantalla para hacerla más clara.
```

<ins>NOTA: se debe consultar el préstamo desde la tabla para devolverlas a la lógica (usando el ‘RepositorioPrestamo’).</ins>

8. **[ 40 ]** Cree un aplicativo MVC usando JavaFX que permita probar las funcionalidades
    a. Se debe crea una pantalla similar a la dada en esta entrega
    b. Se debe crear un controlador de eventos que debe usar el controlador ‘ILibreria’

Se deben mostrar en pantalla los mensajes que retornen los diferentes métodos.
