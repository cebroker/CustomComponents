package co.condorlabs.customcomponents.test

import androidx.core.content.ContextCompat
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import co.condorlabs.customcomponents.custombutton.ButtonState
import co.condorlabs.customcomponents.custombutton.CustomButton
import co.condorlabs.customcomponents.custombutton.CustomButtonStyleFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Oscar Gallon on 2019-04-26.
 */
@RunWith(AndroidJUnit4::class)
class CustomButtonTest : MockActivityTest() {

    private val buttonStyleFactory = CustomButtonStyleFactory()
    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_custom_button
    }

    
    
    fun shouldBeDefaultButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.white)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldBeSuccessButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.successColor)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)
        ruleActivity.runOnUiThread {
            button.setType("success")
        }

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldBeDangerButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.dangerColor)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)
        ruleActivity.runOnUiThread {
            button.setType("danger")
        }

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldBePrimaryButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.primaryColor)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)
        ruleActivity.runOnUiThread {
            button.setType("primary")
        }

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldBeInfoButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.infoColor)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)
        ruleActivity.runOnUiThread {
            button.setType("info")
        }

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldBeOverLayButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.white)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)
        ruleActivity.runOnUiThread {
            button.setType("overlay")
        }

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldBeFlatPrimaryButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.white)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)
        ruleActivity.runOnUiThread {
            button.setType("flatPrimary")
        }

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldBeWhiteShapeButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.transparent)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)
        ruleActivity.runOnUiThread {
            button.setType("shapeWhite")
        }

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldBeWarningButton() {
        restartActivity()

        // Given
        val expectedColor = ContextCompat.getColor(ruleActivity.activity, R.color.warningColor)
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)
        ruleActivity.runOnUiThread {
            button.setType("warning")
        }

        // When
        val backgroundColor = ContextCompat.getColor(
            ruleActivity.activity,
            buttonStyleFactory.getCustomColorFromType(button.getType()).backgroundColor
        )

        // Then
        Assert.assertEquals(expectedColor, backgroundColor)
    }

    
    
    fun shouldShowProgressDialog() {
        restartActivity()

        // Given
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)

        // When
        ruleActivity.runOnUiThread {
            button.changeState(ButtonState.Loading)
        }

        Thread.sleep(500)
        // Then
        Assert.assertTrue(button.text.isEmpty())
    }

    
    @Test
    fun shouldChangeButtonState() {
        restartActivity()

        // Given
        val button = ruleActivity.activity.findViewById<CustomButton>(R.id.btn)

        // When
        ruleActivity.runOnUiThread {
            button.changeState(ButtonState.Loading)
            Thread.sleep(500)
            button.changeState(ButtonState.Normal)
        }

        Thread.sleep(500)
        // Then
        Assert.assertTrue(button.text.isEmpty())
    }
}
