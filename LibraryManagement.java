import java.io.*;
import java.util.*;

class Book {
    String id;
    String title;
    String author;

    Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return id + "," + title + "," + author;
    }
}

public class LibraryManagement {

    static final String FILE = "books.txt";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 Add Book");
            System.out.println("2 Remove Book");
            System.out.println("3 Search Book");
            System.out.println("4 List Books");
            System.out.println("5 Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addBook(sc);
                    break;
                case "2":
                    removeBook(sc);
                    break;
                case "3":
                    searchBook(sc);
                    break;
                case "4":
                    listBooks();
                    break;
                case "5":
                    System.out.println("Exit");
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    static void addBook(Scanner sc) {
        try {
            System.out.print("ID: ");
            String id = sc.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("Invalid");
                return;
            }

            System.out.print("Title: ");
            String title = sc.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Invalid");
                return;
            }

            System.out.print("Author: ");
            String author = sc.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("Invalid");
                return;
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true));
            bw.write(id + "," + title + "," + author);
            bw.newLine();
            bw.close();
            System.out.println("Added");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    static void removeBook(Scanner sc) {
        try {
            System.out.print("ID to remove: ");
            String id = sc.nextLine().trim();

            File file = new File(FILE);
            if (!file.exists()) {
                System.out.println("No data");
                return;
            }

            List<String> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(id)) {
                    lines.add(line);
                } else {
                    found = true;
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();

            if (found) System.out.println("Removed");
            else System.out.println("Not found");

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    static void searchBook(Scanner sc) {
        try {
            System.out.print("Keyword: ");
            String key = sc.nextLine().trim().toLowerCase();

            File file = new File(FILE);
            if (!file.exists()) {
                System.out.println("No data");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;
            boolean flag = false;

            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(key)) {
                    System.out.println(line);
                    flag = true;
                }
            }
            br.close();

            if (!flag) System.out.println("No match");

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    static void listBooks() {
        try {
            File file = new File(FILE);
            if (!file.exists()) {
                System.out.println("No data");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(FILE));
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}