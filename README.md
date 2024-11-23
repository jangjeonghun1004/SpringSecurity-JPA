# Spring Security & JPA

    스프링 시큐리티와 JPA를 이용한 사용자 로그인 & 로그 아웃 로직

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

# JPA 설정

    # application.properties 설정
    ring.datasource.url=jdbc:mysql://127.0.0.1:3306/spring
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

    # 엔티티 설정
    "entity" 패키지를 참고하세요.

    # 리파지토리 설정
    "repository" 패키지를 참고하세요.

    # 서비스 설정
    "service" 패키지를 참고하세요.

# Spring Security

    # SecurityConfig 구성
    1.SecurityFilterChain 빈(요청 관리)
    2.AuthenticationManager 빈(인증 관리)
    3.PasswordEncoder 빈(암호화 관리)
    4.HttpFirewall 빈("//" 오류 이슈)
    "SecurityConfig.java" 파일을 참고하세요. 

    # UserDetailsService 구현
    "CustomUserDetailsService.java" 파일을 참고하세요.

# 테스트

    # 회원 가입
    "RegisterController.java" 파일을 참고하세요.

    # 로그인 & 로그 아웃
    "HomeController.java" 파일을 참고하세요.