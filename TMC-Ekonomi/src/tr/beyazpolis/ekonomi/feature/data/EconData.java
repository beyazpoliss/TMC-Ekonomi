package tr.beyazpolis.ekonomi.feature.data;

import com.mongodb.client.model.Filters;
import java.util.UUID;
import org.bson.Document;
import tr.beyazpolis.database.manager.MongoManager;
import tr.beyazpolis.database.registery.FeatureData;
import tr.beyazpolis.ekonomi.feature.EconFeature;

public class EconData implements FeatureData {

  private final UUID uuid;
  private double money;

  private final EconFeature econFeature;

  public EconData(final UUID uuid, final double money) {
    this.uuid = uuid;
    this.money = money;
    this.econFeature = new EconFeature();
  }

  @Override
  public void save(final MongoManager mongoManager, final UUID uuid) {
    final Document found =
      mongoManager.getDatabase().getCollection(econFeature.getCollectionManager().getName()).find(Filters.eq("uuid", uuid.toString())).first();
    if (found == null){
      final EconData econData = new EconData(uuid,100000);
      Document document = new Document("uuid",uuid.toString());
      document.append("money",econData.getMoney());
      mongoManager.getDatabase().getCollection(econFeature.getCollectionManager().getName()).insertOne(document);
    } else {
      final Document updateValue = new Document("money",money);
      final Document updater = new Document("$set",updateValue);
      mongoManager.getDatabase().getCollection(econFeature.getCollectionManager().getName()).updateOne(found, updater);
    }
  }

  public double setMoney(final double money) {
    return this.money = money;
  }

  public UUID getUuid() {
    return uuid;
  }

  public double getMoney() {
    return money;
  }

  public EconFeature getEconFeature() {
    return econFeature;
  }
}
