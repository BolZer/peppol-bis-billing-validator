plugins {
    java
    id("io.quarkus")
    id("com.diffplug.spotless") version "6.25.0"
    id("com.github.spotbugs") version "6.0.15"
    id("org.checkerframework") version "0.6.40"
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-rest")
    implementation("io.quarkus:quarkus-rest-jackson")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-smallrye-health")

    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.23.1")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.5")
    implementation("net.sf.saxon:Saxon-HE:12.4")
    implementation("com.helger.commons:ph-commons:11.1.6")
    implementation("com.helger.schematron:ph-schematron-api:7.1.0")
    implementation("com.helger.schematron:ph-schematron-xslt:8.0.0")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "io.github.bolzer"
version = "0.1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

spotbugs {
    ignoreFailures = false
    showProgress = false
}

spotless {
    java {
        importOrder()
        prettier(mapOf(
            "prettier" to "2.8.8",
            "prettier-plugin-java" to "2.2.0"
        )).config(mapOf(
            "parser" to "java",
            "tabWidth" to 4,
        ))
        removeUnusedImports()
    }
}

checkerFramework {
    excludeTests = true
    suppressLombokWarnings = false
    checkers = listOf(
        "org.checkerframework.checker.nullness.NullnessChecker",
        "org.checkerframework.checker.units.UnitsChecker",
        "org.checkerframework.checker.interning.InterningChecker",
        "org.checkerframework.checker.index.IndexChecker",
        "org.checkerframework.checker.calledmethods.CalledMethodsChecker",
        "org.checkerframework.checker.resourceleak.ResourceLeakChecker",
        "org.checkerframework.checker.tainting.TaintingChecker",
        "org.checkerframework.checker.formatter.FormatterChecker",
        "org.checkerframework.checker.propkey.PropertyKeyChecker",
        "org.checkerframework.framework.util.PurityChecker",
        "org.checkerframework.common.value.ValueChecker",
    )
    extraJavacArgs = listOf(
        "-AsuppressWarnings=type.anno.before.decl.anno,type.anno.before.modifier"
    )
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}
