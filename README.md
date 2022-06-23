
## 개발환경 구성
- Gradle 
- Springboot 
- Java11 
- Spring REST Docs
- Spring Data JPA
- H2 Database
- Spring Web
 
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0/gradle-plugin/reference/html/#build-image)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#web)


## 요구사항 정리 
- 카테고리 등록/수정/삭제 API 
  * 카테고리를 등록/수정/삭제 할 수 있어야 합니다.
- 카테고리 조회 API 
  * 상위 카테고리를 이용해, 해당 카테고리의 하위의 모든 카테고리를 조회 가능해야 합니다.
  * 상위 카테고리를 지정하지 않을 시, 전체 카테고리를 반환해야 합니다.

### 카테고리 구성 
대/중/소분류 카테고리 코드, 현재 카테고리 ID, 카테고리 레벨, 카테고리명

## 테스트 정의 
- Unit test 및 Integration test 작성
> JUnit repository, controller, service UnitTest 생성


### 테스트 
#### 1. 연결 테스트 
> http://localhost:8080/hello
```json
{
"success": true,
"code": 0,
"msg": "성공하였습니다."
}
```