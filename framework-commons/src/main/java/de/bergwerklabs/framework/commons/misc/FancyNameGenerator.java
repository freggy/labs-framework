package de.bergwerklabs.framework.commons.misc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class FancyNameGenerator {

    private static boolean initialized = false;
    private static final Gson GSON = new Gson();

    private static final List<String> ADJECTIVES = new ArrayList<>();
    private static final Map<String, List<String>> NOUNS = new LinkedHashMap<>();

    private static void tryInitialize() {
        if (initialized) return;
        initialized = true;

        ADJECTIVES.clear();
        NOUNS.clear();

        InputStream nounStream = FancyNameGenerator.class.getClassLoader().getResourceAsStream("fancy-name-generator/nouns.json");
        JsonObject nounData = ((JsonElement) GSON.fromJson(new JsonReader(new InputStreamReader(nounStream)), JsonElement.class)).getAsJsonObject();

        nounData.entrySet().forEach(entry -> {
            List<String> nouns = new ArrayList<>();
            for (JsonElement element : entry.getValue().getAsJsonArray()) {
                nouns.add(element.getAsString());
            }
            NOUNS.put(entry.getKey(), nouns);
        });

        InputStream adjectiveStream = FancyNameGenerator.class.getClassLoader().getResourceAsStream("fancy-name-generator/adjectives.json");
        JsonArray adjectiveData = ((JsonElement) GSON.fromJson(new JsonReader(new InputStreamReader(adjectiveStream)), JsonElement.class)).getAsJsonArray();

        for (JsonElement element : adjectiveData) {
            ADJECTIVES.add(element.getAsString());
        }
    }

    public static String generate(long seed) {
        tryInitialize();

        Random random = new Random(Math.abs(seed));

        String[] articles = NOUNS.keySet().toArray(new String[0]);
        String article = articles[random.nextInt(articles.length)];

        String adjective = ADJECTIVES.get(random.nextInt(ADJECTIVES.size()));

        List<String> nouns = NOUNS.get(article);
        String noun = nouns.get(random.nextInt(nouns.size()));

        return (article + "_" + adjective + "_" + noun).toLowerCase();
    }

    public static String generate(String value) {
        return generate(value.hashCode());
    }
}
