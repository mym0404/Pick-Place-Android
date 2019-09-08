import kotlin.String
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
  const val appcompat: String = "1.1.0"

  const val constraintlayout: String = "1.1.3"

  const val androidx_databinding: String = "3.5.0"

  const val androidx_lifecycle: String = "2.1.0"

  const val androidx_room: String = "2.2.0-rc01"

  const val androidx_test_espresso: String = "3.2.0"

  const val androidx_test_ext_junit: String = "1.1.1"

  const val androidx_test_ext_truth: String = "1.2.0"

  const val androidx_test: String = "1.2.0"

  const val viewpager2: String = "1.0.0-beta04"

  const val multidex: String = "1.0.3"

  const val aapt2: String = "3.5.0-5435860"

  const val com_android_tools_build_gradle: String = "3.5.0"

  const val lint_gradle: String = "26.5.0"

  const val com_github_bumptech_glide: String = "4.9.0"

  const val play_services_maps: String = "17.0.0"

  const val material: String = "1.1.0-alpha06"

  const val firebase_analytics: String = "17.0.0" // available: "17.2.0"

  const val firebase_dynamic_links: String = "19.0.0"

  const val google_services: String = "4.3.2"

  const val android_maps_utils: String = "0.5"

  const val com_google_truth_truth: String = "1.0"

  const val dexter: String = "5.0.0"

  const val com_squareup_retrofit2: String = "2.6.1"

  const val de_fayard_buildsrcversions_gradle_plugin: String = "0.4.2"

  const val junit_junit: String = "4.12"

  const val autofittextview: String = "0.2.1"

  const val org_jetbrains_kotlin: String = "1.3.50"

  const val org_koin: String = "2.0.1"

  const val robolectric: String = "4.3"

  /**
   *
   * See issue 19: How to update Gradle itself?
   * https://github.com/jmfayard/buildSrcVersions/issues/19
   */
  const val gradleLatestVersion: String = "5.6.2"

  const val gradleCurrentVersion: String = "5.4.1"
}

/**
 * See issue #47: how to update buildSrcVersions itself
 * https://github.com/jmfayard/buildSrcVersions/issues/47
 */
val PluginDependenciesSpec.buildSrcVersions: PluginDependencySpec
  inline get() =
      id("de.fayard.buildSrcVersions").version(Versions.de_fayard_buildsrcversions_gradle_plugin)
