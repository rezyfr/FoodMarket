package com.rezyfr.foodmarket.core.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rezyfr.foodmarket.core.ui.R

val fonts = FontFamily(
    Font(R.font.eudoxus_regular, weight = FontWeight.Normal),
    Font(R.font.eudoxus_bold, weight = FontWeight.Bold),
    Font(R.font.eudoxus_semibold, weight = FontWeight.SemiBold),
)
@Immutable
data class CustomTypography(
    val title: Title,
    val body: Body,
    val caption: Caption
) {
    data class Title(
        val hero: TextStyle,
        val extraLarge: TextStyle,
        val large: TextStyle,
        val moderate: Moderate,
        val small: Small,
        val tiny: Tiny,
    )

    data class Body(
        val moderate: Moderate,
        val small: Small,
    )

    data class Caption(
        val moderate: Moderate,
        val small: Small
    )

    data class Moderate(
        val regular: TextStyle,
    ) {
        val demi: TextStyle get() = regular.copy(fontWeight = FontWeight.SemiBold)
        val book: TextStyle get() = regular.copy(fontWeight = FontWeight.Normal)
    }

    data class Small(
        val regular: TextStyle,
    ) {
        val demi: TextStyle get() = regular.copy(fontWeight = FontWeight.SemiBold)
        val bold: TextStyle get() = regular.copy(fontWeight = FontWeight.Bold)
    }

    data class Tiny(
        val regular: TextStyle,
    ) {
        val demi: TextStyle get() = regular.copy(fontWeight = FontWeight.SemiBold)
        val bold: TextStyle get() = regular.copy(fontWeight = FontWeight.Bold)
    }
}

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        body = CustomTypography.Body(
            moderate = CustomTypography.Moderate(
                TextStyle.Default
            ),
            small = CustomTypography.Small(
                TextStyle.Default
            ),
        ),
        caption = CustomTypography.Caption(
            moderate = CustomTypography.Moderate(
                TextStyle.Default
            ),
            small = CustomTypography.Small(
                TextStyle.Default
            ),
        ),
        title = CustomTypography.Title(
            hero = TextStyle.Default,
            extraLarge = TextStyle.Default,
            large = TextStyle.Default,
            moderate = CustomTypography.Moderate(
                TextStyle.Default
            ),
            small = CustomTypography.Small(
                TextStyle.Default
            ),
            tiny = CustomTypography.Tiny(
                TextStyle.Default
            ),
        )
    )
}

val foodMarketTypography = CustomTypography(
    title = CustomTypography.Title(
        hero = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 44.sp
        ),
        extraLarge = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 36.sp
        ),
        large = TextStyle(
            fontFamily = fonts,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            lineHeight = 28.sp
        ),
        moderate = CustomTypography.Moderate(
            regular = TextStyle(
                fontFamily = fonts,
                fontSize = 18.sp,
                lineHeight = 24.sp
            ),
        ),
        small = CustomTypography.Small(
            regular = TextStyle(
                fontFamily = fonts,
                fontSize = 16.sp,
                lineHeight = 20.sp
            ),
        ),
        tiny = CustomTypography.Tiny(
            regular = TextStyle(
                fontFamily = fonts,
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
        ),
    ),
    body = CustomTypography.Body(
        moderate = CustomTypography.Moderate(
            regular = TextStyle(
                fontFamily = fonts,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Normal
            ),
        ),
        small = CustomTypography.Small(
            regular = TextStyle(
                fontFamily = fonts,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Normal
            ),
        ),
    ),
    caption = CustomTypography.Caption(
        moderate = CustomTypography.Moderate(
            regular = TextStyle(
                fontFamily = fonts,
                fontSize = 13.sp,
                lineHeight = 16.sp,
            ),
        ),
        small = CustomTypography.Small(
            regular = TextStyle(
                fontFamily = fonts,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Normal
            ),
        ),
    )
)