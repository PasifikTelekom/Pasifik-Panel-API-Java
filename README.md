# Pasifik-Panel-API-Java
This library package provides a variety systems, the simplest way to integrate Pasifik services with your system.

## Requirements

You should download [java-json.jar](http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm) to your library.

## Usage 

after adding Pasifik.jar to your project 

```java
import pasifik.*;
...
String username = "YOUR_USERNAME";
String password = "YOUR_PASSWORD";
String header = "YOUR_COMPANY";
String lang = "tr"; // 'tr': Turkish response, 'en': English response, 'ar': Arabic response.
Boolean DEBUG = true;

PasifikAPI obj = new PasifikAPI(username, password, lang, DEBUG);
```
## Test Case


