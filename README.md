javafx-modules
==============

'javafx-modules' は、JavaFX 2.xを利用する際のユーティリティ機能を提供するためのライブラリです。
+ 例えば、FXMLとControllerを読み込む際に、Controllerクラスを指定すれば同時に取得することができます。
+ メッセージボックスやbusy indicatorの表示があります。

# 依存ライブラリ
------------------------------
+ Java1.7.+
+ JavaFX2.2+

# ダウンロード
-------------------------------
+ [JavaDoc](http://tatsu-no-otoshigo.github.io/javafx-modules/apidocs/index.html)
+ [Download](http://tatsu-no-otoshigo.github.io/javafx-modules/download/)


# fxmlモジュールの使い方（proj.green.javafx.modules.fxml）
------------------------------

## FXMLとControllerの同時読み込み
-------------------------------
+ 読み込むには、**CustomFxmlTool.loadNodeAndController(...)**を使用します。
+ ファイルの命名規則、配置場所として次のように合わせる必要があります。
++ Controllerは、FXMLの拡張子を除いたものに**Controller**を最後に付けた名前になります。
+ プロパティファイルは、Controllerと同じにする必要があります。ただし、必須ではないので、省略可能です。
++ プロパティファイルを配置している場合、Controllerの「javafx.xml.Initializable」インタフェースで実装した場合の引数「resources」として渡されます。

```profile
sample.javafx <= パッケージ
├ SamplePane.fxml <= FXMLファイル
├ SamplePaneController.java <= ControllerのJavaクラス。
├ SamplePaneController.properties <= Controllerに対するプロパティファイル（省略可能）。

```


```java
import proj.green.javafx.modules.fxml.CustomFxmlTool;
import proj.green.javafx.modules.fxml.NodeAndController;

@Override
public void start(Stage primaryStage) throws Exception {
    
    /*
     * FXML（Node）とControllerの読み込み。
     * ・Nodeは、FXMLのルート要素と同じクラスを指定する必要があります。
     * ・Contrllerはクラスタイプを渡します。
     */
    NodeAndController<AnchorPane, SamplePaneController> nac =
            CustomFxmlTool.loadNodeAndController(AnchorPane.class, SamplePaneController.class);
    
    // FXMLで読み込んだノードを取得できます。
    Node root = nac.getNode();
    primaryStage.setScene(new Scene(root));
    
    // Controllerを取得できます。
    ConstraintPaneController controller = nac.getController();
    
    primaryStage.show();
    primaryStage.requestFocus();
    
}
```

## Controller自身がノードクラスを継承している場合
+ ファイルの命名規則、配置場所として次のように合わせる必要があります。
++ Controllerは、FXMLファイルの拡張子を除いた名前と同じにする必要があります。
+ プロパティファイルは、Controllerと同じにする必要があります。ただし、必須ではないので、省略可能です。
+ FXMLのルート要素（<fx:root type="クラスタイプ">）は、Controllerの継承先と合わせる必要があります。
+ ControllerがPOJOではなく、AnchoePaneなどを継承して作成されている場合は、**CustomFxmlTool.loadComponent()** を使用します。
++ Controllerのコンストラクタ内で自身のインスタンスを渡すことで、使用する際には、単純にnewするだけで使えます。


```profile
sample.javafx <= パッケージ
├ SampleAnchorPane.fxml <= FXMLファイル（ただし、<fx:root>要素がControllerの継承先と同じにする必要があります。）
├ SampleAnchorPane.java <= ControllerのJavaクラス。
├ SampleAnchorPane.properties <= Controllerに対するプロパティファイル（省略可能）。

```

```xml
<!-- FXML -->
<fx:root type="AnchorPane" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="400.0" prefWidth="600.0" xmlns:fx="http://javafx.com/fxml">
  <children>
    <VBox prefHeight="400.0" prefWidth="600.0" AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="0.0">
  </children>
</fx:root>
```

```java
// Controllerクラス
public class SampleAnchorPane extends AnchorPane implements Initializable {

    public SampleAnchorPane() {
        // コンストラクタで、自身を渡す
        CustomFxmlTool.loadComponent(this, this);
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
       // 初期化
    }
}
```

```java
// 使う際には、newするだけでよい。
   SampleAnchorPane pane = new SampleAnchorPane();
```


## グローバルなプロパティファイルの指定
+ プロパティファイルは、Controllerごとに持つこともできますが、共通のものを設定することができます。
++ **CustomFxmlTool.init(ResourceBundle)** メソッドでプロパティファイルを設定します。これは、Controllerなどを読み込む前に行います。
+ グローバルなプロパティファイルとControllerのプロパティファイルが両方ある場合、Controllerのプロパティファイルの項目が優先されます。

```java
public class SampleApplication extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void init() {
        // グローバルなプロパティファイルを設定する
        CustomFxmlTool.init(ResourceBundle.getBundle("SampleApplication"));
    }
}
````

# springモジュールの使い方（proj.green.javafx.modules.spring）
------------------------------
+ fxmlモジュールで扱うControllerクラスがSpringBeanの場合は、**SpringFxmlTool**を使います。
+ Springのコンテナのインスタンス（ApplicationContext）をSpringFxmlToolに渡しておきます。

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;


public class SpringSampletApplication extends Application {
    
    
    private ApplicationContext applicationContext;
    
    @Override
    public void init() {
        
        try {
            applicationContext = new ClassPathXmlApplicationContext("TestApplicationContext.xml");
        } catch(Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        // SpringのApplicationContextを設定する
        SpringFxmlTool.init(applicationContext);
    }
}
```

## FXMLとControllerの同時読み込み(with Spring Bean)
-------------------------------
+ ControllerとSpringBeanやインジェクションする場合、**SpringFxmlTool.loadNodeAndControllerFromSpring(...)**を使用します。
+ Controllerは、予めSpringのコンテナに登録しておく必要があります。
++ SpringBeanをコンテナから取得する際には、名前をもとにして処理します。Bean名は、クラス名の先頭を小文字にしたものになります。
++ 例). 「SamplePaneController.java」の場合、Bean名は「samplePaneController」とする必要があります。
++ Controllerをアノテーション「@Component」「@Service」を使って登録しておけば、自動的に名前が決まるので、アノテーションを使って登録することをお勧めします。

```java
// Controllerを定義します。
@Component
public class Prototype2Controller {
    
    @Resource
    private Sample1Service sample1Service;
    
    @Autowired
    private Sample2Service sample2Service;
````

```java
import proj.green.javafx.modules.fxml.SpringFxmlTool;
import proj.green.javafx.modules.fxml.NodeAndController;

@Override
public void start(Stage primaryStage) throws Exception {
    
    /*
     * FXML（Node）とControllerの読み込み。
     * ・Nodeは、FXMLのルート要素と同じクラスを指定する必要があります。
     * ・Contrllerはクラスタイプを渡します。
     */
    NodeAndController<AnchorPane, SamplePaneController> nac =
            SpringFxmlTool.loadNodeAndControllerFromSpring(AnchorPane.class, SamplePaneController.class);
    
    // FXMLで読み込んだノードを取得できます。
    Node root = nac.getNode();
    primaryStage.setScene(new Scene(root));
    
    // Controllerを取得できます。
    ConstraintPaneController controller = nac.getController();
    
    primaryStage.show();
    primaryStage.requestFocus();
    
}
```
## Controller自身がノードクラスを継承している場合

+ ControllerがPOJOではなく、AnchoePaneなどを継承して作成されている場合は、**SpringFxmlTool.loadComponentFromSpring()** を使用します。
+ ファイルの命名規則は、Springを利用しない場合と同様です。
+ Controller自身は、SpringBeanとして登録はしませが、フィールドに対してインジェクションするときには、アノテーション（@Resource、@Autowired）を利用します。
++ 準備として、**SpringAnnotationInjector**をSpringのコンテナに登録しておく必要があります。

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop-2.5.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx-2.5.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-2.5.xsd
    ">
    
    <!-- AutowiredのScan有効定義 -->
    <context:annotation-config/>
    
    <!-- SpringAnnotationInjectorの登録 -->
    <bean id="springAnnotationInjector" class="proj.green.javafx.modules.spring.SpringAnnotationInjector" />

</beans>
```


```profile
sample.javafx <= パッケージ
├ SampleAnchorPane.fxml <= FXMLファイル（ただし、<fx:root>要素がControllerの継承先と同じにする必要があります。）
├ SampleAnchorPane.java <= ControllerのJavaクラス。
├ SampleAnchorPane.properties <= Controllerに対するプロパティファイル（省略可能）。

```

```java
// Controllerクラス
public class SampleAnchorPane extends AnchorPane {
    
    @Resource
    private Sample1Service sample1Service;
    
    @Autowired
    private Sample2Service sample2Service;
    
    public SampleAnchorPane() {
        // コンストラクタで、自身を渡す
        SpringFxmlTool.loadComponentFromSpring(this, this);
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
       // 初期化
    }
}
```

```java
// 使う際には、newするだけでよい。
   SampleAnchorPane pane = new SampleAnchorPane();
```

## Prototypeスコープのインスタンスの管理
+ Prototypeスコープの今までで取得したインスタンスは、**List<PrototypeBean> SpringFxmlTool.getPrototypeBeans()**で取得することができます。
+ ただし、弱参照で情報を保持しているため、インスタンスがnullになっている場合もあります。
+ インタフェース「DisposableBean」やアノテーション「@PreDestroy」が付与されたメソッドを呼んで、終了させる時などに利用します。
++ 終了させるときは、**SpringFxmlTool.destroyAllProrotypeObject()**を呼びます。

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import proj.green.javafx.modules.fxml.NodeAndController;

import javafx.application.Application;

public class SpringFxmlLoaderTestApplication extends Application {
    
    private ApplicationContext applicationContext;
    
    private SpringLoaderTestPaneController controller;
    
    @Override
    public void init() {
        
        try {
            applicationContext = new ClassPathXmlApplicationContext("TestApplicationContext.xml");
        } catch(Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        // SpringのApplicationContextを設定する
        SpringFxmlTool.init(applicationContext);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //TODO:
        
    }
    
    public void stop() {
        
        if(applicationContext != null && applicationContext instanceof AbstractApplicationContext) {
            // SpringのPrototpypeスコープの終了
            SpringFxmlTool.destroyAllProrotypeObject();
            
            // Springのコンテナの終了
            ((AbstractApplicationContext) applicationContext).close();
            System.out.println("close");
        }
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```


# メッセージボックス（proj.green.javafx.modules.messagebox）
-------------------------------
+ ビルダー形式で、メッセージボックスを組み立てます。
+ **MessageBoxBulder**クラスを利用します。
++ メッセージボックスを表示するには、show()メソッドを利用します。
++ どのボタンを押されたかどうか、show()メソッドの戻り値として列挙型**MessageBoxEvent**が返されます。

```java
MessageBoxEvent ans = MessageBoxBulder.create()
     .icon(MessageBoxIcon.QUESTION)  //メッセージボックスのアイコンの指定
     .title("daialog title")         // タイトルの指定
     .message("message").appendMessage("append") // メッセージの指定。改行も反映されます。
     .buttonYes().buttonNo()         // ボタンの指定。
     .playBeep()                     // Beep音を鳴らす場合
     .show(root);                    // ダイアログの表示。親のノードを指定しない場合はnullや引数なしのshow()メソッドを呼びます。
```

# busy indicator（proj.green.javafx.modules.indicator）
--------------------------------
+ 進捗率を伴わないBusy Inidicatorを表示します。
++ JavaFXには、**ProgressIndicator**をprogressを指定しなければ、busy indicatorが表示できますが、デザインが１つしかありません。
+ JavaScriptのライブライである[busy.js](http://www.netzgesta.de/busy/)のように、大きさ、色、デザインをカスタマイズできます。

## indicatorを単体で表示する
+ **BusyIndicatorBuilder**を使用して、**BusyInidocator**のインスタンスを組み立てます。

```java
BusyIndicator indicator1 = BusyIndicatorBuilder.create()
                .typeEllipse()
                .color(Color.BLUE)
                .radius(10)
                .weight(10)
                .size(30)
                .speed(90)
                .build();
                
// indicatorの表示
indicator1.playFromStart();

// indicatorの非表示
inidicator1.stop();

```


## パネルの上にinidicatorを表示する
+ パネルの上にInidicatorを表示することで、表示中はその後ろにあるControllerなどに触れないようにします。
+ **BusyIndicatorPaneBuilder**を使用して、**BusyIndicatorPane**のインスタンスを組み立てます。
+ inidicator用のパネルは、StackPaneに設定します。
+ 注意点として、Busy inidicatorは、JavaFXのスレッドで動作させるため、表示中にバッチ処理などを

```java
public class BusyIndicatorTestPaneController {
    
    @FXML
    private StackPane myNode;
    
    private BusyIndicatorPane indicatorPane;
    
    @FXML
    private void initialize() {
        
        // インジケータ用のpaneを追加
        indicatorPane = new BusyIndicatorPane();
        indicatorPane.setBusyIndicator(BusyIndicatorBuilder.create()
                .color(Color.BLUE)
                .build());
        
    }
    
    @FXML
    private void handleShowIndicator(final ActionEvent event) {
        
        // インジケータの表示
        indicatorPane.playFromStart();
        myNode.getChildren().add(indicatorPane);
        
        indicatorPane.setMessage("メッセージ");
        
        Task<String> task = new Task<String>() {
            
            @Override
            protected String call() throws Exception {
                //TODO: 重い処理
                System.out.println("end");
                
                indicatorPane.stop();
                return "end";
            }
        };
        
        // 別スレッドで実行する
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        // インジケータの終了
                        stopInidcator();
                        
                        executor.shutdown();
                    }
                });
        
        executor.submit(task);
       
    }
    
    /**
     * 表示中のinidicatorを非表示にする
     */
    private void stopInidcator() {
        if(inidicatorPane != null) {
            myNode.getChildren().remove(indicatorPane);
            indicatorPane.stop();
            indicatorPane = null;
        }
    }
}

```



