BeyondgOOD Project
Gauri and Ashley

Todo List:
- Cleanup Model
    - direct and indirect cyclic inferences
- Assignment 6 things
    - update main() method to handle textual and jswing views
        - -in some-filename -save some-new-filename
        - -in some-filename -gui
        - -gui // opens an empty blank new spreadsheet
    - create a JAR file
    - Textual View
        - testing
    - Visual View
        - Grid of cells
        - Header cells and editable cells must be distinguishable
        - Values of each cell 
        - Row number
        - Scrollable (JScrollPane) 
            - Consider whether we need to make a custom scroll-pane
        - Custom frame class that extends JFrame and View interface
        - Override getPreferredSize and paintComponent in any custom JPanels
        - Steps to take (iterative process):
            1. get something to show up in the view (literally anything)
            2. render a blank worksheet
            3. start populating cells
        - Things to keep in mind:
            1. don't make every cell a separate entity or else it will crash by 100x100
            2. if you have buttons to add rows + cells then we can just use JScrollPane
                - can't have infinite scroll on it, so we can add a buffer on the number of cells in the worksheet and then
                  just have the user press the buttons to add the cells
            3. they will not be checking for tests on this version of the view, because it will depend solely on how the user 
                can interact with the view
         
Timeline:
- Model to be completely functioning (Friday)      
- Learn JSwing (Friday)
- Have both views done by Tuesday (11/12) 
- 11/13 and 11/14 should be to debug and pass JUnit tests
 
    
