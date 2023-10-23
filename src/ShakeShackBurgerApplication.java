
public class Main {
    public static void main(String[] args) {

        // Initialize the classes with a shared instance of the Context.
        MenuContext context=new MenuContext();
        MenuDisplay display = new MenuDisplay(context);
        OrderHandler handler = new OrderHandler(context);
        AdminPage admin=new AdminPage(context);

        display.displayMainMenu();

        handler.handleMainMenuInput();

        admin.displayWaitingOrder();

        admin.printmenu(your_menu_list_here, starting_number_here);

        admin.findDeleteMenu(menu_number_here, menu_name_here);
    }
}