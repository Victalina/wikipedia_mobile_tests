
# Проект по автоматизации тестирования для мобильного приложения [Wikipedia](https://www.wikipedia.org/)

<p align="center">  
<a href="https://www.wikipedia.org/"><img title="Wikipedia" src="images/logo/wikipedia.png" width="950"/></a> 
</p>

> Википедия — многоязычная общедоступная интернет-энциклопедия со свободным контентом, поддержку и написание которой осуществляют добровольцы


## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Тестовое покрытие</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#allure-testOps">Интеграция с Allure TestOps</a>

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>

* <a href="#video">Примеры видео выполнения тестов</a>
____
<a id="tools"></a>
## **Технологии и инструменты:**

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/intellij-idea.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/logo/java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://www.android.com/"><img src="images/logo/android.svg" width="50" height="50"  alt="Android"/></a>  
<a href="https://developer.android.com/studio"><img src="images/logo/androidstudio.svg" width="50" height="50"  alt="Android Studio"/></a>  
<a href="https://github.com/"><img src="images/logo/github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/logo/selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://www.browserstack.com/"><img src="images/logo/browserstack.svg" width="50" height="50"  alt="Browserstack"/></a> 
<a href="https://appium.io/"><img src="images/logo/appium.png" width="50" height="50"  alt="Appium"/></a> 
<a href="https://rest-assured.io/"><img src="images/logo/rest-assured.png" width="50" height="50"  alt="REST-assured"/></a> 
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/allure.svg" width="50" height="50"  alt="Allure"/></a>  
<a href="https://qameta.io/"><img src="images/logo/allure-testOps.svg" width="50" height="50"  alt="TestOps"/></a> 
<a href="https://www.jenkins.io/"><img src="images/logo/jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
<a href="https://telegram.org/"><img src="images/logo/telegram.svg" width="50" height="50"  alt="Telegram"/></a>
</p>

Автотесты для мобильного приложения на `Android` разработаны на языке программирования `Java` с использованием фреймворков `Selenide` и `Appium`; `UIAutomator2` используется как Android драйвер.

В качестве фреймворка для запуска тестов используется `Junit5`, в качестве сборщика проекта - `Gradle`, конфигурация настроена с помощью библиотеки `Owner`.

Произведена настройка CI системы `Jenkins`, при запуске автотестов из которой выполнение тестов осуществляется в облачной ферме мобильных девайсов `Browserstack`. Для взаимодействия с `Browserstack API` используется библиотека `REST-assured`. По результатам каждого запуска автотестов создаётся `Allure` отчёт для визуализации результатов прогона.
При локальном запуске есть возможность использовать эмулятор `Android` девайса.

Реализована интеграция с `Allure TestOps` – системой тест-менеджмента для управления процессом тестирования.

После выполнения автотестов `Telegram` бот присылает сообщение с информацией о результатах запуска.

____
<a id="cases"></a>
## **Тестовое покрытие:**
____
#### Стартовые экраны приложения

- [x] Проверка стартовых экранов приложения
- [x] Пропуск стартовых экранов приложения по кнопке 'Skip'
- [x] Пропуск стартовых экранов приложения по системной кнопке 'Назад'


#### Поиск и открытие статей

- [x] Проверка результатов поиска статей по ключевому слову
- [x] Проверка открытия статьи через поиск

____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/jenkins.svg" width="25"/> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/C34-Vicktalina-unit23-3/)
____
<p align="center">  
<img src="images/screenshot/jenkins_build.png" alt="Jenkins" width="950"/>
</p>

### **Параметры сборки в Jenkins:**

- `TASK` (набор автотестов с соответствующими тегами: `all_android_tests` - все тесты, `open_article_tests` - тесты на открытие статьи, `get_started_screens_tests` - тесты на стартовые экраны, `search_article_tests` - тесты на поиск статьи)
- `LOGIN` (логин аккаунта Browserstack)
- `KEY` (ключ аккаунта Browserstack)
- `JOB_NAME` (наименование задачи в Jenkins, заполняется автоматически)
- `TASK` (наименование таски в Jenkins, заполняется автоматически)

<a id="console"></a>
## Команды для запуска из терминала
___
***Локальный запуск:***
```bash  
gradle clean all_android_tests -DdeviceHost=local
```

***Удалённый запуск через Jenkins:***
```bash  
clean ${TASK} -DdeviceHost=browserstack
"-Dlogin=${LOGIN}"
"-Dkey=${KEY}"
"-Dproject=${JOB_NAME}"
"-Dname=${TASK}"
```
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/allure.svg" width="25"/> Allure [отчет](https://jenkins.autotests.cloud/job/C34-Vicktalina-unit23-3/6/allure/)
___

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screenshot/allure_report.png" width="850"/>  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="images/screenshot/allure_report_test.png" width="850"/>  
</p>

### *Графики*

<p align="center">  
<img title="Allure Graphics" src="images/screenshot/allure_report_graphs.png" width="850"/>
</p>

____

<a id="allure-testOps"></a>
## <img alt="TestOps" height="25" src="images/logo/allure-testOps.svg" width="25"/> Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/4820/dashboards)

### Основная страница Allure TestOps

<p align="center">  
<img title="Allure TestOps main" src="images/screenshot/allure_testops.png" width="850"/>
</p>

### Авто тест-кейсы

<p align="center">  
<img title="Allure TestOps test" src="images/screenshot/allure_testops_test.png" width="850"/>
</p>

___

<a id="telegram"></a>
## <img alt="Allure" height="25" src="images/logo/telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screenshot/telegram_alert.png" width="550"/>  
</p>

____
<a id="video"></a>
## <img alt="Browserstack" height="25" src="images/logo/browserstack.svg" width="25"/> Примеры видео выполнения тестов (Browserstack)
____
<p align="center">
<img title="Selenoid Video" src="images/video/video.gif" width="350"  alt="video"/>  
</p>
<p align="center">
<img title="Selenoid Video" src="images/video/video1.gif" width="350"  alt="video"/> 
</p>

