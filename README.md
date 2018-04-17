## Personnel:
  Berens, Simon
  
  Eckert, Lucien

## The Problem: 
	Is there a path through a maze starting and ending at a designated point.
	
## Known Bugs:
  * runtime error if you enter a non-integer when UserOfMazeSolver propmts you what rank and file you want to start at

## Recursive Abstraction: 
	When I am asked if there is there a path through a maze starting and ending at a designated point, the recursive abstraction can find if there is a path through a maze starting at a point one step further than the current point and ending at a designated point.

## Base Case:
  * is the explorer on the treasure?
  * is the explorer on a wall?
Code-sharing  [here](https://codeshare.io/GqlWpj)

## Version 0:
  * Returns boolean value of existence of solution

## Version 1:
  * Port directions and maze tiles to enums
  
  --Stuff we had time to add--
  
  * Now once the maze is solved MazeSolver will display the path
  * Testing is much much easier
  * Code is shorter

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
## Algorithm:
	solve(maze)
		if explorer is on the end point
			return true
		if explorer is on wall
			return false
		record snapshot s
		return solve(s but with explorer one tile north) ||
		       solve(s but with explorer one tile south) ||
		       solve(s but with explorer one tile east) || 
		       solve(s but with explorer one tile west)

