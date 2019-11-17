BeyondgOOD Project
Gauri and Ashley

Goal of HWK7: Complete spreadsheet application
- ability to add cell
- ability to remove cell
- ability to modify cell 

Todo List:
- View
    - make view scrollable
- HWK7
    - Step 0: Cleanup design
        - ensure interface methods are able to be used in both textual and visual views (otherwise throwing UnsupportedOperationException will suffice)
        - implement a single controller
    - Step 1: Adding editing controls to a new view
        - create a new class that contains existing visual view as a component within it (composite view) and adds the following abilities:
           - ability for user to select individual cell (highlight cell when selected)
            - implement toolbar to show raw value, while the cell will display the evaluated value  
            - allowing a user to edit formula & confirm / reject the edit
        - update main class to handle -edit (this command should open the editor view)
    - Step 2: Editing arbitrary cells
        - add infinity scrolling to editor view
        - computed values of any edited cells must be recomputed and redisplayed immediately
        implement whatever controller, keyboard and mouse handlers you need to support
        - document further changes to models and views in a README.txt
    - TESTING:
        - using Runnables to test keyboard handlers
        - testing controller
    - Things to keep in mind:
        - application should provide visual cues about what input is expected. i.e. user interface should be discoverably
        - application should provide visual cues about the effect of user's input 
        - interactions must user-friendly and intuitive
    - Extra credit:
        - ability to use Delete key to clear a cell's contents
    - Submission:
        - src and test folders
        - resources folder
            - README.txt with documented changes from HWK5
            - JAR file
            - spreadsheet file that computes the first 50 triangular numbers using the standard recursive formula T(1) = 1, T(n) = T(n - 1) + n
                - submit a screenshot of editor view, rendering this file (it should be displaying the cell containing T(10) with the formula for T(10) visible
            
        
           
    
    
         
Timeline:


 
    
