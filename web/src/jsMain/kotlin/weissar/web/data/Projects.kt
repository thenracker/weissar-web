package weissar.web.data

import weissar.web.i18n.Translated

/**
 * [logo] is a file in resources/companies, without it a placeholder icon is shown.
 * [logoFills] for square app icons that should fill the whole badge.
 */
class Project(
    val company: String,
    val logo: String? = null,
    val logoFills: Boolean = false,
    val description: Translated,
    val role: Translated,
    val years: Translated,
    val tags: List<String>,
)

/** Shown in the "Collaborated on" section, in this order. */
val projects = listOf(
    Project(
        company = "Air Bank",
        logo = "airbank.webp",
        description = Translated("Mobilní bankovnictví MyAir", "MyAir mobile banking app"),
        role = Translated("Android vývojář", "Android developer"),
        years = Translated("2023 – nyní", "2023 – now"),
        tags = listOf("Kotlin", "Compose", "MVI", "Coroutines"),
    ),
    Project(
        company = "Tesco",
        logo = "tesco.webp",
        description = Translated("Integrace s Delivery Hero, XML feedy produktů", "Delivery Hero integration, product XML feeds"),
        role = Translated("Kotlin backend vývojář", "Kotlin backend developer"),
        years = Translated("2022 – 2023", "2022 – 2023"),
        tags = listOf("Ktor", "Kotlin"),
    ),
    Project(
        company = "DAMI development",
        logo = "dami.webp",
        logoFills = true,
        description = Translated("Pokladní systém, sociální síť a skladová aplikace", "POS system, social network and warehouse apps"),
        role = Translated("Android lead", "Android lead"),
        years = Translated("2019 – 2023", "2019 – 2023"),
        tags = listOf("Android", "Flutter", "Ktor"),
    ),
    Project(
        company = "Univision",
        description = Translated("Vývoj a správa Android aplikací", "Android apps development and maintenance"),
        role = Translated("Jednatel a vývojář", "Managing director & engineer"),
        years = Translated("2021 – 2023", "2021 – 2023"),
        tags = listOf("Android", "Kotlin"),
    ),
    Project(
        company = "TORANOTEC",
        logo = "toranotec.webp",
        logoFills = true,
        description = Translated("Nová architektura existující Android aplikace", "New architecture for an existing Android app"),
        role = Translated("Android vývojář", "Android developer"),
        years = Translated("2022", "2022"),
        tags = listOf("Jetpack", "Kotlin"),
    ),
    Project(
        company = "MobileSoft",
        logo = "mobilesoft.webp",
        logoFills = true,
        description = Translated("Android aplikace s moderními technologiemi", "Android apps built with modern technologies"),
        role = Translated("Android vývojář", "Android developer"),
        years = Translated("2016 – 2019", "2016 – 2019"),
        tags = listOf("Java", "Kotlin", "RxJava", "MVVM"),
    ),
    Project(
        company = "IHMC Robotics",
        logo = "ihmc.webp",
        description = Translated("Vývoj pro Android, výzkumná stáž na Floridě", "Android development, research exchange in Florida"),
        role = Translated("Výzkumník", "Researcher"),
        years = Translated("2016", "2016"),
        tags = listOf("Android", "Research"),
    ),
    Project(
        company = "ASAP-apps",
        description = Translated("Webové informační systémy", "Web information systems"),
        role = Translated("ASP.NET vývojář", "ASP.NET developer"),
        years = Translated("2016", "2016"),
        tags = listOf("ASP.NET", "MSSQL"),
    ),
)
