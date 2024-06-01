public class IDbuilder {

    public String makeID(Territory territory) {
        StringBuilder builder = new StringBuilder();
        builder.append(territory.getBelongsToCountry().getName(), 0, 3);
        builder.append(territory.getBelongsToRegion().getName(), 0, 3);
        builder.append(territory.getBelongsToMunicipal().getName(), 0, 3);
        return builder.toString().toUpperCase();
    }

}
