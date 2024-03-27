JPA
-INSERT, UPDATE....WHERE
Entity save(Entity) : Entity값으로 데이터베이스에 전달해서 저장 및 수정 후 작업한 
                        Entity를 전달한다.

-DELETE....WHERE(By)
void delete(삭제) by(대상)조회할 분수(값) 해당값이 지정한 변수에 존재하면 삭제

-SELECT....WHERE(By) 필드명=값
Entity(사용자가 임의 선언)
Optional<Entity>    find(조회) by 변수명(값) :개별조회
List<Entity>                               :전체조회
Page<Entity>                               :전체조회(페이지 정보)


1. 제목이 woori인 자료를 검색(일치하면 조회)
findByTitle("woori")   woori(o),woorixt(x)
2. 제목에 woori가 포함된 자료를 검색(비슷한 값 조회)
findByContaining("woori") woori(o), samwoori(o), wooritxg(o)
3. 2024년 3월 1일 이후의 자료를 검색
findByModDateAfter("2024-3-1)
4. 2024년 3월 1일 이전의 자료를 검색
findByModDateBefore("2024-3-1)
5. 2024년 3월 1일부터 3월 15일까지의 자료를 검색
findByModDateBetween("2024-3-1","2024-3-15")
6. 상품가격이 10000원 초과이면 자료를 검색
findByPriceGreaterThan(10000)
7. 상품가격이 10000원 미만이면 자료를 검색
findByPriceLessThan(10000)
8. 상품가격이 10000원 이상이면 자료를 검색
findByPriceGreaterThanEqual(10000)
9. 상품가격이 10000원 이하이면 자료를 검색
findByPriceLessThanEqual(10000)
10. 상품가격이 10000원에서 15000원 사이의 자료를 검색(10000<=가격<15000)
findByPriceBetween(10000,15000)
11. 상품가격이 10000원과 같지 않으면
findByPriceNot(10000)
12. 제목이 woori가 아니면
findByTitleNot("woori")

논리연산자를 이용해서 여러 조건을 조합
AND: 그릭, 이고, 이면서 , 모든 조건을 만족하면 조회
OR: 또는, 이거나, ~중에서, 조건 중 하나라도 만족하면 조회

1.상품이 "상위" 이면서 가격은 15000이하인 자료를 조회
findByProductAndPriceLessThanEqual("상위", 15000)
2.상품이 "신상" 이거나 상품이 "추천"인 자료를 조회
findByProductOrProduct("신상","추천"

JPQ에서 Query
사용자가 데이터베이스 SQL문을 직접 작성해서 조작
1.Entity에 변수명으로 질의어를 조작하는 방법
@Query("SELECT 별칭 FROM ENTITY명 별칭 WHERE 별칭.변수명=값")
2.Entity에 @Column으로 선언된 필드명으로 조작하는 방법
@Query("SELECT*FROM Entity명 WHERE 필드명="값", nativeQuery=true)

동적 SQL : 사용자가 모든 문법을 완성 -JPA
정적 SQL : 문법을 메소드로 만들어서 값 -DSLQuery