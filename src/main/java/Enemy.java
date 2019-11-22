import javafx.scene.image.ImageView;

public class Enemy extends GameEntity {
    private int[][] path;
    private boolean moveX= true;
    private boolean upY=true;
    private boolean pathFinished=false;
    private ImageView enemyImage;
    private int healthPoints;                   // Determines if the monster is still alive
    private boolean isDead;
    private boolean isPathFinished;

    public Enemy(int healthPoints){
        this.healthPoints=healthPoints;
        isDead=false;
        isPathFinished=false;
    }

    public void setPath(int[][] path){
        this.path=path;
    }
    public void setEnemyImage(ImageView enemyImage){
        this.enemyImage=enemyImage;
    }
    public ImageView getEnemyImage(){
        return enemyImage;
    }
    public boolean getPathFinished(){
        return pathFinished;
    }

    public double getCenterX(){
        return enemyImage.getX()+50;
    }
    public double getCenterY(){
        return enemyImage.getY()+50;
    }

    public void updateLocation(){
        if(moveX){
            enemyImage.setX(enemyImage.getX()+1);
            int X=(int) (enemyImage.getX());
            int Y=(int) (enemyImage.getY());
            if((X+1)%100== 1 )
                if(path[Y /100][((X+1)/100) +1] != 1)
                {

                    moveX=false;
                    if(path[(Y/100) +1][(X+1) /100]==1)
                    {
                        upY=false;
                    }
                    if(path[(Y/100) -1][(X+1) /100]==1)
                    {
                        upY=true;
                    }
                    if(path[(Y/100) +1][(X+1) /100]!=1&&path[(Y/100) -1][(X+1) /100]!=1){
                        isPathFinished=true;
                    }

                }
            if(enemyImage.getX()==1100) pathFinished=true;
        }
        else{
            if(upY){
                enemyImage.setY(enemyImage.getY()-1);
                int X=(int) (enemyImage.getX());
                int Y=(int) (enemyImage.getY());
                if((Y-1)%100!=0)
                    if(path[(Y-1) /100][X /100] !=1){
                        moveX=true;
                    }

            }
            else{
                enemyImage.setY(enemyImage.getY()+1);
                int X=(int) (enemyImage.getX());
                int Y=(int) (enemyImage.getY());
                if((Y+1)%100!=0)
                    if(path[((Y+1)/100) +1][X /100]!= 1)
                    {
                        moveX=true;
                        //System.out.println(enemyImage.getX()+" "+enemyImage.getY());
                    }
            }
        }

    }
    public void takeDamage(int damage){
        healthPoints = healthPoints - damage;
        if (healthPoints <= 0){
            isDead = true;
            pathFinished = false;
        }

    }
    public boolean isDead(){
        //System.out.println(healthPoints);
        return isDead;
    }

    public int getHealthPoints(){
        return healthPoints;
    }
    public boolean isPathFinished(){
        return isPathFinished;
    }

}
