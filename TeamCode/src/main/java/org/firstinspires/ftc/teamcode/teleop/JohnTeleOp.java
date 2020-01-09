package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.JohnRobot;

@TeleOp(name = "John Tele Op", group = "John")
public class JohnTeleOp extends OpMode {
    JohnRobot robot;

    @Override
    public void init() {
        robot = new JohnRobot(hardwareMap);
        robot.init();

        // Compensate for the fact that the motors all face a different direction.
        robot.wheels.lf.setDirection(DcMotor.Direction.REVERSE);
        robot.wheels.rf.setDirection(DcMotor.Direction.FORWARD);
        robot.wheels.lb.setDirection(DcMotor.Direction.REVERSE);
        robot.wheels.rb.setDirection(DcMotor.Direction.FORWARD);

        robot.wheels.lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.wheels.rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.wheels.lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.wheels.rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Robot", "Ready");
        telemetry.update();
    }


    @Override
    public void loop() {
        robot.go(gamepad1, gamepad2);
        robot.lift.go(-gamepad2.left_stick_y);
        robot.rotate.setPower(-gamepad2.right_stick_y / 2);
        robot.wrist.setPower((gamepad2.right_trigger - gamepad2.left_trigger) / 2);

        //// counter-rotate rotate to compensate for angle changes if lift is the only thing moving
        /* if (gamepad2.left_stick_y != 0 && gamepad2.right_stick_y == 0) {
            robot.rotate.setPower((gamepad2.left_stick_y) / 5);
        } */

        telemetry.addData("alpha", robot.color_sensor_down.alpha());
        if (gamepad1.a)
            robot.color_sensor_down.enableLed(true);
        else
            robot.color_sensor_down.enableLed(false);

        if (gamepad1.b) {
            robot.wheels.encoderReset();
            telemetry.addData("Encoders", "Reset");
        } else
            telemetry.addData("Left Encoder Average", robot.wheels.encoderAverageLeft());

        if (gamepad2.left_bumper /* close */ ) {
            robot.lGrab.setPosition(.15);
            robot.rGrab.setPosition(.8);
        } else if (gamepad2.right_bumper /* open */ ) {
            robot.lGrab.setPosition(.5);
            robot.rGrab.setPosition(.45);
        }

        /* if (gamepad2.b) {
            robot.drag.setPosition(0);
        } else if (gamepad2.a) {
            robot.drag.setPosition(1);
        } */
    }
}
