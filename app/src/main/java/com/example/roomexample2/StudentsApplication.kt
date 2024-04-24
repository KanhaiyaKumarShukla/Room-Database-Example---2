package com.example.roomexample2

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


// You want to have only one instance of the database and of the repository in your app. An easy way to achieve this is by creating them as members of the Application class.
// Then they will just be retrieved from the Application whenever they're needed, rather than constructed every time. It will extends Application().
class StudentsApplication: Application() {
    // SupervisorJob(): This is a type of coroutine job. A SupervisorJob is a job that is used to manage other coroutines. If one of the coroutines in the supervisor job fails,
    // it doesn't affect the others. It's useful for scenarios where you want to isolate the failure of one coroutine from others.
    private val applicationScope=CoroutineScope(SupervisorJob())

    // Because these objects should only be created when they're first needed, rather than at app startup, we're using  "by lazy".
    // creating database instance.
    private val database by lazy {StudentDataBase.getDatabase(this, applicationScope)}

    //creating repository instance.
    val repository by lazy {StudentRepository.getInstance(database.studentDao())}
}
/*
Now that you created the Application class, update the AndroidManifest file and set "StudentsApplication" as application "android:name".
eg.
<application
android:name=".WordsApplication"
android:allowBackup="true"
android:icon="@mipmap/ic_launcher"
android:label="@string/app_name"
android:roundIcon="@mipmap/ic_launcher_round"
android:supportsRtl="true"
android:theme="@style/AppTheme">
...
*/