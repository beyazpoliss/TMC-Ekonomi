package tr.beyazpolis.ekonomi.network;

import java.util.UUID;
import tr.beyazpolis.database.manager.LibManager;
import tr.beyazpolis.ekonomi.feature.EconFeature;
import tr.beyazpolis.ekonomi.feature.data.EconData;

public class EconomyAPI {

  private EconomyAPI() {}

  public static double getMoney(UUID u){
    EconData econData = (EconData) LibManager.getLibManager().getDatabase().getProfileManager().getOrCreate(u).getFeatureData(EconFeature.class);
    return econData.getMoney();
  }

  public static double addMoney(UUID u, double amount) {
    EconData econData = (EconData) LibManager.getLibManager().getDatabase().getProfileManager().getOrCreate(u).getFeatureData(EconFeature.class);
    return econData.setMoney(econData.getMoney() + amount);
  }

  public static double removeMoney(UUID u, double amount) {
    EconData econData = (EconData) LibManager.getLibManager().getDatabase().getProfileManager().getOrCreate(u).getFeatureData(EconFeature.class);
    return econData.setMoney(econData.getMoney() - amount);
  }

  public static double setMoney(UUID u,double amount){
    EconData econData = (EconData) LibManager.getLibManager().getDatabase().getProfileManager().getOrCreate(u).getFeatureData(EconFeature.class);
    return econData.setMoney(amount);
  }

}
