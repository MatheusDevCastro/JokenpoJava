package com.mycompany.jokenpo;

import com.mongodb.*;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.awt.List;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.transform.dom.DOMSource;
import org.bson.Document;
import java.lang.*;
import org.bson.conversions.Bson;

public class Connect {

    public static void main(String[] args) {

    }

    public static String pegarSenha(String nick) {
        String password = "";
        try ( MongoClient mongo = new MongoClient("localhost", 27017);) {
            MongoDatabase database = mongo.getDatabase("jokenpo");
            MongoCollection<Document> collection = database.getCollection("user");
            try {
                DistinctIterable<String> docs = collection.distinct("Pass", Filters.eq("Nick", nick), String.class);
                MongoCursor<String> results = docs.iterator();
                while (results.hasNext()) {

                    password = results.next();
                }
                //System.out.println("Name: " + name);
            } catch (MongoException me) {
                System.err.println("An error occurred: " + me);
            }
        }
        return password;
    }

    public static int pegarVitorias(String nick) {
        int vitorias = 0;
        try ( MongoClient mongo = new MongoClient("localhost", 27017);) {
            MongoDatabase database = mongo.getDatabase("jokenpo");
            MongoCollection<Document> collection = database.getCollection("user");
            try {
                DistinctIterable<Integer> docs = collection.distinct("Win", Filters.eq("Nick", nick), Integer.class);
                MongoCursor<Integer> results = docs.iterator();
                while (results.hasNext()) {

                    vitorias = results.next();
                }
                //System.out.println("Special Attack: " + spAtk);
            } catch (MongoException me) {
                System.err.println("An error occurred: " + me);
            }
        }
        return vitorias;
    }

    public static int pegarDerrotas(String nick) {
        int derrotas = 0;
        try ( MongoClient mongo = new MongoClient("localhost", 27017);) {
            MongoDatabase database = mongo.getDatabase("jokenpo");
            MongoCollection<Document> collection = database.getCollection("user");
            try {
                DistinctIterable<Integer> docs = collection.distinct("Loss", Filters.eq("Nick", nick), Integer.class);
                MongoCursor<Integer> results = docs.iterator();
                while (results.hasNext()) {

                    derrotas = results.next();
                }
                //System.out.println("Special Attack: " + spAtk);
            } catch (MongoException me) {
                System.err.println("An error occurred: " + me);
            }
        }
        return derrotas;
    }

    public static Jogador novoJogador(String nick) {
        int vitorias = pegarVitorias(nick);
        int derrotas = pegarDerrotas(nick);
        Jogador player = new Jogador(nick, vitorias, derrotas);
        
        //System.out.println("Nick: " + nick);
        //System.out.println("Vitorias: " + pegarVitorias(nick));
        //System.out.println("Derrotas: " + pegarDerrotas(nick));
        
        
        player.info();
        
        return player;
    }

    public static void newUser(String nome, String nick, int idade, String senha) {
        try ( MongoClient mongo = new MongoClient("localhost", 27017);) {
            MongoDatabase database = mongo.getDatabase("jokenpo");
            MongoCollection<Document> collection = database.getCollection("user");
            try {
                Document doc = new Document();
                doc.put("Name", nome);
                doc.put("Nick", nick);
                doc.put("Age", idade);
                doc.put("Pass", senha);
                doc.put("Win", 0);
                doc.put("Loss", 0);

                collection.insertOne(doc);

            } catch (MongoException me) {
                System.err.println("An error occurred: " + me);
            }

        }
    }

    public static void addW(String nick) {
        
        int vitorias = pegarVitorias(nick) + 1;
        try ( MongoClient mongo = new MongoClient("localhost", 27017);) {
            MongoDatabase database = mongo.getDatabase("jokenpo");
            MongoCollection<Document> collection = database.getCollection("user");
            try {
                Document found = (Document) collection.find(new Document("Nick", nick)).first();
                
                if(found != null){
                    Bson updatedvalue = new Document("Win", vitorias);
                    Bson updateoperation = new Document("$set", updatedvalue);
                    collection.updateOne(found, updateoperation);   
                }

            } catch (MongoException me) {
                System.err.println("An error occurred: " + me);
            }

        }
    }
    
    public static void addL(String nick) {
        
        int derrotas = pegarDerrotas(nick) + 1;
        try ( MongoClient mongo = new MongoClient("localhost", 27017);) {
            MongoDatabase database = mongo.getDatabase("jokenpo");
            MongoCollection<Document> collection = database.getCollection("user");
            try {
                Document found = (Document) collection.find(new Document("Nick", nick)).first();
                
                if(found != null){
                    Bson updatedvalue = new Document("Loss", derrotas);
                    Bson updateoperation = new Document("$set", updatedvalue);
                    collection.updateOne(found, updateoperation);   
                }

            } catch (MongoException me) {
                System.err.println("An error occurred: " + me);
            }

        }
    }

}
