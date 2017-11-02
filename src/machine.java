import javax.swing.JLabel;

public class machine {
	private int machineNum;
	private String machineStatus;
	private String machineType;
	private int timeLeft;
	
	/**
	 * 
	 * @param num = machine number
	 * @param type = either Washer or Dryer;
	 * @param time = time remaining in cycle
	 */
	public machine(int num, String type, int time, String status){
		this.machineNum = num;
		this.machineType = type;
		this.timeLeft = time;
		this.machineStatus = status;
	}
	
	public int getNum(){
		return this.machineNum;
	}
	
	public String getStatus(){
		return this.machineStatus;
	}
	
	public String getType(){
		return this.machineType;
	}
	
	public int getTime(){
		return this.timeLeft;
	}

}
