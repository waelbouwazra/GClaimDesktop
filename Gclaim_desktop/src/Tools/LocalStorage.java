/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entities.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Hassen Chouadah
 */
public class LocalStorage {

    private String fileName = "storage.txt";

    public LocalStorage() throws IOException {
        this.initLLocalStorage();
    }

    public File initLLocalStorage() throws IOException {

        File myObj = new File(fileName);
        if (myObj.createNewFile()) {
            System.out.println("storage created: " + myObj.getName());
        } else {
            System.out.println("storage init.");
        }
        return myObj;
    }

    public void writeToStorage(Utilisateur user) throws IOException {

        FileWriter myWriter = new FileWriter(fileName);
        JSONObject obj = new JSONObject();
        obj.put("idUser", user.getId());
        obj.put("username", user.getUsername());
        obj.put("email", user.getEmail());
        obj.put("password", user.getPassword());
        obj.put("verifpassowrd", user.getVerifpassword());
        obj.put("roles", user.getRoles());

        System.out.println(obj.toJSONString());

        myWriter.write(obj.toJSONString());
        myWriter.close();
        System.out.println("Successfully stored connectedUser.");
    }

    public Utilisateur getStoredUser() throws FileNotFoundException {
        Utilisateur user = new Utilisateur();

        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            Object obj = JSONValue.parse(data);
            JSONObject json = (JSONObject) obj;
            user = getUserFromJSON(json);
        }
        myReader.close();
        return user;
    }

    public Utilisateur getUserFromJSON(JSONObject json) {
        Utilisateur user = new Utilisateur();
        long idUser = (Long) json.get("id");
        String username = (String) json.get("username");

        String email = (String) json.get("email");
        String mdpUser = (String) json.get("password");
        String mdpUser1 = (String) json.get("verifpassword");

        String role = (String) json.get("roles");

        user.setId(Math.toIntExact(idUser));
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(mdpUser); 
        user.setVerifpassword(mdpUser1);
        
        user.setRoles(role);
        

        return user;

    }

    public void deleteStorage() {
        File myObj = new File(fileName);
        if (myObj.delete()) {
            System.out.println("Deleted storage: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

}
