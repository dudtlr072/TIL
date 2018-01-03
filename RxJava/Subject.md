# AsyncSubject

> AsyncSubject 클래스는 Observable에서 발행한 마지막 데이터를 받아온다. onComplete이 불리기 전 데이터만 관심이 있고, 그 이전 데이터는 모두 무시한다.



![AsyncSubject](http://reactivex.io/documentation/operators/images/S.AsyncSubject.png)

```java
AsyncSubject<String> subject = AsyncSubject.create();
subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
subject.onNext("1");
subject.onNext("3");
subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
subject.onNext("5");
subject.onComplete();
```

```java
// Output
Subscriber #1 => 5
Subscriber #2 => 5
```



> Subject클래스는 Observable과 Observer클래스 모두를 상속받기 때문에 구독자로도 동작이 가능하다

```java
public abstract class Subject<T> extends Observable<T> implements Observer<T>
```
```java
Float[] temp = {10.1f, 13.4f, 12.5f};
Observable<Float> source = Observable.fromArray(temp);

AsyncSubject<Float> subject = AsyncSubject.create();
subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));

source.subscribe(subject);
```

```java
// Output
Subscriber #1 => 12.5
```



> onComplete()이 호출 된 이후에 subscribe()를 해도 제일 마지막 에 발행된 값을 받아온다.



# BehaviorSubject

> BehaviorSubject는 구독을 하면 가장 최근값 혹은 초기값을 넘겨주는 클래스이다.
>
> 항상 초기값을 가지고 있어야 하기 때문에 createDefault() 함수로 생성한다.



![BehaviorSubject](http://reactivex.io/documentation/operators/images/S.BehaviorSubject.png)

```java
BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");
subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
subject.onNext("1");
subject.onNext("3");
subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
subject.onNext("5");
subject.onComplete();
```

```java
// Output
Subscriber #1 => 6
Subscriber #1 => 1
Subscriber #1 => 3
Subscriber #2 => 3
Subscriber #1 => 5
Subscriber #2 => 5
```


# PublishSubject

> 가장 평범한 클래스로, subscribe()을 호출하면 그때부터 값을 발행하기 시작한다. 마지막 값을 발행하지도 않고, 초기값도 없으며 오직 해당 시간에 발생한 데이터만 전달한다.



![PublishSubject](http://reactivex.io/documentation/operators/images/S.PublishSubject.png)

```java
PublishSubject<String> subject = PublishSubject.create();
subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
subject.onNext("1");
subject.onNext("3");
subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
subject.onNext("5");
subject.onComplete();
```

```java
// Output
Subscriber #1 => 1
Subscriber #1 => 3
Subscriber #1 => 5
Subscriber #2 => 5
```


# ReplaySubject

> ReplaySubject는 subscribe()를 호출하면 그때까지 발행된 모든 데이터를 발행한다. 모든 데이터를 저장하고 있기 때문에 메모리 누수가 발생할 가능성을 염두에 둬야 한다.



![ReplaySubject](http://reactivex.io/documentation/operators/images/S.ReplaySubject.png)

```java
ReplaySubject<String> subject = ReplaySubject.create();
subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
subject.onNext("1");
subject.onNext("3");
subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
subject.onNext("5");
subject.onComplete();
```

```java
// Output
Subscriber #1 => 1
Subscriber #1 => 3
Subscriber #2 => 1
Subscriber #2 => 3
Subscriber #1 => 5
Subscriber #2 => 5
```