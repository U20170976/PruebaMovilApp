# 🛍️ PruebaMovilApp

Aplicación móvil Android desarrollada con Jetpack Compose para gestionar puntos de venta (PDV), visitas y reportes de precios. Esta app es parte de una prueba técnica.

---

## ✨ Funcionalidades principales

- Autenticación de usuario
- Visualización de lista de Puntos de Venta (PDV)
- Acceso al mapa **simulado** con imagen estática (sin API)
- Confirmación de atención al PDV
- Captura de foto (simulada)
- Registro editable de reporte de precios por producto
- Navegación fluida y componentes de Material 3

---

## ⚠️ Consideraciones Técnicas

- **No se utiliza una base de datos real**, todos los datos (PDVs, productos) están hardcodeados para agilizar el desarrollo y demostrar la lógica funcional.
- **El mapa se muestra como una imagen estática** para evitar el uso de la API de Google Maps (requiere configuración de billing).
- **La cámara está integrada mediante intent básico**, pero no se almacena ni persiste la imagen. No se implementó almacenamiento ni procesamiento adicional por tratarse de una prueba funcional.

---

## 🧪 Credenciales de acceso

| Usuario   |   Contraseña  |
|-----------|---------------|
| `admin`   |   `admin123`  |

---

## 👣 Guía de uso

1. Inicia sesión con las credenciales proporcionadas.
2. Selecciona un PDV de la lista.
3. Puedes ver su ubicación en el mapa haciendo clic en el ícono del mapa.
4. Presiona la flecha ➡️ para iniciar la visita.
5. Acepta el cuadro de confirmación.
6. Toma una foto obligatoriamente (sólo para validar flujo).
7. Presiona "VISITAR".
8. Llena los datos del reporte de precios.
9. Haz clic en el ícono 💾 para guardar y regresar al inicio.

---

## 📦 Instalación del APK

1. Abre tu emulador o dispositivo Android.
2. Instala el archivo `app-release.apk` incluido en este repositorio:
   ```bash
   adb install app-release.apk
