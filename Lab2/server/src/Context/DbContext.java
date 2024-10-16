package Context;

import java.util.ArrayList;

public class DbContext {
    private ArrayList<String> KeysTable = new ArrayList<>();

    public DbContext(){
        ArrayList<String> defaultKeys= new ArrayList<String>();
        defaultKeys.add("0000000001");

        setKeysTable(defaultKeys);
    }

    public ArrayList<String> getKeysTable() {
        return KeysTable;
    }

    public void addToKeysTable(String input) {
        KeysTable.add(input);
    }

    private void setKeysTable(ArrayList<String> keysTable) {
        KeysTable = keysTable;
    }
}