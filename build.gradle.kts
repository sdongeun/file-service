plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:9.0.53")
    implementation("jstl:jstl:1.2")
    implementation("org.postgresql:postgresql:42.2.23")
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.5.5")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.5.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")
}

group = "kr.co.e8ight"
version = "0.0.1-SNAPSHOT"
description = "ndxpro"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
