package co.condorlabs.customcomponents.test.simplecamerax

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import co.condorlabs.customcomponents.custombutton.CustomButton
import co.condorlabs.customcomponents.models.CameraConfig
import co.condorlabs.customcomponents.simplecamerax.CameraActivity
import co.condorlabs.customcomponents.test.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Alexis Duque on 2019-11-14.
 * @company Condor Labs.
 * @email eduque@condorlabs.io.
 */
class CameraActivityTest {

    @Rule
    @JvmField
    val ruleActivity = ActivityTestRule(CameraActivity::class.java, true, false)

    @Before
    fun grantPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().uiAutomation.executeShellCommand("pm grant " + getTargetContext().packageName + " android.permission.CAMERA")
        }
    }

    private fun launchActivity(
        cameraConfig: CameraConfig? = null,
        onActivity: (Activity) -> Unit = {}
    ) {
        onActivity(ruleActivity.launchActivity(Intent().putExtras(Bundle().apply {
            putParcelable(CameraActivity.CAMERA_CONFIG_OBJ_PARAM, cameraConfig)
        })))
    }

    @Test
    @LargeTest
    fun shouldDisplayDefaultScreen() {
        launchActivity(
            CameraConfig()
        ) {
            // Given
            val tvDescription = ruleActivity.activity.findViewById<TextView>(R.id.capturePhotoDescription)
            val cancelButton = ruleActivity.activity.findViewById<CustomButton>(R.id.btnCancelPhoto)
            val cropButton = ruleActivity.activity.findViewById<CustomButton>(R.id.btnCropPhoto)
            val fabCaptureButton = ruleActivity.activity.findViewById<FloatingActionButton>(R.id.fabCaptureButton)

            // When
            val cancelButtonText = cancelButton.text
            val cropButtonText = cropButton.text
            val btnCancelVisibility = cancelButton.visibility
            val btnCropCancelVisibility = cropButton.visibility
            val btnCaptureVisibility = fabCaptureButton.visibility
            val descriptionVisibility = tvDescription.visibility

            // Then
            Assert.assertEquals("Retake", cancelButtonText)
            Assert.assertEquals("Use photo", cropButtonText)
            Assert.assertEquals(View.INVISIBLE, btnCancelVisibility)
            Assert.assertEquals(View.INVISIBLE, btnCropCancelVisibility)
            Assert.assertEquals(View.VISIBLE, btnCaptureVisibility)
            Assert.assertEquals(View.GONE, descriptionVisibility)
        }
    }

    @Test
    @LargeTest
    fun shouldTakeAPhoto() {
        launchActivity(
            CameraConfig(
                titleText = "Front title",
                cancelButtonText = "Cancel",
                cropButtonText = "Crop"
            )
        ) {
            // Given
            val tvTitle = ruleActivity.activity.findViewById<TextView>(R.id.cameraTitle)
            val tvDescription = ruleActivity.activity.findViewById<TextView>(R.id.capturePhotoDescription)
            val cancelButton = ruleActivity.activity.findViewById<CustomButton>(R.id.btnCancelPhoto)
            val cropButton = ruleActivity.activity.findViewById<CustomButton>(R.id.btnCropPhoto)
            val fabCaptureButton = ruleActivity.activity.findViewById<FloatingActionButton>(R.id.fabCaptureButton)

            // When
            ruleActivity.runOnUiThread { fabCaptureButton.performClick() }
            Thread.sleep(2000)

            // Then
            Assert.assertEquals("Cancel", cancelButton.text)
            Assert.assertEquals("Crop", cropButton.text)
            Assert.assertEquals("Front title", tvTitle.text)
            Assert.assertEquals(View.VISIBLE, cancelButton.visibility)
            Assert.assertEquals(View.VISIBLE, cropButton.visibility)
            Assert.assertEquals(View.INVISIBLE, fabCaptureButton.visibility)
            Assert.assertEquals(View.GONE, tvDescription.visibility)
        }
    }

    @Test
    @LargeTest
    fun shouldTakeAPhotoAndCancel() {
        launchActivity(
            CameraConfig()
        ) {
            // Given
            val tvDescription = ruleActivity.activity.findViewById<TextView>(R.id.capturePhotoDescription)
            val cancelButton = ruleActivity.activity.findViewById<CustomButton>(R.id.btnCancelPhoto)
            val cropButton = ruleActivity.activity.findViewById<CustomButton>(R.id.btnCropPhoto)
            val fabCaptureButton = ruleActivity.activity.findViewById<FloatingActionButton>(R.id.fabCaptureButton)
            ruleActivity.runOnUiThread { fabCaptureButton.performClick() }
            Thread.sleep(2000)

            // When
            ruleActivity.runOnUiThread { cancelButton.performClick() }

            // Then
            Assert.assertEquals(View.GONE, tvDescription.visibility)
            Assert.assertEquals(View.INVISIBLE, cancelButton.visibility)
            Assert.assertEquals(View.INVISIBLE, cropButton.visibility)
            Assert.assertEquals(View.VISIBLE, fabCaptureButton.visibility)
        }
    }

    @Test
    @LargeTest
    fun shouldTakeAPhotoAndCrop() {
        launchActivity(
            CameraConfig()
        ) {
            // Given
            val cropButton = ruleActivity.activity.findViewById<CustomButton>(R.id.btnCropPhoto)
            val fabCaptureButton = ruleActivity.activity.findViewById<FloatingActionButton>(R.id.fabCaptureButton)
            ruleActivity.runOnUiThread { fabCaptureButton.performClick() }
            Thread.sleep(2000)

            // When
            ruleActivity.runOnUiThread { cropButton.performClick() }
        }
    }
}