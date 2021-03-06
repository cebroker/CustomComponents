package co.condorlabs.customcomponents.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import co.condorlabs.customcomponents.*
import co.condorlabs.customcomponents.customtextview.CustomTextView
import co.condorlabs.customcomponents.test.util.withFontSize
import org.junit.Assert
import org.junit.Test

class CustomTextViewTest : MockActivityTest() {

    @SmallTest
    @Test
    fun shouldShowTextTypeTitle() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_title

        // When
        restartActivity()

        // Then
        onView(withId(R.id.tvTitle)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvTitle)).check(
            matches(
                withFontSize(16f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeSubtitle() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_subtitle

        // When
        restartActivity()

        // Then
        onView(withId(R.id.tvSubtitle)).check(matches(hasTextColor(R.color.subtitleColor)))
        onView(withId(R.id.tvSubtitle)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeDefault() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text

        // When
        restartActivity()

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.labelColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeSectionTitle() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_section_title

        // When
        restartActivity()

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeTitleProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(TITLE_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(16f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeSubtitleProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(SUBTITLE_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.subtitleColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeBodyProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(BODY_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.labelColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeSectionTitleProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(SECTION_TITLE_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeLink() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_link_text
        restartActivity()

        // Then
        onView(withId(R.id.tvLink)).check(matches(hasTextColor(R.color.primaryColor)))
        onView(withId(R.id.tvLink)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeLinkProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(LINK_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.primaryColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeTitleH1() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_h1_title

        // When
        restartActivity()

        // Then
        onView(withId(R.id.tvTitle)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvTitle)).check(
            matches(
                withFontSize(30f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeH1TitleProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(H1_TITLE_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(30f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeTitleH2() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_h2_title

        // When
        restartActivity()

        // Then
        onView(withId(R.id.tvTitle)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvTitle)).check(
            matches(
                withFontSize(24f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeH2TitleProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(H2_TITLE_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(24f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeTitleH3() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_h3_title

        // When
        restartActivity()

        // Then
        onView(withId(R.id.tvTitle)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvTitle)).check(
            matches(
                withFontSize(18f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeH3TitleProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(H3_TITLE_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(18f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldGetTexTypeTitle() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_title
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvTitle)

        // When
        val result = formField.getTextType()

        // Then
        Assert.assertEquals(TITLE_TYPE, result)
    }

    @SmallTest
    @Test
    fun shouldGetTexTypeSubtitle() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_subtitle
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvSubtitle)

        // When
        val result = formField.getTextType()

        // Then
        Assert.assertEquals(SUBTITLE_TYPE, result)
    }

    @SmallTest
    @Test
    fun shouldGetTexTypeSectionTitle() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_section_title
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        val result = formField.getTextType()

        // Then
        Assert.assertEquals(SECTION_TITLE_TYPE, result)
    }

    @SmallTest
    @Test
    fun shouldGetTexTypeLink() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_link_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvLink)

        // When
        val result = formField.getTextType()

        // Then
        Assert.assertEquals(LINK_TYPE, result)
    }

    @SmallTest
    @Test
    fun shouldGetTexTypeH1Title() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_h1_title
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvTitle)

        // When
        val result = formField.getTextType()

        // Then
        Assert.assertEquals(H1_TITLE_TYPE, result)
    }

    @SmallTest
    @Test
    fun shouldGetTexTypeH2Title() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_h2_title
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvTitle)

        // When
        val result = formField.getTextType()

        // Then
        Assert.assertEquals(H2_TITLE_TYPE, result)
    }

    @SmallTest
    @Test
    fun shouldGetTexTypeH3Title() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_h3_title
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvTitle)

        // When
        val result = formField.getTextType()

        // Then
        Assert.assertEquals(H3_TITLE_TYPE, result)
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeSectionBody() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_section_body

        // When
        restartActivity()

        // Then
        onView(withId(R.id.tvTitle)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvTitle)).check(
            matches(
                withFontSize(14f)
            )
        )
    }

    @SmallTest
    @Test
    fun shouldShowTextTypeSectionBodyProgrammatically() {
        // Given
        MockActivity.layout = R.layout.activity_custom_text_view_default_text
        restartActivity()
        val formField = ruleActivity.activity.findViewById<CustomTextView>(R.id.tvDefault)

        // When
        ruleActivity.runOnUiThread {
            formField.setCustomTextViewType(SECTION_BODY_TYPE)
        }

        // Then
        onView(withId(R.id.tvDefault)).check(matches(hasTextColor(R.color.textColor)))
        onView(withId(R.id.tvDefault)).check(
            matches(
                withFontSize(14f)
            )
        )
    }
}
