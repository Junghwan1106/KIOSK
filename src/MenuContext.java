import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuContext {
    private Map<String, List<Menu>> menus;
    private Map<String, List<Item>> menuItems;
    private List<Item> cart;
    private double totalPrice;
    private int orderNumber;
    private List<Order> waitingOrders;
    private List<Order> completedOrders;

    /**             생성자                */
    public MenuContext() {
        menus = new HashMap<>();
        menuItems = new HashMap<>();
        cart = new ArrayList<>();
        totalPrice = 0.0;
        orderNumber = 0;
        waitingOrders = new ArrayList<>();
        completedOrders = new ArrayList<>();

        initializeMenuItems();
    }

    // 메뉴 생성 리스트 추가
    public void initializeMenuItems() {
        addMenus("Main",
                new Menu("Burgers", "앵거스 비프 통살을 다져만든 버거"),
                new Menu("Forzen Custard", "매장에서 신선하게 만드는 아이스크림"),
                new Menu("Drinks", "매장에서 직접 만드는 음료"),
                new Menu("Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주")
        );

        addMenus("Order",
                new Menu("Order", "장바구니를 확인 후 주문합니다."),
                new Menu("Cancel", "진행중인 주문을 취소합니다.")
        );

        addMenuItems("Burgers",
                new Item("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new Item("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new Item("Shroom Burger", 9.4, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"),
                new Item("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new Item("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        );

        addMenuItems("Frozen Custard",
                new Item("Vanilla Ice cream", 1.4, "It's a basic"),
                new Item("Chocolate peanuts butter Ice cream", 1.0, "Delish thing")
        );

        addMenuItems("Drinks",
                new Item("Coke", 1.5, "Coca Cola"),
                new Item("Canada Dry", 1.5, "It's Ginger Ale")
        );

        addMenuItems("Beer",
                new Item("Cass", 4.0, "Origin beer in Korea"),
                new Item("Draft Beer", 4.0, "Everyone likes it")
        );
    }
    // 장바구니에 상품 추가
    public void addToCart(Item menuItem) {
        cart.add(menuItem);
        totalPrice += menuItem.getPrice();
    }
    // 장바구니에 상품 삭제
    public void displayCart() {
        for (Item item : cart) {
            System.out.println(item.getName() + " | "
                    + item.getPrice() + " | "
                    + item.getDescription());
        }
    }

    public List<Menu> getMenus(String key) {
        return menus.get(key);
    }

    public List<Item> getMenuItems(String key) {
        return menuItems.get(key);
    }
    public int getOrderNumber() {
        return orderNumber;
    }
    public List<Order> getCompletedOrders() {
        return completedOrders;
    }
    private void addMenus(String key, Menu... menusToAdd) {
        List<Menu> menuList = new ArrayList<>();
        for (Menu menu : menusToAdd) {
            menuList.add(menu);
        }
        menus.put(key, menuList);
    }
    public List<Order> getWaitingOrders() {
        return waitingOrders;
    }
    private void addMenuItems(String key, Item... itemsToAdd) {
        List<Item> itemList = new ArrayList<>();
        for (Item item : itemsToAdd) {
            itemList.add(item);
        }
        menuItems.put(key, itemList);
    }
    public void resetCart() {
        cart.clear();
        totalPrice = 0.0;
    }
    public int generateOrderNumber() {
        orderNumber++;
        return orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public List<Item> getCart() {
        return cart;
    }
    public void addToCompleteOrder(Order order) {
        // 관리자 페이지에서 주문 처리 완료 후에 싫행
        completedOrders.add(order);
    }
    public void addToWaitingOrder(Order order){
        // 주문 완료 후에 실행
        waitingOrders.add(order);
    }
}