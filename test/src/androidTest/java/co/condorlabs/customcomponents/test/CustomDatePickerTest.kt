package co.condorlabs.customcomponents.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import android.view.View
import co.condorlabs.customcomponents.DATE_PICKER_MAX_YEAR
import co.condorlabs.customcomponents.DATE_PICKER_MIN_YEAR
import co.condorlabs.customcomponents.EMPTY
import co.condorlabs.customcomponents.PropertyNotImplementedException
import co.condorlabs.customcomponents.customedittext.EditTextMonthYearField
import co.condorlabs.customcomponents.test.util.clickDrawable
import co.condorlabs.customcomponents.test.util.setNumberPickerValue
import co.condorlabs.customcomponents.test.util.text
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class CustomDatePickerTest : MockActivityTest() {

    private var editTextRef = withId(R.id.etMonthYear)
    private var dpMonthRef = withId(R.id.monthPicker)
    private var dpYearRef = withId(R.id.yearPicker)
    private var editText: EditTextMonthYearField? = null

    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_customdatepicker_test
        restartActivity()
        editText = ruleActivity.activity.findViewById<View>(R.id.etMonthYearField) as? EditTextMonthYearField
    }

    @SmallTest
    
    fun shouldFormatOnTextChanged() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("0"))
        // Then
        Assert.assertEquals("0M/YYYY", editText?.text())
        // When
        onView(editTextRef).perform(ViewActions.typeText("8"))
        // Then
        Assert.assertEquals("08/YYYY", editText?.text())
        // When
        onView(editTextRef).perform(ViewActions.typeText("2"))
        // Then
        Assert.assertEquals("08/2YYY", editText?.text())
        // When
        onView(editTextRef).perform(ViewActions.typeText("0"))
        // Then
        Assert.assertEquals("08/20YY", editText?.text())
        // When
        onView(editTextRef).perform(ViewActions.typeText("0"))
        // Then
        Assert.assertEquals("08/200Y", editText?.text())
        // When
        onView(editTextRef).perform(ViewActions.typeText("8"))
        // Then
        Assert.assertEquals("08/2008", editText?.text())
    }

    @SmallTest
    
    fun shouldNotAllowMonthGreaterThan12() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("99/2001"))
        // Then
        Assert.assertEquals("12/2001", editText?.text())
    }

    @SmallTest
    
    fun shouldNotAllowMonthEqualsToZero() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("00/2001"))
        // Then
        Assert.assertEquals("01/2001", editText?.text())
    }

    @SmallTest
    
    fun shouldNotAllowYearLessThanMinYear() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("01/0000"))
        // Then
        Assert.assertEquals("01/$DATE_PICKER_MIN_YEAR", editText?.text())
    }

    @SmallTest
    
    fun shouldNotAllowYearGreaterThanMaxYear() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("01/5000"))
        // Then
        Assert.assertEquals("01/$DATE_PICKER_MAX_YEAR", editText?.text())
    }

    @SmallTest
    
    fun shouldNotAllowTypeText() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("a"))
        // Then
        Assert.assertEquals("", editText?.text())
    }

    @SmallTest
    
    fun shouldReturnIsValid() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("01/2008"))
        // Then
        Assert.assertEquals(true, editText?.isValid()?.isValid)
    }

    @SmallTest
    
    fun shouldNotAllowMoreThan4DigitsYear() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("01/20080"))
        // Then
        Assert.assertEquals(7, editText?.text()?.length)
        Assert.assertEquals(true, editText?.isValid()?.isValid)
    }

    @SmallTest
    
    fun shouldNotAllowMoreThan2DigitsMonth() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("021/2008"))
        // Then
        Assert.assertEquals(7, editText?.text()?.length)
        Assert.assertEquals(true, editText?.isValid()?.isValid)
    }

    @SmallTest
    
    fun shouldReturnIsNotValidIfFieldIsEmptyAndIsRequired() {
        // Given
        editText?.isRequired = true
        // When
        onView(editTextRef).perform(ViewActions.typeText(""))
        // Then
        Assert.assertEquals(false, editText?.isValid()?.isValid)
    }

    
    fun shouldReturnIsValidIfFieldIsEmptyAndIsNotRequired() {
        // Given
        editText?.isRequired = false
        // When
        onView(editTextRef).perform(ViewActions.typeText(""))
        // Then
        Assert.assertEquals(true, editText?.isValid()?.isValid)
    }

    
    fun shouldNotAllowYearGreaterThanUpperLimit() {
        // Given
        editText?.upperLimit = Calendar.getInstance().apply {
            set(2007, Calendar.JULY, 1)
        }
        // When
        onView(editTextRef).perform(ViewActions.typeText("01/2008"))
        // Then
        val result = editText?.isValid()
        Assert.assertEquals(false, result?.isValid)
        Assert.assertEquals("The Enter some text must be before 7/2007", result?.error)
    }

    
    fun shouldNotAllowMonthGreaterThanUpperLimit() {
        // Given
        editText?.upperLimit = Calendar.getInstance().apply {
            set(2007, Calendar.JULY, 1)
        }
        // When
        onView(editTextRef).perform(ViewActions.typeText("11/2007"))
        // Then
        val result = editText?.isValid()
        Assert.assertEquals(false, result?.isValid)
        Assert.assertEquals("The Enter some text must be before 7/2007", result?.error)
    }

    
    fun shouldAllowDateEqualsThanUpperLimit() {
        // Given
        editText?.upperLimit = Calendar.getInstance().apply {
            set(2007, Calendar.JULY, 1)
        }
        // When
        onView(editTextRef).perform(ViewActions.typeText("07/2007"))
        // Then
        Assert.assertEquals(true, editText?.isValid()?.isValid)
    }

    
    fun shouldAllowDateLessThanUpperLimit() {
        // Given
        editText?.upperLimit = Calendar.getInstance().apply {
            set(2007, Calendar.JULY, 1)
        }
        // When
        onView(editTextRef).perform(ViewActions.typeText("12/2006"))
        // Then
        Assert.assertEquals(true, editText?.isValid()?.isValid)
    }

    
    fun shouldGetRightMonthAndYear() {
        // When
        onView(editTextRef).perform(ViewActions.typeText("12/2006"))
        // Then
        Assert.assertEquals(11, editText?.getMonth())
        Assert.assertEquals(2006, editText?.getYear())
    }

    @Test
    fun shouldOpenDialog() {
        // When
        onView(editTextRef).perform(clickDrawable())
        onView(dpMonthRef).perform(setNumberPickerValue(11))
        onView(dpYearRef).perform(setNumberPickerValue(2008))
        onView(withSubstring("OK"))
            .check(ViewAssertions.matches(isDisplayed()))
            .perform(ViewActions.click())

        // Then
        Assert.assertEquals(11, editText?.getMonth())
        Assert.assertEquals(2008, editText?.getYear())
    }

    
    fun shouldAllowDateWithoutLimits() {
        // Given
        editText?.isRequired = true
        // When
        onView(editTextRef).perform(ViewActions.typeText("12/2021"))
        // Then
        val result = editText?.isValid()
        Assert.assertEquals(true, result?.isValid)
        Assert.assertEquals(EMPTY, result?.error)
    }

    
    fun shouldAllowDateEqualsThanLowerLimit() {
        // Given
        editText?.lowerLimit = Calendar.getInstance().apply {
            set(2019, Calendar.JUNE, 1)
        }
        // When
        onView(editTextRef).perform(ViewActions.typeText("06/2019"))
        // Then
        Assert.assertEquals(true, editText?.isValid()?.isValid)
    }

    
    fun shouldNotAllowDateLessThanLowerLimit() {
        // Given
        editText?.lowerLimit = Calendar.getInstance().apply {
            set(2019, Calendar.JUNE, 1)
        }
        // When
        onView(editTextRef).perform(ViewActions.typeText("05/2019"))
        // Then
        Assert.assertEquals(false, editText?.isValid()?.isValid)
    }

    
    fun shouldAllowDateGreaterThanLowerLimit() {
        // Given
        editText?.lowerLimit = Calendar.getInstance().apply {
            set(2019, Calendar.JUNE, 1)
        }
        // When
        onView(editTextRef).perform(ViewActions.typeText("05/2021"))
        // Then
        Assert.assertEquals(true, editText?.isValid()?.isValid)
    }

    fun shouldThrowPropertyNotImplementedExceptionWhenHasUpperLimitAndLowerLimit() {
        // Given
        editText?.lowerLimit = Calendar.getInstance().apply {
            set(2019, Calendar.JUNE, 1)
        }
        editText?.upperLimit = Calendar.getInstance().apply {
            set(2020, Calendar.JUNE, 1)
        }
        // When
        onView(editTextRef).perform(ViewActions.typeText("05/2021"))

        // Then
        ruleActivity.runOnUiThread {
            editText?.isValid()
        }
    }
}
