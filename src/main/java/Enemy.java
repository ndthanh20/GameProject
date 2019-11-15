import javafx.scene.image.ImageView;

public class Enemy extends GameEntity {
    private int[][] path;
    private boolean moveX= true;
    private boolean upY=true;
    private boolean pathFinished=false;
    private ImageView enemyImage;

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

    public void updateLocation(){
        if(moveX){
            enemyImage.setX(enemyImage.getX()+1);
            int X=(int) (enemyImage.getX());
            int Y=(int) (enemyImage.getY());
            if((X+1)%100== 1 )
                if(path[(int)(Y)/100][(int)((X+1)/100)+1] != 1)
                {

                    moveX=false;
                    if(path[(int)(Y/100)+1][(int)(X+1)/100]==1)
                    {
                        System.out.println(X+" "+Y+" "+upY);
                        upY=false;
                    }
                    if(path[(int)(Y/100)-1][(int)(X+1)/100]==1)
                    {
                        System.out.println(X+" "+Y+" "+upY);
                        upY=true;
                    }

                }
            System.out.println(enemyImage.getX());
            if(enemyImage.getX()==1100) pathFinished=true;
        }
        else{
            if(upY){
                enemyImage.setY(enemyImage.getY()-1);
                int X=(int) (enemyImage.getX());
                int Y=(int) (enemyImage.getY());
                if((Y-1)%100!=0)
                    if(path[(int)(Y-1)/100][(int)(X)/100] !=1){
                        moveX=true;
                    }

            }
            else{
                enemyImage.setY(enemyImage.getY()+1);
                int X=(int) (enemyImage.getX());
                int Y=(int) (enemyImage.getY());
                if((Y+1)%100!=0)
                    if(path[(int)((Y+1)/100)+1][(int)(X)/100]!= 1)
                    {
                        moveX=true;
                        //System.out.println(enemyImage.getX()+" "+enemyImage.getY());
                    }
            }
        }

    }

}
