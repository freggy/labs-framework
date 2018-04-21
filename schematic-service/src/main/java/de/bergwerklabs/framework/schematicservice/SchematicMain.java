package de.bergwerklabs.framework.schematicservice;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yannic Rieger on 27.06.2017.
 *
 * <p>Does literally nothing.
 *
 * @author Yannic Rieger
 */
public class SchematicMain extends JavaPlugin {

  public static final String CONSOLE_PREFIX = "[SchematicService] ";
  private static SchematicMain instance;

  public static SchematicMain getInstance() {
    return instance;
  }

  @Override
  public void onEnable() {
    instance = this;
  }
}
