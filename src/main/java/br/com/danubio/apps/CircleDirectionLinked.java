
package br.com.danubio.apps;

/**
*
*   Esta classe adiciona e organiza os Direction
*   Uma questão muito importante: As tartarugas estão
*   deitadas na praia, entretanto estão de costas para o
*   usuário do programa. A direita da tartaruga também
*   fica a direita do ususário.
*   É necessário adicionar as direções em ordem
*   NORTH, WEST, SOUTH and EAST
*
*/

public class CircleDirectionLinked {

    private Direction rootDirection;
    
    public CircleDirectionLinked(Direction direction)
    {
        this.rootDirection = direction;
        this.rootDirection.setDirectionLeft(direction);
        this.rootDirection.setDirectionRight(direction);
    }
    
    public void addDirection(Direction direction)
    {
        Direction rightDirection = rootDirection.getDirectionRight();
        
        rootDirection.setDirectionRight(direction);
        direction.setDirectionLeft(rootDirection);
        direction.setDirectionRight(rightDirection);
        rightDirection.setDirectionLeft(direction);
    }
    
    public Direction getDirection()
    {
        return rootDirection;
    }
    
}