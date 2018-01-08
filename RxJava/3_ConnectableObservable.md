# ConnectableObservable

> ConnectableObservable 클래스는 Subject 클래스처럼 차가운 Observable을 뜨거운 Observable로 변환한다. 하지만 connect()를 호출하기 전까지는 아무리 Subscibe()를 하더라도 데이터가 발행되지 않는다.
>
> 생성된 Observable에서 publish() 함수를 호출하면 ConnectableObservable로 변환되어 데이터 발행을 유예시킨다. 그리고나서 connect()를 호출하면 현재 Subscribe() 함수를 호출하고 있는 모든 Observable에 데이터를 발행하기 시작한다.



![ConnectableObservable](http://reactivex.io/documentation/operators/images/publishConnect.c.png)



```java
String[] dt = {"1", "3", "5"};
ConnectableObservable<String> source = Observable.interval(100, TimeUnit.MILLISECONDS)
  		.map(Long::intValue)
  		.map(i -> dt[i])
  		.take(dt.length)
  		.publish();
source.subscribe(data -> System.out.println("#1 => " + data));
source.subscribe(data -> System.out.println("#2 => " + data));
source.connect();
try {
  Thread.sleep(250);
} catch (InterruptedException e) {
  e.printStackTrace();
}
source.subscribe(data -> System.out.println("#3 => " + data));
try {
  Thread.sleep(100);
} catch (InterruptedException e) {
  e.printStackTrace();
}
```

```java
// Output
#1 => 1		// 100ms
#2 => 1		// 100ms
#1 => 3		// 200ms
#2 => 3		// 200ms
#1 => 5		// 300ms
#2 => 5		// 300ms
#3 => 5		// 300ms
```

