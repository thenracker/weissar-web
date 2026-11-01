#  Web Petr Weissar

Osobní web postavený na **Kotlinu** – frontend v **Compose HTML** (Kotlin/JS), backend **Ktor** (Netty).

> Běží na **https://weissar.dev** (GitHub Pages, automaticky se nasazuje z větve `main`)

## Struktura projektu

```
web/                          # Frontend – Compose HTML, kompiluje se do web.js
  src/jsMain/kotlin/weissar/web/
    Main.kt                   # vstupní bod, skládá sekce pod sebe
    theme/Colors.kt           # barvy
    theme/AppStyles.kt        # CSS styly (layout, tlačítka, …)
    components/               # znovupoužitelné komponenty (Section, Title, Body, Bullet, …)
    sections/                 # jednotlivé sekce stránky (Intro, Skills, MoreSkills, Svatebka, Univerzita, Projects, Education, Contact)
    data/Projects.kt          # karty v sekci „Spolupracoval jsem na“
    i18n/Localization.kt      # přepínání CZ / EN, texty se píšou jako tr("česky", "anglicky")
  src/jsMain/resources/       # index.html, obrázky, svg, statické html stránky
server/                       # Ktor server – servíruje zbuildovaný web + /health
Dockerfile                    # build + běh serveru v kontejneru
```

## Vývoj

Potřebuješ jen **JDK 21** (Gradle i Node si to stáhne samo).

```bash
# Frontend s hot reloadem – otevře http://localhost:8080, změny v Kotlinu se hned projeví
./gradlew :web:jsBrowserDevelopmentRun --continuous

# Celý web přes Ktor server (stejně jako v produkci) – http://localhost:8080
./gradlew :server:run

# Testy serveru
./gradlew :server:test
```

### Přidání nové sekce

1. Vytvoř `web/src/jsMain/kotlin/weissar/web/sections/NovaSection.kt` (vzor: `SvatebkaSection.kt`).
2. Přidej `NovaSection()` do `Main.kt`.
3. Obrázky dej do `web/src/jsMain/resources/` a odkazuj je jen názvem souboru (`"obrazek.svg"`).

### Přidání API endpointu

Do `server/src/main/kotlin/weissar/server/Application.kt` do bloku `routing { }`:

```kotlin
get("/api/hello") {
    call.respondText("Ahoj!")
}
```

## Nasazení a doména

Viz **[docs/DOMENA_A_HOSTING.md](docs/DOMENA_A_HOSTING.md)**.

