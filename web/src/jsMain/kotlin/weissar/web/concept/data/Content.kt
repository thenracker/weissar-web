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
            "Nativní aplikace na míru – od návrhu až po vydání na Google Play. Převezmu i rozjetou aplikaci a dám ji do pořádku.",
            "Custom native apps – from design all the way to Google Play. I'll also take over an existing app and get it back in shape.",
        ),
        chips = listOf(Translated("Jetpack Compose", "Jetpack Compose"), Translated("Wear OS", "Wear OS"), Translated("MVVM / MVI", "MVVM / MVI")),
    ),
    Column(
        icon = "dns",
        title = Translated("Backend", "Backend"),
        text = Translated(
            "API a servery v Ktoru, sdílená logika pro Android i iOS a napojení aplikace na AI přes Claude API.",
            "APIs and servers in Ktor, shared logic for Android & iOS and connecting the app to AI through the Claude API.",
        ),
        chips = listOf(Translated("Ktor", "Ktor"), Translated("Multiplatform", "Multiplatform"), Translated("PostgreSQL", "PostgreSQL")),
    ),
    Column(
        icon = "design_services",
        title = Translated("UX/UI konzultace", "UX/UI consulting"),
        text = Translated(
            "Projdu s vámi zadání, navrhnu obrazovky ve Figmě a udělám klikací prototyp. Nebo se podívám na hotovou aplikaci a ukážu, kde uživatelé zbytečně tápou.",
            "We go through the brief together, I design the screens in Figma and build a clickable prototype. Or I review your app and show where users get lost.",
        ),
        chips = listOf(Translated("Analýza", "Analysis"), Translated("Figma", "Figma"), Translated("Prototyp", "Prototype")),
    ),
)

val experience = listOf(
    Column(
        icon = "account_balance",
        title = Translated("Air Bank", "Air Bank"),
        text = Translated(
            "Spolupracuji na vývoji MyAir, mobilního bankovnictví Air Bank.",
            "I'm working on MyAir, the mobile banking app of Air Bank.",
        ),
        chips = listOf(Translated("2023 — nyní", "2023 — now")),
    ),
    Column(
        icon = "shopping_cart",
        title = Translated("Tesco", "Tesco"),
        text = Translated(
            "Odbočka k backendu – servery v Kotlinu a Ktoru, integrace s Delivery Hero a XML feedy produktů.",
            "A backend detour – servers in Kotlin and Ktor, Delivery Hero integration and product XML feeds.",
        ),
        chips = listOf(Translated("2022 — 2023", "2022 — 2023")),
    ),
    Column(
        icon = "point_of_sale",
        title = Translated("DAMI development", "DAMI development"),
        text = Translated(
            "Jako Android lead jsem vedl tým tří vývojářů – pokladní systém, sklad, správa domů i logistika.",
            "As the Android lead I ran a team of three developers – point of sale, warehouse, property management and logistics.",
        ),
        chips = listOf(Translated("2019 — 2023", "2019 — 2023")),
    ),
    Column(
        icon = "smartphone",
        title = Translated("MobileSoft", "MobileSoft"),
        text = Translated(
            "Tři roky Android aplikací pro klienty mobilní agentury – AppBlock, aplikace pro realitní makléře, chat i pivovary.",
            "Three years of Android apps for a mobile agency's clients – AppBlock, an app for estate agents, a chat and breweries.",
        ),
        chips = listOf(Translated("2016 — 2019", "2016 — 2019")),
    ),
    Column(
        icon = "precision_manufacturing",
        title = Translated("IHMC Robotics", "IHMC Robotics"),
        text = Translated(
            "Výzkumná stáž na Floridě – Android aplikace na sběr dat ze senzorů telefonu a jejich streaming po LAN.",
            "Research internship in Florida – an Android app collecting phone sensor data and streaming it over LAN.",
        ),
        chips = listOf(Translated("2016", "2016")),
    ),
    Column(
        icon = "desktop_windows",
        title = Translated("VEBA", "VEBA"),
        text = Translated(
            "Moje první práce v IT – začínal jsem jako technik a postupně přešel k desktopovým databázovým aplikacím v Javě.",
            "My first job in IT – I started as a technician and moved on to desktop database apps in Java.",
        ),
        chips = listOf(Translated("2011 — 2015", "2011 — 2015")),
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
