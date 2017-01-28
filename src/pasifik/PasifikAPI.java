package pasifik;

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.JSONObject;


public class PasifikAPI {
    private final  String authorization;
    private final Boolean DEBUG;
    private final  String base_url;
    public Boolean Is_Passed = true;
    public PasifikAPI(String username, String password, String lang, Boolean DEBUG){
        String auth = username + ":" + password;
        this.authorization = new String(Base64.getEncoder().encode(auth.getBytes()));
        this.base_url = "http://oim.pasifiktelekom.com.tr/"+lang+"/api/";
        this.DEBUG = DEBUG;
    }
    
    public String Submit(String from, String to, String text, Boolean universal, String alphabet, String scheduled_delivery_time, int period)
    {
        HashMap<String, Object> dicto =new HashMap<>(); 
        dicto.put("from", from);
        dicto.put("to", to);
        dicto.put("text", text);
        dicto.put("universal", universal);
        dicto.put("alphabet", alphabet);
        if(scheduled_delivery_time.length() > 0){dicto.put("scheduled_delivery_time", scheduled_delivery_time); }
        if (period > 0){dicto.put("period", period); }
        JSONObject obj = new JSONObject(dicto);
        return this._post("sms/submit/", obj.toString());
    }
    public String Submit(String from, String to, String text){
        HashMap <String ,Object > dicto =new HashMap <>();
        dicto.put("from", from);
        dicto.put("to", to);
        dicto.put("text", text);
        JSONObject obj = new JSONObject(dicto);
        return this._post("sms/submit/", obj.toString());
    }
    
    private String _post(String resource, String json){
        String data = "";String line = "";
        String headers = "";
        String uri = this.base_url + resource;
        HttpURLConnection conn = null;
        OutputStream os = null;
        try {
            conn = (HttpURLConnection)new URL(uri).openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", this.authorization);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            headers = new JSONObject(conn.getRequestProperties()).toString();
            os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((line = br.readLine()) != null){
                data += line;
            }
            os.close();
        }
        catch (IOException e){
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while((line = br.readLine()) != null){
                    data += line;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally{
            conn.disconnect();
        }
        if(this.DEBUG){
            System.out.print(String.format("REQUEST:%s\n", json));
            System.out.print(String.format("HEADER:%s\n", headers));
            System.out.print(String.format("RESPONSE:%s\n", data));
        }
        return data;
    }
    public String SubmitMulti(String from, List<HashMap<String,String>> envelopes, Boolean universal, String alphabet, String scheduled_delivery_time,int period ){
        HashMap<String,Object> dicto =new HashMap<>();
        dicto.put("form", from);
        dicto.put("envelopes",envelopes );
        dicto.put("universal", universal);
        dicto.put("alphabet", alphabet);
        if(scheduled_delivery_time.length() > 0){dicto.put("scheduled_delivery_time", scheduled_delivery_time); }
        if (period > 0){dicto.put("period", period); }
        JSONObject obj = new JSONObject(dicto);
        return this._post("sms/submit/multi/", obj.toString());
    }
    public String QueryMulti (String start_date, String end_date)
    {    
        HashMap <String,Object> dicto =new HashMap<>();
        dicto.put("strat_date", start_date);
        dicto.put("end_date", end_date);
        JSONObject obj=new JSONObject(dicto);
        return this._post("sms/query/multi/", obj.toString());    
    }
    public String QueryMultiId(String sms_id)
    {
        HashMap <String,Object> dicto =new HashMap<>();
        dicto.put("sms_id", sms_id);
        JSONObject obj=new JSONObject(dicto);
        return this._post("sms/query/multi/id/", obj.toString());
    }
    public String Query(String sms_id)
    {
        HashMap<String,Object> dicto =new HashMap<>();
        dicto.put("sms_id", sms_id);
        JSONObject obj=new JSONObject(dicto);
        return this._post("sms/query/", obj.toString());
    }
    public String GetSettings()
    {
        return this._post("user/getsettings/", "{}");
    }
    public String Authorization (Boolean encode)
    { 
        String auth = encode ? this.authorization:new String (Base64.getDecoder().decode(authorization));
        if(this.DEBUG)
        {
            System.out.println(auth);
        }
        return auth;
    }
    public String CallHistory (int i_account, String start_data, String end_date, String cli, String cld, int offset, int limit, String type)
    {
       HashMap <String,Object> dicto =new HashMap<>();
       dicto.put("i_account",  i_account);
       dicto.put("start_date", start_data);
       dicto.put("end_date", end_date);
       dicto.put("cli", cli);
       dicto.put("cld", cld);
       dicto.put("offset", offset);
       dicto.put("limit", limit);
       dicto.put("type", type);
       JSONObject obj=new JSONObject(dicto);
       return this._post("tel/history/", obj.toString());
    }
    public String CallActive(int[] i_account_list)
    {
        HashMap <String,Object> dicto =new HashMap<>();
        dicto.put("i_account_list", i_account_list );
        JSONObject obj=new JSONObject(dicto);
        return this._post("tel/live/", obj.toString());
    }
    public String CallActiveDisconnect(int id)
    {
        HashMap <String,Object> dicto =new HashMap<>();
        dicto.put("id", id);
         JSONObject obj=new JSONObject(dicto);
         return this._post("tel/live/disconnect/", obj.toString());   
    }
}
