package de.bergwerklabs.framework.commons.misc;

import de.bergwerklabs.framework.commons.database.tablebuilder.Database;
import de.bergwerklabs.framework.commons.database.tablebuilder.DatabaseType;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Statement;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NicknameGenerator {

  private static final Database DATABASE =
      new Database(
          DatabaseType.MySQL, "sql.bergwerklabs.de", "nicks", "framework", "UkLBdagt7bghgPCGuGhy");

  private static final Map<String, Double> SETTINGS = new HashMap<>();

  private static final Map<Integer, List<String>> BODY_START_PARTS = new HashMap<>();
  private static final Map<Integer, List<String>> BODY_END_PARTS = new HashMap<>();

  private static final List<String> PREFIX_PARTS = new ArrayList<>();
  private static final List<String> SUFFIX_PARTS = new ArrayList<>();
  private static final List<String> NUMBER_PARTS = new ArrayList<>();

  static {
    Statement settingsStatement = DATABASE.prepareStatement("SELECT * FROM setting");
    Arrays.stream(settingsStatement.execute().getRows())
        .forEach(row -> SETTINGS.put(row.getString("setting"), row.getDouble("setting_value")));
    settingsStatement.close();

    Statement partStatement = DATABASE.prepareStatement("SELECT * FROM part");
    Arrays.stream(partStatement.execute().getRows())
        .forEach(
            row -> {
              String type = row.getString("type");
              String part = row.getString("part");
              if (type.equalsIgnoreCase("PREFIX")) {
                PREFIX_PARTS.add(part);
              } else if (type.equalsIgnoreCase("SUFFIX")) {
                SUFFIX_PARTS.add(part);
              } else if (type.equalsIgnoreCase("NUMBER")) {
                NUMBER_PARTS.add(part);
              } else if (type.equalsIgnoreCase("BODY_START")) {
                addPart(part, BODY_START_PARTS);
              } else if (type.equalsIgnoreCase("BODY_END")) {
                addPart(part, BODY_END_PARTS);
              }
            });
    partStatement.close();
  }

  private static void addPart(String part, Map<Integer, List<String>> toMap) {
    int length = part.length();
    if (!toMap.containsKey(length)) toMap.put(length, new ArrayList<>());
    toMap.get(length).add(part);
  }

  private static String selectRandomStrings(List<String> from, int amount, Random random) {
    StringBuilder result = new StringBuilder("");
    for (int i = 0; i < amount; i++) {
      int index = random.nextInt(from.size());
      result.append(from.get(index));
    }
    return result.toString();
  }

  private static String getRandomUnderscore(Random random) {
    return random.nextDouble() <= SETTINGS.get("SEPARATOR_CHANCE") ? "_" : "";
  }

  private static List<String> getValidStrings(int maxLength, Map<Integer, List<String>> fromMap) {
    List<String> result = new ArrayList<>();
    for (int i = 1; i <= maxLength; i++) {
      if (!fromMap.containsKey(i)) continue;
      result.addAll(fromMap.get(i));
    }
    return result;
  }

  private static String generateBody(int minLeft, int maxLeft, Random random) {
    StringBuilder body =
        new StringBuilder(
            selectRandomStrings(getValidStrings(maxLeft, BODY_START_PARTS), 1, random));

    minLeft = Math.max(minLeft - body.length(), 0);
    maxLeft = maxLeft - body.length();

    do {
      List<String> valids = getValidStrings(maxLeft, BODY_END_PARTS);
      if (valids.size() == 0) break;
      String part = selectRandomStrings(valids, 1, random);
      body.append(part);
      minLeft = Math.max(minLeft - part.length(), 0);
      maxLeft = maxLeft - part.length();
    } while (minLeft > 0 || (maxLeft > 0 && Math.random() <= SETTINGS.get("BODY_APPEND_CHANCE")));

    if (maxLeft > 0) {
      String number;
      if (Math.random() <= SETTINGS.get("RANDOM_NUMBER_CHANCE")) {
        int mode = random.nextInt(4);
        if (mode == 0) {
          number = String.valueOf(random.nextInt(10));
        } else if (mode == 1) {
          number = String.valueOf(random.nextInt(100));
        } else if (mode == 2) {
          number = String.valueOf(random.nextInt(1000));
        } else {
          number = String.valueOf(random.nextInt(10000));
        }
      } else {
        number = selectRandomStrings(NUMBER_PARTS, 1, random);
      }

      if (maxLeft >= number.length()) {
        body.append(number);
      }
    }

    return body.toString();
  }

  /**
   * Generates a random nickname. <b>The first call may take some time as it loads data from the
   * database synchronously.</b>
   *
   * @return the generated nickname
   */
  public static String generate() {
    final SecureRandom random = new SecureRandom();

    int minLength = Math.max(0, SETTINGS.get("MIN_NAME_LENGTH").intValue());
    int maxLength = (int) Math.max(Math.min(16, SETTINGS.get("MAX_NAME_LENGTH")), minLength);

    String prefix = getRandomUnderscore(random);
    if (random.nextDouble() <= SETTINGS.get("PREFIX_CHANCE")) {
      prefix +=
          selectRandomStrings(
              PREFIX_PARTS,
              random.nextInt(SETTINGS.get("MAX_PREFIX_COUNT").intValue() + 1),
              random);
    }
    prefix += getRandomUnderscore(random);

    String suffix = getRandomUnderscore(random);
    if (random.nextDouble() <= SETTINGS.get("SUFFIX_CHANCE")) {
      suffix +=
          selectRandomStrings(
              SUFFIX_PARTS,
              random.nextInt(SETTINGS.get("MAX_SUFFIX_COUNT").intValue() + 1),
              random);
    }
    suffix += getRandomUnderscore(random);

    int minLeft = Math.max(minLength - suffix.length() - prefix.length(), 0);
    int maxLeft = maxLength - prefix.length() - suffix.length();

    String body = generateBody(minLeft, maxLeft, random);

    return prefix + body + suffix;
  }
}
