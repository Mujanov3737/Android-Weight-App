# Project Demo
## Login Screen
![anim1](https://github.com/Mujanov3737/Android-Weight-App/assets/75598761/3d1cddb9-82e4-437e-9622-853f8a193db4)

## Adding Weight (or Note)
![anim2](https://github.com/Mujanov3737/Android-Weight-App/assets/75598761/98c8580a-5dfb-46cc-8f5b-8855b1aa702c)

## Deleting Weight (or Note)
![anim3](https://github.com/Mujanov3737/Android-Weight-App/assets/75598761/03ffdbdc-2d53-40c9-b619-419973fbc26d)

## Areas for Improvement
The application certainly does not offer much in terms of robust features seen in professional mobile applications. Many weight trackers provide graphical data based on analytics and servers to store user data, rather than just locally on a user's device. Additionally, some features such as notifications, input validation, tracking changes, and animations are not fully implemented. Ultimately, the application simply stores string data for a user and functions much like a simple note taking or "to do" application.

# Weight Tracking Application
This application is a fairly simple weight tracking application designed for use in mobile android devices and intended to allow users to enter weights and a corresponding date which is stored in a database to be presented to the user in a list view. However, the application could just as easily be used to store and track any sort of string-based data, and could function similarly to a note-taking application.

As the functionality of the app is fairly simple, the user interface was designed to make it obvious for the user to navigate around the app without needing any instruction. The login screen provides clear indications for creating an account and logging in, with appropriate messages alerting the user when their inputs are invalid. Upon logging in, the only initial area the screen can be interacted with is a plus symbol which, when selected, prompts the user to enter a date and a weight. Once confirmed, this the screen is returned to the list view where the user will see the entry they had just saved along with providing functionality to edit or delete this entry simply by selecting it from the view.

Regarding strategies used to approach the coding process of this application, care was first taken in determining what features might want to be included in the app and what constructs could be used to facilitate the functionality of those features. For example, knowing that before entries could be presented to the user in the graphical interface, they must first be stored inside of a database. From there, exploration into what database solutions are most appropriate for the application and can also be implemented through Android Studio was done to determine using SQLite was a worthwhile option. Following that determination, it was a matter of understanding the paradigms used to implement this solution into working functionality within the context of the app, such as how to instantiate databases, populating the databases with data entered by the user, using CRUD operations, syntax with SQL, and so on.

Testing the application was done strictly through the use of the virtual device manager included in android studio, so it could be said that the entirety of app testing consisted of manual testing. The device manager tool allows for the emulation of an android device, such as a Pixel 2 which was used as the targeted testing device throughout development. As new features were implemented, the emulated device was “booted” and the application was run on the emulated platform to ensure the desired functionality was achieved.

Overall, this project was a valuable learning experience that granted significant insight into the development process of mobile applications. Android Studio offers a rich suite of tools and libraries that make achieving robust, working features more streamlined. Once a developer can understand the foundational concepts of how an Android app is built, these tools can be leveraged easily with the documentation provided.

