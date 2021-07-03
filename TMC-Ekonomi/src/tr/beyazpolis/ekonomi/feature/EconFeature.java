package tr.beyazpolis.ekonomi.feature;

import com.mongodb.client.model.Filters;
import java.util.UUID;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.omg.CORBA.Object;
import tr.beyazpolis.database.manager.CollectionManager;
import tr.beyazpolis.database.manager.MongoManager;
import tr.beyazpolis.database.registery.Feature;
import tr.beyazpolis.database.registery.FeatureData;
import tr.beyazpolis.ekonomi.feature.data.EconData;

public class EconFeature implements Feature {

  private final CollectionManager collectionManager;

  public EconFeature() {
    this.collectionManager = new CollectionManager("Ekonomi");
  }

  @Override
  public FeatureData getOrCreate(final MongoManager mongoManager, UUID uuid) {
    final Document found = mongoManager.getDatabase()
      .getCollection(collectionManager.getName())
      .find(Filters.eq("uuid", uuid.toString()))
      .first();
    if (found == null){
      final EconData econData = new EconData(uuid,100000);
      Document document = new Document("uuid",uuid.toString());
      document.append("money",econData.getMoney());
      mongoManager.getDatabase().getCollection(collectionManager.getName()).insertOne(document);
      return econData;
    } else {
      final double money = found.getDouble("money");
      return new EconData(uuid,money);
    }
  }

  @Override
  public Feature<?> load() {
    collectionManager.load();
    return this;
  }

  public CollectionManager getCollectionManager() {
    return collectionManager;
  }
}
