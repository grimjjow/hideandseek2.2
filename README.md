# Overview of the code

## Environment 
    
    We have a basic interface to define main variables and functions of the environment.
    
    Abtract class Area stores parameters for each type of a the space like, in our case 
    it's square(in the future we might add more types if needed). Also it generates a 
    collection of squares to fill in the grid.
    
    Class Map is just a type of an Area, basically defines walkable space of the environment.
    
    Class Square simply has parameters and some functions of a square.
    
    Class Wall is an example of a different type of Area in comparison to Map.
    
    Class Grid forms a basic environment of squares.
    
    Class FunctionalEnvironment spawns guards on the map and places the grid.
    
    // TODO
    
    // create more classes(Door, Staircase,...) and add functionality
    
    // implement collision detection(how to make guards turn when the encounter an obstacle?)
    
    // improve implementation of movement (ex.make the guards explore along the wall)
    
    // improve grid implemetation if needed
    
    // come up with more tasks and do them(all contribution is highly appraciated)
    
    
## Agent

    Basic interface Agent, again, simply defines agent.
    
    Class Guard specifies a guard type of agent, includes calculations of next move(step) and
    updates the position.
    
    // TODO
    
    // implement other functionalities of a guard(Vision...)
    
    // implement search algorithm(A*, QLearning...)
    
    // again think about other things that need to be done and add them
    
## Maths

    Algebra classe just stores functions for different calculations that we might need.
    
    Vector, default class, nothing more to add.
    
## Graphics

    Right now the GUI implementation is very simple and very bad, I'll work on it.
    
    
    
    
    

