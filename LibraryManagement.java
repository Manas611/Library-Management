import java.util.*;

abstract class Item
{
    private int id;
    private String author;
    private String title;

    public abstract void DisplayDetails();

    // Item Constructor
    Item(int id,String author, String title)
    {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    // Getter of Item
    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

}

class Book extends Item implements CheckOutable
{
    private int ISBN;
    private boolean checkedOut;

    // Constructor of Book Class
    Book(int id, String author, String title, int ISBN)
    {
        super(id, author, title);
        this.ISBN = ISBN;
        this.checkedOut = false;
    }

    // Abstract method of Item
    public void DisplayDetails()
    {
        System.out.println("Detail of the Book is ");
        System.out.println("Book Id " + getId());
        System.out.println("Book Author " + getAuthor());
        System.out.println("Book title " +  getTitle());
        System.out.println("Book ISBN number " + ISBN);

    }

    // Checking Book is present or not
    public void checkOut()
    {
        if(!checkedOut)
        {
            checkedOut = true;
            System.out.println("Check out Done for Book");
        }
        else
        {
            System.out.println("Book is not Present");
        }
    }

    public void checkIn()
    {
        if(checkedOut)
        {
            checkedOut = false;
            System.out.println("Book CheckIn successfull");
        }
        else
        {
            System.out.println("CheckIn not Done");
        }
    }
}

class Magazine extends Book implements CheckOutable
{
    private int IssueNumber;
    private boolean checkedOut;

    // Constructor of Magazine Class
    Magazine(int id, String author, String title,int IssueNumber)
    {
        super(id, author, title, IssueNumber);
        this.IssueNumber = IssueNumber;
        this.checkedOut = false;
    }

    public void DisplayDetails()
    {
        System.out.println();
        System.out.println("Detail of the Magazine is ");
        System.out.println("Magazine Id " + getId());
        System.out.println("Magazine Author " + getAuthor());
        System.out.println("Magazine title " +  getTitle());
        System.out.println("Magazine Issue number " + IssueNumber);
    }

    public void checkOut()
    {
        if(!checkedOut)
        {
            checkedOut = true;
            System.out.println("Check out Done for Magazine");
        }
        else
        {
            System.out.println("Magazine is not Present");
        }
    }

    public void checkIn()
    {
        if(checkedOut)
        {
            checkedOut = false;
            System.out.println("Magazine CheckIn successfull");
        }
        else
        {
            System.out.println("CheckIn not Done");
        }
    }
}

interface CheckOutable {
    void checkOut();
    void checkIn();
}

class LibraryMember
{
    private int memberId;
    private String name;

    LibraryMember(int memberId,String name)
    {
        this.memberId = memberId;
        this.name = name;
    }

    public int getMemberId()
    {
        return memberId;
    }

    public String getMemberName()
    {
        return name;
    }

    // Library Class calls this to Checkout the item
    public void checkOutItem(CheckOutable item)
    {
        item.checkOut();
        System.out.println(name + "Checked out the Item");
    }

    public void returnItem(CheckOutable item)
    {
        item.checkIn();
        System.out.println(name + " returned the item: ");
    }
}

class Library {
    // To store the Items in Map
    private Map<Integer, Item> inventory;

    // To Store members in MAp with its id and name
    private Map<Integer, LibraryMember> members;

    // Constructor
    public Library() {
        inventory = new HashMap<>();
        members = new HashMap<>();
    }

    // Method to add a new item to the inventory
    public void addItem(Item item) {
        inventory.put(item.getId(), item);
    }

    // Method to register a new library member
    public void registerMember(LibraryMember member) {
        members.put(member.getMemberId(), member);
    }

    // Method to check out an item
    public void checkOutItem(int memberId, int itemId) {
        LibraryMember member = members.get(memberId);
        Item item = inventory.get(itemId);
        if (member != null && item != null) {
            member.checkOutItem((CheckOutable) item);
        } else {
            System.out.println("Invalid member ID or item ID.");
        }
    }

    // Method to return an item
    public void returnItem(int memberId, int itemId) {
        LibraryMember member = members.get(memberId);
        Item item = inventory.get(itemId);
        if (member != null && item != null && item instanceof CheckOutable) {
            member.returnItem((CheckOutable) item);
        } else {
            System.out.println("Invalid member ID or item ID.");
        }
    }

    // Method to display item details
    public void displayItemDetails(int itemId) {
        Item item = inventory.get(itemId);
        if (item != null) {
            item.DisplayDetails();
        } else {
            System.out.println("Item not found.");
        }
    }
}



public class LibraryManagement {
    public static void main(String[] args) {
        Book b1 = new Book(1, "Manas", "The last Ride", 100);
        Magazine m1 = new Magazine(2, "Saksham", "Red Ferrari", 200);
        LibraryMember lb = new LibraryMember(10, "Guest");

        Library library = new Library();

        // Adding items to the Library
        library.addItem(b1);
        library.addItem(m1);

        // Adding a new library member
        library.registerMember(lb);

        // Simulating check-out and return operations
        library.checkOutItem(10, 2);
        library.checkOutItem(10, 2);
        
        // library.returnItem(10, 2);

        // Displaying item details
        library.displayItemDetails(2);

    }
}
