## 개발환경 구성
- Gradle 7.4.1
- Springboot 2.7.0 
- Java11 
- Spring Data JPA
- H2 Database
- Spring Web
- Spring REST Docs 3.3.2

## 요구사항 정리 
- 카테고리 등록/수정/삭제 API 
  * 카테고리를 등록/수정/삭제 할 수 있어야 합니다.
- 카테고리 조회 API 
  * 상위 카테고리를 이용해, 해당 카테고리의 하위의 모든 카테고리를 조회 가능해야 합니다.
  * 상위 카테고리를 지정하지 않을 시, 전체 카테고리를 반환해야 합니다.

### 카테고리 구성 
- 대/중/소분류 카테고리 코드
- 현재 카테고리 ID
- 카테고리 레벨
- 카테고리명
- 전시여부

### 구현 내역정리 
**[추가 제약 사항]**
- 상위 카테고리는 수정이 될수 없고 삭제만 가능

- 하위 카테고리가 존재할시 삭제 불가 
   
- 카테고리 레벨은 수정 불가능
  
- 카테고리 등록/수정
  * 수정/삭제 form으로 입력받아서, validation 처리
    * 카테고리명, 레벨 필수값 입력
    * 레벨에 따른 대,중 분류별 카테고리 ID 기입 체크
  * 수정/삭제 디비 데이터 조회하여 validation 처리
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
#### 에러 유형
- HTTP 기본유형 (404, 500 등 미포함)
- Custom 에러 
  * 998 : validate 해야할 객체 null 인 경우
  * 999 : validate 에러
  
```json
{
    "status": 304,
    "errorCode": null,
    "message": "Not Modified",
    "result": null,
    "error": true
}
```

## 테스트 및 문서 정의 
### 방법1. JUnit Test 실행 후 REST Docs 결과값 확인
- JUnit repository, service, API UnitTest 생성
  * REST Docs 저장 경로 : categoryapi/custom

### 방법2. TEST.http 이용하여 API 호출 테스트
- categoryapi/TEST.http 파일 참고