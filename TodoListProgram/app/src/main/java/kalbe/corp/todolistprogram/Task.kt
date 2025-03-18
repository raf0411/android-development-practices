package kalbe.corp.todolistprogram

import java.util.Calendar

data class Task(
    val name : String,
    var status : String = "Pending",
    val date : Calendar = Calendar.getInstance(),
)
