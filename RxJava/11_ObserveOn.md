# observeOn()

> - **subscribeOn()** 함수는 한번 호출했을 때 결정한 Thread를 고정하며, 이후에는 다시 호출해도 Thread가 바뀌지 않는다.
> - **observeOn()** 함수는 여러 번 호출할 수 있으며, 호출되면 그다음부터 동작하는 Thread를 바꿀 수 있다.

![scheduler](http://reactivex.io/documentation/operators/images/schedulers.png)



## observeOn() 실습

> - 각 Observable들이 observeOn()에 의해 Thread가 어떻게 갈리는지 확인한다.
> - **share()**
>   - **.publish().refCount()와 동일**한 함수
>   - **publish()는 connect()를 직접 호출** 해줘야 하는 함수이다.
>   - **refCount()는 subscribe()를 어떤 Observable에서든 한번 하기만하면 자동으로 connect()가 호출**되는 함수이다.
>   - 예제 코드에서는 **각 subscribe()가 거의 동시에 이루어 지기 때문에 API call은 한번만** 이루어지고, 각 subscribe()에서는 결과값만 받을 수 있다.

```java
private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=London&APPID=";
private static final String API_KEY = "****************************";

private final OkHttpClient client = new OkHttpClient();

private String httpGet(String url) throws IOException {
  Utils.print("[api call] " + url);
  Request request = new Request.Builder()
    .url(url)
    .build();
  Response res = client.newCall(request).execute();
  return res.body().string();
}

private void run() {
  Observable<String> source = Observable.just(URL + API_KEY)
    .map(this::httpGet)
    .share()						  // httpGet함수를 한번만 실행할 수 있게 해준다
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.newThread()); // 각각 새로운 Thread에서 실행

  Utils.setStartTime();
  source.map(this::parseTemperature)
    .subscribe(Utils::log_i);
  source.map(this::parseCityName)
    .subscribe(Utils::log_i);
  source.map(this::parseCountry)
    .subscribe(Utils::log_i);
  Utils.sleep(2000);
}

private String parseTemperature(String json) {
  return parse(json, "\"temp\":[0-9]*.[0-9]*");
}

private String parseCityName(String json) {
  return parse(json, "\"name\":\"[a-zA-Z]*\"");
}

private String parseCountry(String json) {
  return parse(json, "\"country\":\"[a-zA-Z]*\"");
}

private String parse(String json, String regex) {
  Pattern pattern = Pattern.compile(regex);
  Matcher match = pattern.matcher(json);
  if (match.find()) {
    return match.group();
  }
  return "N/A";
}

public static void main(String args[]) {
  _18_ObserveOnTest demo = new _18_ObserveOnTest();

  demo.run();
}
```

```java
// Output
[api call] https://api.openweathermap.org/data/2.5/weather?q=London&APPID=**********************
RxCachedThreadScheduler-1 | 778ms | "temp":274.44
RxCachedThreadScheduler-2 | 778ms | "name":"London"
RxCachedThreadScheduler-3 | 778ms | "country":"GB"
```

