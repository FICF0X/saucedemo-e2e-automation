<div align="center">

# 🧪 Automatización E2E de SauceDemo

**Automatización de pruebas de UI end-to-end con Selenium, Cucumber (BDD) y el patrón Page Object.**

[![Java](https://img.shields.io/badge/Java-17%2B-007396?style=flat-square&logo=openjdk&logoColor=white)](#)
[![Selenium](https://img.shields.io/badge/Selenium-4-43B02A?style=flat-square&logo=selenium&logoColor=white)](#)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-23D96C?style=flat-square&logo=cucumber&logoColor=white)](#)
[![Maven](https://img.shields.io/badge/Maven-Wrapper-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](#)

</div>

---

## Qué es

Una suite de pruebas automatizadas que maneja un navegador real a través de los
flujos principales de [saucedemo.com](https://www.saucedemo.com/) — un sitio público
hecho para practicar automatización de UI. Está escrita **BDD-first**: cada prueba
nace como un escenario legible para el negocio y luego se conecta al navegador a
través de Page Objects reutilizables.

Demuestra tres cosas que se le piden a un ingeniero de automatización QA:

- **BDD con Gherkin** — pruebas descritas en lenguaje de negocio (`Dado / Cuando / Entonces`), en español.
- **Patrón Page Object** — los selectores de UI viven en un solo lugar por página, así
  un cambio en la interfaz toca un archivo, no todas las pruebas.
- **Selenium 4 estable** — esperas explícitas y Selenium Manager (cero configuración de drivers).

## Cobertura

| Funcionalidad | Escenarios |
|---------------|-----------|
| **Login** | inicio de sesión exitoso · usuario bloqueado · credenciales inválidas rechazadas |

## Stack

`Java 17+` · `Selenium 4` · `Cucumber 7` · `JUnit Platform` · `AssertJ` · `Maven Wrapper`

## Estructura del proyecto

```
src/test/
├── resources/features/        # Gherkin — QUÉ se prueba (lenguaje de negocio)
│   └── login.feature
└── java/com/ficf0x/saucedemo/
    ├── pages/                 # Page Objects — los selectores de cada pantalla
    ├── hooks/                 # ciclo de vida del navegador (uno por escenario)
    ├── steps/                 # pegamento: conecta el Gherkin con los Page Objects
    └── runners/               # RunCucumberTest — punto de entrada
```

## Cómo correr las pruebas

Solo necesitas un **JDK (17+)** y **Google Chrome**. Maven viene incluido vía el
wrapper, y Selenium Manager descarga el ChromeDriver correcto automáticamente.

```bash
# Linux / macOS
./mvnw test

# Windows
mvnw.cmd test
```

Por defecto el navegador corre **headless**. Para verlo manejar la UI:

```bash
# Linux / macOS
HEADLESS=false ./mvnw test

# Windows (PowerShell)
$env:HEADLESS="false"; mvnw.cmd test
```

Tras cada corrida se genera un reporte HTML legible en `target/cucumber-report.html`,
y si un escenario falla se guarda una captura en `target/screenshots/`.

## Por qué estas decisiones

- **BDD en vez de JUnit plano** — los escenarios sirven además como documentación viva
  que un interesado no técnico puede leer y validar.
- **Page Object en vez de selectores embebidos** — aísla el cambio. Cuando el HTML del
  sitio se modifica, cambia exactamente un Page Object.
- **Maven Wrapper** — cualquiera clona y corre con solo un JDK; sin instalar Maven.

## Roadmap

- Flujo de compra / checkout (carrito → datos de envío → confirmación del pedido).
- Workflow de CI (GitHub Actions) que corra la suite en headless en cada push.
- Ejecución cross-browser (Firefox, Edge) con un driver parametrizable.

## Licencia

MIT © Rafael Linares
