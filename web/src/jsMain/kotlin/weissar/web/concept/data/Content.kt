package weissar.web.concept.data

import weissar.web.i18n.Translated

class Column(
    val icon: String,
    val title: Translated,
    val text: Translated,
    val chips: List<Translated>,
)

class EducationRow(
    val years: String,
    val school: String,
    val detail: Translated,
)

val technologies = listOf("Jetpack Compose", "Kotlin Multiplatform", "Github Actions", "Room", "Material 3", "Kotlin", "Firebase")

val expertise = listOf(
    Column(
        icon = "android",
        title = Translated("Android", "Android"),
        text = Translated(
            "Nativní aplikace od roku 2015. Jetpack Compose, Material 3, Wear OS a řešení postavená pro dlouhodobý rozvoj.",
            "Native apps since 2015. Jetpack Compose, Material 3, Wear OS and solutions built to grow for years.",
        ),
        chips = listOf(Translated("Kotlin", "Kotlin"), Translated("Compose", "Compose"), Translated("Wear OS", "Wear OS")),
    ),
    Column(
        icon = "account_tree",
        title = Translated("Architektura", "Architecture"),
        text = Translated(
            "Čitelné datové toky, oddělené odpovědnosti a správná míra abstrakce pro tým i konkrétní produkt.",
            "Readable data flows, separated responsibilities and the right level of abstraction for the team and the product.",
        ),
        chips = listOf(Translated("MVVM", "MVVM"), Translated("MVI", "MVI"), Translated("Clean", "Clean")),
    ),
    Column(
        icon = "design_services",
        title = Translated("Produkt & UX/UI", "Product & UX/UI"),
        text = Translated(
            "Analýza zadání, struktura funkcí, prototypování a rozhraní, které pomáhá uživatelům bez vysvětlování.",
            "Requirements analysis, feature structure, prototyping and interfaces that help users without explaining.",
        ),
        chips = listOf(Translated("Analýza", "Analysis"), Translated("Figma", "Figma"), Translated("Material 3", "Material 3")),
    ),
)

val experience = listOf(
    Column(
        icon = "account_balance",
        title = Translated("Air Bank", "Air Bank"),
        text = Translated("Android vývoj pro mobilní bankovnictví MyAir.", "Android development of the MyAir mobile banking."),
        chips = listOf(Translated("2023 — nyní", "2023 — now")),
    ),
    Column(
        icon = "shopping_cart",
        title = Translated("Tesco", "Tesco"),
        text = Translated("Integrace s Delivery Hero a XML feedy produktů.", "Delivery Hero integration and XML product feeds."),
        chips = listOf(Translated("2022 — 2023", "2022 — 2023")),
    ),
    Column(
        icon = "point_of_sale",
        title = Translated("DAMI development", "DAMI development"),
        text = Translated(
            "Android lead pro pokladní systém, sklad, sociální sítě a logistiku.",
            "Android lead for a point of sale system, warehouse, social networks and logistics.",
        ),
        chips = listOf(Translated("2019 — 2023", "2019 — 2023")),
    ),
)

val educationRows = listOf(
    EducationRow("2021 — 2023", "Univerzita Hradec Králové", Translated("Ing. et Ing. — Data Science, s vyznamenáním", "Ing. et Ing. — Data Science, with honors")),
    EducationRow("2018 — 2019", "Univerzita Hradec Králové", Translated("Doktorské studium a výuka", "Doctoral studies & teaching")),
    EducationRow("2017", "Huawei Seeds for the Future", Translated("Stáž v laboratořích Huawei v Číně", "Trainee in Huawei labs in China")),
    EducationRow("2017", "Chang Jung Christian University", Translated("Letní škola na Tchaj-wanu", "Summer school in Taiwan")),
    EducationRow("2016", "City University of Hong Kong", Translated("Vývoj pro Android v Hongkongu", "Android development in Hong Kong")),
    EducationRow("2013 — 2018", "Univerzita Hradec Králové", Translated("Bc. a Ing. — Aplikovaná informatika, s vyznamenáním", "Bc. & Ing. — Applied informatics, with honors")),
)
