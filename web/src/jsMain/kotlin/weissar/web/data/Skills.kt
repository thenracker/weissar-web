package weissar.web.data

import weissar.web.i18n.Translated

/** Group of chips in the "Tech stack" section, [featured] chips are highlighted. */
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
        skills = listOf(
            same("Claude Code"),
            same("Claude API"),
            Translated("MCP servery", "MCP servers"),
            same("TensorFlow"),
            Translated("neuronové sítě", "neural networks"),
            same("ML Kit"),
        ),
    ),
    SkillGroup(
        label = same("Android"),
        skills = names(
            "Room", "WorkManager", "DataStore", "Navigation", "Koin", "Dagger",
            "CameraX", "Block Store auth", "Material Design", "Lottie",
        ),
    ),
    SkillGroup(
        label = Translated("Hardware a komunikace", "Hardware & connectivity"),
        skills = listOf(
            Translated("USB komunikace", "USB communication"),
            same("Bluetooth"),
            same("Ethernet"),
            Translated("senzory a IMU", "sensors & IMU"),
            Translated("indoor lokalizace", "indoor positioning"),
            Translated("GPS a mapy", "GPS & maps"),
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
        skills = names("Ktor", "GraphQL", "REST", "Retrofit", "OkHttp", "PostgreSQL", "Docker") +
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
        label = Translated("Architektura a nástroje", "Architecture & tools"),
        skills = names(
            "Clean Architecture", "MVVM", "MVI", "RxJava", "KSP",
            "Gradle", "GitHub Actions", "CI/CD", "Git", "Figma",
        ),
    ),
    SkillGroup(
        label = Translated("Jazyky", "Languages"),
        skills = names("Kotlin", "Java", "Dart", "Python", "C#", "SQL"),
    ),
)
