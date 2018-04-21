package de.bergwerklabs.framework.commons.misc.instantiable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.bergwerklabs.framework.commons.database.tablebuilder.Database;
import de.bergwerklabs.framework.commons.database.tablebuilder.DatabaseType;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Statement;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.StatementResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FancyNameGenerator {

  private static final Database DATABASE =
      new Database(
          DatabaseType.MySQL,
          "sql.bergwerklabs.de",
          "fancy_name_generator",
          "framework",
          "UkLBdagt7bghgPCGuGhy");
  private static final File NOUNS_FILE = Paths.get("./nouns.json").toFile();
  private static final File ADJECTIVES_FILE = Paths.get("./adjectives.json").toFile();
  private static final List<String> ADJECTIVES = new ArrayList<>();
  private static final List<Noun> NOUNS = new ArrayList<>();

  static {
    Gson gson = new Gson();

    if (NOUNS_FILE.exists()) {
      try {
        JsonArray array =
            gson.fromJson(new BufferedReader(new FileReader(NOUNS_FILE)), JsonArray.class);
        for (JsonElement element : array) {
          JsonObject obj = element.getAsJsonObject();
          NOUNS.add(new Noun(obj.get("noun").getAsString(), obj.get("article").getAsString()));
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else {
      Statement nounStatement =
          DATABASE.prepareStatement("SELECT noun, gender FROM nouns WHERE active = 1");
      StatementResult nounResult = nounStatement.execute();
      nounStatement.close();

      Arrays.stream(nounResult.getRows())
          .map(row -> new Noun(row.getString("noun"), getArticle(row.getString("gender"))))
          .forEach(NOUNS::add);

      try (Writer writer = new FileWriter(NOUNS_FILE)) {
        gson.toJson(NOUNS, writer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (ADJECTIVES_FILE.exists()) {
      try {
        JsonArray array =
            gson.fromJson(new BufferedReader(new FileReader(ADJECTIVES_FILE)), JsonArray.class);
        for (JsonElement element : array) {
          ADJECTIVES.add(element.getAsString());
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else {
      Statement adjectiveStatement =
          DATABASE.prepareStatement("SELECT adjective FROM adjectives WHERE active = 1");
      StatementResult adjectiveResult = adjectiveStatement.execute();
      adjectiveStatement.close();

      Arrays.stream(adjectiveResult.getRows())
          .map(row -> row.getString("adjective"))
          .forEach(ADJECTIVES::add);

      try (Writer writer = new FileWriter(ADJECTIVES_FILE)) {
        gson.toJson(ADJECTIVES, writer);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static String getArticle(String gender) {
    switch (gender) {
      case "MASCULINE":
        return "der";
      case "FEMININE":
        return "die";
      default:
        return "das";
    }
  }

  /**
   * Generates a unique string based on the given seed. <b>The first call may take some time as it
   * loads data from the database synchronously.</b>
   *
   * @param seed the seed to generate the string from
   * @return the generated fancy name
   */
  public String generate(long seed) {
    Random random = new Random(Math.abs(seed));
    Noun noun = NOUNS.get(random.nextInt(NOUNS.size()));
    String adjective = ADJECTIVES.get(random.nextInt(ADJECTIVES.size()));
    return noun.article.toLowerCase()
        + "_"
        + adjective.toLowerCase()
        + "_"
        + noun.noun.toLowerCase();
  }

  /**
   * Generates a unique string based on the given value. <b>The first call may take some time as it
   * loads data from the database synchronously.</b>
   *
   * @param value the value to generate the string from
   * @return the generated fancy name
   */
  public String generate(String value) {
    if (value == null) return "undefined";
    return generate(value.hashCode());
  }

  private static class Noun implements Serializable {

    public final String noun;
    public final String article;

    Noun(String noun, String article) {
      this.noun = noun;
      this.article = article;
    }
  }
}
