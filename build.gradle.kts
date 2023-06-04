// Top-level build file where you can add configuration options common to all sub-projects/modules.
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask

// TODO: Remove once KTIJ-19369 is fixed (https://youtrack.jetbrains.com/issue/KTIJ-19369)
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ktlint) apply false
}

allprojects {
    plugins.apply(KtlintPlugin::class.java)

    configure<KtlintExtension> {
        android.set(true)
        verbose.set(true)
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
            reporter(ReporterType.HTML)
        }
        filter {
            include("**/*.kt")
            exclude("**/build/**")
        }
    }

    tasks.withType<GenerateReportsTask> {
        reportsOutputDirectory.set(layout.buildDirectory.dir("reports/ktlint/"))
    }
}
