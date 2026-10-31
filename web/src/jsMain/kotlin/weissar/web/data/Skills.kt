package weissar.web.data

import weissar.web.i18n.Translated

/** Group of chips in the "And more…" section, [featured] chips are highlighted. */
class SkillGroup(
    val label: Translated,
    val skills: List<Translated>,
    val featured: Boolean = false,
)

/** Same text in both languages. */
private fun same(text: String) = Translated(text, text)

private fun names(vararg texts: String) = texts.map(::same)

val skillGroups = listOf(
    SkillGroup(
        label = same("AI & ML"),
        featured = true,
        skills = names("Claude Code", "Claude API", "MCP", "Agent skills") + listOf(
            Translated("agentní workflow", "agentic workflows"),
            same("prompt engineering"),
            same("TensorFlow"),
            Translated("neuronové sítě", "neural networks"),
            same("ML Kit"),
        ),
    ),
    SkillGroup(
        label = same("Android"),
        skills = names("Room", "WorkManager", "DataStore", "Navigation", "Koin", "CameraX", "Block Store auth") +
            Translated("animace", "animations"),
    ),
    SkillGroup(
        label = Translated("Hardware a komunikace", "Hardware & connectivity"),
        skills = listOf(
            Translated("USB komunikace", "USB communication"),
            same("Bluetooth"),
            Translated("senzory a IMU", "sensors & IMU"),
            Translated("indoor lokalizace", "indoor positioning"),
            Translated("GPS a mapy", "GPS & maps"),
            same("Wear OS"),
        ),
    ),
    SkillGroup(
        label = same("Multiplatform"),
        skills = names(
            "Kotlin Multiplatform", "Compose Multiplatform", "Compose Web",
            "Flutter", "Dart", "iOS (Swift)",
        ),
    ),
    SkillGroup(
        label = same("Backend & data"),
        skills = names("Ktor", "GraphQL", "REST", "Retrofit", "OkHttp", "PostgreSQL", "MSSQL", "Docker", "Java Servlets") +
            Translated("XML feedy", "XML feeds"),
    ),
    SkillGroup(
        label = same("Firebase"),
        skills = names(
            "Firestore", "Auth", "Cloud Functions", "Storage", "Cloud Messaging",
            "Crashlytics", "Remote Config", "App Check", "Analytics", "Performance",
        ),
    ),
    SkillGroup(
        label = Translated("Grafika a UI", "Graphics & UI"),
        skills = listOf(
            same("OpenGL"),
            Translated("3D grafika", "3D graphics"),
            Translated("Bézierovy křivky", "Bézier curves"),
            same("custom views"),
            same("Lottie"),
            same("Material Design"),
            same("Figma"),
        ),
    ),
    SkillGroup(
        label = Translated("Architektura a nástroje", "Architecture & tools"),
        skills = names(
            "Clean Architecture", "MVVM", "MVI", "MVP", "RxJava", "KSP",
            "Gradle", "GitHub Actions", "CI/CD", "Git",
        ) + Translated("unit testy", "unit tests"),
    ),
    SkillGroup(
        label = Translated("Jazyky", "Languages"),
        skills = names("Kotlin", "Java", "Dart", "Python", "C#", "ASP.NET", "SQL", "JavaScript", "HTML & CSS"),
    ),
)
