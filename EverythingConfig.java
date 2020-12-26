package org.firstinspires.ftc.teamcode.skystone;

import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public abstract class EverythingConfig extends LinearOpMode {


    protected DcMotor frontleftdrive;
    protected DcMotor frontrightdrive;
    protected DcMotor backleftdrive;
    protected DcMotor backrightdrive;
    protected DcMotor toproller;
    protected DcMotor bottomroller;
    protected DcMotor intake;
    protected Servo servoleft;
    protected Servo servoright;
    protected Servo topgrab;
    protected Servo bottomgrab;


    

    protected void initializeHardware() {
        //Doing all the hardware mappings
        frontleftdrive = hardwareMap.get(DcMotor.class, "frontleftdrive");
        backrightdrive = hardwareMap.get(DcMotor.class, "backrightdrive");
        backleftdrive = hardwareMap.get(DcMotor.class, "backleftdrive");
        frontrightdrive = hardwareMap.get(DcMotor.class, "frontrightdrive");
        toproller = hardwareMap.get(DcMotor.class, "toproller");
        bottomroller = hardwareMap.get(DcMotor.class, "bottomroller");
        intake = hardwareMap.get(DcMotor.class, "intake");
        servoleft = hardwareMap.get(Servo.class, "servoleft");
        servoright = hardwareMap.get(Servo.class, "servoright");
        topgrab = hardwareMap.get(Servo.class, "topgrab");
        bottomgrab = hardwareMap.get(Servo.class, "bottomgrab");
        // Telling Driver that Hardware is Configured
        telemetry.addData("Status: ", "Hardware Configured");
        telemetry.update();


        backleftdrive.setDirection(DcMotor.Direction.FORWARD);
        frontrightdrive.setDirection(DcMotor.Direction.FORWARD);
        frontleftdrive.setDirection(DcMotor.Direction.REVERSE);
        backrightdrive.setDirection(DcMotor.Direction.REVERSE);

        toproller.setDirection(DcMotor.Direction.FORWARD);
        bottomroller.setDirection(DcMotor.Direction.REVERSE);
        
        
        frontleftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightdrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




        telemetry.addData("Status: ", "Hardware Direction Configured");
        telemetry.update();



    }

    protected void Forward(int distance) {

        frontleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        frontleftdrive.setTargetPosition(distance * 90);
        frontrightdrive.setTargetPosition(distance * 90);
        backleftdrive.setTargetPosition(distance * 90);
        backrightdrive.setTargetPosition(distance * 90);

        // set motors to run to target encoder position and stop with brakes on.
        frontleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        frontleftdrive.setPower(0.25);
        frontrightdrive.setPower(0.25);
        backleftdrive.setPower(0.25);
        backrightdrive.setPower(0.25);

        while (frontleftdrive.isBusy() && frontrightdrive.isBusy() && backleftdrive.isBusy() && backrightdrive.isBusy()) {
            telemetry.addData("encoder-front-left", frontleftdrive.getCurrentPosition());
            telemetry.addData("encoder-front-right", frontrightdrive.getCurrentPosition());
            telemetry.addData("encoder-back-left", backleftdrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", frontrightdrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        frontleftdrive.setPower(0);
        frontrightdrive.setPower(0);
        backleftdrive.setPower(0);
        backrightdrive.setPower(0);
        sleep(1000);







    }

    protected void Backward(int distance) {

        frontleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        frontleftdrive.setTargetPosition(-(distance * 90));
        frontrightdrive.setTargetPosition(-(distance * 90));
        backleftdrive.setTargetPosition(-(distance * 90));
        backrightdrive.setTargetPosition(-(distance * 90));

        // set motors to run to target encoder position and stop with brakes on.
        frontleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        frontleftdrive.setPower(-0.25);
        frontrightdrive.setPower(-0.25);
        backleftdrive.setPower(-0.25);
        backrightdrive.setPower(-0.25);


        while (frontleftdrive.isBusy() && frontrightdrive.isBusy() && backleftdrive.isBusy() && backrightdrive.isBusy()) {
            telemetry.addData("encoder-front-left", frontleftdrive.getCurrentPosition());
            telemetry.addData("encoder-front-right", frontrightdrive.getCurrentPosition());
            telemetry.addData("encoder-back-left", backleftdrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", frontrightdrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        frontleftdrive.setPower(0);
        frontrightdrive.setPower(0);
        backleftdrive.setPower(0);
        backrightdrive.setPower(0);
        sleep(1000);



    }

    protected void TurnLeft(int distance) {
        frontleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontleftdrive.setTargetPosition(-(distance * 90));
        frontrightdrive.setTargetPosition(distance * 90);
        backleftdrive.setTargetPosition(-(distance * 90));
        backrightdrive.setTargetPosition(distance * 90);

        // set motors to run to target encoder position and stop with brakes on.
        frontleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        frontleftdrive.setPower(-0.25);
        frontrightdrive.setPower(0.25);
        backleftdrive.setPower(-0.25);
        backrightdrive.setPower(0.25);

        while (frontleftdrive.isBusy() && frontrightdrive.isBusy() && backleftdrive.isBusy() && backrightdrive.isBusy()) {
            telemetry.addData("encoder-front-left", frontleftdrive.getCurrentPosition());
            telemetry.addData("encoder-front-right", frontrightdrive.getCurrentPosition());
            telemetry.addData("encoder-back-left", backleftdrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", frontrightdrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        frontleftdrive.setPower(0);
        frontrightdrive.setPower(0);
        backleftdrive.setPower(0);
        backrightdrive.setPower(0);
        sleep(1000);



    }

    protected void TurnRight(int distance) {

        frontleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontleftdrive.setTargetPosition(distance * 90);
        frontrightdrive.setTargetPosition(-(distance * 90));
        backleftdrive.setTargetPosition(distance * 90);
        backrightdrive.setTargetPosition(-(distance * 90));

        // set motors to run to target encoder position and stop with brakes on.
        frontleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);





        frontleftdrive.setPower(0.25);
        frontrightdrive.setPower(-0.25);
        backleftdrive.setPower(0.25);
        backrightdrive.setPower(-0.25);

        while (frontleftdrive.isBusy() && frontrightdrive.isBusy() && backleftdrive.isBusy() && backrightdrive.isBusy()) {
            telemetry.addData("encoder-front-left", frontleftdrive.getCurrentPosition());
            telemetry.addData("encoder-front-right", frontrightdrive.getCurrentPosition());
            telemetry.addData("encoder-back-left", backleftdrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", frontrightdrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        frontleftdrive.setPower(0);
        frontrightdrive.setPower(0);
        backleftdrive.setPower(0);
        backrightdrive.setPower(0);
        sleep(1000);


    }

    protected void StrafeRight(int distance) {

        frontleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        frontleftdrive.setTargetPosition(distance * 90);
        frontrightdrive.setTargetPosition(-(distance * 90));
        backleftdrive.setTargetPosition(-(distance * 90));
        backrightdrive.setTargetPosition(distance * 90);

        // set motors to run to target encoder position and stop with brakes on.
        frontleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        frontleftdrive.setPower(0.25);
        frontrightdrive.setPower(-0.25);
        backleftdrive.setPower(-0.25);
        backrightdrive.setPower(0.25);


        while (frontleftdrive.isBusy() && frontrightdrive.isBusy() && backleftdrive.isBusy() && backrightdrive.isBusy()) {
            telemetry.addData("encoder-front-left", frontleftdrive.getCurrentPosition());
            telemetry.addData("encoder-front-right", frontrightdrive.getCurrentPosition());
            telemetry.addData("encoder-back-left", backleftdrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", frontrightdrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        frontleftdrive.setPower(0);
        frontrightdrive.setPower(0);
        backleftdrive.setPower(0);
        backrightdrive.setPower(0);
        sleep(1000);


    }

    protected void StrafeLeft(int distance) {

        frontleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        frontleftdrive.setTargetPosition(-(distance * 90));
        frontrightdrive.setTargetPosition(distance * 90);
        backleftdrive.setTargetPosition(distance * 90);
        backrightdrive.setTargetPosition(-(distance * 90));

        // set motors to run to target encoder position and stop with brakes on.
        frontleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        frontleftdrive.setPower(-0.25);
        frontrightdrive.setPower(0.25);
        backleftdrive.setPower(0.25);
        backrightdrive.setPower(-0.25);





        while (frontleftdrive.isBusy() && frontrightdrive.isBusy() && backleftdrive.isBusy() && backrightdrive.isBusy()) {
            telemetry.addData("encoder-front-left", frontleftdrive.getCurrentPosition());
            telemetry.addData("encoder-front-right", frontrightdrive.getCurrentPosition());
            telemetry.addData("encoder-back-left", backleftdrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", frontrightdrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        frontleftdrive.setPower(0);
        frontrightdrive.setPower(0);
        backleftdrive.setPower(0);
        backrightdrive.setPower(0);
        sleep(1000);




    }
    protected void setServo(double pos) {
        servoleft.setPosition(pos);
        servoright.setPosition(1 - pos);
        telemetry.addData("Status", "Servo set to " + pos);
        telemetry.update();
    }
    protected void ServoGrab(double grabpos) {
        topgrab.setPosition(grabpos);
    }
    
    
}   
