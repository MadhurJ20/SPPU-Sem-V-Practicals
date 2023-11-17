/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/

/*
Database Connectivity:
Write a program to implement MongoDB database connectivity with any front-end
language to implement Database navigation operations (add, delete, edit etc.)
*/

import java.util.Scanner;
import com.mongodb.*;

public class MongoDBConnectivity {
    private static void choice_input()
    {
        System.out.println("\n1.insert data into database\n2.update database documents\n3.delete database documents\n4.show database collections\n5.Exit");
    }
    public static void main(String[] args) {
        String key, value;
        Scanner scanner = new Scanner(System.in);
        int choice;
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("TestDB");
            DBCollection collection = db.getCollection("Student");
            do
            {
                choice_input();
                System.out.println("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        BasicDBObject document = new BasicDBObject();
                        String ch;
                        do{
                            System.out.println("Enter key: ");
                            key = scanner.next();
                            System.out.println("Enter value: ");
                            value = scanner.next();
                            document.put(key, value);
                            System.out.println("Do you want to enter more(y/n)? ");
                            ch = scanner.next();
                        } while (!ch.equals("n"));
                        collection.insert(document);
                        break;
                    case 2:
                        BasicDBObject searchObj = new BasicDBObject();
                        System.out.println("Enter searched key: ");
                        key = scanner.next();
                        System.out.println("Enter searched value: ");
                        value = scanner.next();
                        searchObj.put(key, value);
                        BasicDBObject newObj = new BasicDBObject();
                        System.out.println("Enter new key: ");
                        key = scanner.next();
                        System.out.println("Enter new value: ");
                        value = scanner.next();
                        newObj.put(key, value);
                        collection.update(searchObj, newObj);
                        break;
                    case 3:
                        System.out.println("Enter removable key: ");
                        key = scanner.next();
                        System.out.println("Enter removable value: ");
                        value = scanner.next();
                        BasicDBObject removableObj = new BasicDBObject();
                        removableObj.put(key, value);
                        collection.remove(removableObj);
                        break;
                    case 4:
                        DBCursor cursorDoc = collection.find();
                        while (cursorDoc.hasNext()) {
                            System.out.println(cursorDoc.next());
                        }
                        break;
                    case 5:
                        System.exit(0);
                        break;
                }
            } while(choice != 6);
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}

