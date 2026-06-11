# 🚗 Garage Mardel — Sistema de Concesionaria

Aplicación de consola desarrollada en **Java** que permite gestionar el inventario de vehículos de una concesionaria. El sistema persiste los datos en un archivo **JSON** y ofrece un menú interactivo para realizar altas, consultas, filtros y financiamiento.

---

## 📁 Estructura del Proyecto

```
TrabajoFinal/
├── consecionario.json          # Base de datos de vehículos (persistencia)
├── TrabajoFinal.iml            # Archivo de configuración IntelliJ IDEA
└── src/
    ├── Main.java               # Punto de entrada de la aplicación
    ├── Clases/
    │   ├── Vehiculo.java       # Clase abstracta base
    │   ├── Auto.java           # Subclase Auto
    │   ├── Moto.java           # Subclase Moto
    │   ├── Camioneta.java      # Subclase Camioneta
    │   ├── Motor.java          # Clase Motor (composición)
    │   └── Inventario.java     # Gestión del inventario + menú
    ├── Interfaces/
    │   └── Financiamiento.java # Interfaz para cálculo de financiamiento
    ├── Excepciones/
    │   ├── ErrorDuplicado.java # ID duplicado al registrar vehículo
    │   ├── errorNumerico.java  # Valores numéricos inválidos
    │   └── errorTipoDato.java  # Tipo de dato incorrecto
    └── usoJson/
        ├── GestionJson.java    # Serialización/deserialización JSON
        └── JSONUtiles.java     # Lectura y escritura del archivo JSON
```

---

## 🧱 Arquitectura y Diseño

### Jerarquía de clases

```
Vehiculo (abstracta) ← implements Financiamiento, Comparable<Vehiculo>
    ├── Auto
    ├── Moto
    └── Camioneta

Inventario<T extends Vehiculo>   → gestiona la lista y el menú
Consecionaria                    → contiene nombre + Inventario
Motor                            → composición dentro de Vehiculo
```

### Principios aplicados

- **Herencia y polimorfismo**: `Auto`, `Moto` y `Camioneta` extienden `Vehiculo` y sobreescriben `calcularFinanciamiento()`.
- **Interfaz**: `Financiamiento` define el contrato de financiamiento aplicado a cada tipo de vehículo.
- **Genéricos**: `Inventario<T extends Vehiculo>` permite tipado seguro.
- **Comparable**: `Vehiculo` implementa `compareTo` para ordenar por precio descendente.
- **Excepciones personalizadas**: cubren IDs duplicados, valores negativos y tipos de dato inválidos.

---

## ⚙️ Funcionalidades

| Opción | Descripción |
|--------|-------------|
| 1 | Agregar un nuevo vehículo (auto, moto o camioneta) al inventario |
| 2 | Mostrar todos los vehículos con detalle completo |
| 3 | Filtrar por tipo (auto / moto / camioneta) |
| 4 | Ordenar el inventario por precio (mayor a menor) |
| 5 | Ver planes de financiamiento por tipo de vehículo |
| 6 | Filtrar por marca |
| 7 | Filtrar por kilometraje máximo |
| 8 | Adquirir (eliminar) un vehículo del inventario por ID |
| 0 | Salir |

### Planes de financiamiento

| Tipo       | Adelanto | Resto financiado |
|------------|----------|-----------------|
| Auto       | 50%      | 50%             |
| Moto       | 40%      | 60%             |
| Camioneta  | 60%      | 40%             |

---

## 💾 Persistencia JSON

El archivo `consecionario.json` almacena toda la información con la siguiente estructura:

```json
{
  "consecionaria": {
    "nombre": "Garage Mardel",
    "listaVehiculos": [
      {
        "id": 1,
        "tipo": "auto",
        "marca": "Toyota",
        "modelo": "Corolla",
        "ano": 2015,
        "color": "gris",
        "kms": 85000,
        "precio": 9500,
        "motor": {
          "tipo": "nafta",
          "cilindros": 4,
          "potencia": 132
        },
        "descripcion": ["único dueño", "service oficial"]
      }
    ]
  }
}
```

La clase `GestionJson` se encarga de:
- **`mapConsecionaria()`**: lee el JSON al iniciar y carga todos los vehículos en memoria.
- **`actualizaJson(Vehiculo)`**: agrega un nuevo vehículo al JSON sin reescribir todo el archivo.
- **`toJson(Consecionaria)`**: serializa el estado completo de la concesionaria.

La clase `JSONUtiles` provee los métodos de bajo nivel `leer()` y `grabar()` usando `FileReader` / `FileWriter` y la biblioteca `org.json`.

---

## 🧪 Validaciones al registrar un vehículo

Al usar la opción **Agregar vehículo**, el sistema valida:

- El ID debe ser un entero positivo y no puede estar duplicado → `ErrorDuplicado`, `errorNumerico`
- Color, marca y modelo no pueden ser valores numéricos → `errorTipoDato`
- El año debe estar entre 0 y 2025 → `errorNumerico`
- Los kilómetros y el precio no pueden ser negativos → `errorNumerico`
- Los cilindros deben estar entre 2 y 16 → `errorNumerico`
- El tipo de motor debe ser `"nafta"` o `"diesel"` → `errorTipoDato`
- La potencia no puede ser negativa → `errorNumerico`

---

## 🚀 Cómo ejecutar

### Requisitos

- Java 8 o superior
- Biblioteca `org.json` (incluir en el classpath o como dependencia)
- IntelliJ IDEA (recomendado, el proyecto incluye `.iml`)

### Pasos

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/SpinaNicolas/TrabajoFinal.git
   ```
2. Abrir el proyecto en IntelliJ IDEA.
3. Agregar la dependencia `org.json` (por ejemplo, desde Maven Central).
4. Asegurarse de que `consecionario.json` esté en el directorio de trabajo (raíz del proyecto).
5. Ejecutar `Main.java`.

---

## 📦 Dependencias

| Biblioteca | Uso |
|------------|-----|
| `org.json`  | Parseo y construcción de objetos JSON |

---

## 👤 Autor

**Nicolas Spina**
**Facundo Gregorio**
