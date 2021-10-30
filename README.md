# Custom Hashtable
* Designed for students/developers who want a custom hashtable with built-in encryption for saving passwords.
* The hashtable is written in Java.
* The hashtable references a char array which stores the passwords in the order they were inserted in and the index in the character array at which the string is stored is kept in the hashtable.
* The strings are read as an input from a text file with numbers as instructions and strings as value. If a string is deleted from the hashtable, it will replace the string in the char array as a sequence of ‘*’. If load-factor  > 50%, table is rehashed.
* Each command is a numeric equivalent of the function named earlier plus one more (for comment). Command 10 is Insert, Command 11 is Deletion, and Command 12 is Search. Command 13 is Print, Command 14 is Create. Command 15 is Comment: the rest of the line marked by a 15 is ignored. Command 14 for create has an argument which is the value of N. Each one of 10, 11, and 12 has an argument which is a string.
