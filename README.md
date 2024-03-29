Задание: Реализация логирования с помощью Spring AOP

Для демонстрации работы в приложении были созданы сущности User и Order
с соответствующими полями (id, имя, email для пользователя; id, описание, статус для заказа). 
Созданы репозитории и сервисы реализующие методы для создания, чтения, обновления и удаления пользователей и их заказов.

Для получения точек среза создан класс LoggingAspect, где прописаны возможные точки среза для методов и сервисов.

В классе UserAdviceAspect реализованы различные типы советов AOP (Before, AfterReturning).
Здесь применены точки среза из класса LoggingAspect в сочетании com.example.openschooltask3.aspect.LoggingAspect.isUserServiceLayer() 
&& com.example.openschooltask3.aspect.LoggingAspect.isSomeMethod(), которые помогают определить обращения к любым методам UserService.

Аналогично написан класс OrderAdviceAspect. 
Эти классы реализуют логирование до обращения к методам (Before) и результаты работы методов (AfterReturning).

В классе ControllerAdviceAspect реализованы советы типа AfterThrowing, для контроллеров с комбинацией точек среза 
com.example.openschooltask3.aspect.LoggingAspect.isControllerLayer() && com.example.openschooltask3.aspect.LoggingAspect.isSomeMethod(),
что позволяет реализовать логирование обращений к контроллерам и их методам. В частности реализовано логирование выбрасываемых исключений в методах.

Логирование осуществляется с помощью библиотеки Log4j2.
Настройки описаны в файле log4j2.xml. Здесь мы указываем, что к корневому логеру подключены 
два Appender-а: STDOUT и rollingFile. Первый пишет в консоль, а второй пишет в файл “logs/log4j2.log”, 
при этом каждый день создаёт новый файл (TimeBasedTriggeringPolicy interval = "1"), а также ограничивает размер файла 250 мегабайтами.
Уровень логирования "trace".

Проект можно запустить:
1) Запустив файл docker-compose.yml.
Протестировать можно, запустив документацию swagger-openapi.
    Документация openapi:    
    http://localhost:8080/swagger-ui/index.html#/
При запуске приложения в БД будут подгружены тестовые данные из файла TestConfig.
2) Запустив приложение из файла OpenSchoolTask3Application.
   Протестировать можно, запустив документацию swagger-openapi.
   Документация openapi:    
   http://localhost:8080/swagger-ui/index.html#/
   При запуске приложения в БД будут подгружены тестовые данные из файла TestConfig.
3) Можно протестировать работу приложения запустив тесты из файла OpenSchoolTask3ApplicationTests.
    Интеграционные тесты проверят работу методов сервисов и контроллеров, а так же запишут логи работы в файл log4j2.log.



