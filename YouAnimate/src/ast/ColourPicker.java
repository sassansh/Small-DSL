package ast;

import java.util.HashMap;
import java.util.Map;

public class ColourPicker {
    private static final Map<String, String> colourMap = new HashMap<String, String>() {{
        put("red", "red");
        put("green", "#31d631");
        put("yellow", "#ffff24");
        put("blue", "#4691f2");
        put("orange", "#f5a018");
        put("purple", "#a13bcc");
        put("white", "white");
        put("black", "black");
    }};

    static String getColour(String colour) {
        return colourMap.get(colour);
    }
}
