# Mi Aplicación Spring Boot con MongoDB

Este proyecto es una aplicación Spring Boot que utiliza MongoDB como base de datos y proporciona endpoints REST para gestionar productos.

## Instalación y Configuración

1. **Clonar el repositorio:**
   ```sh
   git clone https://github.com/tu-usuario/tu-repositorio.git
   cd tu-repositorio
   ```
2. **Configurar MongoDB:**
   - Asegúrate de tener MongoDB instalado y en ejecución en `localhost:27017`.
   - Si estás usando Docker, puedes correr MongoDB con:
     ```sh
     docker run -d -p 27017:27017 --name mongodb mongo
     ```
3. **Ejecutar la aplicación:**
   ```sh
   mvn spring-boot:run
   ```

## Cómo Ejecutar las Pruebas

El proyecto incluye pruebas unitarias y de integración para validar el correcto funcionamiento de los servicios.

### Pruebas Unitarias
Estas pruebas verifican el comportamiento individual de cada componente (servicios y repositorios) de manera aislada.

Para ejecutar las pruebas unitarias:
```sh
mvn test
```

### Pruebas de Integración
Las pruebas de integración validan el comportamiento de la aplicación completa, incluyendo la interacción con la base de datos MongoDB.

Para ejecutarlas:
```sh
mvn verify
```

### Ejecutar las pruebas en GitHub Actions
El proyecto está configurado con **GitHub Actions** para ejecutar las pruebas automáticamente en cada `push` o `pull request`.

Para ejecutar manualmente el workflow:
1. Ir a la pestaña **Actions** en el repositorio.
2. Seleccionar el workflow deseado.
3. Hacer clic en **Run workflow**.

## Propósito de las Pruebas

1. **Unitarias**: Validan que los servicios y repositorios funcionen correctamente sin depender de otros componentes.
2. **Integración**: Verifican que los endpoints REST y la conexión con MongoDB estén funcionando correctamente.
3. **CI/CD con GitHub Actions**: Garantiza que los cambios en el código no rompan funcionalidades antes de ser desplegados.

---

## Contribución
Si deseas contribuir:
1. Haz un fork del repositorio.
2. Crea una nueva rama (`feature/nueva-funcionalidad`).
3. Realiza los cambios y haz un commit (`git commit -m 'Agrega nueva funcionalidad'`).
4. Envía un pull request.

## Contacto
Para cualquier duda o sugerencia, abre un issue en el repositorio o contáctame a mi correo.

