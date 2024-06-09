package model.territory;

import java.util.HashSet;

import model.map.Map;

public class SubunitsViewer {
    Map map;
    HashSet<String> subunits;

    public SubunitsViewer() {
        showSubunits(map, subunits);
    }

    public String showSubunits(Map map, HashSet<String> subunits) {
        if (subunits == null) {
            return "no info";
        }
        StringBuilder builder = new StringBuilder();
        for (String subunitID : subunits) {
            builder.append(map.getMap().get(subunitID).getName());
            builder.append(" ");
            builder.append(map.getMap().get(subunitID).getType());
            builder.append(", ");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
