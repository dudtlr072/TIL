# Scheduler_callback_hell

연속적인 네트워크 요청이 필요할 때의 콜백 지옥은 RxJava를 통해 간단히 해결이 가능하다. 별도의 Thread나 Callable, Runnable객체를 실행하는 코드가 필요하지 않다.



## Before

> - 첫번째 URL을 호출하고 성공 콜백이 오면 두번째 URL을 호출 하는 방식
> - 콜백 메서드가 중첩되어 사용될 수 밖에 없다.
> - 가독성이 떨어진다

```java
public class _17_SchedulerCallbackHellTest {

    private static final String GITHUB_ROOT = "https://raw.githubusercontent.com/yudong80/reactivejava/master";
    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_hell";
    private final OkHttpClient client = new OkHttpClient();

    public void before_callbackhell() {
        Request request = new Request.Builder()
                .url(FIRST_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Utils.print(response.body().string());

                // 콜백을 다시추가
                Request request = new Request.Builder()
                        .url(SECOND_URL)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Utils.print(response.body().string());
                    }
                });
            }
        });
    }

    public static void main(String args[]) {
        _17_SchedulerCallbackHellTest demo = new _17_SchedulerCallbackHellTest();

        demo.before_callbackhell();
    }
}
```

```java
// Output
Avoid administrative distraction.
Welcome to Callback Hell!!
```



## After

> - **네트워크 요청이기때문에 Schedulers.io()를 사용**
> - concatWith()함수를 사용하여 2개의 네트워크 요청 Observable을 연결한다
> - **concatWith()를 사용하면 2번째 Observable은 concatWith()안에서 작업을 끝내고 나와야지만 1번째 Observable의 결과값과 이어질 수 있다**
> - 가독성이 좋아짐

```java
public class _17_SchedulerCallbackHellTest {

    private static final String GITHUB_ROOT = "https://raw.githubusercontent.com/yudong80/reactivejava/master";
    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_heaven";
    private final OkHttpClient client = new OkHttpClient();

    public String httpGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response res = client.newCall(request).execute();
            return res.body().string();
        } catch (IOException e) {
            throw e;
        }
    }

    public void after_observable() {
        Observable.just(FIRST_URL)
                .map(this::httpGet)
                .concatWith(Observable.just(SECOND_URL)
                        .map(this::httpGet))
                .subscribeOn(Schedulers.io())
                .subscribe(Utils::print, System.out::println);
        Utils.sleep(5000);
    }

    public static void main(String args[]) {
        _17_SchedulerCallbackHellTest demo = new _17_SchedulerCallbackHellTest();

        demo.after_observable();
    }
}
```

```java
// Output
Speak like a human.
Happy Callback Heaven by RxJava2!!
```



## 동시성 네트워크 호출

> - concatWith()함수를 결국 첫번째 Observable의 데이터 발행이 끝날때까지 기다려야 한다.
> - 첫 번째 URL과 두 번째 URL요청을 동시에 수행하고 결과만 조합한다면 좀더 빠른 성능향상을 기대하 수 있다.

```java
Utils.setStartTime();
Observable<String> first = Observable.just(FIRST_URL)
  .subscribeOn(Schedulers.io())
  .map(this::httpGet);
Observable<String> second = Observable.just(SECOND_URL)
  .subscribeOn(Schedulers.io())
  .map(this::httpGet);

Observable.zip(first, second, (a, b) -> "\n>> " + a + "\n>> " + b)
  .subscribe(Utils::log_i);
Utils.sleep(5000);
```

```java
// Output
RxCachedThreadScheduler-1 | 1370ms | 
>> Approachable is better than simple.
>> Happy Callback Heaven by RxJava2!!
```

