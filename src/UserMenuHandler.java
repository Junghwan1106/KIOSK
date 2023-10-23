import java.util.*;

public class UserMenuHandler {
    private static MenuContext menuContext;
    private static AdminMenuHandler adminMenuHandler;

    public UserMenuHandler(MenuContext menuContext) {
        this.menuContext = menuContext;
        this.adminMenuHandler = new AdminMenuHandler(menuContext);
    }
    /**             메인 메뉴 관련 함수              */

    // 메인 메뉴 출력
    public static void displayMainMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");

        System.out.println("[ SHAKESHACK MENU ]");
        List<Menu> mainMenus = menuContext.getMenus("Main");
        int nextNum = printMenu(mainMenus, 1);

        System.out.println("[ ORDER MENU ]");
        List<Menu> orderMenus = menuContext.getMenus("Order");
        nextNum=printMenu(orderMenus, nextNum);
        System.out.println(nextNum+". recent orders |   완료된 최근주문 3개와 현재 대기중인 주문들을 보여줍니다");
        UserMenuHandler.handleMainMenuInput();
    }
    private static int printMenu(List<Menu> menus, int num) {
        for (int i=0; i<menus.size(); i++) {
            System.out.println(num++ + ". " + menus.get(i).getName() + "   | " + menus.get(i).getDescription());
        }
        return num;
    }
    // 메인 메뉴 입력
    private static void handleMainMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        try{
            input = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("잘못된 요청입니다. 다시 입력해주세요");
            displayMainMenu();
        }
        switch (input) {
            case 0: // 관리자 페이지(숨기기)
                adminMenuHandler.displayAdminMenu();
                break;
            case 1:
                displayBurgersMenu();
                break;
            case 2:
                displayFrozenCustardMenu();
                break;
            case 3:
                displayDrinksMenu();
                break;
            case 4:
                displayBeerMenu();
                break;
            case 5:
                displayOrderMenu();
                break;
            case 6:
                handleCancelMenuInput();
                break;
            case 7:
                RecentOrder();
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                handleMainMenuInput();
                break;
        }
    }
    // 최근 주문 목록 출력
    private static void RecentOrder(){
        System.out.println("========================================");
        System.out.println("대기 중인 주문 목록입니다.\n");

        System.out.println("[ 대기 주문 목록 ]");
        List<Order> waitingOrders = menuContext.getWaitingOrders();
        if(waitingOrders.isEmpty()){
            System.out.println("대기 중인 주문이 없습니다.");
        }else {
            AdminMenuHandler.printOrders(waitingOrders);
        }// if~else() of the end
        System.out.println("========================================");

        System.out.println("처리가 완료된 주문 목록입니다.\n");
        System.out.println("[ 최근 주문 완료 목록 ]");
        try {
            AdminMenuHandler.printRecentOrders(menuContext.getCompletedOrders());
            System.out.println("완료된 주문이 없습니다.");
            System.out.println("========================================\n");
        }catch (Exception e){
            System.out.println("잘못된 요청입니다.");
            displayMainMenu();
        }
        displayMainMenu();
    }

    /**             상품 선택 및 확인 관련 함수                 */

    // 버거 메뉴판 출력
    private static void displayBurgersMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.\n");

        System.out.println("[ Burgers MENU ]");
        List<Item> burgerItems = menuContext.getMenuItems("Burgers");
        printMenuItems(burgerItems);

        handleMenuItemInput(burgerItems);
    }
    // 상품 선택 입력
    private static void handleMenuItemInput(List<Item> items) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        try{
            input = scanner.nextInt();
            input--;
            Item selectedItem = items.get(input);
            displayConfirmation(selectedItem);
        }catch (Exception e){
            System.out.println("잘못된 요청입니다. 다시 입력해주세요.!!!");
            handleMenuItemInput(items);
        }
    }
    public static void printMenuItems(List<Item> items) {
        for (int i=0; i<items.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + items.get(i).getName() + "   | " + items.get(i).getPrice() + " | " + items.get(i).getDescription());
        }
    }
    // 아이스크림 메뉴 출력
    private static void displayFrozenCustardMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.\n");

        System.out.println("[ Frozen Custard MENU ]");
        List<Item> frozenCustardItems = menuContext.getMenuItems("Frozen Custard");
        printMenuItems(frozenCustardItems);

        handleMenuItemInput(frozenCustardItems);
    }
    // 음료 메뉴 출력
    private static void displayDrinksMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.\n");

        System.out.println("[ Drinks MENU ]");
        List<Item> drinkItems = menuContext.getMenuItems("Drinks");
        printMenuItems(drinkItems);

        handleMenuItemInput(drinkItems);
    }
    // 맥주 메뉴 출력
    private static void displayBeerMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.\n");

        System.out.println("[ Beer MENU ]");
        List<Item> beerItems = menuContext.getMenuItems("Beer");
        printMenuItems(beerItems);

        handleMenuItemInput(beerItems);
    }
    // 상품 추가 확인
    private static void displayConfirmation(Item menuItem) {
        System.out.println(menuItem.getName() + "   | " + menuItem.getPrice() + " | " + menuItem.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        handleConfirmationInput(menuItem);
    }
    // 상품 추가 확인 입력
    private static void handleConfirmationInput(Item menuItem) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        try{
            input = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleConfirmationInput(menuItem);
        }

        if (input == 1) {
            menuContext.addToCart(menuItem);
            System.out.println("장바구니에 추가되었습니다.");
            displayMainMenu();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleConfirmationInput(menuItem);
        }
    }

    /**             주문 관련 함수             */

    // 주문하기 메뉴 출력
    private static void displayOrderMenu() {
        if(menuContext.getCart().isEmpty()){
            System.out.println("장바구니에 담긴 상품이 없습니다.");
            displayMainMenu();
        }else {
            System.out.println("아래와 같이 주문 하시겠습니까?\n");
            menuContext.displayCart();

            System.out.println("[ Total ]");
            System.out.printf("W %.1f\n", menuContext.getTotalPrice());
            System.out.println("1. 주문      2. 메뉴판");
        }
        handleOrderMenuInput();
    }
    // 주문하기 메뉴 입력
    private static void handleOrderMenuInput() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        try{
            input = scanner.nextInt();
        }catch (Exception e){
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleOrderMenuInput();
        }

        if (input == 1) {
            displayOrderComplete();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleOrderMenuInput();
        }
    }

    // 주문시 요청 사항 입력 받기
    private static void displayOrderComplete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("주문시 요청사항 메시지 : ");
        String request = scanner.nextLine();

        int orderNumber = menuContext.generateOrderNumber();
        System.out.println("주문이 완료되었습니다!\n");
        System.out.println("대기번호는 [ " + orderNumber + " ] 번 입니다.");
        setWaitingOrder(request);

        resetCartAndDisplayMainMenu();
    }

    // 주문한 내역 대기 주문 리스트에 입력하기
    private static void setWaitingOrder(String request) {
        Order order = new Order();
        Date now = new Date();

        List<Item> it = new ArrayList<>();
        for(Item its : menuContext.getCart()){
            it.add(its);
        }

        order.setOrderItems(it);
        order.setTotalPrice(menuContext.getTotalPrice());
        order.setRequestContent(request); //요청 사항
        order.setOrderDate(now);
        order.generateOrderCnt();
        order.setOrderNum(menuContext.getOrderNumber());
        menuContext.addToWaitingOrder(order);
    }
    // 장바구니 초기화 및 메인 메뉴 출력
    private static void resetCartAndDisplayMainMenu() {
        menuContext.resetCart();
        System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
        try {
            Thread.sleep(3000); // 3초 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        displayMainMenu();
    }

    /**             주문 취소 관련 함수             */

    // 주문 취소 메뉴 출력
    private static void handleCancelMenuInput() {
        if(!menuContext.getCart().isEmpty()) {
            System.out.println("주문을 취소하시겠습니까?");
            System.out.println("1. 확인        2. 취소");

            handleCancelConfirmationInput();
        }else {
            System.out.println("취소할 주문이 없습니다.");
            displayMainMenu();
        }
    }
    // 주문 취소 메뉴 입력 및 확인 처리
    private static void handleCancelConfirmationInput() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        try {
            input = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("잘못된 요청입니다. 다시 입력해주세요.");
            handleCancelConfirmationInput();
        }
        if (input == 1) {
            menuContext.resetCart();
            System.out.println("주문이 취소되었습니다.");
            displayMainMenu();
        } else if (input == 2) {
            displayMainMenu();
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            handleCancelConfirmationInput();
        }
    }
}