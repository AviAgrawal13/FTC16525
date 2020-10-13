package org.firstinspires.ftc.teamcode.skystone;

import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Tele Op", group = "Linear Opmode")

public class Driving extends LinearOpMode {
    
    private double drive;
    private double strafe;
    private double turning;
    private float rollers;
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;
    private DcMotor toproller = null;
    private DcMotor bottomroller = null;


    @Override
    public void runOpMode() throws InterruptedException {
        //Doing all the hardware mappings
        frontleftDrive = hardwareMap.get(DcMotor.class, "frontleftdrive");
        backrightDrive = hardwareMap.get(DcMotor.class, "backrightdrive");
        backleftDrive = hardwareMap.get(DcMotor.class, "backleftdrive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "frontrightdrive");
        toproller = hardwareMap.get(DcMotor.class, "toproller");
        bottomroller = hardwareMap.get(DcMotor.class, "bottomroller");
        // Telling Driver that Hardware is Configured
        telemetry.addData("Status: ", "Hardware Configured");
        telemetry.update();
        
        drive = 0;
        strafe = 0;
        turning = 0;
        rollers = 0;
        
        
        backleftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontrightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontleftDrive.setDirection(DcMotor.Direction.FORWARD);
        backrightDrive.setDirection(DcMotor.Direction.REVERSE);
        
        toproller.setDirection(DcMotor.Direction.FORWARD);
        bottomroller.setDirection(DcMotor.Direction.REVERSE);
        
        telemetry.addData("Status: ", "Hardware Direction Configured");
        telemetry.update();
        
        waitForStart();
