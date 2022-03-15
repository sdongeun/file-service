plugins {
    id("org.springframework.boot") version "2.5.5"
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }

//    mavenCentral()
//    maven {
//        url = uri("http://172.16.28.217:9081/repository/maven-releases/")
//        isAllowInsecureProtocol = true
//    }
//
//    maven {
//        url = uri("https://repo1.maven.org/maven2")
//    }
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-config:3.0.5")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("org.hibernate:hibernate-core:5.6.5.Final")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("jstl:jstl:1.2")
    implementation("org.postgresql:postgresql:42.2.23")
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.5.5")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.5.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")
}

group = "kr.co.e8ight.management"
version = "1.0.0"
description = "file-service"

java.sourceCompatibility = JavaVersion.VERSION_11

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}


//tasks.jar {
//    // Remove `plain` postfix from jar file name
//    archiveClassifier.set("")
//}
//
//// maven publish를 사용하여 nexus 레포지토리에 jar 파일 저장
//publishing {
//    publications.create<MavenPublication>("maven") {
//        groupId = group.toString()
//        artifactId = rootProject.name
//        version = version
//        artifact("build/libs/$artifactId-$version.jar") {
//            extension = "jar"
//        }
//    }
//    repositories {
//        maven {
//            url = uri("http://172.16.28.217:9081/repository/maven-releases/")
//            isAllowInsecureProtocol = true
//            credentials {
//                username = "admin"
//                password = "ndxpro123!"
//            }
//        }
//    }
//}
