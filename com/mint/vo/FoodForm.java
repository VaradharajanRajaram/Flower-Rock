package mint.vo;

import java.io.Serializable;

public class FoodForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String foodName;

    private int foodCost;

    private String foodImg;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(int foodCost) {
        this.foodCost = foodCost;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }
}
