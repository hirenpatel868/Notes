import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
    const val org_gradle_kotlin_kotlin_dsl_gradle_plugin: String = "1.2.9"

    const val io_gitlab_arturbosch_detekt_gradle_plugin: String = "1.0.1"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.6.2"

    const val org_jetbrains_kotlin_kotlin_stdlib_jdk8: String = "1.3.50"

    const val kotlin_scripting_compiler_embeddable: String = "1.3.50"

    const val com_gradle_build_scan_gradle_plugin: String = "2.4.2"

    const val kotlin_annotation_processing_gradle: String = "1.3.50"

    const val kotlin_android_extensions_runtime: String = "1.3.50"

    const val com_android_tools_build_gradle: String = "3.5.1"

    const val kotlin_android_extensions: String = "1.3.50"

    const val kotlin_sam_with_receiver: String = "1.3.50"

    const val androidx_test_ext_junit: String = "1.1.2-alpha02"

    const val androidx_test_ext_truth: String = "1.3.0-alpha02"

    const val com_google_truth_truth: String = "1.0"

    const val gradle_license_plugin: String = "0.8.5"

    const val org_jetbrains_kotlinx: String = "1.3.2"

    const val androidx_databinding: String = "3.5.1"

    const val androidx_test_runner: String = "1.3.0-alpha02"

    const val kotlin_gradle_plugin: String = "1.3.50"

    const val androidx_navigation: String = "2.2.0-alpha02"

    const val androidx_test_rules: String = "1.3.0-alpha02"

    const val androidx_lifecycle: String = "2.2.0-alpha04"

    const val androidx_test_core: String = "1.2.1-alpha02"

    const val leakcanary_android: String = "2.0-beta-3"

    const val androidx_fragment: String = "1.2.0-alpha03"

    const val com_google_dagger: String = "2.24"

    const val constraintlayout: String = "2.0.0-beta2"

    const val jacoco_android: String = "0.1.4"

    const val kotlin_reflect: String = "1.3.50"

    const val androidx_room: String = "2.2.0-rc01"

    const val delect_plugin: String = "0.1.0"

    const val espresso_core: String = "3.3.0-alpha02"

    const val activity_ktx: String = "1.1.0-alpha03"

    const val core_testing: String = "2.1.0"

    const val recyclerview: String = "1.1.0-beta04"

    const val junit_junit: String = "4.12"

    const val lint_gradle: String = "26.5.1"

    const val appcompat: String = "1.1.0"

    const val cardview: String = "1.0.0"

    const val core_ktx: String = "1.2.0-alpha04"

    const val material: String = "1.1.0-alpha10"

    const val ktlint: String = "0.34.2"

    const val timber: String = "4.7.1"

    const val aapt2: String = "3.5.1-5435860"

    const val mockk: String = "1.9.3"

    /**
     * Current version: "5.6.2"
     * See issue 19: How to update Gradle itself?
     * https://github.com/jmfayard/buildSrcVersions/issues/19
     */
    const val gradleLatestVersion: String = "5.6.2"
}

/**
 * See issue #47: how to update buildSrcVersions itself
 * https://github.com/jmfayard/buildSrcVersions/issues/47
 */
val PluginDependenciesSpec.buildSrcVersions: PluginDependencySpec
    inline get() =
        id("de.fayard.buildSrcVersions").version(Versions.de_fayard_buildsrcversions_gradle_plugin)
