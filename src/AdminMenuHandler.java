import java.util.*;
import java.text.SimpleDateFormat;

public class AdminMenuHandler {
    private MenuContext menuContext;
    private Scanner scanner;

    public AdminMenuHandler(MenuContext menuContext) {
        this.menuContext = menuContext;
        this.scanner = new Scanner(System.in);
    }
    /**             메인 관리자 메뉴           */

    // 메인 관리자 메뉴를 표시합니다.
    public void displayAdminMenu() {
        System.out.println("Admin ToToRo's 페이지다. 이말이양!");
        System.out.println("1. 대기주문 목록");
        System.out.println("2. 완료주문 목록");
        System.out.println("3. 상품 생성");
        System.out.println("4. 상품 삭제");
        System.out.println("5. 메인 페이지");
        System.out.print("항목을 선택하세요: ");

        int input = 0;

        try{
            input = scanner.nextInt();
        }catch (Exception e){
            System.out.println("잘못된 요청입니다. 다시 입력해주세요.");
        }
        switch (input) {
            case 1:
                displayWaitingOrder();
                break;
            case 2:
                printCompletedOrder();
                break;
            case 3:
                createItem();
                break;
            case 4:
                deleteItem();
                break;
            case 5:
                UserMenuHandler.displayMainMenu();
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                displayAdminMenu();
                break;
        }
    }
    /**         주문 처리 관련 메소드           */

    // 모든 주문 상세 정보를 출력하는 메소드
    public static void printOrders(List<Order> orders) {
        for (int i=0; i<orders.size(); i++) {
            printOrder(orders.get(i));
            System.out.println();
        }
    }
    // 최근 3개의 주문 상세 정보를 출력하는 메소드
    public static void printRecentOrders(List<Order> orders) { //
        int tempNum=orders.size()-3;
        if (tempNum < 0){
            tempNum =0;
        }
        for (int i = tempNum; i < orders.size(); i++) {
            printOrder(orders.get(i));
            Date date = orders.get(i).getCompleteDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:XXX");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            String dateString = sdf.format(date);
            System.out.println("완료 일시: " + dateString);
            System.out.println();
        }
    }
    // 선택한 주문 내역을 출력하는 메소드
    private static void printOrder(Order selectedOrder) {
        int num = selectedOrder.getOrderNum();
        System.out.println("대기 번호 : " + num);
        System.out.println("주문 상품 목록 : ");
        UserMenuHandler.printMenuItems(selectedOrder.orderItems);
        System.out.println("주문 총 가격 : " + selectedOrder.getTotalPrice());
        System.out.println("요청 사항: " + selectedOrder.getRequestContent());

        // 날짜는 ISO 8601 형식으로 ex)2023-10-23T17:13:40+00:00
        Date date = selectedOrder.getOrderDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:XXX");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateString = sdf.format(date);
        System.out.println("주문 일시: " + dateString);
    }
    /**         대기 중인 주문 처리 관련 함수           */

    //대기 중인 주문 조회 및 완료 화면
    private void displayWaitingOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("대기 중인 주문 목록입니다.\n");

        System.out.println("[ 대기 주문 목록 ]");
        List<Order> waitingOrders = menuContext.getWaitingOrders();
        if(waitingOrders.isEmpty()){
            System.out.println("대기 중인 주문이 없습니다.");
            System.out.println("========================================");
            UserMenuHandler.displayMainMenu();
        }else {
            printOrders(waitingOrders);

            System.out.println("완료할 주문 대기 번호를 입력해주세요.");
            handleWaitingOrders(waitingOrders); // 주문 완료 처리할 메서드
        }
    }

    // 대기 중인 주문 처리
    private void handleWaitingOrders(List<Order> orders) {
        int input = scanner.nextInt();  // 선택한 주문 번호
        Order selectedOrder = new Order(); // 선택한 주문
        boolean hasSelectedOrder = false; // 입력한 대기 번호에 맞는 주문이 있는지 여부

        for (Order order : orders) { // 입력받은 번호에 맞는 주문 찾기
            if (order.getOrderNum() == input) {
                selectedOrder = order;
                hasSelectedOrder = true;
            }
        }

        if (hasSelectedOrder) { // 입력받은 번호에 맞는 주문이 있을 경우
            confirmCompleteOrder(selectedOrder);
        } else { // 입력받은 번호에 맞는 주문이 없을 경우
            System.out.println("해당 번호에 맞는 주문이 없습니다.");
            System.out.println("다시 입력해주세요.");
            handleWaitingOrders(orders);
        }

    }

    // 완료할 것인지 확인 후 처리하는 부분
    private void confirmCompleteOrder(Order selectedOrder) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("아래 주문을 완료 처리 하시겠습니까?\n");

        printOrder(selectedOrder);

        System.out.println("1. 완료      2. 메뉴판");
        int confirm = scanner.nextInt();
        if(confirm==1){
            setCompleteOrder(selectedOrder); // 주문 완료 리스트에 넣기
            resetWaitingOrder(selectedOrder); // 대기 주문 리스트에서 빼기
            System.out.println("해당 주문을 완료 처리 하였습니다.");
            System.out.println("========================================");
            UserMenuHandler.displayMainMenu();
        }else if(confirm==2){
            System.out.println("========================================");
            UserMenuHandler.displayMainMenu();
        }else {
            System.out.println("잘못된 입력입니다.");
            confirmCompleteOrder(selectedOrder);
        }
    }
    /**         완료된 주문 리스트에 추가/삭제 함수들           */

    // 완료된 주문 리스트에 추가하는 부분
    private void setCompleteOrder(Order selectedOrder) {
        Order order = new Order();
        Date now = new Date();

        List<Item> it = new ArrayList<>();
        for(Item its : selectedOrder.getOrderItems()){
            it.add(its);
        }

        order.setOrderItems(it);
        order.setTotalPrice(selectedOrder.getTotalPrice());
        order.setRequestContent(selectedOrder.getRequestContent());
        order.setOrderDate(selectedOrder.getOrderDate()); // 주문 일시
        order.setCompleteDate(now); // 완료 주문 일시
        order.setOrderNum(selectedOrder.OrderNum);
        menuContext.addToCompleteOrder(order);
    }

    // 대기 중인 리스트에서 삭제하는 부분
    private void resetWaitingOrder(Order selectedOrder) {
        menuContext.getWaitingOrders().remove(selectedOrder);
    }

    // 완료된 주문 목록 출력
    private void printCompletedOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("처리 완료된 주문 목록입니다.\n");

        System.out.println("[ 완료 주문 목록 ]");
        if(!menuContext.getCompletedOrders().isEmpty()) {
            printOrders(menuContext.getCompletedOrders());
        }else{
            System.out.println("완료된 주문이 없습니다.");
        }// if~else() of the end
        System.out.println("========================================");

        System.out.println("1. 메뉴판");
        int input = 0;
        try{
            input = scanner.nextInt();
        }catch (Exception e){
            System.out.println("잘못된 요청입니다. 다시 입력하세요!");
            printCompletedOrder();
        }
        if(input==1){
            System.out.println("========================================");
            UserMenuHandler.displayMainMenu();
        }else {
            System.out.println("잘못된 입력입니다.");
            System.out.println("========================================");
            printCompletedOrder();
        }
    }
    //삭제할 상품 찾기
    public boolean findDeleteMenu(int menu, String name) {
        ArrayList<String> menuNames = new ArrayList<>(Arrays.asList("Burgers", "Frozen Custard", "Drinks", "Beer"));
        for (Item item : menuContext.getMenuItems(menuNames.get(menu - 1))) {
            if (item.getName().equals(name)) {
                if (!menuContext.getMenuItems(menuNames.get(menu - 1)).isEmpty()) {
                    menuContext.deleteItemFromMenu(menuNames.get(menu - 1), item);
                    return true;
                }
                else return false;
            }
        }
        return false;
    }

    //메인 페이지로 돌아가거나 다시 상품 삭제를 할지 묻는 부분
    private int wantToMainOrAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. 메뉴판  2. 상품삭제");

        int result = scanner.nextInt();

        return result;
    }
    //상품 생성
    private void createItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("새로운 상품 정보를 입력해주세요.");
        System.out.print("메뉴: ");
        int menu = 0;
        try{
            menu = scanner.nextInt();
        }catch (Exception e){
            System.out.println("잘못된 요청입니다. 다시입력해주세요!");
            createItem();
        }
        scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();
        System.out.print("설명: ");
        String description = scanner.nextLine();
        System.out.print("가격: ");
        Double price = 0.0;
        try{
            price = scanner.nextDouble();
        }catch (Exception e){
            System.out.println("잘못된 요청입니다. 다시입력해주세요!");
            createItem();
        }

        // 새로운 상품 생성
        Item newItem = new Item(name, price, description);

        switch (menu) {
            case 1:
                menuContext.addItemToMenu("Burgers", newItem);
                break;
            case 2:
                menuContext.addItemToMenu("Frozen Custard", newItem);
                break;
            case 3:
                menuContext.addItemToMenu("Drinks", newItem);
                break;
            case 4:
                menuContext.addItemToMenu("Beer", newItem);
                break;
            default:
                System.out.println("잘못된 메뉴입니다.");
                break;
        }

        System.out.println("새로운 상품이 생성되었습니다.");
        UserMenuHandler.displayMainMenu();
    }

    //상품 삭제
    private void deleteItem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("삭제할 상품 정보를 입력해주세요.");
        System.out.print("메뉴: ");
        int menu = 0;
        try{
            menu = scanner.nextInt();
        }catch (Exception e){
            System.out.println("잘못된 요청입니다. 다시 입력하세요.");
            deleteItem();
        }
        scanner.nextLine();

        System.out.print("이름: ");
        String name = scanner.nextLine();

        int menuSize = menuContext.getMenus("Main").size();

        if (menu >= 1 && menu <= menuSize) {
            boolean isMenuExist = findDeleteMenu(menu, name);
            if (isMenuExist) {
                System.out.println("상품이 삭제되었습니다.\n");
                UserMenuHandler.displayMainMenu();
            }
            else {
                System.out.println("해당 상품이 존재하지 않습니다.\n");
                int wantResult = wantToMainOrAgain();
                if(wantResult==2) {
                    deleteItem();
                }else {
                    UserMenuHandler.displayMainMenu();
                }
            }
        } else {
            System.out.println("잘못된 메뉴입니다.");
            deleteItem();
        }
    }
}