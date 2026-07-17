# Project Inside Out

# Instrucciones
Se os ha encargado la creación de una aplicación de consola con la cual el usuario podrá gestionar momentos vividos, Mi Diario. Cada momento tendrá una emoción asignada junto con la fecha de cuando ocurrio.

Cada momento vivído tendrá un identificador, un título, una descripción, una emoción, fecha del momento, fecha de creación, fecha de modificación.

Listado de emociones:
1. Alegría
2. Tristeza
3. Ira
4. Asco
5. Miedo
6. Ansiedad
7. Envidia
8. Vergüenza
9. Aburrimiento
10. Nostalgia

Por cada historia de usuario se deberán redactar los criterios de aceptación.

# Historias de usuario
1. COMO usuario QUIERO añadir un momento vivido PARA poder visualisarlo cuando lo necesite recordar

2. COMO usuario QUIERO recuperar la lista de lo momentos vividos registrados PARA poder repasarlos

3. COMO usuario QUIERO suprimir un momento vivido PARA evitar duplicados y mantener la lista de momentos organizada

4. COMO usuario QUIERO obtener los momentos vividos según su emoción PARA poder visualizarlos

5. COMO usuario QUIERO obtener los momentos vividos en un mes determinado

6. COMO usuario QUIERO salir del programa PARA poder iniciar otro

# Ejemplo de interacción con la consola

```
My diario:
1. Añadir momento
2. Ver todos los momentos disponibles
3. Eliminar un momento
4. Filtrar los momentos
5. Salir
Seleccione una opción: 1

Ingrese el título: Un día en el parque de atracciones
Ingresa la fecha (dd/mm/year): 01/05/2024
Ingrese la descripción: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sed eros vel massa scelerisque convallis interdum ut purus.

Selecciona una emoción:
1. Alegría
2. Tristeza
3. Ira
4. Asco
5. Miedo
6. Ansiedad
7. Envidia
8. Vergüenza
9. Aburrimiento
10. Nostalgia
Ingrese su opción: 1
Momento vivído añadido correctamente.

My diario:
1. Añadir momento
2. Ver todos los momentos disponibles
3. Eliminar un momento
4. Filtrar los momentos
5. Salir
Seleccione una opción: 2

Lista de momentos vividos:
1. Ocurrio el: 01/01/2024. Título: Un día en el parque de atracciones. Descripción: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sed eros vel massa scelerisque convallis interdum ut purus. Emoción: Alegría

My diario:
1. Añadir momento
2. Ver todos los momentos disponibles
3. Eliminar un momento
4. Filtrar los momentos
5. Salir
Seleccione una opción: 3

Ingresa el identificador del momento: 1
Momento vivído eliminado correctamente.

My diario:
1. Añadir momento
2. Ver todos los momentos disponibles
3. Eliminar un momento
4. Filtrar los momentos
5. Salir
Seleccione una opción: 4

Filtar por ...:
1. Emoción
2. Fecha
Ingrese una opción: 1

Selecciona una emoción:
1. Alegría
2. Tristeza
3. Ira
4. Asco
5. Miedo
6. Ansiedad
7. Envidia
8. Vergüenza
9. Aburrimiento
10. Nostalgia
Ingrese su opción: 1

Lista de momentos vividos:
1. Ocurrio el: 01/01/2024. Título: Un día en el parque de atracciones. Descripción: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sed eros vel massa scelerisque convallis interdum ut purus. Emoción: Alegría

My diario:
1. Añadir momento
2. Ver todos los momentos disponibles
3. Eliminar un momento
4. Filtrar los momentos
5. Salir
Seleccione una opción: 4

Filtrar por ...:
1. Emoción
2. Fecha
Ingrese una opción: 2

Ingrese la fecha (dd/mm/year): 01/01/2024

Lista de momentos vividos:
1. Ocurrio el: 01/01/2024. Título: Un día en el parque de atracciones. Descripción: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sed eros vel massa scelerisque convallis interdum ut purus. Emoción: Alegría

My diario:
1. Añadir momento
2. Ver todos los momentos disponibles
3. Eliminar un momento
4. Filtrar los momentos
5. Salir
Seleccione una opción: 5

Hasta la próxima!!!
```

# 🏁 Rúbrica de evaluación
- <strong>Interfaz de usuario</strong>
    - La interfaz de usuario permite añadir (10%)
    - La interfaz de usuario permite visualizar todos los momentos (10%)
    - La interfaz de usuario permite eliminar un momento (10%)
    - La interfaz de usuario permite filtrar por emoción (10%)
    - La interfaz de usuario permite filtrar por fecha (10%)

- <strong>Persistencia de datos</strong>
    - Se hace un uso adecuado de la interface Map\<E>. (10%)

- <strong>Código y Buenas Prácticas</strong>
    - Tests de cobertura mínimo un 70% (20%)
    - El código está bien estructurado (10%)
    - Correcta separación de responsabiidades. S de "SOLID" (10%)

