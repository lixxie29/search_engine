package service;

import domain.Document;
import repository.Repository;

import java.util.ArrayList;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Document> getAll() throws Exception{
        var docs = repo.getAll();
        if (docs == null)
            throw new Exception("Could not read");
        return docs;}

    public void UpdateSchema(String newKey,String newContent, String name){
        repo.UpdateSchema(newKey,newContent,name);
    }
}
