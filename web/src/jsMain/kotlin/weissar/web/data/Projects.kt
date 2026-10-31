package weissar.web.data

import weissar.web.i18n.Translated

/** One step on the timeline in the project detail. */
class Milestone(val date: Translated, val text: Translated)

/**
 * [logo] is a file in resources/companies, without it a placeholder icon is shown.
 * [logoFills] for square app icons that should fill the whole badge.
 * [detail], [highlights], [timeline] and [link] are shown in the detail after clicking the card.
 */
class Project(
    val company: String,
    val logo: String? = null,
    val logoFills: Boolean = false,
    val description: Translated,
    val role: Translated,
    val years: Translated,
    val location: Translated? = null,
    val tags: List<String>,
    val detail: Translated,
    val highlights: List<Translated> = emptyList(),
    val timeline: List<Milestone> = emptyList(),
    val link: String? = null,
)

/** Shown in the "Collaborated on" section, in this order. */
val projects = listOf(
    Project(
        company = "Air Bank",
        logo = "airbank.webp",
        description = Translated("Mobilní bankovnictví MyAir", "MyAir mobile banking app"),
        role = Translated("Android vývojář", "Android developer"),
        years = Translated("2023 – nyní", "2023 – now"),
        location = Translated("Praha", "Prague"),
        tags = listOf("Kotlin", "Compose", "MVI", "Coroutines"),
        detail = Translated(
            "Spolupracuju na vývoji MyAir, mobilního bankovnictví Air Bank. " +
                "Kromě nových funkcí jsem do aplikace pomáhal zavést moderní Android stack.",
            "I'm working on MyAir, the mobile banking app of Air Bank. " +
                "Besides new features I helped bring a modern Android stack into the app.",
        ),
        highlights = listOf(
            Translated("zavedení Kotlin coroutines", "introducing Kotlin coroutines"),
            Translated("nové obrazovky v Jetpack Compose", "new screens in Jetpack Compose"),
            Translated("MVI principy napříč aplikací", "MVI principles across the app"),
        ),
        timeline = listOf(
            Milestone(Translated("leden 2023", "Jan 2023"), Translated("nástup do Android týmu", "joined the Android team")),
            Milestone(Translated("nyní", "now"), Translated("vývoj MyAir dál pokračuje", "still building MyAir")),
        ),
        link = "https://www.airbank.cz",
    ),
    Project(
        company = "Tesco",
        logo = "tesco.webp",
        description = Translated("Integrace s Delivery Hero, XML feedy produktů", "Delivery Hero integration, product XML feeds"),
        role = Translated("Kotlin backend vývojář", "Kotlin backend developer"),
        years = Translated("2022 – 2023", "2022 – 2023"),
        tags = listOf("Ktor", "Kotlin"),
        detail = Translated(
            "Backendová odbočka z mobilního světa – serverové aplikace v Kotlinu a Ktoru pro Tesco.",
            "A backend detour from the mobile world – server apps in Kotlin and Ktor for Tesco.",
        ),
        highlights = listOf(
            Translated("Ktor aplikace pro integraci s Delivery Hero", "Ktor app integrating with Delivery Hero"),
            Translated("generování XML feedů nabízeného zboží včetně cenotvorby", "XML feeds of the offered goods including pricing"),
            Translated("feedy pro srovnávače jako Heureka", "feeds for price comparison sites like Heureka"),
        ),
        timeline = listOf(
            Milestone(Translated("červenec 2022", "Jul 2022"), Translated("integrace s Delivery Hero", "Delivery Hero integration")),
            Milestone(Translated("potom", "then"), Translated("nová aplikace pro XML feedy a cenotvorbu", "new app for XML feeds and pricing")),
            Milestone(Translated("leden 2023", "Jan 2023"), Translated("předání projektu", "project handover")),
        ),
    ),
    Project(
        company = "DAMI development",
        logo = "dami.webp",
        logoFills = true,
        description = Translated("Pokladní systém, sociální síť a skladová aplikace", "POS system, social network and warehouse apps"),
        role = Translated("Android lead", "Android lead"),
        years = Translated("2019 – 2023", "2019 – 2023"),
        location = Translated("Hradec Králové", "Hradec Králové"),
        tags = listOf("Android", "Flutter", "Ktor"),
        detail = Translated(
            "Jako Android lead jsem vedl vývoj zakázkových aplikací pro klienty agentury – od návrhu architektury až po vydání.",
            "As the Android lead I drove client projects of the agency – from architecture design to release.",
        ),
        highlights = listOf(
            Translated("pokladní (POS) systém", "point of sale (POS) system"),
            Translated("aplikace pro sociální síť", "social network apps"),
            Translated("správa skladu se čtečkou čárových kódů", "warehouse management with a barcode scanner"),
            Translated("Android, Flutter i Kotlin backend", "Android, Flutter and Kotlin backend"),
            Translated("MVVM a MVI architektura, Jetpack, Compose", "MVVM and MVI architecture, Jetpack, Compose"),
        ),
        timeline = listOf(
            Milestone(Translated("srpen 2019", "Aug 2019"), Translated("nástup jako Android lead", "joined as Android lead")),
            Milestone(Translated("březen 2023", "Mar 2023"), Translated("konec spolupráce", "end of cooperation")),
        ),
        link = "https://www.damidev.com",
    ),
    Project(
        company = "Univision",
        logo = "univision.webp",
        logoFills = true,
        description = Translated("Firma za aplikací Univerzita.app", "The company behind Univerzita.app"),
        role = Translated("Jednatel a vývojář", "Managing director & engineer"),
        years = Translated("2021 – 2023", "2021 – 2023"),
        tags = listOf("Android", "Kotlin", "Startup"),
        detail = Translated(
            "Univision jsme založili, abychom mohli naplno rozjet Univerzitu – aplikaci, která studentům dává rozvrh, známky, zkoušky " +
                "i menzu do kapsy. Kromě vývoje Android aplikace jsem měl na starosti obchod a marketing.",
            "We founded Univision to fully launch Univerzita – the app that puts timetables, grades, exams and canteen menus " +
                "into students' pockets. Besides building the Android app I was in charge of sales and marketing.",
        ),
        highlights = listOf(
            Translated("27 000+ přihlášených studentů", "27,000+ signed up students"),
            Translated("až 20 000 aktivních uživatelů na začátku semestru", "up to 20,000 active users at the start of a semester"),
            Translated("B2B model – pro studenty zdarma", "B2B model – free for students"),
            Translated("článek v CzechCrunch a AppParade", "featured in CzechCrunch and at AppParade"),
        ),
        timeline = listOf(
            Milestone(Translated("2016", "2016"), Translated("UHK Helper – aplikace na hledání volných učeben", "UHK Helper – an app for finding free classrooms")),
            Milestone(Translated("2019", "2019"), Translated("StagApp – rozšíření na všechny školy s IS/STAG", "StagApp – extended to all IS/STAG universities")),
            Milestone(Translated("2020", "2020"), Translated("otevřená beta Univerzita.app", "Univerzita.app open beta")),
            Milestone(Translated("2021", "2021"), Translated("založení Univision s. r. o.", "Univision s. r. o. founded")),
            Milestone(Translated("2022", "2022"), Translated("27 000 studentů a článek v CzechCrunch", "27,000 students and a CzechCrunch article")),
        ),
        link = "https://cc.cz/zalozili-aplikaci-ktera-zlepsuje-zivot-desitkam-tisic-studentu-brzdi-je-ale-neochota-univerzit/",
    ),
    Project(
        company = "TORANOTEC",
        logo = "toranotec.webp",
        logoFills = true,
        description = Translated("Nová architektura existující Android aplikace", "New architecture for an existing Android app"),
        role = Translated("Android vývojář", "Android developer"),
        years = Translated("2022", "2022"),
        location = Translated("Japonsko, na dálku", "Japan, remote"),
        tags = listOf("Jetpack", "Kotlin"),
        detail = Translated(
            "Japonská fintech firma, pro kterou jsem na dálku modernizoval existující Android aplikaci.",
            "A Japanese fintech company – I modernized their existing Android app remotely.",
        ),
        highlights = listOf(
            Translated("nová architektura aplikace", "new app architecture"),
            Translated("nasazení Jetpack komponent", "adopting Jetpack components"),
        ),
        timeline = listOf(
            Milestone(Translated("leden 2022", "Jan 2022"), Translated("začátek spolupráce", "cooperation started")),
            Milestone(Translated("květen 2022", "May 2022"), Translated("předání nové architektury", "new architecture handed over")),
        ),
        link = "https://toranotec.com",
    ),
    Project(
        company = "MobileSoft",
        logo = "mobilesoft.webp",
        logoFills = true,
        description = Translated("Android aplikace s moderními technologiemi", "Android apps built with modern technologies"),
        role = Translated("Android vývojář", "Android developer"),
        years = Translated("2016 – 2019", "2016 – 2019"),
        location = Translated("Hradec Králové", "Hradec Králové"),
        tags = listOf("Java", "Kotlin", "RxJava", "MVVM"),
        detail = Translated(
            "Tři roky vývoje Android aplikací pro klienty mobilní agentury s důrazem na moderní technologie.",
            "Three years of building Android apps for clients of a mobile agency, with a focus on modern technologies.",
        ),
        highlights = listOf(
            Translated("Java a Kotlin", "Java and Kotlin"),
            Translated("RxJava a material design", "RxJava and material design"),
            Translated("MVP a MVVM architektura", "MVP and MVVM architecture"),
        ),
        timeline = listOf(
            Milestone(Translated("říjen 2016", "Oct 2016"), Translated("nástup jako Android vývojář", "joined as Android developer")),
            Milestone(Translated("září 2019", "Sep 2019"), Translated("konec spolupráce", "end of cooperation")),
        ),
        link = "https://mobilesoft.eu",
    ),
    Project(
        company = "IHMC Robotics",
        logo = "ihmc.webp",
        description = Translated("Vývoj pro Android, výzkumná stáž na Floridě", "Android development, research exchange in Florida"),
        role = Translated("Výzkumník", "Researcher"),
        years = Translated("2016", "2016"),
        location = Translated("Pensacola, Florida", "Pensacola, Florida"),
        tags = listOf("Android", "Research"),
        detail = Translated(
            "Výzkumná stáž v Institute for Human & Machine Cognition – institutu, který se věnuje robotice a spolupráci lidí se stroji.",
            "Research exchange at the Institute for Human & Machine Cognition, known for robotics and human-machine teamwork.",
        ),
        highlights = listOf(
            Translated("Android vývoj pro výzkumné projekty", "Android development for research projects"),
        ),
        timeline = listOf(
            Milestone(Translated("listopad 2016", "Nov 2016"), Translated("odlet na Floridu", "flew to Florida")),
            Milestone(Translated("prosinec 2016", "Dec 2016"), Translated("konec stáže", "exchange finished")),
        ),
        link = "https://www.ihmc.us",
    ),
    Project(
        company = "ASAP-apps",
        description = Translated("Webové informační systémy", "Web information systems"),
        role = Translated("ASP.NET vývojář", "ASP.NET developer"),
        years = Translated("2016", "2016"),
        location = Translated("Trutnov", "Trutnov"),
        tags = listOf("ASP.NET", "MSSQL"),
        detail = Translated(
            "Vývoj webových informačních systémů – od návrhu až po aplikační logiku v databázi.",
            "Building web information systems – from the design to the application logic in the database.",
        ),
        highlights = listOf(
            Translated("design informačních systémů", "information systems design"),
            Translated("aplikační logika v MSSQL databázi", "application logic in an MSSQL database"),
        ),
        timeline = listOf(
            Milestone(Translated("březen 2016", "Mar 2016"), Translated("začátek", "started")),
            Milestone(Translated("září 2016", "Sep 2016"), Translated("konec", "finished")),
        ),
    ),
    Project(
        company = "VEBA",
        logo = "veba.webp",
        description = Translated("Desktopové databázové aplikace v Javě", "Desktop database apps in Java"),
        role = Translated("IT technik, Java vývojář", "IT technician, Java developer"),
        years = Translated("2011 – 2015", "2011 – 2015"),
        location = Translated("Broumov", "Broumov"),
        tags = listOf("Java", "OpenSwing", "SQL"),
        detail = Translated(
            "Moje první práce v IT – v textilce, kde jsem začínal jako IT technik a postupně přešel k vývoji desktopových databázových aplikací.",
            "My first job in IT – at a textile company where I started as an IT technician and moved on to building desktop database apps.",
        ),
        timeline = listOf(
            Milestone(Translated("2011 – 2012", "2011 – 2012"), Translated("IT technik", "IT technician")),
            Milestone(Translated("2013 – 2015", "2013 – 2015"), Translated("Java vývojář na brigádách", "Java developer during summer jobs")),
        ),
        link = "https://www.veba.cz",
    ),
)
