# Pasifik Panel API Java
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

Follow `TestCase.java` TestCase class and replace it with requirement parameters, for test just uncomment the following methods inside `Pasifik.java`.



```Java
TestCase test = new TestCase();
//test.send_one_message_to_many_receipients();
//test.send_one_message_to_many_receipients_schedule_delivery();
//test.send_one_message_to_many_receipients_schedule_delivery_with_validity_period();
//test.send_one_message_to_many_receipients_turkish_language();
//test.send_one_message_to_many_receipients_flash_sms();
//test.send_one_message_to_many_receipients_unicode();
//test.send_one_message_to_many_receipients_outside_turkey();
//test.send_many_message_to_many_receipients();
//test.query_multi_general_report();
//test.query_multi_general_report_with_id();
//test.query_detailed_report_with_id();
//test.get_account_settings();
//test.get_authority();
//test.get_cdr_report();
//test.get_cdr_report_range_datetime();
//test.get_cdr_report_with_type();
//test.get_active_calls();
//test.get_disconnect_active_call();
```


To run code **Menu > Run Project** or press **F6**.
You will see the result on **output**.


