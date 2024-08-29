package frc.robot;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Spec {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Message {
        @JsonProperty("BusUtilPerc")
        public double busUtilPerc;

        @JsonProperty("DeviceArray")
        public List<Device> deviceArray;

        @JsonProperty("GeneralReturn")
        public GeneralReturn generalReturn;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Device {
        @JsonProperty("BootloaderRev")
        public String bootloaderRev;

        @JsonProperty("CANbus")
        public String canBus;

        @JsonProperty("CANivoreDevName")
        public String canIvoreDevName;

        @JsonProperty("CurrentVers")
        public String currentVers;

        @JsonProperty("HardwareRev")
        public String hardwareRev;

        @JsonProperty("ID")
        public int id;

        @JsonProperty("IsPROApplication")
        public boolean isProApplication;

        @JsonProperty("IsPROLicensed")
        public boolean isProLicensed;

        @JsonProperty("LicenseCapacity")
        public int licenseCapacity;

        @JsonProperty("LicenseResponseCode")
        public String licenseResponseCode;

        @JsonProperty("Licenses")
        public String[] licenses;

        @JsonProperty("LicensesValid")
        public boolean licensesValid;

        @JsonProperty("ManDate")
        public String manDate;

        @JsonProperty("Model")
        public String model;

        @JsonProperty("Name")
        public String name;

        @JsonProperty("SerialNo")
        public String serialNo;

        @JsonProperty("SoftStatus")
        public String softStatus;

        @JsonProperty("SupportsConfigs")
        public boolean supportsConfigs;

        @JsonProperty("SupportsControl_v2")
        public boolean supportsControlV2;

        @JsonProperty("SupportsDecoratedSelfTest")
        public boolean supportsDecoratedSelfTest;

        @JsonProperty("SupportsLicensing")
        public boolean supportsLicensing;

        @JsonProperty("Vendor")
        public String vendor;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeneralReturn {
        @JsonProperty("Action")
        public String action;

        @JsonProperty("CANbus")
        public String canBus;

        @JsonProperty("Error")
        public int error;

        @JsonProperty("ErrorMessage")
        public String errorMessage;

        @JsonProperty("ID")
        public int id;

        @JsonProperty("Model")
        public String model;
    }
}
