package model.territory;

import java.util.HashSet;

import model.map.Map;

public class SubunitsViewer {
    
    public SubunitsViewer() {
    }

    public String showSubunits(Map map, Territory territory) {
        HashSet<String> subunits = territory.getSubunits();
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