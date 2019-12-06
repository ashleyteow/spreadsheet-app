// Create a README explaining which files you changed for your customers, and why the changes were needed.

In our final zip of code we sent to our customer group, we created a WorksheetViewModel interface that our IWorksheet
interface extended. We also changed our ReadWorksheet class to implement this WorksheetViewModel instead.
Subsequently, the classes that implement WorksheetView all hold an instance of a WorksheetViewModel object to draw data from.
We figured this would make it easier for them to design their adapter class to work with our views.

