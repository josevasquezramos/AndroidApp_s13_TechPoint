<p align="center"><a href="https://www.uns.edu.pe" target="_blank"><img src="https://upload.wikimedia.org/wikipedia/commons/1/1a/Universidad_Nacional_del_Santa_Logo.png" width="250" alt="UNS Logo"></a></p>

<p align="center">
  <a href="https://developer.android.com" target="_blank"><img src="https://img.shields.io/badge/Android-3BBC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"></a>
  <a href="https://kotlinlang.org" target="_blank"><img src="https://img.shields.io/badge/Kotlin-7F52DD?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"></a>
  <a href="https://github.com/google/gson" target="_blank"><img src="https://img.shields.io/badge/Gson-353535?style=for-the-badge&logo=json&logoColor=white" alt="Gson"></a>
</p>

<p align="center">
  <a href="https://developers.google.com/maps/documentation/android-sdk/start" target="_blank"><img src="https://img.shields.io/badge/Google%20Maps-f56c42?style=for-the-badge&logo=googlemaps&logoColor=white" alt="Google Maps"></a>
  <a href="https://developer.android.com/topic/libraries/architecture/viewmodel" target="_blank"><img src="https://img.shields.io/badge/ViewModel-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="ViewModel"></a>
  <a href="https://developer.android.com/topic/libraries/architecture/livedata" target="_blank"><img src="https://img.shields.io/badge/LiveData-4285F4?style=for-the-badge&logo=android&logoColor=white" alt="LiveData"></a>
</p>

<p align="center">
  <a href="https://m3.material.io" target="_blank"><img src="https://img.shields.io/badge/Material%20Components-777777?style=for-the-badge&logo=android&logoColor=white" alt="Material Components"></a>
  <a href="https://developer.android.com/guide/topics/ui/menus" target="_blank"><img src="https://img.shields.io/badge/Android%20Menu-777777?style=for-the-badge&logo=android&logoColor=white" alt="Android Menu"></a>
</p>

## 📍 TechPoint – Encuentra tecnología cerca de ti

Una app móvil desarrollada en **Android Studio con Kotlin**, que ayuda a los usuarios a encontrar **componentes tecnológicos** en tiendas cercanas según su ubicación. Utiliza arquitectura **MVVM**, servicios de **Google Maps**, y patrones modernos de desarrollo con **LiveData**, **ViewModel** y **Material Components**.

## 📦 Módulos de la App

### 🔎 TechPoint (módulo principal)
> ✅ Este módulo es **100% funcional**.
- 🧠 **Búsqueda predictiva** de componentes tecnológicos:
  - Mientras escribes, se sugiere automáticamente el nombre del componente con base en los disponibles en todos los establecimientos registrados.
- 📍 **Ubicación inteligente**:
  - Muestra la tienda **más cercana** a tu ubicación actual que vende ese componente.
- 🛍️ **Detalles mostrados**:
  - Nombre de la tienda
  - Dirección exacta
  - Ubicación en el mapa (Google Maps)
  - Valoración por estrellas
  - Horario de cierre

### 🏠 Inicio
> 👁️ Este módulo solo es un layout
- Pantalla de bienvenida y presentación de la aplicación.
- Solo vista, sin lógica compleja.

### 🔔 Notificaciones
> 👁️ Este módulo solo es un layout, pero sí implementa RecyclerView y Adapter
- Interfaz para mostrar futuras notificaciones sobre:
  - Nuevos componentes añadidos
  - Promociones o cambios en establecimientos

## 🎥 Video demostración

https://github.com/user-attachments/assets/83024bc7-aadb-4b50-a272-dcd6dc9427ef

## 📸 Capturas

### Tema Light

<img width="250" alt="image" src="https://github.com/user-attachments/assets/ec129a66-123d-41af-9fbe-1ae6b6ef3c86" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/b31476dd-961f-4157-9fcb-93b1081ebfc0" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/a3185bb8-7a95-4f15-9c3c-d552b8594e96" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/2abc7e23-156b-467f-ad71-ee05f98ff157" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/4c886c0d-c51b-429c-99a2-a1be4d9cfc0a" />

### Tema Dark

<img width="250" alt="image" src="https://github.com/user-attachments/assets/4709d726-07b0-4302-9638-e56a729adcec" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/94c8f1ea-d2b6-42d9-ad0d-8a694c84f7d4" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/33d9d3f1-d13a-41ea-a64b-42d68dfa7656" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/d019319c-d9ac-4e8b-b6fe-ab7f68b6ce96" />
<img width="250" alt="image" src="https://github.com/user-attachments/assets/42782b65-2b39-4bc3-b177-c82f297b0596" />

## 📦 Estructura del proyecto

```
📁 app/
├── 📁 manifests/
│   └── 📄 AndroidManifest.xml                     # Declaración de componentes, permisos y mapa
│
├── 📁 kotlin + java/
│   └── 📦 com.tupackage.techpoint/
│       ├── 📁 model/
│       │   ├── 📄 Notification.kt                 # Modelo de notificación
│       │   └── 📄 Tienda.kt                       # Modelo de tienda
│       │
│       ├── 📁 view/
│       │   ├── 📁 adapter/
│       │   │   └── 📄 NotificationAdapter.kt      # Adaptador para RecyclerView de notificaciones
│       │   │
│       │   └── 📁 ui/
│       │       ├── 📁 activities/
│       │       │   └── 📄 MainActivity.kt         # Actividad principal que aloja los fragments
│       │       │
│       │       └── 📁 fragments/
│       │           ├── 📄 HomeFragment.kt           # Fragmento para la búsqueda de componentes
│       │           ├── 📄 MapFragment.kt            # Fragmento para mostrar tienda más cercana
│       │           └── 📄 NotificationsFragment.kt  # Fragmento de vista de notificaciones
│       │
│       └── 📁 viewmodel/
│           ├── 📄 HomeViewModel.kt               # ViewModel para HomeFragment
│           ├── 📄 MapViewModel.kt                # ViewModel para MapFragment
│           └── 📄 NotificationsViewModel.kt      # ViewModel para NotificationsFragment
│
├── 📁 assets/
│   └── 📄 tienda.json                             # Datos estáticos de las tiendas registradas
│
├── 📁 res/
│   ├── 📁 layout/
│   │   ├── 📄 activity_main.xml                   # Layout principal (host de fragments)
│   │   ├── 📄 fragment_home.xml                   # UI del módulo de búsqueda
│   │   ├── 📄 fragment_map.xml                    # UI con mapa y datos de tienda
│   │   ├── 📄 fragment_notifications.xml          # Vista de notificaciones (placeholder)
│   │   └── 📄 item_notification.xml               # Ítem individual para lista de notificaciones
│   │
│   ├── 📁 menu/
│   │   └── 📄 bottom_nav_menu.xml                 # Menú inferior para navegación
│   │
│   ├── 📁 navigation/
│   │   └── 📄 mobile_navigation.xml               # Gráfico de navegación con fragments
│   │
│   ├── 📁 values/
│   │   ├── 📄 colors.xml                          # Paleta de colores
│   │   └── 📄 strings.xml                         # Cadenas de texto, incluyendo API Key
```

## ✅ Cómo correr el proyecto

### Requisitos

- Android Studio
- Gradle (usado automáticamente por Android Studio)
- Emulador o dispositivo físico con Android 7.0 (API 24) o superior
- Conexión a Internet (para servicios de mapas)

### Clonar el repositorio

1. Abre Android Studio.
2. Ve a `File > New > Project from Version Control`.
3. Ingresa la URL del repositorio:  
   `https://github.com/josevasquezramos/AndroidApp_s13_TechPoint.git`
4. Haz clic en **Clone**.
5. Android Studio descargará y abrirá el proyecto automáticamente.

### Configurar el entorno

#### Verificar SDK de Android 7.0 (API 24)

- Ve a `Tools > SDK Manager`.
- En la pestaña **SDK Platforms**, asegúrate de tener instalado:
  - ✅ Android 7.0 (Nougat)
- En la pestaña **SDK Tools**, verifica que estén instalados:
  - ✅ Android SDK Build-Tools  
  - ✅ Android Emulator  
  - ✅ Android SDK Platform-Tools  

#### Crear o seleccionar un emulador compatible

1. Ve a `Tools > Device Manager`.
2. Crea un nuevo dispositivo virtual (AVD) con Android 7.0 o selecciona uno ya existente.
3. Inicia el emulador.

### 🔑 Configurar Google Maps API Key

1. Ve a [Google Cloud Console](https://console.cloud.google.com/).
2. Crea un nuevo proyecto o selecciona uno existente.
3. Activa la **API de Maps SDK for Android**.
4. Ve a **APIs & Services > Credentials** y crea una **API Key**.
5. Copia la clave generada.

6. En tu proyecto, abre el archivo:

```
app/src/main/res/values/strings.xml
```

7. Añade o reemplaza la siguiente línea dentro de `<resources>`:

```xml
<string name="google_maps_key">TU_API_KEY_AQUI</string>
```

> 🔐 **Recomendación**: No publiques tu API Key en repositorios públicos sin restricciones.

### ▶️ Ejecutar la aplicación

1. Asegúrate de que el emulador esté corriendo o que tu dispositivo esté conectado.
2. Haz clic en el botón **Run** (`▶️`) en la barra superior de Android Studio.
3. Selecciona el dispositivo deseado.
4. La app se compilará, instalará y ejecutará automáticamente.

___

**Alumno:** Jose Manuel Vasquez Ramos  
**Asignatura:** Aplicaciones Móviles
