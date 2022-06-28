## 개발환경 구성
- Gradle 
- Springboot 2.7.0 
- Java11 
- Spring Data JPA
- H2 Database
- Spring Web
[X] Spring REST Docs 

## 요구사항 정리 
- 카테고리 등록/수정/삭제 API 
  * 카테고리를 등록/수정/삭제 할 수 있어야 합니다.
- 카테고리 조회 API 
  * 상위 카테고리를 이용해, 해당 카테고리의 하위의 모든 카테고리를 조회 가능해야 합니다.
  * 상위 카테고리를 지정하지 않을 시, 전체 카테고리를 반환해야 합니다.

### 카테고리 구성 
대/중/소분류 카테고리 코드, 현재 카테고리 ID, 카테고리 레벨, 카테고리명

### 구현 내역정리 
** 추가 제약 사항
- 상위 카테고리는 수정이 될수 없고 삭제만 가능
- 하위 카테고리가 존재할시 삭제 불가 
- 카테고리 레벨은 수정 불가능

- 카테고리 등록/수정
  * [O] 수정/삭제 form으로 입력받아서, validation 처리
    * 카테고리명, 레벨 필수값 입력
    * 레벨에 따른 대,중 분류별 카테고리 ID 기입 체크
  * [O] 수정/삭제 디비 데이터 조회하여 validation 처리
    * 동일 카테고리명 존재여부 조회
    * 레벨 기준 상위 카테고리 null 체크
    * 수정 시, ID 유효성 체크 

- 카테고리 삭제 
  * 하위 카테고리가 미 존재할 시 삭제
  * 하위 카테고리가 존재할 시 dispYn = false 업데이트
  * 카테고리 ID 기준으로 삭제

- 카테고리 단일 조회 
  * 카테고리 ID 기준으로 조회

- 카테고리 대분류 기준 하위 카테고리 조회
  * 카테고리 ID, level 기준으로 조회

- 카테고리 중분류 기준 하위 카테고리 조회
  * 카테고리 ID, level 기준으로 조회
  
- 카테고리 전체 조회 (전시가능한 카테고리만)

- 카테고리 레벨단위 조회 
  * 카테고리 level 기준으로 조회
  

## 데이타 리턴 타입 정의 
result를 data 항목으로 return 한다.

### 정상
```json
{
    "status": 200,
    "errorCode": null,
    "message": "OK",
    "result": [], 
    "error": false
}
```
### 에러
```json
{
    "status": 304,
    "errorCode": null,
    "message": "Not Modified",
    "result": null,
    "error": true
}
```


## 테스트 정의 
- Unit test 및 Integration test 작성
> JUnit repository, controller, service UnitTest 생성

- http 요청에 의해 API 호출 테스트 
> TEST.http 파일 참고

## 추가 작업 사항
[O] [JPQL 특징을 살려 메서드 이름 변경하여 쿼리 어노테이션 없이 수행](https://www.devkuma.com/docs/jpa/%EC%9E%90%EB%8F%99-%EC%83%9D%EC%84%B1-%EC%BF%BC%EB%A6%AC-%EB%A9%94%EC%86%8C%EB%93%9C%EC%9D%98-%EB%AA%85%EB%AA%85-%EA%B7%9C%EC%B9%99/)
[ ] [@DynamicInsert, @DynamicUpdate or default 값으로 빈값 처리 안되도록 수정](https://dotoridev.tistory.com/6)
[ ] REST Docs 적용 or Swagger 적용 or 삭제