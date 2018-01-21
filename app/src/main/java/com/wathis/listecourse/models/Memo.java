package com.wathis.listecourse.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mathisdelaunay on 18/01/2018.
 */

public class Memo {

    public String titre;
    public String description;

    static private Memo instance;

    static public Memo sharedInstance() {
        if (instance == null) {
            instance = new Memo();
        }
        return instance;
    }

    public Memo() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Memo(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("titre", titre);
        result.put("description", description);
        return result;
    }

}
