package com.slutsenko.cocktailapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "cocktail")
public class Cocktail implements Serializable {

    private String idDrink;
    @PrimaryKey
    @NonNull
    private String strDrink;
    private String strDrinkAlternate;
    private String strDrinkES;
    private String strDrinkDE;
    private String strDrinkFR;
    @SerializedName("strDrinkZH-HANS")
    private String strDrinkZHHANS; //strDrinkZH-HANS
    @SerializedName("strDrinkZH-HANT")
    private String strDrinkZHHANT; //strDrinkZH-HANT
    private String strTags;
    private String strVideo;
    private String strCategory;
    private String strIBA;
    private String strAlcoholic;
    private String strGlass;
    private String strInstructions;
    private String strInstructionsES;
    private String strInstructionsDE;
    private String strInstructionsFR;
    @SerializedName("strInstructionsZH-HANS")
    private String strInstructionsZHHANS;
    @SerializedName("strInstructionsZH-HANT")
    private String strInstructionsZHHANT;
    private String strDrinkThumb;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strCreativeCommonsConfirmed;
    private String dateModified;


    String getIngredients() {
        String allIngredients = "";
        String[] ingredients = new String[]{
                this.getStrIngredient1(), this.getStrIngredient2(), this.getStrIngredient3(),
                this.getStrIngredient4(), this.getStrIngredient5(), this.getStrIngredient6(),
                this.getStrIngredient7(), this.getStrIngredient8(), this.getStrIngredient9(),
                this.getStrIngredient10(), this.getStrIngredient11(), this.getStrIngredient12(),
                this.getStrIngredient13(), this.getStrIngredient14(), this.getStrIngredient15()};
        for (int i = 1; i <= ingredients.length; i++) {
            allIngredients = ingredients[i - 1] != null
                    ? allIngredients + i + ". " + ingredients[i - 1] + "\n" : allIngredients;
        }
        return allIngredients;
    }

    String getMeasures() {
        String allMeasures = "";
        String[] measures = new String[]{
                this.getStrMeasure1(), this.getStrMeasure2(), this.getStrMeasure3(),
                this.getStrMeasure4(), this.getStrMeasure5(), this.getStrMeasure6(),
                this.getStrMeasure7(), this.getStrMeasure8(), this.getStrMeasure9(),
                this.getStrMeasure10(), this.getStrMeasure11(), this.getStrMeasure12(),
                this.getStrMeasure13(), this.getStrMeasure14(), this.getStrMeasure15()};
        for (int i = 1; i <= measures.length; i++) {
            allMeasures = measures[i - 1] != null
                    ? allMeasures + measures[i - 1] + "\n" : allMeasures;
        }
        return allMeasures;
    }


    String getIdDrink() {
        return idDrink;
    }

    String getStrDrink() {
        return strDrink;
    }

    String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    String getStrDrinkES() {
        return strDrinkES;
    }

    String getStrDrinkDE() {
        return strDrinkDE;
    }

    String getStrDrinkFR() {
        return strDrinkFR;
    }

    String getStrDrinkZHHANS() {
        return strDrinkZHHANS;
    }

    String getStrDrinkZHHANT() {
        return strDrinkZHHANT;
    }

    String getStrTags() {
        return strTags;
    }

    String getStrVideo() {
        return strVideo;
    }

    String getStrCategory() {
        return strCategory;
    }

    String getStrIBA() {
        return strIBA;
    }

    String getStrAlcoholic() {
        return strAlcoholic;
    }

    String getStrGlass() {
        return strGlass;
    }

    String getStrInstructions() {
        return strInstructions;
    }

    String getStrInstructionsES() {
        return strInstructionsES;
    }

    String getStrInstructionsDE() {
        return strInstructionsDE;
    }

    String getStrInstructionsFR() {
        return strInstructionsFR;
    }

    String getStrInstructionsZHHANS() {
        return strInstructionsZHHANS;
    }

    String getStrInstructionsZHHANT() {
        return strInstructionsZHHANT;
    }

    String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    String getStrIngredient1() {
        return strIngredient1;
    }

    String getStrIngredient2() {
        return strIngredient2;
    }

    String getStrIngredient3() {
        return strIngredient3;
    }

    String getStrIngredient4() {
        return strIngredient4;
    }

    String getStrIngredient5() {
        return strIngredient5;
    }

    String getStrIngredient6() {
        return strIngredient6;
    }

    String getStrIngredient7() {
        return strIngredient7;
    }

    String getStrIngredient8() {
        return strIngredient8;
    }

    String getStrIngredient9() {
        return strIngredient9;
    }

    String getStrIngredient10() {
        return strIngredient10;
    }

    String getStrIngredient11() {
        return strIngredient11;
    }

    String getStrIngredient12() {
        return strIngredient12;
    }

    String getStrIngredient13() {
        return strIngredient13;
    }

    String getStrIngredient14() {
        return strIngredient14;
    }

    String getStrIngredient15() {
        return strIngredient15;
    }

    String getStrMeasure1() {
        return strMeasure1;
    }

    String getStrMeasure2() {
        return strMeasure2;
    }

    String getStrMeasure3() {
        return strMeasure3;
    }

    String getStrMeasure4() {
        return strMeasure4;
    }

    String getStrMeasure5() {
        return strMeasure5;
    }

    String getStrMeasure6() {
        return strMeasure6;
    }

    String getStrMeasure7() {
        return strMeasure7;
    }

    String getStrMeasure8() {
        return strMeasure8;
    }

    String getStrMeasure9() {
        return strMeasure9;
    }

    String getStrMeasure10() {
        return strMeasure10;
    }

    String getStrMeasure11() {
        return strMeasure11;
    }

    String getStrMeasure12() {
        return strMeasure12;
    }

    String getStrMeasure13() {
        return strMeasure13;
    }

    String getStrMeasure14() {
        return strMeasure14;
    }

    String getStrMeasure15() {
        return strMeasure15;
    }

    String getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    String getDateModified() {
        return dateModified;
    }

    void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    void setStrDrinkAlternate(String strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    void setStrDrinkES(String strDrinkES) {
        this.strDrinkES = strDrinkES;
    }

    void setStrDrinkDE(String strDrinkDE) {
        this.strDrinkDE = strDrinkDE;
    }

    void setStrDrinkFR(String strDrinkFR) {
        this.strDrinkFR = strDrinkFR;
    }

    void setStrDrinkZHHANS(String strDrinkZHHANS) {
        this.strDrinkZHHANS = strDrinkZHHANS;
    }

    void setStrDrinkZHHANT(String strDrinkZHHANT) {
        this.strDrinkZHHANT = strDrinkZHHANT;
    }

    void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    void setStrVideo(String strVideo) {
        this.strVideo = strVideo;
    }

    void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    void setStrIBA(String strIBA) {
        this.strIBA = strIBA;
    }

    void setStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
    }

    void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    void setStrInstructionsES(String strInstructionsES) {
        this.strInstructionsES = strInstructionsES;
    }

    void setStrInstructionsDE(String strInstructionsDE) {
        this.strInstructionsDE = strInstructionsDE;
    }

    void setStrInstructionsFR(String strInstructionsFR) {
        this.strInstructionsFR = strInstructionsFR;
    }

    void setStrInstructionsZHHANS(String strInstructionsZHHANS) {
        this.strInstructionsZHHANS = strInstructionsZHHANS;
    }

    void setStrInstructionsZHHANT(String strInstructionsZHHANT) {
        this.strInstructionsZHHANT = strInstructionsZHHANT;
    }

    void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    void setStrCreativeCommonsConfirmed(String strCreativeCommonsConfirmed) {
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
    }

    void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

}









