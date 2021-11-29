package opa21intro;

public class Main {

    public static void main(String[] args) {

        TodoList todoList = new TodoList();

        todoList.addItem("handla");
        todoList.addItem("göra läxan");
        todoList.addItem("diska");
        todoList.addItem("laga mat");
        todoList.addItem("vila");
        todoList.addItem("jogga");

        todoList.showMenu();

        /*System.out.println(todoList.items);

        System.out.println(todoList.getIndexOf("vila"));

        todoList.addToTopOfList("plugga");

        System.out.println(todoList.items);

        System.out.println(todoList.removeFromBottomOfList());

        System.out.println(todoList.items);

        System.out.println(todoList.removeFromTopOfList());

        System.out.println(todoList.items);

        System.out.println(todoList.removeFromListByIndex(2));

        System.out.println(todoList.items);

        System.out.println(todoList.removeFromListByName("göra läxan"));

        System.out.println(todoList.items);

        todoList.addItem("jogga");

        System.out.println(todoList.removeFromListAndAddToDone("jogga"));

        System.out.println(todoList.moveToTop("vila"));

        System.out.println(todoList.moveToBottom("handla"));

        todoList.moveDown("laga mat");

        System.out.println(todoList.items);

        todoList.moveUp("laga mat");

        System.out.println(todoList.items);*/
    }
}
