package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


public class RingDetectionExample extends org.firstinspires.ftc.teamcode.skystone.EverythingConfig {
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";
    private int RingAmount;

    private static final String VUFORIA_KEY = "ATTRFhT/////AAABmeuU4pPqwkWQpdWB0mGpIXBvTd9EJJ+EjtrJ1fg3Ru0ChVOZB/vhpDZdxae9/JeuxcEWdk6iPegz2CnouzBHApfMjWj87mnni8gB12rn1gqguZoDacVwTIbkScWQKuJnWNHTkw0bzjlU6BpbO0gZgisjl8e+2XAeRkzrB/vPayFy/guzHaJErELJypA0Ebgpt9CgxPbTrm0WLnuufz2+Ocyt5Rjz6B8tVfmBMnKo8a+paDYQcbSWsIQmar9NcrHTXVHoOY6sOorn3mcfFqYKIx09xbS45SSXcCHhxLla73psp5Twx1tL5MSVEX7/54KWlgz8wRAOHg/kxm9WyK6Q15pIxA2aYICpYCdK2NOWpQZi";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {

        initializeHardware();

        initVuforia();
        initTfod();


        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2, 2);
        }

        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();


        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {


                if (tfod != null) {

                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("Rings", RingAmount);
                        telemetry.addData("# Object Detected", updatedRecognitions.size());

                        if(updatedRecognitions.size() == 0) {
                            RingAmount = 0;
                        }

                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());

                            if (200 < recognition.getTop() && recognition.getTop() < 230) {
                                RingAmount = 4;
                            }
                            else if (275 < recognition.getTop() && recognition.getTop() < 286 ) {
                                RingAmount = 1;
                            }
                            else {
                                RingAmount = 0;
                            }


                        }
                        telemetry.update();
                    }
                }

                if (RingAmount = 4) {
                    Forward(120);
                    TurnLeft(10);
                }
                else if (RingAmount = 1)   {
                    Forward(97);
                }
                else if(RingAmount = 0) {
                    Forward(73);
                    TurnLeft(10);
                }

            }

        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }


    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");


        vuforia = ClassFactory.getInstance().createVuforia(parameters);


    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.7f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}
