public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        Powerable pjPower = reg.getFirstOfType(Projector.class);
        Connectable pjConn = reg.getFirstOfType(Projector.class);
        pjPower.powerOn();
        pjConn.connectInput("HDMI-1");

        Dimmable lights = reg.getFirstOfType(LightsPanel.class);
        lights.setBrightness(60);

        Thermal ac = reg.getFirstOfType(AirConditioner.class);
        ac.setTemperatureC(24);

        Scanner scan = reg.getFirstOfType(AttendanceScanner.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstOfType(Projector.class).powerOff();
        reg.getFirstOfType(LightsPanel.class).powerOff();
        reg.getFirstOfType(AirConditioner.class).powerOff();
    }
}
