package Logic;

import Context.DbContext;
import Logic.Enums.Contents;

public class Logic {

    private final String request;
    private final DbContext dbContext;
    private final String PasswordData = "admin";
    private final String KEY_OUT_PREFIX = "key_out";

    public Logic(String request, DbContext dbContext){
        this.request = request;
        this.dbContext = dbContext;
    }

    public String CheckIfKeyExists(){
        String key = extractKeyValue(request);

        if(key.isEmpty()){
            return String.format("{%s-%s : %s}", KEY_OUT_PREFIX, key, Contents.error);
        }
        if(dbContext.getKeysTable().contains(key)) {
            return String.format("{%s-%s : %s}", KEY_OUT_PREFIX, key, Contents.yes);
        }
        return String.format("{%s-%s : %s}", KEY_OUT_PREFIX, key, Contents.not);
    }

    public String SetNewKey(){
        String password = extractPasswordValue(request);
        String key = extractKeyValue(request);

        if(password.isEmpty()){
            return String.format("{%s-%s : %s}", KEY_OUT_PREFIX, key, Contents.error);
        }
        if(PasswordData.equals(password)) {

            dbContext.addToKeysTable(key);
            return String.format("{%s-%s : %s}", KEY_OUT_PREFIX, key, Contents.yes);
        }
        return String.format("{%s-%s : %s}", KEY_OUT_PREFIX, key, Contents.not);
    }

    private static String extractKeyValue(String input) {
        input = input.trim();

        if (input.startsWith("key_in-get")) {
            return input.substring(input.indexOf("{") + 1, input.indexOf("}")).trim();
        } else if (input.startsWith("key_in-set")) {
            return input.substring(input.indexOf(":") + 1, input.indexOf("}")).trim();
        }
        return "";
    }

    private static String extractPasswordValue(String input) {
        input = input.trim();

        if (input.startsWith("key_in-set")) {
            return input.substring(input.indexOf("{") + 1, input.indexOf(":")).trim();
        }
        return "";
    }
}
