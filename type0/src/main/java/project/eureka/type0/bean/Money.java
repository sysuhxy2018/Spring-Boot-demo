package project.eureka.type0.bean;

public class Money {
	private int id;
	private Integer basic;
	private Integer reward;
	private Integer punishment;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getBasic() {
		return basic;
	}
	public void setBasic(Integer basic) {
		this.basic = basic;
	}
	public Integer getReward() {
		return reward;
	}
	public void setReward(Integer reward) {
		this.reward = reward;
	}
	public Integer getPunishment() {
		return punishment;
	}
	public void setPunishment(Integer punishment) {
		this.punishment = punishment;
	}
	@Override
	public String toString() {
		return "Money [id=" + id + ", basic=" + basic + ", reward=" + reward + ", punishment=" + punishment + "]";
	}
	
}
