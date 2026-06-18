# Resumen de Tareas Realizadas (Android Studio)

Este documento detalla los cambios, implementaciones y justificaciones para las tres tareas asignadas.

---

## 1. Pantalla Principal (MainActivity - Lista de Productos)

### Qué se hizo:
* Se implementó una lista estática de 6 productos en la pantalla principal.
* Cada ítem contiene nombre, precio formateado y una imagen representativa del sistema.
* Al hacer clic en un producto se muestra un `Toast` con el nombre y precio formateado.

### Cómo y por qué:
* **Modelo ([Producto.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/Producto.java))**: Una clase POJO simple e inmutable que almacena las propiedades requeridas (`nombre`, `precio`, `imagenResId`).
* **Diseño ([item_producto.xml](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/res/layout/item_producto.xml))**: Un diseño horizontal con un `ImageView` para la imagen, alineado con un sub-contenedor vertical para el nombre y el precio, optimizando el espacio visual de la lista.
* **Adaptador ([ProductoAdapter.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/ProductoAdapter.java))**: Extiende `RecyclerView.Adapter` y mapea las propiedades a las vistas. El precio se formatea rigurosamente como `"S/ %.2f"` con `Locale.US` para garantizar consistencia decimal. El listener de clics está enlazado directamente en `onBindViewHolder` para capturar la interacción y lanzar el `Toast` solicitado.

---

## 2. Gestión de Tareas (Room Database)

### Qué se hizo:
* Módulo local completo utilizando la biblioteca Room para registrar, listar y eliminar tareas mediante pulsación prolongada.

### Cómo y por qué:
* **Entidad ([Tarea.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/Tarea.java))**: Anotada con `@Entity(tableName = "tareas")` con claves primarias autogeneradas y columnas específicas de acuerdo a los requisitos del ejercicio.
* **Acceso a Datos ([TareaDao.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/TareaDao.java))**: Define las consultas e inserciones de base de datos con las anotaciones de Room `@Insert`, `@Query` y `@Delete`.
* **Base de Datos ([AppDatabase.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/AppDatabase.java))**: Extiende `RoomDatabase` implementando un singleton seguro contra hilos. Define un `ExecutorService` de fondo (`databaseExecutor`) para aislar operaciones del hilo principal (UI Thread), evitando bloqueos y errores del sistema.
* **Vista y Lógica ([TareasActivity.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/TareasActivity.java))**:
  * Contiene los inputs de texto y el RecyclerView para visualización inmediata.
  * Todas las escrituras y lecturas de base de datos se ejecutan en el hilo del executor y actualizan la interfaz visual dentro del método `runOnUiThread()`.
  * La eliminación se gestiona asignando un `OnLongClickListener` a los elementos de la lista en el adaptador ([TareaAdapter.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/TareaAdapter.java)).

---

## 3. Consumo de API (JSONPlaceholder Users)

### Qué se hizo:
* Consumo asíncrono del endpoint `https://jsonplaceholder.typicode.com/users` para listar usuarios.

### Cómo y por qué:
* **Configuración del Manifiesto ([AndroidManifest.xml](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/AndroidManifest.xml))**: Se agregó la etiqueta `<uses-permission android:name="android.permission.INTERNET" />` necesaria para habilitar la transmisión de datos HTTP/HTTPS en la aplicación.
* **Lógica de Petición ([UsuariosApiActivity.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/UsuariosApiActivity.java))**: Usa `JsonArrayRequest` de Volley. El parsing de `id`, `name` y `email` se realiza directamente dentro del callback `onResponse` (hilo secundario manejado por Volley) instanciando los modelos `Usuario` e insertándolos a la lista para finalmente notificar al adaptador.
* **Adaptador ([UsuarioApiAdapter.java](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/java/com/example/practica/UsuarioApiAdapter.java))**: Diseñado específicamente para enlazar los datos extraídos de la API a su respectivo layout ([item_usuario_api.xml](file:///home/andre/Academics/Moviles/Proyectos/practica/app/src/main/res/layout/item_usuario_api.xml)), mostrando ID, Nombre y Correo Electrónico.

---

## 4. Limpieza de Código (YAGNI / Ponytail)

### Qué se hizo:
* Eliminación de archivos heredados huérfanos que no tenían relación directa con la tarea actual.

### Cómo y por qué:
* Se removieron archivos como `DetalleActivity.java`, `ListaActivity.java`, `UsuarioAdapter.java`, `UserManager.java` y sus correspondientes XML de layout.
* Se limpió `main_menu.xml` para conservar únicamente los accesos directos a las pantallas de **Gestión de Tareas** e **Usuarios API**.
* Se eliminaron cadenas de caracteres sin uso en `strings.xml`.
* Esta limpieza se fundamenta en principios de simplicidad y YAGNI (You Aren't Gonna Need It), disminuyendo el acoplamiento y el peso de compilación final de la aplicación.
