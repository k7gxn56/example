package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenxiang
 * @date 2020/7/9 17:40
 * @description
 */
public class MongoDBJDBC {
    public static void main(String[] args) {
        try{

            MongoClient mongoClient = new MongoClient("10.43.2.74",40000);
            MongoDatabase clientActivity = mongoClient.getDatabase("client_activity");
            System.out.println("connection to clientActivity success.");
            MongoCollection<Document> testxxx = clientActivity.getCollection("testxxx");
            testxxx.deleteOne(Filters.eq("origin","US"));
            Document document = new Document("language", "IOS")
                    .append("from", 2010)
                    .append("web", true)
                    .append("shell",true)
                    .append("origin","US");
            List<Document> documents = new ArrayList<>();
            documents.add(document);
            testxxx.insertMany(documents);
            FindIterable<Document> documentFindIterable = testxxx.find();
            MongoCursor<Document> documentMongoCursor = documentFindIterable.iterator();
            while (documentMongoCursor.hasNext()){
                System.out.println(documentMongoCursor.next());
            }
            //testxxx.updateMany(Filters.eq("language","Android"),new Document("$set",new Document("origin","US")));
        }catch (Exception e){
            System.err.println(e.getClass().getName()+":"+e.getMessage());
        }
    }
}
