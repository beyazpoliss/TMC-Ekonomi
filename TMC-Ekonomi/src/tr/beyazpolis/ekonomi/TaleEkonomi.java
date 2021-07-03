package tr.beyazpolis.ekonomi;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tr.beyazpolis.database.manager.RegisterManager;
import tr.beyazpolis.ekonomi.feature.EconFeature;

public class TaleEkonomi extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    RegisterManager.register(new EconFeature());
  }

  @Override
  public void onDisable() {
  }
}
