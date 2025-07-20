# Spring keretrendszer kurzus

---

## Tematika

### Spring (Boot) alapok

#### Egyszerű webalkalmazás
- Project létrehozás - Spring Intitializr
- Build Tool alapok 
  - Gradle
  - Függőségek kezelése
  - Maven és Custom Repository
- Keretrendszer alapok
  - Felépítés
  - IOC container
  - Dependency Injection
- Főbb rétegek és annotációk - Controller, Service, Repository, Configuration, Component, Bean
- Adatbázis alapok - H2, Spring Data JPA, Tranzakciók
- CRUD funkcionalitás
- Vaadin felület

### Spring Beanek
- Létrehozás - Bean, Component
- Befecskendezés - Autowired, ComponentScan
- Scope-ok - Singleton, Prototype
- Életciklus
- Proxymode
- Lazy init
- AspectJ vs Spring AOP
- ApplicationContext - bean regisztráció és lekérés
- BeanFactory, BeanDefinition
- Qualifier, Primary, Fallback
- PostConstruct, PreDestroy

#### Restful
- Http Igék - Get,Post, Delete, Put, Patch
- Adat validáció
- Hibakezelés - lokális, globális
- Web filterek
- Api tervezés és OpenApi dokumentáció
- JSON szűrés - JsonView, JsonBackReference, JsonIgnore
- Objektum mappelés (mapstruct)

#### Spring Data (JPA)
- Entity alapok
  - Annotációk
    - Mezők (Column, Transient, CreatedDate…)
    - Lekérdezés (Order, Where..)
    - Osztály (Table, Index)
    - Művelet (filter..)
  - Kapcsolatok (1-1, 1-N, N-N), uni/bidirectional
  - Öröklődés - közös mezők
  - Entity gráf lekérdezés
- Interface alapú lekérdezések - findBy*
  - Szabályok
  - Lapozás és rendezés
  - Annotációk - Query, Param, Modifying
  - Dinamikus lekérdezések, specification, example
  - SQL és JPQL - annotációk
- Lekérdezés mappelése egyedi objektumba, Projection
- Egyedi repository - entitás alapú, keretrendszer szintű
- Tranzakciók részletesen - izoláció, propagáció, belső működés
- Join és az N+1 probléma
- Több ügyfeles (multi-tenant) rendszerek
- Adatbázis migrációk - Liquibase
- SQL log
- Audit, Envers
- Optimistic/Pessimistic lock - Version, Lock annotation
- Lifecycle events

### Kidolgozás alatt
- Aspect orientált programozás
    - Advice típusok (@Before, @After, @Around)
    - Pointcut kifejezések
    - Proxy alapú megvalósítás
- Docker konténer készítése és futtatása Kubernetes alatt
  - Docker és K8s (Kustomize) leírók
  - Optimalizálás
  - Terheléselosztás
  - Health Probes
  - Erőforrás beállítások
  - GC választás (ZGC, Shenandoah..)
  - Graceful shutdown
  - Service Meshek
- Cachelés
    - Spring Cache abstraction
    - Redis integráció
    - Cache invalidáció
- Spring Data Stream - Üzenetsorok Kafka+Avro, RabbitMQ, Nats
- Spring AI
- Spring AWS
- GraalVM
- Microservice alapok
    - Tervezés (miért ne használjuk, mikor érdemes)
    - Kommunikáció
        - Rest - openfeign
        - GRPC
        - Message queues
    - Hibamegelőzés és kezelés
        - bulkhead
        - circuit breaker
        - retry
        - rate-limit
    - CQRS, SAGA
- Observability
    - Metrikák
    - Tracing (nyomkövetés)
    - Logolás - trace, span azonosítók
- DDD - domain vezérelt tervezés
- Reactive alapok
    - Reactor + Webflux
    - RSocket
    - R2DBC
- Spring Gateway
- Spring Security
    - Autentikáció
    - Oauth
    - Autorizáció
    - Jelszavak kezelése - Argon, Bcrypt…
    - Keycloak és egyéb külső szolgáltatások
- Egyéb Spring Data projektek és kiegészítők
    - Mongo
    - Rest
    - Sql
    - Jooq
    - Cassandra
- Graphql részletesen
    - Netflix DGS
    - Query és Mutation
    - Data Loaders(N+1)

#### Tesztelés
- Unit és Integrációs tesztek
- JUnit
- Testcontainers
- Parametrizált tesztek
- Hasznos libek
- Mutációs tesztek
- Perf tesztek (k6, jmeter…)
- Chaos Monkey

#### CI/CD
- Automatizált Szemantikus verziózás
- Szemantikus kiadás (auto deploy, auto changelog generálás)
- Telepítés Github Action-ökkel

#### Observability
- Micrometer + OTEL
- Metrikák gyűjtése
- Metrikák megjelenítése (Grafana, Prometheus vagy Victoria)
- Tracing (Zipkin, Jaeger)
- Logok gyűjtése és szűrése (Splunk/ELK?)
- Jvm/GC Monitorozás

#### Actuator és Spring DevTools (live reload)
  - Használat
  - Végpontok

#### Konfigurációk részletesen
- Alapok
- Value, Spell, Konfigurációs fájl
- Feltételes (Condition*)
- Env függő
- Rendszerfüggő (EnableOnOs*, EnableIfSys*)
- Secrets - hol és hogyan tároljuk
- 
#### Spring Admin
- JMX
- Logok
- Beállítások szerkesztése
- Alap monitoring

#### Spring Async és Application Events

#### Ütemezett feladatok
- Spring schedule
- Quartz

#### Modulith - projektstruktúra kikényszerítése
#### Spring Integration
#### Spring Cloud Contract
#### Spring Task
#### Cloud Bus

#### Code Quality
- Formatters
- Static code analysis (Sonar)
- Vulnerability check (Snyk)

#### Microservices on bare metal
- Config Server + Client
- Service Discovery
- Client side load balancing

#### Egyéb Modulok ?
- Git
- AI asszisztensek, MCP
- Egyéb jvm profiling toolok - JMeter, Flight Recorder, VisualVM
- Dokumentáció - Asciidoc
- Biztonság - CSRF, XSS, CORS…
- Futtatható fat JAR
- Hibakeresés, debugger használata
- I18N
- Spring - Boot nélkül
  - Alkalmazás rétegek elkészítése 
  - DispatcherServlet
