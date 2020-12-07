package com.company;

import java.io.UnsupportedEncodingException;

public class Hashtable {

    private Storedword[] hashtable;
    private static char[] A;
    private int load = 0; // to calculate load factor
    private static int x = 0; // keep a track on index of "char[] A" for new input
    private static int y = 0; // for rehashing
    private static int z = 0;
    private static String[] temp_insert;
    private static String[] temp_delete;


    //Initialising Hashtable
    public Hashtable(int size) {
        A = createArray(size);
        hashtable = new Storedword[size];
        temp_insert = new String[size*15];
        temp_delete = new String[size*15];
    }

    // Array initialization
    public char[] createArray(int size) {
        return new char[size * 15];
    }

    // Insert into hashtable
    public void insert(String key) throws UnsupportedEncodingException {
        temp_insert[y] = key;
        y++;
        int hashedKey = hash(key);
        int probe = 1;
        if (occupied(hashedKey)) {
            int stopIndex = hashedKey;
            if (hashedKey == hashtable.length - 1) {
                hashedKey = 0;
                stopIndex = hashedKey;
            }
            else {
                hashedKey = (hashedKey + (probe * probe));
                stopIndex = hashedKey;

            }
            while (occupied(hashedKey) && hashedKey != stopIndex) {
                //quadratic probing
                probe++;
                hashedKey = (hashedKey + (probe * probe)) % hashtable.length;

            }
            hashtable[hashedKey] = new Storedword(key, x);


            for (int n = 0; n < key.length(); n++) {
                A[x] = key.charAt(n);
                ++x;
            }
            A[++x] = '\0';

            System.out.println(key + " inserted in slot " + hashedKey);
            load++;
        } else {
            hashtable[hashedKey] = new Storedword(key, x);
            for (int n = 0; n < key.length(); n++) {
                A[x] = key.charAt(n);
                ++x;
            }
            A[++x] = '\0';
            System.out.println(key + " inserted in slot " + hashedKey);
            load++;
        }

        if (loadfactor() > 50) {
            System.out.println("/...................................../");
            System.out.println("Rehashing because loadfactor > 50%");
            System.out.println("/...................................../");
            x = 0;
            y = 0;
            z = 0;
            load = 0;
            Storedword[] oldHashtable = hashtable;
            hashtable = new Storedword[oldHashtable.length * 2];
            // new char[] A
            A = createArray(hashtable.length); // size*15 implemented in array method
            //insert
            String[] temp_insert2 = temp_insert;
            temp_insert = new String[temp_insert2.length];
            for (int j = 0; j < temp_insert2.length; j++) {
                if (temp_insert2[j] != null) {
                    insert(temp_insert2[j]);

                } else {
                    break;
                }
            }
            //delete
            String[] temp_delete2 = temp_delete;
            temp_delete = new String[temp_delete2.length];
            for (int j = 0; j < temp_delete2.length; j++) {
                if (temp_delete2[j] != null) {
                    delete(temp_delete2[j]);

                } else {
                    break;
                }
            }

        }
    }


    // Search method
    public void search(String key) throws UnsupportedEncodingException {
        int hashedKey = findKey(key);
        if (hashedKey == -1) {
            System.out.println(key + " not found");
        } else {
            System.out.println(key + " found at slot " + hashedKey);
        }
    }

    // Delete method
    public void delete(String key) throws UnsupportedEncodingException {
        int hashedKey = findKey(key);
        if (hashedKey == -1) {
            System.out.println(key + " not found");
        } else {
            int n = hashtable[hashedKey].index;
            //System.out.print(n);
            for (int i = 0; i < key.length(); i++) {
                A[n] = '*';
                n++;
            }
            hashtable[hashedKey] = null;
            load--;
            temp_delete[z] = key;
            z++;
            System.out.println(key + " deleted from slot " + hashedKey);
        }
    }

    // used to find the key
    private int findKey(String key) throws UnsupportedEncodingException {
        int hashedKey = hash(key);
        int probe = 1;
        if (hashtable[hashedKey] != null && hashtable[hashedKey].key.equals(key)) {
            return hashedKey;
        }
        int stopIndex = hashedKey;
        if (hashedKey == hashtable.length - 1) {
            hashedKey = 0;
            stopIndex = hashedKey;
        } else {
            //hashedKey++;
            hashedKey = (hashedKey + (probe * probe)); //quadratic probing
            stopIndex = hashedKey;
        }
        while (hashedKey != stopIndex && hashtable[hashedKey] != null
                && !hashtable[hashedKey].key.equals(key)) {
            //quadratic probing
            probe++;
            hashedKey = (hashedKey + (probe * probe)) % hashtable.length;
        }
        if (hashtable[hashedKey] != null && hashtable[hashedKey].key.equals(key)) {
            return hashedKey;
        }
        else {
            return -1;
        }
    }

    //hashing the key
    private int hash(String key) throws UnsupportedEncodingException {
        int sum = ascii_sum(key);
        return (sum - 4) % hashtable.length;
    }

    //Convert key into sum of ascii values
    private int ascii_sum(String key) throws UnsupportedEncodingException {
        int sum = 0;
        //getting ascii values using bytes[]
        byte[] bytes = key.getBytes("US-ASCII");
        for (int i = 0; i < bytes.length; i++) {
            sum += bytes[i];
        }
        return sum;
    }

    // true if occupied
    private boolean occupied(int index) {
        return hashtable[index] != null;
    }

    // Load factor
    public int loadfactor() {
        return (load * 100) / hashtable.length;
    }

    // Print hashtable
    public void print() {
        System.out.println("-----------------------------");
        String arr = "";
        for (int i = 0; i < A.length; i++) {
            arr += A[i];

            if (A[i] == '\0') {
                arr += '\\';
            }
            if (A[i] == '\0' && A[i + 1] == '\0') {
                break;
            }
        }
        System.out.println("\tT \t\t\t" + "A: " + arr);
        System.out.println();

        for (int i = 0; i < hashtable.length; i++) {
            if (hashtable[i] == null)
                System.out.println(i + ": ");
            else
                System.out.println(i + ": " + hashtable[i].index);
        }
        System.out.println("-----------------------------");
    }
}
