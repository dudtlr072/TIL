# RxBinding

RxBinding 라이브러리는 View 객체들의 UI 이벤트를 처리할 수 있는 라이브러리로, click, drag, touch등 다양한 UI 이벤트를 자동으로 리스너 등록까지 해준다.

```java
public class MainActivity extends RxAppCompatActivity {

    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RxView.clicks(mButton)
                .map(e -> "clicked")
                .subscribe(getObserver());
    }
}
```


## 추천 검색어 기능 구현하기

> - RxTextView.textChangeEvents() 활용
> - 500ms 동안 입력이 없으면 search 실행​

```java
RxTextView.textChanges(mSearchBox)
    .debounce(500, TimeUnit.MILLISECONDS)
    .filter(s -> !TextUtils.isEmpty(s))
    .observeOn(mainThread())
    .subscribe(s -> mResult.setText("Search: " + s));
```

