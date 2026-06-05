<div align="center">

# 🧪 SauceDemo E2E Automation

**End-to-end UI test automation with Selenium, Cucumber (BDD) and the Page Object Model.**

[![Java](https://img.shields.io/badge/Java-17%2B-007396?style=flat-square&logo=openjdk&logoColor=white)](#)
[![Selenium](https://img.shields.io/badge/Selenium-4-43B02A?style=flat-square&logo=selenium&logoColor=white)](#)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-23D96C?style=flat-square&logo=cucumber&logoColor=white)](#)
[![Maven](https://img.shields.io/badge/Maven-Wrapper-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](#)

</div>

---

## What this is

An automated regression suite that drives a real browser through the core user
journeys of [saucedemo.com](https://www.saucedemo.com/) — a public site built for
practising UI automation. It is written **BDD-first**: every test starts as a
business-readable scenario, then is wired to the browser through reusable Page
Objects.

It demonstrates three things a QA automation engineer is expected to know:

- **BDD with Gherkin** — tests described in plain business language (`Given / When / Then`).
- **The Page Object Model** — UI selectors live in one place per page, so a UI
  change touches one file, not every test.
- **Stable Selenium 4** — explicit waits and Selenium Manager (zero manual driver setup).

## Coverage

| Feature | Scenarios |
|---------|-----------|
| **Login** | successful sign-in · locked-out user blocked · invalid credentials rejected |

## Tech stack

`Java 17+` · `Selenium 4` · `Cucumber 7` · `JUnit Platform` · `AssertJ` · `Maven Wrapper`

## Project structure

```
src/test/
├── resources/features/        # Gherkin — WHAT is tested (business language)
│   └── login.feature
└── java/com/ficf0x/saucedemo/
    ├── pages/                 # Page Objects — the selectors for each screen
    ├── hooks/                 # browser lifecycle (created/closed per scenario)
    ├── steps/                 # glue: connects Gherkin to Page Objects
    └── runners/               # RunCucumberTest — entry point
```

## Running the tests

You only need a **JDK (17+)** and **Google Chrome**. Maven itself is bundled via
the wrapper, and Selenium Manager downloads the matching ChromeDriver on the fly.

```bash
# Linux / macOS
./mvnw test

# Windows
mvnw.cmd test
```

By default the browser runs **headless**. To watch it drive the UI:

```bash
# Linux / macOS
HEADLESS=false ./mvnw test

# Windows (PowerShell)
$env:HEADLESS="false"; mvnw.cmd test
```

A readable HTML report is written to `target/cucumber-report.html` after each run.

## Why these choices

- **BDD over plain JUnit** — the scenarios double as living documentation a
  non-technical stakeholder can read and validate.
- **Page Object Model over inline selectors** — isolates change. When the site's
  markup shifts, exactly one Page Object changes.
- **Maven Wrapper** — anyone can clone and run with just a JDK; no global Maven install.

## Roadmap

- Checkout / purchase flow (cart → delivery details → order confirmation).
- CI workflow (GitHub Actions) running the suite headless on every push.
- Cross-browser runs (Firefox, Edge) via a parameterised driver.

## License

MIT © Rafael Linares
