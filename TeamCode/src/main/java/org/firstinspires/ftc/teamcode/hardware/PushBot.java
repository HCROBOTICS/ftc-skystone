package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PushBot {
    private HardwareMap hw;
    public Wheels wheels;

    public PushBot(HardwareMap hw) {
        this.hw = hw;
        wheels = new Wheels(hw);
    }

    public void init() {
        wheels.init();
        wheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void stop() {
        wheels.stop();
    }
}
