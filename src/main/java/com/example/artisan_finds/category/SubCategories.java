package com.example.artisan_finds.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum SubCategories {

    KeychainLanyards,
    HatsCaps,


    ScarvesWraps,


    PatchesPins,


    HairAccessories,


    BeltsBraces;


//    public static List<SubCategories>getSubCategories(CategoryType categoryType){
//
//     return    Arrays.stream(SubCategories.values()).filter(subCategories -> subCategories.getCategoryType()==categoryType).toList();
//    }
}
