package com.example.artisan_finds.category;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
@RequiredArgsConstructor
public enum CategoryType {

    ACCESSORIES(
            Set.of()
    ),
    ART(
            Set.of()
    ),
    BABY(
            Set.of()
    ),
    BAGS(
            Set.of()
    ),
    CLOTHING(
            Set.of()
    ),
    GIFTS(
            Set.of()
    );

    @Getter
private final Set<SubCategories>subCategories;

}
