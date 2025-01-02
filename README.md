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
    <dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
	<scope>runtime</scope>
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

# JdbcUserDetailsManager

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // 사용자 정보를 가져오는 쿼리
        userDetailsManager.setUsersByUsernameQuery(
            "SELECT username, password, enabled FROM users WHERE username = ?");

        // 권한 정보를 가져오는 쿼리
        userDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT username, authority FROM authorities WHERE username = ?");

        return userDetailsManager;
    }
    
    JdbcUserDetailsManager는 loadUserByUsername 메서드를 내부적으로 사용하므로, 직접 구현하지 않아도 됩니다.
    기존 CustomUserDetailsService 클래스가 더 이상 필요하지 않습니다.

    이외에도 JdbcUserDetailsManager는 권한 정보나 사용자 관리 관련 추가 설정도 제공합니다:
    createUser: 새로운 사용자 추가.
    deleteUser: 사용자 삭제.
    updateUser: 사용자 업데이트.

# 접근 제한

    #SecurityFilterChain 에서 접근 제한 설정
        hasAuthority() = 접미사 "ROLE_"은 자동으로 부여한 후 체크한다.
                          ROLE_USER cannot start with ROLE_ (it is automatically added)

        hasRole() = 데이터베이스 입력된 값 그대로를 체크한다.
        access() = SPEL(Spring Expression Language) 스프링 표현식

        정리해서 말하자면 데이터베이스에는 "USER"라는 값으로 입력하고
        그리고 hasAuthority("ROLE_USER") 이렇게 체크하거나 또는 hasRole("USER")로 체크하면 된다.

    #Controller 에서 접근 제한 설정
        SecurityConfig 에 "@EnableMethodSecurity" 어노테이션 추가
        @EnableMethodSecurity(
            securedEnabled = true,        // @Secured 활성화
            jsr250Enabled = true,         // @RolesAllowed 활성화
            prePostEnabled = true         // @PreAuthorize 및 @PostAuthorize 활성화
        )

        메소드에 "@Secured({"ROLE_USER"})" 어노테이션 추가
        
    #View 에서 접근 제한
        <dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
		</dependency>

        <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

        <sec:authorize access="hasRole('ADMIN')">
            <p>Welcome, Admin! You have special access.</p>
        </sec:authorize>
    
        <sec:authorize access="!hasRole('USER')">
            <p>You are not a registered user. Please log in.</p>
        </sec:authorize>

# ACL
    ACL(Access Control List)는 객체(데이터)에 대한 사용자별 권한 목록을 의미합니다.
    보통 RBAC (Role-Based Access Control)이 역할 기반으로 보안을 관리하는 반면, ACL은 객체 기반 보안을 제공합니다.

# 추가 공부 사항

    EhCache VS Redis
