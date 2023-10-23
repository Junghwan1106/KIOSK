public class ShakeShackBurgerApplication {
    public static void main(String[] args) {
        MenuContext menuContext = new MenuContext();
        UserMenuHandler userMenuHandler = new UserMenuHandler(menuContext);

        // 처음 실행시 사용자 메뉴를 보여줍니다.
        userMenuHandler.displayMainMenu();
    }
}