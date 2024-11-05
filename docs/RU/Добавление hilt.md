
# Добавление библиотеки Hilt
Необходимо, для разделения кода на несколько папочек. К примеру, как сделано у меня в проекте. Для каждого экрана своя папочка и в каждой папочке есть 2 файлика:
первый содержит верстку экрана (view) и второй содержит промежуточный элемент между версткой и серверной частью приложения, а именно манипуляции с данными (viewModel).

## Добавление зависимостей

Заходим в файл build.gradle.kts (Project) и вставляем следующий код в скобочки `plugins {...}` :

```kotlin
  id ("com.google.dagger.hilt.android") version "2.48" apply false
  id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
```

Далле заходим в файл build.gradle.kts (Module) и вставляем следующий код в скобочки `plugins {...}` :

```kotlin
  id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
```
Листаем ниже и вставляем следующий код в скобочки `plugins {...}` :

```kotlin
  // hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
```

Далее, создаем котлин класс MyApplication, который наследуем от  Application() и используем аннотацию `@HiltAndroidApp`.
Таким образом, получаем следующий класс:

```kotlin
  @HiltAndroidApp
  class MyApplication:Application() {
  }
```
В данный класс ничего не пишем. Он необходим только для того, что бы подключить необходимые зависимости.
Далее, необходимо подключить данный класс в файле AndroidManifest.xml. Это мы делаем следующим образом:

```kotlin
  android:name="com.example.MyApplication"
```
Следующий шаг, это в файле MainActivity мы используем аннотацию `@AndroidEntryPoint`.
```kotlin
  @AndroidEntryPoint
  class MainActivity : ComponentActivity() {...}
```
