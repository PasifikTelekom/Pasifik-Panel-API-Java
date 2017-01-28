
package pasifik;
import java.util.*;

/**
 *
 * @author PC
 */
    public class TestCase {
    private final String header;
    private final PasifikAPI obj;
    public TestCase(){
        String username = "YOUR_USERNAME";
        String password = "YOUR_PASSWORD";
        this.header = "YOUR_COMPANY";
        String lang = "tr";// 'tr': Turkish response, 'en': English response, 'ar': Arabic response.
        Boolean DEBUG = true;
        this.obj = new PasifikAPI(username, password, lang, DEBUG);
    }
    public void send_one_message_to_many_receipients(){
        String from=this.header;
        String to="905999999998,905999999999";
        String text="SMS Test";
        String result=this.obj.Submit(from, to, text);
    }
    public void send_one_message_to_many_receipients_schedule_delivery()
    {
        String from=this.header;
        String to="905999999998,905999999999";
        String text="SMS Test";
        String scheduled_delivery_time= "2016-09-28T09:30:00Z";;// "%Y-%m-%dT%H:%M:%SZ" format e.g "2016-07-23T21:54:02Z" in UTC Timezone.
        String result =this.obj.Submit(from, to, text, false, "Default", scheduled_delivery_time,0 );
    }
    public void send_one_message_to_many_receipients_schedule_delivery_with_validity_period()
    {
        String from=this.header;
        String to="905999999998,905999999999";
        String text="SMS Test";
        String scheduled_delivery_time= "2016-09-28T09:30:00Z";// "%Y-%m-%dT%H:%M:%SZ" format e.g "2016-07-23T21:54:02Z" in UTC Timezone.
        int period=1440; // minutes number e.g 1440 minutes for 24 hours
        String result =this.obj.Submit(from, to, text, false, "Default", scheduled_delivery_time, period);
    }  
    public void send_one_message_to_many_receipients_turkish_language()
    {
        String from=this.header;
        String to="905999999998,905999999999";
        String text="Artık Ulusal Dil Tanımlayıcısı ile Türkçe karakterli smslerinizi rahatlıkla iletebilirsiniz.";
        String alphabet= "TurkishSingleShift";
        String result=this.obj.Submit(from, to, text, false, alphabet,"", 0);
    }
    public void send_one_message_to_many_receipients_flash_sms()
    {
        String from=this.header;
        String to="905999999998,905999999999";
        String text="My first Flash SMS, It will be temporary on your phone.";
        String alphabet="DefaultMclass0";
        String result=this.obj.Submit(from, to, text,false, alphabet, "", 0);
    }
    public void send_one_message_to_many_receipients_unicode()
    {
        String from = this.header;
        String to = "905999999998,905999999999";

        String text = "メッセージありがとうございます";
        String alphabet = "UCS2";
        String result=this.obj.Submit(from, to, text,false, alphabet, "", 0);
    }
    public void send_one_message_to_many_receipients_outside_turkey()
    {
        String from = this.header;
        String to = "+435999999998,+435999999999";// '+' required e.g '+43' for Germany mobile prefix number
        String text = "SMS Test";
        Boolean universal=true;// true: it means send sms outside Turkey
        String result =this.obj.Submit(from, to, text, universal,"", "", 0);    
    }
    public void send_many_message_to_many_receipients()
    {
        String from =this.header;
        ArrayList<HashMap<String,String>> envelopes=new ArrayList<>();
        HashMap<String,String> env1= new HashMap<>();
        env1.put("to", "905999999998");
        env1.put("text", "test 1");
        envelopes.add(env1);
        HashMap<String,String> env2= new HashMap <>();
        env2.put("to", "905999999998");
        env2.put("text", "test 1");
        envelopes.add(env2);
        String result = this.obj.SubmitMulti(from, envelopes, null,"", "", 0);    
    }
    public void query_multi_general_report()
    {
        String start_date="01.03.2016";// formated as turkish date time format "%d.%m.%Y"
        String end_date="01.03.1016"; // formated as turkish date time format "%d.%m.%Y"
        String result=this.obj.QueryMulti(start_date, end_date);
    }
    public void query_multi_general_report_with_id()
    {
        String sms_id="123456";
        String result =this.obj.QueryMultiId(sms_id);
    }
    public void query_detailed_report_with_id()
    {
        String sms_id="123456";
        String result=this.obj.Query(sms_id);
    }
    public void get_account_settings()
    {
        String result=this.obj.GetSettings();
    }
    public void get_authority()
    {
        boolean encode=false;
        String result = this.obj.Authorization(encode);
    }
    public void get_cdr_report()
    {
        int i_account=123456;
        String result =this.obj.CallHistory(i_account, "", "", "", "", 0, 0, "");        
    }
    public void get_cdr_report_range_datetime()
    {
        int i_account=123456;
        String start_date = "2016-08-31T10:12:45Z";
        String end_date = "2016-09-01T10:12:45Z";
        String cli = "";	  
        String cld = "";
        int offset = 0;
        int limit = 100; 
        String result=this.obj.CallHistory(i_account, start_date, end_date, cli, cld, offset, limit, "");
    }
    public void get_cdr_report_with_type()
    {
        int i_account = 123456;
        String start_date = "2016-08-31T10:12:45Z";
        String end_date = "2016-09-01T10:12:45Z";
        String cli = "";
        String cld = "";
        int offset = 0;
        int limit = 100;
        String [] type_flag={"non_zero_and_errors", "non_zero", "all", "complete", "incomplete", "errors" };
        String type=type_flag[0];
        String result=this.obj.CallHistory(i_account, start_date, end_date, cli, cld, offset, limit, type);   
    }
    public void get_active_calls()
    {
        int[] i_account_list={ 123,456};
        String result=this.obj.CallActive(i_account_list);
    }
    public void get_disconnect_active_call()
    {
        int id=123456;
        String result =this.obj.CallActiveDisconnect(id);
    }
 }

