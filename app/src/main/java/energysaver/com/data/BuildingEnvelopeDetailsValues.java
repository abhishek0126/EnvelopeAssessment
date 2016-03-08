package energysaver.com.data;

/**
 * Created by abhishekkumar on 06/03/16.
 */
public class BuildingEnvelopeDetailsValues {

    //BuildingEnvelopeDetailsActivity.java member variables
    private int windowToWallRatio;
    private int glazingSolarGainCoefficient;
    private int glazingUFactor;
    private int visibleLightTransmittance;
    private int airLeakageRatio;
    private int skylightSolarHeatCoefficient;
    private int skyLightUFactor;
    private int roofUFactor;
    private String roofInsulationType;
    private int roofThickness;
    private int roofRValue;

    private String buildingId;

    public int getWindowToWallRatio() {
        return windowToWallRatio;
    }

    public void setWindowToWallRatio(int windowToWallRatio) {
        this.windowToWallRatio = windowToWallRatio;
    }

    public int getGlazingSolarGainCoefficient() {
        return glazingSolarGainCoefficient;
    }

    public void setGlazingSolarGainCoefficient(int glazingSolarGainCoefficient) {
        this.glazingSolarGainCoefficient = glazingSolarGainCoefficient;
    }

    public int getGlazingUFactor() {
        return glazingUFactor;
    }

    public void setGlazingUFactor(int glazingUFactor) {
        this.glazingUFactor = glazingUFactor;
    }

    public int getVisibleLightTransmittance() {
        return visibleLightTransmittance;
    }

    public void setVisibleLightTransmittance(int visibleLightTransmittance) {
        this.visibleLightTransmittance = visibleLightTransmittance;
    }

    public int getAirLeakageRatio() {
        return airLeakageRatio;
    }

    public void setAirLeakageRatio(int airLeakageRatio) {
        this.airLeakageRatio = airLeakageRatio;
    }

    public int getSkylightSolarHeatCoefficient() {
        return skylightSolarHeatCoefficient;
    }

    public void setSkylightSolarHeatCoefficient(int skylightSolarHeatCoefficient) {
        this.skylightSolarHeatCoefficient = skylightSolarHeatCoefficient;
    }

    public int getSkyLightUFactor() {
        return skyLightUFactor;
    }

    public void setSkyLightUFactor(int skyLightUFactor) {
        this.skyLightUFactor = skyLightUFactor;
    }

    public int getRoofUFactor() {
        return roofUFactor;
    }

    public void setRoofUFactor(int roofUFactor) {
        this.roofUFactor = roofUFactor;
    }

    public String getRoofInsulationType() {
        return roofInsulationType;
    }

    public void setRoofInsulationType(String roofInsulationType) {
        this.roofInsulationType = roofInsulationType;
    }

    public int getRoofThickness() {
        return roofThickness;
    }

    public void setRoofThickness(int roofThickness) {
        this.roofThickness = roofThickness;
    }

    public int getRoofRValue() {
        return roofRValue;
    }

    public void setRoofRValue(int roofRValue) {
        this.roofRValue = roofRValue;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

}
