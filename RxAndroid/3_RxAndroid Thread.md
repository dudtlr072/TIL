# RxAndroid Thread관리

안드로이드는 기본적으로 Single Thread Model이다. 그래서 처리하는데 오래 걸리는 데이터 전송이나, 파일 입/출력 같은 작업은 별도의 Thread로 분리하여 작업해야 한다. RxAndroid에서는 이런 작업을 간단하게 해결이 가능하다.



### AsyncTask

안드로이드에서 제공하는 추상 클래스로 안드로이드에서 사용하는 대표적인 Thread중하나이다. 별도의 Handler나 Thread사용없이 바로바로 View화면에 업데이트 할 수 있다.

AsyncTask<Params, Progress, Result> 클래스를 선언할때 들어가는 Generic타입은 아래와 같다.

> - Params : doInBack ground()의 파라미터 타입, execute() 함수의 인자값
> - Progress : doInBackground()작업 시 진행 단위의 타입, onProgressUpdate()의 파라미터 타입
> - Result : doInBackground()의 리턴 타입, onPostExecute()의 파라미터 타입



AsyncTask의 단점

> 1. execute() 함수는 UI Thead에서만 실행 가능하다.
> 2. Task는 오직 한번만 실행이 가능하며 재사용이 불가능하다.
> 3. AsyncTask가 구현된 Activity종료 시, 별도의 처리가 없다면 종료되지 않으므로 메모리 누수가 발생할 수 있다.



### RxAndroid Thread

