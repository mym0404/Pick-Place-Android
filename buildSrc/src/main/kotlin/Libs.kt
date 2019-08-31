import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
object Libs {
  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

  /**
   * http://tools.android.com
   */
  const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
      Versions.constraintlayout

  const val databinding_adapters: String = "androidx.databinding:databinding-adapters:" +
      Versions.androidx_databinding

  /**
   * https://developer.android.com/studio
   */
  const val databinding_common: String = "androidx.databinding:databinding-common:" +
      Versions.androidx_databinding

  /**
   * https://developer.android.com/studio
   */
  const val databinding_compiler: String = "androidx.databinding:databinding-compiler:" +
      Versions.androidx_databinding

  const val databinding_runtime: String = "androidx.databinding:databinding-runtime:" +
      Versions.androidx_databinding

  /**
   * https://developer.android.com/testing
   */
  const val espresso_contrib: String = "androidx.test.espresso:espresso-contrib:" +
      Versions.androidx_test_espresso

  /**
   * https://developer.android.com/testing
   */
  const val espresso_core: String = "androidx.test.espresso:espresso-core:" +
      Versions.androidx_test_espresso

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_ext_junit: String = "androidx.test.ext:junit:" +
      Versions.androidx_test_ext_junit

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_ext_truth: String = "androidx.test.ext:truth:" +
      Versions.androidx_test_ext_truth

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_core: String = "androidx.test:core:" + Versions.androidx_test

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_rules: String = "androidx.test:rules:" + Versions.androidx_test

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_runner: String = "androidx.test:runner:" + Versions.androidx_test

  const val viewpager2: String = "androidx.viewpager2:viewpager2:" + Versions.viewpager2

  const val multidex: String = "com.android.support:multidex:" + Versions.multidex

  /**
   * https://developer.android.com/studio
   */
  const val aapt2: String = "com.android.tools.build:aapt2:" + Versions.aapt2

  /**
   * https://developer.android.com/studio
   */
  const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
      Versions.com_android_tools_build_gradle

  /**
   * https://developer.android.com/studio
   */
  const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" + Versions.lint_gradle

  /**
   * https://github.com/bumptech/glide
   */
  const val com_github_bumptech_glide_compiler: String = "com.github.bumptech.glide:compiler:" +
      Versions.com_github_bumptech_glide

  /**
   * https://github.com/bumptech/glide
   */
  const val glide: String = "com.github.bumptech.glide:glide:" + Versions.com_github_bumptech_glide

  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val material: String = "com.google.android.material:material:" + Versions.material

  /**
   * http://github.com/google/truth
   */
  const val com_google_truth_truth: String = "com.google.truth:truth:" +
      Versions.com_google_truth_truth

  /**
   * https://github.com/karumi/Dexter
   */
  const val dexter: String = "com.karumi:dexter:" + Versions.dexter

  /**
   * https://github.com/square/retrofit/
   */
  const val converter_gson: String = "com.squareup.retrofit2:converter-gson:" +
      Versions.com_squareup_retrofit2

  /**
   * https://github.com/square/retrofit/
   */
  const val converter_scalars: String = "com.squareup.retrofit2:converter-scalars:" +
      Versions.com_squareup_retrofit2

  /**
   * https://github.com/square/retrofit/
   */
  const val retrofit: String = "com.squareup.retrofit2:retrofit:" + Versions.com_squareup_retrofit2

  const val de_fayard_buildsrcversions_gradle_plugin: String =
      "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
      Versions.de_fayard_buildsrcversions_gradle_plugin

  /**
   * http://junit.org
   */
  const val junit_junit: String = "junit:junit:" + Versions.junit_junit

  /**
   * https://github.com/grantland/android-autofittextview
   */
  const val autofittextview: String = "me.grantland:autofittextview:" + Versions.autofittextview

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_android_extensions_runtime: String =
      "org.jetbrains.kotlin:kotlin-android-extensions-runtime:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_android_extensions: String = "org.jetbrains.kotlin:kotlin-android-extensions:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_annotation_processing_gradle: String =
      "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:" +
      Versions.org_jetbrains_kotlin
}