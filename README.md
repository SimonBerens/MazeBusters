## Personnel:
  Berens, Simon
  
  Eckert, Lucien

## The Problem: Is there a path through a maze starting and ending at a designated point.

## Recursive Abstraction: When I am asked if there is there a path through a maze starting and ending at a designated point, the recursive abstraction can find if there is a path through a maze starting at a point one step further than the current point and ending at a designated point.

Code-sharing  [here](https://codeshare.io/GqlWpj)

## Version 0:
  * Returns boolean value of existence of solution

## Version n-1 Wishlist:
  * Return all solutions

## Version n Wishlist:
  * Shortest path
  * Set of moves

## Classes:
#### Maze 
* Fields
  * 2-D array for points (maybe in vn just store the turning points) )
  * Current location
* Methods
  * getCurLoc()
  * movePos(direction)
  * markPtAsVisited()
  * markPtAsBacktracked()
  * findMoves()

#### Solver
 * Fields
   * Maze
 * Methods
  * solve()
