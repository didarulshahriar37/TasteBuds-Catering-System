package org.example;

public class Driver {
    private String name;
    private String password;
    private String drivingLicense;
    private boolean available;

    public Driver(String name, String password, String drivingLicense, boolean available) {
        this.name = name;
        this.password = password;
        this.drivingLicense = drivingLicense;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
