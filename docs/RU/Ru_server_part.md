


**Содержание:**
- [Описание серверной части приложения](#описание-серверной-части-приложения)
  - [Схема базы данных](#схема-базы-данных)
  - [Описание таблицы breeds](#описание-таблицы-breeds)
  - [Описание таблицы genders](#описание-таблицы-genders)
  - [Описание таблицы cats](#описание-таблицы-cats)
  - [Описание таблицы users](#описание-таблицы-users)
  - [Описание таблицы user\_cats](#описание-таблицы-user_cats)
  - [Описание таблицы chats](#описание-таблицы-chats)
  - [Описание таблицы chat\_members](#описание-таблицы-chat_members)
  - [Описание таблицы messages](#описание-таблицы-messages)


# Описание серверной части приложения

Примечание:     
***PK*** - первичнвый ключ, это колонка, идентифицирующая таблицу.   
**FK** - внешний ключ, это столбец, который применяется для установления связи между 2 таблицами.   

## Схема базы данных
![bd](/Image_database.png)


## Описание таблицы breeds

Данная таблица хранит информацию о породе кота (кот/кошка) и имеет следующие поля:    

| Поле | Тип данных |
|--------|:----:|
| ***id*** | int8 |
| breed  | text |

Данная таблица хранит информацию о породах котов.

## Описание таблицы genders
Данная таблица хранит информацию о гендере кота (кот/кошка) и имеет следующие поля:     

| Поле | Тип данных |
|--------|:----:|
| ***id*** | int8 |
| gender  | text |


## Описание таблицы cats
Данная таблица хранит информацию о котах (имя, порода, возраст, вес, описание, картинку, пол кота, код породы) и имеет следующие поля:    


| Поле       | Тип данных |
|------------------|:----:|
| ***id***         | uuid |
| name_cat         | text |
| **gender_id**    | text |
| age              | text |
| **breed_id**     | text |
| wheight          | date |
| description_cats | text |
| image_url        | text |

Поле gender_id ссылается на таблицу genders на поле id.
Поле breed_id ссылается на таблицу breeds на поле id.

## Описание таблицы users
Данная таблица хранит информацию о хозяине кота (ФИО, телефон, дату рождения, ссылку на картинку) и имеет следующие поля:     

| Поле | Тип данных |
|-------|:----:|
| **id**       | uuid |
| surname    | text |
| name       | text |
| patronymic | text |
| telephone  | text |
| birthday   | date |
| image_url  | text |


Поле id ссылается на встроенную схему авторизации в supabase (auth), на таблицу users на поле id. Благодаря этому соединению мы можем применять интегрированный механизм аутентификации от Supabase и одновременно сохранять дополнительные сведения о залогиненном пользователе.

## Описание таблицы user_cats
Данная таблица позволяет связать 2 таблицы связью многое-ко-многим. Она имеет следующие поля:     

| Поле | Тип данных |
|------------|:----:|
| ***id***     | int8 |
| **id_user**  | uuid | 
| **id_cats**  | int8 |


Поле id_user ссылается на таблицу users на поле id.
Поле id_cats ссылается на таблицу cats на поле id.
Эти отношения представляют собой связь «один ко многим» между таблицами пользователей и котов. У одного владельца может быть несколько питомцев, но один питомец не может принадлежать нескольким владельцам.

## Описание таблицы chats
Данная таблица хранит информацию о чатах (номере и дате создания) имеет следующие поля:       

| Поле| Тип данных |
|---------------|:----:|
| ***id***        | int8 |
| creation_date | date |

## Описание таблицы chat_members
Данная таблица имеет следующие поля:   

| Поле | Тип данных |
|------------|:----:|
| ***id***     | int8 |
| **chat_id**  | int8 | 
| **user_id**  | uuid |

Поле user_id ссылается на таблицу users на поле id.
Поле chat_id ссылается на таблицу chats на поле id.
Эти всвязи создают отношения «многие ко многим» между таблицами пользователей и чатов. В одном чате может быть несколько участников, как минимум двое, а один и тот же человек может быть участником нескольких чатов.

## Описание таблицы messages
Данная таблица хранит информацию о сообщениях (сообщение, дату отправки, кто отправил, в каком чате) и имеет следующие поля:   


| Поле         | Тип данных |
|---------------|:---------:|
| ***id***      | uuid      |
| text          | text      |
| send_date     | timestamp |
| **sender_id** | uuid      |
| **chat_id**   | int8      |

Поле sender_id ссылается на таблицу users на поле id.
Поле chat_id ссылается на таблицу chats на поле id.
