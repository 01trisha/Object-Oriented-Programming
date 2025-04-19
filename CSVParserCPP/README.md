Программа принимает в качестве аргумента имя текстового файла, и выводить CSV файл ([http://ru.wikipedia.org/wiki/CSV](http://ru.wikipedia.org/wiki/CSV)) с колонками:
1. Слово.
2. Частота.
3. Частота (в %).

CSV файл упорядочен по убыванию частоты, то есть самые частые слова
должны идти в начале. Разделители -  все символы кроме букв и цифр.

Аргументы командной строки программы:

> word_count.exe input.txt output.csv

Требования к программе:
	1. Для работы со строками используется класс стандартной библиотеки std::string (см. [http://www.cplusplus.com/reference/string/string/](http://www.cplusplus.com/reference/string/string/)) 
	2. Работа с файлами осуществляется с помощью классов стандартной библиотеки из модуля fstream ([http://www.cplusplus.com/reference/fstream/](http://www.cplusplus.com/reference/fstream/))
	3. Строки из файла зачитываются с помощью метода std::getline (см. [http://www.cplusplus.com/reference/string/string/getline/](http://www.cplusplus.com/reference/string/string/getline/))
	4. Сохранение зачитанных строк в контейнере стандартной библиотеки std::list ([http://www.cplusplus.com/reference/list/list/](http://www.cplusplus.com/reference/list/list/)), std::map ([http://www.cplusplus.com/reference/map/map/](http://www.cplusplus.com/reference/map/map/))