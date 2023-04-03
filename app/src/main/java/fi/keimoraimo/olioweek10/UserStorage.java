package fi.keimoraimo.olioweek10;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserStorage {

    private ArrayList<User> Users = new ArrayList<>();
    private static final  String FILENAME = "users.data";

    private static UserStorage userStorage = null;

    private UserStorage() {
    }

    public static UserStorage getInstance(){
        if (userStorage == null){
            userStorage = new UserStorage();
        }
        return userStorage;
    }

    public ArrayList<User> getUsers(){
        Collections.sort(Users, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.getLastName().compareToIgnoreCase(user2.getLastName());
            }
        });
        return Users;
    }

    public void addUser(User user, Context context){
        Users.add(user);
        try {
            ObjectOutputStream userWriter = new ObjectOutputStream(context.openFileOutput(FILENAME,Context.MODE_PRIVATE));
            userWriter.writeObject(Users);
            userWriter.close();
        } catch (IOException e) {
            System.out.println("Käyttäjien tallentaminen ei onnistunut");
        }
    }

    public UserStorage loadUsers(Context context) {
        try {
            ObjectInputStream userReader = new ObjectInputStream(context.openFileInput(FILENAME));
            Users = (ArrayList<User>) userReader.readObject();
            userReader.close();
        }catch (FileNotFoundException e){
            System.out.println("Käyttäjien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut");
            e.printStackTrace();
        }
        return null;
    }
}
