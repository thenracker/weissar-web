package weissar.web.data

import weissar.web.i18n.Translated

/** Shown in the "Education" section as cards with the same detail as projects, newest first. */
val education = listOf(
    Project(
        company = "Univerzita Hradec Králové",
        logo = "uhk.webp",
        logoFills = true,
        description = Translated("Ing. et Ing. – Data Science", "Ing. et Ing. – Data Science"),
        role = Translated("s vyznamenáním", "with honors"),
        years = Translated("2021 – 2023", "2021 – 2023"),
        location = Translated("Hradec Králové", "Hradec Králové"),
        tags = listOf("Data Science", "Python", "TensorFlow"),
        detail = Translated(
            "Druhý inženýrský titul, tentokrát z datové vědy – při plné práci a vlastní firmě. " +
                "V diplomové práci jsem učil neuronovou síť rozpoznat z dat telefonu, jak se jeho majitel pohybuje.",
            "A second engineering degree, this time in data science – next to a full-time job and my own company. " +
                "In the thesis I trained a neural network to tell from phone data how its owner moves.",
        ),
        highlights = listOf(
            Translated(
                "diplomová práce: Využití neuronových sítí pro určení pohybu mobilních zařízení",
                "thesis: Using neural networks to determine the movement of mobile devices",
            ),
            Translated("sběr dat ze senzorů vlastní Android aplikací", "sensor data collected by my own Android app"),
            Translated("strojové učení v Pythonu a TensorFlow", "machine learning in Python and TensorFlow"),
        ),
        timeline = listOf(
            Milestone(Translated("2021", "2021"), Translated("nástup do navazujícího studia", "started the master's programme")),
            Milestone(Translated("2023", "2023"), Translated("absolvování s vyznamenáním", "graduated with honors")),
        ),
        link = "https://theses.cz/id/iupmxo/",
    ),
    Project(
        company = "Univerzita Hradec Králové",
        logo = "uhk.webp",
        logoFills = true,
        description = Translated("Doktorské studium a výuka", "Doctoral studies & teaching"),
        role = Translated("Ph.D. student a vyučující", "Ph.D. student & teacher"),
        years = Translated("2018 – 2019", "2018 – 2019"),
        location = Translated("Hradec Králové", "Hradec Králové"),
        tags = listOf("Android", "Java", "Computer graphics"),
        detail = Translated(
            "Rok na doktorátu, během kterého jsem hlavně učil – studenty jsem provázel programováním desktopových " +
                "a mobilních aplikací a počítačovou grafikou.",
            "A year of doctoral studies, mostly spent teaching – I guided students through desktop " +
                "and mobile app development and computer graphics.",
        ),
        highlights = listOf(
            Translated("cvičení z programování mobilních aplikací", "mobile app development classes"),
            Translated("počítačová grafika – rasterizace, 3D, Bézierovy plochy", "computer graphics – rasterisation, 3D, Bézier surfaces"),
            Translated("výzkum indoor lokalizace a senzorů", "research on indoor positioning and sensors"),
            Translated("1. místo na hackathonu HackFIT", "1st place at the HackFIT hackathon"),
        ),
    ),
    Project(
        company = "Huawei Seeds for the Future",
        logo = "huawei.webp",
        description = Translated("Stáž v laboratořích Huawei v Číně", "Trainee in Huawei labs in China"),
        role = Translated("Stážista", "Trainee"),
        years = Translated("2017", "2017"),
        location = Translated("Peking a Šen-čen", "Beijing & Shenzhen"),
        tags = listOf("Networking", "5G", "Cloud"),
        detail = Translated(
            "Program Huawei pro vybrané studenty z celého světa – měsíc v Pekingu a Šen-čenu, " +
                "přednášky o sítích a telekomunikacích, návštěvy laboratoří a čínská kultura.",
            "Huawei's programme for selected students from around the world – a month in Beijing and Shenzhen, " +
                "lectures on networking and telecommunications, lab visits and Chinese culture.",
        ),
        highlights = listOf(
            Translated("konfigurace routerů Huawei", "Huawei router configuration"),
            Translated("výuka čínštiny", "Chinese lessons"),
        ),
        timeline = listOf(
            Milestone(Translated("říjen 2017", "Oct 2017"), Translated("měsíc v Číně", "a month in China")),
        ),
    ),
    Project(
        company = "Chang Jung Christian University",
        logo = "cjcu.webp",
        logoFills = true,
        description = Translated("Letní škola na Tchaj-wanu", "Summer school in Taiwan"),
        role = Translated("Letní škola", "Summer school"),
        years = Translated("2017", "2017"),
        location = Translated("Tainan, Tchaj-wan", "Tainan, Taiwan"),
        tags = listOf("Summer school"),
        detail = Translated(
            "Letní program na univerzitě v Tainanu – výuka v angličtině, studenti z celého světa a spousta Asie okolo.",
            "A summer programme at a university in Tainan – classes in English, students from all over the world and lots of Asia around.",
        ),
        highlights = listOf(
            Translated("programování grafiky v jazyce Processing", "graphics programming in Processing"),
            Translated("kurz managementu", "management course"),
        ),
        timeline = listOf(
            Milestone(Translated("červen 2017", "Jun 2017"), Translated("odlet na Tchaj-wan", "flew to Taiwan")),
            Milestone(Translated("červenec 2017", "Jul 2017"), Translated("konec letní školy", "summer school finished")),
        ),
    ),
    Project(
        company = "Univerzita Hradec Králové",
        logo = "uhk.webp",
        logoFills = true,
        description = Translated("Ing. – Aplikovaná informatika", "Ing. – Applied informatics"),
        role = Translated("s vyznamenáním", "with honors"),
        years = Translated("2016 – 2018", "2016 – 2018"),
        location = Translated("Hradec Králové", "Hradec Králové"),
        tags = listOf("Android", "Indoor positioning"),
        detail = Translated(
            "Inženýrské studium, při kterém jsem už na plný úvazek vyvíjel Android aplikace v MobileSoftu. " +
                "Diplomová práce spojila obojí – lokalizaci uvnitř budov pomocí senzorů telefonu.",
            "Engineering studies while already building Android apps full time at MobileSoft. " +
                "The thesis combined both – indoor positioning using phone sensors.",
        ),
        highlights = listOf(
            Translated(
                "diplomová práce: Indoor lokalizace v systému pro podporu výuky",
                "thesis: Indoor positioning in a system supporting education",
            ),
            Translated("výzkumná stáž v IHMC na Floridě", "research internship at IHMC in Florida"),
            Translated("1. místo na Škoda Auto Hackathonu", "1st place at the Škoda Auto Hackathon"),
        ),
        link = "https://theses.cz/id/sts2mw/",
    ),
    Project(
        company = "City University of Hong Kong",
        logo = "cityu.webp",
        description = Translated("Vývoj pro Android v Hongkongu", "Android development in Hong Kong"),
        role = Translated("Letní škola", "Summer school"),
        years = Translated("2016", "2016"),
        location = Translated("Kowloon, Hongkong", "Kowloon, Hong Kong"),
        tags = listOf("Android", "Java"),
        detail = Translated(
            "Léto v Hongkongu, kde jsem vyvíjel Android aplikace – mimo jiné navigaci a průvodce po kampusu.",
            "A summer in Hong Kong building Android apps – among others a campus navigation and guide.",
        ),
        highlights = listOf(
            Translated("vývoj Android aplikací pro City University", "Android app development for City University"),
        ),
        timeline = listOf(
            Milestone(Translated("červenec 2016", "Jul 2016"), Translated("odlet do Hongkongu", "flew to Hong Kong")),
            Milestone(Translated("srpen 2016", "Aug 2016"), Translated("konec letní školy", "summer school finished")),
        ),
    ),
    Project(
        company = "Univerzita Hradec Králové",
        logo = "uhk.webp",
        logoFills = true,
        description = Translated("Bc. – Aplikovaná informatika", "Bc. – Applied informatics"),
        role = Translated("s vyznamenáním", "with honors"),
        years = Translated("2013 – 2016", "2013 – 2016"),
        location = Translated("Hradec Králové", "Hradec Králové"),
        tags = listOf("Android", "Java"),
        detail = Translated(
            "Zde to celé začalo – první Android aplikace, první hackathony a UHK Helper, ze kterého později vyrostla Univerzita.",
            "This is where it all started – first Android apps, first hackathons and UHK Helper, which later grew into Univerzita.",
        ),
        highlights = listOf(
            Translated("bakalářská práce: Mobilní horolezecký průvodce", "thesis: Mobile climbing guide"),
            Translated("UHK Helper – aplikace na volné učebny", "UHK Helper – an app for free classrooms"),
        ),
        link = "https://theses.cz/id/qaha4q/",
    ),
)

/** Shown as chips under the education cards. */
val awards = listOf(
    Translated("🏆 Škoda Auto Hackathon – 1. místo (2017)", "🏆 Škoda Auto Hackathon – 1st place (2017)"),
    Translated("🏆 HackFIT – 1. místo (2020)", "🏆 HackFIT – 1st place (2020)"),
    Translated("🥈 AppParade 34 – 2. místo (Univerzita)", "🥈 AppParade 34 – 2nd place (Univerzita)"),
    Translated("🥈 Ceny SDGs 2022 – 2. místo (Univerzita)", "🥈 SDGs Awards 2022 – 2nd place (Univerzita)"),
    Translated("AppParade 36 – 7. místo (Svatebka)", "AppParade 36 – 7th place (Svatebka)"),
)

val certificates = listOf(
    Translated("🧠 člen Mensy ČR", "🧠 Mensa Czech Republic member"),
    Translated("TensorFlow Lite (Coursera)", "TensorFlow Lite (Coursera)"),
    Translated("TensorFlow (Coursera)", "TensorFlow (Coursera)"),
    Translated("Cisco Networking Academy (CCNA 1–2)", "Cisco Networking Academy (CCNA 1–2)"),
    Translated("angličtina C1, plynně", "English C1, fluent"),
)
