# Scheduler

스케줄러는 스레드를 지정할 수 있게 해준다. 기본적으로 subscribeOn()과 observeOn() 함수를 통해 지정되며, Observable 함수에서 바로 사용하는것도 가능하다. 

![scheduler](http://reactivex.io/documentation/operators/images/schedulers.png)

> 기본적인 스케줄러의 특징은 아래와 같다.
>
> - **스케줄러는 RxJava코드를 어느 Thread에서 실행할지 지정**할 수 있다.
> - **subscrbeOn()과 oberveOn()함수 모두 지정**하면 Observable에서 데이터 흐름이 발생하는 Thread와 처리된 결과를 구독자에게 발행하는 **Thread를 분리**할 수 있다.
> - **subscribeOn()만 호출하면 Observable의 모든 흐름이 해당 Thread에서만 실행**된다.
> - 스케줄러를 지정하지 않으면 **default는 Main Thread**에서 실행된다.



## 1. Schedulers.newThread()

> - 일반적인 새로운 Thread를 생성한다.
> - 요청 받을때마다 새로운 Thread를 생성한다.

```java
String[] list = {"1", "3", "5"};

Observable.fromArray(list)
  .doOnNext(data -> Utils.log_i("Original data : " + data))
  .map(data -> "<<" + data + ">>")
  .subscribeOn(Schedulers.newThread())
  .subscribe(Utils::log_i);
Utils.sleep(500);

Observable.fromArray(list)
  .doOnNext(data -> Utils.log_i("Original data : " + data))
  .map(data -> "##" + data + "##")
  .subscribeOn(Schedulers.newThread())
  .subscribe(Utils::log_i);
Utils.sleep(500);
```

```java
// Output
RxNewThreadScheduler-1 | Original data : 1
RxNewThreadScheduler-1 | <<1>>
RxNewThreadScheduler-1 | Original data : 3
RxNewThreadScheduler-1 | <<3>>
RxNewThreadScheduler-1 | Original data : 5
RxNewThreadScheduler-1 | <<5>>
RxNewThreadScheduler-2 | Original data : 1
RxNewThreadScheduler-2 | ##1##
RxNewThreadScheduler-2 | Original data : 3
RxNewThreadScheduler-2 | ##3##
RxNewThreadScheduler-2 | Original data : 5
RxNewThreadScheduler-2 | ##5##
```





## 2. Schedulers.computation()

> - CPU에 대응하는 스케줄러로, Thread의 대기시간 없이 빠르게 결과를 도출해야할 때 사용한다.
> - 입/출력 작업을 하지 않는 스케줄러이다.
> - 내부적으로 Thread pool을 생성하며 Thread개수는 기본적으로 프로세서 개수와 동일하다.

```java
String[] list = {"1", "3", "5"};

// zipWith()는 기본적으로 computation()스케줄러이기 때문에 subscribeOn()은 생략해도 된다.
Observable<String> source = Observable.fromArray(list)
  .zipWith(Observable.interval(100, TimeUnit.MILLISECONDS),
           (listItem, time) -> listItem);

source.map(item -> "<<" + item + ">>")
  .subscribeOn(Schedulers.computation())
  .subscribe(Utils::log_i);

source.map(item -> "##" + item + "##")
  .subscribeOn(Schedulers.computation())
  .subscribe(Utils::log_i);
Utils.sleep(1000);
```

```java
// Output
RxComputationThreadPool-4 | <<1>>
RxComputationThreadPool-3 | ##1##
RxComputationThreadPool-3 | ##3##
RxComputationThreadPool-4 | <<3>>
RxComputationThreadPool-4 | <<5>>
RxComputationThreadPool-3 | ##5##
```





## 3. Schedulers.io()

> - IO 스케줄러는 필요할때마다 Thread를 계속 생성한다.
> - 주로 네트워크상의 요청을 처리하거나 각종 입/출력 작업을 실행하기 위한 스케줄러로 사용한다.
>   - **computation() : 일반적인 계산 작업**
>   - **io() : 네트워크상의 요청, 파일 입출력, DB쿼리등**

```java
String root = "c:\\";
File[] files = new File(root).listFiles();

Observable.fromArray(files)
  .filter(f -> !f.isDirectory())
  .map(f -> f.getAbsolutePath())
  .subscribeOn(Schedulers.io())
  .subscribe(Utils::log_i);
Utils.sleep(500);
```

```java
// Output
RxCachedThreadScheduler-1 | c:\bootmgr
RxCachedThreadScheduler-1 | c:\BOOTNXT
RxCachedThreadScheduler-1 | c:\hiberfil.sys
RxCachedThreadScheduler-1 | c:\pagefile.sys
RxCachedThreadScheduler-1 | c:\swapfile.sys
```





## 4. Schedulers.trampoline()

> - Thread를 새로 생성하지않고, 현재 Thread에서 무한한 대기행렬(Queue)을 생성한다.
> - **Queue에 작업을 넣고 하나씩 꺼내어 동작**하므로 여러번 subscribe()한다고 해도 **subscribe()의 순서가 바뀌는 경우는 발생하지 않는다.**

```java
String[] list = {"1", "3", "5"};
Observable<String> source = Observable.fromArray(list);

source.map(item -> "<<" + item + ">>")
  .subscribeOn(Schedulers.trampoline())
  .subscribe(Utils::log_i);

source.map(item -> "##" + item + "##")
  .subscribeOn(Schedulers.trampoline())
  .subscribe(Utils::log_i);
Utils.sleep(500);
```

```java
// Output
main | <<1>>
main | <<3>>
main | <<5>>
main | ##1##
main | ##3##
main | ##5##
```





## 5. Schedulers.single()

> - 별도의 Thread를 하나 생성하여 해당 Thread만 계속 사용한다.
> - **여러개의 Observable이 있어도 별도로 생성된 단일 Thread에서 계속 실행된다.**

```java
Observable<Integer> numbers = Observable.range(100, 5);
Observable<Integer> numbers2 = Observable.range(0, 5);

numbers.subscribeOn(Schedulers.single())
  .subscribe(Utils::log_i);

numbers2.subscribeOn(Schedulers.single())
  .subscribe(Utils::log_i);
Utils.sleep(500);
```

```java
// Output
RxSingleScheduler-1 | 100
RxSingleScheduler-1 | 101
RxSingleScheduler-1 | 102
RxSingleScheduler-1 | 103
RxSingleScheduler-1 | 104
RxSingleScheduler-1 | 0
RxSingleScheduler-1 | 1
RxSingleScheduler-1 | 2
RxSingleScheduler-1 | 3
RxSingleScheduler-1 | 4
```





## 6. Schedulers.from(executor객체)

> - Executor클래스를 재사용할 때만 한정적으로 사용한다.
> - Executor에서 사용하는 Thread를 사용할 수 있게 해준다.

```java
String[] list = {"1", "3", "5"};
Observable<String> source = Observable.fromArray(list);
Executor executor = Executors.newFixedThreadPool(10);

source.subscribeOn(Schedulers.from(executor))
  .subscribe(Utils::log_i);

source.subscribeOn(Schedulers.from(executor))
  .subscribe(Utils::log_i);
Utils.sleep(500);
```

```java
// Output
pool-1-thread-1 | 1
pool-1-thread-1 | 3
pool-1-thread-1 | 5
pool-1-thread-2 | 1
pool-1-thread-2 | 3
pool-1-thread-2 | 5
```

