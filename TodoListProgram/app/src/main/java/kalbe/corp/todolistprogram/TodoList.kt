package kalbe.corp.todolistprogram

import java.text.SimpleDateFormat
import java.util.Locale

class TodoList {
    private var todoList : MutableList<Task> = mutableListOf<Task>();

    fun addTask(name : String){
        val newTask : Task = Task(name);
        todoList.add(newTask);
        println("New task successfully added!");
        print("Press Enter to continue...");
        readln();
    }

    fun removeTask(index : Int){
        if(index !in 0 until todoList.size){
            println("Task not found!");
        } else{
            todoList.removeAt(index);
            println("Task successfully removed!");
        }

        print("Press Enter to continue...");
        readln();
    }

    fun viewTasks(){
        if (todoList.isEmpty()){
            println("No tasks yet...");
        } else {
            for(i in 0 until todoList.size){
                val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss", Locale.ENGLISH)
                val formattedDate = formatter.format(todoList[i].date.time)

                println("${i+1}. [${todoList[i].name} | ${todoList[i].status} | $formattedDate]");
            }
        }
    }

    fun markTaskDone(index: Int){
        if(index !in 0 until todoList.size){
            println("Task not found!");
        } else {
            todoList[index].status = "Done";
            println("Task successfully marked as ${todoList[index].status}");
        }

        print("Press Enter to continue...");
        readln();
    }
}