package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.auto.ControllerCommand;

import static java.lang.Math.min;

public class Odometer {
    private Wheels wheels;
    private Position position;

    private final int GO_SPEED_BOUND = 500;
    private int progress = 0;
    private double speed = 0;

    public Odometer(Wheels wheels, Position position) {
        this.wheels = wheels;
        this.position = position;
    }

    /* positive is forwards and negative is backwards. */
    public double getDistanceForward() {
        double r = (wheels.encoderAverageLeft() + wheels.encoderAverageRight()) / 2;
        r /= wheels.ticksPerInch();

        return r;
    }

    /* positive is right and negative is left. */
    public double getDistanceRight() {
        double r = (wheels.encoderAverageFront() + wheels.encoderAverageBack()) / 2;
        r /= wheels.ticksPerInch();

        return r;
    }

    /* goDistanceForward() is iterative. It returns true until it reaches its target. */
    public boolean goDistanceForward(double inches) {
        wheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        int targetTicks = (int)(wheels.ticksPerInch() * inches);
        int currentTicks = (wheels.encoderAverageLeft() + wheels.encoderAverageRight()) / 2;
        int distanceFromTarget = targetTicks - currentTicks;

        // Yeah, this will need some improvement.
        float speed_down = distanceFromTarget / GO_SPEED_BOUND; // as it slows down near the target
        float speed_up  = -currentTicks       / GO_SPEED_BOUND; // as it speeds up from the start
        float speed = min(speed_down, speed_up);
        if (speed > 1) speed = 1;
        wheels.go(new ControllerCommand(0, -speed, 0, 0));

        /* if (currentTicks > GO_SPEED_BOUND && distanceFromTarget > GO_SPEED_BOUND)
            // full speed forward
            wheels.go(new ControllerCommand(ControllerCommand.Command.FORWARD));
        else if (distanceFromTarget < GO_SPEED_BOUND) {
            float r = distanceFromTarget / GO_SPEED_BOUND;
            // go forwards at partial speed
            wheels.go(new ControllerCommand(0, -r, 0, 0));
        } else if (progress < GO_SPEED_BOUND) {
            float r = progress / GO_SPEED_BOUND;
            // same thing
            wheels.go(new ControllerCommand(0, -r, 0, 0));
        } */

        return true;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}