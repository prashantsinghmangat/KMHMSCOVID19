package covid19kmhms.KMHMSCOVID19.Login;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.io.UnsupportedEncodingException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MHPFlow {

    private OkHttpClient client = new OkHttpClient();

    public String login(String userName, String password){

        final String RELATIVE_PATH = "applogin/";
        if(userName.isEmpty() || password.isEmpty()){
            System.out.println("userName isEmpty() or password isEmpty()");
            return null;
        }
        String returnString = null;
        String salts[] = getSalt(userName);
        /*String hash = BCrypt.hashpw(password, salts[0]);
        String hash1 = BCrypt.hashpw(hash, salts[1]);
        System.out.println("hash---------------------------------> " + hash);
        System.out.println("hash 1----------------------------------> " + hash1);
*/

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObjectResult = null;

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username", userName);
            jsonObject.put("password", password);
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_USER+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            if(response.code() != 200){
                return null;
            }
            return rb.string();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }// login ends here


    public String[] getSalt(String userName){

        String returnString[] = new String[2];
        final String RELATIVE_PATH = "genSalt/";

        String url = GlobalVariables.GLOBAL_PATH_USER+RELATIVE_PATH+userName;
        Request request = new okhttp3.Request.Builder()
                .url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            JSONObject obj = new JSONObject(response.body().string());
            String fixed = obj.getString("fixedSalt");
            String dynamic = obj.getString("dynamicSalt");
            //System.out.println(fixed +"         "+ dynamic);
            returnString[0] = fixed;
            returnString[1] = dynamic;
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnString;
    } //getSalt ends


    public static String decoded(String JWTEncoded) throws Exception {
        try {
            //System.out.println("before spliting " + JWTEncoded);
            String[] split = JWTEncoded.split("\\.");
            //System.out.println("--------------------------------");
            /*for(String string : split){
                System.out.println("012"+string);
            }*/

            return(getJson(split[1]));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }//decode ends here


    public static String getJson(String strEncoded) throws UnsupportedEncodingException{

        //byte[] decodedBytes = Base64.decodeBase64(strEncoded);
        byte[] decodedBytes = Base64.decode(strEncoded,Base64.DEFAULT);
        if(decodedBytes == null) {
            System.out.println("Base64 unable to decode");
            return "Base64 unable to decode";
        }
        return new String(decodedBytes, "UTF-8");
        //return "return String";

    }//getJson ends here



}// MHPFlow class ends here
