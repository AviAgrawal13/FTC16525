package org.firstinspires.ftc.teamcode.skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@Autonomous(name = "ForwardMovementTesting", group = "Linear Opmode")

public class SahasTesting extends EverythingConfig {
    
    public void runOpMode() {
        
        initializeHardware();
        
        
        waitForStart();

        while (opModeIsActive()) {
            
            Forward(10);
            Backward(10);
            TurnLeft(10);
            TurnRight(10);
            StrafeRight(10);
            StrafeLeft(10);
        
                    
    
    
            
        }
    }
}
