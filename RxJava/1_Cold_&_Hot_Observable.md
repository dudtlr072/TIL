Cold observable & Hot observable
=

### 1. Cold observable
> - Observable을 선언하고 다른 함수를 호출해도 subscribe()를 호출하여 구독 하지 않으면 데이터를 발행하지 않음
> - 구독하면 준비된 데이터를 처음부터 발행
> - Ex) 웹 요청, DB 쿼리, 파일 읽기 등

### 2. Hot observable
> - 구독자의 존재여부와 상관없이 데이터를 발행
> - 구독한 시점부터 Observable에서 발행한 값을 받음
> - Ex) 마우스 이벤트, 키보드 이벤트, 시스템 이벤트, 센서 데이터 등
