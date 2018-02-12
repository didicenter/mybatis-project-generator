buildscript {
    ext {
        springBootVersion = '1.5.10.RELEASE'
    }
    repositories {
        mavenLocal()
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public' }
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${r'${'}springBootVersion${r'}'}")
    }
}

apply plugin: 'java'
apply plugin: 'idea'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'

jar {
    baseName = '${artifact}'
    version = '${version}'
}

sourceCompatibility = 1.8
targetCompatibility = 1.8

repositories {
    mavenLocal()
    maven { url 'http://maven.aliyun.com/nexus/content/groups/public' }
    mavenCentral()
}


dependencies {

    compile 'org.springframework.boot:spring-boot-starter-actuator'

    compile 'org.springframework.boot:spring-boot-actuator-docs'

    compile 'org.springframework.boot:spring-boot-starter-web'

    compile 'org.springframework.boot:spring-boot-starter-jdbc'

    compile 'org.springframework.boot:spring-boot-starter-aop'

    compile 'org.springframework.boot:spring-boot-starter-thymeleaf'

    compile 'org.springframework.boot:spring-boot-autoconfigure'

    compile 'io.springfox:springfox-swagger-ui:2.6.1'

    compile 'io.springfox:springfox-swagger2:2.6.1'

    compile 'org.mybatis.spring.boot:mybatis-spring-boot-starter:1.1.1'

    compile 'org.projectlombok:lombok:1.16.18'

    compile 'mysql:mysql-connector-java:8.0.8-dmr'

    compile 'org.postgresql:postgresql:9.4.1212.jre7'

    compile 'org.mybatis:mybatis:3.4.5'

    compile 'org.mybatis:mybatis-spring:1.3.1'

    compile 'com.alibaba:fastjson:1.2.44'

    compile 'com.google.guava:guava:21.0'

    compile group: 'com.google.code.gson', name: 'gson', version: '2.8.2'

    compile group: 'org.springframework.data', name: 'spring-data-redis', version: '1.8.10.RELEASE'

    compile group: 'redis.clients', name: 'jedis', version: '2.9.0'

    compile group: 'com.thoughtworks.xstream', name: 'xstream', version: '1.4.10'

    testCompile 'org.springframework.boot:spring-boot-starter-test'

    testCompile 'org.springframework.restdocs:spring-restdocs-mockmvc'

}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:Dalston.RELEASE"
    }
}
