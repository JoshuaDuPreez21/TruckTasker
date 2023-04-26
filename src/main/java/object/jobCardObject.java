package object;

public class jobCardObject {

	private Long id;
	private String siteName;
	private String siteLatitude;
	private String siteLongitude;
	private String description;
	private String estimedJobTime;
	private String startTime;
	private String endTime;
	private String actualTime;
	private Long idAdmin;
	private Double hourlyRate;
	private Double totalCost;
	private Long idTechnician;
	private String status; // planned, en-route, in progress, delayed, completed
	private Double travelTime; // in seconds
	private Double travelKm;
	private Double waitingTime; // in seconds
	private Long idFleet;
	private String orderNumber;
	private String costCode;
	private String client;
	private Long idClient;
	private String createdTime;
	private String updatedTime;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteLatitude() {
		return siteLatitude;
	}
	public void setSiteLatitude(String siteLatitude) {
		this.siteLatitude = siteLatitude;
	}
	public String getSiteLongitude() {
		return siteLongitude;
	}
	public void setSiteLongitude(String siteLongitude) {
		this.siteLongitude = siteLongitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEstimedJobTime() {
		return estimedJobTime;
	}
	public void setEstimedJobTime(String estimedJobTime) {
		this.estimedJobTime = estimedJobTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getActualTime() {
		return actualTime;
	}
	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}
	public Long getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}
	public Double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(Double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public Long getIdTechnician() {
		return idTechnician;
	}
	public void setIdTechnician(Long idTechnician) {
		this.idTechnician = idTechnician;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(Double travelTime) {
		this.travelTime = travelTime;
	}
	public Double getTravelKm() {
		return travelKm;
	}
	public void setTravelKm(Double travelKm) {
		this.travelKm = travelKm;
	}
	public Double getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(Double waitingTime) {
		this.waitingTime = waitingTime;
	}
	public Long getIdFleet() {
		return idFleet;
	}
	public void setIdFleet(Long idFleet) {
		this.idFleet = idFleet;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCostCode() {
		return costCode;
	}
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	

	

}
