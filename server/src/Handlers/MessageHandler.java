package Handlers;

import java.util.Map;

public class MessageHandler {

    private final String message;
    private final Map<String, String> dbContext;

    public MessageHandler(String message, Map<String, String> dbContext){
        this.message = message;
        this.dbContext = dbContext;
    }

    public String HandleKey(){
        String key = extractKeyValue(message);

        if(key.isEmpty()){
            return String.format("key_out-{%s} : error", key);
        }
        if(dbContext.containsKey(key)) {
            return String.format("key_out-{%s} : yes", key);
        }
        return String.format("key_out-{%s} : not", key);
    }



    public static String extractKeyValue(String input) {
        input = input.trim();

        if (input.startsWith("key_in-get")) {
            return input.substring(input.indexOf("{") + 1, input.indexOf("}")).trim();
        } else if (input.startsWith("key_in-set")) {
            return input.substring(input.indexOf(":") + 1, input.indexOf("}")).trim();
        }
        return "";
    }
}
