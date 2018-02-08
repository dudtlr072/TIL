# RxAndroid

RxAndroid는 RxJava에 최소한의 클래스를 추가하여 안드로이드 앱에서 Reactive 구성 요소를 쉽고 간편하게 사용하도록 만든 라이브러리이다. 기존 Android개발에서 가장 어려움을 겪는 문제 중 하나는 복잡한 스레드 사용인데, 그 결과 다음과 같은 문제들이 발생한다. 하지만 RxAndroid라이브러리가 해당 문제들을 해결하는데 도움을 준다.

- Android의 비동기 처리 및 에러 핸들링
- 수많은 핸들러와 콜백 때문에 발생하는 디버깅 문제
- 2개의 비동기 처리 후 결과를 하나로 합성하는 작업
- 이벤트 중복 실행




## Reactive library

> 출처 - https://github.com/ReactiveX/RxAndroid/wiki

| Reactive API 이름        | 설명                                                         |
| ------------------------ | ------------------------------------------------------------ |
| RxLifecycle              | RxJava를 사용하는 안드로이드 앱용 라이프 사이클 처리 API. 일정 관리 도구로 유명한 트렐로(Trello)에서 만듬. |
| RxBinding                | 안드로이드 UI 위젯용 RxJava 바인딩 API |
| SqlBrite                 | SQLiteOpenHelper와 ContentResolver 클래스의 wrapper 클래스로 쿼리에 리액티브 스트림을 도입 |
| Android-ReactiveLocation | 안드로이드용 리액티브 위치 API 라이브러리 (RxJava 1.x 기준) |
| RxLocation               | 안드로이드용 리액티브 위치 API 라이브러리 (RxJava 2.x 기준) |
| rx-preference            | 안드로이드용 리액티브 SharedPreference 인터페이스 |
| RxFit                    | 안드로이드용 리액티브 Fit 라이브러리 |
| RxWear                   | 안드로이드용 리액티브 웨어러블 API 라이브러리 |
| RxPermissions            | RxJava에서 제공하는 안드로이드 런타임 퍼미션 라이브러리 |
| RxNotification           | RxJava로 Notification을 관리하는 API |
| RxClipboard              | 안드로이드 클립 보드용 RxJava 바인딩 API |
| RxBroadcast              | 안드로이드 Broadcast 및 LocalBroadcast에 관한 RxJava 바인딩 API |
| RxAndroidBle             | 블루투스 LE 장치를 다루기 위한 리액티브 라이브러리 |
| RxImagePicker            | 갤러리 또는 카메라에서 이미지를 선택하기 위한 리액티브 라이브러리 |
| ReativeNetwork           | 네트워크 연결 상태나 인터넷 연결 상태를 확인하는 리액티브 라이브러리 (RxJava 1.x 및 RxJava 2.x와 호환) |
| ReactiveBeacons | 주변에 있는 블루투스 LE 기반의 Beacon을 수신하는 리액티브 라이브러리 (RxJava 1.x 및 RxJava 2.x와 호환) |
| RxDataBinding | 안드로이드 데이터 바인딩 라이브러리용 RxJava 2 바인딩 API |



## RxAndroid Scheduler

기본적으로 RxJava에서 사용하는 모든 Scheduler를 사용할 수 있고, RxAndroid에서 추가로 제공되는 스케줄러는 아래와 같다. **Android app에서는 Observable의 결과물이 대부분 UI로 표현되기 때문에 mainThread()를 자주 사용한다.**

| 스케줄러 이름                  | 설명                                       |
| ------------------------------ | ------------------------------------------ |
| AndroidSchedulers.mainThread() | 안드로이드 UI 스레드에서 동작하는 스케줄러 |
| HandlerScheduler.from(handler) | 특정 핸들러에 의존하여 동작하는 스케줄러   |



## RxLifecycle 라이브러리

안드로이드 앱에서 subscription할 때 발생할 수 있는 메모리 누수를 방지하기 위해 사용하며, 화면이 종료될때 자동으로 dispose()를 해준다.

```java
public class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Observable.just("Hello, rx world!")
                .compose(bindToLifecycle())
                .subscribe(mText::setText);
    }
}
```

