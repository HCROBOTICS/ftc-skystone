// waiting on values test

package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.JohnRobot;

@Disabled
@Autonomous (name = "John Complex Blue Bridge")
public class JohnComplexBlueBridge extends JohnAuto {

    @Override
    public void runOpMode() {
        robot = new JohnRobot(hardwareMap);
        robot.init();
        wheelsInit();
        resetRotate();

        waitForStart();

        while (opModeIsActive()) {

            initGrab();

            forward(INITIAL_FORWARD);
            sleep(SLEEP_TIME);
            turnRight(TURN);
            sleep(SLEEP_TIME);
            forward(1000);

            while (robot.color_sensor_side.red() > RED_SENSOR_VALUE) {
                robot.wheels.go(new ControllerCommand(ControllerCommand.Command.FORWARD));
            }

            sleep(SLEEP_TIME);
            turnLeft(TURN);
            sleep(SLEEP_TIME);
            forward(100);
            sleep(SLEEP_TIME);

            grab_skystone();

            sleep(SLEEP_TIME);
            backward(100);
            sleep(SLEEP_TIME);
            turnLeft(TURN);
            sleep(SLEEP_TIME);

            driveToLine();
            forward(4000);

            sleep(SLEEP_TIME);
            turnRight(TURN);
            sleep(SLEEP_TIME);

            release_skystone();

            sleep(SLEEP_TIME);
            backward(300);
            sleep(SLEEP_TIME);
            turnRight(TURN);
            sleep(SLEEP_TIME);

            driveToLine();

            break;
        }
    }
}
