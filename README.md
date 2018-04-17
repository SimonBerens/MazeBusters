## Personnel:
  Berens, Simon
  
  Eckert, Lucien

## The Problem: 
	Is there a path through a maze starting and ending at a designated point.
	
## Known Bugs:
	-Nothing actually works yet

## Recursive Abstraction: 
	When I am asked if there is there a path through a maze starting and ending at a designated point, the recursive abstraction can find if there is a path through a maze starting at a point one step further than the current point and ending at a designated point.

## Base Case:
  * is the explorer on the treasure?
  * is the explorer on a wall?
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
  * Tile[][] Maze
  * MAX_RANKS
  * rankCount
  * Explorer position
  * Tile enum
  * Direction enum
* Methods
  * Constructor / Copy Constructor
  * Tile explorerIsOnA();
  * Maze go(Direction)
  * Maze dropA(Tile)

#### MazeSolver
 * Fields
   
 * Methods
   * solve(Maze)
   
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

