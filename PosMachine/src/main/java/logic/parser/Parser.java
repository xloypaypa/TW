package logic.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlo on 2015/12/8.
 * it's the parser
 */
public abstract class Parser<T> {
    public final static String DATA_ERROR = "data error";

    public final List<T> getList(String[] message) {
        List<T> result = new ArrayList<>();
        for (String now : message) {
            result.add(solveOneData(now));
        }
        return result;
    }

    protected final T getOne(String message) {
        checkArgument(message);
        return solveOneData(message);
    }

    protected abstract void checkArgument(String message);

    protected abstract T solveOneData(String message);

    protected final boolean isKeyOk(String key) {
        if (!key.startsWith("ITEM")) {
            return false;
        } else if (key.length() != 10) {
            return false;
        } else {
            String id = key.substring("ITEM".length());
            for (int i = 0; i < id.length(); i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

}
