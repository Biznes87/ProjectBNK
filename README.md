# ProjectBNK
Программа работы со Справочником БИК 

Здравствуйте.

При создании базы данных я использовал MySQL.
Для создания базы необходимо выполнить команду:<br/>
mysql --host=localhost --user=user --password=password < PathTo CreateShema.sql,
где user - имя пользователя администратора СУБД, <hr>
    password - ваш пароль,<hr>
    PathTo - путь до места куда вы положите CreateShema.sql, в котором команды для создания БД.
    
После выполнения команды создастся схема bnkbase с таблицами:
bnkseek, tb_pzn, tb_rgn, tb_uer, tb_tnp, tb_real.
В bnkseek загружаются данные из BNKSEEK (указанные в ТЗ поля). Остальные таблицы заполняются также из соответсвующих
DBF файлов (tb_rgn из REG.DBF).

Когда база создана, необходимо отредактировать файл /src/resorces/Settings.txt
В нем параметры подключения к базе указаны как КЛЮЧ=Значение. Ключи редактировать не нужно)

Для работы понадобятся javadbf 0.8.0, mysql-connector-java 5.1.44. Соответствующие зависимости указаны в POM.xml
На всякий случай в target лежит собранный мной jar. Но в нем файл Settings.txt c моими настройками,поэтому,для запуска,нужно указать свои настройки.

После этого можно запускать программу.

Откроется главное окно в котором будет пустая таблица. Чтобы загрузить данные нужно нажать на кнопку Загрузить, появится 
окно выбора директории! где лежат DBF файлы. Когда дериктория выбрана, нажимае ОК и ждем. По завершении через 8-10 секунд данные будут загружены в БД и отображены в таблице.

Кнопки Добавить и Изменить используют одну и туже форму,но при редактировании нельзя изменять БИК. Чтобы создать строку с новым БИК нужно выбирать Добавить. При добавлении текстовые поля не могут быть пустыми. По умолчанию поля с датами заполняются текущей датой.

При нажатии Фильтр в этом же окне появятся поля для отбора согласно заданию. Чтобы отобразились строки соответствующие критериям нужно нажать на ОК фильтра. Чтобы закрыть фильтр и отобразить всю таблицу - нажать CLOSE
