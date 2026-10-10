# Análisis de la Arquitectura Backend

## 1. Contexto

El backend del Proyecto Integrador inicialmente se encontraba organizado bajo un enfoque de arquitectura por capas.

Como parte de la evolución del proyecto, se realizó una reorganización orientada hacia una Arquitectura Hexagonal (Ports and Adapters), con el objetivo de separar las reglas de negocio de los detalles de infraestructura y mejorar la mantenibilidad, escalabilidad y organización del sistema.

Esta reorganización busca establecer una separación clara entre el dominio, la lógica de aplicación y los componentes externos que interactúan con el sistema.

## 2. Arquitectura anterior

El proyecto inicialmente utilizaba una organización basada en capas, donde los componentes se distribuían de acuerdo con sus responsabilidades técnicas.

Durante la evolución del backend se identificó la necesidad de mejorar la separación entre las reglas de negocio y los detalles relacionados con la infraestructura.

Como resultado, se inició una refactorización de la estructura existente hacia una Arquitectura Hexagonal, permitiendo organizar el proyecto mediante un núcleo de aplicación, puertos y adaptadores.

## 3. Arquitectura actual

Actualmente el backend se encuentra organizado siguiendo los principios de Arquitectura Hexagonal, separando principalmente el dominio, la aplicación y la infraestructura.

### Dominio

Contiene los principales modelos y conceptos relacionados con las reglas del negocio del Proyecto Integrador.

Dentro del dominio se encuentran modelos como:

- `Grade`
- `Group`
- `GroupSubject`
- `Period`
- `Program`
- `Role`
- `Student`
- `Subject`
- `Teacher`
- `User`
- `UserRole`
- `Enrollment`
- `Course`

Estos modelos representan diferentes conceptos del sistema, como estudiantes, docentes, usuarios, roles, programas académicos, períodos, grupos, asignaturas, matrículas y calificaciones.

El objetivo de esta capa es mantener los conceptos principales del negocio independientes de los detalles tecnológicos y de infraestructura.

### Aplicación

Contiene la lógica necesaria para coordinar los casos de uso del sistema y establecer la comunicación entre el dominio y los componentes externos.

En esta capa se organizan elementos como los servicios de aplicación, DTO y los puertos de entrada y salida.

Los servicios de aplicación utilizan las reglas y modelos del dominio para ejecutar las operaciones correspondientes, mientras que los puertos permiten establecer contratos para la comunicación con otros componentes.

### Infraestructura

Contiene los componentes encargados de conectar la aplicación con tecnologías y servicios externos.

Esta capa incluye los adaptadores y componentes relacionados con la persistencia, configuración y otras implementaciones concretas necesarias para ejecutar el sistema.

Los adaptadores implementan los contratos definidos mediante los puertos de la aplicación, evitando que la lógica principal dependa directamente de una tecnología específica.

## 4. Puertos y adaptadores

La Arquitectura Hexagonal utiliza el concepto de puertos y adaptadores para separar la lógica de negocio de las implementaciones externas.

### Puertos de entrada

Los puertos de entrada representan las operaciones que la aplicación ofrece a los componentes externos.

Estos puertos permiten acceder a los casos de uso de la aplicación mediante contratos definidos en la capa de aplicación, evitando depender directamente de las implementaciones concretas de los servicios.

### Puertos de salida

Los puertos de salida representan las operaciones que la aplicación necesita utilizar para comunicarse con componentes externos.

Entre estos componentes se encuentran los contratos relacionados con los repositorios, que permiten a la aplicación solicitar operaciones de persistencia sin conocer directamente la implementación utilizada.

### Adaptadores

Los adaptadores son las implementaciones concretas de los puertos y permiten conectar la aplicación con los componentes externos.

Estos pueden encargarse de aspectos como la persistencia de datos, comunicación con bases de datos y otras tecnologías utilizadas por el backend.

Esta separación permite cambiar una implementación externa sin modificar directamente las reglas principales de la aplicación.

## 5. Dependencias

La arquitectura busca establecer una dirección controlada de las dependencias.

El dominio debe permanecer independiente de la infraestructura y de tecnologías externas.

La capa de aplicación utiliza los modelos del dominio y define los contratos necesarios para comunicarse con componentes externos mediante los puertos.

La infraestructura implementa estos contratos mediante adaptadores concretos.

De esta manera, los detalles de infraestructura dependen de las abstracciones definidas por la aplicación y no directamente de las reglas del negocio.

La relación general de las capas puede representarse de la siguiente manera:

```text
        ┌─────────────────────────┐
        │      Infraestructura    │
        │       Adaptadores       │
        └────────────┬────────────┘
                     │
                     ▼
        ┌─────────────────────────┐
        │       Aplicación        │
        │ Servicios / Puertos     │
        └────────────┬────────────┘
                     │
                     ▼
        ┌─────────────────────────┐
        │         Dominio         │
        │    Modelos / Negocio    │
        └─────────────────────────┘