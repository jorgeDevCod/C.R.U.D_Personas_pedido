
# Documentación de la API REST de TareaApi

Esta documentación proporciona una guía sobre cómo utilizar la API REST de TareaApi, incluyendo los endpoints disponibles para gestionar personas y pedidos.

## Base URL
```
http://localhost:8080
```

## Endpoints de Persona

### 1. Crear Persona
- **Método:** `POST`
- **Endpoint:** `/personas/v1/crear`
- **Descripción:** Crea una nueva persona.
- **Cuerpo de la Solicitud:**
```json
{
  "nombres": "Juan",
  "apellidos": "Pérez",
  "numeroDocumento": 12345678,
  "estado": "ACTIVO",
  "direccion": {
    "calle": "Av. Siempre Viva",
    "numero": "742",
    "ciudad": "Springfield"
  }
}
```
- **Respuesta:**
  - **Código 201 Created:** Persona creada exitosamente.
  - **Ejemplo de Respuesta:**
```json
{
  "id": 1,
  "nombres": "Juan",
  "apellidos": "Pérez",
  "numeroDocumento": 12345678,
  "estado": "ACTIVO"
}
```

### 2. Buscar Todos las Personas
- **Método:** `GET`
- **Endpoint:** `/personas/v1/buscarTodos`
- **Descripción:** Obtiene una lista de todas las personas.
- **Respuesta:**
  - **Código 200 OK:** Devuelve una lista de `PersonaEntity`.
  - **Ejemplo de Respuesta:**
```json
[
  {
    "id": 1,
    "nombres": "Juan",
    "apellidos": "Pérez",
    "numeroDocumento": 12345678,
    "estado": "ACTIVO"
  },
  {
    "id": 2,
    "nombres": "Ana",
    "apellidos": "García",
    "numeroDocumento": 87654321,
    "estado": "ACTIVO"
  }
]
```

### 3. Buscar Persona por Número de Documento
- **Método:** `GET`
- **Endpoint:** `/personas/v1/buscarPersonaXnumDocumento/{numeroDocumento}`
- **Descripción:** Busca una persona por su número de documento.
- **Ejemplo de URL:**
```
/personas/v1/buscarPersonaXnumDocumento/12345678
```
- **Respuesta:**
  - **Código 200 OK:** Devuelve la `PersonaEntity` correspondiente.
  - **Código 404 Not Found:** Si no se encuentra la persona.
  - **Ejemplo de Respuesta:**
```json
{
  "id": 1,
  "nombres": "Juan",
  "apellidos": "Pérez",
  "numeroDocumento": 12345678,
  "estado": "ACTIVO"
}
```

### 4. Actualizar Persona
- **Método:** `PUT`
- **Endpoint:** `/personas/v1/actualizarPersona/{id}`
- **Descripción:** Actualiza la información de una persona existente.
- **Ejemplo de URL:**
```
/personas/v1/actualizarPersona/1
```
- **Cuerpo de la Solicitud:**
```json
{
  "nombres": "Juan",
  "apellidos": "Pérez",
  "numeroDocumento": 12345678,
  "estado": "ACTIVO"
}
```
- **Respuesta:**
  - **Código 200 OK:** Persona actualizada exitosamente.
  - **Ejemplo de Respuesta:**
```json
{
  "id": 1,
  "nombres": "Juan",
  "apellidos": "Pérez",
  "numeroDocumento": 12345678,
  "estado": "ACTIVO"
}
```

### 5. Eliminar Persona
- **Método:** `DELETE`
- **Endpoint:** `/personas/v1/eliminarPersona/{id}`
- **Descripción:** Elimina una persona por su ID.
- **Ejemplo de URL:**
```
/personas/v1/eliminarPersona/1
```
- **Respuesta:**
  - **Código 204 No Content:** Persona eliminada exitosamente.

---

## Endpoints de Pedido

### 1. Crear Pedido
- **Método:** `POST`
- **Endpoint:** `/pedidos/v1/crear/{personaId}`
- **Descripción:** Crea un nuevo pedido asociado a una persona.
- **Ejemplo de URL:**
```
/pedidos/v1/crear/1
```
- **Cuerpo de la Solicitud:**
```json
{
  "estado": "PENDIENTE" // Otros estados: PROCESO, CONFIRMADO, ELIMINADO
}
```
- **Respuesta:**
  - **Código 201 Created:** Pedido creado exitosamente.
  - **Ejemplo de Respuesta:**
```json
{
  "id": 1,
  "estado": "PENDIENTE",
  "personaId": 1
}
```

### 2. Buscar Todos los Pedidos
- **Método:** `GET`
- **Endpoint:** `/pedidos/v1/buscarTodos`
- **Descripción:** Obtiene una lista de todos los pedidos filtrados por estado.
- **Parámetro de Consulta:**
```
estado: "PENDIENTE" // o cualquier otro estado
```
- **Ejemplo de URL:**
```
/pedidos/v1/buscarTodos?estado=PENDIENTE
```
- **Respuesta:**
  - **Código 200 OK:** Devuelve una lista de `PedidoEntity`.
  - **Ejemplo de Respuesta:**
```json
[
  {
    "id": 1,
    "estado": "PENDIENTE",
    "personaId": 1
  },
  {
    "id": 2,
    "estado": "CONFIRMADO",
    "personaId": 2
  }
]
```

### 3. Buscar Pedido por Parámetro
- **Método:** `GET`
- **Endpoint:** `/pedidos/v1/buscarPedidoPorParametro`
- **Descripción:** Busca un pedido por ID o estado.
- **Parámetros de Consulta:**
```
id: 1
estado: "PENDIENTE"
```
- **Ejemplo de URL:**
```
/pedidos/v1/buscarPedidoPorParametro?id=1
```
- **Respuesta:**
  - **Código 200 OK:** Devuelve el `PedidoEntity` correspondiente.
  - **Ejemplo de Respuesta:**
```json
{
  "id": 1,
  "estado": "PENDIENTE",
  "personaId": 1
}
```

### 4. Actualizar Pedido
- **Método:** `PUT`
- **Endpoint:** `/pedidos/v1/actualizarPedido/{id}/persona/{personaId}`
- **Descripción:** Actualiza la información de un pedido existente.
- **Ejemplo de URL:**
```
/pedidos/v1/actualizarPedido/1/persona/1
```
- **Cuerpo de la Solicitud:**
```json
{
  "estado": "CONFIRMADO" // Cambia el estado según sea necesario
}
```
- **Respuesta:**
  - **Código 200 OK:** Pedido actualizado exitosamente.
  - **Ejemplo de Respuesta:**
```json
{
  "id": 1,
  "estado": "CONFIRMADO",
  "personaId": 1
}
```

### 5. Eliminar Pedido
- **Método:** `DELETE`
- **Endpoint:** `/pedidos/v1/eliminarPedido/{id}`
- **Descripción:** Elimina un pedido por su ID.
- **Ejemplo de URL:**
```
/pedidos/v1/eliminarPedido/1
```
- **Respuesta:**
  - **Código 204 No Content:** Pedido eliminado exitosamente.

---

## Notas Adicionales
- Asegúrate de que la base de datos esté en ejecución y correctamente configurada.
- Usa herramientas como Postman para enviar las solicitudes y observar las respuestas.
