javafx-modules
==============

'javafx-modules' �́AJavaFX 2.x�𗘗p����ۂ̃��[�e�B���e�B�@�\��񋟂��邽�߂̃��C�u�����ł��B
+ �Ⴆ�΁AFXML��Controller��ǂݍ��ލۂɁAController�N���X���w�肷��Γ����Ɏ擾���邱�Ƃ��ł��܂��B
+ ���b�Z�[�W�{�b�N�X��busy indicator�̕\��������܂��B

# �ˑ����C�u����
------------------------------
+ Java1.7.+
+ JavaFX2.2+

# �_�E�����[�h
-------------------------------
+ [JavaDoc](http://tatsu-no-otoshigo.github.io/javafx-modules/apidocs/index.html)
+ [Download](http://tatsu-no-otoshigo.github.io/javafx-modules/download/)


# fxml���W���[���̎g�����iproj.green.javafx.modules.fxml�j
------------------------------

## FXML��Controller�̓����ǂݍ���
-------------------------------
+ �ǂݍ��ނɂ́A**CustomFxmlTool.loadNodeAndController(...)**���g�p���܂��B
+ �t�@�C���̖����K���A�z�u�ꏊ�Ƃ��Ď��̂悤�ɍ��킹��K�v������܂��B
++ Controller�́AFXML�̊g���q�����������̂�**Controller**���Ō�ɕt�������O�ɂȂ�܂��B
+ �v���p�e�B�t�@�C���́AController�Ɠ����ɂ���K�v������܂��B�������A�K�{�ł͂Ȃ��̂ŁA�ȗ��\�ł��B
++ �v���p�e�B�t�@�C����z�u���Ă���ꍇ�AController�́ujavafx.xml.Initializable�v�C���^�t�F�[�X�Ŏ��������ꍇ�̈����uresources�v�Ƃ��ēn����܂��B

```profile
sample.javafx <= �p�b�P�[�W
�� SamplePane.fxml <= FXML�t�@�C��
�� SamplePaneController.java <= Controller��Java�N���X�B
�� SamplePaneController.properties <= Controller�ɑ΂���v���p�e�B�t�@�C���i�ȗ��\�j�B

```


```java
import proj.green.javafx.modules.fxml.CustomFxmlTool;
import proj.green.javafx.modules.fxml.NodeAndController;

@Override
public void start(Stage primaryStage) throws Exception {
    
    /*
     * FXML�iNode�j��Controller�̓ǂݍ��݁B
     * �ENode�́AFXML�̃��[�g�v�f�Ɠ����N���X���w�肷��K�v������܂��B
     * �EContrller�̓N���X�^�C�v��n���܂��B
     */
    NodeAndController<AnchorPane, SamplePaneController> nac =
            CustomFxmlTool.loadNodeAndController(AnchorPane.class, SamplePaneController.class);
    
    // FXML�œǂݍ��񂾃m�[�h���擾�ł��܂��B
    Node root = nac.getNode();
    primaryStage.setScene(new Scene(root));
    
    // Controller���擾�ł��܂��B
    ConstraintPaneController controller = nac.getController();
    
    primaryStage.show();
    primaryStage.requestFocus();
    
}
```

## Controller���g���m�[�h�N���X���p�����Ă���ꍇ
+ �t�@�C���̖����K���A�z�u�ꏊ�Ƃ��Ď��̂悤�ɍ��킹��K�v������܂��B
++ Controller�́AFXML�t�@�C���̊g���q�����������O�Ɠ����ɂ���K�v������܂��B
+ �v���p�e�B�t�@�C���́AController�Ɠ����ɂ���K�v������܂��B�������A�K�{�ł͂Ȃ��̂ŁA�ȗ��\�ł��B
+ FXML�̃��[�g�v�f�i<fx:root type="�N���X�^�C�v">�j�́AController�̌p����ƍ��킹��K�v������܂��B
+ Controller��POJO�ł͂Ȃ��AAnchoePane�Ȃǂ��p�����č쐬����Ă���ꍇ�́A**CustomFxmlTool.loadComponent()** ���g�p���܂��B
++ Controller�̃R���X�g���N�^���Ŏ��g�̃C���X�^���X��n�����ƂŁA�g�p����ۂɂ́A�P����new���邾���Ŏg���܂��B


```profile
sample.javafx <= �p�b�P�[�W
�� SampleAnchorPane.fxml <= FXML�t�@�C���i�������A<fx:root>�v�f��Controller�̌p����Ɠ����ɂ���K�v������܂��B�j
�� SampleAnchorPane.java <= Controller��Java�N���X�B
�� SampleAnchorPane.properties <= Controller�ɑ΂���v���p�e�B�t�@�C���i�ȗ��\�j�B

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
// Controller�N���X
public class SampleAnchorPane extends AnchorPane implements Initializable {

    public SampleAnchorPane() {
        // �R���X�g���N�^�ŁA���g��n��
        CustomFxmlTool.loadComponent(this, this);
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
       // ������
    }
}
```

```java
// �g���ۂɂ́Anew���邾���ł悢�B
   SampleAnchorPane pane = new SampleAnchorPane();
```


## �O���[�o���ȃv���p�e�B�t�@�C���̎w��
+ �v���p�e�B�t�@�C���́AController���ƂɎ����Ƃ��ł��܂����A���ʂ̂��̂�ݒ肷�邱�Ƃ��ł��܂��B
++ **CustomFxmlTool.init(ResourceBundle)** ���\�b�h�Ńv���p�e�B�t�@�C����ݒ肵�܂��B����́AController�Ȃǂ�ǂݍ��ޑO�ɍs���܂��B
+ �O���[�o���ȃv���p�e�B�t�@�C����Controller�̃v���p�e�B�t�@�C������������ꍇ�AController�̃v���p�e�B�t�@�C���̍��ڂ��D�悳��܂��B

```java
public class SampleApplication extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void init() {
        // �O���[�o���ȃv���p�e�B�t�@�C����ݒ肷��
        CustomFxmlTool.init(ResourceBundle.getBundle("SampleApplication"));
    }
}
````

# spring���W���[���̎g�����iproj.green.javafx.modules.spring�j
------------------------------
+ fxml���W���[���ň���Controller�N���X��SpringBean�̏ꍇ�́A**SpringFxmlTool**���g���܂��B
+ Spring�̃R���e�i�̃C���X�^���X�iApplicationContext�j��SpringFxmlTool�ɓn���Ă����܂��B

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
        
        // Spring��ApplicationContext��ݒ肷��
        SpringFxmlTool.init(applicationContext);
    }
}
```

## FXML��Controller�̓����ǂݍ���(with Spring Bean)
-------------------------------
+ Controller��SpringBean��C���W�F�N�V��������ꍇ�A**SpringFxmlTool.loadNodeAndControllerFromSpring(...)**���g�p���܂��B
+ Controller�́A�\��Spring�̃R���e�i�ɓo�^���Ă����K�v������܂��B
++ SpringBean���R���e�i����擾����ۂɂ́A���O�����Ƃɂ��ď������܂��BBean���́A�N���X���̐擪���������ɂ������̂ɂȂ�܂��B
++ ��). �uSamplePaneController.java�v�̏ꍇ�ABean���́usamplePaneController�v�Ƃ���K�v������܂��B
++ Controller���A�m�e�[�V�����u@Component�v�u@Service�v���g���ēo�^���Ă����΁A�����I�ɖ��O�����܂�̂ŁA�A�m�e�[�V�������g���ēo�^���邱�Ƃ������߂��܂��B

```java
// Controller���`���܂��B
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
     * FXML�iNode�j��Controller�̓ǂݍ��݁B
     * �ENode�́AFXML�̃��[�g�v�f�Ɠ����N���X���w�肷��K�v������܂��B
     * �EContrller�̓N���X�^�C�v��n���܂��B
     */
    NodeAndController<AnchorPane, SamplePaneController> nac =
            SpringFxmlTool.loadNodeAndControllerFromSpring(AnchorPane.class, SamplePaneController.class);
    
    // FXML�œǂݍ��񂾃m�[�h���擾�ł��܂��B
    Node root = nac.getNode();
    primaryStage.setScene(new Scene(root));
    
    // Controller���擾�ł��܂��B
    ConstraintPaneController controller = nac.getController();
    
    primaryStage.show();
    primaryStage.requestFocus();
    
}
```
## Controller���g���m�[�h�N���X���p�����Ă���ꍇ

+ Controller��POJO�ł͂Ȃ��AAnchoePane�Ȃǂ��p�����č쐬����Ă���ꍇ�́A**SpringFxmlTool.loadComponentFromSpring()** ���g�p���܂��B
+ �t�@�C���̖����K���́ASpring�𗘗p���Ȃ��ꍇ�Ɠ��l�ł��B
+ Controller���g�́ASpringBean�Ƃ��ēo�^�͂��܂����A�t�B�[���h�ɑ΂��ăC���W�F�N�V��������Ƃ��ɂ́A�A�m�e�[�V�����i@Resource�A@Autowired�j�𗘗p���܂��B
++ �����Ƃ��āA**SpringAnnotationInjector**��Spring�̃R���e�i�ɓo�^���Ă����K�v������܂��B

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
    
    <!-- Autowired��Scan�L����` -->
    <context:annotation-config/>
    
    <!-- SpringAnnotationInjector�̓o�^ -->
    <bean id="springAnnotationInjector" class="proj.green.javafx.modules.spring.SpringAnnotationInjector" />

</beans>
```


```profile
sample.javafx <= �p�b�P�[�W
�� SampleAnchorPane.fxml <= FXML�t�@�C���i�������A<fx:root>�v�f��Controller�̌p����Ɠ����ɂ���K�v������܂��B�j
�� SampleAnchorPane.java <= Controller��Java�N���X�B
�� SampleAnchorPane.properties <= Controller�ɑ΂���v���p�e�B�t�@�C���i�ȗ��\�j�B

```

```java
// Controller�N���X
public class SampleAnchorPane extends AnchorPane {
    
    @Resource
    private Sample1Service sample1Service;
    
    @Autowired
    private Sample2Service sample2Service;
    
    public SampleAnchorPane() {
        // �R���X�g���N�^�ŁA���g��n��
        SpringFxmlTool.loadComponentFromSpring(this, this);
    }

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
       // ������
    }
}
```

```java
// �g���ۂɂ́Anew���邾���ł悢�B
   SampleAnchorPane pane = new SampleAnchorPane();
```

## Prototype�X�R�[�v�̃C���X�^���X�̊Ǘ�
+ Prototype�X�R�[�v�̍��܂łŎ擾�����C���X�^���X�́A**List<PrototypeBean> SpringFxmlTool.getPrototypeBeans()**�Ŏ擾���邱�Ƃ��ł��܂��B
+ �������A��Q�Ƃŏ���ێ����Ă��邽�߁A�C���X�^���X��null�ɂȂ��Ă���ꍇ������܂��B
+ �C���^�t�F�[�X�uDisposableBean�v��A�m�e�[�V�����u@PreDestroy�v���t�^���ꂽ���\�b�h���Ă�ŁA�I�������鎞�Ȃǂɗ��p���܂��B
++ �I��������Ƃ��́A**SpringFxmlTool.destroyAllProrotypeObject()**���Ăт܂��B

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
        
        // Spring��ApplicationContext��ݒ肷��
        SpringFxmlTool.init(applicationContext);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //TODO:
        
    }
    
    public void stop() {
        
        if(applicationContext != null && applicationContext instanceof AbstractApplicationContext) {
            // Spring��Prototpype�X�R�[�v�̏I��
            SpringFxmlTool.destroyAllProrotypeObject();
            
            // Spring�̃R���e�i�̏I��
            ((AbstractApplicationContext) applicationContext).close();
            System.out.println("close");
        }
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```


# ���b�Z�[�W�{�b�N�X�iproj.green.javafx.modules.messagebox�j
-------------------------------
+ �r���_�[�`���ŁA���b�Z�[�W�{�b�N�X��g�ݗ��Ă܂��B
+ **MessageBoxBulder**�N���X�𗘗p���܂��B
++ ���b�Z�[�W�{�b�N�X��\������ɂ́Ashow()���\�b�h�𗘗p���܂��B
++ �ǂ̃{�^���������ꂽ���ǂ����Ashow()���\�b�h�̖߂�l�Ƃ��ė񋓌^**MessageBoxEvent**���Ԃ���܂��B

```java
MessageBoxEvent ans = MessageBoxBulder.create()
     .icon(MessageBoxIcon.QUESTION)  //���b�Z�[�W�{�b�N�X�̃A�C�R���̎w��
     .title("daialog title")         // �^�C�g���̎w��
     .message("message").appendMessage("append") // ���b�Z�[�W�̎w��B���s�����f����܂��B
     .buttonYes().buttonNo()         // �{�^���̎w��B
     .playBeep()                     // Beep����炷�ꍇ
     .show(root);                    // �_�C�A���O�̕\���B�e�̃m�[�h���w�肵�Ȃ��ꍇ��null������Ȃ���show()���\�b�h���Ăт܂��B
```

# busy indicator�iproj.green.javafx.modules.indicator�j
--------------------------------
+ �i�����𔺂�Ȃ�Busy Inidicator��\�����܂��B
++ JavaFX�ɂ́A**ProgressIndicator**��progress���w�肵�Ȃ���΁Abusy indicator���\���ł��܂����A�f�U�C�����P��������܂���B
+ JavaScript�̃��C�u���C�ł���[busy.js](http://www.netzgesta.de/busy/)�̂悤�ɁA�傫���A�F�A�f�U�C�����J�X�^�}�C�Y�ł��܂��B

## indicator��P�̂ŕ\������
+ **BusyIndicatorBuilder**���g�p���āA**BusyInidocator**�̃C���X�^���X��g�ݗ��Ă܂��B

```java
BusyIndicator indicator1 = BusyIndicatorBuilder.create()
                .typeEllipse()
                .color(Color.BLUE)
                .radius(10)
                .weight(10)
                .size(30)
                .speed(90)
                .build();
                
// indicator�̕\��
indicator1.playFromStart();

// indicator�̔�\��
inidicator1.stop();

```


## �p�l���̏��inidicator��\������
+ �p�l���̏��Inidicator��\�����邱�ƂŁA�\�����͂��̌��ɂ���Controller�ȂǂɐG��Ȃ��悤�ɂ��܂��B
+ **BusyIndicatorPaneBuilder**���g�p���āA**BusyIndicatorPane**�̃C���X�^���X��g�ݗ��Ă܂��B
+ inidicator�p�̃p�l���́AStackPane�ɐݒ肵�܂��B
+ ���ӓ_�Ƃ��āABusy inidicator�́AJavaFX�̃X���b�h�œ��삳���邽�߁A�\�����Ƀo�b�`�����Ȃǂ�

```java
public class BusyIndicatorTestPaneController {
    
    @FXML
    private StackPane myNode;
    
    private BusyIndicatorPane indicatorPane;
    
    @FXML
    private void initialize() {
        
        // �C���W�P�[�^�p��pane��ǉ�
        indicatorPane = new BusyIndicatorPane();
        indicatorPane.setBusyIndicator(BusyIndicatorBuilder.create()
                .color(Color.BLUE)
                .build());
        
    }
    
    @FXML
    private void handleShowIndicator(final ActionEvent event) {
        
        // �C���W�P�[�^�̕\��
        indicatorPane.playFromStart();
        myNode.getChildren().add(indicatorPane);
        
        indicatorPane.setMessage("���b�Z�[�W");
        
        Task<String> task = new Task<String>() {
            
            @Override
            protected String call() throws Exception {
                //TODO: �d������
                System.out.println("end");
                
                indicatorPane.stop();
                return "end";
            }
        };
        
        // �ʃX���b�h�Ŏ��s����
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED,
                new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        // �C���W�P�[�^�̏I��
                        stopInidcator();
                        
                        executor.shutdown();
                    }
                });
        
        executor.submit(task);
       
    }
    
    /**
     * �\������inidicator���\���ɂ���
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



