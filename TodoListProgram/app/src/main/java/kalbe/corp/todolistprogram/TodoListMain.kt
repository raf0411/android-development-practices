package kalbe.corp.todolistprogram

fun main(){
    val todoListProgram : TodoList = TodoList();
    var choice : Int = 0;

    do{
        displayMainMenu();
        choice = readln().toInt();

        when (choice){
            1 -> askAddTaskDetail(todoListProgram);
            2 -> askRemoveTaskDetail(todoListProgram);
            3 -> askMarkDoneTask(todoListProgram);
            4 -> displayViewList(todoListProgram);
            5 -> println("EXITED PROGRAM 0");
        }
    }while(choice != 5);
}

fun askAddTaskDetail(todoList : TodoList){
    var isNameCorrect : Boolean = false;

    println("ADD TASK");
    println("==================");

    do {
        print("Task name [must be between 5 and 100 characters]: ");
        val name : String = readln();

        if(name.length in 5..100){
            isNameCorrect = true;
            todoList.addTask(name);
        }

    } while (!isNameCorrect);
}

fun askRemoveTaskDetail(todoList: TodoList){
    var isIndexCorrect : Boolean = false;

    println("REMOVE TASK");
    println("==================");
    todoList.viewTasks();

    do{
        print("Task index to remove [index starts from 0 | must be a number]: ");
        val index = readln().toIntOrNull()

        if(index != null){
            isIndexCorrect = true;
            todoList.removeTask(index);
        }

    }while (!isIndexCorrect);
}

fun askMarkDoneTask(todoList: TodoList){
    var isIndexCorrect : Boolean = false;

    println("MARK TASK");
    println("==================");
    todoList.viewTasks();

    do{
        print("Task index to mark [index starts from 0 | must be a number]: ");
        val index = readln().toIntOrNull()

        if(index != null){
            isIndexCorrect = true;
            todoList.markTaskDone(index);
        }

    }while (!isIndexCorrect);
}

fun displayViewList(todoList: TodoList){
    println("YOUR TO-DOs")
    println("================");
    todoList.viewTasks();
    println("================");
    print("Press Enter to continue...");
    readln();
}

fun displayMainMenu(){
    println("TO-DO LIST CLI APP");
    println("==================");
    println("1. Add a task");
    println("2. Remove a task");
    println("3. Mark task as Done");
    println("4. View Tasks");
    println("5. Exit Program");
    print(">> ");
}